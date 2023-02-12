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

public final class Page extends GeneratedMessageLite<Page, Builder> implements PageOrBuilder {
    public static final int CONTENT_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Page DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<Page> PARSER = null;
    public static final int SUBPAGES_FIELD_NUMBER = 3;
    private String content_ = "";
    private String name_ = "";
    private Internal.ProtobufList<Page> subpages_ = emptyProtobufList();

    private Page() {
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

    public String getContent() {
        return this.content_;
    }

    public ByteString getContentBytes() {
        return ByteString.copyFromUtf8(this.content_);
    }

    /* access modifiers changed from: private */
    public void setContent(String value) {
        value.getClass();
        this.content_ = value;
    }

    /* access modifiers changed from: private */
    public void clearContent() {
        this.content_ = getDefaultInstance().getContent();
    }

    /* access modifiers changed from: private */
    public void setContentBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.content_ = value.toStringUtf8();
    }

    public List<Page> getSubpagesList() {
        return this.subpages_;
    }

    public List<? extends PageOrBuilder> getSubpagesOrBuilderList() {
        return this.subpages_;
    }

    public int getSubpagesCount() {
        return this.subpages_.size();
    }

    public Page getSubpages(int index) {
        return (Page) this.subpages_.get(index);
    }

    public PageOrBuilder getSubpagesOrBuilder(int index) {
        return (PageOrBuilder) this.subpages_.get(index);
    }

    private void ensureSubpagesIsMutable() {
        Internal.ProtobufList<Page> tmp = this.subpages_;
        if (!tmp.isModifiable()) {
            this.subpages_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setSubpages(int index, Page value) {
        value.getClass();
        ensureSubpagesIsMutable();
        this.subpages_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addSubpages(Page value) {
        value.getClass();
        ensureSubpagesIsMutable();
        this.subpages_.add(value);
    }

    /* access modifiers changed from: private */
    public void addSubpages(int index, Page value) {
        value.getClass();
        ensureSubpagesIsMutable();
        this.subpages_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllSubpages(Iterable<? extends Page> values) {
        ensureSubpagesIsMutable();
        AbstractMessageLite.addAll(values, this.subpages_);
    }

    /* access modifiers changed from: private */
    public void clearSubpages() {
        this.subpages_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeSubpages(int index) {
        ensureSubpagesIsMutable();
        this.subpages_.remove(index);
    }

    public static Page parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Page parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Page parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Page parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Page parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Page parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Page parseFrom(InputStream input) throws IOException {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Page parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Page parseDelimitedFrom(InputStream input) throws IOException {
        return (Page) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Page parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Page) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Page parseFrom(CodedInputStream input) throws IOException {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Page parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Page prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Page, Builder> implements PageOrBuilder {
        /* synthetic */ Builder(C00391 x0) {
            this();
        }

        private Builder() {
            super(Page.DEFAULT_INSTANCE);
        }

        public String getName() {
            return ((Page) this.instance).getName();
        }

        public ByteString getNameBytes() {
            return ((Page) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((Page) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((Page) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((Page) this.instance).setNameBytes(value);
            return this;
        }

        public String getContent() {
            return ((Page) this.instance).getContent();
        }

        public ByteString getContentBytes() {
            return ((Page) this.instance).getContentBytes();
        }

        public Builder setContent(String value) {
            copyOnWrite();
            ((Page) this.instance).setContent(value);
            return this;
        }

        public Builder clearContent() {
            copyOnWrite();
            ((Page) this.instance).clearContent();
            return this;
        }

        public Builder setContentBytes(ByteString value) {
            copyOnWrite();
            ((Page) this.instance).setContentBytes(value);
            return this;
        }

        public List<Page> getSubpagesList() {
            return Collections.unmodifiableList(((Page) this.instance).getSubpagesList());
        }

        public int getSubpagesCount() {
            return ((Page) this.instance).getSubpagesCount();
        }

        public Page getSubpages(int index) {
            return ((Page) this.instance).getSubpages(index);
        }

        public Builder setSubpages(int index, Page value) {
            copyOnWrite();
            ((Page) this.instance).setSubpages(index, value);
            return this;
        }

        public Builder setSubpages(int index, Builder builderForValue) {
            copyOnWrite();
            ((Page) this.instance).setSubpages(index, (Page) builderForValue.build());
            return this;
        }

        public Builder addSubpages(Page value) {
            copyOnWrite();
            ((Page) this.instance).addSubpages(value);
            return this;
        }

        public Builder addSubpages(int index, Page value) {
            copyOnWrite();
            ((Page) this.instance).addSubpages(index, value);
            return this;
        }

        public Builder addSubpages(Builder builderForValue) {
            copyOnWrite();
            ((Page) this.instance).addSubpages((Page) builderForValue.build());
            return this;
        }

        public Builder addSubpages(int index, Builder builderForValue) {
            copyOnWrite();
            ((Page) this.instance).addSubpages(index, (Page) builderForValue.build());
            return this;
        }

        public Builder addAllSubpages(Iterable<? extends Page> values) {
            copyOnWrite();
            ((Page) this.instance).addAllSubpages(values);
            return this;
        }

        public Builder clearSubpages() {
            copyOnWrite();
            ((Page) this.instance).clearSubpages();
            return this;
        }

        public Builder removeSubpages(int index) {
            copyOnWrite();
            ((Page) this.instance).removeSubpages(index);
            return this;
        }
    }

    /* renamed from: com.google.api.Page$1 */
    static /* synthetic */ class C00391 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f33xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f33xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f33xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f33xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f33xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f33xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f33xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f33xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        Class<Page> cls = Page.class;
        switch (C00391.f33xa1df5c61[method.ordinal()]) {
            case 1:
                return new Page();
            case 2:
                return new Builder((C00391) null);
            case 3:
                Object[] objects = {"name_", "content_", "subpages_", cls};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001Ȉ\u0002Ȉ\u0003\u001b", objects);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Page> parser = PARSER;
                if (parser == null) {
                    synchronized (cls) {
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
        Page defaultInstance = new Page();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Page.class, defaultInstance);
    }

    public static Page getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Page> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
