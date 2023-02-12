package com.google.firebase.database.core.utilities.tuple;

import com.google.firebase.database.core.Path;
import com.google.firebase.database.snapshot.Node;

public class NodeAndPath {
    private Node node;
    private Path path;

    public NodeAndPath(Node node2, Path path2) {
        this.node = node2;
        this.path = path2;
    }

    public Node getNode() {
        return this.node;
    }

    public void setNode(Node node2) {
        this.node = node2;
    }

    public Path getPath() {
        return this.path;
    }

    public void setPath(Path path2) {
        this.path = path2;
    }
}
