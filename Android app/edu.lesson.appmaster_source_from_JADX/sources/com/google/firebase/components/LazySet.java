package com.google.firebase.components;

import com.google.firebase.inject.Provider;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class LazySet<T> implements Provider<Set<T>> {
    private volatile Set<T> actualSet = null;
    private volatile Set<Provider<T>> providers = Collections.newSetFromMap(new ConcurrentHashMap());

    LazySet(Collection<Provider<T>> providers2) {
        this.providers.addAll(providers2);
    }

    static LazySet<?> fromCollection(Collection<Provider<?>> providers2) {
        return new LazySet<>((Set) providers2);
    }

    public Set<T> get() {
        if (this.actualSet == null) {
            synchronized (this) {
                if (this.actualSet == null) {
                    this.actualSet = Collections.newSetFromMap(new ConcurrentHashMap());
                    updateSet();
                }
            }
        }
        return Collections.unmodifiableSet(this.actualSet);
    }

    /* access modifiers changed from: package-private */
    public synchronized void add(Provider<T> newProvider) {
        if (this.actualSet == null) {
            this.providers.add(newProvider);
        } else {
            this.actualSet.add(newProvider.get());
        }
    }

    private synchronized void updateSet() {
        for (Provider<T> provider : this.providers) {
            this.actualSet.add(provider.get());
        }
        this.providers = null;
    }
}
