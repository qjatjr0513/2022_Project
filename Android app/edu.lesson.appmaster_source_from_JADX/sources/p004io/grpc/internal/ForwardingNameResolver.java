package p004io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import p004io.grpc.NameResolver;

/* renamed from: io.grpc.internal.ForwardingNameResolver */
abstract class ForwardingNameResolver extends NameResolver {
    private final NameResolver delegate;

    ForwardingNameResolver(NameResolver delegate2) {
        Preconditions.checkNotNull(delegate2, "delegate can not be null");
        this.delegate = delegate2;
    }

    public String getServiceAuthority() {
        return this.delegate.getServiceAuthority();
    }

    @Deprecated
    public void start(NameResolver.Listener listener) {
        this.delegate.start(listener);
    }

    public void start(NameResolver.Listener2 listener) {
        this.delegate.start(listener);
    }

    public void shutdown() {
        this.delegate.shutdown();
    }

    public void refresh() {
        this.delegate.refresh();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) this.delegate).toString();
    }
}
