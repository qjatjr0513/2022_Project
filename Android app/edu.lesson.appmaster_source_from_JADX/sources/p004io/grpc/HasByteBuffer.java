package p004io.grpc;

import java.nio.ByteBuffer;
import javax.annotation.Nullable;

/* renamed from: io.grpc.HasByteBuffer */
public interface HasByteBuffer {
    boolean byteBufferSupported();

    @Nullable
    ByteBuffer getByteBuffer();
}
