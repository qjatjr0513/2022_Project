package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.graph.ElementOrder;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class DirectedGraphConnections<N, V> implements GraphConnections<N, V> {
    private static final Object PRED = new Object();
    /* access modifiers changed from: private */
    public final Map<N, Object> adjacentNodeValues;
    /* access modifiers changed from: private */
    @NullableDecl
    public final List<NodeConnection<N>> orderedNodeConnections;
    /* access modifiers changed from: private */
    public int predecessorCount;
    /* access modifiers changed from: private */
    public int successorCount;

    private static final class PredAndSucc {
        /* access modifiers changed from: private */
        public final Object successorValue;

        PredAndSucc(Object successorValue2) {
            this.successorValue = successorValue2;
        }
    }

    private static abstract class NodeConnection<N> {
        final N node;

        NodeConnection(N node2) {
            this.node = Preconditions.checkNotNull(node2);
        }

        static final class Pred<N> extends NodeConnection<N> {
            Pred(N node) {
                super(node);
            }

            public boolean equals(Object that) {
                if (that instanceof Pred) {
                    return this.node.equals(((Pred) that).node);
                }
                return false;
            }

            public int hashCode() {
                return Pred.class.hashCode() + this.node.hashCode();
            }
        }

        static final class Succ<N> extends NodeConnection<N> {
            Succ(N node) {
                super(node);
            }

            public boolean equals(Object that) {
                if (that instanceof Succ) {
                    return this.node.equals(((Succ) that).node);
                }
                return false;
            }

            public int hashCode() {
                return Succ.class.hashCode() + this.node.hashCode();
            }
        }
    }

    private DirectedGraphConnections(Map<N, Object> adjacentNodeValues2, @NullableDecl List<NodeConnection<N>> orderedNodeConnections2, int predecessorCount2, int successorCount2) {
        this.adjacentNodeValues = (Map) Preconditions.checkNotNull(adjacentNodeValues2);
        this.orderedNodeConnections = orderedNodeConnections2;
        this.predecessorCount = Graphs.checkNonNegative(predecessorCount2);
        this.successorCount = Graphs.checkNonNegative(successorCount2);
        Preconditions.checkState(predecessorCount2 <= adjacentNodeValues2.size() && successorCount2 <= adjacentNodeValues2.size());
    }

    /* renamed from: of */
    static <N, V> DirectedGraphConnections<N, V> m136of(ElementOrder<N> incidentEdgeOrder) {
        List<NodeConnection<N>> orderedNodeConnections2;
        switch (C03918.$SwitchMap$com$google$common$graph$ElementOrder$Type[incidentEdgeOrder.type().ordinal()]) {
            case 1:
                orderedNodeConnections2 = null;
                break;
            case 2:
                orderedNodeConnections2 = new ArrayList<>();
                break;
            default:
                throw new AssertionError(incidentEdgeOrder.type());
        }
        return new DirectedGraphConnections<>(new HashMap(4, 1.0f), orderedNodeConnections2, 0, 0);
    }

    /* renamed from: com.google.common.graph.DirectedGraphConnections$8 */
    static /* synthetic */ class C03918 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$graph$ElementOrder$Type;

        static {
            int[] iArr = new int[ElementOrder.Type.values().length];
            $SwitchMap$com$google$common$graph$ElementOrder$Type = iArr;
            try {
                iArr[ElementOrder.Type.UNORDERED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$common$graph$ElementOrder$Type[ElementOrder.Type.STABLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    static <N, V> DirectedGraphConnections<N, V> ofImmutable(N thisNode, Iterable<EndpointPair<N>> incidentEdges, Function<N, V> successorNodeToValueFn) {
        Preconditions.checkNotNull(thisNode);
        Preconditions.checkNotNull(successorNodeToValueFn);
        Map<N, Object> adjacentNodeValues2 = new HashMap<>();
        ImmutableList.Builder<NodeConnection<N>> orderedNodeConnectionsBuilder = ImmutableList.builder();
        int predecessorCount2 = 0;
        int successorCount2 = 0;
        for (EndpointPair<N> incidentEdge : incidentEdges) {
            if (incidentEdge.nodeU().equals(thisNode) && incidentEdge.nodeV().equals(thisNode)) {
                adjacentNodeValues2.put(thisNode, new PredAndSucc(successorNodeToValueFn.apply(thisNode)));
                orderedNodeConnectionsBuilder.add((Object) new NodeConnection.Pred(thisNode));
                orderedNodeConnectionsBuilder.add((Object) new NodeConnection.Succ(thisNode));
                predecessorCount2++;
                successorCount2++;
            } else if (incidentEdge.nodeV().equals(thisNode)) {
                N predecessor = incidentEdge.nodeU();
                Object existingValue = adjacentNodeValues2.put(predecessor, PRED);
                if (existingValue != null) {
                    adjacentNodeValues2.put(predecessor, new PredAndSucc(existingValue));
                }
                orderedNodeConnectionsBuilder.add((Object) new NodeConnection.Pred(predecessor));
                predecessorCount2++;
            } else {
                Preconditions.checkArgument(incidentEdge.nodeU().equals(thisNode));
                N successor = incidentEdge.nodeV();
                V value = successorNodeToValueFn.apply(successor);
                Object existingValue2 = adjacentNodeValues2.put(successor, value);
                if (existingValue2 != null) {
                    Preconditions.checkArgument(existingValue2 == PRED);
                    adjacentNodeValues2.put(successor, new PredAndSucc(value));
                }
                orderedNodeConnectionsBuilder.add((Object) new NodeConnection.Succ(successor));
                successorCount2++;
            }
        }
        return new DirectedGraphConnections<>(adjacentNodeValues2, orderedNodeConnectionsBuilder.build(), predecessorCount2, successorCount2);
    }

    public Set<N> adjacentNodes() {
        if (this.orderedNodeConnections == null) {
            return Collections.unmodifiableSet(this.adjacentNodeValues.keySet());
        }
        return new AbstractSet<N>() {
            public UnmodifiableIterator<N> iterator() {
                final Iterator<NodeConnection<N>> nodeConnections = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                final Set<N> seenNodes = new HashSet<>();
                return new AbstractIterator<N>(this) {
                    /* access modifiers changed from: protected */
                    public N computeNext() {
                        while (nodeConnections.hasNext()) {
                            NodeConnection<N> nodeConnection = (NodeConnection) nodeConnections.next();
                            if (seenNodes.add(nodeConnection.node)) {
                                return nodeConnection.node;
                            }
                        }
                        return endOfData();
                    }
                };
            }

            public int size() {
                return DirectedGraphConnections.this.adjacentNodeValues.size();
            }

            public boolean contains(@NullableDecl Object obj) {
                return DirectedGraphConnections.this.adjacentNodeValues.containsKey(obj);
            }
        };
    }

    public Set<N> predecessors() {
        return new AbstractSet<N>() {
            public UnmodifiableIterator<N> iterator() {
                if (DirectedGraphConnections.this.orderedNodeConnections == null) {
                    final Iterator<Map.Entry<N, Object>> entries = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                    return new AbstractIterator<N>(this) {
                        /* access modifiers changed from: protected */
                        public N computeNext() {
                            while (entries.hasNext()) {
                                Map.Entry<N, Object> entry = (Map.Entry) entries.next();
                                if (DirectedGraphConnections.isPredecessor(entry.getValue())) {
                                    return entry.getKey();
                                }
                            }
                            return endOfData();
                        }
                    };
                }
                final Iterator<NodeConnection<N>> nodeConnections = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                return new AbstractIterator<N>(this) {
                    /* access modifiers changed from: protected */
                    public N computeNext() {
                        while (nodeConnections.hasNext()) {
                            NodeConnection<N> nodeConnection = (NodeConnection) nodeConnections.next();
                            if (nodeConnection instanceof NodeConnection.Pred) {
                                return nodeConnection.node;
                            }
                        }
                        return endOfData();
                    }
                };
            }

            public int size() {
                return DirectedGraphConnections.this.predecessorCount;
            }

            public boolean contains(@NullableDecl Object obj) {
                return DirectedGraphConnections.isPredecessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }
        };
    }

    public Set<N> successors() {
        return new AbstractSet<N>() {
            public UnmodifiableIterator<N> iterator() {
                if (DirectedGraphConnections.this.orderedNodeConnections == null) {
                    final Iterator<Map.Entry<N, Object>> entries = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                    return new AbstractIterator<N>(this) {
                        /* access modifiers changed from: protected */
                        public N computeNext() {
                            while (entries.hasNext()) {
                                Map.Entry<N, Object> entry = (Map.Entry) entries.next();
                                if (DirectedGraphConnections.isSuccessor(entry.getValue())) {
                                    return entry.getKey();
                                }
                            }
                            return endOfData();
                        }
                    };
                }
                final Iterator<NodeConnection<N>> nodeConnections = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                return new AbstractIterator<N>(this) {
                    /* access modifiers changed from: protected */
                    public N computeNext() {
                        while (nodeConnections.hasNext()) {
                            NodeConnection<N> nodeConnection = (NodeConnection) nodeConnections.next();
                            if (nodeConnection instanceof NodeConnection.Succ) {
                                return nodeConnection.node;
                            }
                        }
                        return endOfData();
                    }
                };
            }

            public int size() {
                return DirectedGraphConnections.this.successorCount;
            }

            public boolean contains(@NullableDecl Object obj) {
                return DirectedGraphConnections.isSuccessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }
        };
    }

    public Iterator<EndpointPair<N>> incidentEdgeIterator(final N thisNode) {
        final Iterator<EndpointPair<N>> resultWithDoubleSelfLoop;
        Preconditions.checkNotNull(thisNode);
        List<NodeConnection<N>> list = this.orderedNodeConnections;
        if (list == null) {
            resultWithDoubleSelfLoop = Iterators.concat(Iterators.transform(predecessors().iterator(), new Function<N, EndpointPair<N>>(this) {
                public EndpointPair<N> apply(N predecessor) {
                    return EndpointPair.ordered(predecessor, thisNode);
                }
            }), Iterators.transform(successors().iterator(), new Function<N, EndpointPair<N>>(this) {
                public EndpointPair<N> apply(N successor) {
                    return EndpointPair.ordered(thisNode, successor);
                }
            }));
        } else {
            resultWithDoubleSelfLoop = Iterators.transform(list.iterator(), new Function<NodeConnection<N>, EndpointPair<N>>(this) {
                public EndpointPair<N> apply(NodeConnection<N> connection) {
                    if (connection instanceof NodeConnection.Succ) {
                        return EndpointPair.ordered(thisNode, connection.node);
                    }
                    return EndpointPair.ordered(connection.node, thisNode);
                }
            });
        }
        final AtomicBoolean alreadySeenSelfLoop = new AtomicBoolean(false);
        return new AbstractIterator<EndpointPair<N>>(this) {
            /* access modifiers changed from: protected */
            /* JADX WARNING: Removed duplicated region for block: B:2:0x0008  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.common.graph.EndpointPair<N> computeNext() {
                /*
                    r3 = this;
                L_0x0000:
                    java.util.Iterator r0 = r0
                    boolean r0 = r0.hasNext()
                    if (r0 == 0) goto L_0x002a
                    java.util.Iterator r0 = r0
                    java.lang.Object r0 = r0.next()
                    com.google.common.graph.EndpointPair r0 = (com.google.common.graph.EndpointPair) r0
                    java.lang.Object r1 = r0.nodeU()
                    java.lang.Object r2 = r0.nodeV()
                    boolean r1 = r1.equals(r2)
                    if (r1 == 0) goto L_0x0029
                    java.util.concurrent.atomic.AtomicBoolean r1 = r1
                    r2 = 1
                    boolean r1 = r1.getAndSet(r2)
                    if (r1 != 0) goto L_0x0028
                    return r0
                L_0x0028:
                    goto L_0x0000
                L_0x0029:
                    return r0
                L_0x002a:
                    java.lang.Object r0 = r3.endOfData()
                    com.google.common.graph.EndpointPair r0 = (com.google.common.graph.EndpointPair) r0
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.DirectedGraphConnections.C03907.computeNext():com.google.common.graph.EndpointPair");
            }
        };
    }

    public V value(N node) {
        Preconditions.checkNotNull(node);
        Object value = this.adjacentNodeValues.get(node);
        if (value == PRED) {
            return null;
        }
        if (value instanceof PredAndSucc) {
            return ((PredAndSucc) value).successorValue;
        }
        return value;
    }

    public void removePredecessor(N node) {
        boolean removedPredecessor;
        Preconditions.checkNotNull(node);
        Object previousValue = this.adjacentNodeValues.get(node);
        if (previousValue == PRED) {
            this.adjacentNodeValues.remove(node);
            removedPredecessor = true;
        } else if (previousValue instanceof PredAndSucc) {
            this.adjacentNodeValues.put(node, ((PredAndSucc) previousValue).successorValue);
            removedPredecessor = true;
        } else {
            removedPredecessor = false;
        }
        if (removedPredecessor) {
            int i = this.predecessorCount - 1;
            this.predecessorCount = i;
            Graphs.checkNonNegative(i);
            List<NodeConnection<N>> list = this.orderedNodeConnections;
            if (list != null) {
                list.remove(new NodeConnection.Pred(node));
            }
        }
    }

    public V removeSuccessor(Object node) {
        Object removedValue;
        Object obj;
        Preconditions.checkNotNull(node);
        Object previousValue = this.adjacentNodeValues.get(node);
        if (previousValue == null || previousValue == (obj = PRED)) {
            removedValue = null;
        } else if (previousValue instanceof PredAndSucc) {
            this.adjacentNodeValues.put(node, obj);
            removedValue = ((PredAndSucc) previousValue).successorValue;
        } else {
            this.adjacentNodeValues.remove(node);
            removedValue = previousValue;
        }
        if (removedValue != null) {
            int i = this.successorCount - 1;
            this.successorCount = i;
            Graphs.checkNonNegative(i);
            List<NodeConnection<N>> list = this.orderedNodeConnections;
            if (list != null) {
                list.remove(new NodeConnection.Succ(node));
            }
        }
        return removedValue;
    }

    public void addPredecessor(N node, V v) {
        boolean addedPredecessor;
        Map<N, Object> map = this.adjacentNodeValues;
        Object obj = PRED;
        Object previousValue = map.put(node, obj);
        if (previousValue == null) {
            addedPredecessor = true;
        } else if (previousValue instanceof PredAndSucc) {
            this.adjacentNodeValues.put(node, previousValue);
            addedPredecessor = false;
        } else if (previousValue != obj) {
            this.adjacentNodeValues.put(node, new PredAndSucc(previousValue));
            addedPredecessor = true;
        } else {
            addedPredecessor = false;
        }
        if (addedPredecessor) {
            int i = this.predecessorCount + 1;
            this.predecessorCount = i;
            Graphs.checkPositive(i);
            List<NodeConnection<N>> list = this.orderedNodeConnections;
            if (list != null) {
                list.add(new NodeConnection.Pred(node));
            }
        }
    }

    public V addSuccessor(N node, V value) {
        Object previousSuccessor;
        Object previousValue = this.adjacentNodeValues.put(node, value);
        if (previousValue == null) {
            previousSuccessor = null;
        } else if (previousValue instanceof PredAndSucc) {
            this.adjacentNodeValues.put(node, new PredAndSucc(value));
            previousSuccessor = ((PredAndSucc) previousValue).successorValue;
        } else if (previousValue == PRED) {
            this.adjacentNodeValues.put(node, new PredAndSucc(value));
            previousSuccessor = null;
        } else {
            previousSuccessor = previousValue;
        }
        if (previousSuccessor == null) {
            int i = this.successorCount + 1;
            this.successorCount = i;
            Graphs.checkPositive(i);
            List<NodeConnection<N>> list = this.orderedNodeConnections;
            if (list != null) {
                list.add(new NodeConnection.Succ(node));
            }
        }
        return previousSuccessor;
    }

    /* access modifiers changed from: private */
    public static boolean isPredecessor(@NullableDecl Object value) {
        return value == PRED || (value instanceof PredAndSucc);
    }

    /* access modifiers changed from: private */
    public static boolean isSuccessor(@NullableDecl Object value) {
        return (value == PRED || value == null) ? false : true;
    }
}
