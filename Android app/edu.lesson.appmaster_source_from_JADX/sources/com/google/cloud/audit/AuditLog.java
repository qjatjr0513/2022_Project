package com.google.cloud.audit;

import com.google.cloud.audit.AuthenticationInfo;
import com.google.cloud.audit.AuthorizationInfo;
import com.google.cloud.audit.RequestMetadata;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Struct;
import com.google.rpc.Status;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class AuditLog extends GeneratedMessageLite<AuditLog, Builder> implements AuditLogOrBuilder {
    public static final int AUTHENTICATION_INFO_FIELD_NUMBER = 3;
    public static final int AUTHORIZATION_INFO_FIELD_NUMBER = 9;
    /* access modifiers changed from: private */
    public static final AuditLog DEFAULT_INSTANCE;
    public static final int METHOD_NAME_FIELD_NUMBER = 8;
    public static final int NUM_RESPONSE_ITEMS_FIELD_NUMBER = 12;
    private static volatile Parser<AuditLog> PARSER = null;
    public static final int REQUEST_FIELD_NUMBER = 16;
    public static final int REQUEST_METADATA_FIELD_NUMBER = 4;
    public static final int RESOURCE_NAME_FIELD_NUMBER = 11;
    public static final int RESPONSE_FIELD_NUMBER = 17;
    public static final int SERVICE_DATA_FIELD_NUMBER = 15;
    public static final int SERVICE_NAME_FIELD_NUMBER = 7;
    public static final int STATUS_FIELD_NUMBER = 2;
    private AuthenticationInfo authenticationInfo_;
    private Internal.ProtobufList<AuthorizationInfo> authorizationInfo_ = emptyProtobufList();
    private String methodName_ = "";
    private long numResponseItems_;
    private RequestMetadata requestMetadata_;
    private Struct request_;
    private String resourceName_ = "";
    private Struct response_;
    private Any serviceData_;
    private String serviceName_ = "";
    private Status status_;

    private AuditLog() {
    }

    public String getServiceName() {
        return this.serviceName_;
    }

    public ByteString getServiceNameBytes() {
        return ByteString.copyFromUtf8(this.serviceName_);
    }

    /* access modifiers changed from: private */
    public void setServiceName(String value) {
        value.getClass();
        this.serviceName_ = value;
    }

    /* access modifiers changed from: private */
    public void clearServiceName() {
        this.serviceName_ = getDefaultInstance().getServiceName();
    }

    /* access modifiers changed from: private */
    public void setServiceNameBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.serviceName_ = value.toStringUtf8();
    }

    public String getMethodName() {
        return this.methodName_;
    }

    public ByteString getMethodNameBytes() {
        return ByteString.copyFromUtf8(this.methodName_);
    }

    /* access modifiers changed from: private */
    public void setMethodName(String value) {
        value.getClass();
        this.methodName_ = value;
    }

    /* access modifiers changed from: private */
    public void clearMethodName() {
        this.methodName_ = getDefaultInstance().getMethodName();
    }

    /* access modifiers changed from: private */
    public void setMethodNameBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.methodName_ = value.toStringUtf8();
    }

    public String getResourceName() {
        return this.resourceName_;
    }

    public ByteString getResourceNameBytes() {
        return ByteString.copyFromUtf8(this.resourceName_);
    }

    /* access modifiers changed from: private */
    public void setResourceName(String value) {
        value.getClass();
        this.resourceName_ = value;
    }

    /* access modifiers changed from: private */
    public void clearResourceName() {
        this.resourceName_ = getDefaultInstance().getResourceName();
    }

    /* access modifiers changed from: private */
    public void setResourceNameBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.resourceName_ = value.toStringUtf8();
    }

    public long getNumResponseItems() {
        return this.numResponseItems_;
    }

    /* access modifiers changed from: private */
    public void setNumResponseItems(long value) {
        this.numResponseItems_ = value;
    }

    /* access modifiers changed from: private */
    public void clearNumResponseItems() {
        this.numResponseItems_ = 0;
    }

    public boolean hasStatus() {
        return this.status_ != null;
    }

    public Status getStatus() {
        Status status = this.status_;
        return status == null ? Status.getDefaultInstance() : status;
    }

    /* access modifiers changed from: private */
    public void setStatus(Status value) {
        value.getClass();
        this.status_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeStatus(Status value) {
        value.getClass();
        Status status = this.status_;
        if (status == null || status == Status.getDefaultInstance()) {
            this.status_ = value;
        } else {
            this.status_ = (Status) ((Status.Builder) Status.newBuilder(this.status_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearStatus() {
        this.status_ = null;
    }

    public boolean hasAuthenticationInfo() {
        return this.authenticationInfo_ != null;
    }

    public AuthenticationInfo getAuthenticationInfo() {
        AuthenticationInfo authenticationInfo = this.authenticationInfo_;
        return authenticationInfo == null ? AuthenticationInfo.getDefaultInstance() : authenticationInfo;
    }

    /* access modifiers changed from: private */
    public void setAuthenticationInfo(AuthenticationInfo value) {
        value.getClass();
        this.authenticationInfo_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeAuthenticationInfo(AuthenticationInfo value) {
        value.getClass();
        AuthenticationInfo authenticationInfo = this.authenticationInfo_;
        if (authenticationInfo == null || authenticationInfo == AuthenticationInfo.getDefaultInstance()) {
            this.authenticationInfo_ = value;
        } else {
            this.authenticationInfo_ = (AuthenticationInfo) ((AuthenticationInfo.Builder) AuthenticationInfo.newBuilder(this.authenticationInfo_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearAuthenticationInfo() {
        this.authenticationInfo_ = null;
    }

    public List<AuthorizationInfo> getAuthorizationInfoList() {
        return this.authorizationInfo_;
    }

    public List<? extends AuthorizationInfoOrBuilder> getAuthorizationInfoOrBuilderList() {
        return this.authorizationInfo_;
    }

    public int getAuthorizationInfoCount() {
        return this.authorizationInfo_.size();
    }

    public AuthorizationInfo getAuthorizationInfo(int index) {
        return (AuthorizationInfo) this.authorizationInfo_.get(index);
    }

    public AuthorizationInfoOrBuilder getAuthorizationInfoOrBuilder(int index) {
        return (AuthorizationInfoOrBuilder) this.authorizationInfo_.get(index);
    }

    private void ensureAuthorizationInfoIsMutable() {
        Internal.ProtobufList<AuthorizationInfo> tmp = this.authorizationInfo_;
        if (!tmp.isModifiable()) {
            this.authorizationInfo_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setAuthorizationInfo(int index, AuthorizationInfo value) {
        value.getClass();
        ensureAuthorizationInfoIsMutable();
        this.authorizationInfo_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addAuthorizationInfo(AuthorizationInfo value) {
        value.getClass();
        ensureAuthorizationInfoIsMutable();
        this.authorizationInfo_.add(value);
    }

    /* access modifiers changed from: private */
    public void addAuthorizationInfo(int index, AuthorizationInfo value) {
        value.getClass();
        ensureAuthorizationInfoIsMutable();
        this.authorizationInfo_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllAuthorizationInfo(Iterable<? extends AuthorizationInfo> values) {
        ensureAuthorizationInfoIsMutable();
        AbstractMessageLite.addAll(values, this.authorizationInfo_);
    }

    /* access modifiers changed from: private */
    public void clearAuthorizationInfo() {
        this.authorizationInfo_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeAuthorizationInfo(int index) {
        ensureAuthorizationInfoIsMutable();
        this.authorizationInfo_.remove(index);
    }

    public boolean hasRequestMetadata() {
        return this.requestMetadata_ != null;
    }

    public RequestMetadata getRequestMetadata() {
        RequestMetadata requestMetadata = this.requestMetadata_;
        return requestMetadata == null ? RequestMetadata.getDefaultInstance() : requestMetadata;
    }

    /* access modifiers changed from: private */
    public void setRequestMetadata(RequestMetadata value) {
        value.getClass();
        this.requestMetadata_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeRequestMetadata(RequestMetadata value) {
        value.getClass();
        RequestMetadata requestMetadata = this.requestMetadata_;
        if (requestMetadata == null || requestMetadata == RequestMetadata.getDefaultInstance()) {
            this.requestMetadata_ = value;
        } else {
            this.requestMetadata_ = (RequestMetadata) ((RequestMetadata.Builder) RequestMetadata.newBuilder(this.requestMetadata_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearRequestMetadata() {
        this.requestMetadata_ = null;
    }

    public boolean hasRequest() {
        return this.request_ != null;
    }

    public Struct getRequest() {
        Struct struct = this.request_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    /* access modifiers changed from: private */
    public void setRequest(Struct value) {
        value.getClass();
        this.request_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeRequest(Struct value) {
        value.getClass();
        Struct struct = this.request_;
        if (struct == null || struct == Struct.getDefaultInstance()) {
            this.request_ = value;
        } else {
            this.request_ = (Struct) ((Struct.Builder) Struct.newBuilder(this.request_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearRequest() {
        this.request_ = null;
    }

    public boolean hasResponse() {
        return this.response_ != null;
    }

    public Struct getResponse() {
        Struct struct = this.response_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    /* access modifiers changed from: private */
    public void setResponse(Struct value) {
        value.getClass();
        this.response_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeResponse(Struct value) {
        value.getClass();
        Struct struct = this.response_;
        if (struct == null || struct == Struct.getDefaultInstance()) {
            this.response_ = value;
        } else {
            this.response_ = (Struct) ((Struct.Builder) Struct.newBuilder(this.response_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearResponse() {
        this.response_ = null;
    }

    public boolean hasServiceData() {
        return this.serviceData_ != null;
    }

    public Any getServiceData() {
        Any any = this.serviceData_;
        return any == null ? Any.getDefaultInstance() : any;
    }

    /* access modifiers changed from: private */
    public void setServiceData(Any value) {
        value.getClass();
        this.serviceData_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeServiceData(Any value) {
        value.getClass();
        Any any = this.serviceData_;
        if (any == null || any == Any.getDefaultInstance()) {
            this.serviceData_ = value;
        } else {
            this.serviceData_ = (Any) ((Any.Builder) Any.newBuilder(this.serviceData_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearServiceData() {
        this.serviceData_ = null;
    }

    public static AuditLog parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AuditLog parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AuditLog parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AuditLog parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AuditLog parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AuditLog parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AuditLog parseFrom(InputStream input) throws IOException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AuditLog parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AuditLog parseDelimitedFrom(InputStream input) throws IOException {
        return (AuditLog) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AuditLog parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AuditLog) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AuditLog parseFrom(CodedInputStream input) throws IOException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AuditLog parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AuditLog prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AuditLog, Builder> implements AuditLogOrBuilder {
        /* synthetic */ Builder(C00561 x0) {
            this();
        }

        private Builder() {
            super(AuditLog.DEFAULT_INSTANCE);
        }

        public String getServiceName() {
            return ((AuditLog) this.instance).getServiceName();
        }

        public ByteString getServiceNameBytes() {
            return ((AuditLog) this.instance).getServiceNameBytes();
        }

        public Builder setServiceName(String value) {
            copyOnWrite();
            ((AuditLog) this.instance).setServiceName(value);
            return this;
        }

        public Builder clearServiceName() {
            copyOnWrite();
            ((AuditLog) this.instance).clearServiceName();
            return this;
        }

        public Builder setServiceNameBytes(ByteString value) {
            copyOnWrite();
            ((AuditLog) this.instance).setServiceNameBytes(value);
            return this;
        }

        public String getMethodName() {
            return ((AuditLog) this.instance).getMethodName();
        }

        public ByteString getMethodNameBytes() {
            return ((AuditLog) this.instance).getMethodNameBytes();
        }

        public Builder setMethodName(String value) {
            copyOnWrite();
            ((AuditLog) this.instance).setMethodName(value);
            return this;
        }

        public Builder clearMethodName() {
            copyOnWrite();
            ((AuditLog) this.instance).clearMethodName();
            return this;
        }

        public Builder setMethodNameBytes(ByteString value) {
            copyOnWrite();
            ((AuditLog) this.instance).setMethodNameBytes(value);
            return this;
        }

        public String getResourceName() {
            return ((AuditLog) this.instance).getResourceName();
        }

        public ByteString getResourceNameBytes() {
            return ((AuditLog) this.instance).getResourceNameBytes();
        }

        public Builder setResourceName(String value) {
            copyOnWrite();
            ((AuditLog) this.instance).setResourceName(value);
            return this;
        }

        public Builder clearResourceName() {
            copyOnWrite();
            ((AuditLog) this.instance).clearResourceName();
            return this;
        }

        public Builder setResourceNameBytes(ByteString value) {
            copyOnWrite();
            ((AuditLog) this.instance).setResourceNameBytes(value);
            return this;
        }

        public long getNumResponseItems() {
            return ((AuditLog) this.instance).getNumResponseItems();
        }

        public Builder setNumResponseItems(long value) {
            copyOnWrite();
            ((AuditLog) this.instance).setNumResponseItems(value);
            return this;
        }

        public Builder clearNumResponseItems() {
            copyOnWrite();
            ((AuditLog) this.instance).clearNumResponseItems();
            return this;
        }

        public boolean hasStatus() {
            return ((AuditLog) this.instance).hasStatus();
        }

        public Status getStatus() {
            return ((AuditLog) this.instance).getStatus();
        }

        public Builder setStatus(Status value) {
            copyOnWrite();
            ((AuditLog) this.instance).setStatus(value);
            return this;
        }

        public Builder setStatus(Status.Builder builderForValue) {
            copyOnWrite();
            ((AuditLog) this.instance).setStatus((Status) builderForValue.build());
            return this;
        }

        public Builder mergeStatus(Status value) {
            copyOnWrite();
            ((AuditLog) this.instance).mergeStatus(value);
            return this;
        }

        public Builder clearStatus() {
            copyOnWrite();
            ((AuditLog) this.instance).clearStatus();
            return this;
        }

        public boolean hasAuthenticationInfo() {
            return ((AuditLog) this.instance).hasAuthenticationInfo();
        }

        public AuthenticationInfo getAuthenticationInfo() {
            return ((AuditLog) this.instance).getAuthenticationInfo();
        }

        public Builder setAuthenticationInfo(AuthenticationInfo value) {
            copyOnWrite();
            ((AuditLog) this.instance).setAuthenticationInfo(value);
            return this;
        }

        public Builder setAuthenticationInfo(AuthenticationInfo.Builder builderForValue) {
            copyOnWrite();
            ((AuditLog) this.instance).setAuthenticationInfo((AuthenticationInfo) builderForValue.build());
            return this;
        }

        public Builder mergeAuthenticationInfo(AuthenticationInfo value) {
            copyOnWrite();
            ((AuditLog) this.instance).mergeAuthenticationInfo(value);
            return this;
        }

        public Builder clearAuthenticationInfo() {
            copyOnWrite();
            ((AuditLog) this.instance).clearAuthenticationInfo();
            return this;
        }

        public List<AuthorizationInfo> getAuthorizationInfoList() {
            return Collections.unmodifiableList(((AuditLog) this.instance).getAuthorizationInfoList());
        }

        public int getAuthorizationInfoCount() {
            return ((AuditLog) this.instance).getAuthorizationInfoCount();
        }

        public AuthorizationInfo getAuthorizationInfo(int index) {
            return ((AuditLog) this.instance).getAuthorizationInfo(index);
        }

        public Builder setAuthorizationInfo(int index, AuthorizationInfo value) {
            copyOnWrite();
            ((AuditLog) this.instance).setAuthorizationInfo(index, value);
            return this;
        }

        public Builder setAuthorizationInfo(int index, AuthorizationInfo.Builder builderForValue) {
            copyOnWrite();
            ((AuditLog) this.instance).setAuthorizationInfo(index, (AuthorizationInfo) builderForValue.build());
            return this;
        }

        public Builder addAuthorizationInfo(AuthorizationInfo value) {
            copyOnWrite();
            ((AuditLog) this.instance).addAuthorizationInfo(value);
            return this;
        }

        public Builder addAuthorizationInfo(int index, AuthorizationInfo value) {
            copyOnWrite();
            ((AuditLog) this.instance).addAuthorizationInfo(index, value);
            return this;
        }

        public Builder addAuthorizationInfo(AuthorizationInfo.Builder builderForValue) {
            copyOnWrite();
            ((AuditLog) this.instance).addAuthorizationInfo((AuthorizationInfo) builderForValue.build());
            return this;
        }

        public Builder addAuthorizationInfo(int index, AuthorizationInfo.Builder builderForValue) {
            copyOnWrite();
            ((AuditLog) this.instance).addAuthorizationInfo(index, (AuthorizationInfo) builderForValue.build());
            return this;
        }

        public Builder addAllAuthorizationInfo(Iterable<? extends AuthorizationInfo> values) {
            copyOnWrite();
            ((AuditLog) this.instance).addAllAuthorizationInfo(values);
            return this;
        }

        public Builder clearAuthorizationInfo() {
            copyOnWrite();
            ((AuditLog) this.instance).clearAuthorizationInfo();
            return this;
        }

        public Builder removeAuthorizationInfo(int index) {
            copyOnWrite();
            ((AuditLog) this.instance).removeAuthorizationInfo(index);
            return this;
        }

        public boolean hasRequestMetadata() {
            return ((AuditLog) this.instance).hasRequestMetadata();
        }

        public RequestMetadata getRequestMetadata() {
            return ((AuditLog) this.instance).getRequestMetadata();
        }

        public Builder setRequestMetadata(RequestMetadata value) {
            copyOnWrite();
            ((AuditLog) this.instance).setRequestMetadata(value);
            return this;
        }

        public Builder setRequestMetadata(RequestMetadata.Builder builderForValue) {
            copyOnWrite();
            ((AuditLog) this.instance).setRequestMetadata((RequestMetadata) builderForValue.build());
            return this;
        }

        public Builder mergeRequestMetadata(RequestMetadata value) {
            copyOnWrite();
            ((AuditLog) this.instance).mergeRequestMetadata(value);
            return this;
        }

        public Builder clearRequestMetadata() {
            copyOnWrite();
            ((AuditLog) this.instance).clearRequestMetadata();
            return this;
        }

        public boolean hasRequest() {
            return ((AuditLog) this.instance).hasRequest();
        }

        public Struct getRequest() {
            return ((AuditLog) this.instance).getRequest();
        }

        public Builder setRequest(Struct value) {
            copyOnWrite();
            ((AuditLog) this.instance).setRequest(value);
            return this;
        }

        public Builder setRequest(Struct.Builder builderForValue) {
            copyOnWrite();
            ((AuditLog) this.instance).setRequest((Struct) builderForValue.build());
            return this;
        }

        public Builder mergeRequest(Struct value) {
            copyOnWrite();
            ((AuditLog) this.instance).mergeRequest(value);
            return this;
        }

        public Builder clearRequest() {
            copyOnWrite();
            ((AuditLog) this.instance).clearRequest();
            return this;
        }

        public boolean hasResponse() {
            return ((AuditLog) this.instance).hasResponse();
        }

        public Struct getResponse() {
            return ((AuditLog) this.instance).getResponse();
        }

        public Builder setResponse(Struct value) {
            copyOnWrite();
            ((AuditLog) this.instance).setResponse(value);
            return this;
        }

        public Builder setResponse(Struct.Builder builderForValue) {
            copyOnWrite();
            ((AuditLog) this.instance).setResponse((Struct) builderForValue.build());
            return this;
        }

        public Builder mergeResponse(Struct value) {
            copyOnWrite();
            ((AuditLog) this.instance).mergeResponse(value);
            return this;
        }

        public Builder clearResponse() {
            copyOnWrite();
            ((AuditLog) this.instance).clearResponse();
            return this;
        }

        public boolean hasServiceData() {
            return ((AuditLog) this.instance).hasServiceData();
        }

        public Any getServiceData() {
            return ((AuditLog) this.instance).getServiceData();
        }

        public Builder setServiceData(Any value) {
            copyOnWrite();
            ((AuditLog) this.instance).setServiceData(value);
            return this;
        }

        public Builder setServiceData(Any.Builder builderForValue) {
            copyOnWrite();
            ((AuditLog) this.instance).setServiceData((Any) builderForValue.build());
            return this;
        }

        public Builder mergeServiceData(Any value) {
            copyOnWrite();
            ((AuditLog) this.instance).mergeServiceData(value);
            return this;
        }

        public Builder clearServiceData() {
            copyOnWrite();
            ((AuditLog) this.instance).clearServiceData();
            return this;
        }
    }

    /* renamed from: com.google.cloud.audit.AuditLog$1 */
    static /* synthetic */ class C00561 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f48xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f48xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f48xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f48xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f48xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f48xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f48xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f48xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00561.f48xa1df5c61[method.ordinal()]) {
            case 1:
                return new AuditLog();
            case 2:
                return new Builder((C00561) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u000b\u0000\u0000\u0002\u0011\u000b\u0000\u0001\u0000\u0002\t\u0003\t\u0004\t\u0007Ȉ\bȈ\t\u001b\u000bȈ\f\u0002\u000f\t\u0010\t\u0011\t", new Object[]{"status_", "authenticationInfo_", "requestMetadata_", "serviceName_", "methodName_", "authorizationInfo_", AuthorizationInfo.class, "resourceName_", "numResponseItems_", "serviceData_", "request_", "response_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<AuditLog> parser = PARSER;
                if (parser == null) {
                    synchronized (AuditLog.class) {
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
        AuditLog defaultInstance = new AuditLog();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(AuditLog.class, defaultInstance);
    }

    public static AuditLog getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuditLog> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
