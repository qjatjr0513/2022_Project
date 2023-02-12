package com.google.firebase.firestore.util;

import com.google.firebase.firestore.core.CompositeFilter;
import com.google.firebase.firestore.core.Filter;
import java.util.Collections;
import java.util.List;

public class LogicUtils {
    public static List<Filter> DnfTransform(CompositeFilter filter) {
        if (filter.getFilters().isEmpty()) {
            return Collections.emptyList();
        }
        return Collections.singletonList(filter);
    }
}
