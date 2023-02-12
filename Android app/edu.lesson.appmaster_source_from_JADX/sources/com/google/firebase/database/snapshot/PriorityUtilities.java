package com.google.firebase.database.snapshot;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.core.Path;

public class PriorityUtilities {
    public static Node NullPriority() {
        return EmptyNode.Empty();
    }

    public static boolean isValidPriority(Node priority) {
        return priority.getPriority().isEmpty() && (priority.isEmpty() || (priority instanceof DoubleNode) || (priority instanceof StringNode) || (priority instanceof DeferredValueNode));
    }

    public static Node parsePriority(Object value) {
        return parsePriority((Path) null, value);
    }

    public static Node parsePriority(Path nodePath, Object value) {
        Node priority = NodeUtilities.NodeFromJSON(value);
        if (priority instanceof LongNode) {
            priority = new DoubleNode(Double.valueOf((double) ((Long) priority.getValue()).longValue()), NullPriority());
        }
        if (isValidPriority(priority)) {
            return priority;
        }
        throw new DatabaseException((nodePath != null ? "Path '" + nodePath + "'" : "Node") + " contains invalid priority: Must be a string, double, ServerValue, or null");
    }
}
