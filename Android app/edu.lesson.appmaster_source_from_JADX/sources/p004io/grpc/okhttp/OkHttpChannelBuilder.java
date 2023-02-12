package p004io.grpc.okhttp;

import com.google.common.base.Preconditions;
import com.google.firebase.messaging.Constants;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.EnumSet;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import p004io.grpc.CallCredentials;
import p004io.grpc.ChannelCredentials;
import p004io.grpc.ChannelLogger;
import p004io.grpc.CompositeCallCredentials;
import p004io.grpc.ManagedChannelBuilder;
import p004io.grpc.TlsChannelCredentials;
import p004io.grpc.internal.AbstractManagedChannelImplBuilder;
import p004io.grpc.internal.AtomicBackoff;
import p004io.grpc.internal.ClientTransportFactory;
import p004io.grpc.internal.ConnectionClientTransport;
import p004io.grpc.internal.GrpcUtil;
import p004io.grpc.internal.KeepAliveManager;
import p004io.grpc.internal.ManagedChannelImplBuilder;
import p004io.grpc.internal.SharedResourceHolder;
import p004io.grpc.internal.TransportTracer;
import p004io.grpc.okhttp.internal.CipherSuite;
import p004io.grpc.okhttp.internal.ConnectionSpec;
import p004io.grpc.okhttp.internal.Platform;
import p004io.grpc.okhttp.internal.TlsVersion;

/* renamed from: io.grpc.okhttp.OkHttpChannelBuilder */
public final class OkHttpChannelBuilder extends AbstractManagedChannelImplBuilder<OkHttpChannelBuilder> {
    private static final long AS_LARGE_AS_INFINITE = TimeUnit.DAYS.toNanos(1000);
    public static final int DEFAULT_FLOW_CONTROL_WINDOW = 65535;
    static final ConnectionSpec INTERNAL_DEFAULT_CONNECTION_SPEC = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).cipherSuites(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256).tlsVersions(TlsVersion.TLS_1_2).supportsTlsExtensions(true).build();
    /* access modifiers changed from: private */
    public static final SharedResourceHolder.Resource<Executor> SHARED_EXECUTOR = new SharedResourceHolder.Resource<Executor>() {
        public Executor create() {
            return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory("grpc-okhttp-%d", true));
        }

        public void close(Executor executor) {
            ((ExecutorService) executor).shutdown();
        }
    };
    private static final Logger log = Logger.getLogger(OkHttpChannelBuilder.class.getName());
    private static final EnumSet<TlsChannelCredentials.Feature> understoodTlsFeatures = EnumSet.of(TlsChannelCredentials.Feature.MTLS, TlsChannelCredentials.Feature.CUSTOM_MANAGERS);
    private ConnectionSpec connectionSpec;
    private int flowControlWindow;
    private final boolean freezeSecurityConfiguration;
    private HostnameVerifier hostnameVerifier;
    private long keepAliveTimeNanos;
    private long keepAliveTimeoutNanos;
    private boolean keepAliveWithoutCalls;
    private final ManagedChannelImplBuilder managedChannelImplBuilder;
    private int maxInboundMetadataSize;
    private NegotiationType negotiationType;
    private ScheduledExecutorService scheduledExecutorService;
    private SocketFactory socketFactory;
    private SSLSocketFactory sslSocketFactory;
    private Executor transportExecutor;
    private TransportTracer.Factory transportTracerFactory;
    private final boolean useGetForSafeMethods;

    /* renamed from: io.grpc.okhttp.OkHttpChannelBuilder$NegotiationType */
    private enum NegotiationType {
        TLS,
        PLAINTEXT
    }

    public static OkHttpChannelBuilder forAddress(String host, int port) {
        return new OkHttpChannelBuilder(host, port);
    }

    public static OkHttpChannelBuilder forAddress(String host, int port, ChannelCredentials creds) {
        return forTarget(GrpcUtil.authorityFromHostAndPort(host, port), creds);
    }

    public static OkHttpChannelBuilder forTarget(String target) {
        return new OkHttpChannelBuilder(target);
    }

    public static OkHttpChannelBuilder forTarget(String target, ChannelCredentials creds) {
        SslSocketFactoryResult result = sslSocketFactoryFrom(creds);
        if (result.error == null) {
            return new OkHttpChannelBuilder(target, creds, result.callCredentials, result.factory);
        }
        throw new IllegalArgumentException(result.error);
    }

    private OkHttpChannelBuilder(String host, int port) {
        this(GrpcUtil.authorityFromHostAndPort(host, port));
    }

    private OkHttpChannelBuilder(String target) {
        this.transportTracerFactory = TransportTracer.getDefaultFactory();
        this.connectionSpec = INTERNAL_DEFAULT_CONNECTION_SPEC;
        this.negotiationType = NegotiationType.TLS;
        this.keepAliveTimeNanos = Long.MAX_VALUE;
        this.keepAliveTimeoutNanos = GrpcUtil.DEFAULT_KEEPALIVE_TIMEOUT_NANOS;
        this.flowControlWindow = 65535;
        this.maxInboundMetadataSize = Integer.MAX_VALUE;
        this.useGetForSafeMethods = false;
        this.managedChannelImplBuilder = new ManagedChannelImplBuilder(target, new OkHttpChannelTransportFactoryBuilder(), new OkHttpChannelDefaultPortProvider());
        this.freezeSecurityConfiguration = false;
    }

    OkHttpChannelBuilder(String target, ChannelCredentials channelCreds, CallCredentials callCreds, SSLSocketFactory factory) {
        this.transportTracerFactory = TransportTracer.getDefaultFactory();
        this.connectionSpec = INTERNAL_DEFAULT_CONNECTION_SPEC;
        this.negotiationType = NegotiationType.TLS;
        this.keepAliveTimeNanos = Long.MAX_VALUE;
        this.keepAliveTimeoutNanos = GrpcUtil.DEFAULT_KEEPALIVE_TIMEOUT_NANOS;
        this.flowControlWindow = 65535;
        this.maxInboundMetadataSize = Integer.MAX_VALUE;
        this.useGetForSafeMethods = false;
        this.managedChannelImplBuilder = new ManagedChannelImplBuilder(target, channelCreds, callCreds, new OkHttpChannelTransportFactoryBuilder(), new OkHttpChannelDefaultPortProvider());
        this.sslSocketFactory = factory;
        this.negotiationType = factory == null ? NegotiationType.PLAINTEXT : NegotiationType.TLS;
        this.freezeSecurityConfiguration = true;
    }

    /* renamed from: io.grpc.okhttp.OkHttpChannelBuilder$OkHttpChannelTransportFactoryBuilder */
    private final class OkHttpChannelTransportFactoryBuilder implements ManagedChannelImplBuilder.ClientTransportFactoryBuilder {
        private OkHttpChannelTransportFactoryBuilder() {
        }

        public ClientTransportFactory buildClientTransportFactory() {
            return OkHttpChannelBuilder.this.buildTransportFactory();
        }
    }

    /* renamed from: io.grpc.okhttp.OkHttpChannelBuilder$OkHttpChannelDefaultPortProvider */
    private final class OkHttpChannelDefaultPortProvider implements ManagedChannelImplBuilder.ChannelBuilderDefaultPortProvider {
        private OkHttpChannelDefaultPortProvider() {
        }

        public int getDefaultPort() {
            return OkHttpChannelBuilder.this.getDefaultPort();
        }
    }

    /* access modifiers changed from: protected */
    public ManagedChannelBuilder<?> delegate() {
        return this.managedChannelImplBuilder;
    }

    /* access modifiers changed from: package-private */
    public OkHttpChannelBuilder setTransportTracerFactory(TransportTracer.Factory transportTracerFactory2) {
        this.transportTracerFactory = transportTracerFactory2;
        return this;
    }

    public OkHttpChannelBuilder transportExecutor(@Nullable Executor transportExecutor2) {
        this.transportExecutor = transportExecutor2;
        return this;
    }

    public OkHttpChannelBuilder socketFactory(@Nullable SocketFactory socketFactory2) {
        this.socketFactory = socketFactory2;
        return this;
    }

    @Deprecated
    public OkHttpChannelBuilder negotiationType(NegotiationType type) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        Preconditions.checkNotNull(type, "type");
        switch (C13242.$SwitchMap$io$grpc$okhttp$NegotiationType[type.ordinal()]) {
            case 1:
                this.negotiationType = NegotiationType.TLS;
                break;
            case 2:
                this.negotiationType = NegotiationType.PLAINTEXT;
                break;
            default:
                throw new AssertionError("Unknown negotiation type: " + type);
        }
        return this;
    }

    public OkHttpChannelBuilder keepAliveTime(long keepAliveTime, TimeUnit timeUnit) {
        Preconditions.checkArgument(keepAliveTime > 0, "keepalive time must be positive");
        long nanos = timeUnit.toNanos(keepAliveTime);
        this.keepAliveTimeNanos = nanos;
        long clampKeepAliveTimeInNanos = KeepAliveManager.clampKeepAliveTimeInNanos(nanos);
        this.keepAliveTimeNanos = clampKeepAliveTimeInNanos;
        if (clampKeepAliveTimeInNanos >= AS_LARGE_AS_INFINITE) {
            this.keepAliveTimeNanos = Long.MAX_VALUE;
        }
        return this;
    }

    public OkHttpChannelBuilder keepAliveTimeout(long keepAliveTimeout, TimeUnit timeUnit) {
        Preconditions.checkArgument(keepAliveTimeout > 0, "keepalive timeout must be positive");
        long nanos = timeUnit.toNanos(keepAliveTimeout);
        this.keepAliveTimeoutNanos = nanos;
        this.keepAliveTimeoutNanos = KeepAliveManager.clampKeepAliveTimeoutInNanos(nanos);
        return this;
    }

    public OkHttpChannelBuilder flowControlWindow(int flowControlWindow2) {
        Preconditions.checkState(flowControlWindow2 > 0, "flowControlWindow must be positive");
        this.flowControlWindow = flowControlWindow2;
        return this;
    }

    public OkHttpChannelBuilder keepAliveWithoutCalls(boolean enable) {
        this.keepAliveWithoutCalls = enable;
        return this;
    }

    public OkHttpChannelBuilder sslSocketFactory(SSLSocketFactory factory) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.sslSocketFactory = factory;
        this.negotiationType = NegotiationType.TLS;
        return this;
    }

    public OkHttpChannelBuilder hostnameVerifier(@Nullable HostnameVerifier hostnameVerifier2) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.hostnameVerifier = hostnameVerifier2;
        return this;
    }

    public OkHttpChannelBuilder connectionSpec(com.squareup.okhttp.ConnectionSpec connectionSpec2) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        Preconditions.checkArgument(connectionSpec2.isTls(), "plaintext ConnectionSpec is not accepted");
        this.connectionSpec = Utils.convertSpec(connectionSpec2);
        return this;
    }

    public OkHttpChannelBuilder tlsConnectionSpec(String[] tlsVersions, String[] cipherSuites) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        Preconditions.checkNotNull(tlsVersions, "tls versions must not null");
        Preconditions.checkNotNull(cipherSuites, "ciphers must not null");
        this.connectionSpec = new ConnectionSpec.Builder(true).supportsTlsExtensions(true).tlsVersions(tlsVersions).cipherSuites(cipherSuites).build();
        return this;
    }

    public OkHttpChannelBuilder usePlaintext() {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.negotiationType = NegotiationType.PLAINTEXT;
        return this;
    }

    public OkHttpChannelBuilder useTransportSecurity() {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.negotiationType = NegotiationType.TLS;
        return this;
    }

    public OkHttpChannelBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService2) {
        this.scheduledExecutorService = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService2, "scheduledExecutorService");
        return this;
    }

    public OkHttpChannelBuilder maxInboundMetadataSize(int bytes) {
        Preconditions.checkArgument(bytes > 0, "maxInboundMetadataSize must be > 0");
        this.maxInboundMetadataSize = bytes;
        return this;
    }

    public OkHttpChannelBuilder maxInboundMessageSize(int max) {
        Preconditions.checkArgument(max >= 0, "negative max");
        this.maxInboundMessageSize = max;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ClientTransportFactory buildTransportFactory() {
        OkHttpTransportFactory okHttpTransportFactory = r2;
        OkHttpTransportFactory okHttpTransportFactory2 = new OkHttpTransportFactory(this.transportExecutor, this.scheduledExecutorService, this.socketFactory, createSslSocketFactory(), this.hostnameVerifier, this.connectionSpec, this.maxInboundMessageSize, this.keepAliveTimeNanos != Long.MAX_VALUE, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.flowControlWindow, this.keepAliveWithoutCalls, this.maxInboundMetadataSize, this.transportTracerFactory, false);
        return okHttpTransportFactory;
    }

    /* access modifiers changed from: package-private */
    public OkHttpChannelBuilder disableCheckAuthority() {
        this.managedChannelImplBuilder.disableCheckAuthority();
        return this;
    }

    /* access modifiers changed from: package-private */
    public OkHttpChannelBuilder enableCheckAuthority() {
        this.managedChannelImplBuilder.enableCheckAuthority();
        return this;
    }

    /* renamed from: io.grpc.okhttp.OkHttpChannelBuilder$2 */
    static /* synthetic */ class C13242 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$okhttp$NegotiationType;
        static final /* synthetic */ int[] $SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType;

        static {
            int[] iArr = new int[NegotiationType.values().length];
            $SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType = iArr;
            try {
                iArr[NegotiationType.PLAINTEXT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType[NegotiationType.TLS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            int[] iArr2 = new int[NegotiationType.values().length];
            $SwitchMap$io$grpc$okhttp$NegotiationType = iArr2;
            try {
                iArr2[NegotiationType.TLS.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$io$grpc$okhttp$NegotiationType[NegotiationType.PLAINTEXT.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int getDefaultPort() {
        switch (C13242.$SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType[this.negotiationType.ordinal()]) {
            case 1:
                return 80;
            case 2:
                return GrpcUtil.DEFAULT_PORT_SSL;
            default:
                throw new AssertionError(this.negotiationType + " not handled");
        }
    }

    /* access modifiers changed from: package-private */
    public void setStatsEnabled(boolean value) {
        this.managedChannelImplBuilder.setStatsEnabled(value);
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public SSLSocketFactory createSslSocketFactory() {
        switch (C13242.$SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType[this.negotiationType.ordinal()]) {
            case 1:
                return null;
            case 2:
                try {
                    if (this.sslSocketFactory == null) {
                        this.sslSocketFactory = SSLContext.getInstance("Default", Platform.get().getProvider()).getSocketFactory();
                    }
                    return this.sslSocketFactory;
                } catch (GeneralSecurityException gse) {
                    throw new RuntimeException("TLS Provider failure", gse);
                }
            default:
                throw new RuntimeException("Unknown negotiation type: " + this.negotiationType);
        }
    }

    /* JADX WARNING: type inference failed for: r4v10, types: [java.lang.Object[]] */
    /* JADX WARNING: type inference failed for: r3v13, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static p004io.grpc.okhttp.OkHttpChannelBuilder.SslSocketFactoryResult sslSocketFactoryFrom(p004io.grpc.ChannelCredentials r8) {
        /*
            boolean r0 = r8 instanceof p004io.grpc.TlsChannelCredentials
            if (r0 == 0) goto L_0x00be
            r0 = r8
            io.grpc.TlsChannelCredentials r0 = (p004io.grpc.TlsChannelCredentials) r0
            java.util.EnumSet<io.grpc.TlsChannelCredentials$Feature> r1 = understoodTlsFeatures
            java.util.Set r1 = r0.incomprehensible(r1)
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L_0x002b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "TLS features not understood: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r1)
            java.lang.String r2 = r2.toString()
            io.grpc.okhttp.OkHttpChannelBuilder$SslSocketFactoryResult r2 = p004io.grpc.okhttp.OkHttpChannelBuilder.SslSocketFactoryResult.error(r2)
            return r2
        L_0x002b:
            r2 = 0
            java.util.List r3 = r0.getKeyManagers()
            r4 = 0
            if (r3 == 0) goto L_0x0041
            java.util.List r3 = r0.getKeyManagers()
            javax.net.ssl.KeyManager[] r5 = new javax.net.ssl.KeyManager[r4]
            java.lang.Object[] r3 = r3.toArray(r5)
            r2 = r3
            javax.net.ssl.KeyManager[] r2 = (javax.net.ssl.KeyManager[]) r2
            goto L_0x004e
        L_0x0041:
            byte[] r3 = r0.getPrivateKey()
            if (r3 == 0) goto L_0x004e
            java.lang.String r3 = "byte[]-based private key unsupported. Use KeyManager"
            io.grpc.okhttp.OkHttpChannelBuilder$SslSocketFactoryResult r3 = p004io.grpc.okhttp.OkHttpChannelBuilder.SslSocketFactoryResult.error(r3)
            return r3
        L_0x004e:
            r3 = 0
            java.util.List r5 = r0.getTrustManagers()
            if (r5 == 0) goto L_0x0063
            java.util.List r5 = r0.getTrustManagers()
            javax.net.ssl.TrustManager[] r4 = new javax.net.ssl.TrustManager[r4]
            java.lang.Object[] r4 = r5.toArray(r4)
            r3 = r4
            javax.net.ssl.TrustManager[] r3 = (javax.net.ssl.TrustManager[]) r3
            goto L_0x0099
        L_0x0063:
            byte[] r4 = r0.getRootCertificates()
            if (r4 == 0) goto L_0x0099
            byte[] r4 = r0.getRootCertificates()     // Catch:{ GeneralSecurityException -> 0x0073 }
            javax.net.ssl.TrustManager[] r4 = createTrustManager(r4)     // Catch:{ GeneralSecurityException -> 0x0073 }
            r3 = r4
            goto L_0x0099
        L_0x0073:
            r4 = move-exception
            java.util.logging.Logger r5 = log
            java.util.logging.Level r6 = java.util.logging.Level.FINE
            java.lang.String r7 = "Exception loading root certificates from credential"
            r5.log(r6, r7, r4)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Unable to load root certificates: "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = r4.getMessage()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            io.grpc.okhttp.OkHttpChannelBuilder$SslSocketFactoryResult r5 = p004io.grpc.okhttp.OkHttpChannelBuilder.SslSocketFactoryResult.error(r5)
            return r5
        L_0x0099:
            java.lang.String r4 = "TLS"
            io.grpc.okhttp.internal.Platform r5 = p004io.grpc.okhttp.internal.Platform.get()     // Catch:{ GeneralSecurityException -> 0x00b5 }
            java.security.Provider r5 = r5.getProvider()     // Catch:{ GeneralSecurityException -> 0x00b5 }
            javax.net.ssl.SSLContext r4 = javax.net.ssl.SSLContext.getInstance(r4, r5)     // Catch:{ GeneralSecurityException -> 0x00b5 }
            r5 = 0
            r4.init(r2, r3, r5)     // Catch:{ GeneralSecurityException -> 0x00b5 }
            javax.net.ssl.SSLSocketFactory r5 = r4.getSocketFactory()
            io.grpc.okhttp.OkHttpChannelBuilder$SslSocketFactoryResult r5 = p004io.grpc.okhttp.OkHttpChannelBuilder.SslSocketFactoryResult.factory(r5)
            return r5
        L_0x00b5:
            r4 = move-exception
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.String r6 = "TLS Provider failure"
            r5.<init>(r6, r4)
            throw r5
        L_0x00be:
            boolean r0 = r8 instanceof p004io.grpc.InsecureChannelCredentials
            if (r0 == 0) goto L_0x00c7
            io.grpc.okhttp.OkHttpChannelBuilder$SslSocketFactoryResult r0 = p004io.grpc.okhttp.OkHttpChannelBuilder.SslSocketFactoryResult.plaintext()
            return r0
        L_0x00c7:
            boolean r0 = r8 instanceof p004io.grpc.CompositeChannelCredentials
            if (r0 == 0) goto L_0x00df
            r0 = r8
            io.grpc.CompositeChannelCredentials r0 = (p004io.grpc.CompositeChannelCredentials) r0
            io.grpc.ChannelCredentials r1 = r0.getChannelCredentials()
            io.grpc.okhttp.OkHttpChannelBuilder$SslSocketFactoryResult r1 = sslSocketFactoryFrom(r1)
            io.grpc.CallCredentials r2 = r0.getCallCredentials()
            io.grpc.okhttp.OkHttpChannelBuilder$SslSocketFactoryResult r1 = r1.withCallCredentials(r2)
            return r1
        L_0x00df:
            boolean r0 = r8 instanceof p004io.grpc.okhttp.SslSocketFactoryChannelCredentials.ChannelCredentials
            if (r0 == 0) goto L_0x00ef
            r0 = r8
            io.grpc.okhttp.SslSocketFactoryChannelCredentials$ChannelCredentials r0 = (p004io.grpc.okhttp.SslSocketFactoryChannelCredentials.ChannelCredentials) r0
            javax.net.ssl.SSLSocketFactory r1 = r0.getFactory()
            io.grpc.okhttp.OkHttpChannelBuilder$SslSocketFactoryResult r1 = p004io.grpc.okhttp.OkHttpChannelBuilder.SslSocketFactoryResult.factory(r1)
            return r1
        L_0x00ef:
            boolean r0 = r8 instanceof p004io.grpc.ChoiceChannelCredentials
            if (r0 == 0) goto L_0x012d
            r0 = r8
            io.grpc.ChoiceChannelCredentials r0 = (p004io.grpc.ChoiceChannelCredentials) r0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.util.List r2 = r0.getCredentialsList()
            java.util.Iterator r2 = r2.iterator()
        L_0x0103:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0123
            java.lang.Object r3 = r2.next()
            io.grpc.ChannelCredentials r3 = (p004io.grpc.ChannelCredentials) r3
            io.grpc.okhttp.OkHttpChannelBuilder$SslSocketFactoryResult r4 = sslSocketFactoryFrom(r3)
            java.lang.String r5 = r4.error
            if (r5 != 0) goto L_0x0118
            return r4
        L_0x0118:
            java.lang.String r5 = ", "
            r1.append(r5)
            java.lang.String r5 = r4.error
            r1.append(r5)
            goto L_0x0103
        L_0x0123:
            r2 = 2
            java.lang.String r2 = r1.substring(r2)
            io.grpc.okhttp.OkHttpChannelBuilder$SslSocketFactoryResult r2 = p004io.grpc.okhttp.OkHttpChannelBuilder.SslSocketFactoryResult.error(r2)
            return r2
        L_0x012d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unsupported credential type: "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.Class r1 = r8.getClass()
            java.lang.String r1 = r1.getName()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            io.grpc.okhttp.OkHttpChannelBuilder$SslSocketFactoryResult r0 = p004io.grpc.okhttp.OkHttpChannelBuilder.SslSocketFactoryResult.error(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.okhttp.OkHttpChannelBuilder.sslSocketFactoryFrom(io.grpc.ChannelCredentials):io.grpc.okhttp.OkHttpChannelBuilder$SslSocketFactoryResult");
    }

    /* JADX INFO: finally extract failed */
    static TrustManager[] createTrustManager(byte[] rootCerts) throws GeneralSecurityException {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        try {
            ks.load((InputStream) null, (char[]) null);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            ByteArrayInputStream in = new ByteArrayInputStream(rootCerts);
            try {
                X509Certificate cert = (X509Certificate) cf.generateCertificate(in);
                ks.setCertificateEntry(cert.getSubjectX500Principal().getName("RFC2253"), cert);
                GrpcUtil.closeQuietly((Closeable) in);
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(ks);
                return trustManagerFactory.getTrustManagers();
            } catch (Throwable th) {
                GrpcUtil.closeQuietly((Closeable) in);
                throw th;
            }
        } catch (IOException ex) {
            throw new GeneralSecurityException(ex);
        }
    }

    /* renamed from: io.grpc.okhttp.OkHttpChannelBuilder$SslSocketFactoryResult */
    static final class SslSocketFactoryResult {
        public final CallCredentials callCredentials;
        public final String error;
        public final SSLSocketFactory factory;

        private SslSocketFactoryResult(SSLSocketFactory factory2, CallCredentials creds, String error2) {
            this.factory = factory2;
            this.callCredentials = creds;
            this.error = error2;
        }

        public static SslSocketFactoryResult error(String error2) {
            return new SslSocketFactoryResult((SSLSocketFactory) null, (CallCredentials) null, (String) Preconditions.checkNotNull(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR));
        }

        public static SslSocketFactoryResult plaintext() {
            return new SslSocketFactoryResult((SSLSocketFactory) null, (CallCredentials) null, (String) null);
        }

        public static SslSocketFactoryResult factory(SSLSocketFactory factory2) {
            return new SslSocketFactoryResult((SSLSocketFactory) Preconditions.checkNotNull(factory2, "factory"), (CallCredentials) null, (String) null);
        }

        public SslSocketFactoryResult withCallCredentials(CallCredentials callCreds) {
            Preconditions.checkNotNull(callCreds, "callCreds");
            if (this.error != null) {
                return this;
            }
            if (this.callCredentials != null) {
                callCreds = new CompositeCallCredentials(this.callCredentials, callCreds);
            }
            return new SslSocketFactoryResult(this.factory, callCreds, (String) null);
        }
    }

    /* renamed from: io.grpc.okhttp.OkHttpChannelBuilder$OkHttpTransportFactory */
    static final class OkHttpTransportFactory implements ClientTransportFactory {
        private boolean closed;
        private final ConnectionSpec connectionSpec;
        private final boolean enableKeepAlive;
        private final Executor executor;
        private final int flowControlWindow;
        @Nullable
        private final HostnameVerifier hostnameVerifier;
        private final AtomicBackoff keepAliveBackoff;
        private final long keepAliveTimeNanos;
        private final long keepAliveTimeoutNanos;
        private final boolean keepAliveWithoutCalls;
        private final int maxInboundMetadataSize;
        private final int maxMessageSize;
        private final SocketFactory socketFactory;
        @Nullable
        private final SSLSocketFactory sslSocketFactory;
        private final ScheduledExecutorService timeoutService;
        private final TransportTracer.Factory transportTracerFactory;
        private final boolean useGetForSafeMethods;
        private final boolean usingSharedExecutor;
        private final boolean usingSharedScheduler;

        private OkHttpTransportFactory(Executor executor2, @Nullable ScheduledExecutorService timeoutService2, @Nullable SocketFactory socketFactory2, @Nullable SSLSocketFactory sslSocketFactory2, @Nullable HostnameVerifier hostnameVerifier2, ConnectionSpec connectionSpec2, int maxMessageSize2, boolean enableKeepAlive2, long keepAliveTimeNanos2, long keepAliveTimeoutNanos2, int flowControlWindow2, boolean keepAliveWithoutCalls2, int maxInboundMetadataSize2, TransportTracer.Factory transportTracerFactory2, boolean useGetForSafeMethods2) {
            Executor executor3 = executor2;
            long j = keepAliveTimeNanos2;
            boolean z = timeoutService2 == null;
            this.usingSharedScheduler = z;
            this.timeoutService = z ? (ScheduledExecutorService) SharedResourceHolder.get(GrpcUtil.TIMER_SERVICE) : timeoutService2;
            this.socketFactory = socketFactory2;
            this.sslSocketFactory = sslSocketFactory2;
            this.hostnameVerifier = hostnameVerifier2;
            this.connectionSpec = connectionSpec2;
            this.maxMessageSize = maxMessageSize2;
            this.enableKeepAlive = enableKeepAlive2;
            this.keepAliveTimeNanos = j;
            this.keepAliveBackoff = new AtomicBackoff("keepalive time nanos", j);
            this.keepAliveTimeoutNanos = keepAliveTimeoutNanos2;
            this.flowControlWindow = flowControlWindow2;
            this.keepAliveWithoutCalls = keepAliveWithoutCalls2;
            this.maxInboundMetadataSize = maxInboundMetadataSize2;
            this.useGetForSafeMethods = useGetForSafeMethods2;
            boolean z2 = executor3 == null;
            this.usingSharedExecutor = z2;
            this.transportTracerFactory = (TransportTracer.Factory) Preconditions.checkNotNull(transportTracerFactory2, "transportTracerFactory");
            if (z2) {
                this.executor = (Executor) SharedResourceHolder.get(OkHttpChannelBuilder.SHARED_EXECUTOR);
            } else {
                this.executor = executor3;
            }
        }

        public ConnectionClientTransport newClientTransport(SocketAddress addr, ClientTransportFactory.ClientTransportOptions options, ChannelLogger channelLogger) {
            if (!this.closed) {
                final AtomicBackoff.State keepAliveTimeNanosState = this.keepAliveBackoff.getState();
                AtomicBackoff.State keepAliveTimeNanosState2 = keepAliveTimeNanosState;
                OkHttpClientTransport transport = new OkHttpClientTransport((InetSocketAddress) addr, options.getAuthority(), options.getUserAgent(), options.getEagAttributes(), this.executor, this.socketFactory, this.sslSocketFactory, this.hostnameVerifier, this.connectionSpec, this.maxMessageSize, this.flowControlWindow, options.getHttpConnectProxiedSocketAddress(), new Runnable() {
                    public void run() {
                        keepAliveTimeNanosState.backoff();
                    }
                }, this.maxInboundMetadataSize, this.transportTracerFactory.create(), this.useGetForSafeMethods);
                if (this.enableKeepAlive) {
                    transport.enableKeepAlive(true, keepAliveTimeNanosState2.get(), this.keepAliveTimeoutNanos, this.keepAliveWithoutCalls);
                }
                return transport;
            }
            throw new IllegalStateException("The transport factory is closed.");
        }

        public ScheduledExecutorService getScheduledExecutorService() {
            return this.timeoutService;
        }

        @CheckReturnValue
        @Nullable
        public ClientTransportFactory.SwapChannelCredentialsResult swapChannelCredentials(ChannelCredentials channelCreds) {
            SslSocketFactoryResult result = OkHttpChannelBuilder.sslSocketFactoryFrom(channelCreds);
            if (result.error != null) {
                return null;
            }
            return new ClientTransportFactory.SwapChannelCredentialsResult(new OkHttpTransportFactory(this.executor, this.timeoutService, this.socketFactory, result.factory, this.hostnameVerifier, this.connectionSpec, this.maxMessageSize, this.enableKeepAlive, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.flowControlWindow, this.keepAliveWithoutCalls, this.maxInboundMetadataSize, this.transportTracerFactory, this.useGetForSafeMethods), result.callCredentials);
        }

        public void close() {
            if (!this.closed) {
                this.closed = true;
                if (this.usingSharedScheduler) {
                    SharedResourceHolder.release(GrpcUtil.TIMER_SERVICE, this.timeoutService);
                }
                if (this.usingSharedExecutor) {
                    SharedResourceHolder.release(OkHttpChannelBuilder.SHARED_EXECUTOR, this.executor);
                }
            }
        }
    }
}
