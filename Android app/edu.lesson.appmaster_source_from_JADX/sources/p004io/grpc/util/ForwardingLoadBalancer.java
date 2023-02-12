package p004io.grpc.util;

import com.google.common.base.MoreObjects;
import java.util.List;
import p004io.grpc.Attributes;
import p004io.grpc.ConnectivityStateInfo;
import p004io.grpc.EquivalentAddressGroup;
import p004io.grpc.LoadBalancer;
import p004io.grpc.Status;

/* renamed from: io.grpc.util.ForwardingLoadBalancer */
public abstract class ForwardingLoadBalancer extends LoadBalancer {
    /* access modifiers changed from: protected */
    public abstract LoadBalancer delegate();

    @Deprecated
    public void handleResolvedAddressGroups(List<EquivalentAddressGroup> servers, Attributes attributes) {
        delegate().handleResolvedAddressGroups(servers, attributes);
    }

    public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        delegate().handleResolvedAddresses(resolvedAddresses);
    }

    public void handleNameResolutionError(Status error) {
        delegate().handleNameResolutionError(error);
    }

    @Deprecated
    public void handleSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo stateInfo) {
        delegate().handleSubchannelState(subchannel, stateInfo);
    }

    public void shutdown() {
        delegate().shutdown();
    }

    public boolean canHandleEmptyAddressListFromNameResolution() {
        return delegate().canHandleEmptyAddressListFromNameResolution();
    }

    public void requestConnection() {
        delegate().requestConnection();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
