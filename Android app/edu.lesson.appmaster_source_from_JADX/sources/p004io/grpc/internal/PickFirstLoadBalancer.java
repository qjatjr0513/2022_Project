package p004io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import p004io.grpc.ConnectivityState;
import p004io.grpc.ConnectivityStateInfo;
import p004io.grpc.EquivalentAddressGroup;
import p004io.grpc.LoadBalancer;
import p004io.grpc.Status;

/* renamed from: io.grpc.internal.PickFirstLoadBalancer */
final class PickFirstLoadBalancer extends LoadBalancer {
    /* access modifiers changed from: private */
    public final LoadBalancer.Helper helper;
    private LoadBalancer.Subchannel subchannel;

    PickFirstLoadBalancer(LoadBalancer.Helper helper2) {
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper2, "helper");
    }

    public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        List<EquivalentAddressGroup> servers = resolvedAddresses.getAddresses();
        LoadBalancer.Subchannel subchannel2 = this.subchannel;
        if (subchannel2 == null) {
            final LoadBalancer.Subchannel subchannel3 = this.helper.createSubchannel(LoadBalancer.CreateSubchannelArgs.newBuilder().setAddresses(servers).build());
            subchannel3.start(new LoadBalancer.SubchannelStateListener() {
                public void onSubchannelState(ConnectivityStateInfo stateInfo) {
                    PickFirstLoadBalancer.this.processSubchannelState(subchannel3, stateInfo);
                }
            });
            this.subchannel = subchannel3;
            this.helper.updateBalancingState(ConnectivityState.CONNECTING, new Picker(LoadBalancer.PickResult.withSubchannel(subchannel3)));
            subchannel3.requestConnection();
            return;
        }
        subchannel2.updateAddresses(servers);
    }

    public void handleNameResolutionError(Status error) {
        LoadBalancer.Subchannel subchannel2 = this.subchannel;
        if (subchannel2 != null) {
            subchannel2.shutdown();
            this.subchannel = null;
        }
        this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new Picker(LoadBalancer.PickResult.withError(error)));
    }

    /* access modifiers changed from: private */
    public void processSubchannelState(LoadBalancer.Subchannel subchannel2, ConnectivityStateInfo stateInfo) {
        LoadBalancer.SubchannelPicker picker;
        ConnectivityState currentState = stateInfo.getState();
        if (currentState != ConnectivityState.SHUTDOWN) {
            if (stateInfo.getState() == ConnectivityState.TRANSIENT_FAILURE || stateInfo.getState() == ConnectivityState.IDLE) {
                this.helper.refreshNameResolution();
            }
            switch (C12902.$SwitchMap$io$grpc$ConnectivityState[currentState.ordinal()]) {
                case 1:
                    picker = new RequestConnectionPicker(subchannel2);
                    break;
                case 2:
                    picker = new Picker(LoadBalancer.PickResult.withNoResult());
                    break;
                case 3:
                    picker = new Picker(LoadBalancer.PickResult.withSubchannel(subchannel2));
                    break;
                case 4:
                    picker = new Picker(LoadBalancer.PickResult.withError(stateInfo.getStatus()));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported state:" + currentState);
            }
            this.helper.updateBalancingState(currentState, picker);
        }
    }

    /* renamed from: io.grpc.internal.PickFirstLoadBalancer$2 */
    static /* synthetic */ class C12902 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$ConnectivityState;

        static {
            int[] iArr = new int[ConnectivityState.values().length];
            $SwitchMap$io$grpc$ConnectivityState = iArr;
            try {
                iArr[ConnectivityState.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$io$grpc$ConnectivityState[ConnectivityState.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$io$grpc$ConnectivityState[ConnectivityState.READY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$io$grpc$ConnectivityState[ConnectivityState.TRANSIENT_FAILURE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public void shutdown() {
        LoadBalancer.Subchannel subchannel2 = this.subchannel;
        if (subchannel2 != null) {
            subchannel2.shutdown();
        }
    }

    public void requestConnection() {
        LoadBalancer.Subchannel subchannel2 = this.subchannel;
        if (subchannel2 != null) {
            subchannel2.requestConnection();
        }
    }

    /* renamed from: io.grpc.internal.PickFirstLoadBalancer$Picker */
    private static final class Picker extends LoadBalancer.SubchannelPicker {
        private final LoadBalancer.PickResult result;

        Picker(LoadBalancer.PickResult result2) {
            this.result = (LoadBalancer.PickResult) Preconditions.checkNotNull(result2, "result");
        }

        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs args) {
            return this.result;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) Picker.class).add("result", (Object) this.result).toString();
        }
    }

    /* renamed from: io.grpc.internal.PickFirstLoadBalancer$RequestConnectionPicker */
    private final class RequestConnectionPicker extends LoadBalancer.SubchannelPicker {
        private final AtomicBoolean connectionRequested = new AtomicBoolean(false);
        /* access modifiers changed from: private */
        public final LoadBalancer.Subchannel subchannel;

        RequestConnectionPicker(LoadBalancer.Subchannel subchannel2) {
            this.subchannel = (LoadBalancer.Subchannel) Preconditions.checkNotNull(subchannel2, "subchannel");
        }

        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs args) {
            if (this.connectionRequested.compareAndSet(false, true)) {
                PickFirstLoadBalancer.this.helper.getSynchronizationContext().execute(new Runnable() {
                    public void run() {
                        RequestConnectionPicker.this.subchannel.requestConnection();
                    }
                });
            }
            return LoadBalancer.PickResult.withNoResult();
        }
    }
}
