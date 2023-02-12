package com.google.firebase.firestore.model;

import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TargetIndexMatcher {
    private final String collectionId;
    private final List<FieldFilter> equalityFilters;
    private FieldFilter inequalityFilter;
    private final List<OrderBy> orderBys;

    public TargetIndexMatcher(Target target) {
        String str;
        if (target.getCollectionGroup() != null) {
            str = target.getCollectionGroup();
        } else {
            str = target.getPath().getLastSegment();
        }
        this.collectionId = str;
        this.orderBys = target.getOrderBy();
        this.inequalityFilter = null;
        this.equalityFilters = new ArrayList();
        Iterator<Filter> it = target.getFilters().iterator();
        while (it.hasNext()) {
            FieldFilter fieldFilter = (FieldFilter) it.next();
            if (fieldFilter.isInequality()) {
                FieldFilter fieldFilter2 = this.inequalityFilter;
                Assert.hardAssert(fieldFilter2 == null || fieldFilter2.getField().equals(fieldFilter.getField()), "Only a single inequality is supported", new Object[0]);
                this.inequalityFilter = fieldFilter;
            } else {
                this.equalityFilters.add(fieldFilter);
            }
        }
    }

    public boolean servedByIndex(FieldIndex index) {
        Assert.hardAssert(index.getCollectionGroup().equals(this.collectionId), "Collection IDs do not match", new Object[0]);
        FieldIndex.Segment arraySegment = index.getArraySegment();
        if (arraySegment != null && !hasMatchingEqualityFilter(arraySegment)) {
            return false;
        }
        Iterator<OrderBy> orderBys2 = this.orderBys.iterator();
        List<FieldIndex.Segment> segments = index.getDirectionalSegments();
        int segmentIndex = 0;
        while (segmentIndex < segments.size() && hasMatchingEqualityFilter(segments.get(segmentIndex))) {
            segmentIndex++;
        }
        if (segmentIndex == segments.size()) {
            return true;
        }
        if (this.inequalityFilter != null) {
            FieldIndex.Segment segment = segments.get(segmentIndex);
            if (!matchesFilter(this.inequalityFilter, segment) || !matchesOrderBy(orderBys2.next(), segment)) {
                return false;
            }
            segmentIndex++;
        }
        while (segmentIndex < segments.size()) {
            FieldIndex.Segment segment2 = segments.get(segmentIndex);
            if (!orderBys2.hasNext() || !matchesOrderBy(orderBys2.next(), segment2)) {
                return false;
            }
            segmentIndex++;
        }
        return true;
    }

    private boolean hasMatchingEqualityFilter(FieldIndex.Segment segment) {
        for (FieldFilter filter : this.equalityFilters) {
            if (matchesFilter(filter, segment)) {
                return true;
            }
        }
        return false;
    }

    private boolean matchesFilter(FieldFilter filter, FieldIndex.Segment segment) {
        if (filter == null || !filter.getField().equals(segment.getFieldPath())) {
            return false;
        }
        if (segment.getKind().equals(FieldIndex.Segment.Kind.CONTAINS) == (filter.getOperator().equals(FieldFilter.Operator.ARRAY_CONTAINS) || filter.getOperator().equals(FieldFilter.Operator.ARRAY_CONTAINS_ANY))) {
            return true;
        }
        return false;
    }

    private boolean matchesOrderBy(OrderBy orderBy, FieldIndex.Segment segment) {
        if (!orderBy.getField().equals(segment.getFieldPath())) {
            return false;
        }
        if ((!segment.getKind().equals(FieldIndex.Segment.Kind.ASCENDING) || !orderBy.getDirection().equals(OrderBy.Direction.ASCENDING)) && (!segment.getKind().equals(FieldIndex.Segment.Kind.DESCENDING) || !orderBy.getDirection().equals(OrderBy.Direction.DESCENDING))) {
            return false;
        }
        return true;
    }
}
