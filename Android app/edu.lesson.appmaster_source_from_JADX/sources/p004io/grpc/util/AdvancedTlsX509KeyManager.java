package p004io.grpc.util;

import com.google.common.base.Preconditions;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedKeyManager;

/* renamed from: io.grpc.util.AdvancedTlsX509KeyManager */
public final class AdvancedTlsX509KeyManager extends X509ExtendedKeyManager {
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(AdvancedTlsX509KeyManager.class.getName());
    private volatile KeyInfo keyInfo;

    /* renamed from: io.grpc.util.AdvancedTlsX509KeyManager$Closeable */
    public interface Closeable extends java.io.Closeable {
        void close();
    }

    public PrivateKey getPrivateKey(String alias) {
        if (alias.equals("default")) {
            return this.keyInfo.key;
        }
        return null;
    }

    public X509Certificate[] getCertificateChain(String alias) {
        if (alias.equals("default")) {
            return (X509Certificate[]) Arrays.copyOf(this.keyInfo.certs, this.keyInfo.certs.length);
        }
        return null;
    }

    public String[] getClientAliases(String keyType, Principal[] issuers) {
        return new String[]{"default"};
    }

    public String chooseClientAlias(String[] keyType, Principal[] issuers, Socket socket) {
        return "default";
    }

    public String chooseEngineClientAlias(String[] keyType, Principal[] issuers, SSLEngine engine) {
        return "default";
    }

    public String[] getServerAliases(String keyType, Principal[] issuers) {
        return new String[]{"default"};
    }

    public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
        return "default";
    }

    public String chooseEngineServerAlias(String keyType, Principal[] issuers, SSLEngine engine) {
        return "default";
    }

    public void updateIdentityCredentials(PrivateKey key, X509Certificate[] certs) {
        this.keyInfo = new KeyInfo((PrivateKey) Preconditions.checkNotNull(key, "key"), (X509Certificate[]) Preconditions.checkNotNull(certs, "certs"));
    }

    public Closeable updateIdentityCredentialsFromFile(File keyFile, File certFile, long period, TimeUnit unit, ScheduledExecutorService executor) throws IOException, GeneralSecurityException {
        if (readAndUpdate(keyFile, certFile, 0, 0).success) {
            final ScheduledFuture<?> future = executor.scheduleWithFixedDelay(new LoadFilePathExecution(keyFile, certFile), period, period, unit);
            return new Closeable() {
                public void close() {
                    future.cancel(false);
                }
            };
        }
        throw new GeneralSecurityException("Files were unmodified before their initial update. Probably a bug.");
    }

    public void updateIdentityCredentialsFromFile(File keyFile, File certFile) throws IOException, GeneralSecurityException {
        if (!readAndUpdate(keyFile, certFile, 0, 0).success) {
            throw new GeneralSecurityException("Files were unmodified before their initial update. Probably a bug.");
        }
    }

    /* renamed from: io.grpc.util.AdvancedTlsX509KeyManager$KeyInfo */
    private static class KeyInfo {
        final X509Certificate[] certs;
        final PrivateKey key;

        public KeyInfo(PrivateKey key2, X509Certificate[] certs2) {
            this.key = key2;
            this.certs = certs2;
        }
    }

    /* renamed from: io.grpc.util.AdvancedTlsX509KeyManager$LoadFilePathExecution */
    private class LoadFilePathExecution implements Runnable {
        File certFile;
        long currentCertTime = 0;
        long currentKeyTime = 0;
        File keyFile;

        public LoadFilePathExecution(File keyFile2, File certFile2) {
            this.keyFile = keyFile2;
            this.certFile = certFile2;
        }

        public void run() {
            try {
                UpdateResult newResult = AdvancedTlsX509KeyManager.this.readAndUpdate(this.keyFile, this.certFile, this.currentKeyTime, this.currentCertTime);
                if (newResult.success) {
                    this.currentKeyTime = newResult.keyTime;
                    this.currentCertTime = newResult.certTime;
                }
            } catch (IOException | GeneralSecurityException e) {
                AdvancedTlsX509KeyManager.log.log(Level.SEVERE, "Failed refreshing private key and certificate chain from files. Using previous ones", e);
            }
        }
    }

    /* renamed from: io.grpc.util.AdvancedTlsX509KeyManager$UpdateResult */
    private static class UpdateResult {
        long certTime;
        long keyTime;
        boolean success;

        public UpdateResult(boolean success2, long keyTime2, long certTime2) {
            this.success = success2;
            this.keyTime = keyTime2;
            this.certTime = certTime2;
        }
    }

    /* access modifiers changed from: private */
    public UpdateResult readAndUpdate(File keyFile, File certFile, long oldKeyTime, long oldCertTime) throws IOException, GeneralSecurityException {
        FileInputStream certInputStream;
        long newKeyTime = keyFile.lastModified();
        long newCertTime = certFile.lastModified();
        if (newKeyTime == oldKeyTime || newCertTime == oldCertTime) {
            File file = keyFile;
            File file2 = certFile;
            return new UpdateResult(false, oldKeyTime, oldCertTime);
        }
        FileInputStream keyInputStream = new FileInputStream(keyFile);
        try {
            PrivateKey key = CertificateUtils.getPrivateKey(keyInputStream);
            try {
                certInputStream = new FileInputStream(certFile);
                updateIdentityCredentials(key, CertificateUtils.getX509Certificates(certInputStream));
                UpdateResult updateResult = new UpdateResult(true, newKeyTime, newCertTime);
                certInputStream.close();
                keyInputStream.close();
                return updateResult;
            } catch (Throwable th) {
                th = th;
                keyInputStream.close();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            File file3 = certFile;
            keyInputStream.close();
            throw th;
        }
    }
}
