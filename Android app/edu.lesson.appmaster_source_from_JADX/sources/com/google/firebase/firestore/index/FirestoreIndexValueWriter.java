package com.google.firebase.firestore.index;

import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.p002v1.ArrayValue;
import com.google.firestore.p002v1.MapValue;
import com.google.firestore.p002v1.Value;
import com.google.protobuf.Timestamp;
import com.google.type.LatLng;
import java.util.Map;

public class FirestoreIndexValueWriter {
    public static final int DOCUMENT_NAME_OFFSET = 5;
    public static final int INDEX_TYPE_ARRAY = 50;
    public static final int INDEX_TYPE_BLOB = 30;
    public static final int INDEX_TYPE_BOOLEAN = 10;
    public static final int INDEX_TYPE_GEOPOINT = 45;
    public static final int INDEX_TYPE_MAP = 55;
    public static final int INDEX_TYPE_NAN = 13;
    public static final int INDEX_TYPE_NULL = 5;
    public static final int INDEX_TYPE_NUMBER = 15;
    public static final int INDEX_TYPE_REFERENCE = 37;
    public static final int INDEX_TYPE_REFERENCE_SEGMENT = 60;
    public static final int INDEX_TYPE_STRING = 25;
    public static final int INDEX_TYPE_TIMESTAMP = 20;
    public static final FirestoreIndexValueWriter INSTANCE = new FirestoreIndexValueWriter();
    public static final int NOT_TRUNCATED = 2;

    private FirestoreIndexValueWriter() {
    }

    public void writeIndexValue(Value value, DirectionalIndexByteEncoder encoder) {
        writeIndexValueAux(value, encoder);
        encoder.writeInfinity();
    }

    /* renamed from: com.google.firebase.firestore.index.FirestoreIndexValueWriter$1 */
    static /* synthetic */ class C07661 {
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
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.DOUBLE_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.INTEGER_VALUE.ordinal()] = 4;
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
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.MAP_VALUE.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.ARRAY_VALUE.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    private void writeIndexValueAux(Value indexValue, DirectionalIndexByteEncoder encoder) {
        switch (C07661.$SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[indexValue.getValueTypeCase().ordinal()]) {
            case 1:
                writeValueTypeLabel(encoder, 5);
                return;
            case 2:
                writeValueTypeLabel(encoder, 10);
                encoder.writeLong(indexValue.getBooleanValue() ? 1 : 0);
                return;
            case 3:
                double number = indexValue.getDoubleValue();
                if (Double.isNaN(number)) {
                    writeValueTypeLabel(encoder, 13);
                    return;
                }
                writeValueTypeLabel(encoder, 15);
                if (number == -0.0d) {
                    encoder.writeDouble(0.0d);
                    return;
                } else {
                    encoder.writeDouble(number);
                    return;
                }
            case 4:
                writeValueTypeLabel(encoder, 15);
                encoder.writeDouble((double) indexValue.getIntegerValue());
                return;
            case 5:
                Timestamp timestamp = indexValue.getTimestampValue();
                writeValueTypeLabel(encoder, 20);
                encoder.writeLong(timestamp.getSeconds());
                encoder.writeLong((long) timestamp.getNanos());
                return;
            case 6:
                writeIndexString(indexValue.getStringValue(), encoder);
                writeTruncationMarker(encoder);
                return;
            case 7:
                writeValueTypeLabel(encoder, 30);
                encoder.writeBytes(indexValue.getBytesValue());
                writeTruncationMarker(encoder);
                return;
            case 8:
                writeIndexEntityRef(indexValue.getReferenceValue(), encoder);
                return;
            case 9:
                LatLng geoPoint = indexValue.getGeoPointValue();
                writeValueTypeLabel(encoder, 45);
                encoder.writeDouble(geoPoint.getLatitude());
                encoder.writeDouble(geoPoint.getLongitude());
                return;
            case 10:
                if (Values.isMaxValue(indexValue)) {
                    writeValueTypeLabel(encoder, Integer.MAX_VALUE);
                    return;
                }
                writeIndexMap(indexValue.getMapValue(), encoder);
                writeTruncationMarker(encoder);
                return;
            case 11:
                writeIndexArray(indexValue.getArrayValue(), encoder);
                writeTruncationMarker(encoder);
                return;
            default:
                throw new IllegalArgumentException("unknown index value type " + indexValue.getValueTypeCase());
        }
    }

    private void writeIndexString(String stringIndexValue, DirectionalIndexByteEncoder encoder) {
        writeValueTypeLabel(encoder, 25);
        writeUnlabeledIndexString(stringIndexValue, encoder);
    }

    private void writeUnlabeledIndexString(String stringIndexValue, DirectionalIndexByteEncoder encoder) {
        encoder.writeString(stringIndexValue);
    }

    private void writeIndexMap(MapValue mapIndexValue, DirectionalIndexByteEncoder encoder) {
        writeValueTypeLabel(encoder, 55);
        for (Map.Entry<String, Value> entry : mapIndexValue.getFieldsMap().entrySet()) {
            writeIndexString(entry.getKey(), encoder);
            writeIndexValueAux(entry.getValue(), encoder);
        }
    }

    private void writeIndexArray(ArrayValue arrayIndexValue, DirectionalIndexByteEncoder encoder) {
        writeValueTypeLabel(encoder, 50);
        for (Value element : arrayIndexValue.getValuesList()) {
            writeIndexValueAux(element, encoder);
        }
    }

    private void writeIndexEntityRef(String referenceValue, DirectionalIndexByteEncoder encoder) {
        writeValueTypeLabel(encoder, 37);
        ResourcePath path = ResourcePath.fromString(referenceValue);
        int numSegments = path.length();
        for (int index = 5; index < numSegments; index++) {
            String segment = path.getSegment(index);
            writeValueTypeLabel(encoder, 60);
            writeUnlabeledIndexString(segment, encoder);
        }
    }

    private void writeValueTypeLabel(DirectionalIndexByteEncoder encoder, int typeOrder) {
        encoder.writeLong((long) typeOrder);
    }

    private void writeTruncationMarker(DirectionalIndexByteEncoder encoder) {
        encoder.writeLong(2);
    }
}
