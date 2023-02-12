package p004io.grpc;

import com.google.common.base.Ascii;
import com.google.common.base.Charsets;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import p004io.grpc.Metadata;

@CheckReturnValue
/* renamed from: io.grpc.Status */
public final class Status {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Status ABORTED = Code.ABORTED.toStatus();
    public static final Status ALREADY_EXISTS = Code.ALREADY_EXISTS.toStatus();
    public static final Status CANCELLED = Code.CANCELLED.toStatus();
    static final Metadata.Key<Status> CODE_KEY = Metadata.Key.m351of("grpc-status", false, new StatusCodeMarshaller());
    public static final Status DATA_LOSS = Code.DATA_LOSS.toStatus();
    public static final Status DEADLINE_EXCEEDED = Code.DEADLINE_EXCEEDED.toStatus();
    public static final Status FAILED_PRECONDITION = Code.FAILED_PRECONDITION.toStatus();
    private static final boolean FAIL_ON_EQUALS_FOR_TEST = Boolean.parseBoolean(System.getProperty(TEST_EQUALS_FAILURE_PROPERTY, "false"));
    public static final Status INTERNAL = Code.INTERNAL.toStatus();
    public static final Status INVALID_ARGUMENT = Code.INVALID_ARGUMENT.toStatus();
    static final Metadata.Key<String> MESSAGE_KEY;
    public static final Status NOT_FOUND = Code.NOT_FOUND.toStatus();

    /* renamed from: OK */
    public static final Status f313OK = Code.OK.toStatus();
    public static final Status OUT_OF_RANGE = Code.OUT_OF_RANGE.toStatus();
    public static final Status PERMISSION_DENIED = Code.PERMISSION_DENIED.toStatus();
    public static final Status RESOURCE_EXHAUSTED = Code.RESOURCE_EXHAUSTED.toStatus();
    /* access modifiers changed from: private */
    public static final List<Status> STATUS_LIST = buildStatusList();
    private static final Metadata.TrustedAsciiMarshaller<String> STATUS_MESSAGE_MARSHALLER;
    private static final String TEST_EQUALS_FAILURE_PROPERTY = "io.grpc.Status.failOnEqualsForTest";
    public static final Status UNAUTHENTICATED = Code.UNAUTHENTICATED.toStatus();
    public static final Status UNAVAILABLE = Code.UNAVAILABLE.toStatus();
    public static final Status UNIMPLEMENTED = Code.UNIMPLEMENTED.toStatus();
    public static final Status UNKNOWN = Code.UNKNOWN.toStatus();
    private final Throwable cause;
    private final Code code;
    private final String description;

    static {
        StatusMessageMarshaller statusMessageMarshaller = new StatusMessageMarshaller();
        STATUS_MESSAGE_MARSHALLER = statusMessageMarshaller;
        MESSAGE_KEY = Metadata.Key.m351of("grpc-message", false, statusMessageMarshaller);
    }

    /* renamed from: io.grpc.Status$Code */
    public enum Code {
        OK(0),
        CANCELLED(1),
        UNKNOWN(2),
        INVALID_ARGUMENT(3),
        DEADLINE_EXCEEDED(4),
        NOT_FOUND(5),
        ALREADY_EXISTS(6),
        PERMISSION_DENIED(7),
        RESOURCE_EXHAUSTED(8),
        FAILED_PRECONDITION(9),
        ABORTED(10),
        OUT_OF_RANGE(11),
        UNIMPLEMENTED(12),
        INTERNAL(13),
        UNAVAILABLE(14),
        DATA_LOSS(15),
        UNAUTHENTICATED(16);
        
        private final int value;
        private final byte[] valueAscii;

        private Code(int value2) {
            this.value = value2;
            this.valueAscii = Integer.toString(value2).getBytes(Charsets.US_ASCII);
        }

        public int value() {
            return this.value;
        }

        public Status toStatus() {
            return (Status) Status.STATUS_LIST.get(this.value);
        }

        /* access modifiers changed from: private */
        public byte[] valueAscii() {
            return this.valueAscii;
        }
    }

    private static List<Status> buildStatusList() {
        TreeMap<Integer, Status> canonicalizer = new TreeMap<>();
        Code[] values = Code.values();
        int length = values.length;
        int i = 0;
        while (i < length) {
            Code code2 = values[i];
            Status replaced = canonicalizer.put(Integer.valueOf(code2.value()), new Status(code2));
            if (replaced == null) {
                i++;
            } else {
                throw new IllegalStateException("Code value duplication between " + replaced.getCode().name() + " & " + code2.name());
            }
        }
        return Collections.unmodifiableList(new ArrayList(canonicalizer.values()));
    }

    public static Status fromCodeValue(int codeValue) {
        if (codeValue >= 0) {
            List<Status> list = STATUS_LIST;
            if (codeValue <= list.size()) {
                return list.get(codeValue);
            }
        }
        return UNKNOWN.withDescription("Unknown code " + codeValue);
    }

    /* access modifiers changed from: private */
    public static Status fromCodeValue(byte[] asciiCodeValue) {
        if (asciiCodeValue.length == 1 && asciiCodeValue[0] == 48) {
            return f313OK;
        }
        return fromCodeValueSlow(asciiCodeValue);
    }

    private static Status fromCodeValueSlow(byte[] asciiCodeValue) {
        int index = 0;
        int codeValue = 0;
        switch (asciiCodeValue.length) {
            case 2:
                if (asciiCodeValue[0] >= 48 && asciiCodeValue[0] <= 57) {
                    codeValue = 0 + ((asciiCodeValue[0] - 48) * 10);
                    index = 0 + 1;
                }
            case 1:
                if (asciiCodeValue[index] >= 48 && asciiCodeValue[index] <= 57) {
                    int codeValue2 = codeValue + (asciiCodeValue[index] - 48);
                    List<Status> list = STATUS_LIST;
                    if (codeValue2 < list.size()) {
                        return list.get(codeValue2);
                    }
                }
                break;
        }
        return UNKNOWN.withDescription("Unknown code " + new String(asciiCodeValue, Charsets.US_ASCII));
    }

    public static Status fromCode(Code code2) {
        return code2.toStatus();
    }

    public static Status fromThrowable(Throwable t) {
        for (Throwable cause2 = (Throwable) Preconditions.checkNotNull(t, "t"); cause2 != null; cause2 = cause2.getCause()) {
            if (cause2 instanceof StatusException) {
                return ((StatusException) cause2).getStatus();
            }
            if (cause2 instanceof StatusRuntimeException) {
                return ((StatusRuntimeException) cause2).getStatus();
            }
        }
        return UNKNOWN.withCause(t);
    }

    @Nullable
    public static Metadata trailersFromThrowable(Throwable t) {
        for (Throwable cause2 = (Throwable) Preconditions.checkNotNull(t, "t"); cause2 != null; cause2 = cause2.getCause()) {
            if (cause2 instanceof StatusException) {
                return ((StatusException) cause2).getTrailers();
            }
            if (cause2 instanceof StatusRuntimeException) {
                return ((StatusRuntimeException) cause2).getTrailers();
            }
        }
        return null;
    }

    static String formatThrowableMessage(Status status) {
        if (status.description == null) {
            return status.code.toString();
        }
        return status.code + ": " + status.description;
    }

    private Status(Code code2) {
        this(code2, (String) null, (Throwable) null);
    }

    private Status(Code code2, @Nullable String description2, @Nullable Throwable cause2) {
        this.code = (Code) Preconditions.checkNotNull(code2, "code");
        this.description = description2;
        this.cause = cause2;
    }

    public Status withCause(Throwable cause2) {
        if (Objects.equal(this.cause, cause2)) {
            return this;
        }
        return new Status(this.code, this.description, cause2);
    }

    public Status withDescription(String description2) {
        if (Objects.equal(this.description, description2)) {
            return this;
        }
        return new Status(this.code, description2, this.cause);
    }

    public Status augmentDescription(String additionalDetail) {
        if (additionalDetail == null) {
            return this;
        }
        if (this.description == null) {
            return new Status(this.code, additionalDetail, this.cause);
        }
        return new Status(this.code, this.description + "\n" + additionalDetail, this.cause);
    }

    public Code getCode() {
        return this.code;
    }

    @Nullable
    public String getDescription() {
        return this.description;
    }

    @Nullable
    public Throwable getCause() {
        return this.cause;
    }

    public boolean isOk() {
        return Code.OK == this.code;
    }

    public StatusRuntimeException asRuntimeException() {
        return new StatusRuntimeException(this);
    }

    public StatusRuntimeException asRuntimeException(@Nullable Metadata trailers) {
        return new StatusRuntimeException(this, trailers);
    }

    public StatusException asException() {
        return new StatusException(this);
    }

    public StatusException asException(@Nullable Metadata trailers) {
        return new StatusException(this, trailers);
    }

    public String toString() {
        MoreObjects.ToStringHelper add = MoreObjects.toStringHelper((Object) this).add("code", (Object) this.code.name()).add("description", (Object) this.description);
        Throwable th = this.cause;
        Object obj = th;
        if (th != null) {
            obj = Throwables.getStackTraceAsString(th);
        }
        return add.add("cause", obj).toString();
    }

    /* renamed from: io.grpc.Status$StatusCodeMarshaller */
    private static final class StatusCodeMarshaller implements Metadata.TrustedAsciiMarshaller<Status> {
        private StatusCodeMarshaller() {
        }

        public byte[] toAsciiString(Status status) {
            return status.getCode().valueAscii();
        }

        public Status parseAsciiString(byte[] serialized) {
            return Status.fromCodeValue(serialized);
        }
    }

    /* renamed from: io.grpc.Status$StatusMessageMarshaller */
    private static final class StatusMessageMarshaller implements Metadata.TrustedAsciiMarshaller<String> {
        private static final byte[] HEX = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};

        private StatusMessageMarshaller() {
        }

        public byte[] toAsciiString(String value) {
            byte[] valueBytes = value.getBytes(Charsets.UTF_8);
            for (int i = 0; i < valueBytes.length; i++) {
                if (isEscapingChar(valueBytes[i])) {
                    return toAsciiStringSlow(valueBytes, i);
                }
            }
            return valueBytes;
        }

        private static boolean isEscapingChar(byte b) {
            return b < 32 || b >= 126 || b == 37;
        }

        private static byte[] toAsciiStringSlow(byte[] valueBytes, int ri) {
            byte[] escapedBytes = new byte[(((valueBytes.length - ri) * 3) + ri)];
            if (ri != 0) {
                System.arraycopy(valueBytes, 0, escapedBytes, 0, ri);
            }
            int wi = ri;
            while (ri < valueBytes.length) {
                byte b = valueBytes[ri];
                if (isEscapingChar(b)) {
                    escapedBytes[wi] = 37;
                    byte[] bArr = HEX;
                    escapedBytes[wi + 1] = bArr[(b >> 4) & 15];
                    escapedBytes[wi + 2] = bArr[b & Ascii.f62SI];
                    wi += 3;
                } else {
                    escapedBytes[wi] = b;
                    wi++;
                }
                ri++;
            }
            return Arrays.copyOf(escapedBytes, wi);
        }

        public String parseAsciiString(byte[] value) {
            for (int i = 0; i < value.length; i++) {
                byte b = value[i];
                if (b < 32 || b >= 126 || (b == 37 && i + 2 < value.length)) {
                    return parseAsciiStringSlow(value);
                }
            }
            return new String(value, 0);
        }

        private static String parseAsciiStringSlow(byte[] value) {
            ByteBuffer buf = ByteBuffer.allocate(value.length);
            int i = 0;
            while (i < value.length) {
                if (value[i] == 37 && i + 2 < value.length) {
                    try {
                        buf.put((byte) Integer.parseInt(new String(value, i + 1, 2, Charsets.US_ASCII), 16));
                        i += 3;
                    } catch (NumberFormatException e) {
                    }
                }
                buf.put(value[i]);
                i++;
            }
            return new String(buf.array(), 0, buf.position(), Charsets.UTF_8);
        }
    }

    public boolean equals(Object obj) {
        if (!FAIL_ON_EQUALS_FOR_TEST) {
            return super.equals(obj);
        }
        throw new AssertionError("Status.equals called; disable this by setting io.grpc.Status.failOnEqualsForTest");
    }

    public int hashCode() {
        return super.hashCode();
    }
}
