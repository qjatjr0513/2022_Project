package p004io.grpc;

import p004io.grpc.NameResolver;

/* renamed from: io.grpc.NameResolverProvider */
public abstract class NameResolverProvider extends NameResolver.Factory {
    /* access modifiers changed from: protected */
    public abstract boolean isAvailable();

    /* access modifiers changed from: protected */
    public abstract int priority();

    /* access modifiers changed from: protected */
    public String getScheme() {
        return getDefaultScheme();
    }
}
