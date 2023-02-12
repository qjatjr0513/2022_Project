package com.google.api;

import com.google.api.CustomHttpPattern;
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

public final class HttpRule extends GeneratedMessageLite<HttpRule, Builder> implements HttpRuleOrBuilder {
    public static final int ADDITIONAL_BINDINGS_FIELD_NUMBER = 11;
    public static final int BODY_FIELD_NUMBER = 7;
    public static final int CUSTOM_FIELD_NUMBER = 8;
    /* access modifiers changed from: private */
    public static final HttpRule DEFAULT_INSTANCE;
    public static final int DELETE_FIELD_NUMBER = 5;
    public static final int GET_FIELD_NUMBER = 2;
    private static volatile Parser<HttpRule> PARSER = null;
    public static final int PATCH_FIELD_NUMBER = 6;
    public static final int POST_FIELD_NUMBER = 4;
    public static final int PUT_FIELD_NUMBER = 3;
    public static final int RESPONSE_BODY_FIELD_NUMBER = 12;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private Internal.ProtobufList<HttpRule> additionalBindings_ = emptyProtobufList();
    private String body_ = "";
    private int patternCase_ = 0;
    private Object pattern_;
    private String responseBody_ = "";
    private String selector_ = "";

    private HttpRule() {
    }

    public enum PatternCase {
        GET(2),
        PUT(3),
        POST(4),
        DELETE(5),
        PATCH(6),
        CUSTOM(8),
        PATTERN_NOT_SET(0);
        
        private final int value;

        private PatternCase(int value2) {
            this.value = value2;
        }

        @Deprecated
        public static PatternCase valueOf(int value2) {
            return forNumber(value2);
        }

        public static PatternCase forNumber(int value2) {
            switch (value2) {
                case 0:
                    return PATTERN_NOT_SET;
                case 2:
                    return GET;
                case 3:
                    return PUT;
                case 4:
                    return POST;
                case 5:
                    return DELETE;
                case 6:
                    return PATCH;
                case 8:
                    return CUSTOM;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    public PatternCase getPatternCase() {
        return PatternCase.forNumber(this.patternCase_);
    }

    /* access modifiers changed from: private */
    public void clearPattern() {
        this.patternCase_ = 0;
        this.pattern_ = null;
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

    public String getGet() {
        if (this.patternCase_ == 2) {
            return (String) this.pattern_;
        }
        return "";
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.protobuf.ByteString getGetBytes() {
        /*
            r3 = this;
            java.lang.String r0 = ""
            int r1 = r3.patternCase_
            r2 = 2
            if (r1 != r2) goto L_0x000c
            java.lang.Object r1 = r3.pattern_
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0
        L_0x000c:
            com.google.protobuf.ByteString r1 = com.google.protobuf.ByteString.copyFromUtf8(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.HttpRule.getGetBytes():com.google.protobuf.ByteString");
    }

    /* access modifiers changed from: private */
    public void setGet(String value) {
        value.getClass();
        this.patternCase_ = 2;
        this.pattern_ = value;
    }

    /* access modifiers changed from: private */
    public void clearGet() {
        if (this.patternCase_ == 2) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setGetBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.pattern_ = value.toStringUtf8();
        this.patternCase_ = 2;
    }

    public String getPut() {
        if (this.patternCase_ == 3) {
            return (String) this.pattern_;
        }
        return "";
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.protobuf.ByteString getPutBytes() {
        /*
            r3 = this;
            java.lang.String r0 = ""
            int r1 = r3.patternCase_
            r2 = 3
            if (r1 != r2) goto L_0x000c
            java.lang.Object r1 = r3.pattern_
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0
        L_0x000c:
            com.google.protobuf.ByteString r1 = com.google.protobuf.ByteString.copyFromUtf8(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.HttpRule.getPutBytes():com.google.protobuf.ByteString");
    }

    /* access modifiers changed from: private */
    public void setPut(String value) {
        value.getClass();
        this.patternCase_ = 3;
        this.pattern_ = value;
    }

    /* access modifiers changed from: private */
    public void clearPut() {
        if (this.patternCase_ == 3) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setPutBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.pattern_ = value.toStringUtf8();
        this.patternCase_ = 3;
    }

    public String getPost() {
        if (this.patternCase_ == 4) {
            return (String) this.pattern_;
        }
        return "";
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.protobuf.ByteString getPostBytes() {
        /*
            r3 = this;
            java.lang.String r0 = ""
            int r1 = r3.patternCase_
            r2 = 4
            if (r1 != r2) goto L_0x000c
            java.lang.Object r1 = r3.pattern_
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0
        L_0x000c:
            com.google.protobuf.ByteString r1 = com.google.protobuf.ByteString.copyFromUtf8(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.HttpRule.getPostBytes():com.google.protobuf.ByteString");
    }

    /* access modifiers changed from: private */
    public void setPost(String value) {
        value.getClass();
        this.patternCase_ = 4;
        this.pattern_ = value;
    }

    /* access modifiers changed from: private */
    public void clearPost() {
        if (this.patternCase_ == 4) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setPostBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.pattern_ = value.toStringUtf8();
        this.patternCase_ = 4;
    }

    public String getDelete() {
        if (this.patternCase_ == 5) {
            return (String) this.pattern_;
        }
        return "";
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.protobuf.ByteString getDeleteBytes() {
        /*
            r3 = this;
            java.lang.String r0 = ""
            int r1 = r3.patternCase_
            r2 = 5
            if (r1 != r2) goto L_0x000c
            java.lang.Object r1 = r3.pattern_
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0
        L_0x000c:
            com.google.protobuf.ByteString r1 = com.google.protobuf.ByteString.copyFromUtf8(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.HttpRule.getDeleteBytes():com.google.protobuf.ByteString");
    }

    /* access modifiers changed from: private */
    public void setDelete(String value) {
        value.getClass();
        this.patternCase_ = 5;
        this.pattern_ = value;
    }

    /* access modifiers changed from: private */
    public void clearDelete() {
        if (this.patternCase_ == 5) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setDeleteBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.pattern_ = value.toStringUtf8();
        this.patternCase_ = 5;
    }

    public String getPatch() {
        if (this.patternCase_ == 6) {
            return (String) this.pattern_;
        }
        return "";
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.protobuf.ByteString getPatchBytes() {
        /*
            r3 = this;
            java.lang.String r0 = ""
            int r1 = r3.patternCase_
            r2 = 6
            if (r1 != r2) goto L_0x000c
            java.lang.Object r1 = r3.pattern_
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0
        L_0x000c:
            com.google.protobuf.ByteString r1 = com.google.protobuf.ByteString.copyFromUtf8(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.HttpRule.getPatchBytes():com.google.protobuf.ByteString");
    }

    /* access modifiers changed from: private */
    public void setPatch(String value) {
        value.getClass();
        this.patternCase_ = 6;
        this.pattern_ = value;
    }

    /* access modifiers changed from: private */
    public void clearPatch() {
        if (this.patternCase_ == 6) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setPatchBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.pattern_ = value.toStringUtf8();
        this.patternCase_ = 6;
    }

    public boolean hasCustom() {
        return this.patternCase_ == 8;
    }

    public CustomHttpPattern getCustom() {
        if (this.patternCase_ == 8) {
            return (CustomHttpPattern) this.pattern_;
        }
        return CustomHttpPattern.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setCustom(CustomHttpPattern value) {
        value.getClass();
        this.pattern_ = value;
        this.patternCase_ = 8;
    }

    /* access modifiers changed from: private */
    public void mergeCustom(CustomHttpPattern value) {
        value.getClass();
        if (this.patternCase_ != 8 || this.pattern_ == CustomHttpPattern.getDefaultInstance()) {
            this.pattern_ = value;
        } else {
            this.pattern_ = ((CustomHttpPattern.Builder) CustomHttpPattern.newBuilder((CustomHttpPattern) this.pattern_).mergeFrom(value)).buildPartial();
        }
        this.patternCase_ = 8;
    }

    /* access modifiers changed from: private */
    public void clearCustom() {
        if (this.patternCase_ == 8) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    public String getBody() {
        return this.body_;
    }

    public ByteString getBodyBytes() {
        return ByteString.copyFromUtf8(this.body_);
    }

    /* access modifiers changed from: private */
    public void setBody(String value) {
        value.getClass();
        this.body_ = value;
    }

    /* access modifiers changed from: private */
    public void clearBody() {
        this.body_ = getDefaultInstance().getBody();
    }

    /* access modifiers changed from: private */
    public void setBodyBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.body_ = value.toStringUtf8();
    }

    public String getResponseBody() {
        return this.responseBody_;
    }

    public ByteString getResponseBodyBytes() {
        return ByteString.copyFromUtf8(this.responseBody_);
    }

    /* access modifiers changed from: private */
    public void setResponseBody(String value) {
        value.getClass();
        this.responseBody_ = value;
    }

    /* access modifiers changed from: private */
    public void clearResponseBody() {
        this.responseBody_ = getDefaultInstance().getResponseBody();
    }

    /* access modifiers changed from: private */
    public void setResponseBodyBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.responseBody_ = value.toStringUtf8();
    }

    public List<HttpRule> getAdditionalBindingsList() {
        return this.additionalBindings_;
    }

    public List<? extends HttpRuleOrBuilder> getAdditionalBindingsOrBuilderList() {
        return this.additionalBindings_;
    }

    public int getAdditionalBindingsCount() {
        return this.additionalBindings_.size();
    }

    public HttpRule getAdditionalBindings(int index) {
        return (HttpRule) this.additionalBindings_.get(index);
    }

    public HttpRuleOrBuilder getAdditionalBindingsOrBuilder(int index) {
        return (HttpRuleOrBuilder) this.additionalBindings_.get(index);
    }

    private void ensureAdditionalBindingsIsMutable() {
        Internal.ProtobufList<HttpRule> tmp = this.additionalBindings_;
        if (!tmp.isModifiable()) {
            this.additionalBindings_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setAdditionalBindings(int index, HttpRule value) {
        value.getClass();
        ensureAdditionalBindingsIsMutable();
        this.additionalBindings_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addAdditionalBindings(HttpRule value) {
        value.getClass();
        ensureAdditionalBindingsIsMutable();
        this.additionalBindings_.add(value);
    }

    /* access modifiers changed from: private */
    public void addAdditionalBindings(int index, HttpRule value) {
        value.getClass();
        ensureAdditionalBindingsIsMutable();
        this.additionalBindings_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllAdditionalBindings(Iterable<? extends HttpRule> values) {
        ensureAdditionalBindingsIsMutable();
        AbstractMessageLite.addAll(values, this.additionalBindings_);
    }

    /* access modifiers changed from: private */
    public void clearAdditionalBindings() {
        this.additionalBindings_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeAdditionalBindings(int index) {
        ensureAdditionalBindingsIsMutable();
        this.additionalBindings_.remove(index);
    }

    public static HttpRule parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static HttpRule parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static HttpRule parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static HttpRule parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static HttpRule parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static HttpRule parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static HttpRule parseFrom(InputStream input) throws IOException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static HttpRule parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static HttpRule parseDelimitedFrom(InputStream input) throws IOException {
        return (HttpRule) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static HttpRule parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (HttpRule) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static HttpRule parseFrom(CodedInputStream input) throws IOException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static HttpRule parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(HttpRule prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<HttpRule, Builder> implements HttpRuleOrBuilder {
        /* synthetic */ Builder(C00221 x0) {
            this();
        }

        private Builder() {
            super(HttpRule.DEFAULT_INSTANCE);
        }

        public PatternCase getPatternCase() {
            return ((HttpRule) this.instance).getPatternCase();
        }

        public Builder clearPattern() {
            copyOnWrite();
            ((HttpRule) this.instance).clearPattern();
            return this;
        }

        public String getSelector() {
            return ((HttpRule) this.instance).getSelector();
        }

        public ByteString getSelectorBytes() {
            return ((HttpRule) this.instance).getSelectorBytes();
        }

        public Builder setSelector(String value) {
            copyOnWrite();
            ((HttpRule) this.instance).setSelector(value);
            return this;
        }

        public Builder clearSelector() {
            copyOnWrite();
            ((HttpRule) this.instance).clearSelector();
            return this;
        }

        public Builder setSelectorBytes(ByteString value) {
            copyOnWrite();
            ((HttpRule) this.instance).setSelectorBytes(value);
            return this;
        }

        public String getGet() {
            return ((HttpRule) this.instance).getGet();
        }

        public ByteString getGetBytes() {
            return ((HttpRule) this.instance).getGetBytes();
        }

        public Builder setGet(String value) {
            copyOnWrite();
            ((HttpRule) this.instance).setGet(value);
            return this;
        }

        public Builder clearGet() {
            copyOnWrite();
            ((HttpRule) this.instance).clearGet();
            return this;
        }

        public Builder setGetBytes(ByteString value) {
            copyOnWrite();
            ((HttpRule) this.instance).setGetBytes(value);
            return this;
        }

        public String getPut() {
            return ((HttpRule) this.instance).getPut();
        }

        public ByteString getPutBytes() {
            return ((HttpRule) this.instance).getPutBytes();
        }

        public Builder setPut(String value) {
            copyOnWrite();
            ((HttpRule) this.instance).setPut(value);
            return this;
        }

        public Builder clearPut() {
            copyOnWrite();
            ((HttpRule) this.instance).clearPut();
            return this;
        }

        public Builder setPutBytes(ByteString value) {
            copyOnWrite();
            ((HttpRule) this.instance).setPutBytes(value);
            return this;
        }

        public String getPost() {
            return ((HttpRule) this.instance).getPost();
        }

        public ByteString getPostBytes() {
            return ((HttpRule) this.instance).getPostBytes();
        }

        public Builder setPost(String value) {
            copyOnWrite();
            ((HttpRule) this.instance).setPost(value);
            return this;
        }

        public Builder clearPost() {
            copyOnWrite();
            ((HttpRule) this.instance).clearPost();
            return this;
        }

        public Builder setPostBytes(ByteString value) {
            copyOnWrite();
            ((HttpRule) this.instance).setPostBytes(value);
            return this;
        }

        public String getDelete() {
            return ((HttpRule) this.instance).getDelete();
        }

        public ByteString getDeleteBytes() {
            return ((HttpRule) this.instance).getDeleteBytes();
        }

        public Builder setDelete(String value) {
            copyOnWrite();
            ((HttpRule) this.instance).setDelete(value);
            return this;
        }

        public Builder clearDelete() {
            copyOnWrite();
            ((HttpRule) this.instance).clearDelete();
            return this;
        }

        public Builder setDeleteBytes(ByteString value) {
            copyOnWrite();
            ((HttpRule) this.instance).setDeleteBytes(value);
            return this;
        }

        public String getPatch() {
            return ((HttpRule) this.instance).getPatch();
        }

        public ByteString getPatchBytes() {
            return ((HttpRule) this.instance).getPatchBytes();
        }

        public Builder setPatch(String value) {
            copyOnWrite();
            ((HttpRule) this.instance).setPatch(value);
            return this;
        }

        public Builder clearPatch() {
            copyOnWrite();
            ((HttpRule) this.instance).clearPatch();
            return this;
        }

        public Builder setPatchBytes(ByteString value) {
            copyOnWrite();
            ((HttpRule) this.instance).setPatchBytes(value);
            return this;
        }

        public boolean hasCustom() {
            return ((HttpRule) this.instance).hasCustom();
        }

        public CustomHttpPattern getCustom() {
            return ((HttpRule) this.instance).getCustom();
        }

        public Builder setCustom(CustomHttpPattern value) {
            copyOnWrite();
            ((HttpRule) this.instance).setCustom(value);
            return this;
        }

        public Builder setCustom(CustomHttpPattern.Builder builderForValue) {
            copyOnWrite();
            ((HttpRule) this.instance).setCustom((CustomHttpPattern) builderForValue.build());
            return this;
        }

        public Builder mergeCustom(CustomHttpPattern value) {
            copyOnWrite();
            ((HttpRule) this.instance).mergeCustom(value);
            return this;
        }

        public Builder clearCustom() {
            copyOnWrite();
            ((HttpRule) this.instance).clearCustom();
            return this;
        }

        public String getBody() {
            return ((HttpRule) this.instance).getBody();
        }

        public ByteString getBodyBytes() {
            return ((HttpRule) this.instance).getBodyBytes();
        }

        public Builder setBody(String value) {
            copyOnWrite();
            ((HttpRule) this.instance).setBody(value);
            return this;
        }

        public Builder clearBody() {
            copyOnWrite();
            ((HttpRule) this.instance).clearBody();
            return this;
        }

        public Builder setBodyBytes(ByteString value) {
            copyOnWrite();
            ((HttpRule) this.instance).setBodyBytes(value);
            return this;
        }

        public String getResponseBody() {
            return ((HttpRule) this.instance).getResponseBody();
        }

        public ByteString getResponseBodyBytes() {
            return ((HttpRule) this.instance).getResponseBodyBytes();
        }

        public Builder setResponseBody(String value) {
            copyOnWrite();
            ((HttpRule) this.instance).setResponseBody(value);
            return this;
        }

        public Builder clearResponseBody() {
            copyOnWrite();
            ((HttpRule) this.instance).clearResponseBody();
            return this;
        }

        public Builder setResponseBodyBytes(ByteString value) {
            copyOnWrite();
            ((HttpRule) this.instance).setResponseBodyBytes(value);
            return this;
        }

        public List<HttpRule> getAdditionalBindingsList() {
            return Collections.unmodifiableList(((HttpRule) this.instance).getAdditionalBindingsList());
        }

        public int getAdditionalBindingsCount() {
            return ((HttpRule) this.instance).getAdditionalBindingsCount();
        }

        public HttpRule getAdditionalBindings(int index) {
            return ((HttpRule) this.instance).getAdditionalBindings(index);
        }

        public Builder setAdditionalBindings(int index, HttpRule value) {
            copyOnWrite();
            ((HttpRule) this.instance).setAdditionalBindings(index, value);
            return this;
        }

        public Builder setAdditionalBindings(int index, Builder builderForValue) {
            copyOnWrite();
            ((HttpRule) this.instance).setAdditionalBindings(index, (HttpRule) builderForValue.build());
            return this;
        }

        public Builder addAdditionalBindings(HttpRule value) {
            copyOnWrite();
            ((HttpRule) this.instance).addAdditionalBindings(value);
            return this;
        }

        public Builder addAdditionalBindings(int index, HttpRule value) {
            copyOnWrite();
            ((HttpRule) this.instance).addAdditionalBindings(index, value);
            return this;
        }

        public Builder addAdditionalBindings(Builder builderForValue) {
            copyOnWrite();
            ((HttpRule) this.instance).addAdditionalBindings((HttpRule) builderForValue.build());
            return this;
        }

        public Builder addAdditionalBindings(int index, Builder builderForValue) {
            copyOnWrite();
            ((HttpRule) this.instance).addAdditionalBindings(index, (HttpRule) builderForValue.build());
            return this;
        }

        public Builder addAllAdditionalBindings(Iterable<? extends HttpRule> values) {
            copyOnWrite();
            ((HttpRule) this.instance).addAllAdditionalBindings(values);
            return this;
        }

        public Builder clearAdditionalBindings() {
            copyOnWrite();
            ((HttpRule) this.instance).clearAdditionalBindings();
            return this;
        }

        public Builder removeAdditionalBindings(int index) {
            copyOnWrite();
            ((HttpRule) this.instance).removeAdditionalBindings(index);
            return this;
        }
    }

    /* renamed from: com.google.api.HttpRule$1 */
    static /* synthetic */ class C00221 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f19xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f19xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f19xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f19xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f19xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f19xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f19xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f19xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        Class<HttpRule> cls = HttpRule.class;
        switch (C00221.f19xa1df5c61[method.ordinal()]) {
            case 1:
                return new HttpRule();
            case 2:
                return new Builder((C00221) null);
            case 3:
                Object[] objects = {"pattern_", "patternCase_", "selector_", "body_", CustomHttpPattern.class, "additionalBindings_", cls, "responseBody_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\n\u0001\u0000\u0001\f\n\u0000\u0001\u0000\u0001Ȉ\u0002Ȼ\u0000\u0003Ȼ\u0000\u0004Ȼ\u0000\u0005Ȼ\u0000\u0006Ȼ\u0000\u0007Ȉ\b<\u0000\u000b\u001b\fȈ", objects);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<HttpRule> parser = PARSER;
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
        HttpRule defaultInstance = new HttpRule();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(HttpRule.class, defaultInstance);
    }

    public static HttpRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HttpRule> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
