package com.google.api;

import com.google.protobuf.Internal;

public enum LaunchStage implements Internal.EnumLite {
    LAUNCH_STAGE_UNSPECIFIED(0),
    EARLY_ACCESS(1),
    ALPHA(2),
    BETA(3),
    GA(4),
    DEPRECATED(5),
    UNRECOGNIZED(-1);
    
    public static final int ALPHA_VALUE = 2;
    public static final int BETA_VALUE = 3;
    public static final int DEPRECATED_VALUE = 5;
    public static final int EARLY_ACCESS_VALUE = 1;
    public static final int GA_VALUE = 4;
    public static final int LAUNCH_STAGE_UNSPECIFIED_VALUE = 0;
    private static final Internal.EnumLiteMap<LaunchStage> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new Internal.EnumLiteMap<LaunchStage>() {
            public LaunchStage findValueByNumber(int number) {
                return LaunchStage.forNumber(number);
            }
        };
    }

    public final int getNumber() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    @Deprecated
    public static LaunchStage valueOf(int value2) {
        return forNumber(value2);
    }

    public static LaunchStage forNumber(int value2) {
        switch (value2) {
            case 0:
                return LAUNCH_STAGE_UNSPECIFIED;
            case 1:
                return EARLY_ACCESS;
            case 2:
                return ALPHA;
            case 3:
                return BETA;
            case 4:
                return GA;
            case 5:
                return DEPRECATED;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<LaunchStage> internalGetValueMap() {
        return internalValueMap;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return LaunchStageVerifier.INSTANCE;
    }

    private static final class LaunchStageVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = null;

        private LaunchStageVerifier() {
        }

        static {
            INSTANCE = new LaunchStageVerifier();
        }

        public boolean isInRange(int number) {
            return LaunchStage.forNumber(number) != null;
        }
    }

    private LaunchStage(int value2) {
        this.value = value2;
    }
}
