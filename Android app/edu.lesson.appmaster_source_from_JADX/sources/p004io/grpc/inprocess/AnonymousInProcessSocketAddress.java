package p004io.grpc.inprocess;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.SocketAddress;
import javax.annotation.Nullable;

/* renamed from: io.grpc.inprocess.AnonymousInProcessSocketAddress */
public final class AnonymousInProcessSocketAddress extends SocketAddress {
    private static final long serialVersionUID = -8567592561863414695L;
    @Nullable
    private InProcessServer server;

    /* access modifiers changed from: package-private */
    @Nullable
    public synchronized InProcessServer getServer() {
        return this.server;
    }

    /* access modifiers changed from: package-private */
    public synchronized void setServer(InProcessServer server2) throws IOException {
        if (this.server == null) {
            this.server = server2;
        } else {
            throw new IOException("Server instance already registered");
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void clearServer(InProcessServer server2) {
        Preconditions.checkState(this.server == server2);
        this.server = null;
    }
}
