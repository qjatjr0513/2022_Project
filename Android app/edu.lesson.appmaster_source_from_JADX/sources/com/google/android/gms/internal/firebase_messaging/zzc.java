package com.google.android.gms.internal.firebase_messaging;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
final class zzc implements ObjectEncoder<zze> {
    static final zzc zza = new zzc();
    private static final FieldDescriptor zzb = FieldDescriptor.m195of("messagingClientEventExtension");

    private zzc() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        ((ObjectEncoderContext) obj2).add(zzb, (Object) ((zze) obj).zza());
    }
}
