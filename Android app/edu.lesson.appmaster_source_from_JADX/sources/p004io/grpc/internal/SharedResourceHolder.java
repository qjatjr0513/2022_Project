package p004io.grpc.internal;

import com.google.common.base.Preconditions;
import java.util.IdentityHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: io.grpc.internal.SharedResourceHolder */
public final class SharedResourceHolder {
    static final long DESTROY_DELAY_SECONDS = 1;
    private static final SharedResourceHolder holder = new SharedResourceHolder(new ScheduledExecutorFactory() {
        public ScheduledExecutorService createScheduledExecutor() {
            return Executors.newSingleThreadScheduledExecutor(GrpcUtil.getThreadFactory("grpc-shared-destroyer-%d", true));
        }
    });
    /* access modifiers changed from: private */
    public ScheduledExecutorService destroyer;
    private final ScheduledExecutorFactory destroyerFactory;
    /* access modifiers changed from: private */
    public final IdentityHashMap<Resource<?>, Instance> instances = new IdentityHashMap<>();

    /* renamed from: io.grpc.internal.SharedResourceHolder$Resource */
    public interface Resource<T> {
        void close(T t);

        T create();
    }

    /* renamed from: io.grpc.internal.SharedResourceHolder$ScheduledExecutorFactory */
    interface ScheduledExecutorFactory {
        ScheduledExecutorService createScheduledExecutor();
    }

    SharedResourceHolder(ScheduledExecutorFactory destroyerFactory2) {
        this.destroyerFactory = destroyerFactory2;
    }

    public static <T> T get(Resource<T> resource) {
        return holder.getInternal(resource);
    }

    public static <T> T release(Resource<T> resource, T instance) {
        return holder.releaseInternal(resource, instance);
    }

    /* access modifiers changed from: package-private */
    public synchronized <T> T getInternal(Resource<T> resource) {
        Instance instance;
        instance = this.instances.get(resource);
        if (instance == null) {
            instance = new Instance(resource.create());
            this.instances.put(resource, instance);
        }
        if (instance.destroyTask != null) {
            instance.destroyTask.cancel(false);
            instance.destroyTask = null;
        }
        instance.refcount++;
        return instance.payload;
    }

    /* access modifiers changed from: package-private */
    public synchronized <T> T releaseInternal(final Resource<T> resource, final T instance) {
        final Instance cached = this.instances.get(resource);
        if (cached != null) {
            boolean z = false;
            Preconditions.checkArgument(instance == cached.payload, "Releasing the wrong instance");
            Preconditions.checkState(cached.refcount > 0, "Refcount has already reached zero");
            cached.refcount--;
            if (cached.refcount == 0) {
                if (cached.destroyTask == null) {
                    z = true;
                }
                Preconditions.checkState(z, "Destroy task already scheduled");
                if (this.destroyer == null) {
                    this.destroyer = this.destroyerFactory.createScheduledExecutor();
                }
                cached.destroyTask = this.destroyer.schedule(new LogExceptionRunnable(new Runnable() {
                    /* JADX INFO: finally extract failed */
                    public void run() {
                        synchronized (SharedResourceHolder.this) {
                            if (cached.refcount == 0) {
                                try {
                                    resource.close(instance);
                                    SharedResourceHolder.this.instances.remove(resource);
                                    if (SharedResourceHolder.this.instances.isEmpty()) {
                                        SharedResourceHolder.this.destroyer.shutdown();
                                        ScheduledExecutorService unused = SharedResourceHolder.this.destroyer = null;
                                    }
                                } catch (Throwable th) {
                                    SharedResourceHolder.this.instances.remove(resource);
                                    if (SharedResourceHolder.this.instances.isEmpty()) {
                                        SharedResourceHolder.this.destroyer.shutdown();
                                        ScheduledExecutorService unused2 = SharedResourceHolder.this.destroyer = null;
                                    }
                                    throw th;
                                }
                            }
                        }
                    }
                }), 1, TimeUnit.SECONDS);
            }
        } else {
            throw new IllegalArgumentException("No cached instance found for " + resource);
        }
        return null;
    }

    /* renamed from: io.grpc.internal.SharedResourceHolder$Instance */
    private static class Instance {
        ScheduledFuture<?> destroyTask;
        final Object payload;
        int refcount;

        Instance(Object payload2) {
            this.payload = payload2;
        }
    }
}
