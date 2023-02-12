package com.google.geo.type;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.type.LatLng;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Viewport extends GeneratedMessageLite<Viewport, Builder> implements ViewportOrBuilder {
    /* access modifiers changed from: private */
    public static final Viewport DEFAULT_INSTANCE;
    public static final int HIGH_FIELD_NUMBER = 2;
    public static final int LOW_FIELD_NUMBER = 1;
    private static volatile Parser<Viewport> PARSER;
    private LatLng high_;
    private LatLng low_;

    private Viewport() {
    }

    public boolean hasLow() {
        return this.low_ != null;
    }

    public LatLng getLow() {
        LatLng latLng = this.low_;
        return latLng == null ? LatLng.getDefaultInstance() : latLng;
    }

    /* access modifiers changed from: private */
    public void setLow(LatLng value) {
        value.getClass();
        this.low_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeLow(LatLng value) {
        value.getClass();
        LatLng latLng = this.low_;
        if (latLng == null || latLng == LatLng.getDefaultInstance()) {
            this.low_ = value;
        } else {
            this.low_ = (LatLng) ((LatLng.Builder) LatLng.newBuilder(this.low_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearLow() {
        this.low_ = null;
    }

    public boolean hasHigh() {
        return this.high_ != null;
    }

    public LatLng getHigh() {
        LatLng latLng = this.high_;
        return latLng == null ? LatLng.getDefaultInstance() : latLng;
    }

    /* access modifiers changed from: private */
    public void setHigh(LatLng value) {
        value.getClass();
        this.high_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeHigh(LatLng value) {
        value.getClass();
        LatLng latLng = this.high_;
        if (latLng == null || latLng == LatLng.getDefaultInstance()) {
            this.high_ = value;
        } else {
            this.high_ = (LatLng) ((LatLng.Builder) LatLng.newBuilder(this.high_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearHigh() {
        this.high_ = null;
    }

    public static Viewport parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Viewport parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Viewport parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Viewport parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Viewport parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Viewport parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Viewport parseFrom(InputStream input) throws IOException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Viewport parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Viewport parseDelimitedFrom(InputStream input) throws IOException {
        return (Viewport) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Viewport parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Viewport) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Viewport parseFrom(CodedInputStream input) throws IOException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Viewport parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Viewport prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Viewport, Builder> implements ViewportOrBuilder {
        /* synthetic */ Builder(C08721 x0) {
            this();
        }

        private Builder() {
            super(Viewport.DEFAULT_INSTANCE);
        }

        public boolean hasLow() {
            return ((Viewport) this.instance).hasLow();
        }

        public LatLng getLow() {
            return ((Viewport) this.instance).getLow();
        }

        public Builder setLow(LatLng value) {
            copyOnWrite();
            ((Viewport) this.instance).setLow(value);
            return this;
        }

        public Builder setLow(LatLng.Builder builderForValue) {
            copyOnWrite();
            ((Viewport) this.instance).setLow((LatLng) builderForValue.build());
            return this;
        }

        public Builder mergeLow(LatLng value) {
            copyOnWrite();
            ((Viewport) this.instance).mergeLow(value);
            return this;
        }

        public Builder clearLow() {
            copyOnWrite();
            ((Viewport) this.instance).clearLow();
            return this;
        }

        public boolean hasHigh() {
            return ((Viewport) this.instance).hasHigh();
        }

        public LatLng getHigh() {
            return ((Viewport) this.instance).getHigh();
        }

        public Builder setHigh(LatLng value) {
            copyOnWrite();
            ((Viewport) this.instance).setHigh(value);
            return this;
        }

        public Builder setHigh(LatLng.Builder builderForValue) {
            copyOnWrite();
            ((Viewport) this.instance).setHigh((LatLng) builderForValue.build());
            return this;
        }

        public Builder mergeHigh(LatLng value) {
            copyOnWrite();
            ((Viewport) this.instance).mergeHigh(value);
            return this;
        }

        public Builder clearHigh() {
            copyOnWrite();
            ((Viewport) this.instance).clearHigh();
            return this;
        }
    }

    /* renamed from: com.google.geo.type.Viewport$1 */
    static /* synthetic */ class C08721 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f241xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f241xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f241xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f241xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f241xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f241xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f241xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f241xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C08721.f241xa1df5c61[method.ordinal()]) {
            case 1:
                return new Viewport();
            case 2:
                return new Builder((C08721) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"low_", "high_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Viewport> parser = PARSER;
                if (parser == null) {
                    synchronized (Viewport.class) {
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
        Viewport defaultInstance = new Viewport();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Viewport.class, defaultInstance);
    }

    public static Viewport getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Viewport> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
