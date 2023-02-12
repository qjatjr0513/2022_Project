package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class Query {
    private static final OrderBy KEY_ORDERING_ASC = OrderBy.getInstance(OrderBy.Direction.ASCENDING, FieldPath.KEY_PATH);
    private static final OrderBy KEY_ORDERING_DESC = OrderBy.getInstance(OrderBy.Direction.DESCENDING, FieldPath.KEY_PATH);
    private final String collectionGroup;
    private final Bound endAt;
    private final List<OrderBy> explicitSortOrder;
    private final List<Filter> filters;
    private final long limit;
    private final LimitType limitType;
    private List<OrderBy> memoizedOrderBy;
    private Target memoizedTarget;
    private final ResourcePath path;
    private final Bound startAt;

    public enum LimitType {
        LIMIT_TO_FIRST,
        LIMIT_TO_LAST
    }

    public static Query atPath(ResourcePath path2) {
        return new Query(path2, (String) null);
    }

    public Query(ResourcePath path2, String collectionGroup2, List<Filter> filters2, List<OrderBy> explicitSortOrder2, long limit2, LimitType limitType2, Bound startAt2, Bound endAt2) {
        this.path = path2;
        this.collectionGroup = collectionGroup2;
        this.explicitSortOrder = explicitSortOrder2;
        this.filters = filters2;
        this.limit = limit2;
        this.limitType = limitType2;
        this.startAt = startAt2;
        this.endAt = endAt2;
    }

    public Query(ResourcePath path2, String collectionGroup2) {
        this(path2, collectionGroup2, Collections.emptyList(), Collections.emptyList(), -1, LimitType.LIMIT_TO_FIRST, (Bound) null, (Bound) null);
    }

    public ResourcePath getPath() {
        return this.path;
    }

    public String getCollectionGroup() {
        return this.collectionGroup;
    }

    public boolean isDocumentQuery() {
        return DocumentKey.isDocumentKey(this.path) && this.collectionGroup == null && this.filters.isEmpty();
    }

    public boolean isCollectionGroupQuery() {
        return this.collectionGroup != null;
    }

    public boolean matchesAllDocuments() {
        if (this.filters.isEmpty() && this.limit == -1 && this.startAt == null && this.endAt == null) {
            if (!getExplicitOrderBy().isEmpty()) {
                return getExplicitOrderBy().size() == 1 && getFirstOrderByField().isKeyField();
            }
            return true;
        }
    }

    public List<Filter> getFilters() {
        return this.filters;
    }

    public long getLimitToFirst() {
        Assert.hardAssert(hasLimitToFirst(), "Called getLimitToFirst when no limit was set", new Object[0]);
        return this.limit;
    }

    public boolean hasLimitToFirst() {
        return this.limitType == LimitType.LIMIT_TO_FIRST && this.limit != -1;
    }

    public long getLimitToLast() {
        Assert.hardAssert(hasLimitToLast(), "Called getLimitToLast when no limit was set", new Object[0]);
        return this.limit;
    }

    public boolean hasLimitToLast() {
        return this.limitType == LimitType.LIMIT_TO_LAST && this.limit != -1;
    }

    public LimitType getLimitType() {
        Assert.hardAssert(hasLimitToLast() || hasLimitToFirst(), "Called getLimitType when no limit was set", new Object[0]);
        return this.limitType;
    }

    public Bound getStartAt() {
        return this.startAt;
    }

    public Bound getEndAt() {
        return this.endAt;
    }

    public FieldPath getFirstOrderByField() {
        if (this.explicitSortOrder.isEmpty()) {
            return null;
        }
        return this.explicitSortOrder.get(0).getField();
    }

    public FieldPath inequalityField() {
        for (Filter filter : this.filters) {
            FieldPath result = filter.getFirstInequalityField();
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public Query filter(Filter filter) {
        boolean z = true;
        Assert.hardAssert(!isDocumentQuery(), "No filter is allowed for document query", new Object[0]);
        FieldPath newInequalityField = filter.getFirstInequalityField();
        FieldPath queryInequalityField = inequalityField();
        Assert.hardAssert(queryInequalityField == null || newInequalityField == null || queryInequalityField.equals(newInequalityField), "Query must only have one inequality field", new Object[0]);
        if (!this.explicitSortOrder.isEmpty() && newInequalityField != null && !this.explicitSortOrder.get(0).field.equals(newInequalityField)) {
            z = false;
        }
        Assert.hardAssert(z, "First orderBy must match inequality field", new Object[0]);
        List<Filter> updatedFilter = new ArrayList<>(this.filters);
        updatedFilter.add(filter);
        return new Query(this.path, this.collectionGroup, updatedFilter, this.explicitSortOrder, this.limit, this.limitType, this.startAt, this.endAt);
    }

    public Query orderBy(OrderBy order) {
        FieldPath inequality;
        Assert.hardAssert(!isDocumentQuery(), "No ordering is allowed for document query", new Object[0]);
        if (!this.explicitSortOrder.isEmpty() || (inequality = inequalityField()) == null || inequality.equals(order.field)) {
            List<OrderBy> updatedSortOrder = new ArrayList<>(this.explicitSortOrder);
            updatedSortOrder.add(order);
            return new Query(this.path, this.collectionGroup, this.filters, updatedSortOrder, this.limit, this.limitType, this.startAt, this.endAt);
        }
        throw Assert.fail("First orderBy must match inequality field", new Object[0]);
    }

    public Query limitToFirst(long limit2) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, limit2, LimitType.LIMIT_TO_FIRST, this.startAt, this.endAt);
    }

    public Query limitToLast(long limit2) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, limit2, LimitType.LIMIT_TO_LAST, this.startAt, this.endAt);
    }

    public Query startAt(Bound bound) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, this.limit, this.limitType, bound, this.endAt);
    }

    public Query endAt(Bound bound) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, this.limit, this.limitType, this.startAt, bound);
    }

    public Query asCollectionQueryAtPath(ResourcePath path2) {
        return new Query(path2, (String) null, this.filters, this.explicitSortOrder, this.limit, this.limitType, this.startAt, this.endAt);
    }

    public List<OrderBy> getExplicitOrderBy() {
        return this.explicitSortOrder;
    }

    public List<OrderBy> getOrderBy() {
        OrderBy.Direction lastDirection;
        if (this.memoizedOrderBy == null) {
            FieldPath inequalityField = inequalityField();
            FieldPath firstOrderByField = getFirstOrderByField();
            if (inequalityField == null || firstOrderByField != null) {
                List<OrderBy> res = new ArrayList<>();
                boolean foundKeyOrdering = false;
                for (OrderBy explicit : this.explicitSortOrder) {
                    res.add(explicit);
                    if (explicit.getField().equals(FieldPath.KEY_PATH)) {
                        foundKeyOrdering = true;
                    }
                }
                if (!foundKeyOrdering) {
                    if (this.explicitSortOrder.size() > 0) {
                        List<OrderBy> list = this.explicitSortOrder;
                        lastDirection = list.get(list.size() - 1).getDirection();
                    } else {
                        lastDirection = OrderBy.Direction.ASCENDING;
                    }
                    res.add(lastDirection.equals(OrderBy.Direction.ASCENDING) ? KEY_ORDERING_ASC : KEY_ORDERING_DESC);
                }
                this.memoizedOrderBy = res;
            } else if (inequalityField.isKeyField()) {
                this.memoizedOrderBy = Collections.singletonList(KEY_ORDERING_ASC);
            } else {
                this.memoizedOrderBy = Arrays.asList(new OrderBy[]{OrderBy.getInstance(OrderBy.Direction.ASCENDING, inequalityField), KEY_ORDERING_ASC});
            }
        }
        return this.memoizedOrderBy;
    }

    private boolean matchesPathAndCollectionGroup(Document doc) {
        ResourcePath docPath = doc.getKey().getPath();
        if (this.collectionGroup != null) {
            if (!doc.getKey().hasCollectionId(this.collectionGroup) || !this.path.isPrefixOf(docPath)) {
                return false;
            }
            return true;
        } else if (DocumentKey.isDocumentKey(this.path)) {
            return this.path.equals(docPath);
        } else {
            if (!this.path.isPrefixOf(docPath) || this.path.length() != docPath.length() - 1) {
                return false;
            }
            return true;
        }
    }

    private boolean matchesFilters(Document doc) {
        for (Filter filter : this.filters) {
            if (!filter.matches(doc)) {
                return false;
            }
        }
        return true;
    }

    private boolean matchesOrderBy(Document doc) {
        for (OrderBy order : this.explicitSortOrder) {
            if (!order.getField().equals(FieldPath.KEY_PATH) && doc.getField(order.field) == null) {
                return false;
            }
        }
        return true;
    }

    private boolean matchesBounds(Document doc) {
        Bound bound = this.startAt;
        if (bound != null && !bound.sortsBeforeDocument(getOrderBy(), doc)) {
            return false;
        }
        Bound bound2 = this.endAt;
        if (bound2 == null || bound2.sortsAfterDocument(getOrderBy(), doc)) {
            return true;
        }
        return false;
    }

    public boolean matches(Document doc) {
        return doc.isFoundDocument() && matchesPathAndCollectionGroup(doc) && matchesOrderBy(doc) && matchesFilters(doc) && matchesBounds(doc);
    }

    public Comparator<Document> comparator() {
        return new QueryComparator(getOrderBy());
    }

    private static class QueryComparator implements Comparator<Document> {
        private final List<OrderBy> sortOrder;

        QueryComparator(List<OrderBy> order) {
            boolean hasKeyOrdering = false;
            for (OrderBy orderBy : order) {
                hasKeyOrdering = hasKeyOrdering || orderBy.getField().equals(FieldPath.KEY_PATH);
            }
            if (hasKeyOrdering) {
                this.sortOrder = order;
                return;
            }
            throw new IllegalArgumentException("QueryComparator needs to have a key ordering");
        }

        public int compare(Document doc1, Document doc2) {
            for (OrderBy order : this.sortOrder) {
                int comp = order.compare(doc1, doc2);
                if (comp != 0) {
                    return comp;
                }
            }
            return 0;
        }
    }

    public Target toTarget() {
        Bound newStartAt;
        Bound newEndAt;
        OrderBy.Direction dir;
        if (this.memoizedTarget == null) {
            if (this.limitType == LimitType.LIMIT_TO_FIRST) {
                this.memoizedTarget = new Target(getPath(), getCollectionGroup(), getFilters(), getOrderBy(), this.limit, getStartAt(), getEndAt());
            } else {
                ArrayList<OrderBy> newOrderBy = new ArrayList<>();
                for (OrderBy orderBy : getOrderBy()) {
                    if (orderBy.getDirection() == OrderBy.Direction.DESCENDING) {
                        dir = OrderBy.Direction.ASCENDING;
                    } else {
                        dir = OrderBy.Direction.DESCENDING;
                    }
                    newOrderBy.add(OrderBy.getInstance(dir, orderBy.getField()));
                }
                if (this.endAt != null) {
                    newStartAt = new Bound(this.endAt.getPosition(), !this.endAt.isInclusive());
                } else {
                    newStartAt = null;
                }
                if (this.startAt != null) {
                    newEndAt = new Bound(this.startAt.getPosition(), !this.startAt.isInclusive());
                } else {
                    newEndAt = null;
                }
                this.memoizedTarget = new Target(getPath(), getCollectionGroup(), getFilters(), newOrderBy, this.limit, newStartAt, newEndAt);
            }
        }
        return this.memoizedTarget;
    }

    public boolean containsCompositeFilters() {
        for (Filter filter : this.filters) {
            if (filter instanceof CompositeFilter) {
                return true;
            }
        }
        return false;
    }

    public String getCanonicalId() {
        return toTarget().getCanonicalId() + "|lt:" + this.limitType;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Query query = (Query) o;
        if (this.limitType != query.limitType) {
            return false;
        }
        return toTarget().equals(query.toTarget());
    }

    public int hashCode() {
        return (toTarget().hashCode() * 31) + this.limitType.hashCode();
    }

    public String toString() {
        return "Query(target=" + toTarget().toString() + ";limitType=" + this.limitType.toString() + ")";
    }
}
