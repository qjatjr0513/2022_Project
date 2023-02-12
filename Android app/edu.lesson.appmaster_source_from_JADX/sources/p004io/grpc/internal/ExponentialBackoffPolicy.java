package p004io.grpc.internal;

import com.google.common.base.Preconditions;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import p004io.grpc.internal.BackoffPolicy;

/* renamed from: io.grpc.internal.ExponentialBackoffPolicy */
public final class ExponentialBackoffPolicy implements BackoffPolicy {
    private long initialBackoffNanos = TimeUnit.SECONDS.toNanos(1);
    private double jitter = 0.2d;
    private long maxBackoffNanos = TimeUnit.MINUTES.toNanos(2);
    private double multiplier = 1.6d;
    private long nextBackoffNanos = this.initialBackoffNanos;
    private Random random = new Random();

    /* renamed from: io.grpc.internal.ExponentialBackoffPolicy$Provider */
    public static final class Provider implements BackoffPolicy.Provider {
        public BackoffPolicy get() {
            return new ExponentialBackoffPolicy();
        }
    }

    public long nextBackoffNanos() {
        long currentBackoffNanos = this.nextBackoffNanos;
        this.nextBackoffNanos = Math.min((long) (((double) currentBackoffNanos) * this.multiplier), this.maxBackoffNanos);
        double d = this.jitter;
        return uniformRandom((-d) * ((double) currentBackoffNanos), d * ((double) currentBackoffNanos)) + currentBackoffNanos;
    }

    private long uniformRandom(double low, double high) {
        Preconditions.checkArgument(high >= low);
        return (long) ((this.random.nextDouble() * (high - low)) + low);
    }

    /* access modifiers changed from: package-private */
    public ExponentialBackoffPolicy setRandom(Random random2) {
        this.random = random2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ExponentialBackoffPolicy setInitialBackoffNanos(long initialBackoffNanos2) {
        this.initialBackoffNanos = initialBackoffNanos2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ExponentialBackoffPolicy setMaxBackoffNanos(long maxBackoffNanos2) {
        this.maxBackoffNanos = maxBackoffNanos2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ExponentialBackoffPolicy setMultiplier(double multiplier2) {
        this.multiplier = multiplier2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ExponentialBackoffPolicy setJitter(double jitter2) {
        this.jitter = jitter2;
        return this;
    }
}
