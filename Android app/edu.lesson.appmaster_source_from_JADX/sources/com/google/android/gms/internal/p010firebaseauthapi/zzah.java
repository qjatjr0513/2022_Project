package com.google.android.gms.internal.p010firebaseauthapi;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzah */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzah implements zzay {
    private final OutputStream zza;

    private zzah(OutputStream outputStream) {
        this.zza = outputStream;
    }

    public static zzay zza(OutputStream outputStream) {
        return new zzah(outputStream);
    }

    public final void zzb(zzho zzho) throws IOException {
        throw null;
    }

    public final void zzc(zzir zzir) throws IOException {
        try {
            zzir.zzq(this.zza);
        } finally {
            this.zza.close();
        }
    }
}
