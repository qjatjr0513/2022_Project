package com.google.android.gms.internal.firebase_messaging;

import com.google.firebase.encoders.annotations.Encodable;
import com.google.firebase.messaging.reporting.MessagingClientEventExtension;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Encodable
/* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
public abstract class zze {
    private static final zzx zza;

    static {
        zzw zzw = new zzw();
        zzd.zza.configure(zzw);
        zza = zzw.zza();
    }

    private zze() {
    }

    public static void zzb(Object obj, OutputStream outputStream) throws IOException {
        zza.zza(obj, outputStream);
    }

    public static byte[] zzc(Object obj) {
        zzx zzx = zza;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            zzx.zza(obj, byteArrayOutputStream);
        } catch (IOException e) {
        }
        return byteArrayOutputStream.toByteArray();
    }

    public abstract MessagingClientEventExtension zza();
}
