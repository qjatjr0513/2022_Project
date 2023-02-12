package com.google.firebase.database.snapshot;

import com.google.firebase.database.core.utilities.Utilities;

public class ChildKey implements Comparable<ChildKey> {
    private static final ChildKey INFO_CHILD_KEY = new ChildKey(".info");
    private static final ChildKey MAX_KEY = new ChildKey(MAX_KEY_NAME);
    public static final String MAX_KEY_NAME = "[MAX_KEY]";
    private static final ChildKey MIN_KEY = new ChildKey(MIN_KEY_NAME);
    public static final String MIN_KEY_NAME = "[MIN_NAME]";
    private static final ChildKey PRIORITY_CHILD_KEY = new ChildKey(".priority");
    /* access modifiers changed from: private */
    public final String key;

    public static ChildKey getMinName() {
        return MIN_KEY;
    }

    public static ChildKey getMaxName() {
        return MAX_KEY;
    }

    public static ChildKey getPriorityKey() {
        return PRIORITY_CHILD_KEY;
    }

    public static ChildKey getInfoKey() {
        return INFO_CHILD_KEY;
    }

    private ChildKey(String key2) {
        this.key = key2;
    }

    public String asString() {
        return this.key;
    }

    public boolean isPriorityChildName() {
        return equals(PRIORITY_CHILD_KEY);
    }

    /* access modifiers changed from: protected */
    public boolean isInt() {
        return false;
    }

    /* access modifiers changed from: protected */
    public int intValue() {
        return 0;
    }

    public int compareTo(ChildKey other) {
        if (this == other) {
            return 0;
        }
        if (this.key.equals(MIN_KEY_NAME) || other.key.equals(MAX_KEY_NAME)) {
            return -1;
        }
        if (other.key.equals(MIN_KEY_NAME) || this.key.equals(MAX_KEY_NAME)) {
            return 1;
        }
        if (isInt()) {
            if (!other.isInt()) {
                return -1;
            }
            int cmp = Utilities.compareInts(intValue(), other.intValue());
            return cmp == 0 ? Utilities.compareInts(this.key.length(), other.key.length()) : cmp;
        } else if (other.isInt()) {
            return 1;
        } else {
            return this.key.compareTo(other.key);
        }
    }

    public String toString() {
        return "ChildKey(\"" + this.key + "\")";
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ChildKey)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return this.key.equals(((ChildKey) obj).key);
    }

    public static ChildKey fromString(String key2) {
        Integer intValue = Utilities.tryParseInt(key2);
        if (intValue != null) {
            return new IntegerChildKey(key2, intValue.intValue());
        }
        if (key2.equals(".priority")) {
            return PRIORITY_CHILD_KEY;
        }
        Utilities.hardAssert(!key2.contains("/"));
        return new ChildKey(key2);
    }

    private static class IntegerChildKey extends ChildKey {
        private final int intValue;

        public /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return ChildKey.super.compareTo((ChildKey) obj);
        }

        IntegerChildKey(String name, int intValue2) {
            super(name);
            this.intValue = intValue2;
        }

        /* access modifiers changed from: protected */
        public boolean isInt() {
            return true;
        }

        /* access modifiers changed from: protected */
        public int intValue() {
            return this.intValue;
        }

        public String toString() {
            return "IntegerChildName(\"" + this.key + "\")";
        }
    }
}
