package com.google.firebase.firestore.core;

import android.text.TextUtils;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.util.Function;
import com.google.firestore.p002v1.StructuredQuery;
import java.util.ArrayList;
import java.util.List;

public class CompositeFilter extends Filter {
    private final List<Filter> filters;
    private final StructuredQuery.CompositeFilter.Operator operator;

    public CompositeFilter(List<Filter> filters2, StructuredQuery.CompositeFilter.Operator operator2) {
        this.filters = filters2;
        this.operator = operator2;
    }

    public List<Filter> getFilters() {
        return this.filters;
    }

    public StructuredQuery.CompositeFilter.Operator getOperator() {
        return this.operator;
    }

    public List<FieldFilter> getFlattenedFilters() {
        List<FieldFilter> result = new ArrayList<>();
        for (Filter subfilter : this.filters) {
            result.addAll(subfilter.getFlattenedFilters());
        }
        return result;
    }

    public FieldPath getFirstInequalityField() {
        FieldFilter found = findFirstMatchingFilter(CompositeFilter$$ExternalSyntheticLambda0.INSTANCE);
        if (found != null) {
            return found.getField();
        }
        return null;
    }

    public boolean isConjunction() {
        return this.operator == StructuredQuery.CompositeFilter.Operator.AND;
    }

    public boolean isDisjunction() {
        return this.operator == StructuredQuery.CompositeFilter.Operator.OPERATOR_UNSPECIFIED;
    }

    public boolean isFlatConjunction() {
        if (this.operator != StructuredQuery.CompositeFilter.Operator.AND) {
            return false;
        }
        for (Filter filter : this.filters) {
            if (filter instanceof CompositeFilter) {
                return false;
            }
        }
        return true;
    }

    private FieldFilter findFirstMatchingFilter(Function<FieldFilter, Boolean> condition) {
        FieldFilter found;
        for (Filter filter : this.filters) {
            if ((filter instanceof FieldFilter) && condition.apply((FieldFilter) filter).booleanValue()) {
                return (FieldFilter) filter;
            }
            if ((filter instanceof CompositeFilter) && (found = ((CompositeFilter) filter).findFirstMatchingFilter(condition)) != null) {
                return found;
            }
        }
        return null;
    }

    public boolean matches(Document doc) {
        if (isConjunction()) {
            for (Filter filter : this.filters) {
                if (!filter.matches(doc)) {
                    return false;
                }
            }
            return true;
        }
        for (Filter filter2 : this.filters) {
            if (filter2.matches(doc)) {
                return true;
            }
        }
        return false;
    }

    public String getCanonicalId() {
        List<String> canonicalIds = new ArrayList<>();
        for (Filter filter : this.filters) {
            canonicalIds.add(filter.getCanonicalId());
        }
        StringBuilder builder = new StringBuilder();
        builder.append(isConjunction() ? "and(" : "or(");
        TextUtils.join(",", canonicalIds);
        builder.append(")");
        return builder.toString();
    }

    public String toString() {
        return getCanonicalId();
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof CompositeFilter)) {
            return false;
        }
        CompositeFilter other = (CompositeFilter) o;
        if (this.operator != other.operator || !this.filters.equals(other.filters)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((37 * 31) + this.operator.hashCode()) * 31) + this.filters.hashCode();
    }
}
