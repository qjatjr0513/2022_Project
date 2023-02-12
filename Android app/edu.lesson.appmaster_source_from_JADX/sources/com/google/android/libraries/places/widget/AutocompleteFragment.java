package com.google.android.libraries.places.widget;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.google.android.libraries.places.C2432R;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.internal.zzdh;
import com.google.android.libraries.places.internal.zzdv;
import com.google.android.libraries.places.internal.zzfm;
import com.google.android.libraries.places.internal.zzge;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.List;

@Deprecated
/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public class AutocompleteFragment extends Fragment {
    private View zza;
    private View zzb;
    private EditText zzc;
    private LocationBias zzd;
    private LocationRestriction zze;
    private String zzf;
    private TypeFilter zzg;
    private zzge<Place.Field> zzh;
    private PlaceSelectionListener zzi;

    private final void zzb() {
        int i;
        boolean isEmpty = this.zzc.getText().toString().isEmpty();
        View view = this.zzb;
        if (true != isEmpty) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            getView().setEnabled(true);
            if (requestCode == 30421) {
                if (this.zzi == null) {
                    if (Log.isLoggable("Places", 5)) {
                        Log.w("Places", "No PlaceSelectionListener is set. No result will be delivered.");
                        requestCode = 30421;
                    } else {
                        requestCode = 30421;
                    }
                } else if (resultCode == -1) {
                    Place placeFromIntent = Autocomplete.getPlaceFromIntent(data);
                    this.zzi.onPlaceSelected(placeFromIntent);
                    setText(placeFromIntent.getName());
                    requestCode = 30421;
                } else {
                    if (resultCode == 2) {
                        this.zzi.onError(Autocomplete.getStatusFromIntent(data));
                        resultCode = 2;
                    }
                    requestCode = 30421;
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        try {
            View inflate = inflater.inflate(C2432R.layout.places_autocomplete_fragment, container, false);
            this.zza = inflate.findViewById(C2432R.C2435id.places_autocomplete_search_button);
            this.zzb = inflate.findViewById(C2432R.C2435id.places_autocomplete_clear_button);
            this.zzc = (EditText) inflate.findViewById(C2432R.C2435id.places_autocomplete_search_input);
            zzc zzc2 = new zzc(this);
            this.zza.setOnClickListener(zzc2);
            this.zzc.setOnClickListener(zzc2);
            this.zzb.setOnClickListener(new zzd(this));
            zzb();
            inflate.setEnabled(false);
            return inflate;
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public void setCountry(String str) {
        this.zzf = str;
    }

    public void setHint(CharSequence hint) {
        if (hint == null) {
            try {
                hint = getString(C2432R.string.places_autocomplete_search_hint);
            } catch (Error | RuntimeException e) {
                zzdh.zzb(e);
                throw e;
            }
        }
        this.zzc.setHint(hint);
        this.zza.setContentDescription(hint);
    }

    public void setLocationBias(LocationBias locationBias) {
        this.zzd = locationBias;
    }

    public void setLocationRestriction(LocationRestriction locationRestriction) {
        this.zze = locationRestriction;
    }

    public void setOnPlaceSelectedListener(PlaceSelectionListener placeSelectionListener) {
        this.zzi = placeSelectionListener;
    }

    public void setTypeFilter(TypeFilter typeFilter) {
        this.zzg = typeFilter;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(View view) {
        if (!getView().isEnabled()) {
            zzfm.zzc(this.zzh, "Place Fields must be set.");
            if (Log.isLoggable("Places", 6)) {
                Log.e("Places", "Autocomplete activity cannot be launched until fragment is enabled.");
                return;
            }
            return;
        }
        Autocomplete.IntentBuilder intentBuilder = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, this.zzh);
        intentBuilder.setInitialQuery(this.zzc.getText().toString());
        intentBuilder.setHint(this.zzc.getHint().toString());
        intentBuilder.setCountry(this.zzf);
        intentBuilder.setLocationBias(this.zzd);
        intentBuilder.setLocationRestriction(this.zze);
        intentBuilder.setTypeFilter(this.zzg);
        intentBuilder.zza(zzdv.FRAGMENT);
        Intent build = intentBuilder.build(getActivity());
        getView().setEnabled(false);
        startActivityForResult(build, 30421);
    }

    public void onDestroyView() {
        try {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
            super.onDestroyView();
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public void setText(CharSequence text) {
        try {
            this.zzc.setText(text);
            zzb();
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public void setPlaceFields(List<Place.Field> placeFields) {
        try {
            zzfm.zzc(placeFields, "Place Fields must not be null.");
            this.zzh = zzge.zzk(placeFields);
            getView().setEnabled(true);
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }
}
