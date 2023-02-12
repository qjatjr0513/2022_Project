package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.p002v1.MapValue;
import com.google.firestore.p002v1.Value;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class ObjectValue implements Cloneable {
    private final Map<String, Object> overlayMap;
    private Value partialValue;

    public static ObjectValue fromMap(Map<String, Value> value) {
        return new ObjectValue((Value) Value.newBuilder().setMapValue(MapValue.newBuilder().putAllFields(value)).build());
    }

    public ObjectValue(Value value) {
        this.overlayMap = new HashMap();
        Assert.hardAssert(value.getValueTypeCase() == Value.ValueTypeCase.MAP_VALUE, "ObjectValues should be backed by a MapValue", new Object[0]);
        Assert.hardAssert(!ServerTimestamps.isServerTimestamp(value), "ServerTimestamps should not be used as an ObjectValue", new Object[0]);
        this.partialValue = value;
    }

    public ObjectValue() {
        this((Value) Value.newBuilder().setMapValue(MapValue.getDefaultInstance()).build());
    }

    public Map<String, Value> getFieldsMap() {
        return buildProto().getMapValue().getFieldsMap();
    }

    public FieldMask getFieldMask() {
        return extractFieldMask(buildProto().getMapValue());
    }

    private FieldMask extractFieldMask(MapValue value) {
        Set<FieldPath> fields = new HashSet<>();
        for (Map.Entry<String, Value> entry : value.getFieldsMap().entrySet()) {
            FieldPath currentPath = FieldPath.fromSingleSegment(entry.getKey());
            if (Values.isMapValue(entry.getValue())) {
                Set<FieldPath> nestedFields = extractFieldMask(entry.getValue().getMapValue()).getMask();
                if (nestedFields.isEmpty()) {
                    fields.add(currentPath);
                } else {
                    for (FieldPath nestedPath : nestedFields) {
                        fields.add((FieldPath) currentPath.append(nestedPath));
                    }
                }
            } else {
                fields.add(currentPath);
            }
        }
        return FieldMask.fromSet(fields);
    }

    public Value get(FieldPath fieldPath) {
        return extractNestedValue(buildProto(), fieldPath);
    }

    private Value extractNestedValue(Value value, FieldPath fieldPath) {
        if (fieldPath.isEmpty()) {
            return value;
        }
        for (int i = 0; i < fieldPath.length() - 1; i++) {
            value = value.getMapValue().getFieldsOrDefault(fieldPath.getSegment(i), (Value) null);
            if (!Values.isMapValue(value)) {
                return null;
            }
        }
        return value.getMapValue().getFieldsOrDefault(fieldPath.getLastSegment(), (Value) null);
    }

    private Value buildProto() {
        synchronized (this.overlayMap) {
            MapValue mergedResult = applyOverlay(FieldPath.EMPTY_PATH, this.overlayMap);
            if (mergedResult != null) {
                this.partialValue = (Value) Value.newBuilder().setMapValue(mergedResult).build();
                this.overlayMap.clear();
            }
        }
        return this.partialValue;
    }

    public void delete(FieldPath path) {
        Assert.hardAssert(!path.isEmpty(), "Cannot delete field for empty path on ObjectValue", new Object[0]);
        setOverlay(path, (Value) null);
    }

    public void set(FieldPath path, Value value) {
        Assert.hardAssert(!path.isEmpty(), "Cannot set field for empty path on ObjectValue", new Object[0]);
        setOverlay(path, value);
    }

    public void setAll(Map<FieldPath, Value> data) {
        for (Map.Entry<FieldPath, Value> entry : data.entrySet()) {
            FieldPath path = entry.getKey();
            if (entry.getValue() == null) {
                delete(path);
            } else {
                set(path, entry.getValue());
            }
        }
    }

    private void setOverlay(FieldPath path, Value value) {
        Map<String, Object> currentLevel = this.overlayMap;
        for (int i = 0; i < path.length() - 1; i++) {
            String currentSegment = path.getSegment(i);
            Object currentValue = currentLevel.get(currentSegment);
            if (currentValue instanceof Map) {
                currentLevel = (Map) currentValue;
            } else if (!(currentValue instanceof Value) || ((Value) currentValue).getValueTypeCase() != Value.ValueTypeCase.MAP_VALUE) {
                Map<String, Object> nextLevel = new HashMap<>();
                currentLevel.put(currentSegment, nextLevel);
                currentLevel = nextLevel;
            } else {
                Map<String, Object> nextLevel2 = new HashMap<>(((Value) currentValue).getMapValue().getFieldsMap());
                currentLevel.put(currentSegment, nextLevel2);
                currentLevel = nextLevel2;
            }
        }
        currentLevel.put(path.getLastSegment(), value);
    }

    private MapValue applyOverlay(FieldPath currentPath, Map<String, Object> currentOverlays) {
        MapValue.Builder resultAtPath;
        boolean modified = false;
        Value existingValue = extractNestedValue(this.partialValue, currentPath);
        if (Values.isMapValue(existingValue)) {
            resultAtPath = (MapValue.Builder) existingValue.getMapValue().toBuilder();
        } else {
            resultAtPath = MapValue.newBuilder();
        }
        for (Map.Entry<String, Object> entry : currentOverlays.entrySet()) {
            String pathSegment = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                MapValue nested = applyOverlay((FieldPath) currentPath.append(pathSegment), (Map) value);
                if (nested != null) {
                    resultAtPath.putFields(pathSegment, (Value) Value.newBuilder().setMapValue(nested).build());
                    modified = true;
                }
            } else if (value instanceof Value) {
                resultAtPath.putFields(pathSegment, (Value) value);
                modified = true;
            } else if (resultAtPath.containsFields(pathSegment)) {
                Assert.hardAssert(value == null, "Expected entry to be a Map, a Value or null", new Object[0]);
                resultAtPath.removeFields(pathSegment);
                modified = true;
            }
        }
        if (modified) {
            return (MapValue) resultAtPath.build();
        }
        return null;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof ObjectValue) {
            return Values.equals(buildProto(), ((ObjectValue) o).buildProto());
        }
        return false;
    }

    public int hashCode() {
        return buildProto().hashCode();
    }

    public String toString() {
        return "ObjectValue{internalValue=" + Values.canonicalId(buildProto()) + '}';
    }

    public ObjectValue clone() {
        return new ObjectValue(buildProto());
    }
}
