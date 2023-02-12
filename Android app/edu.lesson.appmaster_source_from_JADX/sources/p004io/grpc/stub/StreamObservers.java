package p004io.grpc.stub;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.base.Preconditions;
import java.util.Iterator;

/* renamed from: io.grpc.stub.StreamObservers */
public final class StreamObservers {
    public static <V> void copyWithFlowControl(final Iterator<V> source, final CallStreamObserver<V> target) {
        Preconditions.checkNotNull(source, "source");
        Preconditions.checkNotNull(target, TypedValues.AttributesType.S_TARGET);
        target.setOnReadyHandler(new Runnable() {
            private boolean completed;

            public void run() {
                if (!this.completed) {
                    while (CallStreamObserver.this.isReady() && source.hasNext()) {
                        CallStreamObserver.this.onNext(source.next());
                    }
                    if (!source.hasNext()) {
                        this.completed = true;
                        CallStreamObserver.this.onCompleted();
                    }
                }
            }
        });
    }

    public static <V> void copyWithFlowControl(Iterable<V> source, CallStreamObserver<V> target) {
        Preconditions.checkNotNull(source, "source");
        copyWithFlowControl(source.iterator(), target);
    }
}
