package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

class OptionalProvider<T> implements Provider<T>, Deferred<T> {
    private static final Provider<Object> EMPTY_PROVIDER = OptionalProvider$$ExternalSyntheticLambda2.INSTANCE;
    private static final Deferred.DeferredHandler<Object> NOOP_HANDLER = OptionalProvider$$ExternalSyntheticLambda1.INSTANCE;
    private volatile Provider<T> delegate;
    private Deferred.DeferredHandler<T> handler;

    static /* synthetic */ void lambda$static$0(Provider p) {
    }

    static /* synthetic */ Object lambda$static$1() {
        return null;
    }

    private OptionalProvider(Deferred.DeferredHandler<T> handler2, Provider<T> provider) {
        this.handler = handler2;
        this.delegate = provider;
    }

    static <T> OptionalProvider<T> empty() {
        return new OptionalProvider<>(NOOP_HANDLER, EMPTY_PROVIDER);
    }

    /* renamed from: of */
    static <T> OptionalProvider<T> m185of(Provider<T> provider) {
        return new OptionalProvider<>((Deferred.DeferredHandler) null, provider);
    }

    public T get() {
        return this.delegate.get();
    }

    /* access modifiers changed from: package-private */
    public void set(Provider<T> provider) {
        Deferred.DeferredHandler<T> localHandler;
        if (this.delegate == EMPTY_PROVIDER) {
            synchronized (this) {
                localHandler = this.handler;
                this.handler = null;
                this.delegate = provider;
            }
            localHandler.handle(provider);
            return;
        }
        throw new IllegalStateException("provide() can be called only once.");
    }

    public void whenAvailable(Deferred.DeferredHandler<T> handler2) {
        Provider<T> provider;
        Provider<T> provider2 = this.delegate;
        Provider<Object> provider3 = EMPTY_PROVIDER;
        if (provider2 != provider3) {
            handler2.handle(provider2);
            return;
        }
        Provider<T> toRun = null;
        synchronized (this) {
            provider = this.delegate;
            if (provider != provider3) {
                toRun = provider;
            } else {
                this.handler = new OptionalProvider$$ExternalSyntheticLambda0(this.handler, handler2);
            }
        }
        if (toRun != null) {
            handler2.handle(provider);
        }
    }

    static /* synthetic */ void lambda$whenAvailable$2(Deferred.DeferredHandler existingHandler, Deferred.DeferredHandler handler2, Provider p) {
        existingHandler.handle(p);
        handler2.handle(p);
    }
}
