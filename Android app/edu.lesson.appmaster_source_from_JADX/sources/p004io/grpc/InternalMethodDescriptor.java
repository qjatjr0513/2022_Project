package p004io.grpc;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;

/* renamed from: io.grpc.InternalMethodDescriptor */
public final class InternalMethodDescriptor {
    private final InternalKnownTransport transport;

    public InternalMethodDescriptor(InternalKnownTransport transport2) {
        this.transport = (InternalKnownTransport) Preconditions.checkNotNull(transport2, NotificationCompat.CATEGORY_TRANSPORT);
    }

    public Object geRawMethodName(MethodDescriptor<?, ?> descriptor) {
        return descriptor.getRawMethodName(this.transport.ordinal());
    }

    public void setRawMethodName(MethodDescriptor<?, ?> descriptor, Object o) {
        descriptor.setRawMethodName(this.transport.ordinal(), o);
    }
}
