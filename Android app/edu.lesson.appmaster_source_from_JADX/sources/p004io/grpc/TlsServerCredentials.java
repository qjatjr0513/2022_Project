package p004io.grpc;

import com.google.common.base.Preconditions;
import com.google.common.p000io.ByteStreams;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;

/* renamed from: io.grpc.TlsServerCredentials */
public final class TlsServerCredentials extends ServerCredentials {
    private final byte[] certificateChain;
    private final ClientAuth clientAuth;
    private final boolean fakeFeature;
    private final List<KeyManager> keyManagers;
    private final byte[] privateKey;
    private final String privateKeyPassword;
    private final byte[] rootCertificates;
    private final List<TrustManager> trustManagers;

    /* renamed from: io.grpc.TlsServerCredentials$ClientAuth */
    public enum ClientAuth {
        NONE,
        OPTIONAL,
        REQUIRE
    }

    /* renamed from: io.grpc.TlsServerCredentials$Feature */
    public enum Feature {
        FAKE,
        MTLS,
        CUSTOM_MANAGERS
    }

    public static ServerCredentials create(File certChain, File privateKey2) throws IOException {
        return newBuilder().keyManager(certChain, privateKey2).build();
    }

    public static ServerCredentials create(InputStream certChain, InputStream privateKey2) throws IOException {
        return newBuilder().keyManager(certChain, privateKey2).build();
    }

    TlsServerCredentials(Builder builder) {
        this.fakeFeature = builder.fakeFeature;
        this.certificateChain = builder.certificateChain;
        this.privateKey = builder.privateKey;
        this.privateKeyPassword = builder.privateKeyPassword;
        this.keyManagers = builder.keyManagers;
        this.clientAuth = builder.clientAuth;
        this.rootCertificates = builder.rootCertificates;
        this.trustManagers = builder.trustManagers;
    }

    public byte[] getCertificateChain() {
        byte[] bArr = this.certificateChain;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public byte[] getPrivateKey() {
        byte[] bArr = this.privateKey;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public String getPrivateKeyPassword() {
        return this.privateKeyPassword;
    }

    public List<KeyManager> getKeyManagers() {
        return this.keyManagers;
    }

    public ClientAuth getClientAuth() {
        return this.clientAuth;
    }

    public byte[] getRootCertificates() {
        byte[] bArr = this.rootCertificates;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public List<TrustManager> getTrustManagers() {
        return this.trustManagers;
    }

    public Set<Feature> incomprehensible(Set<Feature> understoodFeatures) {
        Set<Feature> incomprehensible = EnumSet.noneOf(Feature.class);
        if (this.fakeFeature) {
            requiredFeature(understoodFeatures, incomprehensible, Feature.FAKE);
        }
        if (this.clientAuth != ClientAuth.NONE) {
            requiredFeature(understoodFeatures, incomprehensible, Feature.MTLS);
        }
        if (!(this.keyManagers == null && this.trustManagers == null)) {
            requiredFeature(understoodFeatures, incomprehensible, Feature.CUSTOM_MANAGERS);
        }
        return Collections.unmodifiableSet(incomprehensible);
    }

    private static void requiredFeature(Set<Feature> understoodFeatures, Set<Feature> incomprehensible, Feature feature) {
        if (!understoodFeatures.contains(feature)) {
            incomprehensible.add(feature);
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* renamed from: io.grpc.TlsServerCredentials$Builder */
    public static final class Builder {
        /* access modifiers changed from: private */
        public byte[] certificateChain;
        /* access modifiers changed from: private */
        public ClientAuth clientAuth;
        /* access modifiers changed from: private */
        public boolean fakeFeature;
        /* access modifiers changed from: private */
        public List<KeyManager> keyManagers;
        /* access modifiers changed from: private */
        public byte[] privateKey;
        /* access modifiers changed from: private */
        public String privateKeyPassword;
        /* access modifiers changed from: private */
        public byte[] rootCertificates;
        /* access modifiers changed from: private */
        public List<TrustManager> trustManagers;

        private Builder() {
            this.clientAuth = ClientAuth.NONE;
        }

        public Builder requireFakeFeature() {
            this.fakeFeature = true;
            return this;
        }

        public Builder keyManager(File certChain, File privateKey2) throws IOException {
            return keyManager(certChain, privateKey2, (String) null);
        }

        public Builder keyManager(File certChain, File privateKey2, String privateKeyPassword2) throws IOException {
            InputStream privateKeyIs = new FileInputStream(certChain);
            try {
                privateKeyIs = new FileInputStream(privateKey2);
                return keyManager(privateKeyIs, privateKeyIs, privateKeyPassword2);
            } catch (Throwable th) {
                throw th;
            } finally {
                privateKeyIs.close();
            }
        }

        public Builder keyManager(InputStream certChain, InputStream privateKey2) throws IOException {
            return keyManager(certChain, privateKey2, (String) null);
        }

        public Builder keyManager(InputStream certChain, InputStream privateKey2, String privateKeyPassword2) throws IOException {
            byte[] certChainBytes = ByteStreams.toByteArray(certChain);
            byte[] privateKeyBytes = ByteStreams.toByteArray(privateKey2);
            clearKeyManagers();
            this.certificateChain = certChainBytes;
            this.privateKey = privateKeyBytes;
            this.privateKeyPassword = privateKeyPassword2;
            return this;
        }

        public Builder keyManager(KeyManager... keyManagers2) {
            List<KeyManager> keyManagerList = Collections.unmodifiableList(new ArrayList(Arrays.asList(keyManagers2)));
            clearKeyManagers();
            this.keyManagers = keyManagerList;
            return this;
        }

        private void clearKeyManagers() {
            this.certificateChain = null;
            this.privateKey = null;
            this.privateKeyPassword = null;
            this.keyManagers = null;
        }

        public Builder clientAuth(ClientAuth clientAuth2) {
            Preconditions.checkNotNull(clientAuth2, "clientAuth");
            this.clientAuth = clientAuth2;
            return this;
        }

        public Builder trustManager(File rootCerts) throws IOException {
            InputStream rootCertsIs = new FileInputStream(rootCerts);
            try {
                return trustManager(rootCertsIs);
            } finally {
                rootCertsIs.close();
            }
        }

        public Builder trustManager(InputStream rootCerts) throws IOException {
            byte[] rootCertsBytes = ByteStreams.toByteArray(rootCerts);
            clearTrustManagers();
            this.rootCertificates = rootCertsBytes;
            return this;
        }

        public Builder trustManager(TrustManager... trustManagers2) {
            List<TrustManager> trustManagerList = Collections.unmodifiableList(new ArrayList(Arrays.asList(trustManagers2)));
            clearTrustManagers();
            this.trustManagers = trustManagerList;
            return this;
        }

        private void clearTrustManagers() {
            this.rootCertificates = null;
            this.trustManagers = null;
        }

        public ServerCredentials build() {
            if (this.certificateChain != null || this.keyManagers != null) {
                return new TlsServerCredentials(this);
            }
            throw new IllegalStateException("A key manager is required");
        }
    }
}
