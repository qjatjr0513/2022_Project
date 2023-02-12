package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.ConstraintReference;
import androidx.constraintlayout.core.state.State;
import java.util.Iterator;

public class HorizontalChainReference extends ChainReference {
    public HorizontalChainReference(State state) {
        super(state, State.Helper.HORIZONTAL_CHAIN);
    }

    public void apply() {
        ConstraintReference first = null;
        ConstraintReference previous = null;
        Iterator it = this.mReferences.iterator();
        while (it.hasNext()) {
            this.mState.constraints(it.next()).clearHorizontal();
        }
        Iterator it2 = this.mReferences.iterator();
        while (it2.hasNext()) {
            ConstraintReference reference = this.mState.constraints(it2.next());
            if (first == null) {
                first = reference;
                if (this.mStartToStart != null) {
                    first.startToStart(this.mStartToStart).margin(this.mMarginStart);
                } else if (this.mStartToEnd != null) {
                    first.startToEnd(this.mStartToEnd).margin(this.mMarginStart);
                } else if (this.mLeftToLeft != null) {
                    first.startToStart(this.mLeftToLeft).margin(this.mMarginLeft);
                } else if (this.mLeftToRight != null) {
                    first.startToEnd(this.mLeftToRight).margin(this.mMarginLeft);
                } else {
                    first.startToStart(State.PARENT);
                }
            }
            if (previous != null) {
                previous.endToStart(reference.getKey());
                reference.startToEnd(previous.getKey());
            }
            previous = reference;
        }
        if (previous != null) {
            if (this.mEndToStart != null) {
                previous.endToStart(this.mEndToStart).margin(this.mMarginEnd);
            } else if (this.mEndToEnd != null) {
                previous.endToEnd(this.mEndToEnd).margin(this.mMarginEnd);
            } else if (this.mRightToLeft != null) {
                previous.endToStart(this.mRightToLeft).margin(this.mMarginRight);
            } else if (this.mRightToRight != null) {
                previous.endToEnd(this.mRightToRight).margin(this.mMarginRight);
            } else {
                previous.endToEnd(State.PARENT);
            }
        }
        if (first != null) {
            if (this.mBias != 0.5f) {
                first.horizontalBias(this.mBias);
            }
            switch (C15441.$SwitchMap$androidx$constraintlayout$core$state$State$Chain[this.mStyle.ordinal()]) {
                case 1:
                    first.setHorizontalChainStyle(0);
                    return;
                case 2:
                    first.setHorizontalChainStyle(1);
                    return;
                case 3:
                    first.setHorizontalChainStyle(2);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: androidx.constraintlayout.core.state.helpers.HorizontalChainReference$1 */
    static /* synthetic */ class C15441 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$state$State$Chain;

        static {
            int[] iArr = new int[State.Chain.values().length];
            $SwitchMap$androidx$constraintlayout$core$state$State$Chain = iArr;
            try {
                iArr[State.Chain.SPREAD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$state$State$Chain[State.Chain.SPREAD_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$state$State$Chain[State.Chain.PACKED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }
}
