package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.p002v1.Value;

public class OrderBy {
    private final Direction direction;
    final FieldPath field;

    public enum Direction {
        ASCENDING(1),
        DESCENDING(-1);
        
        private final int comparisonModifier;

        private Direction(int comparisonModifier2) {
            this.comparisonModifier = comparisonModifier2;
        }

        /* access modifiers changed from: package-private */
        public int getComparisonModifier() {
            return this.comparisonModifier;
        }
    }

    public static OrderBy getInstance(Direction direction2, FieldPath path) {
        return new OrderBy(direction2, path);
    }

    public Direction getDirection() {
        return this.direction;
    }

    public FieldPath getField() {
        return this.field;
    }

    private OrderBy(Direction direction2, FieldPath field2) {
        this.direction = direction2;
        this.field = field2;
    }

    /* access modifiers changed from: package-private */
    public int compare(Document d1, Document d2) {
        if (this.field.equals(FieldPath.KEY_PATH)) {
            return this.direction.getComparisonModifier() * d1.getKey().compareTo(d2.getKey());
        }
        Value v1 = d1.getField(this.field);
        Value v2 = d2.getField(this.field);
        Assert.hardAssert((v1 == null || v2 == null) ? false : true, "Trying to compare documents on fields that don't exist.", new Object[0]);
        return this.direction.getComparisonModifier() * Values.compare(v1, v2);
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof OrderBy)) {
            return false;
        }
        OrderBy other = (OrderBy) o;
        if (this.direction != other.direction || !this.field.equals(other.field)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((29 * 31) + this.direction.hashCode()) * 31) + this.field.hashCode();
    }

    public String toString() {
        return (this.direction == Direction.ASCENDING ? "" : "-") + this.field.canonicalString();
    }
}
