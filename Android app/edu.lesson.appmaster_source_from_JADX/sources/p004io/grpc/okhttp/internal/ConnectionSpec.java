package p004io.grpc.okhttp.internal;

import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* renamed from: io.grpc.okhttp.internal.ConnectionSpec */
public final class ConnectionSpec {
    private static final CipherSuite[] APPROVED_CIPHER_SUITES;
    public static final ConnectionSpec CLEARTEXT = new Builder(false).build();
    public static final ConnectionSpec COMPATIBLE_TLS;
    public static final ConnectionSpec MODERN_TLS;
    /* access modifiers changed from: private */
    public final String[] cipherSuites;
    final boolean supportsTlsExtensions;
    final boolean tls;
    /* access modifiers changed from: private */
    public final String[] tlsVersions;

    static {
        CipherSuite[] cipherSuiteArr = {CipherSuite.TLS_AES_128_GCM_SHA256, CipherSuite.TLS_AES_256_GCM_SHA384, CipherSuite.TLS_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA};
        APPROVED_CIPHER_SUITES = cipherSuiteArr;
        ConnectionSpec build = new Builder(true).cipherSuites(cipherSuiteArr).tlsVersions(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2).supportsTlsExtensions(true).build();
        MODERN_TLS = build;
        COMPATIBLE_TLS = new Builder(build).tlsVersions(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0).supportsTlsExtensions(true).build();
    }

    private ConnectionSpec(Builder builder) {
        this.tls = builder.tls;
        this.cipherSuites = builder.cipherSuites;
        this.tlsVersions = builder.tlsVersions;
        this.supportsTlsExtensions = builder.supportsTlsExtensions;
    }

    public boolean isTls() {
        return this.tls;
    }

    public List<CipherSuite> cipherSuites() {
        String[] strArr = this.cipherSuites;
        if (strArr == null) {
            return null;
        }
        CipherSuite[] result = new CipherSuite[strArr.length];
        int i = 0;
        while (true) {
            String[] strArr2 = this.cipherSuites;
            if (i >= strArr2.length) {
                return Util.immutableList((T[]) result);
            }
            result[i] = CipherSuite.forJavaName(strArr2[i]);
            i++;
        }
    }

    public List<TlsVersion> tlsVersions() {
        TlsVersion[] result = new TlsVersion[this.tlsVersions.length];
        int i = 0;
        while (true) {
            String[] strArr = this.tlsVersions;
            if (i >= strArr.length) {
                return Util.immutableList((T[]) result);
            }
            result[i] = TlsVersion.forJavaName(strArr[i]);
            i++;
        }
    }

    public boolean supportsTlsExtensions() {
        return this.supportsTlsExtensions;
    }

    public void apply(SSLSocket sslSocket, boolean isFallback) {
        ConnectionSpec specToApply = supportedSpec(sslSocket, isFallback);
        sslSocket.setEnabledProtocols(specToApply.tlsVersions);
        String[] cipherSuitesToEnable = specToApply.cipherSuites;
        if (cipherSuitesToEnable != null) {
            sslSocket.setEnabledCipherSuites(cipherSuitesToEnable);
        }
    }

    /* JADX WARNING: type inference failed for: r2v7, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private p004io.grpc.okhttp.internal.ConnectionSpec supportedSpec(javax.net.ssl.SSLSocket r9, boolean r10) {
        /*
            r8 = this;
            r0 = 0
            java.lang.String[] r1 = r8.cipherSuites
            if (r1 == 0) goto L_0x0014
            java.lang.String[] r1 = r9.getEnabledCipherSuites()
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            java.lang.String[] r3 = r8.cipherSuites
            java.lang.Object[] r2 = p004io.grpc.okhttp.internal.Util.intersect(r2, r3, r1)
            r0 = r2
            java.lang.String[] r0 = (java.lang.String[]) r0
        L_0x0014:
            if (r10 == 0) goto L_0x0042
            java.lang.String r1 = "TLS_FALLBACK_SCSV"
            java.lang.String[] r2 = r9.getSupportedCipherSuites()
            java.util.List r2 = java.util.Arrays.asList(r2)
            java.lang.String r3 = "TLS_FALLBACK_SCSV"
            boolean r2 = r2.contains(r3)
            if (r2 == 0) goto L_0x0042
            if (r0 == 0) goto L_0x002d
            r4 = r0
            goto L_0x0031
        L_0x002d:
            java.lang.String[] r4 = r9.getEnabledCipherSuites()
        L_0x0031:
            int r5 = r4.length
            int r5 = r5 + 1
            java.lang.String[] r5 = new java.lang.String[r5]
            int r6 = r4.length
            r7 = 0
            java.lang.System.arraycopy(r4, r7, r5, r7, r6)
            int r6 = r5.length
            int r6 = r6 + -1
            r5[r6] = r3
            r0 = r5
        L_0x0042:
            java.lang.String[] r1 = r9.getEnabledProtocols()
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            java.lang.String[] r3 = r8.tlsVersions
            java.lang.Object[] r2 = p004io.grpc.okhttp.internal.Util.intersect(r2, r3, r1)
            java.lang.String[] r2 = (java.lang.String[]) r2
            io.grpc.okhttp.internal.ConnectionSpec$Builder r3 = new io.grpc.okhttp.internal.ConnectionSpec$Builder
            r3.<init>((p004io.grpc.okhttp.internal.ConnectionSpec) r8)
            io.grpc.okhttp.internal.ConnectionSpec$Builder r3 = r3.cipherSuites((java.lang.String[]) r0)
            io.grpc.okhttp.internal.ConnectionSpec$Builder r3 = r3.tlsVersions((java.lang.String[]) r2)
            io.grpc.okhttp.internal.ConnectionSpec r3 = r3.build()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.okhttp.internal.ConnectionSpec.supportedSpec(javax.net.ssl.SSLSocket, boolean):io.grpc.okhttp.internal.ConnectionSpec");
    }

    public boolean isCompatible(SSLSocket socket) {
        if (!this.tls) {
            return false;
        }
        if (!nonEmptyIntersection(this.tlsVersions, socket.getEnabledProtocols())) {
            return false;
        }
        if (this.cipherSuites != null) {
            return nonEmptyIntersection(this.cipherSuites, socket.getEnabledCipherSuites());
        } else if (socket.getEnabledCipherSuites().length > 0) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean nonEmptyIntersection(String[] a, String[] b) {
        if (a == null || b == null || a.length == 0 || b.length == 0) {
            return false;
        }
        for (String toFind : a) {
            if (contains(b, toFind)) {
                return true;
            }
        }
        return false;
    }

    private static <T> boolean contains(T[] array, T value) {
        for (T arrayValue : array) {
            if (Util.equal(value, arrayValue)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object other) {
        if (!(other instanceof ConnectionSpec)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        ConnectionSpec that = (ConnectionSpec) other;
        boolean z = this.tls;
        if (z != that.tls) {
            return false;
        }
        if (!z || (Arrays.equals(this.cipherSuites, that.cipherSuites) && Arrays.equals(this.tlsVersions, that.tlsVersions) && this.supportsTlsExtensions == that.supportsTlsExtensions)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.tls) {
            return (((((17 * 31) + Arrays.hashCode(this.cipherSuites)) * 31) + Arrays.hashCode(this.tlsVersions)) * 31) + (this.supportsTlsExtensions ^ true ? 1 : 0);
        }
        return 17;
    }

    public String toString() {
        if (!this.tls) {
            return "ConnectionSpec()";
        }
        List<CipherSuite> cipherSuites2 = cipherSuites();
        return "ConnectionSpec(cipherSuites=" + (cipherSuites2 == null ? "[use default]" : cipherSuites2.toString()) + ", tlsVersions=" + tlsVersions() + ", supportsTlsExtensions=" + this.supportsTlsExtensions + ")";
    }

    /* renamed from: io.grpc.okhttp.internal.ConnectionSpec$Builder */
    public static final class Builder {
        /* access modifiers changed from: private */
        public String[] cipherSuites;
        /* access modifiers changed from: private */
        public boolean supportsTlsExtensions;
        /* access modifiers changed from: private */
        public boolean tls;
        /* access modifiers changed from: private */
        public String[] tlsVersions;

        public Builder(boolean tls2) {
            this.tls = tls2;
        }

        public Builder(ConnectionSpec connectionSpec) {
            this.tls = connectionSpec.tls;
            this.cipherSuites = connectionSpec.cipherSuites;
            this.tlsVersions = connectionSpec.tlsVersions;
            this.supportsTlsExtensions = connectionSpec.supportsTlsExtensions;
        }

        public Builder cipherSuites(CipherSuite... cipherSuites2) {
            if (this.tls) {
                String[] strings = new String[cipherSuites2.length];
                for (int i = 0; i < cipherSuites2.length; i++) {
                    strings[i] = cipherSuites2[i].javaName;
                }
                this.cipherSuites = strings;
                return this;
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public Builder cipherSuites(String... cipherSuites2) {
            if (this.tls) {
                if (cipherSuites2 == null) {
                    this.cipherSuites = null;
                } else {
                    this.cipherSuites = (String[]) cipherSuites2.clone();
                }
                return this;
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public Builder tlsVersions(TlsVersion... tlsVersions2) {
            if (!this.tls) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            } else if (tlsVersions2.length != 0) {
                String[] strings = new String[tlsVersions2.length];
                for (int i = 0; i < tlsVersions2.length; i++) {
                    strings[i] = tlsVersions2[i].javaName;
                }
                this.tlsVersions = strings;
                return this;
            } else {
                throw new IllegalArgumentException("At least one TlsVersion is required");
            }
        }

        public Builder tlsVersions(String... tlsVersions2) {
            if (this.tls) {
                if (tlsVersions2 == null) {
                    this.tlsVersions = null;
                } else {
                    this.tlsVersions = (String[]) tlsVersions2.clone();
                }
                return this;
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public Builder supportsTlsExtensions(boolean supportsTlsExtensions2) {
            if (this.tls) {
                this.supportsTlsExtensions = supportsTlsExtensions2;
                return this;
            }
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }

        public ConnectionSpec build() {
            return new ConnectionSpec(this);
        }
    }
}
