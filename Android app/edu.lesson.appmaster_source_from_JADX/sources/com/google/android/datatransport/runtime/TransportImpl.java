package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportScheduleCallback;

final class TransportImpl<T> implements Transport<T> {
    private final String name;
    private final Encoding payloadEncoding;
    private final Transformer<T, byte[]> transformer;
    private final TransportContext transportContext;
    private final TransportInternal transportInternal;

    TransportImpl(TransportContext transportContext2, String name2, Encoding payloadEncoding2, Transformer<T, byte[]> transformer2, TransportInternal transportInternal2) {
        this.transportContext = transportContext2;
        this.name = name2;
        this.payloadEncoding = payloadEncoding2;
        this.transformer = transformer2;
        this.transportInternal = transportInternal2;
    }

    static /* synthetic */ void lambda$send$0(Exception e) {
    }

    public void send(Event<T> event) {
        schedule(event, TransportImpl$$ExternalSyntheticLambda0.INSTANCE);
    }

    public void schedule(Event<T> event, TransportScheduleCallback callback) {
        this.transportInternal.send(SendRequest.builder().setTransportContext(this.transportContext).setEvent(event).setTransportName(this.name).setTransformer(this.transformer).setEncoding(this.payloadEncoding).build(), callback);
    }
}
