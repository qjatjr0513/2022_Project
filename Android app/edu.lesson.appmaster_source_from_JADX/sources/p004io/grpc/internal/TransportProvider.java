package p004io.grpc.internal;

import javax.annotation.Nullable;

/* renamed from: io.grpc.internal.TransportProvider */
interface TransportProvider {
    @Nullable
    ClientTransport obtainActiveTransport();
}
