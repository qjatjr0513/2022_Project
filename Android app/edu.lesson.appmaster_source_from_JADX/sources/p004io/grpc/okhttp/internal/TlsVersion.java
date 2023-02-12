package p004io.grpc.okhttp.internal;

/* renamed from: io.grpc.okhttp.internal.TlsVersion */
public enum TlsVersion {
    TLS_1_3("TLSv1.3"),
    TLS_1_2("TLSv1.2"),
    TLS_1_1("TLSv1.1"),
    TLS_1_0("TLSv1"),
    SSL_3_0("SSLv3");
    
    final String javaName;

    private TlsVersion(String javaName2) {
        this.javaName = javaName2;
    }

    public static TlsVersion forJavaName(String javaName2) {
        if ("TLSv1.3".equals(javaName2)) {
            return TLS_1_3;
        }
        if ("TLSv1.2".equals(javaName2)) {
            return TLS_1_2;
        }
        if ("TLSv1.1".equals(javaName2)) {
            return TLS_1_1;
        }
        if ("TLSv1".equals(javaName2)) {
            return TLS_1_0;
        }
        if ("SSLv3".equals(javaName2)) {
            return SSL_3_0;
        }
        throw new IllegalArgumentException("Unexpected TLS version: " + javaName2);
    }

    public String javaName() {
        return this.javaName;
    }
}
