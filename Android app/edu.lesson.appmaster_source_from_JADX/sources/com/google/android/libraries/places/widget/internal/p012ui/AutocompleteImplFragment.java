package com.google.android.libraries.places.widget.internal.p012ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.libraries.places.C2432R;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.internal.zza;
import com.google.android.libraries.places.internal.zzdh;
import com.google.android.libraries.places.internal.zzdx;
import com.google.android.libraries.places.internal.zzdz;
import com.google.android.libraries.places.internal.zzeb;
import com.google.android.libraries.places.internal.zzel;
import com.google.android.libraries.places.internal.zzep;
import com.google.android.libraries.places.internal.zzer;
import com.google.android.libraries.places.internal.zzeu;
import com.google.android.libraries.places.internal.zzev;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.List;

/* renamed from: com.google.android.libraries.places.widget.internal.ui.AutocompleteImplFragment */
/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class AutocompleteImplFragment extends Fragment {
    private final PlacesClient zza;
    private final zzdx zzb;
    private final zzev zzc;
    private final zza zzd;
    /* access modifiers changed from: private */
    public zzer zze;
    private PlaceSelectionListener zzf;
    /* access modifiers changed from: private */
    public EditText zzg;
    private RecyclerView zzh;
    private View zzi;
    private View zzj;
    private View zzk;
    private View zzl;
    private View zzm;
    private View zzn;
    private View zzo;
    private View zzp;
    private TextView zzq;
    private TextView zzr;
    private zzr zzs;
    private final zzj zzt;

    private AutocompleteImplFragment(int layoutId, PlacesClient client, zzdx options, zzev logger, zza clock) {
        super(layoutId);
        this.zzt = new zzj(this, (zzi) null);
        this.zza = client;
        this.zzb = options;
        this.zzc = logger;
        this.zzd = clock;
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            zzeu zzeu = new zzeu(this.zzb.zzf(), this.zzb.zzh(), this.zzb.zzl(), this.zzd);
            zzer zzer = (zzer) new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) new zzep(new zzel(this.zza, this.zzb, zzeu.zzh()), zzeu, this.zzc)).get(zzer.class);
            this.zze = zzer;
            zzer.zze(bundle);
            requireActivity().getOnBackPressedDispatcher().addCallback(this, new zzf(this, true));
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public final void onPause() {
        super.onPause();
        this.zze.zzi();
    }

    public final void onResume() {
        super.onResume();
        this.zze.zzh();
    }

    public final void onViewCreated(View view, Bundle bundle) {
        String str;
        int identifier;
        try {
            this.zzg = (EditText) view.findViewById(C2432R.C2435id.places_autocomplete_search_bar);
            this.zzh = (RecyclerView) view.findViewById(C2432R.C2435id.places_autocomplete_list);
            this.zzi = view.findViewById(C2432R.C2435id.places_autocomplete_back_button);
            this.zzj = view.findViewById(C2432R.C2435id.places_autocomplete_clear_button);
            this.zzk = view.findViewById(C2432R.C2435id.places_autocomplete_search_bar_separator);
            this.zzl = view.findViewById(C2432R.C2435id.places_autocomplete_progress);
            this.zzm = view.findViewById(C2432R.C2435id.places_autocomplete_try_again_progress);
            this.zzn = view.findViewById(C2432R.C2435id.places_autocomplete_powered_by_google);
            this.zzo = view.findViewById(C2432R.C2435id.places_autocomplete_powered_by_google_separator);
            this.zzp = view.findViewById(C2432R.C2435id.places_autocomplete_sad_cloud);
            this.zzq = (TextView) view.findViewById(C2432R.C2435id.places_autocomplete_error_message);
            this.zzr = (TextView) view.findViewById(C2432R.C2435id.places_autocomplete_try_again);
            this.zzg.addTextChangedListener(this.zzt);
            this.zzg.setOnFocusChangeListener(new zzl((zzk) null));
            EditText editText = this.zzg;
            if (TextUtils.isEmpty(this.zzb.zzk())) {
                str = getString(C2432R.string.places_autocomplete_search_hint);
            } else {
                str = this.zzb.zzk();
            }
            editText.setHint(str);
            AutocompleteActivityMode autocompleteActivityMode = AutocompleteActivityMode.FULLSCREEN;
            switch (this.zzb.zzh().ordinal()) {
                case 0:
                    int zza2 = this.zzb.zza();
                    int zzb2 = this.zzb.zzb();
                    if (Color.alpha(zza2) < 255) {
                        zza2 = 0;
                    }
                    if (!(zza2 == 0 || zzb2 == 0)) {
                        int zza3 = zzeb.zza(zza2, ContextCompat.getColor(requireContext(), C2432R.C2433color.places_text_white_alpha_87), ContextCompat.getColor(requireContext(), C2432R.C2433color.places_text_black_alpha_87));
                        int zza4 = zzeb.zza(zza2, ContextCompat.getColor(requireContext(), C2432R.C2433color.places_text_white_alpha_26), ContextCompat.getColor(requireContext(), C2432R.C2433color.places_text_black_alpha_26));
                        view.findViewById(C2432R.C2435id.places_autocomplete_search_bar_container).setBackgroundColor(zza2);
                        if (Build.VERSION.SDK_INT >= 21) {
                            Window window = requireActivity().getWindow();
                            if (!zzeb.zzc(zzb2, -1, ViewCompat.MEASURED_STATE_MASK)) {
                                window.setStatusBarColor(zzb2);
                            } else if (Build.VERSION.SDK_INT >= 23) {
                                window.setStatusBarColor(zzb2);
                                window.getDecorView().setSystemUiVisibility(8192);
                            }
                        }
                        this.zzg.setTextColor(zza3);
                        this.zzg.setHintTextColor(zza4);
                        zzeb.zzb((ImageView) this.zzi, zza3);
                        zzeb.zzb((ImageView) this.zzj, zza3);
                        break;
                    }
                case 1:
                    if (Build.VERSION.SDK_INT >= 19 && (identifier = getResources().getIdentifier("status_bar_height", "dimen", "android")) > 0) {
                        requireActivity().getWindow().addFlags(67108864);
                        ViewCompat.setPaddingRelative(view, view.getPaddingLeft(), view.getPaddingTop() + getResources().getDimensionPixelSize(identifier), view.getPaddingRight(), view.getPaddingBottom());
                        break;
                    }
            }
            this.zzi.setOnClickListener(new zza(this));
            this.zzj.setOnClickListener(new zzb(this));
            this.zzr.setOnClickListener(new zzc(this));
            this.zzs = new zzr(new zze(this), (byte[]) null);
            this.zzh.setLayoutManager(new LinearLayoutManager(requireContext()));
            this.zzh.setItemAnimator(new zzo(getResources()));
            this.zzh.setAdapter(this.zzs);
            this.zzh.addOnScrollListener(new zzg(this));
            this.zze.zza().observe(getViewLifecycleOwner(), new zzd(this));
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(View view) {
        this.zze.zzj();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzg(zzdz zzdz) {
        try {
            this.zzj.setVisibility(0);
            this.zzk.setVisibility(0);
            this.zzl.setVisibility(8);
            this.zzm.setVisibility(8);
            this.zzn.setVisibility(0);
            this.zzo.setVisibility(8);
            this.zzp.setVisibility(8);
            this.zzq.setVisibility(8);
            this.zzr.setVisibility(8);
            AutocompleteActivityMode autocompleteActivityMode = AutocompleteActivityMode.FULLSCREEN;
            switch (zzdz.zzf() - 1) {
                case 0:
                    if (TextUtils.isEmpty(this.zzb.zzl())) {
                        this.zzj.setVisibility(8);
                    }
                    this.zzg.requestFocus();
                    this.zzg.setText(this.zzb.zzl());
                    EditText editText = this.zzg;
                    editText.setSelection(editText.getText().length());
                    return;
                case 1:
                    this.zzs.submitList((List<AutocompletePrediction>) null);
                    this.zzj.setVisibility(8);
                    this.zzg.getText().clear();
                    return;
                case 2:
                    this.zzl.setVisibility(0);
                    return;
                case 3:
                    this.zzr.setVisibility(8);
                    this.zzm.setVisibility(0);
                    this.zzn.setVisibility(8);
                    this.zzp.setVisibility(0);
                    this.zzq.setVisibility(0);
                    return;
                case 4:
                    this.zzs.submitList(zzdz.zzd());
                    this.zzo.setVisibility(0);
                    return;
                case 5:
                    this.zzs.submitList((List<AutocompletePrediction>) null);
                    this.zzn.setVisibility(8);
                    this.zzp.setVisibility(0);
                    this.zzr.setVisibility(4);
                    this.zzq.setText(getString(C2432R.string.places_autocomplete_no_results_for_query, zzdz.zze()));
                    this.zzq.setVisibility(0);
                    return;
                case 6:
                    break;
                case 8:
                    this.zzg.clearFocus();
                    this.zzg.removeTextChangedListener(this.zzt);
                    this.zzg.setText(zzdz.zzb().getPrimaryText((CharacterStyle) null));
                    this.zzg.addTextChangedListener(this.zzt);
                    break;
                case 9:
                    this.zzf.onError(zzdz.zza());
                    return;
                default:
                    this.zzf.onPlaceSelected(zzdz.zzc());
                    return;
            }
            this.zzs.submitList((List<AutocompletePrediction>) null);
            this.zzn.setVisibility(8);
            this.zzp.setVisibility(0);
            this.zzr.setVisibility(0);
            this.zzq.setText(getString(C2432R.string.places_search_error));
            this.zzq.setVisibility(0);
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public final void zzh(PlaceSelectionListener placeSelectionListener) {
        this.zzf = placeSelectionListener;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(View view) {
        try {
            this.zze.zzk();
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zze(AutocompletePrediction autocompletePrediction, int i) {
        try {
            this.zze.zzf(autocompletePrediction, i);
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(View view) {
        try {
            this.zze.zzl(this.zzg.getText().toString());
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }
}
