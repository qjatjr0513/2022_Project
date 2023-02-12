package p004io.grpc;

/* renamed from: io.grpc.InsecureChannelCredentials */
public final class InsecureChannelCredentials extends ChannelCredentials {
    public static ChannelCredentials create() {
        return new InsecureChannelCredentials();
    }

    private InsecureChannelCredentials() {
    }

    public ChannelCredentials withoutBearerTokens() {
        return this;
    }
}
