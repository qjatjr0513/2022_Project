package com.google.firebase.database.core.view;

import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.filter.IndexedFilter;
import com.google.firebase.database.core.view.filter.LimitedFilter;
import com.google.firebase.database.core.view.filter.NodeFilter;
import com.google.firebase.database.core.view.filter.RangedFilter;
import com.google.firebase.database.snapshot.BooleanNode;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.DoubleNode;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.Index;
import com.google.firebase.database.snapshot.LongNode;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.NodeUtilities;
import com.google.firebase.database.snapshot.PriorityIndex;
import com.google.firebase.database.snapshot.PriorityUtilities;
import com.google.firebase.database.snapshot.StringNode;
import com.google.firebase.database.util.JsonMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class QueryParams {
    public static final QueryParams DEFAULT_PARAMS = new QueryParams();
    private static final String INDEX = "i";
    private static final String INDEX_END_NAME = "en";
    private static final String INDEX_END_VALUE = "ep";
    private static final String INDEX_START_NAME = "sn";
    private static final String INDEX_START_VALUE = "sp";
    private static final String LIMIT = "l";
    private static final String VIEW_FROM = "vf";
    private Index index = PriorityIndex.getInstance();
    private ChildKey indexEndName = null;
    private Node indexEndValue = null;
    private ChildKey indexStartName = null;
    private Node indexStartValue = null;
    private String jsonSerialization = null;
    private Integer limit;
    private ViewFrom viewFrom;

    private enum ViewFrom {
        LEFT,
        RIGHT
    }

    public boolean hasStart() {
        return this.indexStartValue != null;
    }

    public Node getIndexStartValue() {
        if (hasStart()) {
            return this.indexStartValue;
        }
        throw new IllegalArgumentException("Cannot get index start value if start has not been set");
    }

    public ChildKey getIndexStartName() {
        if (hasStart()) {
            ChildKey childKey = this.indexStartName;
            if (childKey != null) {
                return childKey;
            }
            return ChildKey.getMinName();
        }
        throw new IllegalArgumentException("Cannot get index start name if start has not been set");
    }

    public boolean hasEnd() {
        return this.indexEndValue != null;
    }

    public Node getIndexEndValue() {
        if (hasEnd()) {
            return this.indexEndValue;
        }
        throw new IllegalArgumentException("Cannot get index end value if start has not been set");
    }

    public ChildKey getIndexEndName() {
        if (hasEnd()) {
            ChildKey childKey = this.indexEndName;
            if (childKey != null) {
                return childKey;
            }
            return ChildKey.getMaxName();
        }
        throw new IllegalArgumentException("Cannot get index end name if start has not been set");
    }

    public boolean hasLimit() {
        return this.limit != null;
    }

    public boolean hasAnchoredLimit() {
        return hasLimit() && this.viewFrom != null;
    }

    public int getLimit() {
        if (hasLimit()) {
            return this.limit.intValue();
        }
        throw new IllegalArgumentException("Cannot get limit if limit has not been set");
    }

    public Index getIndex() {
        return this.index;
    }

    private QueryParams copy() {
        QueryParams params = new QueryParams();
        params.limit = this.limit;
        params.indexStartValue = this.indexStartValue;
        params.indexStartName = this.indexStartName;
        params.indexEndValue = this.indexEndValue;
        params.indexEndName = this.indexEndName;
        params.viewFrom = this.viewFrom;
        params.index = this.index;
        return params;
    }

    public QueryParams limitToFirst(int limit2) {
        QueryParams copy = copy();
        copy.limit = Integer.valueOf(limit2);
        copy.viewFrom = ViewFrom.LEFT;
        return copy;
    }

    public QueryParams limitToLast(int limit2) {
        QueryParams copy = copy();
        copy.limit = Integer.valueOf(limit2);
        copy.viewFrom = ViewFrom.RIGHT;
        return copy;
    }

    public QueryParams startAt(Node indexStartValue2, ChildKey indexStartName2) {
        Utilities.hardAssert(indexStartValue2.isLeafNode() || indexStartValue2.isEmpty());
        Utilities.hardAssert(!(indexStartValue2 instanceof LongNode));
        QueryParams copy = copy();
        copy.indexStartValue = indexStartValue2;
        copy.indexStartName = indexStartName2;
        return copy;
    }

    public QueryParams endAt(Node indexEndValue2, ChildKey indexEndName2) {
        Utilities.hardAssert(indexEndValue2.isLeafNode() || indexEndValue2.isEmpty());
        Utilities.hardAssert(!(indexEndValue2 instanceof LongNode));
        QueryParams copy = copy();
        copy.indexEndValue = indexEndValue2;
        copy.indexEndName = indexEndName2;
        return copy;
    }

    public QueryParams orderBy(Index index2) {
        QueryParams copy = copy();
        copy.index = index2;
        return copy;
    }

    public boolean isViewFromLeft() {
        ViewFrom viewFrom2 = this.viewFrom;
        if (viewFrom2 != null) {
            return viewFrom2 == ViewFrom.LEFT;
        }
        return hasStart();
    }

    public Map<String, Object> getWireProtocolParams() {
        Map<String, Object> queryObject = new HashMap<>();
        if (hasStart()) {
            queryObject.put(INDEX_START_VALUE, this.indexStartValue.getValue());
            ChildKey childKey = this.indexStartName;
            if (childKey != null) {
                queryObject.put(INDEX_START_NAME, childKey.asString());
            }
        }
        if (hasEnd()) {
            queryObject.put(INDEX_END_VALUE, this.indexEndValue.getValue());
            ChildKey childKey2 = this.indexEndName;
            if (childKey2 != null) {
                queryObject.put(INDEX_END_NAME, childKey2.asString());
            }
        }
        Integer num = this.limit;
        if (num != null) {
            queryObject.put(LIMIT, num);
            ViewFrom viewFromToAdd = this.viewFrom;
            if (viewFromToAdd == null) {
                if (hasStart()) {
                    viewFromToAdd = ViewFrom.LEFT;
                } else {
                    viewFromToAdd = ViewFrom.RIGHT;
                }
            }
            switch (C07361.f142xa04b6c33[viewFromToAdd.ordinal()]) {
                case 1:
                    queryObject.put(VIEW_FROM, LIMIT);
                    break;
                case 2:
                    queryObject.put(VIEW_FROM, "r");
                    break;
            }
        }
        if (!this.index.equals(PriorityIndex.getInstance())) {
            queryObject.put(INDEX, this.index.getQueryDefinition());
        }
        return queryObject;
    }

    /* renamed from: com.google.firebase.database.core.view.QueryParams$1 */
    static /* synthetic */ class C07361 {

        /* renamed from: $SwitchMap$com$google$firebase$database$core$view$QueryParams$ViewFrom */
        static final /* synthetic */ int[] f142xa04b6c33;

        static {
            int[] iArr = new int[ViewFrom.values().length];
            f142xa04b6c33 = iArr;
            try {
                iArr[ViewFrom.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f142xa04b6c33[ViewFrom.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public boolean loadsAllData() {
        return !hasStart() && !hasEnd() && !hasLimit();
    }

    public boolean isDefault() {
        return loadsAllData() && this.index.equals(PriorityIndex.getInstance());
    }

    public boolean isValid() {
        return !hasStart() || !hasEnd() || !hasLimit() || hasAnchoredLimit();
    }

    public String toJSON() {
        if (this.jsonSerialization == null) {
            try {
                this.jsonSerialization = JsonMapper.serializeJson(getWireProtocolParams());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return this.jsonSerialization;
    }

    public static QueryParams fromQueryObject(Map<String, Object> map) {
        QueryParams params = new QueryParams();
        params.limit = (Integer) map.get(LIMIT);
        if (map.containsKey(INDEX_START_VALUE)) {
            params.indexStartValue = normalizeValue(NodeUtilities.NodeFromJSON(map.get(INDEX_START_VALUE)));
            String indexStartName2 = (String) map.get(INDEX_START_NAME);
            if (indexStartName2 != null) {
                params.indexStartName = ChildKey.fromString(indexStartName2);
            }
        }
        if (map.containsKey(INDEX_END_VALUE)) {
            params.indexEndValue = normalizeValue(NodeUtilities.NodeFromJSON(map.get(INDEX_END_VALUE)));
            String indexEndName2 = (String) map.get(INDEX_END_NAME);
            if (indexEndName2 != null) {
                params.indexEndName = ChildKey.fromString(indexEndName2);
            }
        }
        String viewFrom2 = (String) map.get(VIEW_FROM);
        if (viewFrom2 != null) {
            params.viewFrom = viewFrom2.equals(LIMIT) ? ViewFrom.LEFT : ViewFrom.RIGHT;
        }
        String indexStr = (String) map.get(INDEX);
        if (indexStr != null) {
            params.index = Index.fromQueryDefinition(indexStr);
        }
        return params;
    }

    public NodeFilter getNodeFilter() {
        if (loadsAllData()) {
            return new IndexedFilter(getIndex());
        }
        if (hasLimit()) {
            return new LimitedFilter(this);
        }
        return new RangedFilter(this);
    }

    public String toString() {
        return getWireProtocolParams().toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QueryParams that = (QueryParams) o;
        Integer num = this.limit;
        if (num == null ? that.limit != null : !num.equals(that.limit)) {
            return false;
        }
        Index index2 = this.index;
        if (index2 == null ? that.index != null : !index2.equals(that.index)) {
            return false;
        }
        ChildKey childKey = this.indexEndName;
        if (childKey == null ? that.indexEndName != null : !childKey.equals(that.indexEndName)) {
            return false;
        }
        Node node = this.indexEndValue;
        if (node == null ? that.indexEndValue != null : !node.equals(that.indexEndValue)) {
            return false;
        }
        ChildKey childKey2 = this.indexStartName;
        if (childKey2 == null ? that.indexStartName != null : !childKey2.equals(that.indexStartName)) {
            return false;
        }
        Node node2 = this.indexStartValue;
        if (node2 == null ? that.indexStartValue != null : !node2.equals(that.indexStartValue)) {
            return false;
        }
        if (isViewFromLeft() != that.isViewFromLeft()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        Integer num = this.limit;
        int i = 0;
        int result = (((num != null ? num.intValue() : 0) * 31) + (isViewFromLeft() ? 1231 : 1237)) * 31;
        Node node = this.indexStartValue;
        int result2 = (result + (node != null ? node.hashCode() : 0)) * 31;
        ChildKey childKey = this.indexStartName;
        int result3 = (result2 + (childKey != null ? childKey.hashCode() : 0)) * 31;
        Node node2 = this.indexEndValue;
        int result4 = (result3 + (node2 != null ? node2.hashCode() : 0)) * 31;
        ChildKey childKey2 = this.indexEndName;
        int result5 = (result4 + (childKey2 != null ? childKey2.hashCode() : 0)) * 31;
        Index index2 = this.index;
        if (index2 != null) {
            i = index2.hashCode();
        }
        return result5 + i;
    }

    private static Node normalizeValue(Node value) {
        if ((value instanceof StringNode) || (value instanceof BooleanNode) || (value instanceof DoubleNode) || (value instanceof EmptyNode)) {
            return value;
        }
        if (value instanceof LongNode) {
            return new DoubleNode(Double.valueOf(((Long) value.getValue()).doubleValue()), PriorityUtilities.NullPriority());
        }
        throw new IllegalStateException("Unexpected value passed to normalizeValue: " + value.getValue());
    }
}
