package com.google.firebase.database.snapshot;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.core.ServerValues;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeUtilities {
    public static Node NodeFromJSON(Object value) throws DatabaseException {
        return NodeFromJSON(value, PriorityUtilities.NullPriority());
    }

    public static Node NodeFromJSON(Object value, Node priority) throws DatabaseException {
        Map<ChildKey, Node> childData;
        try {
            if (value instanceof Map) {
                Map mapValue = (Map) value;
                if (mapValue.containsKey(".priority")) {
                    priority = PriorityUtilities.parsePriority(mapValue.get(".priority"));
                }
                if (mapValue.containsKey(".value")) {
                    value = mapValue.get(".value");
                }
            }
            if (value == null) {
                return EmptyNode.Empty();
            }
            if (value instanceof String) {
                return new StringNode((String) value, priority);
            }
            if (value instanceof Long) {
                return new LongNode((Long) value, priority);
            }
            if (value instanceof Integer) {
                return new LongNode(Long.valueOf((long) ((Integer) value).intValue()), priority);
            }
            if (value instanceof Double) {
                return new DoubleNode((Double) value, priority);
            }
            if (value instanceof Boolean) {
                return new BooleanNode((Boolean) value, priority);
            }
            if (!(value instanceof Map)) {
                if (!(value instanceof List)) {
                    throw new DatabaseException("Failed to parse node with class " + value.getClass().toString());
                }
            }
            if (value instanceof Map) {
                Map mapValue2 = (Map) value;
                if (mapValue2.containsKey(ServerValues.NAME_SUBKEY_SERVERVALUE)) {
                    return new DeferredValueNode(mapValue2, priority);
                }
                childData = new HashMap<>(mapValue2.size());
                for (String key : mapValue2.keySet()) {
                    if (!key.startsWith(".")) {
                        Node childNode = NodeFromJSON(mapValue2.get(key));
                        if (!childNode.isEmpty()) {
                            childData.put(ChildKey.fromString(key), childNode);
                        }
                    }
                }
            } else {
                List listValue = (List) value;
                childData = new HashMap<>(listValue.size());
                for (int i = 0; i < listValue.size(); i++) {
                    String key2 = "" + i;
                    Node childNode2 = NodeFromJSON(listValue.get(i));
                    if (!childNode2.isEmpty()) {
                        childData.put(ChildKey.fromString(key2), childNode2);
                    }
                }
            }
            if (childData.isEmpty()) {
                return EmptyNode.Empty();
            }
            return new ChildrenNode(ImmutableSortedMap.Builder.fromMap(childData, ChildrenNode.NAME_ONLY_COMPARATOR), priority);
        } catch (ClassCastException e) {
            throw new DatabaseException("Failed to parse node", e);
        }
    }

    public static int nameAndPriorityCompare(ChildKey aKey, Node aPriority, ChildKey bKey, Node bPriority) {
        int priCmp = aPriority.compareTo(bPriority);
        if (priCmp != 0) {
            return priCmp;
        }
        return aKey.compareTo(bKey);
    }
}
