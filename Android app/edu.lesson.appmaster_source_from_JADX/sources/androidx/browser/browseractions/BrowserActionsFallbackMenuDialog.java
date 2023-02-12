package androidx.browser.browseractions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

class BrowserActionsFallbackMenuDialog extends Dialog {
    private static final long ENTER_ANIMATION_DURATION_MS = 250;
    private static final long EXIT_ANIMATION_DURATION_MS = 150;
    private final View mContentView;

    BrowserActionsFallbackMenuDialog(Context context, View contentView) {
        super(context);
        this.mContentView = contentView;
    }

    public void show() {
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        startAnimation(true);
        super.show();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != 0) {
            return false;
        }
        dismiss();
        return true;
    }

    public void dismiss() {
        startAnimation(false);
    }

    private void startAnimation(final boolean isEnterAnimation) {
        float to = 0.0f;
        float from = isEnterAnimation ? 0.0f : 1.0f;
        if (isEnterAnimation) {
            to = 1.0f;
        }
        long duration = isEnterAnimation ? ENTER_ANIMATION_DURATION_MS : 150;
        this.mContentView.setScaleX(from);
        this.mContentView.setScaleY(from);
        this.mContentView.animate().scaleX(to).scaleY(to).setDuration(duration).setInterpolator(new LinearOutSlowInInterpolator()).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                if (!isEnterAnimation) {
                    BrowserActionsFallbackMenuDialog.super.dismiss();
                }
            }
        }).start();
    }
}
