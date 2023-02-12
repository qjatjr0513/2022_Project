package com.google.rpc;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class Help extends GeneratedMessageLite<Help, Builder> implements HelpOrBuilder {
    /* access modifiers changed from: private */
    public static final Help DEFAULT_INSTANCE;
    public static final int LINKS_FIELD_NUMBER = 1;
    private static volatile Parser<Help> PARSER;
    private Internal.ProtobufList<Link> links_ = emptyProtobufList();

    public interface LinkOrBuilder extends MessageLiteOrBuilder {
        String getDescription();

        ByteString getDescriptionBytes();

        String getUrl();

        ByteString getUrlBytes();
    }

    private Help() {
    }

    public static final class Link extends GeneratedMessageLite<Link, Builder> implements LinkOrBuilder {
        /* access modifiers changed from: private */
        public static final Link DEFAULT_INSTANCE;
        public static final int DESCRIPTION_FIELD_NUMBER = 1;
        private static volatile Parser<Link> PARSER = null;
        public static final int URL_FIELD_NUMBER = 2;
        private String description_ = "";
        private String url_ = "";

        private Link() {
        }

        public String getDescription() {
            return this.description_;
        }

        public ByteString getDescriptionBytes() {
            return ByteString.copyFromUtf8(this.description_);
        }

        /* access modifiers changed from: private */
        public void setDescription(String value) {
            value.getClass();
            this.description_ = value;
        }

        /* access modifiers changed from: private */
        public void clearDescription() {
            this.description_ = getDefaultInstance().getDescription();
        }

        /* access modifiers changed from: private */
        public void setDescriptionBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.description_ = value.toStringUtf8();
        }

        public String getUrl() {
            return this.url_;
        }

        public ByteString getUrlBytes() {
            return ByteString.copyFromUtf8(this.url_);
        }

        /* access modifiers changed from: private */
        public void setUrl(String value) {
            value.getClass();
            this.url_ = value;
        }

        /* access modifiers changed from: private */
        public void clearUrl() {
            this.url_ = getDefaultInstance().getUrl();
        }

        /* access modifiers changed from: private */
        public void setUrlBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.url_ = value.toStringUtf8();
        }

        public static Link parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Link parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Link parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Link parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Link parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Link parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Link parseFrom(InputStream input) throws IOException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Link parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Link parseDelimitedFrom(InputStream input) throws IOException {
            return (Link) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Link parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Link) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Link parseFrom(CodedInputStream input) throws IOException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Link parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Link prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Link, Builder> implements LinkOrBuilder {
            /* synthetic */ Builder(C10631 x0) {
                this();
            }

            private Builder() {
                super(Link.DEFAULT_INSTANCE);
            }

            public String getDescription() {
                return ((Link) this.instance).getDescription();
            }

            public ByteString getDescriptionBytes() {
                return ((Link) this.instance).getDescriptionBytes();
            }

            public Builder setDescription(String value) {
                copyOnWrite();
                ((Link) this.instance).setDescription(value);
                return this;
            }

            public Builder clearDescription() {
                copyOnWrite();
                ((Link) this.instance).clearDescription();
                return this;
            }

            public Builder setDescriptionBytes(ByteString value) {
                copyOnWrite();
                ((Link) this.instance).setDescriptionBytes(value);
                return this;
            }

            public String getUrl() {
                return ((Link) this.instance).getUrl();
            }

            public ByteString getUrlBytes() {
                return ((Link) this.instance).getUrlBytes();
            }

            public Builder setUrl(String value) {
                copyOnWrite();
                ((Link) this.instance).setUrl(value);
                return this;
            }

            public Builder clearUrl() {
                copyOnWrite();
                ((Link) this.instance).clearUrl();
                return this;
            }

            public Builder setUrlBytes(ByteString value) {
                copyOnWrite();
                ((Link) this.instance).setUrlBytes(value);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C10631.f285xa1df5c61[method.ordinal()]) {
                case 1:
                    return new Link();
                case 2:
                    return new Builder((C10631) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"description_", "url_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Link> parser = PARSER;
                    if (parser == null) {
                        synchronized (Link.class) {
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
            Link defaultInstance = new Link();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Link.class, defaultInstance);
        }

        public static Link getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Link> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.rpc.Help$1 */
    static /* synthetic */ class C10631 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f285xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f285xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f285xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f285xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f285xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f285xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f285xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f285xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public List<Link> getLinksList() {
        return this.links_;
    }

    public List<? extends LinkOrBuilder> getLinksOrBuilderList() {
        return this.links_;
    }

    public int getLinksCount() {
        return this.links_.size();
    }

    public Link getLinks(int index) {
        return (Link) this.links_.get(index);
    }

    public LinkOrBuilder getLinksOrBuilder(int index) {
        return (LinkOrBuilder) this.links_.get(index);
    }

    private void ensureLinksIsMutable() {
        Internal.ProtobufList<Link> tmp = this.links_;
        if (!tmp.isModifiable()) {
            this.links_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setLinks(int index, Link value) {
        value.getClass();
        ensureLinksIsMutable();
        this.links_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addLinks(Link value) {
        value.getClass();
        ensureLinksIsMutable();
        this.links_.add(value);
    }

    /* access modifiers changed from: private */
    public void addLinks(int index, Link value) {
        value.getClass();
        ensureLinksIsMutable();
        this.links_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllLinks(Iterable<? extends Link> values) {
        ensureLinksIsMutable();
        AbstractMessageLite.addAll(values, this.links_);
    }

    /* access modifiers changed from: private */
    public void clearLinks() {
        this.links_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeLinks(int index) {
        ensureLinksIsMutable();
        this.links_.remove(index);
    }

    public static Help parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Help parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Help parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Help parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Help parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Help parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Help parseFrom(InputStream input) throws IOException {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Help parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Help parseDelimitedFrom(InputStream input) throws IOException {
        return (Help) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Help parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Help) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Help parseFrom(CodedInputStream input) throws IOException {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Help parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Help prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Help, Builder> implements HelpOrBuilder {
        /* synthetic */ Builder(C10631 x0) {
            this();
        }

        private Builder() {
            super(Help.DEFAULT_INSTANCE);
        }

        public List<Link> getLinksList() {
            return Collections.unmodifiableList(((Help) this.instance).getLinksList());
        }

        public int getLinksCount() {
            return ((Help) this.instance).getLinksCount();
        }

        public Link getLinks(int index) {
            return ((Help) this.instance).getLinks(index);
        }

        public Builder setLinks(int index, Link value) {
            copyOnWrite();
            ((Help) this.instance).setLinks(index, value);
            return this;
        }

        public Builder setLinks(int index, Link.Builder builderForValue) {
            copyOnWrite();
            ((Help) this.instance).setLinks(index, (Link) builderForValue.build());
            return this;
        }

        public Builder addLinks(Link value) {
            copyOnWrite();
            ((Help) this.instance).addLinks(value);
            return this;
        }

        public Builder addLinks(int index, Link value) {
            copyOnWrite();
            ((Help) this.instance).addLinks(index, value);
            return this;
        }

        public Builder addLinks(Link.Builder builderForValue) {
            copyOnWrite();
            ((Help) this.instance).addLinks((Link) builderForValue.build());
            return this;
        }

        public Builder addLinks(int index, Link.Builder builderForValue) {
            copyOnWrite();
            ((Help) this.instance).addLinks(index, (Link) builderForValue.build());
            return this;
        }

        public Builder addAllLinks(Iterable<? extends Link> values) {
            copyOnWrite();
            ((Help) this.instance).addAllLinks(values);
            return this;
        }

        public Builder clearLinks() {
            copyOnWrite();
            ((Help) this.instance).clearLinks();
            return this;
        }

        public Builder removeLinks(int index) {
            copyOnWrite();
            ((Help) this.instance).removeLinks(index);
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C10631.f285xa1df5c61[method.ordinal()]) {
            case 1:
                return new Help();
            case 2:
                return new Builder((C10631) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"links_", Link.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Help> parser = PARSER;
                if (parser == null) {
                    synchronized (Help.class) {
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
        Help defaultInstance = new Help();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Help.class, defaultInstance);
    }

    public static Help getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Help> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
