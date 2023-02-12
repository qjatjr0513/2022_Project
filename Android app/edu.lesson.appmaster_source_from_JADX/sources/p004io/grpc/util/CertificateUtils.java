package p004io.grpc.util;

import com.google.common.p000io.BaseEncoding;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

/* renamed from: io.grpc.util.CertificateUtils */
public final class CertificateUtils {
    public static X509Certificate[] getX509Certificates(InputStream inputStream) throws CertificateException {
        return (X509Certificate[]) CertificateFactory.getInstance("X.509").generateCertificates(inputStream).toArray(new X509Certificate[0]);
    }

    public static PrivateKey getPrivateKey(InputStream inputStream) throws UnsupportedEncodingException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        do {
            String readLine = reader.readLine();
            line = readLine;
            if (readLine == null || "-----BEGIN PRIVATE KEY-----".equals(line)) {
                StringBuilder keyContent = new StringBuilder();
            }
            String readLine2 = reader.readLine();
            line = readLine2;
            break;
        } while ("-----BEGIN PRIVATE KEY-----".equals(line));
        StringBuilder keyContent2 = new StringBuilder();
        while (true) {
            String readLine3 = reader.readLine();
            String line2 = readLine3;
            if (readLine3 == null || "-----END PRIVATE KEY-----".equals(line2)) {
                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(BaseEncoding.base64().decode(keyContent2.toString()));
            } else {
                keyContent2.append(line2);
            }
        }
        PKCS8EncodedKeySpec keySpec2 = new PKCS8EncodedKeySpec(BaseEncoding.base64().decode(keyContent2.toString()));
        try {
            return KeyFactory.getInstance("RSA").generatePrivate(keySpec2);
        } catch (InvalidKeySpecException e) {
            try {
                return KeyFactory.getInstance("EC").generatePrivate(keySpec2);
            } catch (InvalidKeySpecException e2) {
                throw new InvalidKeySpecException("Neither RSA nor EC worked", e2);
            }
        }
    }
}
