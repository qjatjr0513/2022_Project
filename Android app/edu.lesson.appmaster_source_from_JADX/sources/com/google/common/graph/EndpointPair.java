package com.google.common.graph;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.Immutable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Immutable(containerOf = {"N"})
public abstract class EndpointPair<N> implements Iterable<N> {
    private final N nodeU;
    private final N nodeV;

    public abstract boolean equals(@NullableDecl Object obj);

    public abstract int hashCode();

    public abstract boolean isOrdered();

    public abstract N source();

    public abstract N target();

    private EndpointPair(N nodeU2, N nodeV2) {
        this.nodeU = Preconditions.checkNotNull(nodeU2);
        this.nodeV = Preconditions.checkNotNull(nodeV2);
    }

    public static <N> EndpointPair<N> ordered(N source, N target) {
        return new Ordered(source, target);
    }

    public static <N> EndpointPair<N> unordered(N nodeU2, N nodeV2) {
        return new Unordered(nodeV2, nodeU2);
    }

    /* renamed from: of */
    static <N> EndpointPair<N> m139of(Graph<?> graph, N nodeU2, N nodeV2) {
        return graph.isDirected() ? ordered(nodeU2, nodeV2) : unordered(nodeU2, nodeV2);
    }

    /* renamed from: of */
    static <N> EndpointPair<N> m140of(Network<?, ?> network, N nodeU2, N nodeV2) {
        return network.isDirected() ? ordered(nodeU2, nodeV2) : unordered(nodeU2, nodeV2);
    }

    public final N nodeU() {
        return this.nodeU;
    }

    public final N nodeV() {
        return this.nodeV;
    }

    public final N adjacentNode(Object node) {
        if (node.equals(this.nodeU)) {
            return this.nodeV;
        }
        if (node.equals(this.nodeV)) {
            return this.nodeU;
        }
        String valueOf = String.valueOf(this);
        String valueOf2 = String.valueOf(node);
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 36 + String.valueOf(valueOf2).length()).append("EndpointPair ").append(valueOf).append(" does not contain node ").append(valueOf2).toString());
    }

    public final UnmodifiableIterator<N> iterator() {
        return Iterators.forArray(this.nodeU, this.nodeV);
    }

    private static final class Ordered<N> extends EndpointPair<N> {
        public /* bridge */ /* synthetic */ Iterator iterator() {
            return EndpointPair.super.iterator();
        }

        private Ordered(N source, N target) {
            super(source, target);
        }

        public N source() {
            return nodeU();
        }

        public N target() {
            return nodeV();
        }

        public boolean isOrdered() {
            return true;
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EndpointPair)) {
                return false;
            }
            EndpointPair<?> other = (EndpointPair) obj;
            if (isOrdered() != other.isOrdered()) {
                return false;
            }
            if (!source().equals(other.source()) || !target().equals(other.target())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hashCode(source(), target());
        }

        public String toString() {
            String valueOf = String.valueOf(source());
            String valueOf2 = String.valueOf(target());
            return new StringBuilder(String.valueOf(valueOf).length() + 6 + String.valueOf(valueOf2).length()).append("<").append(valueOf).append(" -> ").append(valueOf2).append(">").toString();
        }
    }

    private static final class Unordered<N> extends EndpointPair<N> {
        public /* bridge */ /* synthetic */ Iterator iterator() {
            return EndpointPair.super.iterator();
        }

        private Unordered(N nodeU, N nodeV) {
            super(nodeU, nodeV);
        }

        public N source() {
            throw new UnsupportedOperationException("Cannot call source()/target() on a EndpointPair from an undirected graph. Consider calling adjacentNode(node) if you already have a node, or nodeU()/nodeV() if you don't.");
        }

        public N target() {
            throw new UnsupportedOperationException("Cannot call source()/target() on a EndpointPair from an undirected graph. Consider calling adjacentNode(node) if you already have a node, or nodeU()/nodeV() if you don't.");
        }

        public boolean isOrdered() {
            return false;
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EndpointPair)) {
                return false;
            }
            EndpointPair<?> other = (EndpointPair) obj;
            if (isOrdered() != other.isOrdered()) {
                return false;
            }
            if (nodeU().equals(other.nodeU())) {
                return nodeV().equals(other.nodeV());
            }
            if (!nodeU().equals(other.nodeV()) || !nodeV().equals(other.nodeU())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return nodeU().hashCode() + nodeV().hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(nodeU());
            String valueOf2 = String.valueOf(nodeV());
            return new StringBuilder(String.valueOf(valueOf).length() + 4 + String.valueOf(valueOf2).length()).append("[").append(valueOf).append(", ").append(valueOf2).append("]").toString();
        }
    }
}
