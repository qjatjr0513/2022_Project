package p004io.grpc.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p004io.grpc.CallOptions;
import p004io.grpc.ClientStreamTracer;
import p004io.grpc.InternalChannelz;
import p004io.grpc.InternalLogId;
import p004io.grpc.InternalMetadata;
import p004io.grpc.LoadBalancer;
import p004io.grpc.Metadata;
import p004io.grpc.MethodDescriptor;
import p004io.grpc.ProxiedSocketAddress;
import p004io.grpc.ProxyDetector;
import p004io.grpc.Status;
import p004io.grpc.internal.ClientStreamListener;
import p004io.grpc.internal.ClientTransport;
import p004io.grpc.internal.SharedResourceHolder;
import p004io.grpc.internal.StreamListener;

/* renamed from: io.grpc.internal.GrpcUtil */
public final class GrpcUtil {
    public static final Splitter ACCEPT_ENCODING_SPLITTER = Splitter.m23on(',').trimResults();
    public static final CallOptions.Key<Boolean> CALL_OPTIONS_RPC_OWNED_BY_BALANCER = CallOptions.Key.create("io.grpc.internal.CALL_OPTIONS_RPC_OWNED_BY_BALANCER");
    public static final String CONTENT_ACCEPT_ENCODING = "accept-encoding";
    public static final Metadata.Key<byte[]> CONTENT_ACCEPT_ENCODING_KEY = InternalMetadata.keyOf(CONTENT_ACCEPT_ENCODING, new AcceptEncodingMarshaller());
    public static final String CONTENT_ENCODING = "content-encoding";
    public static final Metadata.Key<String> CONTENT_ENCODING_KEY = Metadata.Key.m347of(CONTENT_ENCODING, Metadata.ASCII_STRING_MARSHALLER);
    static final Metadata.Key<String> CONTENT_LENGTH_KEY = Metadata.Key.m347of("content-length", Metadata.ASCII_STRING_MARSHALLER);
    public static final String CONTENT_TYPE_GRPC = "application/grpc";
    public static final Metadata.Key<String> CONTENT_TYPE_KEY = Metadata.Key.m347of("content-type", Metadata.ASCII_STRING_MARSHALLER);
    public static final long DEFAULT_KEEPALIVE_TIMEOUT_NANOS = TimeUnit.SECONDS.toNanos(20);
    public static final String DEFAULT_LB_POLICY = "pick_first";
    public static final int DEFAULT_MAX_HEADER_LIST_SIZE = 8192;
    public static final int DEFAULT_MAX_MESSAGE_SIZE = 4194304;
    public static final int DEFAULT_PORT_PLAINTEXT = 80;
    public static final int DEFAULT_PORT_SSL = 443;
    public static final ProxyDetector DEFAULT_PROXY_DETECTOR = new ProxyDetectorImpl();
    public static final long DEFAULT_SERVER_KEEPALIVE_TIMEOUT_NANOS = TimeUnit.SECONDS.toNanos(20);
    public static final long DEFAULT_SERVER_KEEPALIVE_TIME_NANOS = TimeUnit.HOURS.toNanos(2);
    public static final String HTTP_METHOD = "POST";
    private static final String IMPLEMENTATION_VERSION = "1.44.1";
    public static final long KEEPALIVE_TIME_NANOS_DISABLED = Long.MAX_VALUE;
    public static final String MESSAGE_ACCEPT_ENCODING = "grpc-accept-encoding";
    public static final Metadata.Key<byte[]> MESSAGE_ACCEPT_ENCODING_KEY = InternalMetadata.keyOf(MESSAGE_ACCEPT_ENCODING, new AcceptEncodingMarshaller());
    public static final String MESSAGE_ENCODING = "grpc-encoding";
    public static final Metadata.Key<String> MESSAGE_ENCODING_KEY = Metadata.Key.m347of(MESSAGE_ENCODING, Metadata.ASCII_STRING_MARSHALLER);
    public static final ProxyDetector NOOP_PROXY_DETECTOR = new ProxyDetector() {
        @Nullable
        public ProxiedSocketAddress proxyFor(SocketAddress targetServerAddress) {
            return null;
        }
    };
    /* access modifiers changed from: private */
    public static final ClientStreamTracer NOOP_TRACER = new ClientStreamTracer() {
    };
    public static final long SERVER_KEEPALIVE_TIME_NANOS_DISABLED = Long.MAX_VALUE;
    public static final SharedResourceHolder.Resource<Executor> SHARED_CHANNEL_EXECUTOR = new SharedResourceHolder.Resource<Executor>() {
        private static final String NAME = "grpc-default-executor";

        public Executor create() {
            return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory("grpc-default-executor-%d", true));
        }

        public void close(Executor instance) {
            ((ExecutorService) instance).shutdown();
        }

        public String toString() {
            return NAME;
        }
    };
    public static final Supplier<Stopwatch> STOPWATCH_SUPPLIER = new Supplier<Stopwatch>() {
        public Stopwatch get() {
            return Stopwatch.createUnstarted();
        }
    };
    public static final Metadata.Key<String> TE_HEADER = Metadata.Key.m347of("te", Metadata.ASCII_STRING_MARSHALLER);
    public static final String TE_TRAILERS = "trailers";
    public static final String TIMEOUT = "grpc-timeout";
    public static final Metadata.Key<Long> TIMEOUT_KEY = Metadata.Key.m347of(TIMEOUT, new TimeoutMarshaller());
    public static final SharedResourceHolder.Resource<ScheduledExecutorService> TIMER_SERVICE = new SharedResourceHolder.Resource<ScheduledExecutorService>() {
        public ScheduledExecutorService create() {
            ScheduledExecutorService service = Executors.newScheduledThreadPool(1, GrpcUtil.getThreadFactory("grpc-timer-%d", true));
            try {
                service.getClass().getMethod("setRemoveOnCancelPolicy", new Class[]{Boolean.TYPE}).invoke(service, new Object[]{true});
            } catch (NoSuchMethodException e) {
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new RuntimeException(e3);
            }
            return Executors.unconfigurableScheduledExecutorService(service);
        }

        public void close(ScheduledExecutorService instance) {
            instance.shutdown();
        }
    };
    public static final Metadata.Key<String> USER_AGENT_KEY = Metadata.Key.m347of("user-agent", Metadata.ASCII_STRING_MARSHALLER);
    public static final Charset US_ASCII = StandardCharsets.US_ASCII;
    private static final Logger log = Logger.getLogger(GrpcUtil.class.getName());

    /* renamed from: io.grpc.internal.GrpcUtil$AcceptEncodingMarshaller */
    private static final class AcceptEncodingMarshaller implements InternalMetadata.TrustedAsciiMarshaller<byte[]> {
        private AcceptEncodingMarshaller() {
        }

        public byte[] toAsciiString(byte[] value) {
            return value;
        }

        public byte[] parseAsciiString(byte[] serialized) {
            return serialized;
        }
    }

    public static boolean shouldBeCountedForInUse(CallOptions callOptions) {
        return !Boolean.TRUE.equals(callOptions.getOption(CALL_OPTIONS_RPC_OWNED_BY_BALANCER));
    }

    public static Status httpStatusToGrpcStatus(int httpStatusCode) {
        return httpStatusToGrpcCode(httpStatusCode).toStatus().withDescription("HTTP status code " + httpStatusCode);
    }

    private static Status.Code httpStatusToGrpcCode(int httpStatusCode) {
        if (httpStatusCode >= 100 && httpStatusCode < 200) {
            return Status.Code.INTERNAL;
        }
        switch (httpStatusCode) {
            case WARNING_VALUE:
            case 431:
                return Status.Code.INTERNAL;
            case TypedValues.CycleType.TYPE_CURVE_FIT /*401*/:
                return Status.Code.UNAUTHENTICATED;
            case TypedValues.CycleType.TYPE_ALPHA /*403*/:
                return Status.Code.PERMISSION_DENIED;
            case 404:
                return Status.Code.UNIMPLEMENTED;
            case 429:
            case TypedValues.PositionType.TYPE_DRAWPATH /*502*/:
            case TypedValues.PositionType.TYPE_PERCENT_WIDTH /*503*/:
            case TypedValues.PositionType.TYPE_PERCENT_HEIGHT /*504*/:
                return Status.Code.UNAVAILABLE;
            default:
                return Status.Code.UNKNOWN;
        }
    }

    /* renamed from: io.grpc.internal.GrpcUtil$Http2Error */
    public enum Http2Error {
        NO_ERROR(0, Status.UNAVAILABLE),
        PROTOCOL_ERROR(1, Status.INTERNAL),
        INTERNAL_ERROR(2, Status.INTERNAL),
        FLOW_CONTROL_ERROR(3, Status.INTERNAL),
        SETTINGS_TIMEOUT(4, Status.INTERNAL),
        STREAM_CLOSED(5, Status.INTERNAL),
        FRAME_SIZE_ERROR(6, Status.INTERNAL),
        REFUSED_STREAM(7, Status.UNAVAILABLE),
        CANCEL(8, Status.CANCELLED),
        COMPRESSION_ERROR(9, Status.INTERNAL),
        CONNECT_ERROR(10, Status.INTERNAL),
        ENHANCE_YOUR_CALM(11, Status.RESOURCE_EXHAUSTED.withDescription("Bandwidth exhausted")),
        INADEQUATE_SECURITY(12, Status.PERMISSION_DENIED.withDescription("Permission denied as protocol is not secure enough to call")),
        HTTP_1_1_REQUIRED(13, Status.UNKNOWN);
        
        private static final Http2Error[] codeMap = null;
        private final int code;
        private final Status status;

        static {
            codeMap = buildHttp2CodeMap();
        }

        private static Http2Error[] buildHttp2CodeMap() {
            Http2Error[] errors = values();
            Http2Error[] http2CodeMap = new Http2Error[(((int) errors[errors.length - 1].code()) + 1)];
            for (Http2Error error : errors) {
                http2CodeMap[(int) error.code()] = error;
            }
            return http2CodeMap;
        }

        private Http2Error(int code2, Status status2) {
            this.code = code2;
            String description = "HTTP/2 error code: " + name();
            this.status = status2.withDescription(status2.getDescription() != null ? description + " (" + status2.getDescription() + ")" : description);
        }

        public long code() {
            return (long) this.code;
        }

        public Status status() {
            return this.status;
        }

        public static Http2Error forCode(long code2) {
            Http2Error[] http2ErrorArr = codeMap;
            if (code2 >= ((long) http2ErrorArr.length) || code2 < 0) {
                return null;
            }
            return http2ErrorArr[(int) code2];
        }

        public static Status statusForCode(long code2) {
            Http2Error error = forCode(code2);
            if (error == null) {
                return Status.fromCodeValue(INTERNAL_ERROR.status().getCode().value()).withDescription("Unrecognized HTTP/2 error code: " + code2);
            }
            return error.status();
        }
    }

    public static boolean isGrpcContentType(String contentType) {
        if (contentType == null || CONTENT_TYPE_GRPC.length() > contentType.length()) {
            return false;
        }
        String contentType2 = contentType.toLowerCase();
        if (!contentType2.startsWith(CONTENT_TYPE_GRPC)) {
            return false;
        }
        if (contentType2.length() == CONTENT_TYPE_GRPC.length()) {
            return true;
        }
        char nextChar = contentType2.charAt(CONTENT_TYPE_GRPC.length());
        if (nextChar == '+' || nextChar == ';') {
            return true;
        }
        return false;
    }

    public static String getGrpcUserAgent(String transportName, @Nullable String applicationUserAgent) {
        StringBuilder builder = new StringBuilder();
        if (applicationUserAgent != null) {
            builder.append(applicationUserAgent);
            builder.append(' ');
        }
        builder.append("grpc-java-");
        builder.append(transportName);
        builder.append('/');
        builder.append(IMPLEMENTATION_VERSION);
        return builder.toString();
    }

    /* renamed from: io.grpc.internal.GrpcUtil$GrpcBuildVersion */
    public static final class GrpcBuildVersion {
        private final String implementationVersion;
        private final String userAgent;

        private GrpcBuildVersion(String userAgent2, String implementationVersion2) {
            this.userAgent = (String) Preconditions.checkNotNull(userAgent2, "userAgentName");
            this.implementationVersion = (String) Preconditions.checkNotNull(implementationVersion2, "implementationVersion");
        }

        public String getUserAgent() {
            return this.userAgent;
        }

        public String getImplementationVersion() {
            return this.implementationVersion;
        }

        public String toString() {
            return this.userAgent + " " + this.implementationVersion;
        }
    }

    public static GrpcBuildVersion getGrpcBuildVersion() {
        return new GrpcBuildVersion("gRPC Java", IMPLEMENTATION_VERSION);
    }

    public static URI authorityToUri(String authority) {
        Preconditions.checkNotNull(authority, "authority");
        try {
            return new URI((String) null, authority, (String) null, (String) null, (String) null);
        } catch (URISyntaxException ex) {
            throw new IllegalArgumentException("Invalid authority: " + authority, ex);
        }
    }

    public static String checkAuthority(String authority) {
        URI uri = authorityToUri(authority);
        boolean z = true;
        Preconditions.checkArgument(uri.getHost() != null, "No host in authority '%s'", (Object) authority);
        if (uri.getUserInfo() != null) {
            z = false;
        }
        Preconditions.checkArgument(z, "Userinfo must not be present on authority: '%s'", (Object) authority);
        return authority;
    }

    public static String authorityFromHostAndPort(String host, int port) {
        try {
            return new URI((String) null, (String) null, host, port, (String) null, (String) null, (String) null).getAuthority();
        } catch (URISyntaxException ex) {
            throw new IllegalArgumentException("Invalid host or port: " + host + " " + port, ex);
        }
    }

    public static ThreadFactory getThreadFactory(String nameFormat, boolean daemon) {
        return new ThreadFactoryBuilder().setDaemon(daemon).setNameFormat(nameFormat).build();
    }

    public static String getHost(InetSocketAddress addr) {
        try {
            return (String) InetSocketAddress.class.getMethod("getHostString", new Class[0]).invoke(addr, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            return addr.getHostName();
        }
    }

    /* renamed from: io.grpc.internal.GrpcUtil$TimeoutMarshaller */
    static class TimeoutMarshaller implements Metadata.AsciiMarshaller<Long> {
        TimeoutMarshaller() {
        }

        public String toAsciiString(Long timeoutNanos) {
            TimeUnit unit = TimeUnit.NANOSECONDS;
            if (timeoutNanos.longValue() < 0) {
                throw new IllegalArgumentException("Timeout too small");
            } else if (timeoutNanos.longValue() < 100000000) {
                return timeoutNanos + "n";
            } else {
                if (timeoutNanos.longValue() < 100000000 * 1000) {
                    return unit.toMicros(timeoutNanos.longValue()) + "u";
                }
                if (timeoutNanos.longValue() < 100000000 * 1000 * 1000) {
                    return unit.toMillis(timeoutNanos.longValue()) + "m";
                }
                if (timeoutNanos.longValue() < 100000000 * 1000 * 1000 * 1000) {
                    return unit.toSeconds(timeoutNanos.longValue()) + "S";
                }
                if (timeoutNanos.longValue() < 100000000 * 1000 * 1000 * 1000 * 60) {
                    return unit.toMinutes(timeoutNanos.longValue()) + "M";
                }
                return unit.toHours(timeoutNanos.longValue()) + "H";
            }
        }

        public Long parseAsciiString(String serialized) {
            Preconditions.checkArgument(serialized.length() > 0, "empty timeout");
            Preconditions.checkArgument(serialized.length() <= 9, "bad timeout format");
            long value = Long.parseLong(serialized.substring(0, serialized.length() - 1));
            char unit = serialized.charAt(serialized.length() - 1);
            switch (unit) {
                case 'H':
                    return Long.valueOf(TimeUnit.HOURS.toNanos(value));
                case 'M':
                    return Long.valueOf(TimeUnit.MINUTES.toNanos(value));
                case 'S':
                    return Long.valueOf(TimeUnit.SECONDS.toNanos(value));
                case 'm':
                    return Long.valueOf(TimeUnit.MILLISECONDS.toNanos(value));
                case 'n':
                    return Long.valueOf(value);
                case 'u':
                    return Long.valueOf(TimeUnit.MICROSECONDS.toNanos(value));
                default:
                    throw new IllegalArgumentException(String.format("Invalid timeout unit: %s", new Object[]{Character.valueOf(unit)}));
            }
        }
    }

    @Nullable
    static ClientTransport getTransportFromPickResult(LoadBalancer.PickResult result, boolean isWaitForReady) {
        final ClientTransport transport;
        LoadBalancer.Subchannel subchannel = result.getSubchannel();
        if (subchannel != null) {
            transport = ((TransportProvider) subchannel.getInternalSubchannel()).obtainActiveTransport();
        } else {
            transport = null;
        }
        if (transport != null) {
            final ClientStreamTracer.Factory streamTracerFactory = result.getStreamTracerFactory();
            if (streamTracerFactory == null) {
                return transport;
            }
            return new ClientTransport() {
                public ClientStream newStream(MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions, ClientStreamTracer[] tracers) {
                    ClientStreamTracer streamTracer = ClientStreamTracer.Factory.this.newClientStreamTracer(ClientStreamTracer.StreamInfo.newBuilder().setCallOptions(callOptions).build(), headers);
                    Preconditions.checkState(tracers[tracers.length - 1] == GrpcUtil.NOOP_TRACER, "lb tracer already assigned");
                    tracers[tracers.length - 1] = streamTracer;
                    return transport.newStream(method, headers, callOptions, tracers);
                }

                public void ping(ClientTransport.PingCallback callback, Executor executor) {
                    transport.ping(callback, executor);
                }

                public InternalLogId getLogId() {
                    return transport.getLogId();
                }

                public ListenableFuture<InternalChannelz.SocketStats> getStats() {
                    return transport.getStats();
                }
            };
        } else if (result.getStatus().isOk()) {
            return null;
        } else {
            if (result.isDrop()) {
                return new FailingClientTransport(result.getStatus(), ClientStreamListener.RpcProgress.DROPPED);
            }
            if (!isWaitForReady) {
                return new FailingClientTransport(result.getStatus(), ClientStreamListener.RpcProgress.PROCESSED);
            }
            return null;
        }
    }

    public static ClientStreamTracer[] getClientStreamTracers(CallOptions callOptions, Metadata headers, int previousAttempts, boolean isTransparentRetry) {
        List<ClientStreamTracer.Factory> factories = callOptions.getStreamTracerFactories();
        ClientStreamTracer[] tracers = new ClientStreamTracer[(factories.size() + 1)];
        ClientStreamTracer.StreamInfo streamInfo = ClientStreamTracer.StreamInfo.newBuilder().setCallOptions(callOptions).setPreviousAttempts(previousAttempts).setIsTransparentRetry(isTransparentRetry).build();
        for (int i = 0; i < factories.size(); i++) {
            tracers[i] = factories.get(i).newClientStreamTracer(streamInfo, headers);
        }
        tracers[tracers.length - 1] = NOOP_TRACER;
        return tracers;
    }

    static void closeQuietly(StreamListener.MessageProducer producer) {
        while (true) {
            InputStream next = producer.next();
            InputStream message = next;
            if (next != null) {
                closeQuietly((Closeable) message);
            } else {
                return;
            }
        }
    }

    public static void closeQuietly(@Nullable Closeable message) {
        if (message != null) {
            try {
                message.close();
            } catch (IOException ioException) {
                log.log(Level.WARNING, "exception caught in closeQuietly", ioException);
            }
        }
    }

    static <T> boolean iterableContains(Iterable<T> iterable, T item) {
        if (iterable instanceof Collection) {
            try {
                return ((Collection) iterable).contains(item);
            } catch (NullPointerException e) {
                return false;
            } catch (ClassCastException e2) {
                return false;
            }
        } else {
            for (T i : iterable) {
                if (Objects.equal(i, item)) {
                    return true;
                }
            }
            return false;
        }
    }

    private GrpcUtil() {
    }
}
