package p004io.grpc;

/* renamed from: io.grpc.InsecureServerCredentials */
public final class InsecureServerCredentials extends ServerCredentials {
    public static ServerCredentials create() {
        return new InsecureServerCredentials();
    }

    private InsecureServerCredentials() {
    }
}
