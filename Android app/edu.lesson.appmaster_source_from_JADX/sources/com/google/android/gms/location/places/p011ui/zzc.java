package com.google.android.gms.location.places.p011ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

/* renamed from: com.google.android.gms.location.places.ui.zzc */
public class zzc {
    protected final Intent intent;

    public zzc(String str) {
        Intent intent2 = new Intent(str);
        this.intent = intent2;
        intent2.setPackage("com.google.android.gms");
    }

    /* access modifiers changed from: protected */
    public Intent build(Activity activity) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        Resources.Theme theme = activity.getTheme();
        TypedValue typedValue = new TypedValue();
        TypedValue typedValue2 = new TypedValue();
        if (theme.resolveAttribute(16843827, typedValue, true) && !this.intent.hasExtra("primary_color")) {
            this.intent.putExtra("primary_color", typedValue.data);
        }
        if (theme.resolveAttribute(16843828, typedValue2, true) && !this.intent.hasExtra("primary_color_dark")) {
            this.intent.putExtra("primary_color_dark", typedValue2.data);
        }
        GoogleApiAvailability.getInstance().verifyGooglePlayServicesIsAvailable(activity, 12451000);
        return this.intent;
    }
}
