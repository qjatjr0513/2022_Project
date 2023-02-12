package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.p002v1.Value;
import java.util.List;

public final class Bound {
    private final boolean inclusive;
    private final List<Value> position;

    public Bound(List<Value> position2, boolean inclusive2) {
        this.position = position2;
        this.inclusive = inclusive2;
    }

    public List<Value> getPosition() {
        return this.position;
    }

    public boolean isInclusive() {
        return this.inclusive;
    }

    public String positionString() {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (Value indexComponent : this.position) {
            if (!first) {
                builder.append(",");
            }
            first = false;
            builder.append(Values.canonicalId(indexComponent));
        }
        return builder.toString();
    }

    public boolean sortsBeforeDocument(List<OrderBy> orderBy, Document document) {
        int comparison = compareToDocument(orderBy, document);
        if (this.inclusive) {
            if (comparison <= 0) {
                return true;
            }
        } else if (comparison < 0) {
            return true;
        }
        return false;
    }

    public boolean sortsAfterDocument(List<OrderBy> orderBy, Document document) {
        int comparison = compareToDocument(orderBy, document);
        if (this.inclusive) {
            if (comparison >= 0) {
                return true;
            }
        } else if (comparison > 0) {
            return true;
        }
        return false;
    }

    private int compareToDocument(List<OrderBy> orderBy, Document document) {
        Assert.hardAssert(this.position.size() <= orderBy.size(), "Bound has more components than query's orderBy", new Object[0]);
        int comparison = 0;
        for (int i = 0; i < this.position.size(); i++) {
            OrderBy orderByComponent = orderBy.get(i);
            Value component = this.position.get(i);
            if (orderByComponent.field.equals(FieldPath.KEY_PATH)) {
                Assert.hardAssert(Values.isReferenceValue(component), "Bound has a non-key value where the key path is being used %s", component);
                comparison = DocumentKey.fromName(component.getReferenceValue()).compareTo(document.getKey());
            } else {
                Value docValue = document.getField(orderByComponent.getField());
                Assert.hardAssert(docValue != null, "Field should exist since document matched the orderBy already.", new Object[0]);
                comparison = Values.compare(component, docValue);
            }
            if (orderByComponent.getDirection().equals(OrderBy.Direction.DESCENDING)) {
                comparison *= -1;
            }
            if (comparison != 0) {
                break;
            }
        }
        return comparison;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bound bound = (Bound) o;
        if (this.inclusive != bound.inclusive || !this.position.equals(bound.position)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((int) this.inclusive) * true) + this.position.hashCode();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Bound(inclusive=");
        builder.append(this.inclusive);
        builder.append(", position=");
        for (int i = 0; i < this.position.size(); i++) {
            if (i > 0) {
                builder.append(" and ");
            }
            builder.append(Values.canonicalId(this.position.get(i)));
        }
        builder.append(")");
        return builder.toString();
    }
}
