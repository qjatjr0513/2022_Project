package com.google.rpc.context;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Struct;
import com.google.protobuf.Timestamp;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class AttributeContext extends GeneratedMessageLite<AttributeContext, Builder> implements AttributeContextOrBuilder {
    public static final int API_FIELD_NUMBER = 6;
    /* access modifiers changed from: private */
    public static final AttributeContext DEFAULT_INSTANCE;
    public static final int DESTINATION_FIELD_NUMBER = 2;
    public static final int ORIGIN_FIELD_NUMBER = 7;
    private static volatile Parser<AttributeContext> PARSER = null;
    public static final int REQUEST_FIELD_NUMBER = 3;
    public static final int RESOURCE_FIELD_NUMBER = 5;
    public static final int RESPONSE_FIELD_NUMBER = 4;
    public static final int SOURCE_FIELD_NUMBER = 1;
    private Api api_;
    private Peer destination_;
    private Peer origin_;
    private Request request_;
    private Resource resource_;
    private Response response_;
    private Peer source_;

    public interface ApiOrBuilder extends MessageLiteOrBuilder {
        String getOperation();

        ByteString getOperationBytes();

        String getProtocol();

        ByteString getProtocolBytes();

        String getService();

        ByteString getServiceBytes();

        String getVersion();

        ByteString getVersionBytes();
    }

    public interface AuthOrBuilder extends MessageLiteOrBuilder {
        String getAccessLevels(int i);

        ByteString getAccessLevelsBytes(int i);

        int getAccessLevelsCount();

        List<String> getAccessLevelsList();

        String getAudiences(int i);

        ByteString getAudiencesBytes(int i);

        int getAudiencesCount();

        List<String> getAudiencesList();

        Struct getClaims();

        String getPresenter();

        ByteString getPresenterBytes();

        String getPrincipal();

        ByteString getPrincipalBytes();

        boolean hasClaims();
    }

    public interface PeerOrBuilder extends MessageLiteOrBuilder {
        boolean containsLabels(String str);

        String getIp();

        ByteString getIpBytes();

        @Deprecated
        Map<String, String> getLabels();

        int getLabelsCount();

        Map<String, String> getLabelsMap();

        String getLabelsOrDefault(String str, String str2);

        String getLabelsOrThrow(String str);

        long getPort();

        String getPrincipal();

        ByteString getPrincipalBytes();

        String getRegionCode();

        ByteString getRegionCodeBytes();
    }

    public interface RequestOrBuilder extends MessageLiteOrBuilder {
        boolean containsHeaders(String str);

        Auth getAuth();

        @Deprecated
        Map<String, String> getHeaders();

        int getHeadersCount();

        Map<String, String> getHeadersMap();

        String getHeadersOrDefault(String str, String str2);

        String getHeadersOrThrow(String str);

        String getHost();

        ByteString getHostBytes();

        String getId();

        ByteString getIdBytes();

        String getMethod();

        ByteString getMethodBytes();

        String getPath();

        ByteString getPathBytes();

        String getProtocol();

        ByteString getProtocolBytes();

        String getQuery();

        ByteString getQueryBytes();

        String getReason();

        ByteString getReasonBytes();

        String getScheme();

        ByteString getSchemeBytes();

        long getSize();

        Timestamp getTime();

        boolean hasAuth();

        boolean hasTime();
    }

    public interface ResourceOrBuilder extends MessageLiteOrBuilder {
        boolean containsLabels(String str);

        @Deprecated
        Map<String, String> getLabels();

        int getLabelsCount();

        Map<String, String> getLabelsMap();

        String getLabelsOrDefault(String str, String str2);

        String getLabelsOrThrow(String str);

        String getName();

        ByteString getNameBytes();

        String getService();

        ByteString getServiceBytes();

        String getType();

        ByteString getTypeBytes();
    }

    public interface ResponseOrBuilder extends MessageLiteOrBuilder {
        boolean containsHeaders(String str);

        long getCode();

        @Deprecated
        Map<String, String> getHeaders();

        int getHeadersCount();

        Map<String, String> getHeadersMap();

        String getHeadersOrDefault(String str, String str2);

        String getHeadersOrThrow(String str);

        long getSize();

        Timestamp getTime();

        boolean hasTime();
    }

    private AttributeContext() {
    }

    public static final class Peer extends GeneratedMessageLite<Peer, Builder> implements PeerOrBuilder {
        /* access modifiers changed from: private */
        public static final Peer DEFAULT_INSTANCE;
        public static final int IP_FIELD_NUMBER = 1;
        public static final int LABELS_FIELD_NUMBER = 6;
        private static volatile Parser<Peer> PARSER = null;
        public static final int PORT_FIELD_NUMBER = 2;
        public static final int PRINCIPAL_FIELD_NUMBER = 7;
        public static final int REGION_CODE_FIELD_NUMBER = 8;
        private String ip_ = "";
        private MapFieldLite<String, String> labels_ = MapFieldLite.emptyMapField();
        private long port_;
        private String principal_ = "";
        private String regionCode_ = "";

        private Peer() {
        }

        public String getIp() {
            return this.ip_;
        }

        public ByteString getIpBytes() {
            return ByteString.copyFromUtf8(this.ip_);
        }

        /* access modifiers changed from: private */
        public void setIp(String value) {
            value.getClass();
            this.ip_ = value;
        }

        /* access modifiers changed from: private */
        public void clearIp() {
            this.ip_ = getDefaultInstance().getIp();
        }

        /* access modifiers changed from: private */
        public void setIpBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.ip_ = value.toStringUtf8();
        }

        public long getPort() {
            return this.port_;
        }

        /* access modifiers changed from: private */
        public void setPort(long value) {
            this.port_ = value;
        }

        /* access modifiers changed from: private */
        public void clearPort() {
            this.port_ = 0;
        }

        private static final class LabelsDefaultEntryHolder {
            static final MapEntryLite<String, String> defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

            private LabelsDefaultEntryHolder() {
            }
        }

        private MapFieldLite<String, String> internalGetLabels() {
            return this.labels_;
        }

        private MapFieldLite<String, String> internalGetMutableLabels() {
            if (!this.labels_.isMutable()) {
                this.labels_ = this.labels_.mutableCopy();
            }
            return this.labels_;
        }

        public int getLabelsCount() {
            return internalGetLabels().size();
        }

        public boolean containsLabels(String key) {
            key.getClass();
            return internalGetLabels().containsKey(key);
        }

        @Deprecated
        public Map<String, String> getLabels() {
            return getLabelsMap();
        }

        public Map<String, String> getLabelsMap() {
            return Collections.unmodifiableMap(internalGetLabels());
        }

        public String getLabelsOrDefault(String key, String defaultValue) {
            key.getClass();
            Map<String, String> map = internalGetLabels();
            return map.containsKey(key) ? map.get(key) : defaultValue;
        }

        public String getLabelsOrThrow(String key) {
            key.getClass();
            Map<String, String> map = internalGetLabels();
            if (map.containsKey(key)) {
                return map.get(key);
            }
            throw new IllegalArgumentException();
        }

        /* access modifiers changed from: private */
        public Map<String, String> getMutableLabelsMap() {
            return internalGetMutableLabels();
        }

        public String getPrincipal() {
            return this.principal_;
        }

        public ByteString getPrincipalBytes() {
            return ByteString.copyFromUtf8(this.principal_);
        }

        /* access modifiers changed from: private */
        public void setPrincipal(String value) {
            value.getClass();
            this.principal_ = value;
        }

        /* access modifiers changed from: private */
        public void clearPrincipal() {
            this.principal_ = getDefaultInstance().getPrincipal();
        }

        /* access modifiers changed from: private */
        public void setPrincipalBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.principal_ = value.toStringUtf8();
        }

        public String getRegionCode() {
            return this.regionCode_;
        }

        public ByteString getRegionCodeBytes() {
            return ByteString.copyFromUtf8(this.regionCode_);
        }

        /* access modifiers changed from: private */
        public void setRegionCode(String value) {
            value.getClass();
            this.regionCode_ = value;
        }

        /* access modifiers changed from: private */
        public void clearRegionCode() {
            this.regionCode_ = getDefaultInstance().getRegionCode();
        }

        /* access modifiers changed from: private */
        public void setRegionCodeBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.regionCode_ = value.toStringUtf8();
        }

        public static Peer parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Peer parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Peer parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Peer parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Peer parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Peer parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Peer parseFrom(InputStream input) throws IOException {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Peer parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Peer parseDelimitedFrom(InputStream input) throws IOException {
            return (Peer) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Peer parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Peer) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Peer parseFrom(CodedInputStream input) throws IOException {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Peer parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Peer prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Peer, Builder> implements PeerOrBuilder {
            /* synthetic */ Builder(C10711 x0) {
                this();
            }

            private Builder() {
                super(Peer.DEFAULT_INSTANCE);
            }

            public String getIp() {
                return ((Peer) this.instance).getIp();
            }

            public ByteString getIpBytes() {
                return ((Peer) this.instance).getIpBytes();
            }

            public Builder setIp(String value) {
                copyOnWrite();
                ((Peer) this.instance).setIp(value);
                return this;
            }

            public Builder clearIp() {
                copyOnWrite();
                ((Peer) this.instance).clearIp();
                return this;
            }

            public Builder setIpBytes(ByteString value) {
                copyOnWrite();
                ((Peer) this.instance).setIpBytes(value);
                return this;
            }

            public long getPort() {
                return ((Peer) this.instance).getPort();
            }

            public Builder setPort(long value) {
                copyOnWrite();
                ((Peer) this.instance).setPort(value);
                return this;
            }

            public Builder clearPort() {
                copyOnWrite();
                ((Peer) this.instance).clearPort();
                return this;
            }

            public int getLabelsCount() {
                return ((Peer) this.instance).getLabelsMap().size();
            }

            public boolean containsLabels(String key) {
                key.getClass();
                return ((Peer) this.instance).getLabelsMap().containsKey(key);
            }

            public Builder clearLabels() {
                copyOnWrite();
                ((Peer) this.instance).getMutableLabelsMap().clear();
                return this;
            }

            public Builder removeLabels(String key) {
                key.getClass();
                copyOnWrite();
                ((Peer) this.instance).getMutableLabelsMap().remove(key);
                return this;
            }

            @Deprecated
            public Map<String, String> getLabels() {
                return getLabelsMap();
            }

            public Map<String, String> getLabelsMap() {
                return Collections.unmodifiableMap(((Peer) this.instance).getLabelsMap());
            }

            public String getLabelsOrDefault(String key, String defaultValue) {
                key.getClass();
                Map<String, String> map = ((Peer) this.instance).getLabelsMap();
                return map.containsKey(key) ? map.get(key) : defaultValue;
            }

            public String getLabelsOrThrow(String key) {
                key.getClass();
                Map<String, String> map = ((Peer) this.instance).getLabelsMap();
                if (map.containsKey(key)) {
                    return map.get(key);
                }
                throw new IllegalArgumentException();
            }

            public Builder putLabels(String key, String value) {
                key.getClass();
                value.getClass();
                copyOnWrite();
                ((Peer) this.instance).getMutableLabelsMap().put(key, value);
                return this;
            }

            public Builder putAllLabels(Map<String, String> values) {
                copyOnWrite();
                ((Peer) this.instance).getMutableLabelsMap().putAll(values);
                return this;
            }

            public String getPrincipal() {
                return ((Peer) this.instance).getPrincipal();
            }

            public ByteString getPrincipalBytes() {
                return ((Peer) this.instance).getPrincipalBytes();
            }

            public Builder setPrincipal(String value) {
                copyOnWrite();
                ((Peer) this.instance).setPrincipal(value);
                return this;
            }

            public Builder clearPrincipal() {
                copyOnWrite();
                ((Peer) this.instance).clearPrincipal();
                return this;
            }

            public Builder setPrincipalBytes(ByteString value) {
                copyOnWrite();
                ((Peer) this.instance).setPrincipalBytes(value);
                return this;
            }

            public String getRegionCode() {
                return ((Peer) this.instance).getRegionCode();
            }

            public ByteString getRegionCodeBytes() {
                return ((Peer) this.instance).getRegionCodeBytes();
            }

            public Builder setRegionCode(String value) {
                copyOnWrite();
                ((Peer) this.instance).setRegionCode(value);
                return this;
            }

            public Builder clearRegionCode() {
                copyOnWrite();
                ((Peer) this.instance).clearRegionCode();
                return this;
            }

            public Builder setRegionCodeBytes(ByteString value) {
                copyOnWrite();
                ((Peer) this.instance).setRegionCodeBytes(value);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C10711.f293xa1df5c61[method.ordinal()]) {
                case 1:
                    return new Peer();
                case 2:
                    return new Builder((C10711) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\b\u0005\u0001\u0000\u0000\u0001Ȉ\u0002\u0002\u00062\u0007Ȉ\bȈ", new Object[]{"ip_", "port_", "labels_", LabelsDefaultEntryHolder.defaultEntry, "principal_", "regionCode_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Peer> parser = PARSER;
                    if (parser == null) {
                        synchronized (Peer.class) {
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
            Peer defaultInstance = new Peer();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Peer.class, defaultInstance);
        }

        public static Peer getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Peer> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.rpc.context.AttributeContext$1 */
    static /* synthetic */ class C10711 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f293xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f293xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f293xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f293xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f293xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f293xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f293xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f293xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public static final class Api extends GeneratedMessageLite<Api, Builder> implements ApiOrBuilder {
        /* access modifiers changed from: private */
        public static final Api DEFAULT_INSTANCE;
        public static final int OPERATION_FIELD_NUMBER = 2;
        private static volatile Parser<Api> PARSER = null;
        public static final int PROTOCOL_FIELD_NUMBER = 3;
        public static final int SERVICE_FIELD_NUMBER = 1;
        public static final int VERSION_FIELD_NUMBER = 4;
        private String operation_ = "";
        private String protocol_ = "";
        private String service_ = "";
        private String version_ = "";

        private Api() {
        }

        public String getService() {
            return this.service_;
        }

        public ByteString getServiceBytes() {
            return ByteString.copyFromUtf8(this.service_);
        }

        /* access modifiers changed from: private */
        public void setService(String value) {
            value.getClass();
            this.service_ = value;
        }

        /* access modifiers changed from: private */
        public void clearService() {
            this.service_ = getDefaultInstance().getService();
        }

        /* access modifiers changed from: private */
        public void setServiceBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.service_ = value.toStringUtf8();
        }

        public String getOperation() {
            return this.operation_;
        }

        public ByteString getOperationBytes() {
            return ByteString.copyFromUtf8(this.operation_);
        }

        /* access modifiers changed from: private */
        public void setOperation(String value) {
            value.getClass();
            this.operation_ = value;
        }

        /* access modifiers changed from: private */
        public void clearOperation() {
            this.operation_ = getDefaultInstance().getOperation();
        }

        /* access modifiers changed from: private */
        public void setOperationBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.operation_ = value.toStringUtf8();
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

        public String getVersion() {
            return this.version_;
        }

        public ByteString getVersionBytes() {
            return ByteString.copyFromUtf8(this.version_);
        }

        /* access modifiers changed from: private */
        public void setVersion(String value) {
            value.getClass();
            this.version_ = value;
        }

        /* access modifiers changed from: private */
        public void clearVersion() {
            this.version_ = getDefaultInstance().getVersion();
        }

        /* access modifiers changed from: private */
        public void setVersionBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.version_ = value.toStringUtf8();
        }

        public static Api parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Api parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Api parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Api parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Api parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Api parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Api parseFrom(InputStream input) throws IOException {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Api parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Api parseDelimitedFrom(InputStream input) throws IOException {
            return (Api) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Api parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Api) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Api parseFrom(CodedInputStream input) throws IOException {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Api parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Api prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Api, Builder> implements ApiOrBuilder {
            /* synthetic */ Builder(C10711 x0) {
                this();
            }

            private Builder() {
                super(Api.DEFAULT_INSTANCE);
            }

            public String getService() {
                return ((Api) this.instance).getService();
            }

            public ByteString getServiceBytes() {
                return ((Api) this.instance).getServiceBytes();
            }

            public Builder setService(String value) {
                copyOnWrite();
                ((Api) this.instance).setService(value);
                return this;
            }

            public Builder clearService() {
                copyOnWrite();
                ((Api) this.instance).clearService();
                return this;
            }

            public Builder setServiceBytes(ByteString value) {
                copyOnWrite();
                ((Api) this.instance).setServiceBytes(value);
                return this;
            }

            public String getOperation() {
                return ((Api) this.instance).getOperation();
            }

            public ByteString getOperationBytes() {
                return ((Api) this.instance).getOperationBytes();
            }

            public Builder setOperation(String value) {
                copyOnWrite();
                ((Api) this.instance).setOperation(value);
                return this;
            }

            public Builder clearOperation() {
                copyOnWrite();
                ((Api) this.instance).clearOperation();
                return this;
            }

            public Builder setOperationBytes(ByteString value) {
                copyOnWrite();
                ((Api) this.instance).setOperationBytes(value);
                return this;
            }

            public String getProtocol() {
                return ((Api) this.instance).getProtocol();
            }

            public ByteString getProtocolBytes() {
                return ((Api) this.instance).getProtocolBytes();
            }

            public Builder setProtocol(String value) {
                copyOnWrite();
                ((Api) this.instance).setProtocol(value);
                return this;
            }

            public Builder clearProtocol() {
                copyOnWrite();
                ((Api) this.instance).clearProtocol();
                return this;
            }

            public Builder setProtocolBytes(ByteString value) {
                copyOnWrite();
                ((Api) this.instance).setProtocolBytes(value);
                return this;
            }

            public String getVersion() {
                return ((Api) this.instance).getVersion();
            }

            public ByteString getVersionBytes() {
                return ((Api) this.instance).getVersionBytes();
            }

            public Builder setVersion(String value) {
                copyOnWrite();
                ((Api) this.instance).setVersion(value);
                return this;
            }

            public Builder clearVersion() {
                copyOnWrite();
                ((Api) this.instance).clearVersion();
                return this;
            }

            public Builder setVersionBytes(ByteString value) {
                copyOnWrite();
                ((Api) this.instance).setVersionBytes(value);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C10711.f293xa1df5c61[method.ordinal()]) {
                case 1:
                    return new Api();
                case 2:
                    return new Builder((C10711) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004Ȉ", new Object[]{"service_", "operation_", "protocol_", "version_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Api> parser = PARSER;
                    if (parser == null) {
                        synchronized (Api.class) {
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
            Api defaultInstance = new Api();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Api.class, defaultInstance);
        }

        public static Api getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Api> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Auth extends GeneratedMessageLite<Auth, Builder> implements AuthOrBuilder {
        public static final int ACCESS_LEVELS_FIELD_NUMBER = 5;
        public static final int AUDIENCES_FIELD_NUMBER = 2;
        public static final int CLAIMS_FIELD_NUMBER = 4;
        /* access modifiers changed from: private */
        public static final Auth DEFAULT_INSTANCE;
        private static volatile Parser<Auth> PARSER = null;
        public static final int PRESENTER_FIELD_NUMBER = 3;
        public static final int PRINCIPAL_FIELD_NUMBER = 1;
        private Internal.ProtobufList<String> accessLevels_ = GeneratedMessageLite.emptyProtobufList();
        private Internal.ProtobufList<String> audiences_ = GeneratedMessageLite.emptyProtobufList();
        private Struct claims_;
        private String presenter_ = "";
        private String principal_ = "";

        private Auth() {
        }

        public String getPrincipal() {
            return this.principal_;
        }

        public ByteString getPrincipalBytes() {
            return ByteString.copyFromUtf8(this.principal_);
        }

        /* access modifiers changed from: private */
        public void setPrincipal(String value) {
            value.getClass();
            this.principal_ = value;
        }

        /* access modifiers changed from: private */
        public void clearPrincipal() {
            this.principal_ = getDefaultInstance().getPrincipal();
        }

        /* access modifiers changed from: private */
        public void setPrincipalBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.principal_ = value.toStringUtf8();
        }

        public List<String> getAudiencesList() {
            return this.audiences_;
        }

        public int getAudiencesCount() {
            return this.audiences_.size();
        }

        public String getAudiences(int index) {
            return (String) this.audiences_.get(index);
        }

        public ByteString getAudiencesBytes(int index) {
            return ByteString.copyFromUtf8((String) this.audiences_.get(index));
        }

        private void ensureAudiencesIsMutable() {
            Internal.ProtobufList<String> tmp = this.audiences_;
            if (!tmp.isModifiable()) {
                this.audiences_ = GeneratedMessageLite.mutableCopy(tmp);
            }
        }

        /* access modifiers changed from: private */
        public void setAudiences(int index, String value) {
            value.getClass();
            ensureAudiencesIsMutable();
            this.audiences_.set(index, value);
        }

        /* access modifiers changed from: private */
        public void addAudiences(String value) {
            value.getClass();
            ensureAudiencesIsMutable();
            this.audiences_.add(value);
        }

        /* access modifiers changed from: private */
        public void addAllAudiences(Iterable<String> values) {
            ensureAudiencesIsMutable();
            AbstractMessageLite.addAll(values, this.audiences_);
        }

        /* access modifiers changed from: private */
        public void clearAudiences() {
            this.audiences_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void addAudiencesBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            ensureAudiencesIsMutable();
            this.audiences_.add(value.toStringUtf8());
        }

        public String getPresenter() {
            return this.presenter_;
        }

        public ByteString getPresenterBytes() {
            return ByteString.copyFromUtf8(this.presenter_);
        }

        /* access modifiers changed from: private */
        public void setPresenter(String value) {
            value.getClass();
            this.presenter_ = value;
        }

        /* access modifiers changed from: private */
        public void clearPresenter() {
            this.presenter_ = getDefaultInstance().getPresenter();
        }

        /* access modifiers changed from: private */
        public void setPresenterBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.presenter_ = value.toStringUtf8();
        }

        public boolean hasClaims() {
            return this.claims_ != null;
        }

        public Struct getClaims() {
            Struct struct = this.claims_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        /* access modifiers changed from: private */
        public void setClaims(Struct value) {
            value.getClass();
            this.claims_ = value;
        }

        /* access modifiers changed from: private */
        public void mergeClaims(Struct value) {
            value.getClass();
            Struct struct = this.claims_;
            if (struct == null || struct == Struct.getDefaultInstance()) {
                this.claims_ = value;
            } else {
                this.claims_ = (Struct) ((Struct.Builder) Struct.newBuilder(this.claims_).mergeFrom(value)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearClaims() {
            this.claims_ = null;
        }

        public List<String> getAccessLevelsList() {
            return this.accessLevels_;
        }

        public int getAccessLevelsCount() {
            return this.accessLevels_.size();
        }

        public String getAccessLevels(int index) {
            return (String) this.accessLevels_.get(index);
        }

        public ByteString getAccessLevelsBytes(int index) {
            return ByteString.copyFromUtf8((String) this.accessLevels_.get(index));
        }

        private void ensureAccessLevelsIsMutable() {
            Internal.ProtobufList<String> tmp = this.accessLevels_;
            if (!tmp.isModifiable()) {
                this.accessLevels_ = GeneratedMessageLite.mutableCopy(tmp);
            }
        }

        /* access modifiers changed from: private */
        public void setAccessLevels(int index, String value) {
            value.getClass();
            ensureAccessLevelsIsMutable();
            this.accessLevels_.set(index, value);
        }

        /* access modifiers changed from: private */
        public void addAccessLevels(String value) {
            value.getClass();
            ensureAccessLevelsIsMutable();
            this.accessLevels_.add(value);
        }

        /* access modifiers changed from: private */
        public void addAllAccessLevels(Iterable<String> values) {
            ensureAccessLevelsIsMutable();
            AbstractMessageLite.addAll(values, this.accessLevels_);
        }

        /* access modifiers changed from: private */
        public void clearAccessLevels() {
            this.accessLevels_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void addAccessLevelsBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            ensureAccessLevelsIsMutable();
            this.accessLevels_.add(value.toStringUtf8());
        }

        public static Auth parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Auth parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Auth parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Auth parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Auth parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Auth parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Auth parseFrom(InputStream input) throws IOException {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Auth parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Auth parseDelimitedFrom(InputStream input) throws IOException {
            return (Auth) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Auth parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Auth) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Auth parseFrom(CodedInputStream input) throws IOException {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Auth parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Auth prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Auth, Builder> implements AuthOrBuilder {
            /* synthetic */ Builder(C10711 x0) {
                this();
            }

            private Builder() {
                super(Auth.DEFAULT_INSTANCE);
            }

            public String getPrincipal() {
                return ((Auth) this.instance).getPrincipal();
            }

            public ByteString getPrincipalBytes() {
                return ((Auth) this.instance).getPrincipalBytes();
            }

            public Builder setPrincipal(String value) {
                copyOnWrite();
                ((Auth) this.instance).setPrincipal(value);
                return this;
            }

            public Builder clearPrincipal() {
                copyOnWrite();
                ((Auth) this.instance).clearPrincipal();
                return this;
            }

            public Builder setPrincipalBytes(ByteString value) {
                copyOnWrite();
                ((Auth) this.instance).setPrincipalBytes(value);
                return this;
            }

            public List<String> getAudiencesList() {
                return Collections.unmodifiableList(((Auth) this.instance).getAudiencesList());
            }

            public int getAudiencesCount() {
                return ((Auth) this.instance).getAudiencesCount();
            }

            public String getAudiences(int index) {
                return ((Auth) this.instance).getAudiences(index);
            }

            public ByteString getAudiencesBytes(int index) {
                return ((Auth) this.instance).getAudiencesBytes(index);
            }

            public Builder setAudiences(int index, String value) {
                copyOnWrite();
                ((Auth) this.instance).setAudiences(index, value);
                return this;
            }

            public Builder addAudiences(String value) {
                copyOnWrite();
                ((Auth) this.instance).addAudiences(value);
                return this;
            }

            public Builder addAllAudiences(Iterable<String> values) {
                copyOnWrite();
                ((Auth) this.instance).addAllAudiences(values);
                return this;
            }

            public Builder clearAudiences() {
                copyOnWrite();
                ((Auth) this.instance).clearAudiences();
                return this;
            }

            public Builder addAudiencesBytes(ByteString value) {
                copyOnWrite();
                ((Auth) this.instance).addAudiencesBytes(value);
                return this;
            }

            public String getPresenter() {
                return ((Auth) this.instance).getPresenter();
            }

            public ByteString getPresenterBytes() {
                return ((Auth) this.instance).getPresenterBytes();
            }

            public Builder setPresenter(String value) {
                copyOnWrite();
                ((Auth) this.instance).setPresenter(value);
                return this;
            }

            public Builder clearPresenter() {
                copyOnWrite();
                ((Auth) this.instance).clearPresenter();
                return this;
            }

            public Builder setPresenterBytes(ByteString value) {
                copyOnWrite();
                ((Auth) this.instance).setPresenterBytes(value);
                return this;
            }

            public boolean hasClaims() {
                return ((Auth) this.instance).hasClaims();
            }

            public Struct getClaims() {
                return ((Auth) this.instance).getClaims();
            }

            public Builder setClaims(Struct value) {
                copyOnWrite();
                ((Auth) this.instance).setClaims(value);
                return this;
            }

            public Builder setClaims(Struct.Builder builderForValue) {
                copyOnWrite();
                ((Auth) this.instance).setClaims((Struct) builderForValue.build());
                return this;
            }

            public Builder mergeClaims(Struct value) {
                copyOnWrite();
                ((Auth) this.instance).mergeClaims(value);
                return this;
            }

            public Builder clearClaims() {
                copyOnWrite();
                ((Auth) this.instance).clearClaims();
                return this;
            }

            public List<String> getAccessLevelsList() {
                return Collections.unmodifiableList(((Auth) this.instance).getAccessLevelsList());
            }

            public int getAccessLevelsCount() {
                return ((Auth) this.instance).getAccessLevelsCount();
            }

            public String getAccessLevels(int index) {
                return ((Auth) this.instance).getAccessLevels(index);
            }

            public ByteString getAccessLevelsBytes(int index) {
                return ((Auth) this.instance).getAccessLevelsBytes(index);
            }

            public Builder setAccessLevels(int index, String value) {
                copyOnWrite();
                ((Auth) this.instance).setAccessLevels(index, value);
                return this;
            }

            public Builder addAccessLevels(String value) {
                copyOnWrite();
                ((Auth) this.instance).addAccessLevels(value);
                return this;
            }

            public Builder addAllAccessLevels(Iterable<String> values) {
                copyOnWrite();
                ((Auth) this.instance).addAllAccessLevels(values);
                return this;
            }

            public Builder clearAccessLevels() {
                copyOnWrite();
                ((Auth) this.instance).clearAccessLevels();
                return this;
            }

            public Builder addAccessLevelsBytes(ByteString value) {
                copyOnWrite();
                ((Auth) this.instance).addAccessLevelsBytes(value);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C10711.f293xa1df5c61[method.ordinal()]) {
                case 1:
                    return new Auth();
                case 2:
                    return new Builder((C10711) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0002\u0000\u0001Ȉ\u0002Ț\u0003Ȉ\u0004\t\u0005Ț", new Object[]{"principal_", "audiences_", "presenter_", "claims_", "accessLevels_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Auth> parser = PARSER;
                    if (parser == null) {
                        synchronized (Auth.class) {
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
            Auth defaultInstance = new Auth();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Auth.class, defaultInstance);
        }

        public static Auth getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Auth> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Request extends GeneratedMessageLite<Request, Builder> implements RequestOrBuilder {
        public static final int AUTH_FIELD_NUMBER = 13;
        /* access modifiers changed from: private */
        public static final Request DEFAULT_INSTANCE;
        public static final int HEADERS_FIELD_NUMBER = 3;
        public static final int HOST_FIELD_NUMBER = 5;
        public static final int ID_FIELD_NUMBER = 1;
        public static final int METHOD_FIELD_NUMBER = 2;
        private static volatile Parser<Request> PARSER = null;
        public static final int PATH_FIELD_NUMBER = 4;
        public static final int PROTOCOL_FIELD_NUMBER = 11;
        public static final int QUERY_FIELD_NUMBER = 7;
        public static final int REASON_FIELD_NUMBER = 12;
        public static final int SCHEME_FIELD_NUMBER = 6;
        public static final int SIZE_FIELD_NUMBER = 10;
        public static final int TIME_FIELD_NUMBER = 9;
        private Auth auth_;
        private MapFieldLite<String, String> headers_ = MapFieldLite.emptyMapField();
        private String host_ = "";
        private String id_ = "";
        private String method_ = "";
        private String path_ = "";
        private String protocol_ = "";
        private String query_ = "";
        private String reason_ = "";
        private String scheme_ = "";
        private long size_;
        private Timestamp time_;

        private Request() {
        }

        public String getId() {
            return this.id_;
        }

        public ByteString getIdBytes() {
            return ByteString.copyFromUtf8(this.id_);
        }

        /* access modifiers changed from: private */
        public void setId(String value) {
            value.getClass();
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        public void clearId() {
            this.id_ = getDefaultInstance().getId();
        }

        /* access modifiers changed from: private */
        public void setIdBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.id_ = value.toStringUtf8();
        }

        public String getMethod() {
            return this.method_;
        }

        public ByteString getMethodBytes() {
            return ByteString.copyFromUtf8(this.method_);
        }

        /* access modifiers changed from: private */
        public void setMethod(String value) {
            value.getClass();
            this.method_ = value;
        }

        /* access modifiers changed from: private */
        public void clearMethod() {
            this.method_ = getDefaultInstance().getMethod();
        }

        /* access modifiers changed from: private */
        public void setMethodBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.method_ = value.toStringUtf8();
        }

        private static final class HeadersDefaultEntryHolder {
            static final MapEntryLite<String, String> defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

            private HeadersDefaultEntryHolder() {
            }
        }

        private MapFieldLite<String, String> internalGetHeaders() {
            return this.headers_;
        }

        private MapFieldLite<String, String> internalGetMutableHeaders() {
            if (!this.headers_.isMutable()) {
                this.headers_ = this.headers_.mutableCopy();
            }
            return this.headers_;
        }

        public int getHeadersCount() {
            return internalGetHeaders().size();
        }

        public boolean containsHeaders(String key) {
            key.getClass();
            return internalGetHeaders().containsKey(key);
        }

        @Deprecated
        public Map<String, String> getHeaders() {
            return getHeadersMap();
        }

        public Map<String, String> getHeadersMap() {
            return Collections.unmodifiableMap(internalGetHeaders());
        }

        public String getHeadersOrDefault(String key, String defaultValue) {
            key.getClass();
            Map<String, String> map = internalGetHeaders();
            return map.containsKey(key) ? map.get(key) : defaultValue;
        }

        public String getHeadersOrThrow(String key) {
            key.getClass();
            Map<String, String> map = internalGetHeaders();
            if (map.containsKey(key)) {
                return map.get(key);
            }
            throw new IllegalArgumentException();
        }

        /* access modifiers changed from: private */
        public Map<String, String> getMutableHeadersMap() {
            return internalGetMutableHeaders();
        }

        public String getPath() {
            return this.path_;
        }

        public ByteString getPathBytes() {
            return ByteString.copyFromUtf8(this.path_);
        }

        /* access modifiers changed from: private */
        public void setPath(String value) {
            value.getClass();
            this.path_ = value;
        }

        /* access modifiers changed from: private */
        public void clearPath() {
            this.path_ = getDefaultInstance().getPath();
        }

        /* access modifiers changed from: private */
        public void setPathBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.path_ = value.toStringUtf8();
        }

        public String getHost() {
            return this.host_;
        }

        public ByteString getHostBytes() {
            return ByteString.copyFromUtf8(this.host_);
        }

        /* access modifiers changed from: private */
        public void setHost(String value) {
            value.getClass();
            this.host_ = value;
        }

        /* access modifiers changed from: private */
        public void clearHost() {
            this.host_ = getDefaultInstance().getHost();
        }

        /* access modifiers changed from: private */
        public void setHostBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.host_ = value.toStringUtf8();
        }

        public String getScheme() {
            return this.scheme_;
        }

        public ByteString getSchemeBytes() {
            return ByteString.copyFromUtf8(this.scheme_);
        }

        /* access modifiers changed from: private */
        public void setScheme(String value) {
            value.getClass();
            this.scheme_ = value;
        }

        /* access modifiers changed from: private */
        public void clearScheme() {
            this.scheme_ = getDefaultInstance().getScheme();
        }

        /* access modifiers changed from: private */
        public void setSchemeBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.scheme_ = value.toStringUtf8();
        }

        public String getQuery() {
            return this.query_;
        }

        public ByteString getQueryBytes() {
            return ByteString.copyFromUtf8(this.query_);
        }

        /* access modifiers changed from: private */
        public void setQuery(String value) {
            value.getClass();
            this.query_ = value;
        }

        /* access modifiers changed from: private */
        public void clearQuery() {
            this.query_ = getDefaultInstance().getQuery();
        }

        /* access modifiers changed from: private */
        public void setQueryBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.query_ = value.toStringUtf8();
        }

        public boolean hasTime() {
            return this.time_ != null;
        }

        public Timestamp getTime() {
            Timestamp timestamp = this.time_;
            return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
        }

        /* access modifiers changed from: private */
        public void setTime(Timestamp value) {
            value.getClass();
            this.time_ = value;
        }

        /* access modifiers changed from: private */
        public void mergeTime(Timestamp value) {
            value.getClass();
            Timestamp timestamp = this.time_;
            if (timestamp == null || timestamp == Timestamp.getDefaultInstance()) {
                this.time_ = value;
            } else {
                this.time_ = (Timestamp) ((Timestamp.Builder) Timestamp.newBuilder(this.time_).mergeFrom(value)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearTime() {
            this.time_ = null;
        }

        public long getSize() {
            return this.size_;
        }

        /* access modifiers changed from: private */
        public void setSize(long value) {
            this.size_ = value;
        }

        /* access modifiers changed from: private */
        public void clearSize() {
            this.size_ = 0;
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

        public String getReason() {
            return this.reason_;
        }

        public ByteString getReasonBytes() {
            return ByteString.copyFromUtf8(this.reason_);
        }

        /* access modifiers changed from: private */
        public void setReason(String value) {
            value.getClass();
            this.reason_ = value;
        }

        /* access modifiers changed from: private */
        public void clearReason() {
            this.reason_ = getDefaultInstance().getReason();
        }

        /* access modifiers changed from: private */
        public void setReasonBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.reason_ = value.toStringUtf8();
        }

        public boolean hasAuth() {
            return this.auth_ != null;
        }

        public Auth getAuth() {
            Auth auth = this.auth_;
            return auth == null ? Auth.getDefaultInstance() : auth;
        }

        /* access modifiers changed from: private */
        public void setAuth(Auth value) {
            value.getClass();
            this.auth_ = value;
        }

        /* access modifiers changed from: private */
        public void mergeAuth(Auth value) {
            value.getClass();
            Auth auth = this.auth_;
            if (auth == null || auth == Auth.getDefaultInstance()) {
                this.auth_ = value;
            } else {
                this.auth_ = (Auth) ((Auth.Builder) Auth.newBuilder(this.auth_).mergeFrom(value)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearAuth() {
            this.auth_ = null;
        }

        public static Request parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Request parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Request parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Request parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Request parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Request parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Request parseFrom(InputStream input) throws IOException {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Request parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Request parseDelimitedFrom(InputStream input) throws IOException {
            return (Request) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Request parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Request) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Request parseFrom(CodedInputStream input) throws IOException {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Request parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Request prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Request, Builder> implements RequestOrBuilder {
            /* synthetic */ Builder(C10711 x0) {
                this();
            }

            private Builder() {
                super(Request.DEFAULT_INSTANCE);
            }

            public String getId() {
                return ((Request) this.instance).getId();
            }

            public ByteString getIdBytes() {
                return ((Request) this.instance).getIdBytes();
            }

            public Builder setId(String value) {
                copyOnWrite();
                ((Request) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((Request) this.instance).clearId();
                return this;
            }

            public Builder setIdBytes(ByteString value) {
                copyOnWrite();
                ((Request) this.instance).setIdBytes(value);
                return this;
            }

            public String getMethod() {
                return ((Request) this.instance).getMethod();
            }

            public ByteString getMethodBytes() {
                return ((Request) this.instance).getMethodBytes();
            }

            public Builder setMethod(String value) {
                copyOnWrite();
                ((Request) this.instance).setMethod(value);
                return this;
            }

            public Builder clearMethod() {
                copyOnWrite();
                ((Request) this.instance).clearMethod();
                return this;
            }

            public Builder setMethodBytes(ByteString value) {
                copyOnWrite();
                ((Request) this.instance).setMethodBytes(value);
                return this;
            }

            public int getHeadersCount() {
                return ((Request) this.instance).getHeadersMap().size();
            }

            public boolean containsHeaders(String key) {
                key.getClass();
                return ((Request) this.instance).getHeadersMap().containsKey(key);
            }

            public Builder clearHeaders() {
                copyOnWrite();
                ((Request) this.instance).getMutableHeadersMap().clear();
                return this;
            }

            public Builder removeHeaders(String key) {
                key.getClass();
                copyOnWrite();
                ((Request) this.instance).getMutableHeadersMap().remove(key);
                return this;
            }

            @Deprecated
            public Map<String, String> getHeaders() {
                return getHeadersMap();
            }

            public Map<String, String> getHeadersMap() {
                return Collections.unmodifiableMap(((Request) this.instance).getHeadersMap());
            }

            public String getHeadersOrDefault(String key, String defaultValue) {
                key.getClass();
                Map<String, String> map = ((Request) this.instance).getHeadersMap();
                return map.containsKey(key) ? map.get(key) : defaultValue;
            }

            public String getHeadersOrThrow(String key) {
                key.getClass();
                Map<String, String> map = ((Request) this.instance).getHeadersMap();
                if (map.containsKey(key)) {
                    return map.get(key);
                }
                throw new IllegalArgumentException();
            }

            public Builder putHeaders(String key, String value) {
                key.getClass();
                value.getClass();
                copyOnWrite();
                ((Request) this.instance).getMutableHeadersMap().put(key, value);
                return this;
            }

            public Builder putAllHeaders(Map<String, String> values) {
                copyOnWrite();
                ((Request) this.instance).getMutableHeadersMap().putAll(values);
                return this;
            }

            public String getPath() {
                return ((Request) this.instance).getPath();
            }

            public ByteString getPathBytes() {
                return ((Request) this.instance).getPathBytes();
            }

            public Builder setPath(String value) {
                copyOnWrite();
                ((Request) this.instance).setPath(value);
                return this;
            }

            public Builder clearPath() {
                copyOnWrite();
                ((Request) this.instance).clearPath();
                return this;
            }

            public Builder setPathBytes(ByteString value) {
                copyOnWrite();
                ((Request) this.instance).setPathBytes(value);
                return this;
            }

            public String getHost() {
                return ((Request) this.instance).getHost();
            }

            public ByteString getHostBytes() {
                return ((Request) this.instance).getHostBytes();
            }

            public Builder setHost(String value) {
                copyOnWrite();
                ((Request) this.instance).setHost(value);
                return this;
            }

            public Builder clearHost() {
                copyOnWrite();
                ((Request) this.instance).clearHost();
                return this;
            }

            public Builder setHostBytes(ByteString value) {
                copyOnWrite();
                ((Request) this.instance).setHostBytes(value);
                return this;
            }

            public String getScheme() {
                return ((Request) this.instance).getScheme();
            }

            public ByteString getSchemeBytes() {
                return ((Request) this.instance).getSchemeBytes();
            }

            public Builder setScheme(String value) {
                copyOnWrite();
                ((Request) this.instance).setScheme(value);
                return this;
            }

            public Builder clearScheme() {
                copyOnWrite();
                ((Request) this.instance).clearScheme();
                return this;
            }

            public Builder setSchemeBytes(ByteString value) {
                copyOnWrite();
                ((Request) this.instance).setSchemeBytes(value);
                return this;
            }

            public String getQuery() {
                return ((Request) this.instance).getQuery();
            }

            public ByteString getQueryBytes() {
                return ((Request) this.instance).getQueryBytes();
            }

            public Builder setQuery(String value) {
                copyOnWrite();
                ((Request) this.instance).setQuery(value);
                return this;
            }

            public Builder clearQuery() {
                copyOnWrite();
                ((Request) this.instance).clearQuery();
                return this;
            }

            public Builder setQueryBytes(ByteString value) {
                copyOnWrite();
                ((Request) this.instance).setQueryBytes(value);
                return this;
            }

            public boolean hasTime() {
                return ((Request) this.instance).hasTime();
            }

            public Timestamp getTime() {
                return ((Request) this.instance).getTime();
            }

            public Builder setTime(Timestamp value) {
                copyOnWrite();
                ((Request) this.instance).setTime(value);
                return this;
            }

            public Builder setTime(Timestamp.Builder builderForValue) {
                copyOnWrite();
                ((Request) this.instance).setTime((Timestamp) builderForValue.build());
                return this;
            }

            public Builder mergeTime(Timestamp value) {
                copyOnWrite();
                ((Request) this.instance).mergeTime(value);
                return this;
            }

            public Builder clearTime() {
                copyOnWrite();
                ((Request) this.instance).clearTime();
                return this;
            }

            public long getSize() {
                return ((Request) this.instance).getSize();
            }

            public Builder setSize(long value) {
                copyOnWrite();
                ((Request) this.instance).setSize(value);
                return this;
            }

            public Builder clearSize() {
                copyOnWrite();
                ((Request) this.instance).clearSize();
                return this;
            }

            public String getProtocol() {
                return ((Request) this.instance).getProtocol();
            }

            public ByteString getProtocolBytes() {
                return ((Request) this.instance).getProtocolBytes();
            }

            public Builder setProtocol(String value) {
                copyOnWrite();
                ((Request) this.instance).setProtocol(value);
                return this;
            }

            public Builder clearProtocol() {
                copyOnWrite();
                ((Request) this.instance).clearProtocol();
                return this;
            }

            public Builder setProtocolBytes(ByteString value) {
                copyOnWrite();
                ((Request) this.instance).setProtocolBytes(value);
                return this;
            }

            public String getReason() {
                return ((Request) this.instance).getReason();
            }

            public ByteString getReasonBytes() {
                return ((Request) this.instance).getReasonBytes();
            }

            public Builder setReason(String value) {
                copyOnWrite();
                ((Request) this.instance).setReason(value);
                return this;
            }

            public Builder clearReason() {
                copyOnWrite();
                ((Request) this.instance).clearReason();
                return this;
            }

            public Builder setReasonBytes(ByteString value) {
                copyOnWrite();
                ((Request) this.instance).setReasonBytes(value);
                return this;
            }

            public boolean hasAuth() {
                return ((Request) this.instance).hasAuth();
            }

            public Auth getAuth() {
                return ((Request) this.instance).getAuth();
            }

            public Builder setAuth(Auth value) {
                copyOnWrite();
                ((Request) this.instance).setAuth(value);
                return this;
            }

            public Builder setAuth(Auth.Builder builderForValue) {
                copyOnWrite();
                ((Request) this.instance).setAuth((Auth) builderForValue.build());
                return this;
            }

            public Builder mergeAuth(Auth value) {
                copyOnWrite();
                ((Request) this.instance).mergeAuth(value);
                return this;
            }

            public Builder clearAuth() {
                copyOnWrite();
                ((Request) this.instance).clearAuth();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C10711.f293xa1df5c61[method.ordinal()]) {
                case 1:
                    return new Request();
                case 2:
                    return new Builder((C10711) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\f\u0000\u0000\u0001\r\f\u0001\u0000\u0000\u0001Ȉ\u0002Ȉ\u00032\u0004Ȉ\u0005Ȉ\u0006Ȉ\u0007Ȉ\t\t\n\u0002\u000bȈ\fȈ\r\t", new Object[]{"id_", "method_", "headers_", HeadersDefaultEntryHolder.defaultEntry, "path_", "host_", "scheme_", "query_", "time_", "size_", "protocol_", "reason_", "auth_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Request> parser = PARSER;
                    if (parser == null) {
                        synchronized (Request.class) {
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
            Request defaultInstance = new Request();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Request.class, defaultInstance);
        }

        public static Request getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Request> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Response extends GeneratedMessageLite<Response, Builder> implements ResponseOrBuilder {
        public static final int CODE_FIELD_NUMBER = 1;
        /* access modifiers changed from: private */
        public static final Response DEFAULT_INSTANCE;
        public static final int HEADERS_FIELD_NUMBER = 3;
        private static volatile Parser<Response> PARSER = null;
        public static final int SIZE_FIELD_NUMBER = 2;
        public static final int TIME_FIELD_NUMBER = 4;
        private long code_;
        private MapFieldLite<String, String> headers_ = MapFieldLite.emptyMapField();
        private long size_;
        private Timestamp time_;

        private Response() {
        }

        public long getCode() {
            return this.code_;
        }

        /* access modifiers changed from: private */
        public void setCode(long value) {
            this.code_ = value;
        }

        /* access modifiers changed from: private */
        public void clearCode() {
            this.code_ = 0;
        }

        public long getSize() {
            return this.size_;
        }

        /* access modifiers changed from: private */
        public void setSize(long value) {
            this.size_ = value;
        }

        /* access modifiers changed from: private */
        public void clearSize() {
            this.size_ = 0;
        }

        private static final class HeadersDefaultEntryHolder {
            static final MapEntryLite<String, String> defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

            private HeadersDefaultEntryHolder() {
            }
        }

        private MapFieldLite<String, String> internalGetHeaders() {
            return this.headers_;
        }

        private MapFieldLite<String, String> internalGetMutableHeaders() {
            if (!this.headers_.isMutable()) {
                this.headers_ = this.headers_.mutableCopy();
            }
            return this.headers_;
        }

        public int getHeadersCount() {
            return internalGetHeaders().size();
        }

        public boolean containsHeaders(String key) {
            key.getClass();
            return internalGetHeaders().containsKey(key);
        }

        @Deprecated
        public Map<String, String> getHeaders() {
            return getHeadersMap();
        }

        public Map<String, String> getHeadersMap() {
            return Collections.unmodifiableMap(internalGetHeaders());
        }

        public String getHeadersOrDefault(String key, String defaultValue) {
            key.getClass();
            Map<String, String> map = internalGetHeaders();
            return map.containsKey(key) ? map.get(key) : defaultValue;
        }

        public String getHeadersOrThrow(String key) {
            key.getClass();
            Map<String, String> map = internalGetHeaders();
            if (map.containsKey(key)) {
                return map.get(key);
            }
            throw new IllegalArgumentException();
        }

        /* access modifiers changed from: private */
        public Map<String, String> getMutableHeadersMap() {
            return internalGetMutableHeaders();
        }

        public boolean hasTime() {
            return this.time_ != null;
        }

        public Timestamp getTime() {
            Timestamp timestamp = this.time_;
            return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
        }

        /* access modifiers changed from: private */
        public void setTime(Timestamp value) {
            value.getClass();
            this.time_ = value;
        }

        /* access modifiers changed from: private */
        public void mergeTime(Timestamp value) {
            value.getClass();
            Timestamp timestamp = this.time_;
            if (timestamp == null || timestamp == Timestamp.getDefaultInstance()) {
                this.time_ = value;
            } else {
                this.time_ = (Timestamp) ((Timestamp.Builder) Timestamp.newBuilder(this.time_).mergeFrom(value)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearTime() {
            this.time_ = null;
        }

        public static Response parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Response parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Response parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Response parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Response parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Response parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Response parseFrom(InputStream input) throws IOException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Response parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Response parseDelimitedFrom(InputStream input) throws IOException {
            return (Response) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Response parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Response) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Response parseFrom(CodedInputStream input) throws IOException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Response parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Response prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Response, Builder> implements ResponseOrBuilder {
            /* synthetic */ Builder(C10711 x0) {
                this();
            }

            private Builder() {
                super(Response.DEFAULT_INSTANCE);
            }

            public long getCode() {
                return ((Response) this.instance).getCode();
            }

            public Builder setCode(long value) {
                copyOnWrite();
                ((Response) this.instance).setCode(value);
                return this;
            }

            public Builder clearCode() {
                copyOnWrite();
                ((Response) this.instance).clearCode();
                return this;
            }

            public long getSize() {
                return ((Response) this.instance).getSize();
            }

            public Builder setSize(long value) {
                copyOnWrite();
                ((Response) this.instance).setSize(value);
                return this;
            }

            public Builder clearSize() {
                copyOnWrite();
                ((Response) this.instance).clearSize();
                return this;
            }

            public int getHeadersCount() {
                return ((Response) this.instance).getHeadersMap().size();
            }

            public boolean containsHeaders(String key) {
                key.getClass();
                return ((Response) this.instance).getHeadersMap().containsKey(key);
            }

            public Builder clearHeaders() {
                copyOnWrite();
                ((Response) this.instance).getMutableHeadersMap().clear();
                return this;
            }

            public Builder removeHeaders(String key) {
                key.getClass();
                copyOnWrite();
                ((Response) this.instance).getMutableHeadersMap().remove(key);
                return this;
            }

            @Deprecated
            public Map<String, String> getHeaders() {
                return getHeadersMap();
            }

            public Map<String, String> getHeadersMap() {
                return Collections.unmodifiableMap(((Response) this.instance).getHeadersMap());
            }

            public String getHeadersOrDefault(String key, String defaultValue) {
                key.getClass();
                Map<String, String> map = ((Response) this.instance).getHeadersMap();
                return map.containsKey(key) ? map.get(key) : defaultValue;
            }

            public String getHeadersOrThrow(String key) {
                key.getClass();
                Map<String, String> map = ((Response) this.instance).getHeadersMap();
                if (map.containsKey(key)) {
                    return map.get(key);
                }
                throw new IllegalArgumentException();
            }

            public Builder putHeaders(String key, String value) {
                key.getClass();
                value.getClass();
                copyOnWrite();
                ((Response) this.instance).getMutableHeadersMap().put(key, value);
                return this;
            }

            public Builder putAllHeaders(Map<String, String> values) {
                copyOnWrite();
                ((Response) this.instance).getMutableHeadersMap().putAll(values);
                return this;
            }

            public boolean hasTime() {
                return ((Response) this.instance).hasTime();
            }

            public Timestamp getTime() {
                return ((Response) this.instance).getTime();
            }

            public Builder setTime(Timestamp value) {
                copyOnWrite();
                ((Response) this.instance).setTime(value);
                return this;
            }

            public Builder setTime(Timestamp.Builder builderForValue) {
                copyOnWrite();
                ((Response) this.instance).setTime((Timestamp) builderForValue.build());
                return this;
            }

            public Builder mergeTime(Timestamp value) {
                copyOnWrite();
                ((Response) this.instance).mergeTime(value);
                return this;
            }

            public Builder clearTime() {
                copyOnWrite();
                ((Response) this.instance).clearTime();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C10711.f293xa1df5c61[method.ordinal()]) {
                case 1:
                    return new Response();
                case 2:
                    return new Builder((C10711) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0001\u0000\u0000\u0001\u0002\u0002\u0002\u00032\u0004\t", new Object[]{"code_", "size_", "headers_", HeadersDefaultEntryHolder.defaultEntry, "time_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Response> parser = PARSER;
                    if (parser == null) {
                        synchronized (Response.class) {
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
            Response defaultInstance = new Response();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Response.class, defaultInstance);
        }

        public static Response getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Response> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Resource extends GeneratedMessageLite<Resource, Builder> implements ResourceOrBuilder {
        /* access modifiers changed from: private */
        public static final Resource DEFAULT_INSTANCE;
        public static final int LABELS_FIELD_NUMBER = 4;
        public static final int NAME_FIELD_NUMBER = 2;
        private static volatile Parser<Resource> PARSER = null;
        public static final int SERVICE_FIELD_NUMBER = 1;
        public static final int TYPE_FIELD_NUMBER = 3;
        private MapFieldLite<String, String> labels_ = MapFieldLite.emptyMapField();
        private String name_ = "";
        private String service_ = "";
        private String type_ = "";

        private Resource() {
        }

        public String getService() {
            return this.service_;
        }

        public ByteString getServiceBytes() {
            return ByteString.copyFromUtf8(this.service_);
        }

        /* access modifiers changed from: private */
        public void setService(String value) {
            value.getClass();
            this.service_ = value;
        }

        /* access modifiers changed from: private */
        public void clearService() {
            this.service_ = getDefaultInstance().getService();
        }

        /* access modifiers changed from: private */
        public void setServiceBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.service_ = value.toStringUtf8();
        }

        public String getName() {
            return this.name_;
        }

        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        /* access modifiers changed from: private */
        public void setName(String value) {
            value.getClass();
            this.name_ = value;
        }

        /* access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        /* access modifiers changed from: private */
        public void setNameBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.name_ = value.toStringUtf8();
        }

        public String getType() {
            return this.type_;
        }

        public ByteString getTypeBytes() {
            return ByteString.copyFromUtf8(this.type_);
        }

        /* access modifiers changed from: private */
        public void setType(String value) {
            value.getClass();
            this.type_ = value;
        }

        /* access modifiers changed from: private */
        public void clearType() {
            this.type_ = getDefaultInstance().getType();
        }

        /* access modifiers changed from: private */
        public void setTypeBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.type_ = value.toStringUtf8();
        }

        private static final class LabelsDefaultEntryHolder {
            static final MapEntryLite<String, String> defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

            private LabelsDefaultEntryHolder() {
            }
        }

        private MapFieldLite<String, String> internalGetLabels() {
            return this.labels_;
        }

        private MapFieldLite<String, String> internalGetMutableLabels() {
            if (!this.labels_.isMutable()) {
                this.labels_ = this.labels_.mutableCopy();
            }
            return this.labels_;
        }

        public int getLabelsCount() {
            return internalGetLabels().size();
        }

        public boolean containsLabels(String key) {
            key.getClass();
            return internalGetLabels().containsKey(key);
        }

        @Deprecated
        public Map<String, String> getLabels() {
            return getLabelsMap();
        }

        public Map<String, String> getLabelsMap() {
            return Collections.unmodifiableMap(internalGetLabels());
        }

        public String getLabelsOrDefault(String key, String defaultValue) {
            key.getClass();
            Map<String, String> map = internalGetLabels();
            return map.containsKey(key) ? map.get(key) : defaultValue;
        }

        public String getLabelsOrThrow(String key) {
            key.getClass();
            Map<String, String> map = internalGetLabels();
            if (map.containsKey(key)) {
                return map.get(key);
            }
            throw new IllegalArgumentException();
        }

        /* access modifiers changed from: private */
        public Map<String, String> getMutableLabelsMap() {
            return internalGetMutableLabels();
        }

        public static Resource parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Resource parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Resource parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Resource parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Resource parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Resource parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Resource parseFrom(InputStream input) throws IOException {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Resource parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Resource parseDelimitedFrom(InputStream input) throws IOException {
            return (Resource) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Resource parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Resource) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Resource parseFrom(CodedInputStream input) throws IOException {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Resource parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Resource prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Resource, Builder> implements ResourceOrBuilder {
            /* synthetic */ Builder(C10711 x0) {
                this();
            }

            private Builder() {
                super(Resource.DEFAULT_INSTANCE);
            }

            public String getService() {
                return ((Resource) this.instance).getService();
            }

            public ByteString getServiceBytes() {
                return ((Resource) this.instance).getServiceBytes();
            }

            public Builder setService(String value) {
                copyOnWrite();
                ((Resource) this.instance).setService(value);
                return this;
            }

            public Builder clearService() {
                copyOnWrite();
                ((Resource) this.instance).clearService();
                return this;
            }

            public Builder setServiceBytes(ByteString value) {
                copyOnWrite();
                ((Resource) this.instance).setServiceBytes(value);
                return this;
            }

            public String getName() {
                return ((Resource) this.instance).getName();
            }

            public ByteString getNameBytes() {
                return ((Resource) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((Resource) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((Resource) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((Resource) this.instance).setNameBytes(value);
                return this;
            }

            public String getType() {
                return ((Resource) this.instance).getType();
            }

            public ByteString getTypeBytes() {
                return ((Resource) this.instance).getTypeBytes();
            }

            public Builder setType(String value) {
                copyOnWrite();
                ((Resource) this.instance).setType(value);
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((Resource) this.instance).clearType();
                return this;
            }

            public Builder setTypeBytes(ByteString value) {
                copyOnWrite();
                ((Resource) this.instance).setTypeBytes(value);
                return this;
            }

            public int getLabelsCount() {
                return ((Resource) this.instance).getLabelsMap().size();
            }

            public boolean containsLabels(String key) {
                key.getClass();
                return ((Resource) this.instance).getLabelsMap().containsKey(key);
            }

            public Builder clearLabels() {
                copyOnWrite();
                ((Resource) this.instance).getMutableLabelsMap().clear();
                return this;
            }

            public Builder removeLabels(String key) {
                key.getClass();
                copyOnWrite();
                ((Resource) this.instance).getMutableLabelsMap().remove(key);
                return this;
            }

            @Deprecated
            public Map<String, String> getLabels() {
                return getLabelsMap();
            }

            public Map<String, String> getLabelsMap() {
                return Collections.unmodifiableMap(((Resource) this.instance).getLabelsMap());
            }

            public String getLabelsOrDefault(String key, String defaultValue) {
                key.getClass();
                Map<String, String> map = ((Resource) this.instance).getLabelsMap();
                return map.containsKey(key) ? map.get(key) : defaultValue;
            }

            public String getLabelsOrThrow(String key) {
                key.getClass();
                Map<String, String> map = ((Resource) this.instance).getLabelsMap();
                if (map.containsKey(key)) {
                    return map.get(key);
                }
                throw new IllegalArgumentException();
            }

            public Builder putLabels(String key, String value) {
                key.getClass();
                value.getClass();
                copyOnWrite();
                ((Resource) this.instance).getMutableLabelsMap().put(key, value);
                return this;
            }

            public Builder putAllLabels(Map<String, String> values) {
                copyOnWrite();
                ((Resource) this.instance).getMutableLabelsMap().putAll(values);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C10711.f293xa1df5c61[method.ordinal()]) {
                case 1:
                    return new Resource();
                case 2:
                    return new Builder((C10711) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0001\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u00042", new Object[]{"service_", "name_", "type_", "labels_", LabelsDefaultEntryHolder.defaultEntry});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Resource> parser = PARSER;
                    if (parser == null) {
                        synchronized (Resource.class) {
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
            Resource defaultInstance = new Resource();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Resource.class, defaultInstance);
        }

        public static Resource getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Resource> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public boolean hasOrigin() {
        return this.origin_ != null;
    }

    public Peer getOrigin() {
        Peer peer = this.origin_;
        return peer == null ? Peer.getDefaultInstance() : peer;
    }

    /* access modifiers changed from: private */
    public void setOrigin(Peer value) {
        value.getClass();
        this.origin_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeOrigin(Peer value) {
        value.getClass();
        Peer peer = this.origin_;
        if (peer == null || peer == Peer.getDefaultInstance()) {
            this.origin_ = value;
        } else {
            this.origin_ = (Peer) ((Peer.Builder) Peer.newBuilder(this.origin_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearOrigin() {
        this.origin_ = null;
    }

    public boolean hasSource() {
        return this.source_ != null;
    }

    public Peer getSource() {
        Peer peer = this.source_;
        return peer == null ? Peer.getDefaultInstance() : peer;
    }

    /* access modifiers changed from: private */
    public void setSource(Peer value) {
        value.getClass();
        this.source_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeSource(Peer value) {
        value.getClass();
        Peer peer = this.source_;
        if (peer == null || peer == Peer.getDefaultInstance()) {
            this.source_ = value;
        } else {
            this.source_ = (Peer) ((Peer.Builder) Peer.newBuilder(this.source_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearSource() {
        this.source_ = null;
    }

    public boolean hasDestination() {
        return this.destination_ != null;
    }

    public Peer getDestination() {
        Peer peer = this.destination_;
        return peer == null ? Peer.getDefaultInstance() : peer;
    }

    /* access modifiers changed from: private */
    public void setDestination(Peer value) {
        value.getClass();
        this.destination_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeDestination(Peer value) {
        value.getClass();
        Peer peer = this.destination_;
        if (peer == null || peer == Peer.getDefaultInstance()) {
            this.destination_ = value;
        } else {
            this.destination_ = (Peer) ((Peer.Builder) Peer.newBuilder(this.destination_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearDestination() {
        this.destination_ = null;
    }

    public boolean hasRequest() {
        return this.request_ != null;
    }

    public Request getRequest() {
        Request request = this.request_;
        return request == null ? Request.getDefaultInstance() : request;
    }

    /* access modifiers changed from: private */
    public void setRequest(Request value) {
        value.getClass();
        this.request_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeRequest(Request value) {
        value.getClass();
        Request request = this.request_;
        if (request == null || request == Request.getDefaultInstance()) {
            this.request_ = value;
        } else {
            this.request_ = (Request) ((Request.Builder) Request.newBuilder(this.request_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearRequest() {
        this.request_ = null;
    }

    public boolean hasResponse() {
        return this.response_ != null;
    }

    public Response getResponse() {
        Response response = this.response_;
        return response == null ? Response.getDefaultInstance() : response;
    }

    /* access modifiers changed from: private */
    public void setResponse(Response value) {
        value.getClass();
        this.response_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeResponse(Response value) {
        value.getClass();
        Response response = this.response_;
        if (response == null || response == Response.getDefaultInstance()) {
            this.response_ = value;
        } else {
            this.response_ = (Response) ((Response.Builder) Response.newBuilder(this.response_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearResponse() {
        this.response_ = null;
    }

    public boolean hasResource() {
        return this.resource_ != null;
    }

    public Resource getResource() {
        Resource resource = this.resource_;
        return resource == null ? Resource.getDefaultInstance() : resource;
    }

    /* access modifiers changed from: private */
    public void setResource(Resource value) {
        value.getClass();
        this.resource_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeResource(Resource value) {
        value.getClass();
        Resource resource = this.resource_;
        if (resource == null || resource == Resource.getDefaultInstance()) {
            this.resource_ = value;
        } else {
            this.resource_ = (Resource) ((Resource.Builder) Resource.newBuilder(this.resource_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearResource() {
        this.resource_ = null;
    }

    public boolean hasApi() {
        return this.api_ != null;
    }

    public Api getApi() {
        Api api = this.api_;
        return api == null ? Api.getDefaultInstance() : api;
    }

    /* access modifiers changed from: private */
    public void setApi(Api value) {
        value.getClass();
        this.api_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeApi(Api value) {
        value.getClass();
        Api api = this.api_;
        if (api == null || api == Api.getDefaultInstance()) {
            this.api_ = value;
        } else {
            this.api_ = (Api) ((Api.Builder) Api.newBuilder(this.api_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearApi() {
        this.api_ = null;
    }

    public static AttributeContext parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AttributeContext parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AttributeContext parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AttributeContext parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AttributeContext parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AttributeContext parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AttributeContext parseFrom(InputStream input) throws IOException {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AttributeContext parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AttributeContext parseDelimitedFrom(InputStream input) throws IOException {
        return (AttributeContext) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AttributeContext parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AttributeContext) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AttributeContext parseFrom(CodedInputStream input) throws IOException {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AttributeContext parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AttributeContext prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AttributeContext, Builder> implements AttributeContextOrBuilder {
        /* synthetic */ Builder(C10711 x0) {
            this();
        }

        private Builder() {
            super(AttributeContext.DEFAULT_INSTANCE);
        }

        public boolean hasOrigin() {
            return ((AttributeContext) this.instance).hasOrigin();
        }

        public Peer getOrigin() {
            return ((AttributeContext) this.instance).getOrigin();
        }

        public Builder setOrigin(Peer value) {
            copyOnWrite();
            ((AttributeContext) this.instance).setOrigin(value);
            return this;
        }

        public Builder setOrigin(Peer.Builder builderForValue) {
            copyOnWrite();
            ((AttributeContext) this.instance).setOrigin((Peer) builderForValue.build());
            return this;
        }

        public Builder mergeOrigin(Peer value) {
            copyOnWrite();
            ((AttributeContext) this.instance).mergeOrigin(value);
            return this;
        }

        public Builder clearOrigin() {
            copyOnWrite();
            ((AttributeContext) this.instance).clearOrigin();
            return this;
        }

        public boolean hasSource() {
            return ((AttributeContext) this.instance).hasSource();
        }

        public Peer getSource() {
            return ((AttributeContext) this.instance).getSource();
        }

        public Builder setSource(Peer value) {
            copyOnWrite();
            ((AttributeContext) this.instance).setSource(value);
            return this;
        }

        public Builder setSource(Peer.Builder builderForValue) {
            copyOnWrite();
            ((AttributeContext) this.instance).setSource((Peer) builderForValue.build());
            return this;
        }

        public Builder mergeSource(Peer value) {
            copyOnWrite();
            ((AttributeContext) this.instance).mergeSource(value);
            return this;
        }

        public Builder clearSource() {
            copyOnWrite();
            ((AttributeContext) this.instance).clearSource();
            return this;
        }

        public boolean hasDestination() {
            return ((AttributeContext) this.instance).hasDestination();
        }

        public Peer getDestination() {
            return ((AttributeContext) this.instance).getDestination();
        }

        public Builder setDestination(Peer value) {
            copyOnWrite();
            ((AttributeContext) this.instance).setDestination(value);
            return this;
        }

        public Builder setDestination(Peer.Builder builderForValue) {
            copyOnWrite();
            ((AttributeContext) this.instance).setDestination((Peer) builderForValue.build());
            return this;
        }

        public Builder mergeDestination(Peer value) {
            copyOnWrite();
            ((AttributeContext) this.instance).mergeDestination(value);
            return this;
        }

        public Builder clearDestination() {
            copyOnWrite();
            ((AttributeContext) this.instance).clearDestination();
            return this;
        }

        public boolean hasRequest() {
            return ((AttributeContext) this.instance).hasRequest();
        }

        public Request getRequest() {
            return ((AttributeContext) this.instance).getRequest();
        }

        public Builder setRequest(Request value) {
            copyOnWrite();
            ((AttributeContext) this.instance).setRequest(value);
            return this;
        }

        public Builder setRequest(Request.Builder builderForValue) {
            copyOnWrite();
            ((AttributeContext) this.instance).setRequest((Request) builderForValue.build());
            return this;
        }

        public Builder mergeRequest(Request value) {
            copyOnWrite();
            ((AttributeContext) this.instance).mergeRequest(value);
            return this;
        }

        public Builder clearRequest() {
            copyOnWrite();
            ((AttributeContext) this.instance).clearRequest();
            return this;
        }

        public boolean hasResponse() {
            return ((AttributeContext) this.instance).hasResponse();
        }

        public Response getResponse() {
            return ((AttributeContext) this.instance).getResponse();
        }

        public Builder setResponse(Response value) {
            copyOnWrite();
            ((AttributeContext) this.instance).setResponse(value);
            return this;
        }

        public Builder setResponse(Response.Builder builderForValue) {
            copyOnWrite();
            ((AttributeContext) this.instance).setResponse((Response) builderForValue.build());
            return this;
        }

        public Builder mergeResponse(Response value) {
            copyOnWrite();
            ((AttributeContext) this.instance).mergeResponse(value);
            return this;
        }

        public Builder clearResponse() {
            copyOnWrite();
            ((AttributeContext) this.instance).clearResponse();
            return this;
        }

        public boolean hasResource() {
            return ((AttributeContext) this.instance).hasResource();
        }

        public Resource getResource() {
            return ((AttributeContext) this.instance).getResource();
        }

        public Builder setResource(Resource value) {
            copyOnWrite();
            ((AttributeContext) this.instance).setResource(value);
            return this;
        }

        public Builder setResource(Resource.Builder builderForValue) {
            copyOnWrite();
            ((AttributeContext) this.instance).setResource((Resource) builderForValue.build());
            return this;
        }

        public Builder mergeResource(Resource value) {
            copyOnWrite();
            ((AttributeContext) this.instance).mergeResource(value);
            return this;
        }

        public Builder clearResource() {
            copyOnWrite();
            ((AttributeContext) this.instance).clearResource();
            return this;
        }

        public boolean hasApi() {
            return ((AttributeContext) this.instance).hasApi();
        }

        public Api getApi() {
            return ((AttributeContext) this.instance).getApi();
        }

        public Builder setApi(Api value) {
            copyOnWrite();
            ((AttributeContext) this.instance).setApi(value);
            return this;
        }

        public Builder setApi(Api.Builder builderForValue) {
            copyOnWrite();
            ((AttributeContext) this.instance).setApi((Api) builderForValue.build());
            return this;
        }

        public Builder mergeApi(Api value) {
            copyOnWrite();
            ((AttributeContext) this.instance).mergeApi(value);
            return this;
        }

        public Builder clearApi() {
            copyOnWrite();
            ((AttributeContext) this.instance).clearApi();
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C10711.f293xa1df5c61[method.ordinal()]) {
            case 1:
                return new AttributeContext();
            case 2:
                return new Builder((C10711) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0000\u0000\u0001\t\u0002\t\u0003\t\u0004\t\u0005\t\u0006\t\u0007\t", new Object[]{"source_", "destination_", "request_", "response_", "resource_", "api_", "origin_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<AttributeContext> parser = PARSER;
                if (parser == null) {
                    synchronized (AttributeContext.class) {
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
        AttributeContext defaultInstance = new AttributeContext();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(AttributeContext.class, defaultInstance);
    }

    public static AttributeContext getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AttributeContext> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
