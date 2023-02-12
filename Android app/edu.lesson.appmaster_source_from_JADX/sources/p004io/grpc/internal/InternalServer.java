package p004io.grpc.internal;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.List;
import javax.annotation.Nullable;
import p004io.grpc.InternalChannelz;
import p004io.grpc.InternalInstrumented;

/* renamed from: io.grpc.internal.InternalServer */
public interface InternalServer {
    SocketAddress getListenSocketAddress();

    List<? extends SocketAddress> getListenSocketAddresses();

    @Nullable
    InternalInstrumented<InternalChannelz.SocketStats> getListenSocketStats();

    @Nullable
    List<InternalInstrumented<InternalChannelz.SocketStats>> getListenSocketStatsList();

    void shutdown();

    void start(ServerListener serverListener) throws IOException;
}
