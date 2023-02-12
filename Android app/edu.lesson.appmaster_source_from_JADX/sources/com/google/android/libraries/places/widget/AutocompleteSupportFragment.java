package com.google.android.libraries.places.widget;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import com.google.android.libraries.places.C2432R;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.internal.zzdh;
import com.google.android.libraries.places.internal.zzdv;
import com.google.android.libraries.places.internal.zzdw;
import com.google.android.libraries.places.internal.zzdx;
import com.google.android.libraries.places.internal.zzge;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public class AutocompleteSupportFragment extends Fragment {
    private final MutableLiveData<CharSequence> zza = new MutableLiveData<>();
    private final MutableLiveData<CharSequence> zzb = new MutableLiveData<>();
    private zzdw zzc = zzdx.zzm(AutocompleteActivityMode.OVERLAY, zzge.zzm(), zzdv.FRAGMENT);
    private PlaceSelectionListener zzd;

    public AutocompleteSupportFragment() {
        super(C2432R.layout.places_autocomplete_fragment);
    }

    public static AutocompleteSupportFragment newInstance() {
        return new AutocompleteSupportFragment();
    }

    private final void zze() {
        Intent build = new Autocomplete.IntentBuilder(this.zzc.zzl()).build(requireContext());
        if (requireView().isEnabled()) {
            requireView().setEnabled(false);
            startActivityForResult(build, 30421);
        }
    }

    private final void zzf(View view) {
        int i;
        if (true != TextUtils.isEmpty(this.zza.getValue())) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 30421) {
            try {
                PlaceSelectionListener placeSelectionListener = this.zzd;
                if (placeSelectionListener == null) {
                    if (Log.isLoggable("Places", 5)) {
                        Log.w("Places", "No PlaceSelectionListener is set. No result will be delivered.");
                    }
                } else if (data == null) {
                    if (Log.isLoggable("Places", 6)) {
                        Log.e("Places", "Intent data was null.");
                    }
                } else if (resultCode == -1) {
                    Place placeFromIntent = Autocomplete.getPlaceFromIntent(data);
                    placeSelectionListener.onPlaceSelected(placeFromIntent);
                    setText(placeFromIntent.getName());
                } else {
                    placeSelectionListener.onError(Autocomplete.getStatusFromIntent(data));
                }
            } catch (Error | RuntimeException e) {
                zzdh.zzb(e);
                throw e;
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            try {
                zzdx zzdx = (zzdx) savedInstanceState.getParcelable("options");
                if (zzdx != null) {
                    if (this.zza.getValue() == null) {
                        this.zza.postValue(zzdx.zzl());
                    }
                    if (this.zzb.getValue() == null) {
                        this.zzb.postValue(zzdx.zzk());
                    }
                    this.zzc = zzdx.zzg();
                }
            } catch (Error | RuntimeException e) {
                zzdh.zzb(e);
                throw e;
            }
        }
    }

    public void onResume() {
        super.onResume();
        requireView().setEnabled(true);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable("options", this.zzc.zzl());
    }

    public void onViewCreated(View view, Bundle bundle) {
        View findViewById = view.findViewById(C2432R.C2435id.places_autocomplete_search_button);
        View findViewById2 = view.findViewById(C2432R.C2435id.places_autocomplete_clear_button);
        EditText editText = (EditText) view.findViewById(C2432R.C2435id.places_autocomplete_search_input);
        findViewById.setOnClickListener(new zze(this));
        editText.setOnClickListener(new zzf(this));
        findViewById2.setOnClickListener(new zzg(this));
        zzf(findViewById2);
        this.zza.observe(getViewLifecycleOwner(), new zzi(this, editText, findViewById2));
        this.zzb.observe(getViewLifecycleOwner(), new zzh(editText, findViewById));
    }

    public AutocompleteSupportFragment setActivityMode(AutocompleteActivityMode mode) {
        this.zzc.zzf(mode);
        return this;
    }

    public AutocompleteSupportFragment setCountries(List<String> countries) {
        this.zzc.zza(countries);
        return this;
    }

    public AutocompleteSupportFragment setCountry(String country) {
        this.zzc.zzm(country);
        return this;
    }

    public AutocompleteSupportFragment setHint(CharSequence hint) {
        if (hint == null) {
            try {
                String string = getString(C2432R.string.places_autocomplete_search_hint);
                this.zzc.zzb(string);
                this.zzb.postValue(string);
            } catch (Error | RuntimeException e) {
                zzdh.zzb(e);
                throw e;
            }
        } else {
            this.zzc.zzb(hint.toString());
            this.zzb.postValue(hint);
        }
        return this;
    }

    public AutocompleteSupportFragment setLocationBias(LocationBias locationBias) {
        this.zzc.zzd(locationBias);
        return this;
    }

    public AutocompleteSupportFragment setLocationRestriction(LocationRestriction locationRestriction) {
        this.zzc.zze(locationRestriction);
        return this;
    }

    public AutocompleteSupportFragment setOnPlaceSelectedListener(PlaceSelectionListener placeSelectionListener) {
        this.zzd = placeSelectionListener;
        return this;
    }

    public AutocompleteSupportFragment setPlaceFields(List<Place.Field> placeFields) {
        this.zzc.zzh(placeFields);
        return this;
    }

    public AutocompleteSupportFragment setText(CharSequence text) {
        try {
            this.zzc.zzc(TextUtils.isEmpty(text) ? null : text.toString());
            this.zza.postValue(text);
            return this;
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public AutocompleteSupportFragment setTypeFilter(TypeFilter typeFilter) {
        this.zzc.zzk(typeFilter);
        return this;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(View view) {
        zze();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(View view) {
        zze();
    }

    public AutocompleteSupportFragment setCountries(String... countries) {
        this.zzc.zza(zzge.zzl(countries));
        return this;
    }

    static /* synthetic */ void zzd(EditText editText, View view, CharSequence charSequence) {
        try {
            editText.setHint(charSequence);
            view.setContentDescription(charSequence);
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(EditText editText, View view, CharSequence charSequence) {
        try {
            editText.setText(charSequence);
            zzf(view);
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }
}
