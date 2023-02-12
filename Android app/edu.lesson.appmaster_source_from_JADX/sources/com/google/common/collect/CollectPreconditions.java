package com.google.common.collect;

import com.google.common.base.Preconditions;

final class CollectPreconditions {
    CollectPreconditions() {
    }

    static void checkEntryNotNull(Object key, Object value) {
        if (key == null) {
            String valueOf = String.valueOf(value);
            throw new NullPointerException(new StringBuilder(String.valueOf(valueOf).length() + 24).append("null key in entry: null=").append(valueOf).toString());
        } else if (value == null) {
            String valueOf2 = String.valueOf(key);
            throw new NullPointerException(new StringBuilder(String.valueOf(valueOf2).length() + 26).append("null value in entry: ").append(valueOf2).append("=null").toString());
        }
    }

    static int checkNonnegative(int value, String name) {
        if (value >= 0) {
            return value;
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(name).length() + 40).append(name).append(" cannot be negative but was: ").append(value).toString());
    }

    static long checkNonnegative(long value, String name) {
        if (value >= 0) {
            return value;
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(name).length() + 49).append(name).append(" cannot be negative but was: ").append(value).toString());
    }

    static void checkPositive(int value, String name) {
        if (value <= 0) {
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(name).length() + 38).append(name).append(" must be positive but was: ").append(value).toString());
        }
    }

    static void checkRemove(boolean canRemove) {
        Preconditions.checkState(canRemove, "no calls to next() since the last call to remove()");
    }
}
