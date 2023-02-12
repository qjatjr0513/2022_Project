package com.google.firebase.firestore.model;

import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.p002v1.ArrayValue;
import com.google.firestore.p002v1.ArrayValueOrBuilder;
import com.google.firestore.p002v1.MapValue;
import com.google.firestore.p002v1.Value;
import com.google.protobuf.ByteString;
import com.google.protobuf.NullValue;
import com.google.protobuf.Timestamp;
import com.google.type.LatLng;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Values {
    public static final Value MAX_VALUE;
    private static final Value MAX_VALUE_TYPE;
    public static final Value MIN_VALUE;
    public static final Value NAN_VALUE = ((Value) Value.newBuilder().setDoubleValue(Double.NaN).build());
    public static final Value NULL_VALUE;
    public static final int TYPE_ORDER_ARRAY = 9;
    public static final int TYPE_ORDER_BLOB = 6;
    public static final int TYPE_ORDER_BOOLEAN = 1;
    public static final int TYPE_ORDER_GEOPOINT = 8;
    public static final int TYPE_ORDER_MAP = 10;
    public static final int TYPE_ORDER_MAX_VALUE = Integer.MAX_VALUE;
    public static final int TYPE_ORDER_NULL = 0;
    public static final int TYPE_ORDER_NUMBER = 2;
    public static final int TYPE_ORDER_REFERENCE = 7;
    public static final int TYPE_ORDER_SERVER_TIMESTAMP = 4;
    public static final int TYPE_ORDER_STRING = 5;
    public static final int TYPE_ORDER_TIMESTAMP = 3;

    static {
        Value value = (Value) Value.newBuilder().setNullValue(NullValue.NULL_VALUE).build();
        NULL_VALUE = value;
        MIN_VALUE = value;
        Value value2 = (Value) Value.newBuilder().setStringValue("__max__").build();
        MAX_VALUE_TYPE = value2;
        MAX_VALUE = (Value) Value.newBuilder().setMapValue(MapValue.newBuilder().putFields("__type__", value2)).build();
    }

    /* renamed from: com.google.firebase.firestore.model.Values$1 */
    static /* synthetic */ class C07761 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase;

        static {
            int[] iArr = new int[Value.ValueTypeCase.values().length];
            $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase = iArr;
            try {
                iArr[Value.ValueTypeCase.NULL_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.BOOLEAN_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.INTEGER_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.DOUBLE_VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.TIMESTAMP_VALUE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.STRING_VALUE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.BYTES_VALUE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.REFERENCE_VALUE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.GEO_POINT_VALUE.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.ARRAY_VALUE.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.MAP_VALUE.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    public static int typeOrder(Value value) {
        switch (C07761.$SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[value.getValueTypeCase().ordinal()]) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 2;
            case 5:
                return 3;
            case 6:
                return 5;
            case 7:
                return 6;
            case 8:
                return 7;
            case 9:
                return 8;
            case 10:
                return 9;
            case 11:
                if (ServerTimestamps.isServerTimestamp(value)) {
                    return 4;
                }
                if (isMaxValue(value)) {
                    return Integer.MAX_VALUE;
                }
                return 10;
            default:
                throw Assert.fail("Invalid value type: " + value.getValueTypeCase(), new Object[0]);
        }
    }

    public static boolean equals(Value left, Value right) {
        int leftType;
        if (left == right) {
            return true;
        }
        if (left == null || right == null || (leftType = typeOrder(left)) != typeOrder(right)) {
            return false;
        }
        switch (leftType) {
            case 2:
                return numberEquals(left, right);
            case 4:
                return ServerTimestamps.getLocalWriteTime(left).equals(ServerTimestamps.getLocalWriteTime(right));
            case 9:
                return arrayEquals(left, right);
            case 10:
                return objectEquals(left, right);
            case Integer.MAX_VALUE:
                return true;
            default:
                return left.equals(right);
        }
    }

    private static boolean numberEquals(Value left, Value right) {
        if (left.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE && right.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE) {
            if (left.getIntegerValue() == right.getIntegerValue()) {
                return true;
            }
            return false;
        } else if (left.getValueTypeCase() != Value.ValueTypeCase.DOUBLE_VALUE || right.getValueTypeCase() != Value.ValueTypeCase.DOUBLE_VALUE) {
            return false;
        } else {
            if (Double.doubleToLongBits(left.getDoubleValue()) == Double.doubleToLongBits(right.getDoubleValue())) {
                return true;
            }
            return false;
        }
    }

    private static boolean arrayEquals(Value left, Value right) {
        ArrayValue leftArray = left.getArrayValue();
        ArrayValue rightArray = right.getArrayValue();
        if (leftArray.getValuesCount() != rightArray.getValuesCount()) {
            return false;
        }
        for (int i = 0; i < leftArray.getValuesCount(); i++) {
            if (!equals(leftArray.getValues(i), rightArray.getValues(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean objectEquals(Value left, Value right) {
        MapValue leftMap = left.getMapValue();
        MapValue rightMap = right.getMapValue();
        if (leftMap.getFieldsCount() != rightMap.getFieldsCount()) {
            return false;
        }
        for (Map.Entry<String, Value> entry : leftMap.getFieldsMap().entrySet()) {
            if (!equals(entry.getValue(), rightMap.getFieldsMap().get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public static boolean contains(ArrayValueOrBuilder haystack, Value needle) {
        for (Value haystackElement : haystack.getValuesList()) {
            if (equals(haystackElement, needle)) {
                return true;
            }
        }
        return false;
    }

    public static int compare(Value left, Value right) {
        int leftType = typeOrder(left);
        int rightType = typeOrder(right);
        if (leftType != rightType) {
            return Util.compareIntegers(leftType, rightType);
        }
        switch (leftType) {
            case 0:
            case Integer.MAX_VALUE:
                return 0;
            case 1:
                return Util.compareBooleans(left.getBooleanValue(), right.getBooleanValue());
            case 2:
                return compareNumbers(left, right);
            case 3:
                return compareTimestamps(left.getTimestampValue(), right.getTimestampValue());
            case 4:
                return compareTimestamps(ServerTimestamps.getLocalWriteTime(left), ServerTimestamps.getLocalWriteTime(right));
            case 5:
                return left.getStringValue().compareTo(right.getStringValue());
            case 6:
                return Util.compareByteStrings(left.getBytesValue(), right.getBytesValue());
            case 7:
                return compareReferences(left.getReferenceValue(), right.getReferenceValue());
            case 8:
                return compareGeoPoints(left.getGeoPointValue(), right.getGeoPointValue());
            case 9:
                return compareArrays(left.getArrayValue(), right.getArrayValue());
            case 10:
                return compareMaps(left.getMapValue(), right.getMapValue());
            default:
                throw Assert.fail("Invalid value type: " + leftType, new Object[0]);
        }
    }

    public static Value max(Value left, Value right) {
        if (left == null && right == null) {
            return null;
        }
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return compare(left, right) > 0 ? left : right;
    }

    public static Value min(Value left, Value right) {
        if (left == null && right == null) {
            return null;
        }
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return compare(left, right) < 0 ? left : right;
    }

    private static int compareNumbers(Value left, Value right) {
        if (left.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE) {
            double leftDouble = left.getDoubleValue();
            if (right.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE) {
                return Util.compareDoubles(leftDouble, right.getDoubleValue());
            }
            if (right.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE) {
                return Util.compareMixed(leftDouble, right.getIntegerValue());
            }
        } else if (left.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE) {
            long leftLong = left.getIntegerValue();
            if (right.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE) {
                return Util.compareLongs(leftLong, right.getIntegerValue());
            }
            if (right.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE) {
                return Util.compareMixed(right.getDoubleValue(), leftLong) * -1;
            }
        }
        throw Assert.fail("Unexpected values: %s vs %s", left, right);
    }

    private static int compareTimestamps(Timestamp left, Timestamp right) {
        int cmp = Util.compareLongs(left.getSeconds(), right.getSeconds());
        if (cmp != 0) {
            return cmp;
        }
        return Util.compareIntegers(left.getNanos(), right.getNanos());
    }

    private static int compareReferences(String leftPath, String rightPath) {
        String[] leftSegments = leftPath.split("/", -1);
        String[] rightSegments = rightPath.split("/", -1);
        int minLength = Math.min(leftSegments.length, rightSegments.length);
        for (int i = 0; i < minLength; i++) {
            int cmp = leftSegments[i].compareTo(rightSegments[i]);
            if (cmp != 0) {
                return cmp;
            }
        }
        return Util.compareIntegers(leftSegments.length, rightSegments.length);
    }

    private static int compareGeoPoints(LatLng left, LatLng right) {
        int comparison = Util.compareDoubles(left.getLatitude(), right.getLatitude());
        if (comparison == 0) {
            return Util.compareDoubles(left.getLongitude(), right.getLongitude());
        }
        return comparison;
    }

    private static int compareArrays(ArrayValue left, ArrayValue right) {
        int minLength = Math.min(left.getValuesCount(), right.getValuesCount());
        for (int i = 0; i < minLength; i++) {
            int cmp = compare(left.getValues(i), right.getValues(i));
            if (cmp != 0) {
                return cmp;
            }
        }
        return Util.compareIntegers(left.getValuesCount(), right.getValuesCount());
    }

    private static int compareMaps(MapValue left, MapValue right) {
        Iterator<Map.Entry<String, Value>> iterator1 = new TreeMap(left.getFieldsMap()).entrySet().iterator();
        Iterator<Map.Entry<String, Value>> iterator2 = new TreeMap(right.getFieldsMap()).entrySet().iterator();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            Map.Entry<String, Value> entry1 = iterator1.next();
            Map.Entry<String, Value> entry2 = iterator2.next();
            int keyCompare = entry1.getKey().compareTo(entry2.getKey());
            if (keyCompare != 0) {
                return keyCompare;
            }
            int valueCompare = compare(entry1.getValue(), entry2.getValue());
            if (valueCompare != 0) {
                return valueCompare;
            }
        }
        return Util.compareBooleans(iterator1.hasNext(), iterator2.hasNext());
    }

    public static String canonicalId(Value value) {
        StringBuilder builder = new StringBuilder();
        canonifyValue(builder, value);
        return builder.toString();
    }

    private static void canonifyValue(StringBuilder builder, Value value) {
        switch (C07761.$SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[value.getValueTypeCase().ordinal()]) {
            case 1:
                builder.append("null");
                return;
            case 2:
                builder.append(value.getBooleanValue());
                return;
            case 3:
                builder.append(value.getIntegerValue());
                return;
            case 4:
                builder.append(value.getDoubleValue());
                return;
            case 5:
                canonifyTimestamp(builder, value.getTimestampValue());
                return;
            case 6:
                builder.append(value.getStringValue());
                return;
            case 7:
                builder.append(Util.toDebugString(value.getBytesValue()));
                return;
            case 8:
                canonifyReference(builder, value);
                return;
            case 9:
                canonifyGeoPoint(builder, value.getGeoPointValue());
                return;
            case 10:
                canonifyArray(builder, value.getArrayValue());
                return;
            case 11:
                canonifyObject(builder, value.getMapValue());
                return;
            default:
                throw Assert.fail("Invalid value type: " + value.getValueTypeCase(), new Object[0]);
        }
    }

    private static void canonifyTimestamp(StringBuilder builder, Timestamp timestamp) {
        builder.append(String.format("time(%s,%s)", new Object[]{Long.valueOf(timestamp.getSeconds()), Integer.valueOf(timestamp.getNanos())}));
    }

    private static void canonifyGeoPoint(StringBuilder builder, LatLng latLng) {
        builder.append(String.format("geo(%s,%s)", new Object[]{Double.valueOf(latLng.getLatitude()), Double.valueOf(latLng.getLongitude())}));
    }

    private static void canonifyReference(StringBuilder builder, Value value) {
        Assert.hardAssert(isReferenceValue(value), "Value should be a ReferenceValue", new Object[0]);
        builder.append(DocumentKey.fromName(value.getReferenceValue()));
    }

    private static void canonifyObject(StringBuilder builder, MapValue mapValue) {
        List<String> keys = new ArrayList<>(mapValue.getFieldsMap().keySet());
        Collections.sort(keys);
        builder.append("{");
        boolean first = true;
        for (String key : keys) {
            if (!first) {
                builder.append(",");
            } else {
                first = false;
            }
            builder.append(key).append(":");
            canonifyValue(builder, mapValue.getFieldsOrThrow(key));
        }
        builder.append("}");
    }

    private static void canonifyArray(StringBuilder builder, ArrayValue arrayValue) {
        builder.append("[");
        for (int i = 0; i < arrayValue.getValuesCount(); i++) {
            canonifyValue(builder, arrayValue.getValues(i));
            if (i != arrayValue.getValuesCount() - 1) {
                builder.append(",");
            }
        }
        builder.append("]");
    }

    public static boolean isInteger(Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE;
    }

    public static boolean isDouble(Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE;
    }

    public static boolean isNumber(Value value) {
        return isInteger(value) || isDouble(value);
    }

    public static boolean isArray(Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.ARRAY_VALUE;
    }

    public static boolean isReferenceValue(Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.REFERENCE_VALUE;
    }

    public static boolean isNullValue(Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.NULL_VALUE;
    }

    public static boolean isNanValue(Value value) {
        return value != null && Double.isNaN(value.getDoubleValue());
    }

    public static boolean isMapValue(Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.MAP_VALUE;
    }

    public static Value refValue(DatabaseId databaseId, DocumentKey key) {
        return (Value) Value.newBuilder().setReferenceValue(String.format("projects/%s/databases/%s/documents/%s", new Object[]{databaseId.getProjectId(), databaseId.getDatabaseId(), key.toString()})).build();
    }

    public static Value getLowerBound(Value.ValueTypeCase valueTypeCase) {
        switch (C07761.$SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[valueTypeCase.ordinal()]) {
            case 1:
                return NULL_VALUE;
            case 2:
                return (Value) Value.newBuilder().setBooleanValue(false).build();
            case 3:
            case 4:
                return (Value) Value.newBuilder().setDoubleValue(Double.NaN).build();
            case 5:
                return (Value) Value.newBuilder().setTimestampValue(Timestamp.newBuilder().setSeconds(Long.MIN_VALUE)).build();
            case 6:
                return (Value) Value.newBuilder().setStringValue("").build();
            case 7:
                return (Value) Value.newBuilder().setBytesValue(ByteString.EMPTY).build();
            case 8:
                return refValue(DatabaseId.EMPTY, DocumentKey.empty());
            case 9:
                return (Value) Value.newBuilder().setGeoPointValue(LatLng.newBuilder().setLatitude(-90.0d).setLongitude(-180.0d)).build();
            case 10:
                return (Value) Value.newBuilder().setArrayValue(ArrayValue.getDefaultInstance()).build();
            case 11:
                return (Value) Value.newBuilder().setMapValue(MapValue.getDefaultInstance()).build();
            default:
                throw new IllegalArgumentException("Unknown value type: " + valueTypeCase);
        }
    }

    public static Value getUpperBound(Value.ValueTypeCase valueTypeCase) {
        switch (C07761.$SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[valueTypeCase.ordinal()]) {
            case 1:
                return getLowerBound(Value.ValueTypeCase.BOOLEAN_VALUE);
            case 2:
                return getLowerBound(Value.ValueTypeCase.INTEGER_VALUE);
            case 3:
            case 4:
                return getLowerBound(Value.ValueTypeCase.TIMESTAMP_VALUE);
            case 5:
                return getLowerBound(Value.ValueTypeCase.STRING_VALUE);
            case 6:
                return getLowerBound(Value.ValueTypeCase.BYTES_VALUE);
            case 7:
                return getLowerBound(Value.ValueTypeCase.REFERENCE_VALUE);
            case 8:
                return getLowerBound(Value.ValueTypeCase.GEO_POINT_VALUE);
            case 9:
                return getLowerBound(Value.ValueTypeCase.ARRAY_VALUE);
            case 10:
                return getLowerBound(Value.ValueTypeCase.MAP_VALUE);
            case 11:
                return MAX_VALUE;
            default:
                throw new IllegalArgumentException("Unknown value type: " + valueTypeCase);
        }
    }

    public static boolean isMaxValue(Value value) {
        return MAX_VALUE_TYPE.equals(value.getMapValue().getFieldsMap().get("__type__"));
    }
}
