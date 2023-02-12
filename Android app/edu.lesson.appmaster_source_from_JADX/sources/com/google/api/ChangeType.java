package com.google.api;

import com.google.protobuf.Internal;

public enum ChangeType implements Internal.EnumLite {
    CHANGE_TYPE_UNSPECIFIED(0),
    ADDED(1),
    REMOVED(2),
    MODIFIED(3),
    UNRECOGNIZED(-1);
    
    public static final int ADDED_VALUE = 1;
    public static final int CHANGE_TYPE_UNSPECIFIED_VALUE = 0;
    public static final int MODIFIED_VALUE = 3;
    public static final int REMOVED_VALUE = 2;
    private static final Internal.EnumLiteMap<ChangeType> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new Internal.EnumLiteMap<ChangeType>() {
            public ChangeType findValueByNumber(int number) {
                return ChangeType.forNumber(number);
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
    public static ChangeType valueOf(int value2) {
        return forNumber(value2);
    }

    public static ChangeType forNumber(int value2) {
        switch (value2) {
            case 0:
                return CHANGE_TYPE_UNSPECIFIED;
            case 1:
                return ADDED;
            case 2:
                return REMOVED;
            case 3:
                return MODIFIED;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<ChangeType> internalGetValueMap() {
        return internalValueMap;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return ChangeTypeVerifier.INSTANCE;
    }

    private static final class ChangeTypeVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = null;

        private ChangeTypeVerifier() {
        }

        static {
            INSTANCE = new ChangeTypeVerifier();
        }

        public boolean isInRange(int number) {
            return ChangeType.forNumber(number) != null;
        }
    }

    private ChangeType(int value2) {
        this.value = value2;
    }
}
