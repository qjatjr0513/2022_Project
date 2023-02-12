package p004io.grpc;

import androidx.core.app.NotificationCompat;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import p004io.grpc.Attributes;
import p004io.grpc.ClientStreamTracer;
import p004io.grpc.NameResolver;

/* renamed from: io.grpc.LoadBalancer */
public abstract class LoadBalancer {
    public static final Attributes.Key<Map<String, ?>> ATTR_HEALTH_CHECKING_CONFIG = Attributes.Key.create("health-checking-config");
    private int recursionCount;

    /* renamed from: io.grpc.LoadBalancer$Factory */
    public static abstract class Factory {
        public abstract LoadBalancer newLoadBalancer(Helper helper);
    }

    /* renamed from: io.grpc.LoadBalancer$PickSubchannelArgs */
    public static abstract class PickSubchannelArgs {
        public abstract CallOptions getCallOptions();

        public abstract Metadata getHeaders();

        public abstract MethodDescriptor<?, ?> getMethodDescriptor();
    }

    /* renamed from: io.grpc.LoadBalancer$SubchannelStateListener */
    public interface SubchannelStateListener {
        void onSubchannelState(ConnectivityStateInfo connectivityStateInfo);
    }

    public abstract void handleNameResolutionError(Status status);

    public abstract void shutdown();

    @Deprecated
    public void handleResolvedAddressGroups(List<EquivalentAddressGroup> servers, Attributes attributes) {
        int i = this.recursionCount;
        this.recursionCount = i + 1;
        if (i == 0) {
            handleResolvedAddresses(ResolvedAddresses.newBuilder().setAddresses(servers).setAttributes(attributes).build());
        }
        this.recursionCount = 0;
    }

    public void handleResolvedAddresses(ResolvedAddresses resolvedAddresses) {
        int i = this.recursionCount;
        this.recursionCount = i + 1;
        if (i == 0) {
            handleResolvedAddressGroups(resolvedAddresses.getAddresses(), resolvedAddresses.getAttributes());
        }
        this.recursionCount = 0;
    }

    /* renamed from: io.grpc.LoadBalancer$ResolvedAddresses */
    public static final class ResolvedAddresses {
        private final List<EquivalentAddressGroup> addresses;
        private final Attributes attributes;
        @Nullable
        private final Object loadBalancingPolicyConfig;

        private ResolvedAddresses(List<EquivalentAddressGroup> addresses2, Attributes attributes2, Object loadBalancingPolicyConfig2) {
            this.addresses = Collections.unmodifiableList(new ArrayList((Collection) Preconditions.checkNotNull(addresses2, "addresses")));
            this.attributes = (Attributes) Preconditions.checkNotNull(attributes2, "attributes");
            this.loadBalancingPolicyConfig = loadBalancingPolicyConfig2;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder toBuilder() {
            return newBuilder().setAddresses(this.addresses).setAttributes(this.attributes).setLoadBalancingPolicyConfig(this.loadBalancingPolicyConfig);
        }

        public List<EquivalentAddressGroup> getAddresses() {
            return this.addresses;
        }

        public Attributes getAttributes() {
            return this.attributes;
        }

        @Nullable
        public Object getLoadBalancingPolicyConfig() {
            return this.loadBalancingPolicyConfig;
        }

        /* renamed from: io.grpc.LoadBalancer$ResolvedAddresses$Builder */
        public static final class Builder {
            private List<EquivalentAddressGroup> addresses;
            private Attributes attributes = Attributes.EMPTY;
            @Nullable
            private Object loadBalancingPolicyConfig;

            Builder() {
            }

            public Builder setAddresses(List<EquivalentAddressGroup> addresses2) {
                this.addresses = addresses2;
                return this;
            }

            public Builder setAttributes(Attributes attributes2) {
                this.attributes = attributes2;
                return this;
            }

            public Builder setLoadBalancingPolicyConfig(@Nullable Object loadBalancingPolicyConfig2) {
                this.loadBalancingPolicyConfig = loadBalancingPolicyConfig2;
                return this;
            }

            public ResolvedAddresses build() {
                return new ResolvedAddresses(this.addresses, this.attributes, this.loadBalancingPolicyConfig);
            }
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("addresses", (Object) this.addresses).add("attributes", (Object) this.attributes).add("loadBalancingPolicyConfig", this.loadBalancingPolicyConfig).toString();
        }

        public int hashCode() {
            return Objects.hashCode(this.addresses, this.attributes, this.loadBalancingPolicyConfig);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ResolvedAddresses)) {
                return false;
            }
            ResolvedAddresses that = (ResolvedAddresses) obj;
            if (!Objects.equal(this.addresses, that.addresses) || !Objects.equal(this.attributes, that.attributes) || !Objects.equal(this.loadBalancingPolicyConfig, that.loadBalancingPolicyConfig)) {
                return false;
            }
            return true;
        }
    }

    @Deprecated
    public void handleSubchannelState(Subchannel subchannel, ConnectivityStateInfo stateInfo) {
    }

    public boolean canHandleEmptyAddressListFromNameResolution() {
        return false;
    }

    public void requestConnection() {
    }

    /* renamed from: io.grpc.LoadBalancer$SubchannelPicker */
    public static abstract class SubchannelPicker {
        public abstract PickResult pickSubchannel(PickSubchannelArgs pickSubchannelArgs);

        @Deprecated
        public void requestConnection() {
        }
    }

    /* renamed from: io.grpc.LoadBalancer$PickResult */
    public static final class PickResult {
        private static final PickResult NO_RESULT = new PickResult((Subchannel) null, (ClientStreamTracer.Factory) null, Status.f313OK, false);
        private final boolean drop;
        private final Status status;
        @Nullable
        private final ClientStreamTracer.Factory streamTracerFactory;
        @Nullable
        private final Subchannel subchannel;

        private PickResult(@Nullable Subchannel subchannel2, @Nullable ClientStreamTracer.Factory streamTracerFactory2, Status status2, boolean drop2) {
            this.subchannel = subchannel2;
            this.streamTracerFactory = streamTracerFactory2;
            this.status = (Status) Preconditions.checkNotNull(status2, NotificationCompat.CATEGORY_STATUS);
            this.drop = drop2;
        }

        public static PickResult withSubchannel(Subchannel subchannel2, @Nullable ClientStreamTracer.Factory streamTracerFactory2) {
            return new PickResult((Subchannel) Preconditions.checkNotNull(subchannel2, "subchannel"), streamTracerFactory2, Status.f313OK, false);
        }

        public static PickResult withSubchannel(Subchannel subchannel2) {
            return withSubchannel(subchannel2, (ClientStreamTracer.Factory) null);
        }

        public static PickResult withError(Status error) {
            Preconditions.checkArgument(!error.isOk(), "error status shouldn't be OK");
            return new PickResult((Subchannel) null, (ClientStreamTracer.Factory) null, error, false);
        }

        public static PickResult withDrop(Status status2) {
            Preconditions.checkArgument(!status2.isOk(), "drop status shouldn't be OK");
            return new PickResult((Subchannel) null, (ClientStreamTracer.Factory) null, status2, true);
        }

        public static PickResult withNoResult() {
            return NO_RESULT;
        }

        @Nullable
        public Subchannel getSubchannel() {
            return this.subchannel;
        }

        @Nullable
        public ClientStreamTracer.Factory getStreamTracerFactory() {
            return this.streamTracerFactory;
        }

        public Status getStatus() {
            return this.status;
        }

        public boolean isDrop() {
            return this.drop;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("subchannel", (Object) this.subchannel).add("streamTracerFactory", (Object) this.streamTracerFactory).add(NotificationCompat.CATEGORY_STATUS, (Object) this.status).add("drop", this.drop).toString();
        }

        public int hashCode() {
            return Objects.hashCode(this.subchannel, this.status, this.streamTracerFactory, Boolean.valueOf(this.drop));
        }

        public boolean equals(Object other) {
            if (!(other instanceof PickResult)) {
                return false;
            }
            PickResult that = (PickResult) other;
            if (!Objects.equal(this.subchannel, that.subchannel) || !Objects.equal(this.status, that.status) || !Objects.equal(this.streamTracerFactory, that.streamTracerFactory) || this.drop != that.drop) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: io.grpc.LoadBalancer$CreateSubchannelArgs */
    public static final class CreateSubchannelArgs {
        private final List<EquivalentAddressGroup> addrs;
        private final Attributes attrs;
        private final Object[][] customOptions;

        private CreateSubchannelArgs(List<EquivalentAddressGroup> addrs2, Attributes attrs2, Object[][] customOptions2) {
            this.addrs = (List) Preconditions.checkNotNull(addrs2, "addresses are not set");
            this.attrs = (Attributes) Preconditions.checkNotNull(attrs2, "attrs");
            this.customOptions = (Object[][]) Preconditions.checkNotNull(customOptions2, "customOptions");
        }

        public List<EquivalentAddressGroup> getAddresses() {
            return this.addrs;
        }

        public Attributes getAttributes() {
            return this.attrs;
        }

        public <T> T getOption(Key<T> key) {
            Preconditions.checkNotNull(key, "key");
            int i = 0;
            while (true) {
                Object[][] objArr = this.customOptions;
                if (i >= objArr.length) {
                    return key.defaultValue;
                }
                if (key.equals(objArr[i][0])) {
                    return this.customOptions[i][1];
                }
                i++;
            }
        }

        public Builder toBuilder() {
            return newBuilder().setAddresses(this.addrs).setAttributes(this.attrs).copyCustomOptions(this.customOptions);
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("addrs", (Object) this.addrs).add("attrs", (Object) this.attrs).add("customOptions", (Object) Arrays.deepToString(this.customOptions)).toString();
        }

        /* renamed from: io.grpc.LoadBalancer$CreateSubchannelArgs$Builder */
        public static final class Builder {
            private List<EquivalentAddressGroup> addrs;
            private Attributes attrs = Attributes.EMPTY;
            private Object[][] customOptions = ((Object[][]) Array.newInstance(Object.class, new int[]{0, 2}));

            Builder() {
            }

            /* access modifiers changed from: private */
            public <T> Builder copyCustomOptions(Object[][] options) {
                int length = options.length;
                int[] iArr = new int[2];
                iArr[1] = 2;
                iArr[0] = length;
                Object[][] objArr = (Object[][]) Array.newInstance(Object.class, iArr);
                this.customOptions = objArr;
                System.arraycopy(options, 0, objArr, 0, options.length);
                return this;
            }

            public <T> Builder addOption(Key<T> key, T value) {
                Preconditions.checkNotNull(key, "key");
                Preconditions.checkNotNull(value, "value");
                int existingIdx = -1;
                int i = 0;
                while (true) {
                    Object[][] objArr = this.customOptions;
                    if (i >= objArr.length) {
                        break;
                    } else if (key.equals(objArr[i][0])) {
                        existingIdx = i;
                        break;
                    } else {
                        i++;
                    }
                }
                if (existingIdx == -1) {
                    int[] iArr = new int[2];
                    iArr[1] = 2;
                    iArr[0] = this.customOptions.length + 1;
                    Object[][] newCustomOptions = (Object[][]) Array.newInstance(Object.class, iArr);
                    Object[][] objArr2 = this.customOptions;
                    System.arraycopy(objArr2, 0, newCustomOptions, 0, objArr2.length);
                    this.customOptions = newCustomOptions;
                    existingIdx = newCustomOptions.length - 1;
                }
                this.customOptions[existingIdx] = new Object[]{key, value};
                return this;
            }

            public Builder setAddresses(EquivalentAddressGroup addrs2) {
                this.addrs = Collections.singletonList(addrs2);
                return this;
            }

            public Builder setAddresses(List<EquivalentAddressGroup> addrs2) {
                Preconditions.checkArgument(!addrs2.isEmpty(), "addrs is empty");
                this.addrs = Collections.unmodifiableList(new ArrayList(addrs2));
                return this;
            }

            public Builder setAttributes(Attributes attrs2) {
                this.attrs = (Attributes) Preconditions.checkNotNull(attrs2, "attrs");
                return this;
            }

            public CreateSubchannelArgs build() {
                return new CreateSubchannelArgs(this.addrs, this.attrs, this.customOptions);
            }
        }

        /* renamed from: io.grpc.LoadBalancer$CreateSubchannelArgs$Key */
        public static final class Key<T> {
            private final String debugString;
            /* access modifiers changed from: private */
            public final T defaultValue;

            private Key(String debugString2, T defaultValue2) {
                this.debugString = debugString2;
                this.defaultValue = defaultValue2;
            }

            public static <T> Key<T> create(String debugString2) {
                Preconditions.checkNotNull(debugString2, "debugString");
                return new Key<>(debugString2, (Object) null);
            }

            public static <T> Key<T> createWithDefault(String debugString2, T defaultValue2) {
                Preconditions.checkNotNull(debugString2, "debugString");
                return new Key<>(debugString2, defaultValue2);
            }

            public T getDefault() {
                return this.defaultValue;
            }

            public String toString() {
                return this.debugString;
            }
        }
    }

    /* renamed from: io.grpc.LoadBalancer$Helper */
    public static abstract class Helper {
        public abstract ManagedChannel createOobChannel(EquivalentAddressGroup equivalentAddressGroup, String str);

        public abstract String getAuthority();

        public abstract void updateBalancingState(@Nonnull ConnectivityState connectivityState, @Nonnull SubchannelPicker subchannelPicker);

        public Subchannel createSubchannel(CreateSubchannelArgs args) {
            throw new UnsupportedOperationException();
        }

        public ManagedChannel createOobChannel(List<EquivalentAddressGroup> list, String authority) {
            throw new UnsupportedOperationException();
        }

        public void updateOobChannelAddresses(ManagedChannel channel, EquivalentAddressGroup eag) {
            throw new UnsupportedOperationException();
        }

        public void updateOobChannelAddresses(ManagedChannel channel, List<EquivalentAddressGroup> list) {
            throw new UnsupportedOperationException();
        }

        public ManagedChannel createResolvingOobChannel(String target) {
            return createResolvingOobChannelBuilder(target).build();
        }

        @Deprecated
        public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String target) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String target, ChannelCredentials creds) {
            throw new UnsupportedOperationException();
        }

        public void refreshNameResolution() {
            throw new UnsupportedOperationException();
        }

        public void ignoreRefreshNameResolutionCheck() {
        }

        public SynchronizationContext getSynchronizationContext() {
            throw new UnsupportedOperationException();
        }

        public ScheduledExecutorService getScheduledExecutorService() {
            throw new UnsupportedOperationException();
        }

        public ChannelCredentials getChannelCredentials() {
            return getUnsafeChannelCredentials().withoutBearerTokens();
        }

        public ChannelCredentials getUnsafeChannelCredentials() {
            throw new UnsupportedOperationException();
        }

        public ChannelLogger getChannelLogger() {
            throw new UnsupportedOperationException();
        }

        public NameResolver.Args getNameResolverArgs() {
            throw new UnsupportedOperationException();
        }

        public NameResolverRegistry getNameResolverRegistry() {
            throw new UnsupportedOperationException();
        }
    }

    /* renamed from: io.grpc.LoadBalancer$Subchannel */
    public static abstract class Subchannel {
        public abstract Attributes getAttributes();

        public abstract void requestConnection();

        public abstract void shutdown();

        public void start(SubchannelStateListener listener) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public final EquivalentAddressGroup getAddresses() {
            List<EquivalentAddressGroup> groups = getAllAddresses();
            boolean z = true;
            if (groups.size() != 1) {
                z = false;
            }
            Preconditions.checkState(z, "%s does not have exactly one group", (Object) groups);
            return groups.get(0);
        }

        public List<EquivalentAddressGroup> getAllAddresses() {
            throw new UnsupportedOperationException();
        }

        public Channel asChannel() {
            throw new UnsupportedOperationException();
        }

        public ChannelLogger getChannelLogger() {
            throw new UnsupportedOperationException();
        }

        public void updateAddresses(List<EquivalentAddressGroup> list) {
            throw new UnsupportedOperationException();
        }

        public Object getInternalSubchannel() {
            throw new UnsupportedOperationException();
        }
    }
}
