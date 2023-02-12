package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.UnmodifiableIterator;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

abstract class MultiEdgesConnecting<E> extends AbstractSet<E> {
    private final Map<E, ?> outEdgeToNode;
    /* access modifiers changed from: private */
    public final Object targetNode;

    MultiEdgesConnecting(Map<E, ?> outEdgeToNode2, Object targetNode2) {
        this.outEdgeToNode = (Map) Preconditions.checkNotNull(outEdgeToNode2);
        this.targetNode = Preconditions.checkNotNull(targetNode2);
    }

    public UnmodifiableIterator<E> iterator() {
        final Iterator<Map.Entry<E, ?>> it = this.outEdgeToNode.entrySet().iterator();
        return new AbstractIterator<E>() {
            /* access modifiers changed from: protected */
            public E computeNext() {
                while (it.hasNext()) {
                    Map.Entry<E, ?> entry = (Map.Entry) it.next();
                    if (MultiEdgesConnecting.this.targetNode.equals(entry.getValue())) {
                        return entry.getKey();
                    }
                }
                return endOfData();
            }
        };
    }

    public boolean contains(@NullableDecl Object edge) {
        return this.targetNode.equals(this.outEdgeToNode.get(edge));
    }
}
