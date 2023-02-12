package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.p002v1.ArrayValue;
import com.google.firestore.p002v1.Value;
import java.util.Collections;
import java.util.List;

public abstract class ArrayTransformOperation implements TransformOperation {
    private final List<Value> elements;

    /* access modifiers changed from: protected */
    public abstract Value apply(Value value);

    ArrayTransformOperation(List<Value> elements2) {
        this.elements = Collections.unmodifiableList(elements2);
    }

    public List<Value> getElements() {
        return this.elements;
    }

    public Value applyToLocalView(Value previousValue, Timestamp localWriteTime) {
        return apply(previousValue);
    }

    public Value applyToRemoteDocument(Value previousValue, Value transformResult) {
        return apply(previousValue);
    }

    public Value computeBaseValue(Value currentValue) {
        return null;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.elements.equals(((ArrayTransformOperation) o).elements);
    }

    public int hashCode() {
        return (getClass().hashCode() * 31) + this.elements.hashCode();
    }

    static ArrayValue.Builder coercedFieldValuesArray(Value value) {
        if (Values.isArray(value)) {
            return (ArrayValue.Builder) value.getArrayValue().toBuilder();
        }
        return ArrayValue.newBuilder();
    }

    public static class Union extends ArrayTransformOperation {
        public Union(List<Value> elements) {
            super(elements);
        }

        /* access modifiers changed from: protected */
        public Value apply(Value previousValue) {
            ArrayValue.Builder result = coercedFieldValuesArray(previousValue);
            for (Value unionElement : getElements()) {
                if (!Values.contains(result, unionElement)) {
                    result.addValues(unionElement);
                }
            }
            return (Value) Value.newBuilder().setArrayValue(result).build();
        }
    }

    public static class Remove extends ArrayTransformOperation {
        public Remove(List<Value> elements) {
            super(elements);
        }

        /* access modifiers changed from: protected */
        public Value apply(Value previousValue) {
            ArrayValue.Builder result = coercedFieldValuesArray(previousValue);
            for (Value removeElement : getElements()) {
                int i = 0;
                while (i < result.getValuesCount()) {
                    if (Values.equals(result.getValues(i), removeElement)) {
                        result.removeValues(i);
                    } else {
                        i++;
                    }
                }
            }
            return (Value) Value.newBuilder().setArrayValue(result).build();
        }
    }
}
