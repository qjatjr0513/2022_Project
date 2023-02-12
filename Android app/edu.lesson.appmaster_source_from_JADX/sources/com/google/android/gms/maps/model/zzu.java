package com.google.android.gms.maps.model;

import com.google.android.gms.internal.maps.zzai;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
final class zzu extends zzai {
    final /* synthetic */ TileProvider zza;

    zzu(TileOverlayOptions tileOverlayOptions, TileProvider tileProvider) {
        this.zza = tileProvider;
    }

    public final Tile zzb(int i, int i2, int i3) {
        return this.zza.getTile(i, i2, i3);
    }
}
