package p004io.grpc;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: io.grpc.Drainable */
public interface Drainable {
    int drainTo(OutputStream outputStream) throws IOException;
}
