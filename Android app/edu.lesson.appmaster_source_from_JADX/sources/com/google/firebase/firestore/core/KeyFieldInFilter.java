package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.p002v1.Value;
import java.util.ArrayList;
import java.util.List;

public class KeyFieldInFilter extends FieldFilter {
    private final List<DocumentKey> keys;

    KeyFieldInFilter(FieldPath field, Value value) {
        super(field, FieldFilter.Operator.IN, value);
        ArrayList arrayList = new ArrayList();
        this.keys = arrayList;
        arrayList.addAll(extractDocumentKeysFromArrayValue(FieldFilter.Operator.IN, value));
    }

    public boolean matches(Document doc) {
        return this.keys.contains(doc.getKey());
    }

    static List<DocumentKey> extractDocumentKeysFromArrayValue(FieldFilter.Operator operator, Value value) {
        Assert.hardAssert(operator == FieldFilter.Operator.IN || operator == FieldFilter.Operator.NOT_IN, "extractDocumentKeysFromArrayValue requires IN or NOT_IN operators", new Object[0]);
        Assert.hardAssert(Values.isArray(value), "KeyFieldInFilter/KeyFieldNotInFilter expects an ArrayValue", new Object[0]);
        List<DocumentKey> keys2 = new ArrayList<>();
        for (Value element : value.getArrayValue().getValuesList()) {
            Assert.hardAssert(Values.isReferenceValue(element), "Comparing on key with " + operator.toString() + ", but an array value was not a ReferenceValue", new Object[0]);
            keys2.add(DocumentKey.fromName(element.getReferenceValue()));
        }
        return keys2;
    }
}
