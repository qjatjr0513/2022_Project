package p004io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import p004io.grpc.CallOptions;
import p004io.grpc.InternalConfigSelector;
import p004io.grpc.LoadBalancer;
import p004io.grpc.MethodDescriptor;
import p004io.grpc.Status;
import p004io.grpc.internal.RetriableStream;

/* renamed from: io.grpc.internal.ManagedChannelServiceConfig */
final class ManagedChannelServiceConfig {
    @Nullable
    private final MethodInfo defaultMethodConfig;
    @Nullable
    private final Map<String, ?> healthCheckingConfig;
    @Nullable
    private final Object loadBalancingConfig;
    @Nullable
    private final RetriableStream.Throttle retryThrottling;
    private final Map<String, MethodInfo> serviceMap;
    private final Map<String, MethodInfo> serviceMethodMap;

    ManagedChannelServiceConfig(@Nullable MethodInfo defaultMethodConfig2, Map<String, MethodInfo> serviceMethodMap2, Map<String, MethodInfo> serviceMap2, @Nullable RetriableStream.Throttle retryThrottling2, @Nullable Object loadBalancingConfig2, @Nullable Map<String, ?> healthCheckingConfig2) {
        Map<String, ?> map;
        this.defaultMethodConfig = defaultMethodConfig2;
        this.serviceMethodMap = Collections.unmodifiableMap(new HashMap(serviceMethodMap2));
        this.serviceMap = Collections.unmodifiableMap(new HashMap(serviceMap2));
        this.retryThrottling = retryThrottling2;
        this.loadBalancingConfig = loadBalancingConfig2;
        if (healthCheckingConfig2 != null) {
            map = Collections.unmodifiableMap(new HashMap(healthCheckingConfig2));
        } else {
            map = null;
        }
        this.healthCheckingConfig = map;
    }

    static ManagedChannelServiceConfig empty() {
        return new ManagedChannelServiceConfig((MethodInfo) null, new HashMap(), new HashMap(), (RetriableStream.Throttle) null, (Object) null, (Map<String, ?>) null);
    }

    static ManagedChannelServiceConfig fromServiceConfig(Map<String, ?> serviceConfig, boolean retryEnabled, int maxRetryAttemptsLimit, int maxHedgedAttemptsLimit, @Nullable Object loadBalancingConfig2) {
        List<Map<String, ?>> methodConfigs;
        Iterator<Map<String, ?>> it;
        Map<String, ?> methodConfig;
        boolean z = retryEnabled;
        RetriableStream.Throttle retryThrottling2 = null;
        if (z) {
            retryThrottling2 = ServiceConfigUtil.getThrottlePolicy(serviceConfig);
        }
        Map<String, MethodInfo> serviceMethodMap2 = new HashMap<>();
        Map<String, MethodInfo> serviceMap2 = new HashMap<>();
        Map<String, ?> healthCheckingConfig2 = ServiceConfigUtil.getHealthCheckedService(serviceConfig);
        List<Map<String, ?>> methodConfigs2 = ServiceConfigUtil.getMethodConfigFromServiceConfig(serviceConfig);
        if (methodConfigs2 == null) {
            return new ManagedChannelServiceConfig((MethodInfo) null, serviceMethodMap2, serviceMap2, retryThrottling2, loadBalancingConfig2, healthCheckingConfig2);
        }
        Iterator<Map<String, ?>> it2 = methodConfigs2.iterator();
        MethodInfo defaultMethodConfig2 = null;
        while (it2.hasNext()) {
            Map<String, ?> methodConfig2 = it2.next();
            MethodInfo info = new MethodInfo(methodConfig2, z, maxRetryAttemptsLimit, maxHedgedAttemptsLimit);
            List<Map<String, ?>> nameList = ServiceConfigUtil.getNameListFromMethodConfig(methodConfig2);
            if (nameList == null) {
                Map<String, ?> map = methodConfig2;
            } else if (!nameList.isEmpty()) {
                for (Map<String, ?> name : nameList) {
                    String serviceName = ServiceConfigUtil.getServiceFromName(name);
                    String methodName = ServiceConfigUtil.getMethodFromName(name);
                    boolean z2 = true;
                    if (Strings.isNullOrEmpty(serviceName)) {
                        methodConfig = methodConfig2;
                        it = it2;
                        Preconditions.checkArgument(Strings.isNullOrEmpty(methodName), "missing service name for method %s", (Object) methodName);
                        if (defaultMethodConfig2 != null) {
                            z2 = false;
                        }
                        methodConfigs = methodConfigs2;
                        Preconditions.checkArgument(z2, "Duplicate default method config in service config %s", (Object) serviceConfig);
                        defaultMethodConfig2 = info;
                    } else {
                        methodConfig = methodConfig2;
                        it = it2;
                        methodConfigs = methodConfigs2;
                        Map<String, ?> map2 = serviceConfig;
                        if (Strings.isNullOrEmpty(methodName)) {
                            Preconditions.checkArgument(!serviceMap2.containsKey(serviceName), "Duplicate service %s", (Object) serviceName);
                            serviceMap2.put(serviceName, info);
                        } else {
                            String fullMethodName = MethodDescriptor.generateFullMethodName(serviceName, methodName);
                            String str = methodName;
                            Preconditions.checkArgument(!serviceMethodMap2.containsKey(fullMethodName), "Duplicate method name %s", (Object) fullMethodName);
                            serviceMethodMap2.put(fullMethodName, info);
                        }
                    }
                    boolean z3 = retryEnabled;
                    methodConfig2 = methodConfig;
                    it2 = it;
                    methodConfigs2 = methodConfigs;
                }
                Iterator<Map<String, ?>> it3 = it2;
                List<Map<String, ?>> methodConfigs3 = methodConfigs2;
                Map<String, ?> map3 = serviceConfig;
                z = retryEnabled;
                methodConfigs2 = methodConfigs3;
            }
            List<Map<String, ?>> methodConfigs4 = methodConfigs2;
            Map<String, ?> map4 = serviceConfig;
            z = retryEnabled;
            it2 = it2;
            methodConfigs2 = methodConfigs4;
        }
        return new ManagedChannelServiceConfig(defaultMethodConfig2, serviceMethodMap2, serviceMap2, retryThrottling2, loadBalancingConfig2, healthCheckingConfig2);
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Map<String, ?> getHealthCheckingConfig() {
        return this.healthCheckingConfig;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public InternalConfigSelector getDefaultConfigSelector() {
        if (!this.serviceMap.isEmpty() || !this.serviceMethodMap.isEmpty() || this.defaultMethodConfig != null) {
            return new ServiceConfigConvertedSelector();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Object getLoadBalancingConfig() {
        return this.loadBalancingConfig;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public RetriableStream.Throttle getRetryThrottling() {
        return this.retryThrottling;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public MethodInfo getMethodConfig(MethodDescriptor<?, ?> method) {
        MethodInfo methodInfo = this.serviceMethodMap.get(method.getFullMethodName());
        if (methodInfo == null) {
            methodInfo = this.serviceMap.get(method.getServiceName());
        }
        if (methodInfo == null) {
            return this.defaultMethodConfig;
        }
        return methodInfo;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ManagedChannelServiceConfig that = (ManagedChannelServiceConfig) o;
        if (!Objects.equal(this.defaultMethodConfig, that.defaultMethodConfig) || !Objects.equal(this.serviceMethodMap, that.serviceMethodMap) || !Objects.equal(this.serviceMap, that.serviceMap) || !Objects.equal(this.retryThrottling, that.retryThrottling) || !Objects.equal(this.loadBalancingConfig, that.loadBalancingConfig)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.defaultMethodConfig, this.serviceMethodMap, this.serviceMap, this.retryThrottling, this.loadBalancingConfig);
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("defaultMethodConfig", (Object) this.defaultMethodConfig).add("serviceMethodMap", (Object) this.serviceMethodMap).add("serviceMap", (Object) this.serviceMap).add("retryThrottling", (Object) this.retryThrottling).add("loadBalancingConfig", this.loadBalancingConfig).toString();
    }

    /* renamed from: io.grpc.internal.ManagedChannelServiceConfig$MethodInfo */
    static final class MethodInfo {
        static final CallOptions.Key<MethodInfo> KEY = CallOptions.Key.create("io.grpc.internal.ManagedChannelServiceConfig.MethodInfo");
        final HedgingPolicy hedgingPolicy;
        final Integer maxInboundMessageSize;
        final Integer maxOutboundMessageSize;
        final RetryPolicy retryPolicy;
        final Long timeoutNanos;
        final Boolean waitForReady;

        MethodInfo(Map<String, ?> methodConfig, boolean retryEnabled, int maxRetryAttemptsLimit, int maxHedgedAttemptsLimit) {
            this.timeoutNanos = ServiceConfigUtil.getTimeoutFromMethodConfig(methodConfig);
            this.waitForReady = ServiceConfigUtil.getWaitForReadyFromMethodConfig(methodConfig);
            Integer maxResponseMessageBytesFromMethodConfig = ServiceConfigUtil.getMaxResponseMessageBytesFromMethodConfig(methodConfig);
            this.maxInboundMessageSize = maxResponseMessageBytesFromMethodConfig;
            boolean z = true;
            if (maxResponseMessageBytesFromMethodConfig != null) {
                Preconditions.checkArgument(maxResponseMessageBytesFromMethodConfig.intValue() >= 0, "maxInboundMessageSize %s exceeds bounds", (Object) maxResponseMessageBytesFromMethodConfig);
            }
            Integer maxRequestMessageBytesFromMethodConfig = ServiceConfigUtil.getMaxRequestMessageBytesFromMethodConfig(methodConfig);
            this.maxOutboundMessageSize = maxRequestMessageBytesFromMethodConfig;
            if (maxRequestMessageBytesFromMethodConfig != null) {
                Preconditions.checkArgument(maxRequestMessageBytesFromMethodConfig.intValue() < 0 ? false : z, "maxOutboundMessageSize %s exceeds bounds", (Object) maxRequestMessageBytesFromMethodConfig);
            }
            HedgingPolicy hedgingPolicy2 = null;
            Map<String, ?> retryPolicyMap = retryEnabled ? ServiceConfigUtil.getRetryPolicyFromMethodConfig(methodConfig) : null;
            this.retryPolicy = retryPolicyMap == null ? null : retryPolicy(retryPolicyMap, maxRetryAttemptsLimit);
            Map<String, ?> hedgingPolicyMap = retryEnabled ? ServiceConfigUtil.getHedgingPolicyFromMethodConfig(methodConfig) : null;
            this.hedgingPolicy = hedgingPolicyMap != null ? hedgingPolicy(hedgingPolicyMap, maxHedgedAttemptsLimit) : hedgingPolicy2;
        }

        public int hashCode() {
            return Objects.hashCode(this.timeoutNanos, this.waitForReady, this.maxInboundMessageSize, this.maxOutboundMessageSize, this.retryPolicy, this.hedgingPolicy);
        }

        public boolean equals(Object other) {
            if (!(other instanceof MethodInfo)) {
                return false;
            }
            MethodInfo that = (MethodInfo) other;
            if (!Objects.equal(this.timeoutNanos, that.timeoutNanos) || !Objects.equal(this.waitForReady, that.waitForReady) || !Objects.equal(this.maxInboundMessageSize, that.maxInboundMessageSize) || !Objects.equal(this.maxOutboundMessageSize, that.maxOutboundMessageSize) || !Objects.equal(this.retryPolicy, that.retryPolicy) || !Objects.equal(this.hedgingPolicy, that.hedgingPolicy)) {
                return false;
            }
            return true;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("timeoutNanos", (Object) this.timeoutNanos).add("waitForReady", (Object) this.waitForReady).add("maxInboundMessageSize", (Object) this.maxInboundMessageSize).add("maxOutboundMessageSize", (Object) this.maxOutboundMessageSize).add("retryPolicy", (Object) this.retryPolicy).add("hedgingPolicy", (Object) this.hedgingPolicy).toString();
        }

        private static RetryPolicy retryPolicy(Map<String, ?> retryPolicy2, int maxAttemptsLimit) {
            int maxAttempts = ((Integer) Preconditions.checkNotNull(ServiceConfigUtil.getMaxAttemptsFromRetryPolicy(retryPolicy2), "maxAttempts cannot be empty")).intValue();
            boolean z = true;
            Preconditions.checkArgument(maxAttempts >= 2, "maxAttempts must be greater than 1: %s", maxAttempts);
            int maxAttempts2 = Math.min(maxAttempts, maxAttemptsLimit);
            long initialBackoffNanos = ((Long) Preconditions.checkNotNull(ServiceConfigUtil.getInitialBackoffNanosFromRetryPolicy(retryPolicy2), "initialBackoff cannot be empty")).longValue();
            Preconditions.checkArgument(initialBackoffNanos > 0, "initialBackoffNanos must be greater than 0: %s", initialBackoffNanos);
            long maxBackoffNanos = ((Long) Preconditions.checkNotNull(ServiceConfigUtil.getMaxBackoffNanosFromRetryPolicy(retryPolicy2), "maxBackoff cannot be empty")).longValue();
            Preconditions.checkArgument(maxBackoffNanos > 0, "maxBackoff must be greater than 0: %s", maxBackoffNanos);
            double backoffMultiplier = ((Double) Preconditions.checkNotNull(ServiceConfigUtil.getBackoffMultiplierFromRetryPolicy(retryPolicy2), "backoffMultiplier cannot be empty")).doubleValue();
            Preconditions.checkArgument(backoffMultiplier > 0.0d, "backoffMultiplier must be greater than 0: %s", (Object) Double.valueOf(backoffMultiplier));
            Long perAttemptRecvTimeout = ServiceConfigUtil.getPerAttemptRecvTimeoutNanosFromRetryPolicy(retryPolicy2);
            Preconditions.checkArgument(perAttemptRecvTimeout == null || perAttemptRecvTimeout.longValue() >= 0, "perAttemptRecvTimeout cannot be negative: %s", (Object) perAttemptRecvTimeout);
            Set<Status.Code> retryableCodes = ServiceConfigUtil.getRetryableStatusCodesFromRetryPolicy(retryPolicy2);
            if (perAttemptRecvTimeout == null && retryableCodes.isEmpty()) {
                z = false;
            }
            Preconditions.checkArgument(z, "retryableStatusCodes cannot be empty without perAttemptRecvTimeout");
            long j = maxBackoffNanos;
            return new RetryPolicy(maxAttempts2, initialBackoffNanos, maxBackoffNanos, backoffMultiplier, perAttemptRecvTimeout, retryableCodes);
        }

        private static HedgingPolicy hedgingPolicy(Map<String, ?> hedgingPolicy2, int maxAttemptsLimit) {
            int maxAttempts = ((Integer) Preconditions.checkNotNull(ServiceConfigUtil.getMaxAttemptsFromHedgingPolicy(hedgingPolicy2), "maxAttempts cannot be empty")).intValue();
            boolean z = true;
            Preconditions.checkArgument(maxAttempts >= 2, "maxAttempts must be greater than 1: %s", maxAttempts);
            int maxAttempts2 = Math.min(maxAttempts, maxAttemptsLimit);
            long hedgingDelayNanos = ((Long) Preconditions.checkNotNull(ServiceConfigUtil.getHedgingDelayNanosFromHedgingPolicy(hedgingPolicy2), "hedgingDelay cannot be empty")).longValue();
            if (hedgingDelayNanos < 0) {
                z = false;
            }
            Preconditions.checkArgument(z, "hedgingDelay must not be negative: %s", hedgingDelayNanos);
            return new HedgingPolicy(maxAttempts2, hedgingDelayNanos, ServiceConfigUtil.getNonFatalStatusCodesFromHedgingPolicy(hedgingPolicy2));
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelServiceConfig$ServiceConfigConvertedSelector */
    static final class ServiceConfigConvertedSelector extends InternalConfigSelector {
        final ManagedChannelServiceConfig config;

        private ServiceConfigConvertedSelector(ManagedChannelServiceConfig config2) {
            this.config = config2;
        }

        public InternalConfigSelector.Result selectConfig(LoadBalancer.PickSubchannelArgs args) {
            return InternalConfigSelector.Result.newBuilder().setConfig(this.config).build();
        }
    }
}
