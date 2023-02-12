package com.google.type;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Fraction extends GeneratedMessageLite<Fraction, Builder> implements FractionOrBuilder {
    /* access modifiers changed from: private */
    public static final Fraction DEFAULT_INSTANCE;
    public static final int DENOMINATOR_FIELD_NUMBER = 2;
    public static final int NUMERATOR_FIELD_NUMBER = 1;
    private static volatile Parser<Fraction> PARSER;
    private long denominator_;
    private long numerator_;

    private Fraction() {
    }

    public long getNumerator() {
        return this.numerator_;
    }

    /* access modifiers changed from: private */
    public void setNumerator(long value) {
        this.numerator_ = value;
    }

    /* access modifiers changed from: private */
    public void clearNumerator() {
        this.numerator_ = 0;
    }

    public long getDenominator() {
        return this.denominator_;
    }

    /* access modifiers changed from: private */
    public void setDenominator(long value) {
        this.denominator_ = value;
    }

    /* access modifiers changed from: private */
    public void clearDenominator() {
        this.denominator_ = 0;
    }

    public static Fraction parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Fraction parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Fraction parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Fraction parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Fraction parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Fraction parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Fraction parseFrom(InputStream input) throws IOException {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Fraction parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Fraction parseDelimitedFrom(InputStream input) throws IOException {
        return (Fraction) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Fraction parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Fraction) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Fraction parseFrom(CodedInputStream input) throws IOException {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Fraction parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Fraction prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Fraction, Builder> implements FractionOrBuilder {
        /* synthetic */ Builder(C10781 x0) {
            this();
        }

        private Builder() {
            super(Fraction.DEFAULT_INSTANCE);
        }

        public long getNumerator() {
            return ((Fraction) this.instance).getNumerator();
        }

        public Builder setNumerator(long value) {
            copyOnWrite();
            ((Fraction) this.instance).setNumerator(value);
            return this;
        }

        public Builder clearNumerator() {
            copyOnWrite();
            ((Fraction) this.instance).clearNumerator();
            return this;
        }

        public long getDenominator() {
            return ((Fraction) this.instance).getDenominator();
        }

        public Builder setDenominator(long value) {
            copyOnWrite();
            ((Fraction) this.instance).setDenominator(value);
            return this;
        }

        public Builder clearDenominator() {
            copyOnWrite();
            ((Fraction) this.instance).clearDenominator();
            return this;
        }
    }

    /* renamed from: com.google.type.Fraction$1 */
    static /* synthetic */ class C10781 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f298xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f298xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f298xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f298xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f298xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f298xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f298xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f298xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C10781.f298xa1df5c61[method.ordinal()]) {
            case 1:
                return new Fraction();
            case 2:
                return new Builder((C10781) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0002", new Object[]{"numerator_", "denominator_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Fraction> parser = PARSER;
                if (parser == null) {
                    synchronized (Fraction.class) {
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
        Fraction defaultInstance = new Fraction();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Fraction.class, defaultInstance);
    }

    public static Fraction getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Fraction> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
