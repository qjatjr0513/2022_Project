package okio;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class HashingSource extends ForwardingSource {
    private final Mac mac;
    private final MessageDigest messageDigest;

    public static HashingSource md5(Source source) {
        return new HashingSource(source, "MD5");
    }

    public static HashingSource sha1(Source source) {
        return new HashingSource(source, "SHA-1");
    }

    public static HashingSource sha256(Source source) {
        return new HashingSource(source, "SHA-256");
    }

    public static HashingSource hmacSha1(Source source, ByteString key) {
        return new HashingSource(source, key, "HmacSHA1");
    }

    public static HashingSource hmacSha256(Source source, ByteString key) {
        return new HashingSource(source, key, "HmacSHA256");
    }

    private HashingSource(Source source, String algorithm) {
        super(source);
        try {
            this.messageDigest = MessageDigest.getInstance(algorithm);
            this.mac = null;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        }
    }

    private HashingSource(Source source, ByteString key, String algorithm) {
        super(source);
        try {
            Mac instance = Mac.getInstance(algorithm);
            this.mac = instance;
            instance.init(new SecretKeySpec(key.toByteArray(), algorithm));
            this.messageDigest = null;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        } catch (InvalidKeyException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public long read(Buffer sink, long byteCount) throws IOException {
        long result = super.read(sink, byteCount);
        if (result != -1) {
            long start = sink.size - result;
            long offset = sink.size;
            Segment s = sink.head;
            while (offset > start) {
                s = s.prev;
                offset -= (long) (s.limit - s.pos);
            }
            while (offset < sink.size) {
                int pos = (int) ((((long) s.pos) + start) - offset);
                MessageDigest messageDigest2 = this.messageDigest;
                if (messageDigest2 != null) {
                    messageDigest2.update(s.data, pos, s.limit - pos);
                } else {
                    this.mac.update(s.data, pos, s.limit - pos);
                }
                offset += (long) (s.limit - s.pos);
                start = offset;
                s = s.next;
            }
        }
        return result;
    }

    public final ByteString hash() {
        MessageDigest messageDigest2 = this.messageDigest;
        return ByteString.m355of(messageDigest2 != null ? messageDigest2.digest() : this.mac.doFinal());
    }
}
