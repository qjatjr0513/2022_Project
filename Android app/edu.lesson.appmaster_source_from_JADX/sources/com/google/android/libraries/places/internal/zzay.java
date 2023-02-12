package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzay extends zzbr<Object, FetchPhotoRequest> {
    zzay(FetchPhotoRequest fetchPhotoRequest, String str, boolean z, zzdl zzdl) {
        super(fetchPhotoRequest, (Locale) null, str, false, zzdl);
    }

    /* access modifiers changed from: protected */
    public final String zze() {
        return "photo";
    }

    public final Map<String, String> zzf() {
        FetchPhotoRequest fetchPhotoRequest = (FetchPhotoRequest) zzb();
        PhotoMetadata photoMetadata = fetchPhotoRequest.getPhotoMetadata();
        HashMap hashMap = new HashMap();
        zzg(hashMap, "maxheight", fetchPhotoRequest.getMaxHeight(), (Object) null);
        zzg(hashMap, "maxwidth", fetchPhotoRequest.getMaxWidth(), (Object) null);
        hashMap.put("photoreference", photoMetadata.zza());
        return hashMap;
    }
}
