package com.squareup.okhttp;

import java.io.IOException;

public enum Protocol {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2");
    
    private final String protocol;

    private Protocol(String protocol2) {
        this.protocol = protocol2;
    }

    public static Protocol get(String protocol2) throws IOException {
        Protocol protocol3 = HTTP_1_0;
        if (protocol2.equals(protocol3.protocol)) {
            return protocol3;
        }
        Protocol protocol4 = HTTP_1_1;
        if (protocol2.equals(protocol4.protocol)) {
            return protocol4;
        }
        Protocol protocol5 = HTTP_2;
        if (protocol2.equals(protocol5.protocol)) {
            return protocol5;
        }
        Protocol protocol6 = SPDY_3;
        if (protocol2.equals(protocol6.protocol)) {
            return protocol6;
        }
        throw new IOException("Unexpected protocol: " + protocol2);
    }

    public String toString() {
        return this.protocol;
    }
}
