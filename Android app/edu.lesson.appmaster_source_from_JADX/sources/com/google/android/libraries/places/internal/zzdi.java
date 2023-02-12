package com.google.android.libraries.places.internal;

import com.google.android.datatransport.Transformer;
import java.io.IOException;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzdi implements Transformer {
    public static final /* synthetic */ zzdi zza = new zzdi();

    private /* synthetic */ zzdi() {
    }

    public final Object apply(Object obj) {
        zzjr zzjr = (zzjr) obj;
        try {
            byte[] bArr = new byte[zzjr.zzv()];
            zzabf zzC = zzabf.zzC(bArr);
            zzjr.zzH(zzC);
            zzC.zzD();
            return bArr;
        } catch (IOException e) {
            String name = zzjr.getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 72);
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a byte array threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }
}
