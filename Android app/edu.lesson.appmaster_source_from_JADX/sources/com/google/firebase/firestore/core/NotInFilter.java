package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.p002v1.Value;

public class NotInFilter extends FieldFilter {
    NotInFilter(FieldPath field, Value value) {
        super(field, FieldFilter.Operator.NOT_IN, value);
        Assert.hardAssert(Values.isArray(value), "NotInFilter expects an ArrayValue", new Object[0]);
    }

    public boolean matches(Document doc) {
        Value other;
        if (!Values.contains(getValue().getArrayValue(), Values.NULL_VALUE) && (other = doc.getField(getField())) != null && !Values.contains(getValue().getArrayValue(), other)) {
            return true;
        }
        return false;
    }
}
