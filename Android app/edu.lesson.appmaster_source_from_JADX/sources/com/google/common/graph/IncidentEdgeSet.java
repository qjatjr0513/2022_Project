package com.google.common.graph;

import java.util.AbstractSet;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

abstract class IncidentEdgeSet<N> extends AbstractSet<EndpointPair<N>> {
    protected final BaseGraph<N> graph;
    protected final N node;

    IncidentEdgeSet(BaseGraph<N> graph2, N node2) {
        this.graph = graph2;
        this.node = node2;
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        if (this.graph.isDirected()) {
            return (this.graph.inDegree(this.node) + this.graph.outDegree(this.node)) - (this.graph.successors(this.node).contains(this.node) ? 1 : 0);
        }
        return this.graph.adjacentNodes(this.node).size();
    }

    public boolean contains(@NullableDecl Object obj) {
        if (!(obj instanceof EndpointPair)) {
            return false;
        }
        EndpointPair<?> endpointPair = (EndpointPair) obj;
        if (this.graph.isDirected()) {
            if (!endpointPair.isOrdered()) {
                return false;
            }
            Object source = endpointPair.source();
            Object target = endpointPair.target();
            if ((!this.node.equals(source) || !this.graph.successors(this.node).contains(target)) && (!this.node.equals(target) || !this.graph.predecessors(this.node).contains(source))) {
                return false;
            }
            return true;
        } else if (endpointPair.isOrdered()) {
            return false;
        } else {
            Set<N> adjacent = this.graph.adjacentNodes(this.node);
            Object nodeU = endpointPair.nodeU();
            Object nodeV = endpointPair.nodeV();
            if ((!this.node.equals(nodeV) || !adjacent.contains(nodeU)) && (!this.node.equals(nodeU) || !adjacent.contains(nodeV))) {
                return false;
            }
            return true;
        }
    }
}
