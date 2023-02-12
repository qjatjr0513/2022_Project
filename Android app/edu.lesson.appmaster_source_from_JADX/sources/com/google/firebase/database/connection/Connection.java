package com.google.firebase.database.connection;

import com.google.common.net.HttpHeaders;
import com.google.firebase.database.connection.WebsocketConnection;
import com.google.firebase.database.logging.LogWrapper;
import java.util.HashMap;
import java.util.Map;

class Connection implements WebsocketConnection.Delegate {
    private static final String REQUEST_PAYLOAD = "d";
    private static final String REQUEST_TYPE = "t";
    private static final String REQUEST_TYPE_DATA = "d";
    private static final String SERVER_CONTROL_MESSAGE = "c";
    private static final String SERVER_CONTROL_MESSAGE_DATA = "d";
    private static final String SERVER_CONTROL_MESSAGE_HELLO = "h";
    private static final String SERVER_CONTROL_MESSAGE_RESET = "r";
    private static final String SERVER_CONTROL_MESSAGE_SHUTDOWN = "s";
    private static final String SERVER_CONTROL_MESSAGE_TYPE = "t";
    private static final String SERVER_DATA_MESSAGE = "d";
    private static final String SERVER_ENVELOPE_DATA = "d";
    private static final String SERVER_ENVELOPE_TYPE = "t";
    private static final String SERVER_HELLO_HOST = "h";
    private static final String SERVER_HELLO_SESSION_ID = "s";
    private static final String SERVER_HELLO_TIMESTAMP = "ts";
    private static long connectionIds = 0;
    private WebsocketConnection conn;
    private Delegate delegate;
    private HostInfo hostInfo;
    private final LogWrapper logger;
    private State state = State.REALTIME_CONNECTING;

    public interface Delegate {
        void onCacheHost(String str);

        void onDataMessage(Map<String, Object> map);

        void onDisconnect(DisconnectReason disconnectReason);

        void onKill(String str);

        void onReady(long j, String str);
    }

    public enum DisconnectReason {
        SERVER_RESET,
        OTHER
    }

    private enum State {
        REALTIME_CONNECTING,
        REALTIME_CONNECTED,
        REALTIME_DISCONNECTED
    }

    public Connection(ConnectionContext context, HostInfo hostInfo2, String cachedHost, Delegate delegate2, String optLastSessionId, String appCheckToken) {
        long connId = connectionIds;
        connectionIds = 1 + connId;
        this.hostInfo = hostInfo2;
        this.delegate = delegate2;
        this.logger = new LogWrapper(context.getLogger(), HttpHeaders.CONNECTION, "conn_" + connId);
        this.conn = new WebsocketConnection(context, hostInfo2, cachedHost, appCheckToken, this, optLastSessionId);
    }

    public void open() {
        if (this.logger.logsDebug()) {
            this.logger.debug("Opening a connection", new Object[0]);
        }
        this.conn.open();
    }

    public void close(DisconnectReason reason) {
        if (this.state != State.REALTIME_DISCONNECTED) {
            if (this.logger.logsDebug()) {
                this.logger.debug("closing realtime connection", new Object[0]);
            }
            this.state = State.REALTIME_DISCONNECTED;
            WebsocketConnection websocketConnection = this.conn;
            if (websocketConnection != null) {
                websocketConnection.close();
                this.conn = null;
            }
            this.delegate.onDisconnect(reason);
        }
    }

    public void close() {
        close(DisconnectReason.OTHER);
    }

    public void sendRequest(Map<String, Object> message, boolean isSensitive) {
        Map<String, Object> request = new HashMap<>();
        request.put("t", "d");
        request.put("d", message);
        sendData(request, isSensitive);
    }

    public void onMessage(Map<String, Object> message) {
        try {
            String messageType = (String) message.get("t");
            if (messageType == null) {
                if (this.logger.logsDebug()) {
                    this.logger.debug("Failed to parse server message: missing message type:" + message.toString(), new Object[0]);
                }
                close();
            } else if (messageType.equals("d")) {
                onDataMessage((Map) message.get("d"));
            } else if (messageType.equals(SERVER_CONTROL_MESSAGE)) {
                onControlMessage((Map) message.get("d"));
            } else if (this.logger.logsDebug()) {
                this.logger.debug("Ignoring unknown server message type: " + messageType, new Object[0]);
            }
        } catch (ClassCastException e) {
            if (this.logger.logsDebug()) {
                this.logger.debug("Failed to parse server message: " + e.toString(), new Object[0]);
            }
            close();
        }
    }

    public void onDisconnect(boolean wasEverConnected) {
        this.conn = null;
        if (wasEverConnected || this.state != State.REALTIME_CONNECTING) {
            if (this.logger.logsDebug()) {
                this.logger.debug("Realtime connection lost", new Object[0]);
            }
        } else if (this.logger.logsDebug()) {
            this.logger.debug("Realtime connection failed", new Object[0]);
        }
        close();
    }

    private void onDataMessage(Map<String, Object> data) {
        if (this.logger.logsDebug()) {
            this.logger.debug("received data message: " + data.toString(), new Object[0]);
        }
        this.delegate.onDataMessage(data);
    }

    private void onControlMessage(Map<String, Object> data) {
        if (this.logger.logsDebug()) {
            this.logger.debug("Got control message: " + data.toString(), new Object[0]);
        }
        try {
            String messageType = (String) data.get("t");
            if (messageType == null) {
                if (this.logger.logsDebug()) {
                    this.logger.debug("Got invalid control message: " + data.toString(), new Object[0]);
                }
                close();
            } else if (messageType.equals("s")) {
                onConnectionShutdown((String) data.get("d"));
            } else if (messageType.equals(SERVER_CONTROL_MESSAGE_RESET)) {
                onReset((String) data.get("d"));
            } else if (messageType.equals("h")) {
                onHandshake((Map) data.get("d"));
            } else if (this.logger.logsDebug()) {
                this.logger.debug("Ignoring unknown control message: " + messageType, new Object[0]);
            }
        } catch (ClassCastException e) {
            if (this.logger.logsDebug()) {
                this.logger.debug("Failed to parse control message: " + e.toString(), new Object[0]);
            }
            close();
        }
    }

    private void onConnectionShutdown(String reason) {
        if (this.logger.logsDebug()) {
            this.logger.debug("Connection shutdown command received. Shutting down...", new Object[0]);
        }
        this.delegate.onKill(reason);
        close();
    }

    private void onHandshake(Map<String, Object> handshake) {
        long timestamp = ((Long) handshake.get(SERVER_HELLO_TIMESTAMP)).longValue();
        this.delegate.onCacheHost((String) handshake.get("h"));
        String sessionId = (String) handshake.get("s");
        if (this.state == State.REALTIME_CONNECTING) {
            this.conn.start();
            onConnectionReady(timestamp, sessionId);
        }
    }

    private void onConnectionReady(long timestamp, String sessionId) {
        if (this.logger.logsDebug()) {
            this.logger.debug("realtime connection established", new Object[0]);
        }
        this.state = State.REALTIME_CONNECTED;
        this.delegate.onReady(timestamp, sessionId);
    }

    private void onReset(String host) {
        if (this.logger.logsDebug()) {
            this.logger.debug("Got a reset; killing connection to " + this.hostInfo.getHost() + "; Updating internalHost to " + host, new Object[0]);
        }
        this.delegate.onCacheHost(host);
        close(DisconnectReason.SERVER_RESET);
    }

    private void sendData(Map<String, Object> data, boolean isSensitive) {
        if (this.state != State.REALTIME_CONNECTED) {
            this.logger.debug("Tried to send on an unconnected connection", new Object[0]);
            return;
        }
        if (isSensitive) {
            this.logger.debug("Sending data (contents hidden)", new Object[0]);
        } else {
            this.logger.debug("Sending data: %s", data);
        }
        this.conn.send(data);
    }

    public void injectConnectionFailure() {
        close();
    }
}
