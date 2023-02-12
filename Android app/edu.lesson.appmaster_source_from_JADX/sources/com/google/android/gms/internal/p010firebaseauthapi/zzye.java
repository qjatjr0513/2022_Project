package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.internal.p010firebaseauthapi.zzyd;
import com.google.android.gms.internal.p010firebaseauthapi.zzye;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzye */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public abstract class zzye<MessageType extends zzye<MessageType, BuilderType>, BuilderType extends zzyd<MessageType, BuilderType>> implements zzaaz {
    protected int zza = 0;

    /* access modifiers changed from: package-private */
    public int zzn() {
        throw null;
    }

    public final zzyu zzo() {
        try {
            int zzs = zzs();
            zzyu zzyu = zzyu.zzb;
            byte[] bArr = new byte[zzs];
            zzze zzG = zzze.zzG(bArr);
            zzG(zzG);
            zzG.zzI();
            return new zzys(bArr);
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 72);
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ByteString threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzp(int i) {
        throw null;
    }

    public final void zzq(OutputStream outputStream) throws IOException {
        zzze zzH = zzze.zzH(outputStream, zzze.zzB(zzs()));
        zzG(zzH);
        zzH.zzN();
    }

    public final byte[] zzr() {
        try {
            byte[] bArr = new byte[zzs()];
            zzze zzG = zzze.zzG(bArr);
            zzG(zzG);
            zzG.zzI();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 72);
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a byte array threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }
}
