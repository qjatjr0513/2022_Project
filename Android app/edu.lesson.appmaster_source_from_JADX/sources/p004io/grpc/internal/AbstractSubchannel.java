package p004io.grpc.internal;

import p004io.grpc.InternalChannelz;
import p004io.grpc.InternalInstrumented;
import p004io.grpc.LoadBalancer;

/* renamed from: io.grpc.internal.AbstractSubchannel */
abstract class AbstractSubchannel extends LoadBalancer.Subchannel {
    /* access modifiers changed from: package-private */
    public abstract InternalInstrumented<InternalChannelz.ChannelStats> getInstrumentedInternalSubchannel();

    AbstractSubchannel() {
    }
}
