package p004io.grpc.stub;

import com.google.common.base.Preconditions;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import p004io.grpc.CallCredentials;
import p004io.grpc.CallOptions;
import p004io.grpc.Channel;
import p004io.grpc.ClientInterceptor;
import p004io.grpc.ClientInterceptors;
import p004io.grpc.Deadline;
import p004io.grpc.stub.AbstractStub;

@CheckReturnValue
/* renamed from: io.grpc.stub.AbstractStub */
public abstract class AbstractStub<S extends AbstractStub<S>> {
    private final CallOptions callOptions;
    private final Channel channel;

    /* renamed from: io.grpc.stub.AbstractStub$StubFactory */
    public interface StubFactory<T extends AbstractStub<T>> {
        T newStub(Channel channel, CallOptions callOptions);
    }

    /* access modifiers changed from: protected */
    public abstract S build(Channel channel2, CallOptions callOptions2);

    protected AbstractStub(Channel channel2) {
        this(channel2, CallOptions.DEFAULT);
    }

    protected AbstractStub(Channel channel2, CallOptions callOptions2) {
        this.channel = (Channel) Preconditions.checkNotNull(channel2, "channel");
        this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions2, "callOptions");
    }

    public final Channel getChannel() {
        return this.channel;
    }

    public final CallOptions getCallOptions() {
        return this.callOptions;
    }

    public static <T extends AbstractStub<T>> T newStub(StubFactory<T> factory, Channel channel2) {
        return newStub(factory, channel2, CallOptions.DEFAULT);
    }

    public static <T extends AbstractStub<T>> T newStub(StubFactory<T> factory, Channel channel2, CallOptions callOptions2) {
        return factory.newStub(channel2, callOptions2);
    }

    public final S withDeadline(@Nullable Deadline deadline) {
        return build(this.channel, this.callOptions.withDeadline(deadline));
    }

    public final S withDeadlineAfter(long duration, TimeUnit unit) {
        return build(this.channel, this.callOptions.withDeadlineAfter(duration, unit));
    }

    public final S withExecutor(Executor executor) {
        return build(this.channel, this.callOptions.withExecutor(executor));
    }

    public final S withCompression(String compressorName) {
        return build(this.channel, this.callOptions.withCompression(compressorName));
    }

    @Deprecated
    public final S withChannel(Channel newChannel) {
        return build(newChannel, this.callOptions);
    }

    public final <T> S withOption(CallOptions.Key<T> key, T value) {
        return build(this.channel, this.callOptions.withOption(key, value));
    }

    public final S withInterceptors(ClientInterceptor... interceptors) {
        return build(ClientInterceptors.intercept(this.channel, interceptors), this.callOptions);
    }

    public final S withCallCredentials(CallCredentials credentials) {
        return build(this.channel, this.callOptions.withCallCredentials(credentials));
    }

    public final S withWaitForReady() {
        return build(this.channel, this.callOptions.withWaitForReady());
    }

    public final S withMaxInboundMessageSize(int maxSize) {
        return build(this.channel, this.callOptions.withMaxInboundMessageSize(maxSize));
    }

    public final S withMaxOutboundMessageSize(int maxSize) {
        return build(this.channel, this.callOptions.withMaxOutboundMessageSize(maxSize));
    }
}
