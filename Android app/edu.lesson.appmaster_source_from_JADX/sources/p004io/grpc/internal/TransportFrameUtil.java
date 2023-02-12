package p004io.grpc.internal;

import com.google.common.base.Charsets;
import com.google.common.p000io.BaseEncoding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.CheckReturnValue;
import p004io.grpc.InternalMetadata;
import p004io.grpc.Metadata;

/* renamed from: io.grpc.internal.TransportFrameUtil */
public final class TransportFrameUtil {
    private static final byte[] binaryHeaderSuffixBytes = Metadata.BINARY_HEADER_SUFFIX.getBytes(Charsets.US_ASCII);
    private static final Logger logger = Logger.getLogger(TransportFrameUtil.class.getName());

    public static byte[][] toHttp2Headers(Metadata headers) {
        byte[][] serializedHeaders = InternalMetadata.serialize(headers);
        if (serializedHeaders == null) {
            return new byte[0][];
        }
        int k = 0;
        for (int i = 0; i < serializedHeaders.length; i += 2) {
            byte[] key = serializedHeaders[i];
            byte[] value = serializedHeaders[i + 1];
            if (endsWith(key, binaryHeaderSuffixBytes)) {
                serializedHeaders[k] = key;
                serializedHeaders[k + 1] = InternalMetadata.BASE64_ENCODING_OMIT_PADDING.encode(value).getBytes(Charsets.US_ASCII);
                k += 2;
            } else if (isSpecCompliantAscii(value)) {
                serializedHeaders[k] = key;
                serializedHeaders[k + 1] = value;
                k += 2;
            } else {
                logger.warning("Metadata key=" + new String(key, Charsets.US_ASCII) + ", value=" + Arrays.toString(value) + " contains invalid ASCII characters");
            }
        }
        if (k == serializedHeaders.length) {
            return serializedHeaders;
        }
        return (byte[][]) Arrays.copyOfRange(serializedHeaders, 0, k);
    }

    @CheckReturnValue
    public static byte[][] toRawSerializedHeaders(byte[][] http2Headers) {
        for (int i = 0; i < http2Headers.length; i += 2) {
            byte[] key = http2Headers[i];
            byte[] value = http2Headers[i + 1];
            if (endsWith(key, binaryHeaderSuffixBytes)) {
                for (byte b : value) {
                    if (b == 44) {
                        return serializeHeadersWithCommasInBin(http2Headers, i);
                    }
                }
                http2Headers[i + 1] = BaseEncoding.base64().decode(new String(value, Charsets.US_ASCII));
            }
        }
        return http2Headers;
    }

    private static byte[][] serializeHeadersWithCommasInBin(byte[][] http2Headers, int resumeFrom) {
        List<byte[]> headerList = new ArrayList<>(http2Headers.length + 10);
        for (int i = 0; i < resumeFrom; i++) {
            headerList.add(http2Headers[i]);
        }
        for (int i2 = resumeFrom; i2 < http2Headers.length; i2 += 2) {
            byte[] key = http2Headers[i2];
            byte[] value = http2Headers[i2 + 1];
            if (!endsWith(key, binaryHeaderSuffixBytes)) {
                headerList.add(key);
                headerList.add(value);
            } else {
                int prevIdx = 0;
                for (int idx = 0; idx <= value.length; idx++) {
                    if (idx == value.length || value[idx] == 44) {
                        byte[] decodedVal = BaseEncoding.base64().decode(new String(value, prevIdx, idx - prevIdx, Charsets.US_ASCII));
                        prevIdx = idx + 1;
                        headerList.add(key);
                        headerList.add(decodedVal);
                    }
                }
            }
        }
        return (byte[][]) headerList.toArray(new byte[0][]);
    }

    private static boolean endsWith(byte[] subject, byte[] suffix) {
        int start = subject.length - suffix.length;
        if (start < 0) {
            return false;
        }
        for (int i = start; i < subject.length; i++) {
            if (subject[i] != suffix[i - start]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSpecCompliantAscii(byte[] subject) {
        for (byte b : subject) {
            if (b < 32 || b > 126) {
                return false;
            }
        }
        return true;
    }

    private TransportFrameUtil() {
    }
}
