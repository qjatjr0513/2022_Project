package com.google.common.graph;

import java.util.Set;

abstract class ForwardingGraph<N> extends AbstractGraph<N> {
    /* access modifiers changed from: protected */
    public abstract BaseGraph<N> delegate();

    ForwardingGraph() {
    }

    public Set<N> nodes() {
        return delegate().nodes();
    }

    /* access modifiers changed from: protected */
    public long edgeCount() {
        return (long) delegate().edges().size();
    }

    public boolean isDirected() {
        return delegate().isDirected();
    }

    public boolean allowsSelfLoops() {
        return delegate().allowsSelfLoops();
    }

    public ElementOrder<N> nodeOrder() {
        return delegate().nodeOrder();
    }

    public ElementOrder<N> incidentEdgeOrder() {
        return delegate().incidentEdgeOrder();
    }

    public Set<N> adjacentNodes(N node) {
        return delegate().adjacentNodes(node);
    }

    public Set<N> predecessors(N node) {
        return delegate().predecessors(node);
    }

    public Set<N> successors(N node) {
        return delegate().successors(node);
    }

    public Set<EndpointPair<N>> incidentEdges(N node) {
        return delegate().incidentEdges(node);
    }

    public int degree(N node) {
        return delegate().degree(node);
    }

    public int inDegree(N node) {
        return delegate().inDegree(node);
    }

    public int outDegree(N node) {
        return delegate().outDegree(node);
    }

    public boolean hasEdgeConnecting(N nodeU, N nodeV) {
        return delegate().hasEdgeConnecting(nodeU, nodeV);
    }

    public boolean hasEdgeConnecting(EndpointPair<N> endpoints) {
        return delegate().hasEdgeConnecting(endpoints);
    }
}
