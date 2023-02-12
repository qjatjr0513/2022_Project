package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class Graphs {

    private enum NodeVisitState {
        PENDING,
        COMPLETE
    }

    private Graphs() {
    }

    public static <N> boolean hasCycle(Graph<N> graph) {
        int numEdges = graph.edges().size();
        if (numEdges == 0) {
            return false;
        }
        if (!graph.isDirected() && numEdges >= graph.nodes().size()) {
            return true;
        }
        Map<Object, NodeVisitState> visitedNodes = Maps.newHashMapWithExpectedSize(graph.nodes().size());
        for (N node : graph.nodes()) {
            if (subgraphHasCycle(graph, visitedNodes, node, (N) null)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasCycle(Network<?, ?> network) {
        if (network.isDirected() || !network.allowsParallelEdges() || network.edges().size() <= network.asGraph().edges().size()) {
            return hasCycle(network.asGraph());
        }
        return true;
    }

    private static <N> boolean subgraphHasCycle(Graph<N> graph, Map<Object, NodeVisitState> visitedNodes, N node, @NullableDecl N previousNode) {
        NodeVisitState state = visitedNodes.get(node);
        if (state == NodeVisitState.COMPLETE) {
            return false;
        }
        if (state == NodeVisitState.PENDING) {
            return true;
        }
        visitedNodes.put(node, NodeVisitState.PENDING);
        for (N nextNode : graph.successors(node)) {
            if (canTraverseWithoutReusingEdge(graph, nextNode, previousNode) && subgraphHasCycle(graph, visitedNodes, nextNode, node)) {
                return true;
            }
        }
        visitedNodes.put(node, NodeVisitState.COMPLETE);
        return false;
    }

    private static boolean canTraverseWithoutReusingEdge(Graph<?> graph, Object nextNode, @NullableDecl Object previousNode) {
        if (graph.isDirected() || !Objects.equal(previousNode, nextNode)) {
            return true;
        }
        return false;
    }

    public static <N> Graph<N> transitiveClosure(Graph<N> graph) {
        MutableGraph<N1> build = GraphBuilder.from(graph).allowsSelfLoops(true).build();
        if (graph.isDirected()) {
            for (N node : graph.nodes()) {
                for (N reachableNode : reachableNodes(graph, node)) {
                    build.putEdge(node, reachableNode);
                }
            }
        } else {
            Set<N> visitedNodes = new HashSet<>();
            for (N node2 : graph.nodes()) {
                if (!visitedNodes.contains(node2)) {
                    Set<N> reachableNodes = reachableNodes(graph, node2);
                    visitedNodes.addAll(reachableNodes);
                    int pairwiseMatch = 1;
                    for (N nodeU : reachableNodes) {
                        int pairwiseMatch2 = pairwiseMatch + 1;
                        for (T putEdge : Iterables.limit(reachableNodes, pairwiseMatch)) {
                            build.putEdge(nodeU, putEdge);
                        }
                        pairwiseMatch = pairwiseMatch2;
                    }
                }
            }
        }
        return build;
    }

    public static <N> Set<N> reachableNodes(Graph<N> graph, N node) {
        Preconditions.checkArgument(graph.nodes().contains(node), "Node %s is not an element of this graph.", (Object) node);
        return ImmutableSet.copyOf(Traverser.forGraph(graph).breadthFirst(node));
    }

    public static <N> Graph<N> transpose(Graph<N> graph) {
        if (!graph.isDirected()) {
            return graph;
        }
        if (graph instanceof TransposedGraph) {
            return ((TransposedGraph) graph).graph;
        }
        return new TransposedGraph(graph);
    }

    public static <N, V> ValueGraph<N, V> transpose(ValueGraph<N, V> graph) {
        if (!graph.isDirected()) {
            return graph;
        }
        if (graph instanceof TransposedValueGraph) {
            return ((TransposedValueGraph) graph).graph;
        }
        return new TransposedValueGraph(graph);
    }

    public static <N, E> Network<N, E> transpose(Network<N, E> network) {
        if (!network.isDirected()) {
            return network;
        }
        if (network instanceof TransposedNetwork) {
            return ((TransposedNetwork) network).network;
        }
        return new TransposedNetwork(network);
    }

    static <N> EndpointPair<N> transpose(EndpointPair<N> endpoints) {
        if (endpoints.isOrdered()) {
            return EndpointPair.ordered(endpoints.target(), endpoints.source());
        }
        return endpoints;
    }

    private static class TransposedGraph<N> extends ForwardingGraph<N> {
        /* access modifiers changed from: private */
        public final Graph<N> graph;

        TransposedGraph(Graph<N> graph2) {
            this.graph = graph2;
        }

        /* access modifiers changed from: protected */
        public Graph<N> delegate() {
            return this.graph;
        }

        public Set<N> predecessors(N node) {
            return delegate().successors(node);
        }

        public Set<N> successors(N node) {
            return delegate().predecessors(node);
        }

        public Set<EndpointPair<N>> incidentEdges(N node) {
            return new IncidentEdgeSet<N>(this, node) {
                public Iterator<EndpointPair<N>> iterator() {
                    return Iterators.transform(TransposedGraph.this.delegate().incidentEdges(this.node).iterator(), new Function<EndpointPair<N>, EndpointPair<N>>() {
                        public EndpointPair<N> apply(EndpointPair<N> edge) {
                            return EndpointPair.m139of((Graph<?>) TransposedGraph.this.delegate(), edge.nodeV(), edge.nodeU());
                        }
                    });
                }
            };
        }

        public int inDegree(N node) {
            return delegate().outDegree(node);
        }

        public int outDegree(N node) {
            return delegate().inDegree(node);
        }

        public boolean hasEdgeConnecting(N nodeU, N nodeV) {
            return delegate().hasEdgeConnecting(nodeV, nodeU);
        }

        public boolean hasEdgeConnecting(EndpointPair<N> endpoints) {
            return delegate().hasEdgeConnecting(Graphs.transpose(endpoints));
        }
    }

    private static class TransposedValueGraph<N, V> extends ForwardingValueGraph<N, V> {
        /* access modifiers changed from: private */
        public final ValueGraph<N, V> graph;

        TransposedValueGraph(ValueGraph<N, V> graph2) {
            this.graph = graph2;
        }

        /* access modifiers changed from: protected */
        public ValueGraph<N, V> delegate() {
            return this.graph;
        }

        public Set<N> predecessors(N node) {
            return delegate().successors(node);
        }

        public Set<N> successors(N node) {
            return delegate().predecessors(node);
        }

        public int inDegree(N node) {
            return delegate().outDegree(node);
        }

        public int outDegree(N node) {
            return delegate().inDegree(node);
        }

        public boolean hasEdgeConnecting(N nodeU, N nodeV) {
            return delegate().hasEdgeConnecting(nodeV, nodeU);
        }

        public boolean hasEdgeConnecting(EndpointPair<N> endpoints) {
            return delegate().hasEdgeConnecting(Graphs.transpose(endpoints));
        }

        @NullableDecl
        public V edgeValueOrDefault(N nodeU, N nodeV, @NullableDecl V defaultValue) {
            return delegate().edgeValueOrDefault(nodeV, nodeU, defaultValue);
        }

        @NullableDecl
        public V edgeValueOrDefault(EndpointPair<N> endpoints, @NullableDecl V defaultValue) {
            return delegate().edgeValueOrDefault(Graphs.transpose(endpoints), defaultValue);
        }
    }

    private static class TransposedNetwork<N, E> extends ForwardingNetwork<N, E> {
        /* access modifiers changed from: private */
        public final Network<N, E> network;

        TransposedNetwork(Network<N, E> network2) {
            this.network = network2;
        }

        /* access modifiers changed from: protected */
        public Network<N, E> delegate() {
            return this.network;
        }

        public Set<N> predecessors(N node) {
            return delegate().successors(node);
        }

        public Set<N> successors(N node) {
            return delegate().predecessors(node);
        }

        public int inDegree(N node) {
            return delegate().outDegree(node);
        }

        public int outDegree(N node) {
            return delegate().inDegree(node);
        }

        public Set<E> inEdges(N node) {
            return delegate().outEdges(node);
        }

        public Set<E> outEdges(N node) {
            return delegate().inEdges(node);
        }

        public EndpointPair<N> incidentNodes(E edge) {
            EndpointPair<N> endpointPair = delegate().incidentNodes(edge);
            return EndpointPair.m140of((Network<?, ?>) this.network, endpointPair.nodeV(), endpointPair.nodeU());
        }

        public Set<E> edgesConnecting(N nodeU, N nodeV) {
            return delegate().edgesConnecting(nodeV, nodeU);
        }

        public Set<E> edgesConnecting(EndpointPair<N> endpoints) {
            return delegate().edgesConnecting(Graphs.transpose(endpoints));
        }

        public E edgeConnectingOrNull(N nodeU, N nodeV) {
            return delegate().edgeConnectingOrNull(nodeV, nodeU);
        }

        public E edgeConnectingOrNull(EndpointPair<N> endpoints) {
            return delegate().edgeConnectingOrNull(Graphs.transpose(endpoints));
        }

        public boolean hasEdgeConnecting(N nodeU, N nodeV) {
            return delegate().hasEdgeConnecting(nodeV, nodeU);
        }

        public boolean hasEdgeConnecting(EndpointPair<N> endpoints) {
            return delegate().hasEdgeConnecting(Graphs.transpose(endpoints));
        }
    }

    public static <N> MutableGraph<N> inducedSubgraph(Graph<N> graph, Iterable<? extends N> nodes) {
        MutableGraph<N1> mutableGraph;
        if (nodes instanceof Collection) {
            mutableGraph = GraphBuilder.from(graph).expectedNodeCount(((Collection) nodes).size()).build();
        } else {
            mutableGraph = GraphBuilder.from(graph).build();
        }
        for (N node : nodes) {
            mutableGraph.addNode(node);
        }
        for (N1 next : mutableGraph.nodes()) {
            for (N successorNode : graph.successors(next)) {
                if (mutableGraph.nodes().contains(successorNode)) {
                    mutableGraph.putEdge(next, successorNode);
                }
            }
        }
        return mutableGraph;
    }

    public static <N, V> MutableValueGraph<N, V> inducedSubgraph(ValueGraph<N, V> graph, Iterable<? extends N> nodes) {
        MutableValueGraph<N1, V1> mutableValueGraph;
        if (nodes instanceof Collection) {
            mutableValueGraph = ValueGraphBuilder.from(graph).expectedNodeCount(((Collection) nodes).size()).build();
        } else {
            mutableValueGraph = ValueGraphBuilder.from(graph).build();
        }
        for (N node : nodes) {
            mutableValueGraph.addNode(node);
        }
        for (N1 next : mutableValueGraph.nodes()) {
            for (N successorNode : graph.successors(next)) {
                if (mutableValueGraph.nodes().contains(successorNode)) {
                    mutableValueGraph.putEdgeValue(next, successorNode, graph.edgeValueOrDefault(next, successorNode, null));
                }
            }
        }
        return mutableValueGraph;
    }

    public static <N, E> MutableNetwork<N, E> inducedSubgraph(Network<N, E> network, Iterable<? extends N> nodes) {
        MutableNetwork<N1, E1> mutableNetwork;
        if (nodes instanceof Collection) {
            mutableNetwork = NetworkBuilder.from(network).expectedNodeCount(((Collection) nodes).size()).build();
        } else {
            mutableNetwork = NetworkBuilder.from(network).build();
        }
        for (N node : nodes) {
            mutableNetwork.addNode(node);
        }
        for (N1 next : mutableNetwork.nodes()) {
            for (E edge : network.outEdges(next)) {
                N successorNode = network.incidentNodes(edge).adjacentNode(next);
                if (mutableNetwork.nodes().contains(successorNode)) {
                    mutableNetwork.addEdge(next, successorNode, edge);
                }
            }
        }
        return mutableNetwork;
    }

    public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
        MutableGraph<N1> build = GraphBuilder.from(graph).expectedNodeCount(graph.nodes().size()).build();
        for (N node : graph.nodes()) {
            build.addNode(node);
        }
        for (EndpointPair<N> edge : graph.edges()) {
            build.putEdge(edge.nodeU(), edge.nodeV());
        }
        return build;
    }

    public static <N, V> MutableValueGraph<N, V> copyOf(ValueGraph<N, V> graph) {
        MutableValueGraph<N1, V1> build = ValueGraphBuilder.from(graph).expectedNodeCount(graph.nodes().size()).build();
        for (N node : graph.nodes()) {
            build.addNode(node);
        }
        for (EndpointPair<N> edge : graph.edges()) {
            build.putEdgeValue(edge.nodeU(), edge.nodeV(), graph.edgeValueOrDefault(edge.nodeU(), edge.nodeV(), null));
        }
        return build;
    }

    public static <N, E> MutableNetwork<N, E> copyOf(Network<N, E> network) {
        MutableNetwork<N1, E1> build = NetworkBuilder.from(network).expectedNodeCount(network.nodes().size()).expectedEdgeCount(network.edges().size()).build();
        for (N node : network.nodes()) {
            build.addNode(node);
        }
        for (E edge : network.edges()) {
            EndpointPair<N> endpointPair = network.incidentNodes(edge);
            build.addEdge(endpointPair.nodeU(), endpointPair.nodeV(), edge);
        }
        return build;
    }

    static int checkNonNegative(int value) {
        Preconditions.checkArgument(value >= 0, "Not true that %s is non-negative.", value);
        return value;
    }

    static long checkNonNegative(long value) {
        Preconditions.checkArgument(value >= 0, "Not true that %s is non-negative.", value);
        return value;
    }

    static int checkPositive(int value) {
        Preconditions.checkArgument(value > 0, "Not true that %s is positive.", value);
        return value;
    }

    static long checkPositive(long value) {
        Preconditions.checkArgument(value > 0, "Not true that %s is positive.", value);
        return value;
    }
}
