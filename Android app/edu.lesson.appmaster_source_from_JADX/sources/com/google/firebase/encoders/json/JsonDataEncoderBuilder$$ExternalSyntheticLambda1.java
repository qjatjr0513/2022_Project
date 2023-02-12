package com.google.firebase.encoders.json;

import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;

public final /* synthetic */ class JsonDataEncoderBuilder$$ExternalSyntheticLambda1 implements ValueEncoder {
    public static final /* synthetic */ JsonDataEncoderBuilder$$ExternalSyntheticLambda1 INSTANCE = new JsonDataEncoderBuilder$$ExternalSyntheticLambda1();

    private /* synthetic */ JsonDataEncoderBuilder$$ExternalSyntheticLambda1() {
    }

    public final void encode(Object obj, Object obj2) {
        ((ValueEncoderContext) obj2).add(((Boolean) obj).booleanValue());
    }
}
