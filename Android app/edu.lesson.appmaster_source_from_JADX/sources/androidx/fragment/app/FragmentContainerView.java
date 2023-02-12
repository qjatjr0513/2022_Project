package androidx.fragment.app;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.C2329R;
import java.util.ArrayList;

public final class FragmentContainerView extends FrameLayout {
    private View.OnApplyWindowInsetsListener mApplyWindowInsetsListener;
    private ArrayList<View> mDisappearingFragmentChildren;
    private boolean mDrawDisappearingViewsFirst;
    private ArrayList<View> mTransitioningFragmentViews;

    public FragmentContainerView(Context context) {
        super(context);
        this.mDrawDisappearingViewsFirst = true;
    }

    public FragmentContainerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FragmentContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mDrawDisappearingViewsFirst = true;
        if (attrs != null) {
            String name = attrs.getClassAttribute();
            String attribute = "class";
            TypedArray a = context.obtainStyledAttributes(attrs, C2329R.styleable.FragmentContainerView);
            if (name == null) {
                name = a.getString(C2329R.styleable.FragmentContainerView_android_name);
                attribute = "android:name";
            }
            a.recycle();
            if (name != null && !isInEditMode()) {
                throw new UnsupportedOperationException("FragmentContainerView must be within a FragmentActivity to use " + attribute + "=\"" + name + "\"");
            }
        }
    }

    FragmentContainerView(Context context, AttributeSet attrs, FragmentManager fm) {
        super(context, attrs);
        String tagMessage;
        this.mDrawDisappearingViewsFirst = true;
        String name = attrs.getClassAttribute();
        TypedArray a = context.obtainStyledAttributes(attrs, C2329R.styleable.FragmentContainerView);
        name = name == null ? a.getString(C2329R.styleable.FragmentContainerView_android_name) : name;
        String tag = a.getString(C2329R.styleable.FragmentContainerView_android_tag);
        a.recycle();
        int id = getId();
        Fragment existingFragment = fm.findFragmentById(id);
        if (name != null && existingFragment == null) {
            if (id <= 0) {
                if (tag != null) {
                    tagMessage = " with tag " + tag;
                } else {
                    tagMessage = "";
                }
                throw new IllegalStateException("FragmentContainerView must have an android:id to add Fragment " + name + tagMessage);
            }
            Fragment containerFragment = fm.getFragmentFactory().instantiate(context.getClassLoader(), name);
            containerFragment.onInflate(context, attrs, (Bundle) null);
            fm.beginTransaction().setReorderingAllowed(true).add((ViewGroup) this, containerFragment, tag).commitNowAllowingStateLoss();
        }
        fm.onContainerAvailable(this);
    }

    public void setLayoutTransition(LayoutTransition transition) {
        if (Build.VERSION.SDK_INT < 18) {
            super.setLayoutTransition(transition);
            return;
        }
        throw new UnsupportedOperationException("FragmentContainerView does not support Layout Transitions or animateLayoutChanges=\"true\".");
    }

    public void setOnApplyWindowInsetsListener(View.OnApplyWindowInsetsListener listener) {
        this.mApplyWindowInsetsListener = listener;
    }

    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        return insets;
    }

    public WindowInsets dispatchApplyWindowInsets(WindowInsets insets) {
        WindowInsetsCompat dispatchInsets;
        WindowInsetsCompat insetsCompat = WindowInsetsCompat.toWindowInsetsCompat(insets);
        View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = this.mApplyWindowInsetsListener;
        if (onApplyWindowInsetsListener != null) {
            dispatchInsets = WindowInsetsCompat.toWindowInsetsCompat(onApplyWindowInsetsListener.onApplyWindowInsets(this, insets));
        } else {
            dispatchInsets = ViewCompat.onApplyWindowInsets(this, insetsCompat);
        }
        if (!dispatchInsets.isConsumed()) {
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                ViewCompat.dispatchApplyWindowInsets(getChildAt(i), dispatchInsets);
            }
        }
        return insets;
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        if (this.mDrawDisappearingViewsFirst && this.mDisappearingFragmentChildren != null) {
            for (int i = 0; i < this.mDisappearingFragmentChildren.size(); i++) {
                super.drawChild(canvas, this.mDisappearingFragmentChildren.get(i), getDrawingTime());
            }
        }
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View child, long drawingTime) {
        ArrayList<View> arrayList;
        if (!this.mDrawDisappearingViewsFirst || (arrayList = this.mDisappearingFragmentChildren) == null || arrayList.size() <= 0 || !this.mDisappearingFragmentChildren.contains(child)) {
            return super.drawChild(canvas, child, drawingTime);
        }
        return false;
    }

    public void startViewTransition(View view) {
        if (view.getParent() == this) {
            if (this.mTransitioningFragmentViews == null) {
                this.mTransitioningFragmentViews = new ArrayList<>();
            }
            this.mTransitioningFragmentViews.add(view);
        }
        super.startViewTransition(view);
    }

    public void endViewTransition(View view) {
        ArrayList<View> arrayList = this.mTransitioningFragmentViews;
        if (arrayList != null) {
            arrayList.remove(view);
            ArrayList<View> arrayList2 = this.mDisappearingFragmentChildren;
            if (arrayList2 != null && arrayList2.remove(view)) {
                this.mDrawDisappearingViewsFirst = true;
            }
        }
        super.endViewTransition(view);
    }

    /* access modifiers changed from: package-private */
    public void setDrawDisappearingViewsLast(boolean drawDisappearingViewsFirst) {
        this.mDrawDisappearingViewsFirst = drawDisappearingViewsFirst;
    }

    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (FragmentManager.getViewFragment(child) != null) {
            super.addView(child, index, params);
            return;
        }
        throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + child + " is not associated with a Fragment.");
    }

    /* access modifiers changed from: protected */
    public boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params, boolean preventRequestLayout) {
        if (FragmentManager.getViewFragment(child) != null) {
            return super.addViewInLayout(child, index, params, preventRequestLayout);
        }
        throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + child + " is not associated with a Fragment.");
    }

    public void removeViewAt(int index) {
        addDisappearingFragmentView(getChildAt(index));
        super.removeViewAt(index);
    }

    public void removeViewInLayout(View view) {
        addDisappearingFragmentView(view);
        super.removeViewInLayout(view);
    }

    public void removeView(View view) {
        addDisappearingFragmentView(view);
        super.removeView(view);
    }

    public void removeViews(int start, int count) {
        for (int i = start; i < start + count; i++) {
            addDisappearingFragmentView(getChildAt(i));
        }
        super.removeViews(start, count);
    }

    public void removeViewsInLayout(int start, int count) {
        for (int i = start; i < start + count; i++) {
            addDisappearingFragmentView(getChildAt(i));
        }
        super.removeViewsInLayout(start, count);
    }

    public void removeAllViewsInLayout() {
        for (int i = getChildCount() - 1; i >= 0; i--) {
            addDisappearingFragmentView(getChildAt(i));
        }
        super.removeAllViewsInLayout();
    }

    /* access modifiers changed from: protected */
    public void removeDetachedView(View child, boolean animate) {
        if (animate) {
            addDisappearingFragmentView(child);
        }
        super.removeDetachedView(child, animate);
    }

    private void addDisappearingFragmentView(View v) {
        ArrayList<View> arrayList = this.mTransitioningFragmentViews;
        if (arrayList != null && arrayList.contains(v)) {
            if (this.mDisappearingFragmentChildren == null) {
                this.mDisappearingFragmentChildren = new ArrayList<>();
            }
            this.mDisappearingFragmentChildren.add(v);
        }
    }
}
