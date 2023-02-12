package com.google.cloud.datastore.core.number;

public final class NumberComparisonHelper {
    public static final double LONG_EXCLUSIVE_UPPER_BOUND_AS_DOUBLE = 9.223372036854776E18d;
    public static final double LONG_INCLUSIVE_LOWER_BOUND_AS_DOUBLE = -9.223372036854776E18d;
    public static final long MAX_SAFE_LONG = 9007199254740992L;
    public static final long MIN_SAFE_LONG = -9007199254740992L;

    public static int firestoreCompareDoubleWithLong(double doubleValue, long longValue) {
        if (Double.isNaN(doubleValue) || doubleValue < -9.223372036854776E18d) {
            return -1;
        }
        if (doubleValue >= 9.223372036854776E18d) {
            return 1;
        }
        int cmp = compareLongs((long) doubleValue, longValue);
        if (cmp != 0) {
            return cmp;
        }
        return firestoreCompareDoubles(doubleValue, (double) longValue);
    }

    public static int compareLongs(long leftLong, long rightLong) {
        if (leftLong < rightLong) {
            return -1;
        }
        if (leftLong > rightLong) {
            return 1;
        }
        return 0;
    }

    public static int firestoreCompareDoubles(double leftDouble, double rightDouble) {
        if (leftDouble < rightDouble) {
            return -1;
        }
        if (leftDouble > rightDouble) {
            return 1;
        }
        if (leftDouble == rightDouble) {
            return 0;
        }
        if (!Double.isNaN(rightDouble)) {
            return -1;
        }
        if (!Double.isNaN(leftDouble)) {
            return 1;
        }
        return 0;
    }

    private NumberComparisonHelper() {
    }
}
