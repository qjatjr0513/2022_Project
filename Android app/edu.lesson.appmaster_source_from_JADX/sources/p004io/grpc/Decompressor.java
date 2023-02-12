package p004io.grpc;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: io.grpc.Decompressor */
public interface Decompressor {
    InputStream decompress(InputStream inputStream) throws IOException;

    String getMessageEncoding();
}
