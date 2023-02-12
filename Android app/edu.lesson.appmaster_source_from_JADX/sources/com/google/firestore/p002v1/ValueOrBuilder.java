package com.google.firestore.p002v1;

import com.google.firestore.p002v1.Value;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.NullValue;
import com.google.protobuf.Timestamp;
import com.google.type.LatLng;

/* renamed from: com.google.firestore.v1.ValueOrBuilder */
public interface ValueOrBuilder extends MessageLiteOrBuilder {
    ArrayValue getArrayValue();

    boolean getBooleanValue();

    ByteString getBytesValue();

    double getDoubleValue();

    LatLng getGeoPointValue();

    long getIntegerValue();

    MapValue getMapValue();

    NullValue getNullValue();

    int getNullValueValue();

    String getReferenceValue();

    ByteString getReferenceValueBytes();

    String getStringValue();

    ByteString getStringValueBytes();

    Timestamp getTimestampValue();

    Value.ValueTypeCase getValueTypeCase();

    boolean hasArrayValue();

    boolean hasGeoPointValue();

    boolean hasMapValue();

    boolean hasTimestampValue();
}
