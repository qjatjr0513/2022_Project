package p004io.grpc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: io.grpc.ChoiceChannelCredentials */
public final class ChoiceChannelCredentials extends ChannelCredentials {
    private final List<ChannelCredentials> creds;

    public static ChannelCredentials create(ChannelCredentials... creds2) {
        if (creds2.length != 0) {
            int length = creds2.length;
            int i = 0;
            while (i < length) {
                if (creds2[i] != null) {
                    i++;
                } else {
                    throw new NullPointerException();
                }
            }
            return new ChoiceChannelCredentials(Collections.unmodifiableList(new ArrayList(Arrays.asList(creds2))));
        }
        throw new IllegalArgumentException("At least one credential is required");
    }

    private ChoiceChannelCredentials(List<ChannelCredentials> creds2) {
        this.creds = creds2;
    }

    public List<ChannelCredentials> getCredentialsList() {
        return this.creds;
    }

    public ChannelCredentials withoutBearerTokens() {
        List<ChannelCredentials> credsWithoutTokens = new ArrayList<>();
        for (ChannelCredentials cred : this.creds) {
            credsWithoutTokens.add(cred.withoutBearerTokens());
        }
        return new ChoiceChannelCredentials(Collections.unmodifiableList(credsWithoutTokens));
    }
}
