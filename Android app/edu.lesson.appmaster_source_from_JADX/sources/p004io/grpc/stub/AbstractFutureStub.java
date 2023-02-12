package p004io.grpc.stub;

import javax.annotation.CheckReturnValue;
import p004io.grpc.CallOptions;
import p004io.grpc.Channel;
import p004io.grpc.stub.AbstractFutureStub;
import p004io.grpc.stub.AbstractStub;
import p004io.grpc.stub.ClientCalls;

@CheckReturnValue
/* renamed from: io.grpc.stub.AbstractFutureStub */
public abstract class AbstractFutureStub<S extends AbstractFutureStub<S>> extends AbstractStub<S> {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    protected AbstractFutureStub(Channel channel, CallOptions callOptions) {
        super(channel, callOptions);
    }

    public static <T extends AbstractStub<T>> T newStub(AbstractStub.StubFactory<T> factory, Channel channel) {
        return newStub(factory, channel, CallOptions.DEFAULT);
    }

    public static <T extends AbstractStub<T>> T newStub(AbstractStub.StubFactory<T> factory, Channel channel, CallOptions callOptions) {
        T stub = factory.newStub(channel, callOptions.withOption(ClientCalls.STUB_TYPE_OPTION, ClientCalls.StubType.FUTURE));
        if (stub instanceof AbstractFutureStub) {
            return stub;
        }
        throw new AssertionError(String.format("Expected AbstractFutureStub, but got %s.", new Object[]{stub.getClass()}));
    }
}
