package com.google.android.gms.common;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public class GooglePlayServicesManifestException extends IllegalStateException {
    private final int zza;

    public GooglePlayServicesManifestException(int actualVersion, String message) {
        super(message);
        this.zza = actualVersion;
    }

    public int getActualVersion() {
        return this.zza;
    }

    public int getExpectedVersion() {
        return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }
}
