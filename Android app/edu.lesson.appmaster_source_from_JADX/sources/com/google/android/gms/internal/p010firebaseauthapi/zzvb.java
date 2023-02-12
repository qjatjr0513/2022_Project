package com.google.android.gms.internal.p010firebaseauthapi;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import org.json.JSONException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvb */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzvb {
    public static void zza(String str, zzui zzui, zzuz zzuz, Type type, zzum zzum) {
        BufferedOutputStream bufferedOutputStream;
        InputStream inputStream;
        BufferedReader bufferedReader;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            byte[] bytes = zzui.zza().getBytes(Charset.defaultCharset());
            int length = bytes.length;
            httpURLConnection.setFixedLengthStreamingMode(length);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setConnectTimeout(60000);
            zzum.zza(httpURLConnection);
            bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream(), length);
            bufferedOutputStream.write(bytes, 0, length);
            bufferedOutputStream.close();
            int responseCode = httpURLConnection.getResponseCode();
            if (zzb(responseCode)) {
                inputStream = httpURLConnection.getInputStream();
            } else {
                inputStream = httpURLConnection.getErrorStream();
            }
            StringBuilder sb = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            String sb2 = sb.toString();
            if (!zzb(responseCode)) {
                zzuz.zza((String) zzuh.zza(sb2, String.class));
                return;
            } else {
                zzuz.zzb((zzuj) zzuh.zza(sb2, type));
                return;
            }
            throw th;
            throw th;
        } catch (SocketTimeoutException e) {
            zzuz.zza("TIMEOUT");
        } catch (UnknownHostException e2) {
            zzuz.zza("<<Network Error>>");
        } catch (zzpz | IOException | JSONException e3) {
            zzuz.zza(e3.getMessage());
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
    }

    private static final boolean zzb(int i) {
        return i >= 200 && i < 300;
    }
}
