package com.google.api;

import com.google.api.Property;
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

public final class ProjectProperties extends GeneratedMessageLite<ProjectProperties, Builder> implements ProjectPropertiesOrBuilder {
    /* access modifiers changed from: private */
    public static final ProjectProperties DEFAULT_INSTANCE;
    private static volatile Parser<ProjectProperties> PARSER = null;
    public static final int PROPERTIES_FIELD_NUMBER = 1;
    private Internal.ProtobufList<Property> properties_ = emptyProtobufList();

    private ProjectProperties() {
    }

    public List<Property> getPropertiesList() {
        return this.properties_;
    }

    public List<? extends PropertyOrBuilder> getPropertiesOrBuilderList() {
        return this.properties_;
    }

    public int getPropertiesCount() {
        return this.properties_.size();
    }

    public Property getProperties(int index) {
        return (Property) this.properties_.get(index);
    }

    public PropertyOrBuilder getPropertiesOrBuilder(int index) {
        return (PropertyOrBuilder) this.properties_.get(index);
    }

    private void ensurePropertiesIsMutable() {
        Internal.ProtobufList<Property> tmp = this.properties_;
        if (!tmp.isModifiable()) {
            this.properties_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setProperties(int index, Property value) {
        value.getClass();
        ensurePropertiesIsMutable();
        this.properties_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addProperties(Property value) {
        value.getClass();
        ensurePropertiesIsMutable();
        this.properties_.add(value);
    }

    /* access modifiers changed from: private */
    public void addProperties(int index, Property value) {
        value.getClass();
        ensurePropertiesIsMutable();
        this.properties_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllProperties(Iterable<? extends Property> values) {
        ensurePropertiesIsMutable();
        AbstractMessageLite.addAll(values, this.properties_);
    }

    /* access modifiers changed from: private */
    public void clearProperties() {
        this.properties_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeProperties(int index) {
        ensurePropertiesIsMutable();
        this.properties_.remove(index);
    }

    public static ProjectProperties parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProjectProperties parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProjectProperties parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProjectProperties parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProjectProperties parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProjectProperties parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProjectProperties parseFrom(InputStream input) throws IOException {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProjectProperties parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProjectProperties parseDelimitedFrom(InputStream input) throws IOException {
        return (ProjectProperties) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ProjectProperties parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProjectProperties) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProjectProperties parseFrom(CodedInputStream input) throws IOException {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProjectProperties parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ProjectProperties prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProjectProperties, Builder> implements ProjectPropertiesOrBuilder {
        /* synthetic */ Builder(C00401 x0) {
            this();
        }

        private Builder() {
            super(ProjectProperties.DEFAULT_INSTANCE);
        }

        public List<Property> getPropertiesList() {
            return Collections.unmodifiableList(((ProjectProperties) this.instance).getPropertiesList());
        }

        public int getPropertiesCount() {
            return ((ProjectProperties) this.instance).getPropertiesCount();
        }

        public Property getProperties(int index) {
            return ((ProjectProperties) this.instance).getProperties(index);
        }

        public Builder setProperties(int index, Property value) {
            copyOnWrite();
            ((ProjectProperties) this.instance).setProperties(index, value);
            return this;
        }

        public Builder setProperties(int index, Property.Builder builderForValue) {
            copyOnWrite();
            ((ProjectProperties) this.instance).setProperties(index, (Property) builderForValue.build());
            return this;
        }

        public Builder addProperties(Property value) {
            copyOnWrite();
            ((ProjectProperties) this.instance).addProperties(value);
            return this;
        }

        public Builder addProperties(int index, Property value) {
            copyOnWrite();
            ((ProjectProperties) this.instance).addProperties(index, value);
            return this;
        }

        public Builder addProperties(Property.Builder builderForValue) {
            copyOnWrite();
            ((ProjectProperties) this.instance).addProperties((Property) builderForValue.build());
            return this;
        }

        public Builder addProperties(int index, Property.Builder builderForValue) {
            copyOnWrite();
            ((ProjectProperties) this.instance).addProperties(index, (Property) builderForValue.build());
            return this;
        }

        public Builder addAllProperties(Iterable<? extends Property> values) {
            copyOnWrite();
            ((ProjectProperties) this.instance).addAllProperties(values);
            return this;
        }

        public Builder clearProperties() {
            copyOnWrite();
            ((ProjectProperties) this.instance).clearProperties();
            return this;
        }

        public Builder removeProperties(int index) {
            copyOnWrite();
            ((ProjectProperties) this.instance).removeProperties(index);
            return this;
        }
    }

    /* renamed from: com.google.api.ProjectProperties$1 */
    static /* synthetic */ class C00401 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f34xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f34xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f34xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f34xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f34xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f34xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f34xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f34xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00401.f34xa1df5c61[method.ordinal()]) {
            case 1:
                return new ProjectProperties();
            case 2:
                return new Builder((C00401) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"properties_", Property.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ProjectProperties> parser = PARSER;
                if (parser == null) {
                    synchronized (ProjectProperties.class) {
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
        ProjectProperties defaultInstance = new ProjectProperties();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(ProjectProperties.class, defaultInstance);
    }

    public static ProjectProperties getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ProjectProperties> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
