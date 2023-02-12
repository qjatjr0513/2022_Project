package com.google.android.libraries.places.internal;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzeb {
    public static int zza(int i, int i2, int i3) {
        return zzc(i, i2, i3) ? i3 : i2;
    }

    public static void zzb(ImageView imageView, int i) {
        Drawable drawable = imageView.getDrawable();
        int rgb = Color.rgb(Color.red(i), Color.green(i), Color.blue(i));
        Drawable mutate = drawable.mutate();
        mutate.setColorFilter(rgb, PorterDuff.Mode.SRC_ATOP);
        mutate.setAlpha(Color.alpha(i));
    }

    public static boolean zzc(int i, int i2, int i3) {
        double zzf = zzf(i);
        double zze = zze(zzf(i2), zzf);
        if (zze <= 3.0d && zze <= zze(zzf(i3), zzf)) {
            return true;
        }
        return false;
    }

    private static double zzd(double d) {
        return d <= 0.03928d ? d / 12.92d : Math.pow((d + 0.055d) / 1.055d, 2.4d);
    }

    private static double zze(double d, double d2) {
        return ((double) Math.round(((Math.max(d, d2) + 0.05d) / (Math.min(d, d2) + 0.05d)) * 100.0d)) / 100.0d;
    }

    private static double zzf(int i) {
        return (zzd(((double) Color.red(i)) / 255.0d) * 0.2126d) + (zzd(((double) Color.green(i)) / 255.0d) * 0.7152d) + (zzd(((double) Color.blue(i)) / 255.0d) * 0.0722d);
    }
}
