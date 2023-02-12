package com.google.android.material.chip;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.C2436R;
import com.google.android.material.internal.FlowLayout;
import java.util.ArrayList;
import java.util.List;

public class ChipGroup extends FlowLayout {
    private static final int DEF_STYLE_RES = C2436R.style.Widget_MaterialComponents_ChipGroup;
    /* access modifiers changed from: private */
    public int checkedId;
    /* access modifiers changed from: private */
    public final CheckedStateTracker checkedStateTracker;
    private int chipSpacingHorizontal;
    private int chipSpacingVertical;
    private OnCheckedChangeListener onCheckedChangeListener;
    private PassThroughHierarchyChangeListener passThroughListener;
    /* access modifiers changed from: private */
    public boolean protectFromCheckedChange;
    /* access modifiers changed from: private */
    public boolean selectionRequired;
    /* access modifiers changed from: private */
    public boolean singleSelection;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(ChipGroup chipGroup, int i);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
        }
    }

    public ChipGroup(Context context) {
        this(context, (AttributeSet) null);
    }

    public ChipGroup(Context context, AttributeSet attrs) {
        this(context, attrs, C2436R.attr.chipGroupStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ChipGroup(android.content.Context r9, android.util.AttributeSet r10, int r11) {
        /*
            r8 = this;
            int r4 = DEF_STYLE_RES
            android.content.Context r0 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r9, r10, r11, r4)
            r8.<init>(r0, r10, r11)
            com.google.android.material.chip.ChipGroup$CheckedStateTracker r0 = new com.google.android.material.chip.ChipGroup$CheckedStateTracker
            r1 = 0
            r0.<init>()
            r8.checkedStateTracker = r0
            com.google.android.material.chip.ChipGroup$PassThroughHierarchyChangeListener r0 = new com.google.android.material.chip.ChipGroup$PassThroughHierarchyChangeListener
            r0.<init>()
            r8.passThroughListener = r0
            r6 = -1
            r8.checkedId = r6
            r7 = 0
            r8.protectFromCheckedChange = r7
            android.content.Context r9 = r8.getContext()
            int[] r2 = com.google.android.material.C2436R.styleable.ChipGroup
            int[] r5 = new int[r7]
            r0 = r9
            r1 = r10
            r3 = r11
            android.content.res.TypedArray r0 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r0, r1, r2, r3, r4, r5)
            int r1 = com.google.android.material.C2436R.styleable.ChipGroup_chipSpacing
            int r1 = r0.getDimensionPixelOffset(r1, r7)
            int r2 = com.google.android.material.C2436R.styleable.ChipGroup_chipSpacingHorizontal
            int r2 = r0.getDimensionPixelOffset(r2, r1)
            r8.setChipSpacingHorizontal(r2)
            int r2 = com.google.android.material.C2436R.styleable.ChipGroup_chipSpacingVertical
            int r2 = r0.getDimensionPixelOffset(r2, r1)
            r8.setChipSpacingVertical(r2)
            int r2 = com.google.android.material.C2436R.styleable.ChipGroup_singleLine
            boolean r2 = r0.getBoolean(r2, r7)
            r8.setSingleLine((boolean) r2)
            int r2 = com.google.android.material.C2436R.styleable.ChipGroup_singleSelection
            boolean r2 = r0.getBoolean(r2, r7)
            r8.setSingleSelection((boolean) r2)
            int r2 = com.google.android.material.C2436R.styleable.ChipGroup_selectionRequired
            boolean r2 = r0.getBoolean(r2, r7)
            r8.setSelectionRequired(r2)
            int r2 = com.google.android.material.C2436R.styleable.ChipGroup_checkedChip
            int r2 = r0.getResourceId(r2, r6)
            if (r2 == r6) goto L_0x006a
            r8.checkedId = r2
        L_0x006a:
            r0.recycle()
            com.google.android.material.chip.ChipGroup$PassThroughHierarchyChangeListener r3 = r8.passThroughListener
            super.setOnHierarchyChangeListener(r3)
            r3 = 1
            androidx.core.view.ViewCompat.setImportantForAccessibility(r8, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.ChipGroup.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        AccessibilityNodeInfoCompat.wrap(info).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(getRowCount(), isSingleLine() ? getChipCount() : -1, false, isSingleSelection() ? 1 : 2));
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        return new LayoutParams(lp);
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return super.checkLayoutParams(p) && (p instanceof LayoutParams);
    }

    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener listener) {
        ViewGroup.OnHierarchyChangeListener unused = this.passThroughListener.onHierarchyChangeListener = listener;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        int i = this.checkedId;
        if (i != -1) {
            setCheckedStateForView(i, true);
            setCheckedId(this.checkedId);
        }
    }

    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof Chip) {
            Chip chip = (Chip) child;
            if (chip.isChecked()) {
                int i = this.checkedId;
                if (i != -1 && this.singleSelection) {
                    setCheckedStateForView(i, false);
                }
                setCheckedId(chip.getId());
            }
        }
        super.addView(child, index, params);
    }

    @Deprecated
    public void setDividerDrawableHorizontal(Drawable divider) {
        throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setDividerDrawableVertical(Drawable divider) {
        throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setShowDividerHorizontal(int dividerMode) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setShowDividerVertical(int dividerMode) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setFlexWrap(int flexWrap) {
        throw new UnsupportedOperationException("Changing flex wrap not allowed. ChipGroup exposes a singleLine attribute instead.");
    }

    public void check(int id) {
        int i = this.checkedId;
        if (id != i) {
            if (i != -1 && this.singleSelection) {
                setCheckedStateForView(i, false);
            }
            if (id != -1) {
                setCheckedStateForView(id, true);
            }
            setCheckedId(id);
        }
    }

    public int getCheckedChipId() {
        if (this.singleSelection) {
            return this.checkedId;
        }
        return -1;
    }

    public List<Integer> getCheckedChipIds() {
        ArrayList<Integer> checkedIds = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if ((child instanceof Chip) && ((Chip) child).isChecked()) {
                checkedIds.add(Integer.valueOf(child.getId()));
                if (this.singleSelection) {
                    return checkedIds;
                }
            }
        }
        return checkedIds;
    }

    public void clearCheck() {
        this.protectFromCheckedChange = true;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child instanceof Chip) {
                ((Chip) child).setChecked(false);
            }
        }
        this.protectFromCheckedChange = false;
        setCheckedId(-1);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.onCheckedChangeListener = listener;
    }

    /* access modifiers changed from: private */
    public void setCheckedId(int checkedId2) {
        setCheckedId(checkedId2, true);
    }

    /* access modifiers changed from: private */
    public void setCheckedId(int checkedId2, boolean fromUser) {
        this.checkedId = checkedId2;
        OnCheckedChangeListener onCheckedChangeListener2 = this.onCheckedChangeListener;
        if (onCheckedChangeListener2 != null && this.singleSelection && fromUser) {
            onCheckedChangeListener2.onCheckedChanged(this, checkedId2);
        }
    }

    /* access modifiers changed from: private */
    public void setCheckedStateForView(int viewId, boolean checked) {
        View checkedView = findViewById(viewId);
        if (checkedView instanceof Chip) {
            this.protectFromCheckedChange = true;
            ((Chip) checkedView).setChecked(checked);
            this.protectFromCheckedChange = false;
        }
    }

    private int getChipCount() {
        int count = 0;
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof Chip) {
                count++;
            }
        }
        return count;
    }

    /* access modifiers changed from: package-private */
    public int getIndexOfChip(View child) {
        if (!(child instanceof Chip)) {
            return -1;
        }
        int index = 0;
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof Chip) {
                if (((Chip) getChildAt(i)) == child) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    public void setChipSpacing(int chipSpacing) {
        setChipSpacingHorizontal(chipSpacing);
        setChipSpacingVertical(chipSpacing);
    }

    public void setChipSpacingResource(int id) {
        setChipSpacing(getResources().getDimensionPixelOffset(id));
    }

    public int getChipSpacingHorizontal() {
        return this.chipSpacingHorizontal;
    }

    public void setChipSpacingHorizontal(int chipSpacingHorizontal2) {
        if (this.chipSpacingHorizontal != chipSpacingHorizontal2) {
            this.chipSpacingHorizontal = chipSpacingHorizontal2;
            setItemSpacing(chipSpacingHorizontal2);
            requestLayout();
        }
    }

    public void setChipSpacingHorizontalResource(int id) {
        setChipSpacingHorizontal(getResources().getDimensionPixelOffset(id));
    }

    public int getChipSpacingVertical() {
        return this.chipSpacingVertical;
    }

    public void setChipSpacingVertical(int chipSpacingVertical2) {
        if (this.chipSpacingVertical != chipSpacingVertical2) {
            this.chipSpacingVertical = chipSpacingVertical2;
            setLineSpacing(chipSpacingVertical2);
            requestLayout();
        }
    }

    public void setChipSpacingVerticalResource(int id) {
        setChipSpacingVertical(getResources().getDimensionPixelOffset(id));
    }

    public boolean isSingleLine() {
        return super.isSingleLine();
    }

    public void setSingleLine(boolean singleLine) {
        super.setSingleLine(singleLine);
    }

    public void setSingleLine(int id) {
        setSingleLine(getResources().getBoolean(id));
    }

    public boolean isSingleSelection() {
        return this.singleSelection;
    }

    public void setSingleSelection(boolean singleSelection2) {
        if (this.singleSelection != singleSelection2) {
            this.singleSelection = singleSelection2;
            clearCheck();
        }
    }

    public void setSingleSelection(int id) {
        setSingleSelection(getResources().getBoolean(id));
    }

    public void setSelectionRequired(boolean selectionRequired2) {
        this.selectionRequired = selectionRequired2;
    }

    public boolean isSelectionRequired() {
        return this.selectionRequired;
    }

    private class CheckedStateTracker implements CompoundButton.OnCheckedChangeListener {
        private CheckedStateTracker() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!ChipGroup.this.protectFromCheckedChange) {
                if (!ChipGroup.this.getCheckedChipIds().isEmpty() || !ChipGroup.this.selectionRequired) {
                    int id = buttonView.getId();
                    if (isChecked) {
                        if (!(ChipGroup.this.checkedId == -1 || ChipGroup.this.checkedId == id || !ChipGroup.this.singleSelection)) {
                            ChipGroup chipGroup = ChipGroup.this;
                            chipGroup.setCheckedStateForView(chipGroup.checkedId, false);
                        }
                        ChipGroup.this.setCheckedId(id);
                    } else if (ChipGroup.this.checkedId == id) {
                        ChipGroup.this.setCheckedId(-1);
                    }
                } else {
                    ChipGroup.this.setCheckedStateForView(buttonView.getId(), true);
                    ChipGroup.this.setCheckedId(buttonView.getId(), false);
                }
            }
        }
    }

    private class PassThroughHierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        /* access modifiers changed from: private */
        public ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener;

        private PassThroughHierarchyChangeListener() {
        }

        public void onChildViewAdded(View parent, View child) {
            if (parent == ChipGroup.this && (child instanceof Chip)) {
                if (child.getId() == -1) {
                    child.setId(ViewCompat.generateViewId());
                }
                Chip chip = (Chip) child;
                if (chip.isChecked()) {
                    ((ChipGroup) parent).check(chip.getId());
                }
                chip.setOnCheckedChangeListenerInternal(ChipGroup.this.checkedStateTracker);
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener2 = this.onHierarchyChangeListener;
            if (onHierarchyChangeListener2 != null) {
                onHierarchyChangeListener2.onChildViewAdded(parent, child);
            }
        }

        public void onChildViewRemoved(View parent, View child) {
            if (parent == ChipGroup.this && (child instanceof Chip)) {
                ((Chip) child).setOnCheckedChangeListenerInternal((CompoundButton.OnCheckedChangeListener) null);
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener2 = this.onHierarchyChangeListener;
            if (onHierarchyChangeListener2 != null) {
                onHierarchyChangeListener2.onChildViewRemoved(parent, child);
            }
        }
    }
}
