package com.google.api;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class HttpBody extends GeneratedMessageLite<HttpBody, Builder> implements HttpBodyOrBuilder {
    public static final int CONTENT_TYPE_FIELD_NUMBER = 1;
    public static final int DATA_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final HttpBody DEFAULT_INSTANCE;
    public static final int EXTENSIONS_FIELD_NUMBER = 3;
    private static volatile Parser<HttpBody> PARSER;
    private String contentType_ = "";
    private ByteString data_ = ByteString.EMPTY;
    private Internal.ProtobufList<Any> extensions_ = emptyProtobufList();

    private HttpBody() {
    }

    public String getContentType() {
        return this.contentType_;
    }

    public ByteString getContentTypeBytes() {
        return ByteString.copyFromUtf8(this.contentType_);
    }

    /* access modifiers changed from: private */
    public void setContentType(String value) {
        value.getClass();
        this.contentType_ = value;
    }

    /* access modifiers changed from: private */
    public void clearContentType() {
        this.contentType_ = getDefaultInstance().getContentType();
    }

    /* access modifiers changed from: private */
    public void setContentTypeBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.contentType_ = value.toStringUtf8();
    }

    public ByteString getData() {
        return this.data_;
    }

    /* access modifiers changed from: private */
    public void setData(ByteString value) {
        value.getClass();
        this.data_ = value;
    }

    /* access modifiers changed from: private */
    public void clearData() {
        this.data_ = getDefaultInstance().getData();
    }

    public List<Any> getExtensionsList() {
        return this.extensions_;
    }

    public List<? extends AnyOrBuilder> getExtensionsOrBuilderList() {
        return this.extensions_;
    }

    public int getExtensionsCount() {
        return this.extensions_.size();
    }

    public Any getExtensions(int index) {
        return (Any) this.extensions_.get(index);
    }

    public AnyOrBuilder getExtensionsOrBuilder(int index) {
        return (AnyOrBuilder) this.extensions_.get(index);
    }

    private void ensureExtensionsIsMutable() {
        Internal.ProtobufList<Any> tmp = this.extensions_;
        if (!tmp.isModifiable()) {
            this.extensions_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setExtensions(int index, Any value) {
        value.getClass();
        ensureExtensionsIsMutable();
        this.extensions_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addExtensions(Any value) {
        value.getClass();
        ensureExtensionsIsMutable();
        this.extensions_.add(value);
    }

    /* access modifiers changed from: private */
    public void addExtensions(int index, Any value) {
        value.getClass();
        ensureExtensionsIsMutable();
        this.extensions_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllExtensions(Iterable<? extends Any> values) {
        ensureExtensionsIsMutable();
        AbstractMessageLite.addAll(values, this.extensions_);
    }

    /* access modifiers changed from: private */
    public void clearExtensions() {
        this.extensions_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeExtensions(int index) {
        ensureExtensionsIsMutable();
        this.extensions_.remove(index);
    }

    public static HttpBody parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static HttpBody parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static HttpBody parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static HttpBody parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static HttpBody parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static HttpBody parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static HttpBody parseFrom(InputStream input) throws IOException {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static HttpBody parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static HttpBody parseDelimitedFrom(InputStream input) throws IOException {
        return (HttpBody) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static HttpBody parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (HttpBody) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static HttpBody parseFrom(CodedInputStream input) throws IOException {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static HttpBody parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(HttpBody prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<HttpBody, Builder> implements HttpBodyOrBuilder {
        /* synthetic */ Builder(C00211 x0) {
            this();
        }

        private Builder() {
            super(HttpBody.DEFAULT_INSTANCE);
        }

        public String getContentType() {
            return ((HttpBody) this.instance).getContentType();
        }

        public ByteString getContentTypeBytes() {
            return ((HttpBody) this.instance).getContentTypeBytes();
        }

        public Builder setContentType(String value) {
            copyOnWrite();
            ((HttpBody) this.instance).setContentType(value);
            return this;
        }

        public Builder clearContentType() {
            copyOnWrite();
            ((HttpBody) this.instance).clearContentType();
            return this;
        }

        public Builder setContentTypeBytes(ByteString value) {
            copyOnWrite();
            ((HttpBody) this.instance).setContentTypeBytes(value);
            return this;
        }

        public ByteString getData() {
            return ((HttpBody) this.instance).getData();
        }

        public Builder setData(ByteString value) {
            copyOnWrite();
            ((HttpBody) this.instance).setData(value);
            return this;
        }

        public Builder clearData() {
            copyOnWrite();
            ((HttpBody) this.instance).clearData();
            return this;
        }

        public List<Any> getExtensionsList() {
            return Collections.unmodifiableList(((HttpBody) this.instance).getExtensionsList());
        }

        public int getExtensionsCount() {
            return ((HttpBody) this.instance).getExtensionsCount();
        }

        public Any getExtensions(int index) {
            return ((HttpBody) this.instance).getExtensions(index);
        }

        public Builder setExtensions(int index, Any value) {
            copyOnWrite();
            ((HttpBody) this.instance).setExtensions(index, value);
            return this;
        }

        public Builder setExtensions(int index, Any.Builder builderForValue) {
            copyOnWrite();
            ((HttpBody) this.instance).setExtensions(index, (Any) builderForValue.build());
            return this;
        }

        public Builder addExtensions(Any value) {
            copyOnWrite();
            ((HttpBody) this.instance).addExtensions(value);
            return this;
        }

        public Builder addExtensions(int index, Any value) {
            copyOnWrite();
            ((HttpBody) this.instance).addExtensions(index, value);
            return this;
        }

        public Builder addExtensions(Any.Builder builderForValue) {
            copyOnWrite();
            ((HttpBody) this.instance).addExtensions((Any) builderForValue.build());
            return this;
        }

        public Builder addExtensions(int index, Any.Builder builderForValue) {
            copyOnWrite();
            ((HttpBody) this.instance).addExtensions(index, (Any) builderForValue.build());
            return this;
        }

        public Builder addAllExtensions(Iterable<? extends Any> values) {
            copyOnWrite();
            ((HttpBody) this.instance).addAllExtensions(values);
            return this;
        }

        public Builder clearExtensions() {
            copyOnWrite();
            ((HttpBody) this.instance).clearExtensions();
            return this;
        }

        public Builder removeExtensions(int index) {
            copyOnWrite();
            ((HttpBody) this.instance).removeExtensions(index);
            return this;
        }
    }

    /* renamed from: com.google.api.HttpBody$1 */
    static /* synthetic */ class C00211 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f18xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f18xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f18xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f18xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f18xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f18xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f18xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f18xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00211.f18xa1df5c61[method.ordinal()]) {
            case 1:
                return new HttpBody();
            case 2:
                return new Builder((C00211) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001Ȉ\u0002\n\u0003\u001b", new Object[]{"contentType_", "data_", "extensions_", Any.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<HttpBody> parser = PARSER;
                if (parser == null) {
                    synchronized (HttpBody.class) {
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
        HttpBody defaultInstance = new HttpBody();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(HttpBody.class, defaultInstance);
    }

    public static HttpBody getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HttpBody> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
