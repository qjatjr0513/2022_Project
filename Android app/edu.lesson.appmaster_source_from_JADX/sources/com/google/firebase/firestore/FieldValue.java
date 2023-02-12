package com.google.firebase.firestore;

import java.util.Arrays;
import java.util.List;

public abstract class FieldValue {
    private static final DeleteFieldValue DELETE_INSTANCE = new DeleteFieldValue();
    private static final ServerTimestampFieldValue SERVER_TIMESTAMP_INSTANCE = new ServerTimestampFieldValue();

    /* access modifiers changed from: package-private */
    public abstract String getMethodName();

    FieldValue() {
    }

    static class DeleteFieldValue extends FieldValue {
        DeleteFieldValue() {
        }

        /* access modifiers changed from: package-private */
        public String getMethodName() {
            return "FieldValue.delete";
        }
    }

    static class ServerTimestampFieldValue extends FieldValue {
        ServerTimestampFieldValue() {
        }

        /* access modifiers changed from: package-private */
        public String getMethodName() {
            return "FieldValue.serverTimestamp";
        }
    }

    static class ArrayUnionFieldValue extends FieldValue {
        private final List<Object> elements;

        ArrayUnionFieldValue(List<Object> elements2) {
            this.elements = elements2;
        }

        /* access modifiers changed from: package-private */
        public String getMethodName() {
            return "FieldValue.arrayUnion";
        }

        /* access modifiers changed from: package-private */
        public List<Object> getElements() {
            return this.elements;
        }
    }

    static class ArrayRemoveFieldValue extends FieldValue {
        private final List<Object> elements;

        ArrayRemoveFieldValue(List<Object> elements2) {
            this.elements = elements2;
        }

        /* access modifiers changed from: package-private */
        public String getMethodName() {
            return "FieldValue.arrayRemove";
        }

        /* access modifiers changed from: package-private */
        public List<Object> getElements() {
            return this.elements;
        }
    }

    static class NumericIncrementFieldValue extends FieldValue {
        private final Number operand;

        NumericIncrementFieldValue(Number operand2) {
            this.operand = operand2;
        }

        /* access modifiers changed from: package-private */
        public String getMethodName() {
            return "FieldValue.increment";
        }

        /* access modifiers changed from: package-private */
        public Number getOperand() {
            return this.operand;
        }
    }

    public static FieldValue delete() {
        return DELETE_INSTANCE;
    }

    public static FieldValue serverTimestamp() {
        return SERVER_TIMESTAMP_INSTANCE;
    }

    public static FieldValue arrayUnion(Object... elements) {
        return new ArrayUnionFieldValue(Arrays.asList(elements));
    }

    public static FieldValue arrayRemove(Object... elements) {
        return new ArrayRemoveFieldValue(Arrays.asList(elements));
    }

    public static FieldValue increment(long l) {
        return new NumericIncrementFieldValue(Long.valueOf(l));
    }

    public static FieldValue increment(double l) {
        return new NumericIncrementFieldValue(Double.valueOf(l));
    }
}
