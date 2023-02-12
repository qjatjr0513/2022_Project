package com.google.firebase.database.connection;

import java.net.URI;

public class HostInfo {
    private static final String LAST_SESSION_ID_PARAM = "ls";
    private static final String VERSION_PARAM = "v";
    private final String host;
    private final String namespace;
    private final boolean secure;

    public HostInfo(String host2, String namespace2, boolean secure2) {
        this.host = host2;
        this.namespace = namespace2;
        this.secure = secure2;
    }

    public String toString() {
        return "http" + (this.secure ? "s" : "") + "://" + this.host;
    }

    public static URI getConnectionUrl(String host2, boolean secure2, String namespace2, String optLastSessionId) {
        String url = (secure2 ? "wss" : "ws") + "://" + host2 + "/.ws?ns=" + namespace2 + "&" + VERSION_PARAM + "=" + "5";
        if (optLastSessionId != null) {
            url = url + "&ls=" + optLastSessionId;
        }
        return URI.create(url);
    }

    public String getHost() {
        return this.host;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public boolean isSecure() {
        return this.secure;
    }
}
