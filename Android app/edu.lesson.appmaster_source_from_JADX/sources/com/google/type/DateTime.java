package com.google.type;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Duration;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.type.TimeZone;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class DateTime extends GeneratedMessageLite<DateTime, Builder> implements DateTimeOrBuilder {
    public static final int DAY_FIELD_NUMBER = 3;
    /* access modifiers changed from: private */
    public static final DateTime DEFAULT_INSTANCE;
    public static final int HOURS_FIELD_NUMBER = 4;
    public static final int MINUTES_FIELD_NUMBER = 5;
    public static final int MONTH_FIELD_NUMBER = 2;
    public static final int NANOS_FIELD_NUMBER = 7;
    private static volatile Parser<DateTime> PARSER = null;
    public static final int SECONDS_FIELD_NUMBER = 6;
    public static final int TIME_ZONE_FIELD_NUMBER = 9;
    public static final int UTC_OFFSET_FIELD_NUMBER = 8;
    public static final int YEAR_FIELD_NUMBER = 1;
    private int day_;
    private int hours_;
    private int minutes_;
    private int month_;
    private int nanos_;
    private int seconds_;
    private int timeOffsetCase_ = 0;
    private Object timeOffset_;
    private int year_;

    private DateTime() {
    }

    public enum TimeOffsetCase {
        UTC_OFFSET(8),
        TIME_ZONE(9),
        TIMEOFFSET_NOT_SET(0);
        
        private final int value;

        private TimeOffsetCase(int value2) {
            this.value = value2;
        }

        @Deprecated
        public static TimeOffsetCase valueOf(int value2) {
            return forNumber(value2);
        }

        public static TimeOffsetCase forNumber(int value2) {
            switch (value2) {
                case 0:
                    return TIMEOFFSET_NOT_SET;
                case 8:
                    return UTC_OFFSET;
                case 9:
                    return TIME_ZONE;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    public TimeOffsetCase getTimeOffsetCase() {
        return TimeOffsetCase.forNumber(this.timeOffsetCase_);
    }

    /* access modifiers changed from: private */
    public void clearTimeOffset() {
        this.timeOffsetCase_ = 0;
        this.timeOffset_ = null;
    }

    public int getYear() {
        return this.year_;
    }

    /* access modifiers changed from: private */
    public void setYear(int value) {
        this.year_ = value;
    }

    /* access modifiers changed from: private */
    public void clearYear() {
        this.year_ = 0;
    }

    public int getMonth() {
        return this.month_;
    }

    /* access modifiers changed from: private */
    public void setMonth(int value) {
        this.month_ = value;
    }

    /* access modifiers changed from: private */
    public void clearMonth() {
        this.month_ = 0;
    }

    public int getDay() {
        return this.day_;
    }

    /* access modifiers changed from: private */
    public void setDay(int value) {
        this.day_ = value;
    }

    /* access modifiers changed from: private */
    public void clearDay() {
        this.day_ = 0;
    }

    public int getHours() {
        return this.hours_;
    }

    /* access modifiers changed from: private */
    public void setHours(int value) {
        this.hours_ = value;
    }

    /* access modifiers changed from: private */
    public void clearHours() {
        this.hours_ = 0;
    }

    public int getMinutes() {
        return this.minutes_;
    }

    /* access modifiers changed from: private */
    public void setMinutes(int value) {
        this.minutes_ = value;
    }

    /* access modifiers changed from: private */
    public void clearMinutes() {
        this.minutes_ = 0;
    }

    public int getSeconds() {
        return this.seconds_;
    }

    /* access modifiers changed from: private */
    public void setSeconds(int value) {
        this.seconds_ = value;
    }

    /* access modifiers changed from: private */
    public void clearSeconds() {
        this.seconds_ = 0;
    }

    public int getNanos() {
        return this.nanos_;
    }

    /* access modifiers changed from: private */
    public void setNanos(int value) {
        this.nanos_ = value;
    }

    /* access modifiers changed from: private */
    public void clearNanos() {
        this.nanos_ = 0;
    }

    public boolean hasUtcOffset() {
        return this.timeOffsetCase_ == 8;
    }

    public Duration getUtcOffset() {
        if (this.timeOffsetCase_ == 8) {
            return (Duration) this.timeOffset_;
        }
        return Duration.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setUtcOffset(Duration value) {
        value.getClass();
        this.timeOffset_ = value;
        this.timeOffsetCase_ = 8;
    }

    /* access modifiers changed from: private */
    public void mergeUtcOffset(Duration value) {
        value.getClass();
        if (this.timeOffsetCase_ != 8 || this.timeOffset_ == Duration.getDefaultInstance()) {
            this.timeOffset_ = value;
        } else {
            this.timeOffset_ = ((Duration.Builder) Duration.newBuilder((Duration) this.timeOffset_).mergeFrom(value)).buildPartial();
        }
        this.timeOffsetCase_ = 8;
    }

    /* access modifiers changed from: private */
    public void clearUtcOffset() {
        if (this.timeOffsetCase_ == 8) {
            this.timeOffsetCase_ = 0;
            this.timeOffset_ = null;
        }
    }

    public boolean hasTimeZone() {
        return this.timeOffsetCase_ == 9;
    }

    public TimeZone getTimeZone() {
        if (this.timeOffsetCase_ == 9) {
            return (TimeZone) this.timeOffset_;
        }
        return TimeZone.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setTimeZone(TimeZone value) {
        value.getClass();
        this.timeOffset_ = value;
        this.timeOffsetCase_ = 9;
    }

    /* access modifiers changed from: private */
    public void mergeTimeZone(TimeZone value) {
        value.getClass();
        if (this.timeOffsetCase_ != 9 || this.timeOffset_ == TimeZone.getDefaultInstance()) {
            this.timeOffset_ = value;
        } else {
            this.timeOffset_ = ((TimeZone.Builder) TimeZone.newBuilder((TimeZone) this.timeOffset_).mergeFrom(value)).buildPartial();
        }
        this.timeOffsetCase_ = 9;
    }

    /* access modifiers changed from: private */
    public void clearTimeZone() {
        if (this.timeOffsetCase_ == 9) {
            this.timeOffsetCase_ = 0;
            this.timeOffset_ = null;
        }
    }

    public static DateTime parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DateTime parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DateTime parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DateTime parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DateTime parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DateTime parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DateTime parseFrom(InputStream input) throws IOException {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DateTime parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DateTime parseDelimitedFrom(InputStream input) throws IOException {
        return (DateTime) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static DateTime parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DateTime) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DateTime parseFrom(CodedInputStream input) throws IOException {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DateTime parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(DateTime prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DateTime, Builder> implements DateTimeOrBuilder {
        /* synthetic */ Builder(C10751 x0) {
            this();
        }

        private Builder() {
            super(DateTime.DEFAULT_INSTANCE);
        }

        public TimeOffsetCase getTimeOffsetCase() {
            return ((DateTime) this.instance).getTimeOffsetCase();
        }

        public Builder clearTimeOffset() {
            copyOnWrite();
            ((DateTime) this.instance).clearTimeOffset();
            return this;
        }

        public int getYear() {
            return ((DateTime) this.instance).getYear();
        }

        public Builder setYear(int value) {
            copyOnWrite();
            ((DateTime) this.instance).setYear(value);
            return this;
        }

        public Builder clearYear() {
            copyOnWrite();
            ((DateTime) this.instance).clearYear();
            return this;
        }

        public int getMonth() {
            return ((DateTime) this.instance).getMonth();
        }

        public Builder setMonth(int value) {
            copyOnWrite();
            ((DateTime) this.instance).setMonth(value);
            return this;
        }

        public Builder clearMonth() {
            copyOnWrite();
            ((DateTime) this.instance).clearMonth();
            return this;
        }

        public int getDay() {
            return ((DateTime) this.instance).getDay();
        }

        public Builder setDay(int value) {
            copyOnWrite();
            ((DateTime) this.instance).setDay(value);
            return this;
        }

        public Builder clearDay() {
            copyOnWrite();
            ((DateTime) this.instance).clearDay();
            return this;
        }

        public int getHours() {
            return ((DateTime) this.instance).getHours();
        }

        public Builder setHours(int value) {
            copyOnWrite();
            ((DateTime) this.instance).setHours(value);
            return this;
        }

        public Builder clearHours() {
            copyOnWrite();
            ((DateTime) this.instance).clearHours();
            return this;
        }

        public int getMinutes() {
            return ((DateTime) this.instance).getMinutes();
        }

        public Builder setMinutes(int value) {
            copyOnWrite();
            ((DateTime) this.instance).setMinutes(value);
            return this;
        }

        public Builder clearMinutes() {
            copyOnWrite();
            ((DateTime) this.instance).clearMinutes();
            return this;
        }

        public int getSeconds() {
            return ((DateTime) this.instance).getSeconds();
        }

        public Builder setSeconds(int value) {
            copyOnWrite();
            ((DateTime) this.instance).setSeconds(value);
            return this;
        }

        public Builder clearSeconds() {
            copyOnWrite();
            ((DateTime) this.instance).clearSeconds();
            return this;
        }

        public int getNanos() {
            return ((DateTime) this.instance).getNanos();
        }

        public Builder setNanos(int value) {
            copyOnWrite();
            ((DateTime) this.instance).setNanos(value);
            return this;
        }

        public Builder clearNanos() {
            copyOnWrite();
            ((DateTime) this.instance).clearNanos();
            return this;
        }

        public boolean hasUtcOffset() {
            return ((DateTime) this.instance).hasUtcOffset();
        }

        public Duration getUtcOffset() {
            return ((DateTime) this.instance).getUtcOffset();
        }

        public Builder setUtcOffset(Duration value) {
            copyOnWrite();
            ((DateTime) this.instance).setUtcOffset(value);
            return this;
        }

        public Builder setUtcOffset(Duration.Builder builderForValue) {
            copyOnWrite();
            ((DateTime) this.instance).setUtcOffset((Duration) builderForValue.build());
            return this;
        }

        public Builder mergeUtcOffset(Duration value) {
            copyOnWrite();
            ((DateTime) this.instance).mergeUtcOffset(value);
            return this;
        }

        public Builder clearUtcOffset() {
            copyOnWrite();
            ((DateTime) this.instance).clearUtcOffset();
            return this;
        }

        public boolean hasTimeZone() {
            return ((DateTime) this.instance).hasTimeZone();
        }

        public TimeZone getTimeZone() {
            return ((DateTime) this.instance).getTimeZone();
        }

        public Builder setTimeZone(TimeZone value) {
            copyOnWrite();
            ((DateTime) this.instance).setTimeZone(value);
            return this;
        }

        public Builder setTimeZone(TimeZone.Builder builderForValue) {
            copyOnWrite();
            ((DateTime) this.instance).setTimeZone((TimeZone) builderForValue.build());
            return this;
        }

        public Builder mergeTimeZone(TimeZone value) {
            copyOnWrite();
            ((DateTime) this.instance).mergeTimeZone(value);
            return this;
        }

        public Builder clearTimeZone() {
            copyOnWrite();
            ((DateTime) this.instance).clearTimeZone();
            return this;
        }
    }

    /* renamed from: com.google.type.DateTime$1 */
    static /* synthetic */ class C10751 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f296xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f296xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f296xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f296xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f296xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f296xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f296xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f296xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C10751.f296xa1df5c61[method.ordinal()]) {
            case 1:
                return new DateTime();
            case 2:
                return new Builder((C10751) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\t\u0001\u0000\u0001\t\t\u0000\u0000\u0000\u0001\u0004\u0002\u0004\u0003\u0004\u0004\u0004\u0005\u0004\u0006\u0004\u0007\u0004\b<\u0000\t<\u0000", new Object[]{"timeOffset_", "timeOffsetCase_", "year_", "month_", "day_", "hours_", "minutes_", "seconds_", "nanos_", Duration.class, TimeZone.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<DateTime> parser = PARSER;
                if (parser == null) {
                    synchronized (DateTime.class) {
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
        DateTime defaultInstance = new DateTime();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(DateTime.class, defaultInstance);
    }

    public static DateTime getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DateTime> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
