package p004io.grpc.protobuf.lite;

import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.Nullable;
import p004io.grpc.Drainable;
import p004io.grpc.KnownLength;

/* renamed from: io.grpc.protobuf.lite.ProtoInputStream */
final class ProtoInputStream extends InputStream implements Drainable, KnownLength {
    @Nullable
    private MessageLite message;
    private final Parser<?> parser;
    @Nullable
    private ByteArrayInputStream partial;

    ProtoInputStream(MessageLite message2, Parser<?> parser2) {
        this.message = message2;
        this.parser = parser2;
    }

    public int drainTo(OutputStream target) throws IOException {
        MessageLite messageLite = this.message;
        if (messageLite != null) {
            int written = messageLite.getSerializedSize();
            this.message.writeTo(target);
            this.message = null;
            return written;
        }
        ByteArrayInputStream byteArrayInputStream = this.partial;
        if (byteArrayInputStream == null) {
            return 0;
        }
        int written2 = (int) ProtoLiteUtils.copy(byteArrayInputStream, target);
        this.partial = null;
        return written2;
    }

    public int read() {
        if (this.message != null) {
            this.partial = new ByteArrayInputStream(this.message.toByteArray());
            this.message = null;
        }
        ByteArrayInputStream byteArrayInputStream = this.partial;
        if (byteArrayInputStream != null) {
            return byteArrayInputStream.read();
        }
        return -1;
    }

    public int read(byte[] b, int off, int len) throws IOException {
        MessageLite messageLite = this.message;
        if (messageLite != null) {
            int size = messageLite.getSerializedSize();
            if (size == 0) {
                this.message = null;
                this.partial = null;
                return -1;
            } else if (len >= size) {
                CodedOutputStream stream = CodedOutputStream.newInstance(b, off, size);
                this.message.writeTo(stream);
                stream.flush();
                stream.checkNoSpaceLeft();
                this.message = null;
                this.partial = null;
                return size;
            } else {
                this.partial = new ByteArrayInputStream(this.message.toByteArray());
                this.message = null;
            }
        }
        ByteArrayInputStream byteArrayInputStream = this.partial;
        if (byteArrayInputStream != null) {
            return byteArrayInputStream.read(b, off, len);
        }
        return -1;
    }

    public int available() {
        MessageLite messageLite = this.message;
        if (messageLite != null) {
            return messageLite.getSerializedSize();
        }
        ByteArrayInputStream byteArrayInputStream = this.partial;
        if (byteArrayInputStream != null) {
            return byteArrayInputStream.available();
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public MessageLite message() {
        MessageLite messageLite = this.message;
        if (messageLite != null) {
            return messageLite;
        }
        throw new IllegalStateException("message not available");
    }

    /* access modifiers changed from: package-private */
    public Parser<?> parser() {
        return this.parser;
    }
}
