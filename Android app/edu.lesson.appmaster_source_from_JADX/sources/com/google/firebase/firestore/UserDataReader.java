package com.google.firebase.firestore;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.mutation.ArrayTransformOperation;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.model.mutation.NumericIncrementTransformOperation;
import com.google.firebase.firestore.model.mutation.ServerTimestampOperation;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.CustomClassMapper;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.p002v1.MapValue;
import com.google.firestore.p002v1.Value;
import com.google.protobuf.NullValue;
import com.google.type.LatLng;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class UserDataReader {
    private final DatabaseId databaseId;

    public UserDataReader(DatabaseId databaseId2) {
        this.databaseId = databaseId2;
    }

    public UserData.ParsedSetData parseSetData(Object input) {
        UserData.ParseAccumulator accumulator = new UserData.ParseAccumulator(UserData.Source.Set);
        return accumulator.toSetData(convertAndParseDocumentData(input, accumulator.rootContext()));
    }

    public UserData.ParsedSetData parseMergeData(Object input, FieldMask fieldMask) {
        UserData.ParseAccumulator accumulator = new UserData.ParseAccumulator(UserData.Source.MergeSet);
        ObjectValue updateData = convertAndParseDocumentData(input, accumulator.rootContext());
        if (fieldMask == null) {
            return accumulator.toMergeData(updateData);
        }
        for (FieldPath field : fieldMask.getMask()) {
            if (!accumulator.contains(field)) {
                throw new IllegalArgumentException("Field '" + field.toString() + "' is specified in your field mask but not in your input data.");
            }
        }
        return accumulator.toMergeData(updateData, fieldMask);
    }

    public UserData.ParsedUpdateData parseUpdateData(Map<String, Object> data) {
        Preconditions.checkNotNull(data, "Provided update data must not be null.");
        UserData.ParseAccumulator accumulator = new UserData.ParseAccumulator(UserData.Source.Update);
        UserData.ParseContext context = accumulator.rootContext();
        ObjectValue updateData = new ObjectValue();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            FieldPath fieldPath = FieldPath.fromDotSeparatedPath(entry.getKey()).getInternalPath();
            Object fieldValue = entry.getValue();
            if (fieldValue instanceof FieldValue.DeleteFieldValue) {
                context.addToFieldMask(fieldPath);
            } else {
                Value parsedValue = convertAndParseFieldData(fieldValue, context.childContext(fieldPath));
                if (parsedValue != null) {
                    context.addToFieldMask(fieldPath);
                    updateData.set(fieldPath, parsedValue);
                }
            }
        }
        return accumulator.toUpdateData(updateData);
    }

    public UserData.ParsedUpdateData parseUpdateData(List<Object> fieldsAndValues) {
        FieldPath parsedField;
        Assert.hardAssert(fieldsAndValues.size() % 2 == 0, "Expected fieldAndValues to contain an even number of elements", new Object[0]);
        UserData.ParseAccumulator accumulator = new UserData.ParseAccumulator(UserData.Source.Update);
        UserData.ParseContext context = accumulator.rootContext();
        ObjectValue updateData = new ObjectValue();
        Iterator<Object> iterator = fieldsAndValues.iterator();
        while (iterator.hasNext()) {
            Object fieldPath = iterator.next();
            Object fieldValue = iterator.next();
            Assert.hardAssert((fieldPath instanceof String) || (fieldPath instanceof FieldPath), "Expected argument to be String or FieldPath.", new Object[0]);
            if (fieldPath instanceof String) {
                parsedField = FieldPath.fromDotSeparatedPath((String) fieldPath).getInternalPath();
            } else {
                parsedField = ((FieldPath) fieldPath).getInternalPath();
            }
            if (fieldValue instanceof FieldValue.DeleteFieldValue) {
                context.addToFieldMask(parsedField);
            } else {
                Value parsedValue = convertAndParseFieldData(fieldValue, context.childContext(parsedField));
                if (parsedValue != null) {
                    context.addToFieldMask(parsedField);
                    updateData.set(parsedField, parsedValue);
                }
            }
        }
        return accumulator.toUpdateData(updateData);
    }

    public Value parseQueryValue(Object input) {
        return parseQueryValue(input, false);
    }

    public Value parseQueryValue(Object input, boolean allowArrays) {
        UserData.ParseAccumulator accumulator = new UserData.ParseAccumulator(allowArrays ? UserData.Source.ArrayArgument : UserData.Source.Argument);
        Value parsed = convertAndParseFieldData(input, accumulator.rootContext());
        Assert.hardAssert(parsed != null, "Parsed data should not be null.", new Object[0]);
        Assert.hardAssert(accumulator.getFieldTransforms().isEmpty(), "Field transforms should have been disallowed.", new Object[0]);
        return parsed;
    }

    public Value convertAndParseFieldData(Object input, UserData.ParseContext context) {
        return parseData(CustomClassMapper.convertToPlainJavaTypes(input), context);
    }

    private ObjectValue convertAndParseDocumentData(Object input, UserData.ParseContext context) {
        if (!input.getClass().isArray()) {
            Value parsedValue = parseData(CustomClassMapper.convertToPlainJavaTypes(input), context);
            if (parsedValue.getValueTypeCase() == Value.ValueTypeCase.MAP_VALUE) {
                return new ObjectValue(parsedValue);
            }
            throw new IllegalArgumentException("Invalid data. Data must be a Map<String, Object> or a suitable POJO object, but it was " + "of type: " + Util.typeName(input));
        }
        throw new IllegalArgumentException("Invalid data. Data must be a Map<String, Object> or a suitable POJO object, but it was " + "an array");
    }

    private Value parseData(Object input, UserData.ParseContext context) {
        if (input instanceof Map) {
            return parseMap((Map) input, context);
        }
        if (input instanceof FieldValue) {
            parseSentinelFieldValue((FieldValue) input, context);
            return null;
        }
        if (context.getPath() != null) {
            context.addToFieldMask(context.getPath());
        }
        if (!(input instanceof List)) {
            return parseScalarValue(input, context);
        }
        if (!context.isArrayElement() || context.getDataSource() == UserData.Source.ArrayArgument) {
            return parseList((List) input, context);
        }
        throw context.createError("Nested arrays are not supported");
    }

    private <K, V> Value parseMap(Map<K, V> map, UserData.ParseContext context) {
        if (map.isEmpty()) {
            if (context.getPath() != null && !context.getPath().isEmpty()) {
                context.addToFieldMask(context.getPath());
            }
            return (Value) Value.newBuilder().setMapValue(MapValue.getDefaultInstance()).build();
        }
        MapValue.Builder mapBuilder = MapValue.newBuilder();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getKey() instanceof String) {
                String key = (String) entry.getKey();
                Value parsedValue = parseData(entry.getValue(), context.childContext(key));
                if (parsedValue != null) {
                    mapBuilder.putFields(key, parsedValue);
                }
            } else {
                throw context.createError(String.format("Non-String Map key (%s) is not allowed", new Object[]{entry.getValue()}));
            }
        }
        return (Value) Value.newBuilder().setMapValue(mapBuilder).build();
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [com.google.protobuf.GeneratedMessageLite] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <T> com.google.firestore.p002v1.Value parseList(java.util.List<T> r8, com.google.firebase.firestore.core.UserData.ParseContext r9) {
        /*
            r7 = this;
            com.google.firestore.v1.ArrayValue$Builder r0 = com.google.firestore.p002v1.ArrayValue.newBuilder()
            r1 = 0
            java.util.Iterator r2 = r8.iterator()
        L_0x0009:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0035
            java.lang.Object r3 = r2.next()
            com.google.firebase.firestore.core.UserData$ParseContext r4 = r9.childContext((int) r1)
            com.google.firestore.v1.Value r4 = r7.parseData(r3, r4)
            if (r4 != 0) goto L_0x002e
            com.google.firestore.v1.Value$Builder r5 = com.google.firestore.p002v1.Value.newBuilder()
            com.google.protobuf.NullValue r6 = com.google.protobuf.NullValue.NULL_VALUE
            com.google.firestore.v1.Value$Builder r5 = r5.setNullValue(r6)
            com.google.protobuf.GeneratedMessageLite r5 = r5.build()
            r4 = r5
            com.google.firestore.v1.Value r4 = (com.google.firestore.p002v1.Value) r4
        L_0x002e:
            r0.addValues((com.google.firestore.p002v1.Value) r4)
            int r1 = r1 + 1
            goto L_0x0009
        L_0x0035:
            com.google.firestore.v1.Value$Builder r2 = com.google.firestore.p002v1.Value.newBuilder()
            com.google.firestore.v1.Value$Builder r2 = r2.setArrayValue((com.google.firestore.p002v1.ArrayValue.Builder) r0)
            com.google.protobuf.GeneratedMessageLite r2 = r2.build()
            com.google.firestore.v1.Value r2 = (com.google.firestore.p002v1.Value) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.UserDataReader.parseList(java.util.List, com.google.firebase.firestore.core.UserData$ParseContext):com.google.firestore.v1.Value");
    }

    private void parseSentinelFieldValue(FieldValue value, UserData.ParseContext context) {
        boolean z = true;
        if (!context.isWrite()) {
            throw context.createError(String.format("%s() can only be used with set() and update()", new Object[]{value.getMethodName()}));
        } else if (context.getPath() == null) {
            throw context.createError(String.format("%s() is not currently supported inside arrays", new Object[]{value.getMethodName()}));
        } else if (value instanceof FieldValue.DeleteFieldValue) {
            if (context.getDataSource() == UserData.Source.MergeSet) {
                context.addToFieldMask(context.getPath());
            } else if (context.getDataSource() == UserData.Source.Update) {
                if (context.getPath().length() <= 0) {
                    z = false;
                }
                Assert.hardAssert(z, "FieldValue.delete() at the top level should have already been handled.", new Object[0]);
                throw context.createError("FieldValue.delete() can only appear at the top level of your update data");
            } else {
                throw context.createError("FieldValue.delete() can only be used with update() and set() with SetOptions.merge()");
            }
        } else if (value instanceof FieldValue.ServerTimestampFieldValue) {
            context.addToFieldTransforms(context.getPath(), ServerTimestampOperation.getInstance());
        } else if (value instanceof FieldValue.ArrayUnionFieldValue) {
            context.addToFieldTransforms(context.getPath(), new ArrayTransformOperation.Union(parseArrayTransformElements(((FieldValue.ArrayUnionFieldValue) value).getElements())));
        } else if (value instanceof FieldValue.ArrayRemoveFieldValue) {
            context.addToFieldTransforms(context.getPath(), new ArrayTransformOperation.Remove(parseArrayTransformElements(((FieldValue.ArrayRemoveFieldValue) value).getElements())));
        } else if (value instanceof FieldValue.NumericIncrementFieldValue) {
            context.addToFieldTransforms(context.getPath(), new NumericIncrementTransformOperation(parseQueryValue(((FieldValue.NumericIncrementFieldValue) value).getOperand())));
        } else {
            throw Assert.fail("Unknown FieldValue type: %s", Util.typeName(value));
        }
    }

    private Value parseScalarValue(Object input, UserData.ParseContext context) {
        if (input == null) {
            return (Value) Value.newBuilder().setNullValue(NullValue.NULL_VALUE).build();
        }
        if (input instanceof Integer) {
            return (Value) Value.newBuilder().setIntegerValue((long) ((Integer) input).intValue()).build();
        }
        if (input instanceof Long) {
            return (Value) Value.newBuilder().setIntegerValue(((Long) input).longValue()).build();
        }
        if (input instanceof Float) {
            return (Value) Value.newBuilder().setDoubleValue(((Float) input).doubleValue()).build();
        }
        if (input instanceof Double) {
            return (Value) Value.newBuilder().setDoubleValue(((Double) input).doubleValue()).build();
        }
        if (input instanceof Boolean) {
            return (Value) Value.newBuilder().setBooleanValue(((Boolean) input).booleanValue()).build();
        }
        if (input instanceof String) {
            return (Value) Value.newBuilder().setStringValue((String) input).build();
        }
        if (input instanceof Date) {
            return parseTimestamp(new Timestamp((Date) input));
        }
        if (input instanceof Timestamp) {
            return parseTimestamp((Timestamp) input);
        }
        if (input instanceof GeoPoint) {
            GeoPoint geoPoint = (GeoPoint) input;
            return (Value) Value.newBuilder().setGeoPointValue(LatLng.newBuilder().setLatitude(geoPoint.getLatitude()).setLongitude(geoPoint.getLongitude())).build();
        } else if (input instanceof Blob) {
            return (Value) Value.newBuilder().setBytesValue(((Blob) input).toByteString()).build();
        } else {
            if (input instanceof DocumentReference) {
                DocumentReference ref = (DocumentReference) input;
                if (ref.getFirestore() != null) {
                    DatabaseId otherDb = ref.getFirestore().getDatabaseId();
                    if (!otherDb.equals(this.databaseId)) {
                        throw context.createError(String.format("Document reference is for database %s/%s but should be for database %s/%s", new Object[]{otherDb.getProjectId(), otherDb.getDatabaseId(), this.databaseId.getProjectId(), this.databaseId.getDatabaseId()}));
                    }
                }
                return (Value) Value.newBuilder().setReferenceValue(String.format("projects/%s/databases/%s/documents/%s", new Object[]{this.databaseId.getProjectId(), this.databaseId.getDatabaseId(), ((DocumentReference) input).getPath()})).build();
            } else if (input.getClass().isArray()) {
                throw context.createError("Arrays are not supported; use a List instead");
            } else {
                throw context.createError("Unsupported type: " + Util.typeName(input));
            }
        }
    }

    private Value parseTimestamp(Timestamp timestamp) {
        return (Value) Value.newBuilder().setTimestampValue(com.google.protobuf.Timestamp.newBuilder().setSeconds(timestamp.getSeconds()).setNanos((timestamp.getNanoseconds() / 1000) * 1000)).build();
    }

    private List<Value> parseArrayTransformElements(List<Object> elements) {
        UserData.ParseAccumulator accumulator = new UserData.ParseAccumulator(UserData.Source.Argument);
        List<Value> result = new ArrayList<>(elements.size());
        for (int i = 0; i < elements.size(); i++) {
            result.add(convertAndParseFieldData(elements.get(i), accumulator.rootContext().childContext(i)));
        }
        return result;
    }
}
