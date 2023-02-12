package com.google.common.graph;

import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

abstract class ForwardingValueGraph<N, V> extends AbstractValueGraph<N, V> {
    /* access modifiers changed from: protected */
    public abstract ValueGraph<N, V> delegate();

    ForwardingValueGraph() {
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

    @NullableDecl
    public V edgeValueOrDefault(N nodeU, N nodeV, @NullableDecl V defaultValue) {
        return delegate().edgeValueOrDefault(nodeU, nodeV, defaultValue);
    }

    @NullableDecl
    public V edgeValueOrDefault(EndpointPair<N> endpoints, @NullableDecl V defaultValue) {
        return delegate().edgeValueOrDefault(endpoints, defaultValue);
    }
}
