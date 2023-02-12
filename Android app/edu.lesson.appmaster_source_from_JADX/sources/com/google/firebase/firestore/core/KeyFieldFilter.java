package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.p002v1.Value;

public class KeyFieldFilter extends FieldFilter {
    private final DocumentKey key = DocumentKey.fromName(getValue().getReferenceValue());

    KeyFieldFilter(FieldPath field, FieldFilter.Operator operator, Value value) {
        super(field, operator, value);
        Assert.hardAssert(Values.isReferenceValue(value), "KeyFieldFilter expects a ReferenceValue", new Object[0]);
    }

    public boolean matches(Document doc) {
        return matchesComparison(doc.getKey().compareTo(this.key));
    }
}
