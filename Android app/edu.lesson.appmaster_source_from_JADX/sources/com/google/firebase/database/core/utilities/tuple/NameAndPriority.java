package com.google.firebase.database.core.utilities.tuple;

import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.NodeUtilities;

public class NameAndPriority implements Comparable<NameAndPriority> {
    private ChildKey name;
    private Node priority;

    public NameAndPriority(ChildKey name2, Node priority2) {
        this.name = name2;
        this.priority = priority2;
    }

    public ChildKey getName() {
        return this.name;
    }

    public Node getPriority() {
        return this.priority;
    }

    public int compareTo(NameAndPriority o) {
        return NodeUtilities.nameAndPriorityCompare(this.name, this.priority, o.name, o.priority);
    }
}
