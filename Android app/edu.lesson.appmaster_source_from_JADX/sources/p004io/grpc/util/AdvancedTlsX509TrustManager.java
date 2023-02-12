package p004io.grpc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509ExtendedTrustManager;

/* renamed from: io.grpc.util.AdvancedTlsX509TrustManager */
public final class AdvancedTlsX509TrustManager extends X509ExtendedTrustManager {
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(AdvancedTlsX509TrustManager.class.getName());
    private volatile X509ExtendedTrustManager delegateManager;
    private final SslSocketAndEnginePeerVerifier socketAndEnginePeerVerifier;
    private final Verification verification;

    /* renamed from: io.grpc.util.AdvancedTlsX509TrustManager$Closeable */
    public interface Closeable extends java.io.Closeable {
        void close();
    }

    /* renamed from: io.grpc.util.AdvancedTlsX509TrustManager$SslSocketAndEnginePeerVerifier */
    public interface SslSocketAndEnginePeerVerifier {
        void verifyPeerCertificate(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException;

        void verifyPeerCertificate(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException;
    }

    /* renamed from: io.grpc.util.AdvancedTlsX509TrustManager$Verification */
    public enum Verification {
        CERTIFICATE_AND_HOST_NAME_VERIFICATION,
        CERTIFICATE_ONLY_VERIFICATION,
        INSECURELY_SKIP_ALL_VERIFICATION
    }

    private AdvancedTlsX509TrustManager(Verification verification2, SslSocketAndEnginePeerVerifier socketAndEnginePeerVerifier2) throws CertificateException {
        this.delegateManager = null;
        this.verification = verification2;
        this.socketAndEnginePeerVerifier = socketAndEnginePeerVerifier2;
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        throw new CertificateException("Not enough information to validate peer. SSLEngine or Socket required.");
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {
        checkTrusted(chain, authType, (SSLEngine) null, socket, false);
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {
        checkTrusted(chain, authType, engine, (Socket) null, false);
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {
        checkTrusted(chain, authType, engine, (Socket) null, true);
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        throw new CertificateException("Not enough information to validate peer. SSLEngine or Socket required.");
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {
        checkTrusted(chain, authType, (SSLEngine) null, socket, true);
    }

    public X509Certificate[] getAcceptedIssuers() {
        if (this.delegateManager == null) {
            return new X509Certificate[0];
        }
        return this.delegateManager.getAcceptedIssuers();
    }

    public void useSystemDefaultTrustCerts() throws CertificateException, KeyStoreException, NoSuchAlgorithmException {
        this.delegateManager = createDelegateTrustManager((KeyStore) null);
    }

    public void updateTrustCredentials(X509Certificate[] trustCerts) throws IOException, GeneralSecurityException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load((InputStream) null, (char[]) null);
        int i = 1;
        for (X509Certificate cert : trustCerts) {
            keyStore.setCertificateEntry(Integer.toString(i), cert);
            i++;
        }
        this.delegateManager = createDelegateTrustManager(keyStore);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: javax.net.ssl.TrustManager[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object[]} */
    /* JADX WARNING: type inference failed for: r4v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static javax.net.ssl.X509ExtendedTrustManager createDelegateTrustManager(java.security.KeyStore r6) throws java.security.cert.CertificateException, java.security.KeyStoreException, java.security.NoSuchAlgorithmException {
        /*
            java.lang.String r0 = javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm()
            javax.net.ssl.TrustManagerFactory r0 = javax.net.ssl.TrustManagerFactory.getInstance(r0)
            r0.init(r6)
            r1 = 0
            javax.net.ssl.TrustManager[] r2 = r0.getTrustManagers()
            r3 = 0
        L_0x0011:
            int r4 = r2.length
            if (r3 >= r4) goto L_0x0023
            r4 = r2[r3]
            boolean r4 = r4 instanceof javax.net.ssl.X509ExtendedTrustManager
            if (r4 == 0) goto L_0x0020
            r4 = r2[r3]
            r1 = r4
            javax.net.ssl.X509ExtendedTrustManager r1 = (javax.net.ssl.X509ExtendedTrustManager) r1
            goto L_0x0023
        L_0x0020:
            int r3 = r3 + 1
            goto L_0x0011
        L_0x0023:
            if (r1 == 0) goto L_0x0026
            return r1
        L_0x0026:
            java.security.cert.CertificateException r3 = new java.security.cert.CertificateException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Failed to find X509ExtendedTrustManager with default TrustManager algorithm "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.util.AdvancedTlsX509TrustManager.createDelegateTrustManager(java.security.KeyStore):javax.net.ssl.X509ExtendedTrustManager");
    }

    private void checkTrusted(X509Certificate[] chain, String authType, SSLEngine sslEngine, Socket socket, boolean checkingServer) throws CertificateException {
        if (chain == null || chain.length == 0) {
            throw new IllegalArgumentException("Want certificate verification but got null or empty certificates");
        } else if (sslEngine == null && socket == null) {
            throw new CertificateException("Not enough information to validate peer. SSLEngine or Socket required.");
        } else {
            if (this.verification != Verification.INSECURELY_SKIP_ALL_VERIFICATION) {
                X509ExtendedTrustManager currentDelegateManager = this.delegateManager;
                if (currentDelegateManager == null) {
                    throw new CertificateException("No trust roots configured");
                } else if (checkingServer) {
                    String algorithm = this.verification == Verification.CERTIFICATE_AND_HOST_NAME_VERIFICATION ? "HTTPS" : "";
                    if (sslEngine != null) {
                        SSLParameters sslParams = sslEngine.getSSLParameters();
                        sslParams.setEndpointIdentificationAlgorithm(algorithm);
                        sslEngine.setSSLParameters(sslParams);
                        currentDelegateManager.checkServerTrusted(chain, authType, sslEngine);
                    } else if (socket instanceof SSLSocket) {
                        SSLSocket sslSocket = (SSLSocket) socket;
                        SSLParameters sslParams2 = sslSocket.getSSLParameters();
                        sslParams2.setEndpointIdentificationAlgorithm(algorithm);
                        sslSocket.setSSLParameters(sslParams2);
                        currentDelegateManager.checkServerTrusted(chain, authType, sslSocket);
                    } else {
                        throw new CertificateException("socket is not a type of SSLSocket");
                    }
                } else {
                    currentDelegateManager.checkClientTrusted(chain, authType, sslEngine);
                }
            }
            SslSocketAndEnginePeerVerifier sslSocketAndEnginePeerVerifier = this.socketAndEnginePeerVerifier;
            if (sslSocketAndEnginePeerVerifier == null) {
                return;
            }
            if (sslEngine != null) {
                sslSocketAndEnginePeerVerifier.verifyPeerCertificate(chain, authType, sslEngine);
            } else {
                sslSocketAndEnginePeerVerifier.verifyPeerCertificate(chain, authType, socket);
            }
        }
    }

    public Closeable updateTrustCredentialsFromFile(File trustCertFile, long period, TimeUnit unit, ScheduledExecutorService executor) throws IOException, GeneralSecurityException {
        File file = trustCertFile;
        if (readAndUpdate(trustCertFile, 0) != 0) {
            final ScheduledFuture<?> future = executor.scheduleWithFixedDelay(new LoadFilePathExecution(trustCertFile), period, period, unit);
            return new Closeable() {
                public void close() {
                    future.cancel(false);
                }
            };
        }
        throw new GeneralSecurityException("Files were unmodified before their initial update. Probably a bug.");
    }

    /* renamed from: io.grpc.util.AdvancedTlsX509TrustManager$LoadFilePathExecution */
    private class LoadFilePathExecution implements Runnable {
        long currentTime = 0;
        File file;

        public LoadFilePathExecution(File file2) {
            this.file = file2;
        }

        public void run() {
            try {
                this.currentTime = AdvancedTlsX509TrustManager.this.readAndUpdate(this.file, this.currentTime);
            } catch (IOException | GeneralSecurityException e) {
                AdvancedTlsX509TrustManager.log.log(Level.SEVERE, "Failed refreshing trust CAs from file. Using previous CAs", e);
            }
        }
    }

    public void updateTrustCredentialsFromFile(File trustCertFile) throws IOException, GeneralSecurityException {
        if (readAndUpdate(trustCertFile, 0) == 0) {
            throw new GeneralSecurityException("Files were unmodified before their initial update. Probably a bug.");
        }
    }

    /* access modifiers changed from: private */
    public long readAndUpdate(File trustCertFile, long oldTime) throws IOException, GeneralSecurityException {
        long newTime = trustCertFile.lastModified();
        if (newTime == oldTime) {
            return oldTime;
        }
        FileInputStream inputStream = new FileInputStream(trustCertFile);
        try {
            updateTrustCredentials(CertificateUtils.getX509Certificates(inputStream));
            return newTime;
        } finally {
            inputStream.close();
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* renamed from: io.grpc.util.AdvancedTlsX509TrustManager$Builder */
    public static final class Builder {
        private SslSocketAndEnginePeerVerifier socketAndEnginePeerVerifier;
        private Verification verification;

        private Builder() {
            this.verification = Verification.CERTIFICATE_AND_HOST_NAME_VERIFICATION;
        }

        public Builder setVerification(Verification verification2) {
            this.verification = verification2;
            return this;
        }

        public Builder setSslSocketAndEnginePeerVerifier(SslSocketAndEnginePeerVerifier verifier) {
            this.socketAndEnginePeerVerifier = verifier;
            return this;
        }

        public AdvancedTlsX509TrustManager build() throws CertificateException {
            return new AdvancedTlsX509TrustManager(this.verification, this.socketAndEnginePeerVerifier);
        }
    }
}
