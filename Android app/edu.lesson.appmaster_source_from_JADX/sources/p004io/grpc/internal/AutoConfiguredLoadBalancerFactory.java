package p004io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import p004io.grpc.Attributes;
import p004io.grpc.ChannelLogger;
import p004io.grpc.ConnectivityState;
import p004io.grpc.ConnectivityStateInfo;
import p004io.grpc.EquivalentAddressGroup;
import p004io.grpc.LoadBalancer;
import p004io.grpc.LoadBalancerProvider;
import p004io.grpc.LoadBalancerRegistry;
import p004io.grpc.NameResolver;
import p004io.grpc.Status;
import p004io.grpc.internal.ServiceConfigUtil;

/* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory */
public final class AutoConfiguredLoadBalancerFactory {
    /* access modifiers changed from: private */
    public final String defaultPolicy;
    /* access modifiers changed from: private */
    public final LoadBalancerRegistry registry;

    public AutoConfiguredLoadBalancerFactory(String defaultPolicy2) {
        this(LoadBalancerRegistry.getDefaultRegistry(), defaultPolicy2);
    }

    AutoConfiguredLoadBalancerFactory(LoadBalancerRegistry registry2, String defaultPolicy2) {
        this.registry = (LoadBalancerRegistry) Preconditions.checkNotNull(registry2, "registry");
        this.defaultPolicy = (String) Preconditions.checkNotNull(defaultPolicy2, "defaultPolicy");
    }

    public AutoConfiguredLoadBalancer newLoadBalancer(LoadBalancer.Helper helper) {
        return new AutoConfiguredLoadBalancer(helper);
    }

    /* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory$NoopLoadBalancer */
    private static final class NoopLoadBalancer extends LoadBalancer {
        private NoopLoadBalancer() {
        }

        @Deprecated
        public void handleResolvedAddressGroups(List<EquivalentAddressGroup> list, Attributes a) {
        }

        public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        }

        public void handleNameResolutionError(Status error) {
        }

        public void shutdown() {
        }
    }

    /* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory$AutoConfiguredLoadBalancer */
    public final class AutoConfiguredLoadBalancer {
        private LoadBalancer delegate;
        private LoadBalancerProvider delegateProvider;
        private final LoadBalancer.Helper helper;

        AutoConfiguredLoadBalancer(LoadBalancer.Helper helper2) {
            this.helper = helper2;
            LoadBalancerProvider provider = AutoConfiguredLoadBalancerFactory.this.registry.getProvider(AutoConfiguredLoadBalancerFactory.this.defaultPolicy);
            this.delegateProvider = provider;
            if (provider != null) {
                this.delegate = provider.newLoadBalancer(helper2);
                return;
            }
            throw new IllegalStateException("Could not find policy '" + AutoConfiguredLoadBalancerFactory.this.defaultPolicy + "'. Make sure its implementation is either registered to LoadBalancerRegistry or included in META-INF/services/io.grpc.LoadBalancerProvider from your jar files.");
        }

        /* access modifiers changed from: package-private */
        public Status tryHandleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
            List<EquivalentAddressGroup> servers = resolvedAddresses.getAddresses();
            Attributes attributes = resolvedAddresses.getAttributes();
            ServiceConfigUtil.PolicySelection policySelection = (ServiceConfigUtil.PolicySelection) resolvedAddresses.getLoadBalancingPolicyConfig();
            if (policySelection == null) {
                try {
                    AutoConfiguredLoadBalancerFactory autoConfiguredLoadBalancerFactory = AutoConfiguredLoadBalancerFactory.this;
                    policySelection = new ServiceConfigUtil.PolicySelection(autoConfiguredLoadBalancerFactory.getProviderOrThrow(autoConfiguredLoadBalancerFactory.defaultPolicy, "using default policy"), (Object) null);
                } catch (PolicyException e) {
                    this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new FailingPicker(Status.INTERNAL.withDescription(e.getMessage())));
                    this.delegate.shutdown();
                    this.delegateProvider = null;
                    this.delegate = new NoopLoadBalancer();
                    return Status.f313OK;
                }
            }
            if (this.delegateProvider == null || !policySelection.provider.getPolicyName().equals(this.delegateProvider.getPolicyName())) {
                this.helper.updateBalancingState(ConnectivityState.CONNECTING, new EmptyPicker());
                this.delegate.shutdown();
                LoadBalancerProvider loadBalancerProvider = policySelection.provider;
                this.delegateProvider = loadBalancerProvider;
                LoadBalancer old = this.delegate;
                this.delegate = loadBalancerProvider.newLoadBalancer(this.helper);
                this.helper.getChannelLogger().log(ChannelLogger.ChannelLogLevel.INFO, "Load balancer changed from {0} to {1}", old.getClass().getSimpleName(), this.delegate.getClass().getSimpleName());
            }
            Object lbConfig = policySelection.config;
            if (lbConfig != null) {
                this.helper.getChannelLogger().log(ChannelLogger.ChannelLogLevel.DEBUG, "Load-balancing config: {0}", policySelection.config);
            }
            LoadBalancer delegate2 = getDelegate();
            if (resolvedAddresses.getAddresses().isEmpty() && !delegate2.canHandleEmptyAddressListFromNameResolution()) {
                return Status.UNAVAILABLE.withDescription("NameResolver returned no usable address. addrs=" + servers + ", attrs=" + attributes);
            }
            delegate2.handleResolvedAddresses(LoadBalancer.ResolvedAddresses.newBuilder().setAddresses(resolvedAddresses.getAddresses()).setAttributes(attributes).setLoadBalancingPolicyConfig(lbConfig).build());
            return Status.f313OK;
        }

        /* access modifiers changed from: package-private */
        public void handleNameResolutionError(Status error) {
            getDelegate().handleNameResolutionError(error);
        }

        /* access modifiers changed from: package-private */
        @Deprecated
        public void handleSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo stateInfo) {
            getDelegate().handleSubchannelState(subchannel, stateInfo);
        }

        /* access modifiers changed from: package-private */
        public void requestConnection() {
            getDelegate().requestConnection();
        }

        /* access modifiers changed from: package-private */
        public void shutdown() {
            this.delegate.shutdown();
            this.delegate = null;
        }

        public LoadBalancer getDelegate() {
            return this.delegate;
        }

        /* access modifiers changed from: package-private */
        public void setDelegate(LoadBalancer lb) {
            this.delegate = lb;
        }

        /* access modifiers changed from: package-private */
        public LoadBalancerProvider getDelegateProvider() {
            return this.delegateProvider;
        }
    }

    /* access modifiers changed from: private */
    public LoadBalancerProvider getProviderOrThrow(String policy, String choiceReason) throws PolicyException {
        LoadBalancerProvider provider = this.registry.getProvider(policy);
        if (provider != null) {
            return provider;
        }
        throw new PolicyException("Trying to load '" + policy + "' because " + choiceReason + ", but it's unavailable");
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public NameResolver.ConfigOrError parseLoadBalancerPolicy(Map<String, ?> serviceConfig) {
        List<ServiceConfigUtil.LbConfig> loadBalancerConfigs = null;
        if (serviceConfig != null) {
            try {
                loadBalancerConfigs = ServiceConfigUtil.unwrapLoadBalancingConfigList(ServiceConfigUtil.getLoadBalancingConfigsFromServiceConfig(serviceConfig));
            } catch (RuntimeException e) {
                return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("can't parse load balancer configuration").withCause(e));
            }
        }
        if (loadBalancerConfigs == null || loadBalancerConfigs.isEmpty()) {
            return null;
        }
        return ServiceConfigUtil.selectLbPolicyFromList(loadBalancerConfigs, this.registry);
    }

    /* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory$PolicyException */
    static final class PolicyException extends Exception {
        private static final long serialVersionUID = 1;

        private PolicyException(String msg) {
            super(msg);
        }
    }

    /* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory$EmptyPicker */
    private static final class EmptyPicker extends LoadBalancer.SubchannelPicker {
        private EmptyPicker() {
        }

        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs args) {
            return LoadBalancer.PickResult.withNoResult();
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) EmptyPicker.class).toString();
        }
    }

    /* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory$FailingPicker */
    private static final class FailingPicker extends LoadBalancer.SubchannelPicker {
        private final Status failure;

        FailingPicker(Status failure2) {
            this.failure = failure2;
        }

        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs args) {
            return LoadBalancer.PickResult.withError(this.failure);
        }
    }
}
