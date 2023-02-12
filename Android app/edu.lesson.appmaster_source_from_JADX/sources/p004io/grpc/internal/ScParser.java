package p004io.grpc.internal;

import com.google.common.base.Preconditions;
import java.util.Map;
import p004io.grpc.NameResolver;
import p004io.grpc.Status;

/* renamed from: io.grpc.internal.ScParser */
public final class ScParser extends NameResolver.ServiceConfigParser {
    private final AutoConfiguredLoadBalancerFactory autoLoadBalancerFactory;
    private final int maxHedgedAttemptsLimit;
    private final int maxRetryAttemptsLimit;
    private final boolean retryEnabled;

    public ScParser(boolean retryEnabled2, int maxRetryAttemptsLimit2, int maxHedgedAttemptsLimit2, AutoConfiguredLoadBalancerFactory autoLoadBalancerFactory2) {
        this.retryEnabled = retryEnabled2;
        this.maxRetryAttemptsLimit = maxRetryAttemptsLimit2;
        this.maxHedgedAttemptsLimit = maxHedgedAttemptsLimit2;
        this.autoLoadBalancerFactory = (AutoConfiguredLoadBalancerFactory) Preconditions.checkNotNull(autoLoadBalancerFactory2, "autoLoadBalancerFactory");
    }

    public NameResolver.ConfigOrError parseServiceConfig(Map<String, ?> rawServiceConfig) {
        Object loadBalancingPolicySelection;
        try {
            NameResolver.ConfigOrError choiceFromLoadBalancer = this.autoLoadBalancerFactory.parseLoadBalancerPolicy(rawServiceConfig);
            if (choiceFromLoadBalancer == null) {
                loadBalancingPolicySelection = null;
            } else if (choiceFromLoadBalancer.getError() != null) {
                return NameResolver.ConfigOrError.fromError(choiceFromLoadBalancer.getError());
            } else {
                loadBalancingPolicySelection = choiceFromLoadBalancer.getConfig();
            }
            return NameResolver.ConfigOrError.fromConfig(ManagedChannelServiceConfig.fromServiceConfig(rawServiceConfig, this.retryEnabled, this.maxRetryAttemptsLimit, this.maxHedgedAttemptsLimit, loadBalancingPolicySelection));
        } catch (RuntimeException e) {
            return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("failed to parse service config").withCause(e));
        }
    }
}
