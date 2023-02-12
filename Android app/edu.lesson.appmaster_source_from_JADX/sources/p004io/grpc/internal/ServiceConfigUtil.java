package p004io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import com.google.common.base.VerifyException;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p004io.grpc.LoadBalancerProvider;
import p004io.grpc.LoadBalancerRegistry;
import p004io.grpc.NameResolver;
import p004io.grpc.Status;
import p004io.grpc.internal.RetriableStream;

/* renamed from: io.grpc.internal.ServiceConfigUtil */
public final class ServiceConfigUtil {
    private ServiceConfigUtil() {
    }

    @Nullable
    public static Map<String, ?> getHealthCheckedService(@Nullable Map<String, ?> serviceConfig) {
        if (serviceConfig == null) {
            return null;
        }
        return JsonUtil.getObject(serviceConfig, "healthCheckConfig");
    }

    @Nullable
    public static String getHealthCheckedServiceName(@Nullable Map<String, ?> healthCheckedServiceConfig) {
        if (healthCheckedServiceConfig == null) {
            return null;
        }
        return JsonUtil.getString(healthCheckedServiceConfig, "serviceName");
    }

    @Nullable
    static RetriableStream.Throttle getThrottlePolicy(@Nullable Map<String, ?> serviceConfig) {
        Map<String, ?> throttling;
        if (serviceConfig == null || (throttling = JsonUtil.getObject(serviceConfig, "retryThrottling")) == null) {
            return null;
        }
        float maxTokens = JsonUtil.getNumberAsDouble(throttling, "maxTokens").floatValue();
        float tokenRatio = JsonUtil.getNumberAsDouble(throttling, "tokenRatio").floatValue();
        boolean z = true;
        Preconditions.checkState(maxTokens > 0.0f, "maxToken should be greater than zero");
        if (tokenRatio <= 0.0f) {
            z = false;
        }
        Preconditions.checkState(z, "tokenRatio should be greater than zero");
        return new RetriableStream.Throttle(maxTokens, tokenRatio);
    }

    @Nullable
    static Integer getMaxAttemptsFromRetryPolicy(Map<String, ?> retryPolicy) {
        return JsonUtil.getNumberAsInteger(retryPolicy, "maxAttempts");
    }

    @Nullable
    static Long getInitialBackoffNanosFromRetryPolicy(Map<String, ?> retryPolicy) {
        return JsonUtil.getStringAsDuration(retryPolicy, "initialBackoff");
    }

    @Nullable
    static Long getMaxBackoffNanosFromRetryPolicy(Map<String, ?> retryPolicy) {
        return JsonUtil.getStringAsDuration(retryPolicy, "maxBackoff");
    }

    @Nullable
    static Double getBackoffMultiplierFromRetryPolicy(Map<String, ?> retryPolicy) {
        return JsonUtil.getNumberAsDouble(retryPolicy, "backoffMultiplier");
    }

    @Nullable
    static Long getPerAttemptRecvTimeoutNanosFromRetryPolicy(Map<String, ?> retryPolicy) {
        return JsonUtil.getStringAsDuration(retryPolicy, "perAttemptRecvTimeout");
    }

    private static Set<Status.Code> getListOfStatusCodesAsSet(Map<String, ?> obj, String key) {
        List<?> statuses = JsonUtil.getList(obj, key);
        if (statuses == null) {
            return null;
        }
        return getStatusCodesFromList(statuses);
    }

    private static Set<Status.Code> getStatusCodesFromList(List<?> statuses) {
        Status.Code code;
        EnumSet<Status.Code> codes = EnumSet.noneOf(Status.Code.class);
        for (Object next : statuses) {
            if (next instanceof Double) {
                Double statusD = (Double) next;
                int codeValue = statusD.intValue();
                boolean z = true;
                Verify.verify(((double) codeValue) == statusD.doubleValue(), "Status code %s is not integral", (Object) next);
                code = Status.fromCodeValue(codeValue).getCode();
                if (code.value() != statusD.intValue()) {
                    z = false;
                }
                Verify.verify(z, "Status code %s is not valid", (Object) next);
            } else if (next instanceof String) {
                try {
                    code = Status.Code.valueOf((String) next);
                } catch (IllegalArgumentException iae) {
                    throw new VerifyException("Status code " + next + " is not valid", iae);
                }
            } else {
                throw new VerifyException("Can not convert status code " + next + " to Status.Code, because its type is " + next.getClass());
            }
            codes.add(code);
        }
        return Collections.unmodifiableSet(codes);
    }

    static Set<Status.Code> getRetryableStatusCodesFromRetryPolicy(Map<String, ?> retryPolicy) {
        Set<Status.Code> codes = getListOfStatusCodesAsSet(retryPolicy, "retryableStatusCodes");
        Verify.verify(codes != null, "%s is required in retry policy", (Object) "retryableStatusCodes");
        Verify.verify(true ^ codes.contains(Status.Code.OK), "%s must not contain OK", (Object) "retryableStatusCodes");
        return codes;
    }

    @Nullable
    static Integer getMaxAttemptsFromHedgingPolicy(Map<String, ?> hedgingPolicy) {
        return JsonUtil.getNumberAsInteger(hedgingPolicy, "maxAttempts");
    }

    @Nullable
    static Long getHedgingDelayNanosFromHedgingPolicy(Map<String, ?> hedgingPolicy) {
        return JsonUtil.getStringAsDuration(hedgingPolicy, "hedgingDelay");
    }

    static Set<Status.Code> getNonFatalStatusCodesFromHedgingPolicy(Map<String, ?> hedgingPolicy) {
        Set<Status.Code> codes = getListOfStatusCodesAsSet(hedgingPolicy, "nonFatalStatusCodes");
        if (codes == null) {
            return Collections.unmodifiableSet(EnumSet.noneOf(Status.Code.class));
        }
        Verify.verify(!codes.contains(Status.Code.OK), "%s must not contain OK", (Object) "nonFatalStatusCodes");
        return codes;
    }

    @Nullable
    static String getServiceFromName(Map<String, ?> name) {
        return JsonUtil.getString(name, NotificationCompat.CATEGORY_SERVICE);
    }

    @Nullable
    static String getMethodFromName(Map<String, ?> name) {
        return JsonUtil.getString(name, FirebaseAnalytics.Param.METHOD);
    }

    @Nullable
    static Map<String, ?> getRetryPolicyFromMethodConfig(Map<String, ?> methodConfig) {
        return JsonUtil.getObject(methodConfig, "retryPolicy");
    }

    @Nullable
    static Map<String, ?> getHedgingPolicyFromMethodConfig(Map<String, ?> methodConfig) {
        return JsonUtil.getObject(methodConfig, "hedgingPolicy");
    }

    @Nullable
    static List<Map<String, ?>> getNameListFromMethodConfig(Map<String, ?> methodConfig) {
        return JsonUtil.getListOfObjects(methodConfig, AppMeasurementSdk.ConditionalUserProperty.NAME);
    }

    @Nullable
    static Long getTimeoutFromMethodConfig(Map<String, ?> methodConfig) {
        return JsonUtil.getStringAsDuration(methodConfig, "timeout");
    }

    @Nullable
    static Boolean getWaitForReadyFromMethodConfig(Map<String, ?> methodConfig) {
        return JsonUtil.getBoolean(methodConfig, "waitForReady");
    }

    @Nullable
    static Integer getMaxRequestMessageBytesFromMethodConfig(Map<String, ?> methodConfig) {
        return JsonUtil.getNumberAsInteger(methodConfig, "maxRequestMessageBytes");
    }

    @Nullable
    static Integer getMaxResponseMessageBytesFromMethodConfig(Map<String, ?> methodConfig) {
        return JsonUtil.getNumberAsInteger(methodConfig, "maxResponseMessageBytes");
    }

    @Nullable
    static List<Map<String, ?>> getMethodConfigFromServiceConfig(Map<String, ?> serviceConfig) {
        return JsonUtil.getListOfObjects(serviceConfig, "methodConfig");
    }

    public static List<Map<String, ?>> getLoadBalancingConfigsFromServiceConfig(Map<String, ?> serviceConfig) {
        String policy;
        List<Map<String, ?>> lbConfigs = new ArrayList<>();
        if (serviceConfig.containsKey("loadBalancingConfig")) {
            lbConfigs.addAll(JsonUtil.getListOfObjects(serviceConfig, "loadBalancingConfig"));
        }
        if (lbConfigs.isEmpty() && (policy = JsonUtil.getString(serviceConfig, "loadBalancingPolicy")) != null) {
            lbConfigs.add(Collections.singletonMap(policy.toLowerCase(Locale.ROOT), Collections.emptyMap()));
        }
        return Collections.unmodifiableList(lbConfigs);
    }

    public static LbConfig unwrapLoadBalancingConfig(Map<String, ?> lbConfig) {
        if (lbConfig.size() == 1) {
            String key = (String) lbConfig.entrySet().iterator().next().getKey();
            return new LbConfig(key, JsonUtil.getObject(lbConfig, key));
        }
        throw new RuntimeException("There are " + lbConfig.size() + " fields in a LoadBalancingConfig object. Exactly one is expected. Config=" + lbConfig);
    }

    public static List<LbConfig> unwrapLoadBalancingConfigList(List<Map<String, ?>> list) {
        if (list == null) {
            return null;
        }
        ArrayList<LbConfig> result = new ArrayList<>();
        for (Map<String, ?> rawChildPolicy : list) {
            result.add(unwrapLoadBalancingConfig(rawChildPolicy));
        }
        return Collections.unmodifiableList(result);
    }

    public static NameResolver.ConfigOrError selectLbPolicyFromList(List<LbConfig> lbConfigs, LoadBalancerRegistry lbRegistry) {
        List<String> policiesTried = new ArrayList<>();
        for (LbConfig lbConfig : lbConfigs) {
            String policy = lbConfig.getPolicyName();
            LoadBalancerProvider provider = lbRegistry.getProvider(policy);
            if (provider == null) {
                policiesTried.add(policy);
            } else {
                if (!policiesTried.isEmpty()) {
                    Logger.getLogger(ServiceConfigUtil.class.getName()).log(Level.FINEST, "{0} specified by Service Config are not available", policiesTried);
                }
                NameResolver.ConfigOrError parsedLbPolicyConfig = provider.parseLoadBalancingPolicyConfig(lbConfig.getRawConfigValue());
                if (parsedLbPolicyConfig.getError() != null) {
                    return parsedLbPolicyConfig;
                }
                return NameResolver.ConfigOrError.fromConfig(new PolicySelection(provider, parsedLbPolicyConfig.getConfig()));
            }
        }
        return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("None of " + policiesTried + " specified by Service Config are available."));
    }

    /* renamed from: io.grpc.internal.ServiceConfigUtil$LbConfig */
    public static final class LbConfig {
        private final String policyName;
        private final Map<String, ?> rawConfigValue;

        public LbConfig(String policyName2, Map<String, ?> rawConfigValue2) {
            this.policyName = (String) Preconditions.checkNotNull(policyName2, "policyName");
            this.rawConfigValue = (Map) Preconditions.checkNotNull(rawConfigValue2, "rawConfigValue");
        }

        public String getPolicyName() {
            return this.policyName;
        }

        public Map<String, ?> getRawConfigValue() {
            return this.rawConfigValue;
        }

        public boolean equals(Object o) {
            if (!(o instanceof LbConfig)) {
                return false;
            }
            LbConfig other = (LbConfig) o;
            if (!this.policyName.equals(other.policyName) || !this.rawConfigValue.equals(other.rawConfigValue)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hashCode(this.policyName, this.rawConfigValue);
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("policyName", (Object) this.policyName).add("rawConfigValue", (Object) this.rawConfigValue).toString();
        }
    }

    /* renamed from: io.grpc.internal.ServiceConfigUtil$PolicySelection */
    public static final class PolicySelection {
        @Nullable
        final Object config;
        final LoadBalancerProvider provider;

        public PolicySelection(LoadBalancerProvider provider2, @Nullable Object config2) {
            this.provider = (LoadBalancerProvider) Preconditions.checkNotNull(provider2, "provider");
            this.config = config2;
        }

        public LoadBalancerProvider getProvider() {
            return this.provider;
        }

        @Nullable
        public Object getConfig() {
            return this.config;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            PolicySelection that = (PolicySelection) o;
            if (!Objects.equal(this.provider, that.provider) || !Objects.equal(this.config, that.config)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hashCode(this.provider, this.config);
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("provider", (Object) this.provider).add("config", this.config).toString();
        }
    }
}
