package p004io.grpc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: io.grpc.ChoiceServerCredentials */
public final class ChoiceServerCredentials extends ServerCredentials {
    private final List<ServerCredentials> creds;

    public static ServerCredentials create(ServerCredentials... creds2) {
        if (creds2.length != 0) {
            return new ChoiceServerCredentials(creds2);
        }
        throw new IllegalArgumentException("At least one credential is required");
    }

    private ChoiceServerCredentials(ServerCredentials... creds2) {
        int length = creds2.length;
        int i = 0;
        while (i < length) {
            if (creds2[i] != null) {
                i++;
            } else {
                throw new NullPointerException();
            }
        }
        this.creds = Collections.unmodifiableList(new ArrayList(Arrays.asList(creds2)));
    }

    public List<ServerCredentials> getCredentialsList() {
        return this.creds;
    }
}
