package p004io.grpc.internal;

import java.io.InputStream;
import javax.annotation.Nullable;

/* renamed from: io.grpc.internal.StreamListener */
public interface StreamListener {

    /* renamed from: io.grpc.internal.StreamListener$MessageProducer */
    public interface MessageProducer {
        @Nullable
        InputStream next();
    }

    void messagesAvailable(MessageProducer messageProducer);

    void onReady();
}
