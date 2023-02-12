package com.google.firebase.firestore;

import com.google.firebase.firestore.core.FieldFilter;
import com.google.firestore.p002v1.StructuredQuery;
import java.util.Arrays;
import java.util.List;

public class Filter {

    static class UnaryFilter extends Filter {
        private final FieldPath field;
        private final FieldFilter.Operator operator;
        private final Object value;

        public UnaryFilter(FieldPath field2, FieldFilter.Operator operator2, Object value2) {
            this.field = field2;
            this.operator = operator2;
            this.value = value2;
        }

        public FieldPath getField() {
            return this.field;
        }

        public FieldFilter.Operator getOperator() {
            return this.operator;
        }

        public Object getValue() {
            return this.value;
        }
    }

    static class CompositeFilter extends Filter {
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
    }

    public static Filter equalTo(String field, Object value) {
        return equalTo(FieldPath.fromDotSeparatedPath(field), value);
    }

    public static Filter equalTo(FieldPath fieldPath, Object value) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.EQUAL, value);
    }

    public static Filter notEqualTo(String field, Object value) {
        return notEqualTo(FieldPath.fromDotSeparatedPath(field), value);
    }

    public static Filter notEqualTo(FieldPath fieldPath, Object value) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.NOT_EQUAL, value);
    }

    public static Filter greaterThan(String field, Object value) {
        return greaterThan(FieldPath.fromDotSeparatedPath(field), value);
    }

    public static Filter greaterThan(FieldPath fieldPath, Object value) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.GREATER_THAN, value);
    }

    public static Filter greaterThanOrEqualTo(String field, Object value) {
        return greaterThanOrEqualTo(FieldPath.fromDotSeparatedPath(field), value);
    }

    public static Filter greaterThanOrEqualTo(FieldPath fieldPath, Object value) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.GREATER_THAN_OR_EQUAL, value);
    }

    public static Filter lessThan(String field, Object value) {
        return lessThan(FieldPath.fromDotSeparatedPath(field), value);
    }

    public static Filter lessThan(FieldPath fieldPath, Object value) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.LESS_THAN, value);
    }

    public static Filter lessThanOrEqualTo(String field, Object value) {
        return lessThanOrEqualTo(FieldPath.fromDotSeparatedPath(field), value);
    }

    public static Filter lessThanOrEqualTo(FieldPath fieldPath, Object value) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.LESS_THAN_OR_EQUAL, value);
    }

    public static Filter arrayContains(String field, Object value) {
        return arrayContains(FieldPath.fromDotSeparatedPath(field), value);
    }

    public static Filter arrayContains(FieldPath fieldPath, Object value) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.ARRAY_CONTAINS, value);
    }

    public static Filter arrayContainsAny(String field, Object value) {
        return arrayContainsAny(FieldPath.fromDotSeparatedPath(field), value);
    }

    public static Filter arrayContainsAny(FieldPath fieldPath, Object value) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.ARRAY_CONTAINS_ANY, value);
    }

    public static Filter inArray(String field, Object value) {
        return inArray(FieldPath.fromDotSeparatedPath(field), value);
    }

    public static Filter inArray(FieldPath fieldPath, Object value) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.IN, value);
    }

    public static Filter notInArray(String field, Object value) {
        return notInArray(FieldPath.fromDotSeparatedPath(field), value);
    }

    public static Filter notInArray(FieldPath fieldPath, Object value) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.NOT_IN, value);
    }

    /* renamed from: or */
    public static Filter m198or(Filter... filters) {
        return new CompositeFilter(Arrays.asList(filters), StructuredQuery.CompositeFilter.Operator.OPERATOR_UNSPECIFIED);
    }

    public static Filter and(Filter... filters) {
        return new CompositeFilter(Arrays.asList(filters), StructuredQuery.CompositeFilter.Operator.AND);
    }
}
