package p004io.grpc.internal;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import p004io.grpc.ManagedChannel;

/* renamed from: io.grpc.internal.ManagedChannelOrphanWrapper */
final class ManagedChannelOrphanWrapper extends ForwardingManagedChannel {
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(ManagedChannelOrphanWrapper.class.getName());
    private static final ReferenceQueue<ManagedChannelOrphanWrapper> refqueue = new ReferenceQueue<>();
    private static final ConcurrentMap<ManagedChannelReference, ManagedChannelReference> refs = new ConcurrentHashMap();
    private final ManagedChannelReference phantom;

    ManagedChannelOrphanWrapper(ManagedChannel delegate) {
        this(delegate, refqueue, refs);
    }

    ManagedChannelOrphanWrapper(ManagedChannel delegate, ReferenceQueue<ManagedChannelOrphanWrapper> refqueue2, ConcurrentMap<ManagedChannelReference, ManagedChannelReference> refs2) {
        super(delegate);
        this.phantom = new ManagedChannelReference(this, delegate, refqueue2, refs2);
    }

    public ManagedChannel shutdown() {
        this.phantom.clearSafely();
        return super.shutdown();
    }

    public ManagedChannel shutdownNow() {
        this.phantom.clearSafely();
        return super.shutdownNow();
    }

    /* renamed from: io.grpc.internal.ManagedChannelOrphanWrapper$ManagedChannelReference */
    static final class ManagedChannelReference extends WeakReference<ManagedChannelOrphanWrapper> {
        private static final String ALLOCATION_SITE_PROPERTY_NAME = "io.grpc.ManagedChannel.enableAllocationTracking";
        private static final boolean ENABLE_ALLOCATION_TRACKING = Boolean.parseBoolean(System.getProperty(ALLOCATION_SITE_PROPERTY_NAME, "true"));
        private static final RuntimeException missingCallSite = missingCallSite();
        private final Reference<RuntimeException> allocationSite;
        private final String channelStr;
        private final ReferenceQueue<ManagedChannelOrphanWrapper> refqueue;
        private final ConcurrentMap<ManagedChannelReference, ManagedChannelReference> refs;
        private final AtomicBoolean shutdown = new AtomicBoolean();

        ManagedChannelReference(ManagedChannelOrphanWrapper orphanable, ManagedChannel channel, ReferenceQueue<ManagedChannelOrphanWrapper> refqueue2, ConcurrentMap<ManagedChannelReference, ManagedChannelReference> refs2) {
            super(orphanable, refqueue2);
            RuntimeException runtimeException;
            if (ENABLE_ALLOCATION_TRACKING) {
                runtimeException = new RuntimeException("ManagedChannel allocation site");
            } else {
                runtimeException = missingCallSite;
            }
            this.allocationSite = new SoftReference(runtimeException);
            this.channelStr = channel.toString();
            this.refqueue = refqueue2;
            this.refs = refs2;
            refs2.put(this, this);
            cleanQueue(refqueue2);
        }

        public void clear() {
            clearInternal();
            cleanQueue(this.refqueue);
        }

        /* access modifiers changed from: private */
        public void clearSafely() {
            if (!this.shutdown.getAndSet(true)) {
                clear();
            }
        }

        private void clearInternal() {
            super.clear();
            this.refs.remove(this);
            this.allocationSite.clear();
        }

        private static RuntimeException missingCallSite() {
            RuntimeException e = new RuntimeException("ManagedChannel allocation site not recorded.  Set -Dio.grpc.ManagedChannel.enableAllocationTracking=true to enable it");
            e.setStackTrace(new StackTraceElement[0]);
            return e;
        }

        static int cleanQueue(ReferenceQueue<ManagedChannelOrphanWrapper> refqueue2) {
            int orphanedChannels = 0;
            while (true) {
                ManagedChannelReference managedChannelReference = (ManagedChannelReference) refqueue2.poll();
                ManagedChannelReference ref = managedChannelReference;
                if (managedChannelReference == null) {
                    return orphanedChannels;
                }
                RuntimeException maybeAllocationSite = ref.allocationSite.get();
                ref.clearInternal();
                if (!ref.shutdown.get()) {
                    orphanedChannels++;
                    Level level = Level.SEVERE;
                    if (ManagedChannelOrphanWrapper.logger.isLoggable(level)) {
                        LogRecord lr = new LogRecord(level, "*~*~*~ Channel {0} was not shutdown properly!!! ~*~*~*" + System.getProperty("line.separator") + "    Make sure to call shutdown()/shutdownNow() and wait until awaitTermination() returns true.");
                        lr.setLoggerName(ManagedChannelOrphanWrapper.logger.getName());
                        lr.setParameters(new Object[]{ref.channelStr});
                        lr.setThrown(maybeAllocationSite);
                        ManagedChannelOrphanWrapper.logger.log(lr);
                    }
                }
            }
        }
    }
}
