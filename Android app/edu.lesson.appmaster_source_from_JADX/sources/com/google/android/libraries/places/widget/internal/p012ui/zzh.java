package com.google.android.libraries.places.widget.internal.p012ui;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.internal.zza;
import com.google.android.libraries.places.internal.zzde;
import com.google.android.libraries.places.internal.zzdf;
import com.google.android.libraries.places.internal.zzdj;
import com.google.android.libraries.places.internal.zzdx;
import com.google.android.libraries.places.internal.zze;
import com.google.android.libraries.places.internal.zzev;
import com.google.android.libraries.places.internal.zzew;

/* renamed from: com.google.android.libraries.places.widget.internal.ui.zzh */
/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzh extends FragmentFactory {
    private final int zza;
    private final PlacesClient zzb;
    private final zzdx zzc;
    private final zzev zzd;
    private final zza zze = new zze();

    public zzh(int i, Context context, zzdx zzdx) {
        this.zza = i;
        Context applicationContext = context.getApplicationContext();
        zzde zzd2 = zzdf.zzd(applicationContext);
        zzd2.zzd(2);
        zzdf zze2 = zzd2.zze();
        zzdj zzdj = new zzdj(applicationContext);
        this.zzb = Places.zza(applicationContext, zze2);
        this.zzc = zzdx;
        this.zzd = new zzew(zzdj, zze2, (byte[]) null);
    }

    public final Fragment instantiate(ClassLoader classLoader, String str) {
        if (loadFragmentClass(classLoader, str) == AutocompleteImplFragment.class) {
            return new AutocompleteImplFragment(this.zza, this.zzb, this.zzc, this.zzd, this.zze);
        }
        return super.instantiate(classLoader, str);
    }
}
