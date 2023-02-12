package p004io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.net.URI;
import p004io.grpc.InternalServiceProviders;
import p004io.grpc.NameResolver;
import p004io.grpc.NameResolverProvider;

/* renamed from: io.grpc.internal.DnsNameResolverProvider */
public final class DnsNameResolverProvider extends NameResolverProvider {
    private static final String SCHEME = "dns";

    public DnsNameResolver newNameResolver(URI targetUri, NameResolver.Args args) {
        if (!SCHEME.equals(targetUri.getScheme())) {
            return null;
        }
        String targetPath = (String) Preconditions.checkNotNull(targetUri.getPath(), "targetPath");
        Preconditions.checkArgument(targetPath.startsWith("/"), "the path component (%s) of the target (%s) must start with '/'", (Object) targetPath, (Object) targetUri);
        return new DnsNameResolver(targetUri.getAuthority(), targetPath.substring(1), args, GrpcUtil.SHARED_CHANNEL_EXECUTOR, Stopwatch.createUnstarted(), InternalServiceProviders.isAndroid(getClass().getClassLoader()));
    }

    public String getDefaultScheme() {
        return SCHEME;
    }

    /* access modifiers changed from: protected */
    public boolean isAvailable() {
        return true;
    }

    public int priority() {
        return 5;
    }
}
