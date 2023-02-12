package com.google.android.libraries.places.widget;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.C2432R;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
import com.google.android.libraries.places.internal.zzdh;
import com.google.android.libraries.places.internal.zzdx;
import com.google.android.libraries.places.internal.zzfm;
import com.google.android.libraries.places.widget.internal.p012ui.AutocompleteImplFragment;
import com.google.android.libraries.places.widget.internal.p012ui.zzh;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public class AutocompleteActivity extends AppCompatActivity implements PlaceSelectionListener {
    public static final int RESULT_ERROR = 2;
    static boolean zza = true;
    private int zzb;
    private int zzc;
    private boolean zzd = false;

    public AutocompleteActivity() {
        super(C2432R.layout.places_autocomplete_activity);
    }

    private final void zzc(int i, Place place, Status status) {
        try {
            Intent intent = new Intent();
            if (place != null) {
                intent.putExtra("places/selected_place", place);
            }
            intent.putExtra("places/status", status);
            setResult(i, intent);
            finish();
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        try {
            zzfm.zzi(Places.isInitialized(), "Places must be initialized.");
            boolean z = true;
            if (zza) {
                zzfm.zzi(getCallingActivity() != null, "Cannot find caller. startActivityForResult should be used.");
            }
            zzdx zzdx = (zzdx) getIntent().getParcelableExtra("places/AutocompleteOptions");
            if (zzdx != null) {
                AutocompleteActivityMode autocompleteActivityMode = AutocompleteActivityMode.FULLSCREEN;
                switch (zzdx.zzh().ordinal()) {
                    case 0:
                        this.zzb = C2432R.layout.places_autocomplete_impl_fragment_fullscreen;
                        this.zzc = C2432R.style.PlacesAutocompleteFullscreen;
                        break;
                    case 1:
                        this.zzb = C2432R.layout.places_autocomplete_impl_fragment_overlay;
                        this.zzc = C2432R.style.PlacesAutocompleteOverlay;
                        break;
                }
                getSupportFragmentManager().setFragmentFactory(new zzh(this.zzb, this, zzdx));
                setTheme(this.zzc);
                super.onCreate(savedInstanceState);
                AutocompleteImplFragment autocompleteImplFragment = (AutocompleteImplFragment) getSupportFragmentManager().findFragmentById(C2432R.C2435id.places_autocomplete_content);
                if (autocompleteImplFragment == null) {
                    z = false;
                }
                zzfm.zzh(z);
                autocompleteImplFragment.zzh(this);
                View findViewById = findViewById(16908290);
                findViewById.setOnTouchListener(new zzb(this, autocompleteImplFragment, findViewById));
                findViewById.setOnClickListener(new zza(this));
                if (zzdx.zzj().isEmpty()) {
                    zzc(2, (Place) null, new Status((int) PlacesStatusCodes.INVALID_REQUEST, "Place Fields must not be empty."));
                    return;
                }
                return;
            }
            throw null;
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public void onError(Status status) {
        zzc(true != status.isCanceled() ? 2 : 0, (Place) null, status);
    }

    public void onPlaceSelected(Place place) {
        zzc(-1, place, Status.RESULT_SUCCESS);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(View view) {
        if (this.zzd) {
            zzc(0, (Place) null, new Status(16));
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ boolean zzb(AutocompleteImplFragment autocompleteImplFragment, View view, View view2, MotionEvent motionEvent) {
        this.zzd = false;
        View view3 = autocompleteImplFragment.getView();
        if (view3 == null || motionEvent.getY() <= ((float) view3.getBottom())) {
            return false;
        }
        this.zzd = true;
        view.performClick();
        return true;
    }
}
