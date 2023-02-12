package p004io.grpc.okhttp.internal.framed;

import okio.BufferedSink;
import okio.BufferedSource;
import p004io.grpc.okhttp.internal.Protocol;

/* renamed from: io.grpc.okhttp.internal.framed.Variant */
public interface Variant {
    Protocol getProtocol();

    FrameReader newReader(BufferedSource bufferedSource, boolean z);

    FrameWriter newWriter(BufferedSink bufferedSink, boolean z);
}
