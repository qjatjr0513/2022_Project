package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
public class zzkh extends IOException {
    public zzkh(String str) {
        super(str);
    }

    static zzkg zza() {
        return new zzkg("Protocol message tag had invalid wire type.");
    }

    static zzkh zzb() {
        return new zzkh("Protocol message contained an invalid tag (zero).");
    }

    static zzkh zzc() {
        return new zzkh("Protocol message had invalid UTF-8.");
    }

    static zzkh zzd() {
        return new zzkh("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzkh zze() {
        return new zzkh("Failed to parse the message.");
    }

    static zzkh zzf() {
        return new zzkh("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }
}
