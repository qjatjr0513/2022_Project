package com.google.common.util.concurrent;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AggregateFuture;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

abstract class CollectionFuture<V, C> extends AggregateFuture<V, C> {
    private List<Present<V>> values;

    /* access modifiers changed from: package-private */
    public abstract C combine(List<Present<V>> list);

    CollectionFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> futures, boolean allMustSucceed) {
        super(futures, allMustSucceed, true);
        List<Present<V>> list;
        if (futures.isEmpty()) {
            list = ImmutableList.m41of();
        } else {
            list = Lists.newArrayListWithCapacity(futures.size());
        }
        List<Present<V>> values2 = list;
        for (int i = 0; i < futures.size(); i++) {
            values2.add((Object) null);
        }
        this.values = values2;
    }

    /* access modifiers changed from: package-private */
    public final void collectOneValue(int index, @NullableDecl V returnValue) {
        List<Present<V>> localValues = this.values;
        if (localValues != null) {
            localValues.set(index, new Present(returnValue));
        }
    }

    /* access modifiers changed from: package-private */
    public final void handleAllCompleted() {
        List<Present<V>> localValues = this.values;
        if (localValues != null) {
            set(combine(localValues));
        }
    }

    /* access modifiers changed from: package-private */
    public void releaseResources(AggregateFuture.ReleaseResourcesReason reason) {
        super.releaseResources(reason);
        this.values = null;
    }

    static final class ListFuture<V> extends CollectionFuture<V, List<V>> {
        ListFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> futures, boolean allMustSucceed) {
            super(futures, allMustSucceed);
            init();
        }

        public List<V> combine(List<Present<V>> values) {
            List<V> result = Lists.newArrayListWithCapacity(values.size());
            Iterator<Present<V>> it = values.iterator();
            while (it.hasNext()) {
                Present<V> element = it.next();
                result.add(element != null ? element.value : null);
            }
            return Collections.unmodifiableList(result);
        }
    }

    private static final class Present<V> {
        V value;

        Present(V value2) {
            this.value = value2;
        }
    }
}
