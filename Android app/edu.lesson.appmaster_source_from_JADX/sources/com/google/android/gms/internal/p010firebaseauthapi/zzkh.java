package com.google.android.gms.internal.p010firebaseauthapi;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import javax.crypto.AEADBadTagException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkh */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
abstract class zzkh implements zzag {
    private final zzkf zza;
    private final zzkf zzb;

    public zzkh(byte[] bArr) throws GeneralSecurityException {
        this.zza = zzc(bArr, 1);
        this.zzb = zzc(bArr, 0);
    }

    private final byte[] zzd(ByteBuffer byteBuffer, byte[] bArr) throws GeneralSecurityException {
        int i;
        int i2;
        if (byteBuffer.remaining() >= this.zza.zzb() + 16) {
            int position = byteBuffer.position();
            byte[] bArr2 = new byte[16];
            byteBuffer.position(byteBuffer.limit() - 16);
            byteBuffer.get(bArr2);
            byteBuffer.position(position);
            byteBuffer.limit(byteBuffer.limit() - 16);
            byte[] bArr3 = new byte[this.zza.zzb()];
            byteBuffer.get(bArr3);
            try {
                byte[] bArr4 = new byte[32];
                this.zzb.zzd(bArr3, 0).get(bArr4);
                int length = bArr.length;
                int i3 = length & 15;
                if (i3 == 0) {
                    i = length;
                } else {
                    i = (length + 16) - i3;
                }
                int remaining = byteBuffer.remaining();
                int i4 = remaining % 16;
                if (i4 == 0) {
                    i2 = remaining;
                } else {
                    i2 = (remaining + 16) - i4;
                }
                int i5 = i2 + i;
                ByteBuffer order = ByteBuffer.allocate(i5 + 16).order(ByteOrder.LITTLE_ENDIAN);
                order.put(bArr);
                order.position(i);
                order.put(byteBuffer);
                order.position(i5);
                order.putLong((long) length);
                order.putLong((long) remaining);
                if (zzkd.zzb(zzla.zza(bArr4, order.array()), bArr2)) {
                    byteBuffer.position(position);
                    return this.zza.zzh(byteBuffer);
                }
                throw new GeneralSecurityException("invalid MAC");
            } catch (GeneralSecurityException e) {
                throw new AEADBadTagException(e.toString());
            }
        } else {
            throw new GeneralSecurityException("ciphertext too short");
        }
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return zzd(ByteBuffer.wrap(bArr), bArr2);
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        throw null;
    }

    /* access modifiers changed from: package-private */
    public abstract zzkf zzc(byte[] bArr, int i) throws InvalidKeyException;
}
