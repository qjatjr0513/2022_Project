package com.google.logging.type;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Duration;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class HttpRequest extends GeneratedMessageLite<HttpRequest, Builder> implements HttpRequestOrBuilder {
    public static final int CACHE_FILL_BYTES_FIELD_NUMBER = 12;
    public static final int CACHE_HIT_FIELD_NUMBER = 9;
    public static final int CACHE_LOOKUP_FIELD_NUMBER = 11;
    public static final int CACHE_VALIDATED_WITH_ORIGIN_SERVER_FIELD_NUMBER = 10;
    /* access modifiers changed from: private */
    public static final HttpRequest DEFAULT_INSTANCE;
    public static final int LATENCY_FIELD_NUMBER = 14;
    private static volatile Parser<HttpRequest> PARSER = null;
    public static final int PROTOCOL_FIELD_NUMBER = 15;
    public static final int REFERER_FIELD_NUMBER = 8;
    public static final int REMOTE_IP_FIELD_NUMBER = 7;
    public static final int REQUEST_METHOD_FIELD_NUMBER = 1;
    public static final int REQUEST_SIZE_FIELD_NUMBER = 3;
    public static final int REQUEST_URL_FIELD_NUMBER = 2;
    public static final int RESPONSE_SIZE_FIELD_NUMBER = 5;
    public static final int SERVER_IP_FIELD_NUMBER = 13;
    public static final int STATUS_FIELD_NUMBER = 4;
    public static final int USER_AGENT_FIELD_NUMBER = 6;
    private long cacheFillBytes_;
    private boolean cacheHit_;
    private boolean cacheLookup_;
    private boolean cacheValidatedWithOriginServer_;
    private Duration latency_;
    private String protocol_ = "";
    private String referer_ = "";
    private String remoteIp_ = "";
    private String requestMethod_ = "";
    private long requestSize_;
    private String requestUrl_ = "";
    private long responseSize_;
    private String serverIp_ = "";
    private int status_;
    private String userAgent_ = "";

    private HttpRequest() {
    }

    public String getRequestMethod() {
        return this.requestMethod_;
    }

    public ByteString getRequestMethodBytes() {
        return ByteString.copyFromUtf8(this.requestMethod_);
    }

    /* access modifiers changed from: private */
    public void setRequestMethod(String value) {
        value.getClass();
        this.requestMethod_ = value;
    }

    /* access modifiers changed from: private */
    public void clearRequestMethod() {
        this.requestMethod_ = getDefaultInstance().getRequestMethod();
    }

    /* access modifiers changed from: private */
    public void setRequestMethodBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.requestMethod_ = value.toStringUtf8();
    }

    public String getRequestUrl() {
        return this.requestUrl_;
    }

    public ByteString getRequestUrlBytes() {
        return ByteString.copyFromUtf8(this.requestUrl_);
    }

    /* access modifiers changed from: private */
    public void setRequestUrl(String value) {
        value.getClass();
        this.requestUrl_ = value;
    }

    /* access modifiers changed from: private */
    public void clearRequestUrl() {
        this.requestUrl_ = getDefaultInstance().getRequestUrl();
    }

    /* access modifiers changed from: private */
    public void setRequestUrlBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.requestUrl_ = value.toStringUtf8();
    }

    public long getRequestSize() {
        return this.requestSize_;
    }

    /* access modifiers changed from: private */
    public void setRequestSize(long value) {
        this.requestSize_ = value;
    }

    /* access modifiers changed from: private */
    public void clearRequestSize() {
        this.requestSize_ = 0;
    }

    public int getStatus() {
        return this.status_;
    }

    /* access modifiers changed from: private */
    public void setStatus(int value) {
        this.status_ = value;
    }

    /* access modifiers changed from: private */
    public void clearStatus() {
        this.status_ = 0;
    }

    public long getResponseSize() {
        return this.responseSize_;
    }

    /* access modifiers changed from: private */
    public void setResponseSize(long value) {
        this.responseSize_ = value;
    }

    /* access modifiers changed from: private */
    public void clearResponseSize() {
        this.responseSize_ = 0;
    }

    public String getUserAgent() {
        return this.userAgent_;
    }

    public ByteString getUserAgentBytes() {
        return ByteString.copyFromUtf8(this.userAgent_);
    }

    /* access modifiers changed from: private */
    public void setUserAgent(String value) {
        value.getClass();
        this.userAgent_ = value;
    }

    /* access modifiers changed from: private */
    public void clearUserAgent() {
        this.userAgent_ = getDefaultInstance().getUserAgent();
    }

    /* access modifiers changed from: private */
    public void setUserAgentBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.userAgent_ = value.toStringUtf8();
    }

    public String getRemoteIp() {
        return this.remoteIp_;
    }

    public ByteString getRemoteIpBytes() {
        return ByteString.copyFromUtf8(this.remoteIp_);
    }

    /* access modifiers changed from: private */
    public void setRemoteIp(String value) {
        value.getClass();
        this.remoteIp_ = value;
    }

    /* access modifiers changed from: private */
    public void clearRemoteIp() {
        this.remoteIp_ = getDefaultInstance().getRemoteIp();
    }

    /* access modifiers changed from: private */
    public void setRemoteIpBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.remoteIp_ = value.toStringUtf8();
    }

    public String getServerIp() {
        return this.serverIp_;
    }

    public ByteString getServerIpBytes() {
        return ByteString.copyFromUtf8(this.serverIp_);
    }

    /* access modifiers changed from: private */
    public void setServerIp(String value) {
        value.getClass();
        this.serverIp_ = value;
    }

    /* access modifiers changed from: private */
    public void clearServerIp() {
        this.serverIp_ = getDefaultInstance().getServerIp();
    }

    /* access modifiers changed from: private */
    public void setServerIpBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.serverIp_ = value.toStringUtf8();
    }

    public String getReferer() {
        return this.referer_;
    }

    public ByteString getRefererBytes() {
        return ByteString.copyFromUtf8(this.referer_);
    }

    /* access modifiers changed from: private */
    public void setReferer(String value) {
        value.getClass();
        this.referer_ = value;
    }

    /* access modifiers changed from: private */
    public void clearReferer() {
        this.referer_ = getDefaultInstance().getReferer();
    }

    /* access modifiers changed from: private */
    public void setRefererBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.referer_ = value.toStringUtf8();
    }

    public boolean hasLatency() {
        return this.latency_ != null;
    }

    public Duration getLatency() {
        Duration duration = this.latency_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    /* access modifiers changed from: private */
    public void setLatency(Duration value) {
        value.getClass();
        this.latency_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeLatency(Duration value) {
        value.getClass();
        Duration duration = this.latency_;
        if (duration == null || duration == Duration.getDefaultInstance()) {
            this.latency_ = value;
        } else {
            this.latency_ = (Duration) ((Duration.Builder) Duration.newBuilder(this.latency_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearLatency() {
        this.latency_ = null;
    }

    public boolean getCacheLookup() {
        return this.cacheLookup_;
    }

    /* access modifiers changed from: private */
    public void setCacheLookup(boolean value) {
        this.cacheLookup_ = value;
    }

    /* access modifiers changed from: private */
    public void clearCacheLookup() {
        this.cacheLookup_ = false;
    }

    public boolean getCacheHit() {
        return this.cacheHit_;
    }

    /* access modifiers changed from: private */
    public void setCacheHit(boolean value) {
        this.cacheHit_ = value;
    }

    /* access modifiers changed from: private */
    public void clearCacheHit() {
        this.cacheHit_ = false;
    }

    public boolean getCacheValidatedWithOriginServer() {
        return this.cacheValidatedWithOriginServer_;
    }

    /* access modifiers changed from: private */
    public void setCacheValidatedWithOriginServer(boolean value) {
        this.cacheValidatedWithOriginServer_ = value;
    }

    /* access modifiers changed from: private */
    public void clearCacheValidatedWithOriginServer() {
        this.cacheValidatedWithOriginServer_ = false;
    }

    public long getCacheFillBytes() {
        return this.cacheFillBytes_;
    }

    /* access modifiers changed from: private */
    public void setCacheFillBytes(long value) {
        this.cacheFillBytes_ = value;
    }

    /* access modifiers changed from: private */
    public void clearCacheFillBytes() {
        this.cacheFillBytes_ = 0;
    }

    public String getProtocol() {
        return this.protocol_;
    }

    public ByteString getProtocolBytes() {
        return ByteString.copyFromUtf8(this.protocol_);
    }

    /* access modifiers changed from: private */
    public void setProtocol(String value) {
        value.getClass();
        this.protocol_ = value;
    }

    /* access modifiers changed from: private */
    public void clearProtocol() {
        this.protocol_ = getDefaultInstance().getProtocol();
    }

    /* access modifiers changed from: private */
    public void setProtocolBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.protocol_ = value.toStringUtf8();
    }

    public static HttpRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static HttpRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static HttpRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static HttpRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static HttpRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static HttpRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static HttpRequest parseFrom(InputStream input) throws IOException {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static HttpRequest parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static HttpRequest parseDelimitedFrom(InputStream input) throws IOException {
        return (HttpRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static HttpRequest parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (HttpRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static HttpRequest parseFrom(CodedInputStream input) throws IOException {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static HttpRequest parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(HttpRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<HttpRequest, Builder> implements HttpRequestOrBuilder {
        /* synthetic */ Builder(C09701 x0) {
            this();
        }

        private Builder() {
            super(HttpRequest.DEFAULT_INSTANCE);
        }

        public String getRequestMethod() {
            return ((HttpRequest) this.instance).getRequestMethod();
        }

        public ByteString getRequestMethodBytes() {
            return ((HttpRequest) this.instance).getRequestMethodBytes();
        }

        public Builder setRequestMethod(String value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setRequestMethod(value);
            return this;
        }

        public Builder clearRequestMethod() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearRequestMethod();
            return this;
        }

        public Builder setRequestMethodBytes(ByteString value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setRequestMethodBytes(value);
            return this;
        }

        public String getRequestUrl() {
            return ((HttpRequest) this.instance).getRequestUrl();
        }

        public ByteString getRequestUrlBytes() {
            return ((HttpRequest) this.instance).getRequestUrlBytes();
        }

        public Builder setRequestUrl(String value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setRequestUrl(value);
            return this;
        }

        public Builder clearRequestUrl() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearRequestUrl();
            return this;
        }

        public Builder setRequestUrlBytes(ByteString value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setRequestUrlBytes(value);
            return this;
        }

        public long getRequestSize() {
            return ((HttpRequest) this.instance).getRequestSize();
        }

        public Builder setRequestSize(long value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setRequestSize(value);
            return this;
        }

        public Builder clearRequestSize() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearRequestSize();
            return this;
        }

        public int getStatus() {
            return ((HttpRequest) this.instance).getStatus();
        }

        public Builder setStatus(int value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setStatus(value);
            return this;
        }

        public Builder clearStatus() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearStatus();
            return this;
        }

        public long getResponseSize() {
            return ((HttpRequest) this.instance).getResponseSize();
        }

        public Builder setResponseSize(long value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setResponseSize(value);
            return this;
        }

        public Builder clearResponseSize() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearResponseSize();
            return this;
        }

        public String getUserAgent() {
            return ((HttpRequest) this.instance).getUserAgent();
        }

        public ByteString getUserAgentBytes() {
            return ((HttpRequest) this.instance).getUserAgentBytes();
        }

        public Builder setUserAgent(String value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setUserAgent(value);
            return this;
        }

        public Builder clearUserAgent() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearUserAgent();
            return this;
        }

        public Builder setUserAgentBytes(ByteString value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setUserAgentBytes(value);
            return this;
        }

        public String getRemoteIp() {
            return ((HttpRequest) this.instance).getRemoteIp();
        }

        public ByteString getRemoteIpBytes() {
            return ((HttpRequest) this.instance).getRemoteIpBytes();
        }

        public Builder setRemoteIp(String value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setRemoteIp(value);
            return this;
        }

        public Builder clearRemoteIp() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearRemoteIp();
            return this;
        }

        public Builder setRemoteIpBytes(ByteString value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setRemoteIpBytes(value);
            return this;
        }

        public String getServerIp() {
            return ((HttpRequest) this.instance).getServerIp();
        }

        public ByteString getServerIpBytes() {
            return ((HttpRequest) this.instance).getServerIpBytes();
        }

        public Builder setServerIp(String value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setServerIp(value);
            return this;
        }

        public Builder clearServerIp() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearServerIp();
            return this;
        }

        public Builder setServerIpBytes(ByteString value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setServerIpBytes(value);
            return this;
        }

        public String getReferer() {
            return ((HttpRequest) this.instance).getReferer();
        }

        public ByteString getRefererBytes() {
            return ((HttpRequest) this.instance).getRefererBytes();
        }

        public Builder setReferer(String value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setReferer(value);
            return this;
        }

        public Builder clearReferer() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearReferer();
            return this;
        }

        public Builder setRefererBytes(ByteString value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setRefererBytes(value);
            return this;
        }

        public boolean hasLatency() {
            return ((HttpRequest) this.instance).hasLatency();
        }

        public Duration getLatency() {
            return ((HttpRequest) this.instance).getLatency();
        }

        public Builder setLatency(Duration value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setLatency(value);
            return this;
        }

        public Builder setLatency(Duration.Builder builderForValue) {
            copyOnWrite();
            ((HttpRequest) this.instance).setLatency((Duration) builderForValue.build());
            return this;
        }

        public Builder mergeLatency(Duration value) {
            copyOnWrite();
            ((HttpRequest) this.instance).mergeLatency(value);
            return this;
        }

        public Builder clearLatency() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearLatency();
            return this;
        }

        public boolean getCacheLookup() {
            return ((HttpRequest) this.instance).getCacheLookup();
        }

        public Builder setCacheLookup(boolean value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setCacheLookup(value);
            return this;
        }

        public Builder clearCacheLookup() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearCacheLookup();
            return this;
        }

        public boolean getCacheHit() {
            return ((HttpRequest) this.instance).getCacheHit();
        }

        public Builder setCacheHit(boolean value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setCacheHit(value);
            return this;
        }

        public Builder clearCacheHit() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearCacheHit();
            return this;
        }

        public boolean getCacheValidatedWithOriginServer() {
            return ((HttpRequest) this.instance).getCacheValidatedWithOriginServer();
        }

        public Builder setCacheValidatedWithOriginServer(boolean value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setCacheValidatedWithOriginServer(value);
            return this;
        }

        public Builder clearCacheValidatedWithOriginServer() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearCacheValidatedWithOriginServer();
            return this;
        }

        public long getCacheFillBytes() {
            return ((HttpRequest) this.instance).getCacheFillBytes();
        }

        public Builder setCacheFillBytes(long value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setCacheFillBytes(value);
            return this;
        }

        public Builder clearCacheFillBytes() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearCacheFillBytes();
            return this;
        }

        public String getProtocol() {
            return ((HttpRequest) this.instance).getProtocol();
        }

        public ByteString getProtocolBytes() {
            return ((HttpRequest) this.instance).getProtocolBytes();
        }

        public Builder setProtocol(String value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setProtocol(value);
            return this;
        }

        public Builder clearProtocol() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearProtocol();
            return this;
        }

        public Builder setProtocolBytes(ByteString value) {
            copyOnWrite();
            ((HttpRequest) this.instance).setProtocolBytes(value);
            return this;
        }
    }

    /* renamed from: com.google.logging.type.HttpRequest$1 */
    static /* synthetic */ class C09701 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f243xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f243xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f243xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f243xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f243xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f243xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f243xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f243xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C09701.f243xa1df5c61[method.ordinal()]) {
            case 1:
                return new HttpRequest();
            case 2:
                return new Builder((C09701) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u000f\u0000\u0000\u0001\u000f\u000f\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u0002\u0004\u0004\u0005\u0002\u0006Ȉ\u0007Ȉ\bȈ\t\u0007\n\u0007\u000b\u0007\f\u0002\rȈ\u000e\t\u000fȈ", new Object[]{"requestMethod_", "requestUrl_", "requestSize_", "status_", "responseSize_", "userAgent_", "remoteIp_", "referer_", "cacheHit_", "cacheValidatedWithOriginServer_", "cacheLookup_", "cacheFillBytes_", "serverIp_", "latency_", "protocol_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<HttpRequest> parser = PARSER;
                if (parser == null) {
                    synchronized (HttpRequest.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        HttpRequest defaultInstance = new HttpRequest();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(HttpRequest.class, defaultInstance);
    }

    public static HttpRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HttpRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
