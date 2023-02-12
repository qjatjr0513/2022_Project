package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

class StandardNetwork<N, E> extends AbstractNetwork<N, E> {
    private final boolean allowsParallelEdges;
    private final boolean allowsSelfLoops;
    private final ElementOrder<E> edgeOrder;
    protected final MapIteratorCache<E, N> edgeToReferenceNode;
    private final boolean isDirected;
    protected final MapIteratorCache<N, NetworkConnections<N, E>> nodeConnections;
    private final ElementOrder<N> nodeOrder;

    StandardNetwork(NetworkBuilder<? super N, ? super E> builder) {
        this(builder, builder.nodeOrder.createMap(((Integer) builder.expectedNodeCount.mo1880or(10)).intValue()), builder.edgeOrder.createMap(builder.expectedEdgeCount.mo1880or(20).intValue()));
    }

    StandardNetwork(NetworkBuilder<? super N, ? super E> builder, Map<N, NetworkConnections<N, E>> nodeConnections2, Map<E, N> edgeToReferenceNode2) {
        MapIteratorCache<N, NetworkConnections<N, E>> mapIteratorCache;
        this.isDirected = builder.directed;
        this.allowsParallelEdges = builder.allowsParallelEdges;
        this.allowsSelfLoops = builder.allowsSelfLoops;
        this.nodeOrder = builder.nodeOrder.cast();
        this.edgeOrder = builder.edgeOrder.cast();
        if (nodeConnections2 instanceof TreeMap) {
            mapIteratorCache = new MapRetrievalCache<>(nodeConnections2);
        } else {
            mapIteratorCache = new MapIteratorCache<>(nodeConnections2);
        }
        this.nodeConnections = mapIteratorCache;
        this.edgeToReferenceNode = new MapIteratorCache<>(edgeToReferenceNode2);
    }

    public Set<N> nodes() {
        return this.nodeConnections.unmodifiableKeySet();
    }

    public Set<E> edges() {
        return this.edgeToReferenceNode.unmodifiableKeySet();
    }

    public boolean isDirected() {
        return this.isDirected;
    }

    public boolean allowsParallelEdges() {
        return this.allowsParallelEdges;
    }

    public boolean allowsSelfLoops() {
        return this.allowsSelfLoops;
    }

    public ElementOrder<N> nodeOrder() {
        return this.nodeOrder;
    }

    public ElementOrder<E> edgeOrder() {
        return this.edgeOrder;
    }

    public Set<E> incidentEdges(N node) {
        return checkedConnections(node).incidentEdges();
    }

    public EndpointPair<N> incidentNodes(E edge) {
        N nodeU = checkedReferenceNode(edge);
        return EndpointPair.m140of((Network<?, ?>) this, nodeU, this.nodeConnections.get(nodeU).adjacentNode(edge));
    }

    public Set<N> adjacentNodes(N node) {
        return checkedConnections(node).adjacentNodes();
    }

    public Set<E> edgesConnecting(N nodeU, N nodeV) {
        NetworkConnections<N, E> connectionsU = checkedConnections(nodeU);
        if (!this.allowsSelfLoops && nodeU == nodeV) {
            return ImmutableSet.m83of();
        }
        Preconditions.checkArgument(containsNode(nodeV), "Node %s is not an element of this graph.", (Object) nodeV);
        return connectionsU.edgesConnecting(nodeV);
    }

    public Set<E> inEdges(N node) {
        return checkedConnections(node).inEdges();
    }

    public Set<E> outEdges(N node) {
        return checkedConnections(node).outEdges();
    }

    public Set<N> predecessors(N node) {
        return checkedConnections(node).predecessors();
    }

    public Set<N> successors(N node) {
        return checkedConnections(node).successors();
    }

    /* access modifiers changed from: protected */
    public final NetworkConnections<N, E> checkedConnections(N node) {
        NetworkConnections<N, E> connections = this.nodeConnections.get(node);
        if (connections != null) {
            return connections;
        }
        Preconditions.checkNotNull(node);
        throw new IllegalArgumentException(String.format("Node %s is not an element of this graph.", new Object[]{node}));
    }

    /* access modifiers changed from: protected */
    public final N checkedReferenceNode(E edge) {
        N referenceNode = this.edgeToReferenceNode.get(edge);
        if (referenceNode != null) {
            return referenceNode;
        }
        Preconditions.checkNotNull(edge);
        throw new IllegalArgumentException(String.format("Edge %s is not an element of this graph.", new Object[]{edge}));
    }

    /* access modifiers changed from: protected */
    public final boolean containsNode(@NullableDecl N node) {
        return this.nodeConnections.containsKey(node);
    }

    /* access modifiers changed from: protected */
    public final boolean containsEdge(@NullableDecl E edge) {
        return this.edgeToReferenceNode.containsKey(edge);
    }
}
