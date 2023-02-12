package com.google.common.graph;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

final class UndirectedNetworkConnections<N, E> extends AbstractUndirectedNetworkConnections<N, E> {
    protected UndirectedNetworkConnections(Map<E, N> incidentEdgeMap) {
        super(incidentEdgeMap);
    }

    /* renamed from: of */
    static <N, E> UndirectedNetworkConnections<N, E> m144of() {
        return new UndirectedNetworkConnections<>(HashBiMap.create(2));
    }

    static <N, E> UndirectedNetworkConnections<N, E> ofImmutable(Map<E, N> incidentEdges) {
        return new UndirectedNetworkConnections<>(ImmutableBiMap.copyOf(incidentEdges));
    }

    public Set<N> adjacentNodes() {
        return Collections.unmodifiableSet(((BiMap) this.incidentEdgeMap).values());
    }

    public Set<E> edgesConnecting(N node) {
        return new EdgesConnecting(((BiMap) this.incidentEdgeMap).inverse(), node);
    }
}
