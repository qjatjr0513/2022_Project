package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import java.util.Set;

final class TransportFactoryImpl implements TransportFactory {
    private final Set<Encoding> supportedPayloadEncodings;
    private final TransportContext transportContext;
    private final TransportInternal transportInternal;

    TransportFactoryImpl(Set<Encoding> supportedPayloadEncodings2, TransportContext transportContext2, TransportInternal transportInternal2) {
        this.supportedPayloadEncodings = supportedPayloadEncodings2;
        this.transportContext = transportContext2;
        this.transportInternal = transportInternal2;
    }

    public <T> Transport<T> getTransport(String name, Class<T> payloadType, Transformer<T, byte[]> payloadTransformer) {
        return getTransport(name, payloadType, Encoding.m395of("proto"), payloadTransformer);
    }

    public <T> Transport<T> getTransport(String name, Class<T> cls, Encoding payloadEncoding, Transformer<T, byte[]> payloadTransformer) {
        if (this.supportedPayloadEncodings.contains(payloadEncoding)) {
            return new TransportImpl(this.transportContext, name, payloadEncoding, payloadTransformer, this.transportInternal);
        }
        throw new IllegalArgumentException(String.format("%s is not supported byt this factory. Supported encodings are: %s.", new Object[]{payloadEncoding, this.supportedPayloadEncodings}));
    }
}
