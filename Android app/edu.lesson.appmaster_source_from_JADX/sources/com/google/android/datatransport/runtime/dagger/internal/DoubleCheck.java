package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;
import javax.inject.Provider;

public final class DoubleCheck<T> implements Provider<T>, Lazy<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider<T> provider;

    private DoubleCheck(Provider<T> provider2) {
        if (provider2 != null) {
            this.provider = provider2;
            return;
        }
        throw new AssertionError();
    }

    public T get() {
        Object result = this.instance;
        Object obj = UNINITIALIZED;
        if (result == obj) {
            synchronized (this) {
                result = this.instance;
                if (result == obj) {
                    result = this.provider.get();
                    this.instance = reentrantCheck(this.instance, result);
                    this.provider = null;
                }
            }
        }
        return result;
    }

    public static Object reentrantCheck(Object currentInstance, Object newInstance) {
        if (!(currentInstance != UNINITIALIZED && !(currentInstance instanceof MemoizedSentinel)) || currentInstance == newInstance) {
            return newInstance;
        }
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + currentInstance + " & " + newInstance + ". This is likely due to a circular dependency.");
    }

    public static <P extends Provider<T>, T> Provider<T> provider(P delegate) {
        Preconditions.checkNotNull(delegate);
        if (delegate instanceof DoubleCheck) {
            return delegate;
        }
        return new DoubleCheck(delegate);
    }

    public static <P extends Provider<T>, T> Lazy<T> lazy(P provider2) {
        if (provider2 instanceof Lazy) {
            return (Lazy) provider2;
        }
        return new DoubleCheck((Provider) Preconditions.checkNotNull(provider2));
    }
}
