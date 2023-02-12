package p004io.grpc.internal;

/* renamed from: io.grpc.internal.WritableBuffer */
public interface WritableBuffer {
    int readableBytes();

    void release();

    int writableBytes();

    void write(byte b);

    void write(byte[] bArr, int i, int i2);
}
