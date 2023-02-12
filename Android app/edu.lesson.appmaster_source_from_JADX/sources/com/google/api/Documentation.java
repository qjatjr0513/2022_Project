package com.google.api;

import com.google.api.DocumentationRule;
import com.google.api.Page;
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

public final class Documentation extends GeneratedMessageLite<Documentation, Builder> implements DocumentationOrBuilder {
    /* access modifiers changed from: private */
    public static final Documentation DEFAULT_INSTANCE;
    public static final int DOCUMENTATION_ROOT_URL_FIELD_NUMBER = 4;
    public static final int OVERVIEW_FIELD_NUMBER = 2;
    public static final int PAGES_FIELD_NUMBER = 5;
    private static volatile Parser<Documentation> PARSER = null;
    public static final int RULES_FIELD_NUMBER = 3;
    public static final int SUMMARY_FIELD_NUMBER = 1;
    private String documentationRootUrl_ = "";
    private String overview_ = "";
    private Internal.ProtobufList<Page> pages_ = emptyProtobufList();
    private Internal.ProtobufList<DocumentationRule> rules_ = emptyProtobufList();
    private String summary_ = "";

    private Documentation() {
    }

    public String getSummary() {
        return this.summary_;
    }

    public ByteString getSummaryBytes() {
        return ByteString.copyFromUtf8(this.summary_);
    }

    /* access modifiers changed from: private */
    public void setSummary(String value) {
        value.getClass();
        this.summary_ = value;
    }

    /* access modifiers changed from: private */
    public void clearSummary() {
        this.summary_ = getDefaultInstance().getSummary();
    }

    /* access modifiers changed from: private */
    public void setSummaryBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.summary_ = value.toStringUtf8();
    }

    public List<Page> getPagesList() {
        return this.pages_;
    }

    public List<? extends PageOrBuilder> getPagesOrBuilderList() {
        return this.pages_;
    }

    public int getPagesCount() {
        return this.pages_.size();
    }

    public Page getPages(int index) {
        return (Page) this.pages_.get(index);
    }

    public PageOrBuilder getPagesOrBuilder(int index) {
        return (PageOrBuilder) this.pages_.get(index);
    }

    private void ensurePagesIsMutable() {
        Internal.ProtobufList<Page> tmp = this.pages_;
        if (!tmp.isModifiable()) {
            this.pages_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setPages(int index, Page value) {
        value.getClass();
        ensurePagesIsMutable();
        this.pages_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addPages(Page value) {
        value.getClass();
        ensurePagesIsMutable();
        this.pages_.add(value);
    }

    /* access modifiers changed from: private */
    public void addPages(int index, Page value) {
        value.getClass();
        ensurePagesIsMutable();
        this.pages_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllPages(Iterable<? extends Page> values) {
        ensurePagesIsMutable();
        AbstractMessageLite.addAll(values, this.pages_);
    }

    /* access modifiers changed from: private */
    public void clearPages() {
        this.pages_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removePages(int index) {
        ensurePagesIsMutable();
        this.pages_.remove(index);
    }

    public List<DocumentationRule> getRulesList() {
        return this.rules_;
    }

    public List<? extends DocumentationRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    public int getRulesCount() {
        return this.rules_.size();
    }

    public DocumentationRule getRules(int index) {
        return (DocumentationRule) this.rules_.get(index);
    }

    public DocumentationRuleOrBuilder getRulesOrBuilder(int index) {
        return (DocumentationRuleOrBuilder) this.rules_.get(index);
    }

    private void ensureRulesIsMutable() {
        Internal.ProtobufList<DocumentationRule> tmp = this.rules_;
        if (!tmp.isModifiable()) {
            this.rules_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setRules(int index, DocumentationRule value) {
        value.getClass();
        ensureRulesIsMutable();
        this.rules_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addRules(DocumentationRule value) {
        value.getClass();
        ensureRulesIsMutable();
        this.rules_.add(value);
    }

    /* access modifiers changed from: private */
    public void addRules(int index, DocumentationRule value) {
        value.getClass();
        ensureRulesIsMutable();
        this.rules_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllRules(Iterable<? extends DocumentationRule> values) {
        ensureRulesIsMutable();
        AbstractMessageLite.addAll(values, this.rules_);
    }

    /* access modifiers changed from: private */
    public void clearRules() {
        this.rules_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeRules(int index) {
        ensureRulesIsMutable();
        this.rules_.remove(index);
    }

    public String getDocumentationRootUrl() {
        return this.documentationRootUrl_;
    }

    public ByteString getDocumentationRootUrlBytes() {
        return ByteString.copyFromUtf8(this.documentationRootUrl_);
    }

    /* access modifiers changed from: private */
    public void setDocumentationRootUrl(String value) {
        value.getClass();
        this.documentationRootUrl_ = value;
    }

    /* access modifiers changed from: private */
    public void clearDocumentationRootUrl() {
        this.documentationRootUrl_ = getDefaultInstance().getDocumentationRootUrl();
    }

    /* access modifiers changed from: private */
    public void setDocumentationRootUrlBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.documentationRootUrl_ = value.toStringUtf8();
    }

    public String getOverview() {
        return this.overview_;
    }

    public ByteString getOverviewBytes() {
        return ByteString.copyFromUtf8(this.overview_);
    }

    /* access modifiers changed from: private */
    public void setOverview(String value) {
        value.getClass();
        this.overview_ = value;
    }

    /* access modifiers changed from: private */
    public void clearOverview() {
        this.overview_ = getDefaultInstance().getOverview();
    }

    /* access modifiers changed from: private */
    public void setOverviewBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.overview_ = value.toStringUtf8();
    }

    public static Documentation parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Documentation parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Documentation parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Documentation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Documentation parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Documentation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Documentation parseFrom(InputStream input) throws IOException {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Documentation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Documentation parseDelimitedFrom(InputStream input) throws IOException {
        return (Documentation) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Documentation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Documentation) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Documentation parseFrom(CodedInputStream input) throws IOException {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Documentation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Documentation prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Documentation, Builder> implements DocumentationOrBuilder {
        /* synthetic */ Builder(C00161 x0) {
            this();
        }

        private Builder() {
            super(Documentation.DEFAULT_INSTANCE);
        }

        public String getSummary() {
            return ((Documentation) this.instance).getSummary();
        }

        public ByteString getSummaryBytes() {
            return ((Documentation) this.instance).getSummaryBytes();
        }

        public Builder setSummary(String value) {
            copyOnWrite();
            ((Documentation) this.instance).setSummary(value);
            return this;
        }

        public Builder clearSummary() {
            copyOnWrite();
            ((Documentation) this.instance).clearSummary();
            return this;
        }

        public Builder setSummaryBytes(ByteString value) {
            copyOnWrite();
            ((Documentation) this.instance).setSummaryBytes(value);
            return this;
        }

        public List<Page> getPagesList() {
            return Collections.unmodifiableList(((Documentation) this.instance).getPagesList());
        }

        public int getPagesCount() {
            return ((Documentation) this.instance).getPagesCount();
        }

        public Page getPages(int index) {
            return ((Documentation) this.instance).getPages(index);
        }

        public Builder setPages(int index, Page value) {
            copyOnWrite();
            ((Documentation) this.instance).setPages(index, value);
            return this;
        }

        public Builder setPages(int index, Page.Builder builderForValue) {
            copyOnWrite();
            ((Documentation) this.instance).setPages(index, (Page) builderForValue.build());
            return this;
        }

        public Builder addPages(Page value) {
            copyOnWrite();
            ((Documentation) this.instance).addPages(value);
            return this;
        }

        public Builder addPages(int index, Page value) {
            copyOnWrite();
            ((Documentation) this.instance).addPages(index, value);
            return this;
        }

        public Builder addPages(Page.Builder builderForValue) {
            copyOnWrite();
            ((Documentation) this.instance).addPages((Page) builderForValue.build());
            return this;
        }

        public Builder addPages(int index, Page.Builder builderForValue) {
            copyOnWrite();
            ((Documentation) this.instance).addPages(index, (Page) builderForValue.build());
            return this;
        }

        public Builder addAllPages(Iterable<? extends Page> values) {
            copyOnWrite();
            ((Documentation) this.instance).addAllPages(values);
            return this;
        }

        public Builder clearPages() {
            copyOnWrite();
            ((Documentation) this.instance).clearPages();
            return this;
        }

        public Builder removePages(int index) {
            copyOnWrite();
            ((Documentation) this.instance).removePages(index);
            return this;
        }

        public List<DocumentationRule> getRulesList() {
            return Collections.unmodifiableList(((Documentation) this.instance).getRulesList());
        }

        public int getRulesCount() {
            return ((Documentation) this.instance).getRulesCount();
        }

        public DocumentationRule getRules(int index) {
            return ((Documentation) this.instance).getRules(index);
        }

        public Builder setRules(int index, DocumentationRule value) {
            copyOnWrite();
            ((Documentation) this.instance).setRules(index, value);
            return this;
        }

        public Builder setRules(int index, DocumentationRule.Builder builderForValue) {
            copyOnWrite();
            ((Documentation) this.instance).setRules(index, (DocumentationRule) builderForValue.build());
            return this;
        }

        public Builder addRules(DocumentationRule value) {
            copyOnWrite();
            ((Documentation) this.instance).addRules(value);
            return this;
        }

        public Builder addRules(int index, DocumentationRule value) {
            copyOnWrite();
            ((Documentation) this.instance).addRules(index, value);
            return this;
        }

        public Builder addRules(DocumentationRule.Builder builderForValue) {
            copyOnWrite();
            ((Documentation) this.instance).addRules((DocumentationRule) builderForValue.build());
            return this;
        }

        public Builder addRules(int index, DocumentationRule.Builder builderForValue) {
            copyOnWrite();
            ((Documentation) this.instance).addRules(index, (DocumentationRule) builderForValue.build());
            return this;
        }

        public Builder addAllRules(Iterable<? extends DocumentationRule> values) {
            copyOnWrite();
            ((Documentation) this.instance).addAllRules(values);
            return this;
        }

        public Builder clearRules() {
            copyOnWrite();
            ((Documentation) this.instance).clearRules();
            return this;
        }

        public Builder removeRules(int index) {
            copyOnWrite();
            ((Documentation) this.instance).removeRules(index);
            return this;
        }

        public String getDocumentationRootUrl() {
            return ((Documentation) this.instance).getDocumentationRootUrl();
        }

        public ByteString getDocumentationRootUrlBytes() {
            return ((Documentation) this.instance).getDocumentationRootUrlBytes();
        }

        public Builder setDocumentationRootUrl(String value) {
            copyOnWrite();
            ((Documentation) this.instance).setDocumentationRootUrl(value);
            return this;
        }

        public Builder clearDocumentationRootUrl() {
            copyOnWrite();
            ((Documentation) this.instance).clearDocumentationRootUrl();
            return this;
        }

        public Builder setDocumentationRootUrlBytes(ByteString value) {
            copyOnWrite();
            ((Documentation) this.instance).setDocumentationRootUrlBytes(value);
            return this;
        }

        public String getOverview() {
            return ((Documentation) this.instance).getOverview();
        }

        public ByteString getOverviewBytes() {
            return ((Documentation) this.instance).getOverviewBytes();
        }

        public Builder setOverview(String value) {
            copyOnWrite();
            ((Documentation) this.instance).setOverview(value);
            return this;
        }

        public Builder clearOverview() {
            copyOnWrite();
            ((Documentation) this.instance).clearOverview();
            return this;
        }

        public Builder setOverviewBytes(ByteString value) {
            copyOnWrite();
            ((Documentation) this.instance).setOverviewBytes(value);
            return this;
        }
    }

    /* renamed from: com.google.api.Documentation$1 */
    static /* synthetic */ class C00161 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f14xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f14xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f14xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f14xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f14xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f14xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f14xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f14xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00161.f14xa1df5c61[method.ordinal()]) {
            case 1:
                return new Documentation();
            case 2:
                return new Builder((C00161) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0002\u0000\u0001Ȉ\u0002Ȉ\u0003\u001b\u0004Ȉ\u0005\u001b", new Object[]{"summary_", "overview_", "rules_", DocumentationRule.class, "documentationRootUrl_", "pages_", Page.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Documentation> parser = PARSER;
                if (parser == null) {
                    synchronized (Documentation.class) {
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
        Documentation defaultInstance = new Documentation();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Documentation.class, defaultInstance);
    }

    public static Documentation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Documentation> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
