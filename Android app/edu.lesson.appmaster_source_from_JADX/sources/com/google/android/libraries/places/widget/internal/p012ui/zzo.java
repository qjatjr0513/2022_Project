package com.google.android.libraries.places.widget.internal.p012ui;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.libraries.places.C2432R;
import com.google.android.libraries.places.internal.zzdh;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.libraries.places.widget.internal.ui.zzo */
/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzo extends DefaultItemAnimator {
    private final List<RecyclerView.ViewHolder> zza = new ArrayList();
    private final List<RecyclerView.ViewHolder> zzb = new ArrayList();
    /* access modifiers changed from: private */
    public final List<RecyclerView.ViewHolder> zzc = new ArrayList();
    private final int zzd;

    public zzo(Resources resources) {
        this.zzd = resources.getDimensionPixelSize(C2432R.dimen.places_autocomplete_vertical_dropdown);
    }

    private final void zzd(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        this.zzc.add(viewHolder);
        long moveDuration = getMoveDuration();
        int layoutPosition = viewHolder.getLayoutPosition();
        view.setTranslationY((float) (-this.zzd));
        view.setAlpha(0.0f);
        ViewPropertyAnimator animate = view.animate();
        animate.cancel();
        animate.translationY(0.0f).alpha(1.0f).setDuration(133).setInterpolator(new FastOutSlowInInterpolator()).setStartDelay(moveDuration + ((long) (layoutPosition * 67)));
        animate.setListener(new zzn(this, view, viewHolder, animate)).start();
    }

    /* access modifiers changed from: private */
    public final void zze() {
        if (!isRunning()) {
            dispatchAnimationsFinished();
        }
    }

    /* access modifiers changed from: private */
    public static void zzf(View view) {
        view.setAlpha(1.0f);
        view.setTranslationY(0.0f);
    }

    public final boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        try {
            endAnimation(viewHolder);
            viewHolder.itemView.setAlpha(0.0f);
            if (((zzs) viewHolder).zzb()) {
                this.zza.add(viewHolder);
                return true;
            }
            this.zzb.add(viewHolder);
            return true;
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public final void endAnimation(RecyclerView.ViewHolder viewHolder) {
        try {
            super.endAnimation(viewHolder);
            if (this.zza.remove(viewHolder)) {
                zzf(viewHolder.itemView);
                dispatchAddFinished(viewHolder);
            }
            zze();
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public final void endAnimations() {
        try {
            for (int size = this.zza.size() - 1; size >= 0; size--) {
                RecyclerView.ViewHolder viewHolder = this.zza.get(size);
                zzf(viewHolder.itemView);
                dispatchAddFinished(viewHolder);
                this.zza.remove(size);
            }
            List<RecyclerView.ViewHolder> list = this.zzc;
            for (int size2 = list.size() - 1; size2 >= 0; size2--) {
                list.get(size2).itemView.animate().cancel();
            }
            super.endAnimations();
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public final boolean isRunning() {
        try {
            if (super.isRunning() || !this.zzb.isEmpty() || !this.zza.isEmpty() || !this.zzc.isEmpty()) {
                return true;
            }
            return false;
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public final void runPendingAnimations() {
        try {
            for (RecyclerView.ViewHolder animateAdd : this.zzb) {
                super.animateAdd(animateAdd);
            }
            this.zzb.clear();
            super.runPendingAnimations();
            if (!this.zza.isEmpty()) {
                ArrayList<RecyclerView.ViewHolder> arrayList = new ArrayList<>(this.zza);
                this.zza.clear();
                for (RecyclerView.ViewHolder viewHolder : arrayList) {
                    View view = viewHolder.itemView;
                    this.zzc.add(viewHolder);
                    long moveDuration = getMoveDuration() + ((long) (viewHolder.getLayoutPosition() * 67));
                    view.setTranslationY((float) (-this.zzd));
                    view.setAlpha(0.0f);
                    ViewPropertyAnimator animate = view.animate();
                    animate.cancel();
                    animate.translationY(0.0f).alpha(1.0f).setDuration(133).setInterpolator(new FastOutSlowInInterpolator()).setStartDelay(moveDuration);
                    animate.setListener(new zzn(this, view, viewHolder, animate)).start();
                }
            }
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }
}
