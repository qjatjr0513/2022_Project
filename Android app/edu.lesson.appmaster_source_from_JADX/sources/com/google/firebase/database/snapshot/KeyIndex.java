package com.google.firebase.database.snapshot;

import com.google.firebase.database.core.utilities.Utilities;

public class KeyIndex extends Index {
    private static final KeyIndex INSTANCE = new KeyIndex();

    public static KeyIndex getInstance() {
        return INSTANCE;
    }

    private KeyIndex() {
    }

    public boolean isDefinedOn(Node a) {
        return true;
    }

    public NamedNode makePost(ChildKey name, Node value) {
        Utilities.hardAssert(value instanceof StringNode);
        return new NamedNode(ChildKey.fromString((String) value.getValue()), EmptyNode.Empty());
    }

    public NamedNode maxPost() {
        return NamedNode.getMaxNode();
    }

    public String getQueryDefinition() {
        return ".key";
    }

    public int compare(NamedNode o1, NamedNode o2) {
        return o1.getName().compareTo(o2.getName());
    }

    public boolean equals(Object o) {
        return o instanceof KeyIndex;
    }

    public int hashCode() {
        return 37;
    }

    public String toString() {
        return "KeyIndex";
    }
}
