package p004io.grpc.util;

import com.google.common.base.MoreObjects;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import p004io.grpc.ChannelCredentials;
import p004io.grpc.ChannelLogger;
import p004io.grpc.ConnectivityState;
import p004io.grpc.EquivalentAddressGroup;
import p004io.grpc.LoadBalancer;
import p004io.grpc.ManagedChannel;
import p004io.grpc.ManagedChannelBuilder;
import p004io.grpc.NameResolver;
import p004io.grpc.NameResolverRegistry;
import p004io.grpc.SynchronizationContext;

/* renamed from: io.grpc.util.ForwardingLoadBalancerHelper */
public abstract class ForwardingLoadBalancerHelper extends LoadBalancer.Helper {
    /* access modifiers changed from: protected */
    public abstract LoadBalancer.Helper delegate();

    public LoadBalancer.Subchannel createSubchannel(LoadBalancer.CreateSubchannelArgs args) {
        return delegate().createSubchannel(args);
    }

    public ManagedChannel createOobChannel(EquivalentAddressGroup eag, String authority) {
        return delegate().createOobChannel(eag, authority);
    }

    public ManagedChannel createOobChannel(List<EquivalentAddressGroup> eag, String authority) {
        return delegate().createOobChannel(eag, authority);
    }

    public void updateOobChannelAddresses(ManagedChannel channel, EquivalentAddressGroup eag) {
        delegate().updateOobChannelAddresses(channel, eag);
    }

    public void updateOobChannelAddresses(ManagedChannel channel, List<EquivalentAddressGroup> eag) {
        delegate().updateOobChannelAddresses(channel, eag);
    }

    @Deprecated
    public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String target) {
        return delegate().createResolvingOobChannelBuilder(target);
    }

    public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String target, ChannelCredentials creds) {
        return delegate().createResolvingOobChannelBuilder(target, creds);
    }

    public ManagedChannel createResolvingOobChannel(String target) {
        return delegate().createResolvingOobChannel(target);
    }

    public void updateBalancingState(ConnectivityState newState, LoadBalancer.SubchannelPicker newPicker) {
        delegate().updateBalancingState(newState, newPicker);
    }

    public void refreshNameResolution() {
        delegate().refreshNameResolution();
    }

    public void ignoreRefreshNameResolutionCheck() {
        delegate().ignoreRefreshNameResolutionCheck();
    }

    public String getAuthority() {
        return delegate().getAuthority();
    }

    public ChannelCredentials getChannelCredentials() {
        return delegate().getChannelCredentials();
    }

    public ChannelCredentials getUnsafeChannelCredentials() {
        return delegate().getUnsafeChannelCredentials();
    }

    public SynchronizationContext getSynchronizationContext() {
        return delegate().getSynchronizationContext();
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return delegate().getScheduledExecutorService();
    }

    public ChannelLogger getChannelLogger() {
        return delegate().getChannelLogger();
    }

    public NameResolver.Args getNameResolverArgs() {
        return delegate().getNameResolverArgs();
    }

    public NameResolverRegistry getNameResolverRegistry() {
        return delegate().getNameResolverRegistry();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
