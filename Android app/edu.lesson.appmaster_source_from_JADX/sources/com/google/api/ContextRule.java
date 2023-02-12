package com.google.api;

import com.google.protobuf.AbstractMessageLite;
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

public final class ContextRule extends GeneratedMessageLite<ContextRule, Builder> implements ContextRuleOrBuilder {
    public static final int ALLOWED_REQUEST_EXTENSIONS_FIELD_NUMBER = 4;
    public static final int ALLOWED_RESPONSE_EXTENSIONS_FIELD_NUMBER = 5;
    /* access modifiers changed from: private */
    public static final ContextRule DEFAULT_INSTANCE;
    private static volatile Parser<ContextRule> PARSER = null;
    public static final int PROVIDED_FIELD_NUMBER = 3;
    public static final int REQUESTED_FIELD_NUMBER = 2;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private Internal.ProtobufList<String> allowedRequestExtensions_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList<String> allowedResponseExtensions_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList<String> provided_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList<String> requested_ = GeneratedMessageLite.emptyProtobufList();
    private String selector_ = "";

    private ContextRule() {
    }

    public String getSelector() {
        return this.selector_;
    }

    public ByteString getSelectorBytes() {
        return ByteString.copyFromUtf8(this.selector_);
    }

    /* access modifiers changed from: private */
    public void setSelector(String value) {
        value.getClass();
        this.selector_ = value;
    }

    /* access modifiers changed from: private */
    public void clearSelector() {
        this.selector_ = getDefaultInstance().getSelector();
    }

    /* access modifiers changed from: private */
    public void setSelectorBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.selector_ = value.toStringUtf8();
    }

    public List<String> getRequestedList() {
        return this.requested_;
    }

    public int getRequestedCount() {
        return this.requested_.size();
    }

    public String getRequested(int index) {
        return (String) this.requested_.get(index);
    }

    public ByteString getRequestedBytes(int index) {
        return ByteString.copyFromUtf8((String) this.requested_.get(index));
    }

    private void ensureRequestedIsMutable() {
        Internal.ProtobufList<String> tmp = this.requested_;
        if (!tmp.isModifiable()) {
            this.requested_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setRequested(int index, String value) {
        value.getClass();
        ensureRequestedIsMutable();
        this.requested_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addRequested(String value) {
        value.getClass();
        ensureRequestedIsMutable();
        this.requested_.add(value);
    }

    /* access modifiers changed from: private */
    public void addAllRequested(Iterable<String> values) {
        ensureRequestedIsMutable();
        AbstractMessageLite.addAll(values, this.requested_);
    }

    /* access modifiers changed from: private */
    public void clearRequested() {
        this.requested_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void addRequestedBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        ensureRequestedIsMutable();
        this.requested_.add(value.toStringUtf8());
    }

    public List<String> getProvidedList() {
        return this.provided_;
    }

    public int getProvidedCount() {
        return this.provided_.size();
    }

    public String getProvided(int index) {
        return (String) this.provided_.get(index);
    }

    public ByteString getProvidedBytes(int index) {
        return ByteString.copyFromUtf8((String) this.provided_.get(index));
    }

    private void ensureProvidedIsMutable() {
        Internal.ProtobufList<String> tmp = this.provided_;
        if (!tmp.isModifiable()) {
            this.provided_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setProvided(int index, String value) {
        value.getClass();
        ensureProvidedIsMutable();
        this.provided_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addProvided(String value) {
        value.getClass();
        ensureProvidedIsMutable();
        this.provided_.add(value);
    }

    /* access modifiers changed from: private */
    public void addAllProvided(Iterable<String> values) {
        ensureProvidedIsMutable();
        AbstractMessageLite.addAll(values, this.provided_);
    }

    /* access modifiers changed from: private */
    public void clearProvided() {
        this.provided_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void addProvidedBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        ensureProvidedIsMutable();
        this.provided_.add(value.toStringUtf8());
    }

    public List<String> getAllowedRequestExtensionsList() {
        return this.allowedRequestExtensions_;
    }

    public int getAllowedRequestExtensionsCount() {
        return this.allowedRequestExtensions_.size();
    }

    public String getAllowedRequestExtensions(int index) {
        return (String) this.allowedRequestExtensions_.get(index);
    }

    public ByteString getAllowedRequestExtensionsBytes(int index) {
        return ByteString.copyFromUtf8((String) this.allowedRequestExtensions_.get(index));
    }

    private void ensureAllowedRequestExtensionsIsMutable() {
        Internal.ProtobufList<String> tmp = this.allowedRequestExtensions_;
        if (!tmp.isModifiable()) {
            this.allowedRequestExtensions_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setAllowedRequestExtensions(int index, String value) {
        value.getClass();
        ensureAllowedRequestExtensionsIsMutable();
        this.allowedRequestExtensions_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllowedRequestExtensions(String value) {
        value.getClass();
        ensureAllowedRequestExtensionsIsMutable();
        this.allowedRequestExtensions_.add(value);
    }

    /* access modifiers changed from: private */
    public void addAllAllowedRequestExtensions(Iterable<String> values) {
        ensureAllowedRequestExtensionsIsMutable();
        AbstractMessageLite.addAll(values, this.allowedRequestExtensions_);
    }

    /* access modifiers changed from: private */
    public void clearAllowedRequestExtensions() {
        this.allowedRequestExtensions_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void addAllowedRequestExtensionsBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        ensureAllowedRequestExtensionsIsMutable();
        this.allowedRequestExtensions_.add(value.toStringUtf8());
    }

    public List<String> getAllowedResponseExtensionsList() {
        return this.allowedResponseExtensions_;
    }

    public int getAllowedResponseExtensionsCount() {
        return this.allowedResponseExtensions_.size();
    }

    public String getAllowedResponseExtensions(int index) {
        return (String) this.allowedResponseExtensions_.get(index);
    }

    public ByteString getAllowedResponseExtensionsBytes(int index) {
        return ByteString.copyFromUtf8((String) this.allowedResponseExtensions_.get(index));
    }

    private void ensureAllowedResponseExtensionsIsMutable() {
        Internal.ProtobufList<String> tmp = this.allowedResponseExtensions_;
        if (!tmp.isModifiable()) {
            this.allowedResponseExtensions_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setAllowedResponseExtensions(int index, String value) {
        value.getClass();
        ensureAllowedResponseExtensionsIsMutable();
        this.allowedResponseExtensions_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllowedResponseExtensions(String value) {
        value.getClass();
        ensureAllowedResponseExtensionsIsMutable();
        this.allowedResponseExtensions_.add(value);
    }

    /* access modifiers changed from: private */
    public void addAllAllowedResponseExtensions(Iterable<String> values) {
        ensureAllowedResponseExtensionsIsMutable();
        AbstractMessageLite.addAll(values, this.allowedResponseExtensions_);
    }

    /* access modifiers changed from: private */
    public void clearAllowedResponseExtensions() {
        this.allowedResponseExtensions_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void addAllowedResponseExtensionsBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        ensureAllowedResponseExtensionsIsMutable();
        this.allowedResponseExtensions_.add(value.toStringUtf8());
    }

    public static ContextRule parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ContextRule parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ContextRule parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ContextRule parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ContextRule parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ContextRule parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ContextRule parseFrom(InputStream input) throws IOException {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ContextRule parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ContextRule parseDelimitedFrom(InputStream input) throws IOException {
        return (ContextRule) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ContextRule parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ContextRule) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ContextRule parseFrom(CodedInputStream input) throws IOException {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ContextRule parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ContextRule prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ContextRule, Builder> implements ContextRuleOrBuilder {
        /* synthetic */ Builder(C00121 x0) {
            this();
        }

        private Builder() {
            super(ContextRule.DEFAULT_INSTANCE);
        }

        public String getSelector() {
            return ((ContextRule) this.instance).getSelector();
        }

        public ByteString getSelectorBytes() {
            return ((ContextRule) this.instance).getSelectorBytes();
        }

        public Builder setSelector(String value) {
            copyOnWrite();
            ((ContextRule) this.instance).setSelector(value);
            return this;
        }

        public Builder clearSelector() {
            copyOnWrite();
            ((ContextRule) this.instance).clearSelector();
            return this;
        }

        public Builder setSelectorBytes(ByteString value) {
            copyOnWrite();
            ((ContextRule) this.instance).setSelectorBytes(value);
            return this;
        }

        public List<String> getRequestedList() {
            return Collections.unmodifiableList(((ContextRule) this.instance).getRequestedList());
        }

        public int getRequestedCount() {
            return ((ContextRule) this.instance).getRequestedCount();
        }

        public String getRequested(int index) {
            return ((ContextRule) this.instance).getRequested(index);
        }

        public ByteString getRequestedBytes(int index) {
            return ((ContextRule) this.instance).getRequestedBytes(index);
        }

        public Builder setRequested(int index, String value) {
            copyOnWrite();
            ((ContextRule) this.instance).setRequested(index, value);
            return this;
        }

        public Builder addRequested(String value) {
            copyOnWrite();
            ((ContextRule) this.instance).addRequested(value);
            return this;
        }

        public Builder addAllRequested(Iterable<String> values) {
            copyOnWrite();
            ((ContextRule) this.instance).addAllRequested(values);
            return this;
        }

        public Builder clearRequested() {
            copyOnWrite();
            ((ContextRule) this.instance).clearRequested();
            return this;
        }

        public Builder addRequestedBytes(ByteString value) {
            copyOnWrite();
            ((ContextRule) this.instance).addRequestedBytes(value);
            return this;
        }

        public List<String> getProvidedList() {
            return Collections.unmodifiableList(((ContextRule) this.instance).getProvidedList());
        }

        public int getProvidedCount() {
            return ((ContextRule) this.instance).getProvidedCount();
        }

        public String getProvided(int index) {
            return ((ContextRule) this.instance).getProvided(index);
        }

        public ByteString getProvidedBytes(int index) {
            return ((ContextRule) this.instance).getProvidedBytes(index);
        }

        public Builder setProvided(int index, String value) {
            copyOnWrite();
            ((ContextRule) this.instance).setProvided(index, value);
            return this;
        }

        public Builder addProvided(String value) {
            copyOnWrite();
            ((ContextRule) this.instance).addProvided(value);
            return this;
        }

        public Builder addAllProvided(Iterable<String> values) {
            copyOnWrite();
            ((ContextRule) this.instance).addAllProvided(values);
            return this;
        }

        public Builder clearProvided() {
            copyOnWrite();
            ((ContextRule) this.instance).clearProvided();
            return this;
        }

        public Builder addProvidedBytes(ByteString value) {
            copyOnWrite();
            ((ContextRule) this.instance).addProvidedBytes(value);
            return this;
        }

        public List<String> getAllowedRequestExtensionsList() {
            return Collections.unmodifiableList(((ContextRule) this.instance).getAllowedRequestExtensionsList());
        }

        public int getAllowedRequestExtensionsCount() {
            return ((ContextRule) this.instance).getAllowedRequestExtensionsCount();
        }

        public String getAllowedRequestExtensions(int index) {
            return ((ContextRule) this.instance).getAllowedRequestExtensions(index);
        }

        public ByteString getAllowedRequestExtensionsBytes(int index) {
            return ((ContextRule) this.instance).getAllowedRequestExtensionsBytes(index);
        }

        public Builder setAllowedRequestExtensions(int index, String value) {
            copyOnWrite();
            ((ContextRule) this.instance).setAllowedRequestExtensions(index, value);
            return this;
        }

        public Builder addAllowedRequestExtensions(String value) {
            copyOnWrite();
            ((ContextRule) this.instance).addAllowedRequestExtensions(value);
            return this;
        }

        public Builder addAllAllowedRequestExtensions(Iterable<String> values) {
            copyOnWrite();
            ((ContextRule) this.instance).addAllAllowedRequestExtensions(values);
            return this;
        }

        public Builder clearAllowedRequestExtensions() {
            copyOnWrite();
            ((ContextRule) this.instance).clearAllowedRequestExtensions();
            return this;
        }

        public Builder addAllowedRequestExtensionsBytes(ByteString value) {
            copyOnWrite();
            ((ContextRule) this.instance).addAllowedRequestExtensionsBytes(value);
            return this;
        }

        public List<String> getAllowedResponseExtensionsList() {
            return Collections.unmodifiableList(((ContextRule) this.instance).getAllowedResponseExtensionsList());
        }

        public int getAllowedResponseExtensionsCount() {
            return ((ContextRule) this.instance).getAllowedResponseExtensionsCount();
        }

        public String getAllowedResponseExtensions(int index) {
            return ((ContextRule) this.instance).getAllowedResponseExtensions(index);
        }

        public ByteString getAllowedResponseExtensionsBytes(int index) {
            return ((ContextRule) this.instance).getAllowedResponseExtensionsBytes(index);
        }

        public Builder setAllowedResponseExtensions(int index, String value) {
            copyOnWrite();
            ((ContextRule) this.instance).setAllowedResponseExtensions(index, value);
            return this;
        }

        public Builder addAllowedResponseExtensions(String value) {
            copyOnWrite();
            ((ContextRule) this.instance).addAllowedResponseExtensions(value);
            return this;
        }

        public Builder addAllAllowedResponseExtensions(Iterable<String> values) {
            copyOnWrite();
            ((ContextRule) this.instance).addAllAllowedResponseExtensions(values);
            return this;
        }

        public Builder clearAllowedResponseExtensions() {
            copyOnWrite();
            ((ContextRule) this.instance).clearAllowedResponseExtensions();
            return this;
        }

        public Builder addAllowedResponseExtensionsBytes(ByteString value) {
            copyOnWrite();
            ((ContextRule) this.instance).addAllowedResponseExtensionsBytes(value);
            return this;
        }
    }

    /* renamed from: com.google.api.ContextRule$1 */
    static /* synthetic */ class C00121 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f10xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f10xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f10xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f10xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f10xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f10xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f10xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f10xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00121.f10xa1df5c61[method.ordinal()]) {
            case 1:
                return new ContextRule();
            case 2:
                return new Builder((C00121) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0004\u0000\u0001Ȉ\u0002Ț\u0003Ț\u0004Ț\u0005Ț", new Object[]{"selector_", "requested_", "provided_", "allowedRequestExtensions_", "allowedResponseExtensions_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ContextRule> parser = PARSER;
                if (parser == null) {
                    synchronized (ContextRule.class) {
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
        ContextRule defaultInstance = new ContextRule();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(ContextRule.class, defaultInstance);
    }

    public static ContextRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ContextRule> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
