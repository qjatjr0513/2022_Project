package com.google.android.gms.internal.p010firebaseauthapi;

import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaae */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public class zzaae extends IOException {
    private zzaaz zza = null;

    public zzaae(IOException iOException) {
        super(iOException.getMessage(), iOException);
    }

    static zzaad zza() {
        return new zzaad("Protocol message tag had invalid wire type.");
    }

    static zzaae zzb() {
        return new zzaae("Protocol message end-group tag did not match expected tag.");
    }

    static zzaae zzc() {
        return new zzaae("Protocol message contained an invalid tag (zero).");
    }

    static zzaae zzd() {
        return new zzaae("Protocol message had invalid UTF-8.");
    }

    static zzaae zze() {
        return new zzaae("CodedInputStream encountered a malformed varint.");
    }

    static zzaae zzf() {
        return new zzaae("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzaae zzg() {
        return new zzaae("Failed to parse the message.");
    }

    static zzaae zzi() {
        return new zzaae("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public final zzaae zzh(zzaaz zzaaz) {
        this.zza = zzaaz;
        return this;
    }

    public zzaae(String str) {
        super(str);
    }
}
