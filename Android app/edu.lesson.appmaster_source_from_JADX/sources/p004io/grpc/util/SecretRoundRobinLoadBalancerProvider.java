package p004io.grpc.util;

import java.util.Map;
import p004io.grpc.LoadBalancer;
import p004io.grpc.LoadBalancerProvider;
import p004io.grpc.NameResolver;

/* renamed from: io.grpc.util.SecretRoundRobinLoadBalancerProvider */
final class SecretRoundRobinLoadBalancerProvider {
    private SecretRoundRobinLoadBalancerProvider() {
    }

    /* renamed from: io.grpc.util.SecretRoundRobinLoadBalancerProvider$Provider */
    public static final class Provider extends LoadBalancerProvider {
        private static final String NO_CONFIG = "no service config";

        public boolean isAvailable() {
            return true;
        }

        public int getPriority() {
            return 5;
        }

        public String getPolicyName() {
            return "round_robin";
        }

        public LoadBalancer newLoadBalancer(LoadBalancer.Helper helper) {
            return new RoundRobinLoadBalancer(helper);
        }

        public NameResolver.ConfigOrError parseLoadBalancingPolicyConfig(Map<String, ?> map) {
            return NameResolver.ConfigOrError.fromConfig(NO_CONFIG);
        }
    }
}
