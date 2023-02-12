package p004io.grpc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* renamed from: io.grpc.Codec */
public interface Codec extends Compressor, Decompressor {

    /* renamed from: io.grpc.Codec$Gzip */
    public static final class Gzip implements Codec {
        public String getMessageEncoding() {
            return "gzip";
        }

        public OutputStream compress(OutputStream os) throws IOException {
            return new GZIPOutputStream(os);
        }

        public InputStream decompress(InputStream is) throws IOException {
            return new GZIPInputStream(is);
        }
    }

    /* renamed from: io.grpc.Codec$Identity */
    public static final class Identity implements Codec {
        public static final Codec NONE = new Identity();

        public InputStream decompress(InputStream is) {
            return is;
        }

        public String getMessageEncoding() {
            return "identity";
        }

        public OutputStream compress(OutputStream os) {
            return os;
        }

        private Identity() {
        }
    }
}
