package p004io.grpc;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: io.grpc.Compressor */
public interface Compressor {
    OutputStream compress(OutputStream outputStream) throws IOException;

    String getMessageEncoding();
}
