package p004io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReferenceArray;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

/* renamed from: io.grpc.MethodDescriptor */
public final class MethodDescriptor<ReqT, RespT> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final String fullMethodName;
    private final boolean idempotent;
    private final AtomicReferenceArray<Object> rawMethodNames;
    private final Marshaller<ReqT> requestMarshaller;
    private final Marshaller<RespT> responseMarshaller;
    private final boolean safe;
    private final boolean sampledToLocalTracing;
    @Nullable
    private final Object schemaDescriptor;
    @Nullable
    private final String serviceName;
    private final MethodType type;

    /* renamed from: io.grpc.MethodDescriptor$Marshaller */
    public interface Marshaller<T> {
        T parse(InputStream inputStream);

        InputStream stream(T t);
    }

    /* renamed from: io.grpc.MethodDescriptor$PrototypeMarshaller */
    public interface PrototypeMarshaller<T> extends ReflectableMarshaller<T> {
        @Nullable
        T getMessagePrototype();
    }

    /* renamed from: io.grpc.MethodDescriptor$ReflectableMarshaller */
    public interface ReflectableMarshaller<T> extends Marshaller<T> {
        Class<T> getMessageClass();
    }

    /* access modifiers changed from: package-private */
    public final Object getRawMethodName(int transportOrdinal) {
        return this.rawMethodNames.get(transportOrdinal);
    }

    /* access modifiers changed from: package-private */
    public final void setRawMethodName(int transportOrdinal, Object o) {
        this.rawMethodNames.lazySet(transportOrdinal, o);
    }

    /* renamed from: io.grpc.MethodDescriptor$MethodType */
    public enum MethodType {
        UNARY,
        CLIENT_STREAMING,
        SERVER_STREAMING,
        BIDI_STREAMING,
        UNKNOWN;

        public final boolean clientSendsOneMessage() {
            return this == UNARY || this == SERVER_STREAMING;
        }

        public final boolean serverSendsOneMessage() {
            return this == UNARY || this == CLIENT_STREAMING;
        }
    }

    @Deprecated
    public static <RequestT, ResponseT> MethodDescriptor<RequestT, ResponseT> create(MethodType type2, String fullMethodName2, Marshaller<RequestT> requestMarshaller2, Marshaller<ResponseT> responseMarshaller2) {
        return new MethodDescriptor(type2, fullMethodName2, requestMarshaller2, responseMarshaller2, (Object) null, false, false, false);
    }

    private MethodDescriptor(MethodType type2, String fullMethodName2, Marshaller<ReqT> requestMarshaller2, Marshaller<RespT> responseMarshaller2, Object schemaDescriptor2, boolean idempotent2, boolean safe2, boolean sampledToLocalTracing2) {
        this.rawMethodNames = new AtomicReferenceArray<>(2);
        if (!safe2 || idempotent2) {
            this.type = (MethodType) Preconditions.checkNotNull(type2, "type");
            this.fullMethodName = (String) Preconditions.checkNotNull(fullMethodName2, "fullMethodName");
            this.serviceName = extractFullServiceName(fullMethodName2);
            this.requestMarshaller = (Marshaller) Preconditions.checkNotNull(requestMarshaller2, "requestMarshaller");
            this.responseMarshaller = (Marshaller) Preconditions.checkNotNull(responseMarshaller2, "responseMarshaller");
            this.schemaDescriptor = schemaDescriptor2;
            this.idempotent = idempotent2;
            this.safe = safe2;
            this.sampledToLocalTracing = sampledToLocalTracing2;
            return;
        }
        throw new AssertionError("safe should imply idempotent");
    }

    public MethodType getType() {
        return this.type;
    }

    public String getFullMethodName() {
        return this.fullMethodName;
    }

    @Nullable
    public String getServiceName() {
        return this.serviceName;
    }

    @Nullable
    public String getBareMethodName() {
        return extractBareMethodName(this.fullMethodName);
    }

    public RespT parseResponse(InputStream input) {
        return this.responseMarshaller.parse(input);
    }

    public InputStream streamRequest(ReqT requestMessage) {
        return this.requestMarshaller.stream(requestMessage);
    }

    public ReqT parseRequest(InputStream input) {
        return this.requestMarshaller.parse(input);
    }

    public InputStream streamResponse(RespT response) {
        return this.responseMarshaller.stream(response);
    }

    public Marshaller<ReqT> getRequestMarshaller() {
        return this.requestMarshaller;
    }

    public Marshaller<RespT> getResponseMarshaller() {
        return this.responseMarshaller;
    }

    @Nullable
    public Object getSchemaDescriptor() {
        return this.schemaDescriptor;
    }

    public boolean isIdempotent() {
        return this.idempotent;
    }

    public boolean isSafe() {
        return this.safe;
    }

    public boolean isSampledToLocalTracing() {
        return this.sampledToLocalTracing;
    }

    public static String generateFullMethodName(String fullServiceName, String methodName) {
        return ((String) Preconditions.checkNotNull(fullServiceName, "fullServiceName")) + "/" + ((String) Preconditions.checkNotNull(methodName, "methodName"));
    }

    @Nullable
    public static String extractFullServiceName(String fullMethodName2) {
        int index = ((String) Preconditions.checkNotNull(fullMethodName2, "fullMethodName")).lastIndexOf(47);
        if (index == -1) {
            return null;
        }
        return fullMethodName2.substring(0, index);
    }

    @Nullable
    public static String extractBareMethodName(String fullMethodName2) {
        int index = ((String) Preconditions.checkNotNull(fullMethodName2, "fullMethodName")).lastIndexOf(47);
        if (index == -1) {
            return null;
        }
        return fullMethodName2.substring(index + 1);
    }

    @CheckReturnValue
    public static <ReqT, RespT> Builder<ReqT, RespT> newBuilder() {
        return newBuilder((Marshaller) null, (Marshaller) null);
    }

    @CheckReturnValue
    public static <ReqT, RespT> Builder<ReqT, RespT> newBuilder(Marshaller<ReqT> requestMarshaller2, Marshaller<RespT> responseMarshaller2) {
        return new Builder().setRequestMarshaller(requestMarshaller2).setResponseMarshaller(responseMarshaller2);
    }

    @CheckReturnValue
    public Builder<ReqT, RespT> toBuilder() {
        return toBuilder(this.requestMarshaller, this.responseMarshaller);
    }

    @CheckReturnValue
    public <NewReqT, NewRespT> Builder<NewReqT, NewRespT> toBuilder(Marshaller<NewReqT> requestMarshaller2, Marshaller<NewRespT> responseMarshaller2) {
        return newBuilder().setRequestMarshaller(requestMarshaller2).setResponseMarshaller(responseMarshaller2).setType(this.type).setFullMethodName(this.fullMethodName).setIdempotent(this.idempotent).setSafe(this.safe).setSampledToLocalTracing(this.sampledToLocalTracing).setSchemaDescriptor(this.schemaDescriptor);
    }

    /* renamed from: io.grpc.MethodDescriptor$Builder */
    public static final class Builder<ReqT, RespT> {
        private String fullMethodName;
        private boolean idempotent;
        private Marshaller<ReqT> requestMarshaller;
        private Marshaller<RespT> responseMarshaller;
        private boolean safe;
        private boolean sampledToLocalTracing;
        private Object schemaDescriptor;
        private MethodType type;

        private Builder() {
        }

        public Builder<ReqT, RespT> setRequestMarshaller(Marshaller<ReqT> requestMarshaller2) {
            this.requestMarshaller = requestMarshaller2;
            return this;
        }

        public Builder<ReqT, RespT> setResponseMarshaller(Marshaller<RespT> responseMarshaller2) {
            this.responseMarshaller = responseMarshaller2;
            return this;
        }

        public Builder<ReqT, RespT> setType(MethodType type2) {
            this.type = type2;
            return this;
        }

        public Builder<ReqT, RespT> setFullMethodName(String fullMethodName2) {
            this.fullMethodName = fullMethodName2;
            return this;
        }

        public Builder<ReqT, RespT> setSchemaDescriptor(@Nullable Object schemaDescriptor2) {
            this.schemaDescriptor = schemaDescriptor2;
            return this;
        }

        public Builder<ReqT, RespT> setIdempotent(boolean idempotent2) {
            this.idempotent = idempotent2;
            if (!idempotent2) {
                this.safe = false;
            }
            return this;
        }

        public Builder<ReqT, RespT> setSafe(boolean safe2) {
            this.safe = safe2;
            if (safe2) {
                this.idempotent = true;
            }
            return this;
        }

        public Builder<ReqT, RespT> setSampledToLocalTracing(boolean value) {
            this.sampledToLocalTracing = value;
            return this;
        }

        @CheckReturnValue
        public MethodDescriptor<ReqT, RespT> build() {
            return new MethodDescriptor(this.type, this.fullMethodName, this.requestMarshaller, this.responseMarshaller, this.schemaDescriptor, this.idempotent, this.safe, this.sampledToLocalTracing);
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("fullMethodName", (Object) this.fullMethodName).add("type", (Object) this.type).add("idempotent", this.idempotent).add("safe", this.safe).add("sampledToLocalTracing", this.sampledToLocalTracing).add("requestMarshaller", (Object) this.requestMarshaller).add("responseMarshaller", (Object) this.responseMarshaller).add("schemaDescriptor", this.schemaDescriptor).omitNullValues().toString();
    }
}
