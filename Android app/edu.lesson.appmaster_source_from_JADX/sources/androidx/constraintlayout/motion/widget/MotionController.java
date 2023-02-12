package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.VelocityMatrix;
import androidx.constraintlayout.motion.utils.CustomSupport;
import androidx.constraintlayout.motion.utils.ViewOscillator;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.motion.utils.ViewState;
import androidx.constraintlayout.motion.utils.ViewTimeCycle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class MotionController {
    static final int BOUNCE = 4;
    private static final boolean DEBUG = false;
    public static final int DRAW_PATH_AS_CONFIGURED = 4;
    public static final int DRAW_PATH_BASIC = 1;
    public static final int DRAW_PATH_CARTESIAN = 3;
    public static final int DRAW_PATH_NONE = 0;
    public static final int DRAW_PATH_RECTANGLE = 5;
    public static final int DRAW_PATH_RELATIVE = 2;
    public static final int DRAW_PATH_SCREEN = 6;
    static final int EASE_IN = 1;
    static final int EASE_IN_OUT = 0;
    static final int EASE_OUT = 2;
    private static final boolean FAVOR_FIXED_SIZE_VIEWS = false;
    public static final int HORIZONTAL_PATH_X = 2;
    public static final int HORIZONTAL_PATH_Y = 3;
    private static final int INTERPOLATOR_REFERENCE_ID = -2;
    private static final int INTERPOLATOR_UNDEFINED = -3;
    static final int LINEAR = 3;
    static final int OVERSHOOT = 5;
    public static final int PATH_PERCENT = 0;
    public static final int PATH_PERPENDICULAR = 1;
    public static final int ROTATION_LEFT = 2;
    public static final int ROTATION_RIGHT = 1;
    private static final int SPLINE_STRING = -1;
    private static final String TAG = "MotionController";
    public static final int VERTICAL_PATH_X = 4;
    public static final int VERTICAL_PATH_Y = 5;
    private int MAX_DIMENSION = 4;
    String[] attributeTable;
    private CurveFit mArcSpline;
    private int[] mAttributeInterpolatorCount;
    private String[] mAttributeNames;
    private HashMap<String, ViewSpline> mAttributesMap;
    String mConstraintTag;
    float mCurrentCenterX;
    float mCurrentCenterY;
    private int mCurveFitType = -1;
    private HashMap<String, ViewOscillator> mCycleMap;
    private MotionPaths mEndMotionPath = new MotionPaths();
    private MotionConstrainedPoint mEndPoint = new MotionConstrainedPoint();
    boolean mForceMeasure = false;
    int mId;
    private double[] mInterpolateData;
    private int[] mInterpolateVariables;
    private double[] mInterpolateVelocity;
    private ArrayList<Key> mKeyList = new ArrayList<>();
    private KeyTrigger[] mKeyTriggers;
    private ArrayList<MotionPaths> mMotionPaths = new ArrayList<>();
    float mMotionStagger = Float.NaN;
    private boolean mNoMovement = false;
    private int mPathMotionArc = Key.UNSET;
    private Interpolator mQuantizeMotionInterpolator = null;
    private float mQuantizeMotionPhase = Float.NaN;
    private int mQuantizeMotionSteps = Key.UNSET;
    private CurveFit[] mSpline;
    float mStaggerOffset = 0.0f;
    float mStaggerScale = 1.0f;
    private MotionPaths mStartMotionPath = new MotionPaths();
    private MotionConstrainedPoint mStartPoint = new MotionConstrainedPoint();
    Rect mTempRect = new Rect();
    private HashMap<String, ViewTimeCycle> mTimeCycleAttributesMap;
    private int mTransformPivotTarget = Key.UNSET;
    private View mTransformPivotView = null;
    private float[] mValuesBuff = new float[4];
    private float[] mVelocity = new float[1];
    View mView;

    public int getTransformPivotTarget() {
        return this.mTransformPivotTarget;
    }

    public void setTransformPivotTarget(int transformPivotTarget) {
        this.mTransformPivotTarget = transformPivotTarget;
        this.mTransformPivotView = null;
    }

    /* access modifiers changed from: package-private */
    public MotionPaths getKeyFrame(int i) {
        return this.mMotionPaths.get(i);
    }

    MotionController(View view) {
        setView(view);
    }

    public float getStartX() {
        return this.mStartMotionPath.f371x;
    }

    public float getStartY() {
        return this.mStartMotionPath.f372y;
    }

    public float getFinalX() {
        return this.mEndMotionPath.f371x;
    }

    public float getFinalY() {
        return this.mEndMotionPath.f372y;
    }

    public float getStartWidth() {
        return this.mStartMotionPath.width;
    }

    public float getStartHeight() {
        return this.mStartMotionPath.height;
    }

    public float getFinalWidth() {
        return this.mEndMotionPath.width;
    }

    public float getFinalHeight() {
        return this.mEndMotionPath.height;
    }

    public int getAnimateRelativeTo() {
        return this.mStartMotionPath.mAnimateRelativeTo;
    }

    public void setupRelative(MotionController motionController) {
        this.mStartMotionPath.setupRelative(motionController, motionController.mStartMotionPath);
        this.mEndMotionPath.setupRelative(motionController, motionController.mEndMotionPath);
    }

    public float getCenterX() {
        return this.mCurrentCenterX;
    }

    public float getCenterY() {
        return this.mCurrentCenterY;
    }

    public void getCenter(double p, float[] pos, float[] vel) {
        double[] position = new double[4];
        double[] velocity = new double[4];
        int[] iArr = new int[4];
        this.mSpline[0].getPos(p, position);
        this.mSpline[0].getSlope(p, velocity);
        Arrays.fill(vel, 0.0f);
        this.mStartMotionPath.getCenter(p, this.mInterpolateVariables, position, pos, velocity, vel);
    }

    public void remeasure() {
        this.mForceMeasure = true;
    }

    /* access modifiers changed from: package-private */
    public void buildPath(float[] points, int pointCount) {
        float position;
        double p;
        MotionController motionController = this;
        int i = pointCount;
        float f = 1.0f;
        float mils = 1.0f / ((float) (i - 1));
        HashMap<String, ViewSpline> hashMap = motionController.mAttributesMap;
        ViewOscillator viewOscillator = null;
        SplineSet trans_x = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, ViewSpline> hashMap2 = motionController.mAttributesMap;
        SplineSet trans_y = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, ViewOscillator> hashMap3 = motionController.mCycleMap;
        ViewOscillator osc_x = hashMap3 == null ? null : hashMap3.get("translationX");
        HashMap<String, ViewOscillator> hashMap4 = motionController.mCycleMap;
        if (hashMap4 != null) {
            viewOscillator = hashMap4.get("translationY");
        }
        ViewOscillator osc_y = viewOscillator;
        int i2 = 0;
        while (i2 < i) {
            float position2 = ((float) i2) * mils;
            float f2 = motionController.mStaggerScale;
            if (f2 != f) {
                float f3 = motionController.mStaggerOffset;
                if (position2 < f3) {
                    position2 = 0.0f;
                }
                if (position2 <= f3 || ((double) position2) >= 1.0d) {
                    position = position2;
                } else {
                    position = Math.min((position2 - f3) * f2, f);
                }
            } else {
                position = position2;
            }
            double p2 = (double) position;
            Easing easing = motionController.mStartMotionPath.mKeyFrameEasing;
            Iterator<MotionPaths> it = motionController.mMotionPaths.iterator();
            float start = 0.0f;
            Easing easing2 = easing;
            float end = Float.NaN;
            while (it.hasNext()) {
                MotionPaths frame = it.next();
                if (frame.mKeyFrameEasing != null) {
                    if (frame.time < position) {
                        easing2 = frame.mKeyFrameEasing;
                        start = frame.time;
                    } else if (Float.isNaN(end)) {
                        end = frame.time;
                    }
                }
            }
            if (easing2 != null) {
                if (Float.isNaN(end)) {
                    end = 1.0f;
                }
                double d = p2;
                float offset = (float) easing2.get((double) ((position - start) / (end - start)));
                float f4 = offset;
                float f5 = end;
                p = (double) (((end - start) * offset) + start);
            } else {
                float f6 = end;
                p = p2;
            }
            motionController.mSpline[0].getPos(p, motionController.mInterpolateData);
            CurveFit curveFit = motionController.mArcSpline;
            if (curveFit != null) {
                double[] dArr = motionController.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(p, dArr);
                }
            }
            double d2 = p;
            Easing easing3 = easing2;
            float position3 = position;
            motionController.mStartMotionPath.getCenter(p, motionController.mInterpolateVariables, motionController.mInterpolateData, points, i2 * 2);
            if (osc_x != null) {
                int i3 = i2 * 2;
                points[i3] = points[i3] + osc_x.get(position3);
            } else if (trans_x != null) {
                int i4 = i2 * 2;
                points[i4] = points[i4] + trans_x.get(position3);
            }
            if (osc_y != null) {
                int i5 = (i2 * 2) + 1;
                points[i5] = points[i5] + osc_y.get(position3);
            } else if (trans_y != null) {
                int i6 = (i2 * 2) + 1;
                points[i6] = points[i6] + trans_y.get(position3);
            }
            i2++;
            f = 1.0f;
            motionController = this;
        }
    }

    /* access modifiers changed from: package-private */
    public double[] getPos(double position) {
        this.mSpline[0].getPos(position, this.mInterpolateData);
        CurveFit curveFit = this.mArcSpline;
        if (curveFit != null) {
            double[] dArr = this.mInterpolateData;
            if (dArr.length > 0) {
                curveFit.getPos(position, dArr);
            }
        }
        return this.mInterpolateData;
    }

    /* access modifiers changed from: package-private */
    public void buildBounds(float[] bounds, int pointCount) {
        float mils;
        MotionController motionController = this;
        int i = pointCount;
        float f = 1.0f;
        float mils2 = 1.0f / ((float) (i - 1));
        HashMap<String, ViewSpline> hashMap = motionController.mAttributesMap;
        SplineSet trans_x = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, ViewSpline> hashMap2 = motionController.mAttributesMap;
        if (hashMap2 != null) {
            SplineSet splineSet = hashMap2.get("translationY");
        }
        HashMap<String, ViewOscillator> hashMap3 = motionController.mCycleMap;
        if (hashMap3 != null) {
            ViewOscillator viewOscillator = hashMap3.get("translationX");
        }
        HashMap<String, ViewOscillator> hashMap4 = motionController.mCycleMap;
        if (hashMap4 != null) {
            ViewOscillator viewOscillator2 = hashMap4.get("translationY");
        }
        int i2 = 0;
        while (i2 < i) {
            float position = ((float) i2) * mils2;
            float f2 = motionController.mStaggerScale;
            if (f2 != f) {
                float f3 = motionController.mStaggerOffset;
                if (position < f3) {
                    position = 0.0f;
                }
                if (position > f3 && ((double) position) < 1.0d) {
                    position = Math.min((position - f3) * f2, f);
                }
            }
            double p = (double) position;
            Easing easing = motionController.mStartMotionPath.mKeyFrameEasing;
            float start = 0.0f;
            float end = Float.NaN;
            Iterator<MotionPaths> it = motionController.mMotionPaths.iterator();
            while (it.hasNext()) {
                MotionPaths frame = it.next();
                if (frame.mKeyFrameEasing != null) {
                    if (frame.time < position) {
                        Easing easing2 = frame.mKeyFrameEasing;
                        start = frame.time;
                        easing = easing2;
                    } else if (Float.isNaN(end)) {
                        end = frame.time;
                    }
                }
                int i3 = pointCount;
            }
            if (easing != null) {
                if (Float.isNaN(end)) {
                    end = 1.0f;
                }
                mils = mils2;
                p = (double) (((end - start) * ((float) easing.get((double) ((position - start) / (end - start))))) + start);
            } else {
                mils = mils2;
            }
            motionController.mSpline[0].getPos(p, motionController.mInterpolateData);
            CurveFit curveFit = motionController.mArcSpline;
            if (curveFit != null) {
                double[] dArr = motionController.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(p, dArr);
                }
            }
            motionController.mStartMotionPath.getBounds(motionController.mInterpolateVariables, motionController.mInterpolateData, bounds, i2 * 2);
            i2++;
            motionController = this;
            i = pointCount;
            mils2 = mils;
            trans_x = trans_x;
            f = 1.0f;
        }
    }

    private float getPreCycleDistance() {
        double p;
        float offset;
        int pointCount = 100;
        float[] points = new float[2];
        float mils = 1.0f / ((float) (100 - 1));
        float sum = 0.0f;
        double x = 0.0d;
        double y = 0.0d;
        int i = 0;
        while (i < pointCount) {
            float position = ((float) i) * mils;
            double p2 = (double) position;
            Easing easing = this.mStartMotionPath.mKeyFrameEasing;
            int pointCount2 = pointCount;
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            float start = 0.0f;
            Easing easing2 = easing;
            float end = Float.NaN;
            while (it.hasNext()) {
                MotionPaths frame = it.next();
                Iterator<MotionPaths> it2 = it;
                if (frame.mKeyFrameEasing != null) {
                    if (frame.time < position) {
                        Easing easing3 = frame.mKeyFrameEasing;
                        start = frame.time;
                        easing2 = easing3;
                    } else if (Float.isNaN(end)) {
                        end = frame.time;
                    }
                }
                it = it2;
            }
            if (easing2 != null) {
                if (Float.isNaN(end)) {
                    end = 1.0f;
                }
                double d = p2;
                offset = end;
                p = (double) (((end - start) * ((float) easing2.get((double) ((position - start) / (end - start))))) + start);
            } else {
                offset = end;
                p = p2;
            }
            this.mSpline[0].getPos(p, this.mInterpolateData);
            float f = offset;
            double d2 = p;
            Easing easing4 = easing2;
            float f2 = position;
            int i2 = i;
            this.mStartMotionPath.getCenter(p, this.mInterpolateVariables, this.mInterpolateData, points, 0);
            if (i2 > 0) {
                sum = (float) (((double) sum) + Math.hypot(y - ((double) points[1]), x - ((double) points[0])));
            }
            x = (double) points[0];
            y = (double) points[1];
            i = i2 + 1;
            pointCount = pointCount2;
        }
        return sum;
    }

    /* access modifiers changed from: package-private */
    public KeyPositionBase getPositionKeyframe(int layoutWidth, int layoutHeight, float x, float y) {
        RectF start = new RectF();
        start.left = this.mStartMotionPath.f371x;
        start.top = this.mStartMotionPath.f372y;
        start.right = start.left + this.mStartMotionPath.width;
        start.bottom = start.top + this.mStartMotionPath.height;
        RectF end = new RectF();
        end.left = this.mEndMotionPath.f371x;
        end.top = this.mEndMotionPath.f372y;
        end.right = end.left + this.mEndMotionPath.width;
        end.bottom = end.top + this.mEndMotionPath.height;
        Iterator<Key> it = this.mKeyList.iterator();
        while (it.hasNext()) {
            Key key = it.next();
            if ((key instanceof KeyPositionBase) && ((KeyPositionBase) key).intersects(layoutWidth, layoutHeight, start, end, x, y)) {
                return (KeyPositionBase) key;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int buildKeyFrames(float[] keyFrames, int[] mode) {
        if (keyFrames == null) {
            return 0;
        }
        int count = 0;
        double[] time = this.mSpline[0].getTimePoints();
        if (mode != null) {
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            while (it.hasNext()) {
                mode[count] = it.next().mMode;
                count++;
            }
            count = 0;
        }
        for (int i = 0; i < time.length; i++) {
            this.mSpline[0].getPos(time[i], this.mInterpolateData);
            this.mStartMotionPath.getCenter(time[i], this.mInterpolateVariables, this.mInterpolateData, keyFrames, count);
            count += 2;
        }
        return count / 2;
    }

    /* access modifiers changed from: package-private */
    public int buildKeyBounds(float[] keyBounds, int[] mode) {
        if (keyBounds == null) {
            return 0;
        }
        int count = 0;
        double[] time = this.mSpline[0].getTimePoints();
        if (mode != null) {
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            while (it.hasNext()) {
                mode[count] = it.next().mMode;
                count++;
            }
            count = 0;
        }
        for (double pos : time) {
            this.mSpline[0].getPos(pos, this.mInterpolateData);
            this.mStartMotionPath.getBounds(this.mInterpolateVariables, this.mInterpolateData, keyBounds, count);
            count += 2;
        }
        return count / 2;
    }

    /* access modifiers changed from: package-private */
    public int getAttributeValues(String attributeType, float[] points, int pointCount) {
        float f = 1.0f / ((float) (pointCount - 1));
        SplineSet spline = this.mAttributesMap.get(attributeType);
        if (spline == null) {
            return -1;
        }
        for (int j = 0; j < points.length; j++) {
            points[j] = spline.get((float) (j / (points.length - 1)));
        }
        return points.length;
    }

    /* access modifiers changed from: package-private */
    public void buildRect(float p, float[] path, int offset) {
        this.mSpline[0].getPos((double) getAdjustedPosition(p, (float[]) null), this.mInterpolateData);
        this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, path, offset);
    }

    /* access modifiers changed from: package-private */
    public void buildRectangles(float[] path, int pointCount) {
        float mils = 1.0f / ((float) (pointCount - 1));
        for (int i = 0; i < pointCount; i++) {
            this.mSpline[0].getPos((double) getAdjustedPosition(((float) i) * mils, (float[]) null), this.mInterpolateData);
            this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, path, i * 8);
        }
    }

    /* access modifiers changed from: package-private */
    public float getKeyFrameParameter(int type, float x, float y) {
        float dx = this.mEndMotionPath.f371x - this.mStartMotionPath.f371x;
        float dy = this.mEndMotionPath.f372y - this.mStartMotionPath.f372y;
        float startCenterX = this.mStartMotionPath.f371x + (this.mStartMotionPath.width / 2.0f);
        float startCenterY = this.mStartMotionPath.f372y + (this.mStartMotionPath.height / 2.0f);
        float hypotenuse = (float) Math.hypot((double) dx, (double) dy);
        if (((double) hypotenuse) < 1.0E-7d) {
            return Float.NaN;
        }
        float vx = x - startCenterX;
        float vy = y - startCenterY;
        if (((float) Math.hypot((double) vx, (double) vy)) == 0.0f) {
            return 0.0f;
        }
        float pathDistance = (vx * dx) + (vy * dy);
        switch (type) {
            case 0:
                return pathDistance / hypotenuse;
            case 1:
                return (float) Math.sqrt((double) ((hypotenuse * hypotenuse) - (pathDistance * pathDistance)));
            case 2:
                return vx / dx;
            case 3:
                return vy / dx;
            case 4:
                return vx / dy;
            case 5:
                return vy / dy;
            default:
                return 0.0f;
        }
    }

    private void insertKey(MotionPaths point) {
        int pos = Collections.binarySearch(this.mMotionPaths, point);
        if (pos == 0) {
            Log.e(TAG, " KeyPath position \"" + point.position + "\" outside of range");
        }
        this.mMotionPaths.add((-pos) - 1, point);
    }

    /* access modifiers changed from: package-private */
    public void addKeys(ArrayList<Key> list) {
        this.mKeyList.addAll(list);
    }

    public void addKey(Key key) {
        this.mKeyList.add(key);
    }

    public void setPathMotionArc(int arc) {
        this.mPathMotionArc = arc;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v25, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v28, resolved type: double[][]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setup(int r31, int r32, float r33, long r34) {
        /*
            r30 = this;
            r0 = r30
            java.lang.Class<double> r1 = double.class
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            java.util.HashSet r5 = new java.util.HashSet
            r5.<init>()
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            r7 = 0
            int r8 = r0.mPathMotionArc
            int r9 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r8 == r9) goto L_0x002a
            androidx.constraintlayout.motion.widget.MotionPaths r8 = r0.mStartMotionPath
            int r9 = r0.mPathMotionArc
            r8.mPathMotionArc = r9
        L_0x002a:
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r8 = r0.mStartPoint
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r9 = r0.mEndPoint
            r8.different(r9, r4)
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r8 = r0.mKeyList
            if (r8 == 0) goto L_0x00a3
            java.util.Iterator r8 = r8.iterator()
        L_0x0039:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x00a0
            java.lang.Object r9 = r8.next()
            androidx.constraintlayout.motion.widget.Key r9 = (androidx.constraintlayout.motion.widget.Key) r9
            boolean r10 = r9 instanceof androidx.constraintlayout.motion.widget.KeyPosition
            if (r10 == 0) goto L_0x0072
            r10 = r9
            androidx.constraintlayout.motion.widget.KeyPosition r10 = (androidx.constraintlayout.motion.widget.KeyPosition) r10
            androidx.constraintlayout.motion.widget.MotionPaths r15 = new androidx.constraintlayout.motion.widget.MotionPaths
            androidx.constraintlayout.motion.widget.MotionPaths r14 = r0.mStartMotionPath
            androidx.constraintlayout.motion.widget.MotionPaths r13 = r0.mEndMotionPath
            r11 = r15
            r12 = r31
            r16 = r13
            r13 = r32
            r17 = r14
            r14 = r10
            r18 = r2
            r2 = r15
            r15 = r17
            r11.<init>(r12, r13, r14, r15, r16)
            r0.insertKey(r2)
            int r2 = r10.mCurveFit
            int r11 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r2 == r11) goto L_0x0071
            int r2 = r10.mCurveFit
            r0.mCurveFitType = r2
        L_0x0071:
            goto L_0x009d
        L_0x0072:
            r18 = r2
            boolean r2 = r9 instanceof androidx.constraintlayout.motion.widget.KeyCycle
            if (r2 == 0) goto L_0x007c
            r9.getAttributeNames(r5)
            goto L_0x009d
        L_0x007c:
            boolean r2 = r9 instanceof androidx.constraintlayout.motion.widget.KeyTimeCycle
            if (r2 == 0) goto L_0x0084
            r9.getAttributeNames(r3)
            goto L_0x009d
        L_0x0084:
            boolean r2 = r9 instanceof androidx.constraintlayout.motion.widget.KeyTrigger
            if (r2 == 0) goto L_0x0097
            if (r7 != 0) goto L_0x0090
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r7 = r2
        L_0x0090:
            r2 = r9
            androidx.constraintlayout.motion.widget.KeyTrigger r2 = (androidx.constraintlayout.motion.widget.KeyTrigger) r2
            r7.add(r2)
            goto L_0x009d
        L_0x0097:
            r9.setInterpolation(r6)
            r9.getAttributeNames(r4)
        L_0x009d:
            r2 = r18
            goto L_0x0039
        L_0x00a0:
            r18 = r2
            goto L_0x00a5
        L_0x00a3:
            r18 = r2
        L_0x00a5:
            r2 = 0
            if (r7 == 0) goto L_0x00b2
            androidx.constraintlayout.motion.widget.KeyTrigger[] r8 = new androidx.constraintlayout.motion.widget.KeyTrigger[r2]
            java.lang.Object[] r8 = r7.toArray(r8)
            androidx.constraintlayout.motion.widget.KeyTrigger[] r8 = (androidx.constraintlayout.motion.widget.KeyTrigger[]) r8
            r0.mKeyTriggers = r8
        L_0x00b2:
            boolean r8 = r4.isEmpty()
            java.lang.String r9 = ","
            java.lang.String r10 = "CUSTOM,"
            r11 = 1
            if (r8 != 0) goto L_0x01a2
            java.util.HashMap r8 = new java.util.HashMap
            r8.<init>()
            r0.mAttributesMap = r8
            java.util.Iterator r8 = r4.iterator()
        L_0x00c8:
            boolean r12 = r8.hasNext()
            if (r12 == 0) goto L_0x013a
            java.lang.Object r12 = r8.next()
            java.lang.String r12 = (java.lang.String) r12
            boolean r13 = r12.startsWith(r10)
            if (r13 == 0) goto L_0x0120
            android.util.SparseArray r13 = new android.util.SparseArray
            r13.<init>()
            java.lang.String[] r14 = r12.split(r9)
            r14 = r14[r11]
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r15 = r0.mKeyList
            java.util.Iterator r15 = r15.iterator()
        L_0x00eb:
            boolean r16 = r15.hasNext()
            if (r16 == 0) goto L_0x0119
            java.lang.Object r16 = r15.next()
            r11 = r16
            androidx.constraintlayout.motion.widget.Key r11 = (androidx.constraintlayout.motion.widget.Key) r11
            java.util.HashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r2 = r11.mCustomConstraints
            if (r2 != 0) goto L_0x0100
            r2 = 0
            r11 = 1
            goto L_0x00eb
        L_0x0100:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r2 = r11.mCustomConstraints
            java.lang.Object r2 = r2.get(r14)
            androidx.constraintlayout.widget.ConstraintAttribute r2 = (androidx.constraintlayout.widget.ConstraintAttribute) r2
            if (r2 == 0) goto L_0x0112
            r19 = r7
            int r7 = r11.mFramePosition
            r13.append(r7, r2)
            goto L_0x0114
        L_0x0112:
            r19 = r7
        L_0x0114:
            r7 = r19
            r2 = 0
            r11 = 1
            goto L_0x00eb
        L_0x0119:
            r19 = r7
            androidx.constraintlayout.motion.utils.ViewSpline r2 = androidx.constraintlayout.motion.utils.ViewSpline.makeCustomSpline(r12, r13)
            goto L_0x0126
        L_0x0120:
            r19 = r7
            androidx.constraintlayout.motion.utils.ViewSpline r2 = androidx.constraintlayout.motion.utils.ViewSpline.makeSpline(r12)
        L_0x0126:
            if (r2 != 0) goto L_0x012d
            r7 = r19
            r2 = 0
            r11 = 1
            goto L_0x00c8
        L_0x012d:
            r2.setType(r12)
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r7 = r0.mAttributesMap
            r7.put(r12, r2)
            r7 = r19
            r2 = 0
            r11 = 1
            goto L_0x00c8
        L_0x013a:
            r19 = r7
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r2 = r0.mKeyList
            if (r2 == 0) goto L_0x015a
            java.util.Iterator r2 = r2.iterator()
        L_0x0144:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x015a
            java.lang.Object r7 = r2.next()
            androidx.constraintlayout.motion.widget.Key r7 = (androidx.constraintlayout.motion.widget.Key) r7
            boolean r8 = r7 instanceof androidx.constraintlayout.motion.widget.KeyAttributes
            if (r8 == 0) goto L_0x0159
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r8 = r0.mAttributesMap
            r7.addValues(r8)
        L_0x0159:
            goto L_0x0144
        L_0x015a:
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r2 = r0.mStartPoint
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r7 = r0.mAttributesMap
            r8 = 0
            r2.addValues(r7, r8)
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r2 = r0.mEndPoint
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r7 = r0.mAttributesMap
            r8 = 100
            r2.addValues(r7, r8)
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r2 = r0.mAttributesMap
            java.util.Set r2 = r2.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x0175:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x01a4
            java.lang.Object r7 = r2.next()
            java.lang.String r7 = (java.lang.String) r7
            r8 = 0
            boolean r11 = r6.containsKey(r7)
            if (r11 == 0) goto L_0x0194
            java.lang.Object r11 = r6.get(r7)
            java.lang.Integer r11 = (java.lang.Integer) r11
            if (r11 == 0) goto L_0x0194
            int r8 = r11.intValue()
        L_0x0194:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r11 = r0.mAttributesMap
            java.lang.Object r11 = r11.get(r7)
            androidx.constraintlayout.core.motion.utils.SplineSet r11 = (androidx.constraintlayout.core.motion.utils.SplineSet) r11
            if (r11 == 0) goto L_0x01a1
            r11.setup(r8)
        L_0x01a1:
            goto L_0x0175
        L_0x01a2:
            r19 = r7
        L_0x01a4:
            boolean r2 = r3.isEmpty()
            if (r2 != 0) goto L_0x0286
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r2 = r0.mTimeCycleAttributesMap
            if (r2 != 0) goto L_0x01b5
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r0.mTimeCycleAttributesMap = r2
        L_0x01b5:
            java.util.Iterator r2 = r3.iterator()
        L_0x01b9:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x0230
            java.lang.Object r7 = r2.next()
            java.lang.String r7 = (java.lang.String) r7
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r8 = r0.mTimeCycleAttributesMap
            boolean r8 = r8.containsKey(r7)
            if (r8 == 0) goto L_0x01ce
            goto L_0x01b9
        L_0x01ce:
            r8 = 0
            boolean r11 = r7.startsWith(r10)
            if (r11 == 0) goto L_0x0218
            android.util.SparseArray r11 = new android.util.SparseArray
            r11.<init>()
            java.lang.String[] r12 = r7.split(r9)
            r13 = 1
            r12 = r12[r13]
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r13 = r0.mKeyList
            java.util.Iterator r13 = r13.iterator()
        L_0x01e7:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x020f
            java.lang.Object r14 = r13.next()
            androidx.constraintlayout.motion.widget.Key r14 = (androidx.constraintlayout.motion.widget.Key) r14
            java.util.HashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r15 = r14.mCustomConstraints
            if (r15 != 0) goto L_0x01f8
            goto L_0x01e7
        L_0x01f8:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r15 = r14.mCustomConstraints
            java.lang.Object r15 = r15.get(r12)
            androidx.constraintlayout.widget.ConstraintAttribute r15 = (androidx.constraintlayout.widget.ConstraintAttribute) r15
            if (r15 == 0) goto L_0x020a
            r20 = r2
            int r2 = r14.mFramePosition
            r11.append(r2, r15)
            goto L_0x020c
        L_0x020a:
            r20 = r2
        L_0x020c:
            r2 = r20
            goto L_0x01e7
        L_0x020f:
            r20 = r2
            androidx.constraintlayout.motion.utils.ViewTimeCycle r2 = androidx.constraintlayout.motion.utils.ViewTimeCycle.makeCustomSpline(r7, r11)
            r11 = r34
            goto L_0x0220
        L_0x0218:
            r20 = r2
            r11 = r34
            androidx.constraintlayout.motion.utils.ViewTimeCycle r2 = androidx.constraintlayout.motion.utils.ViewTimeCycle.makeSpline(r7, r11)
        L_0x0220:
            if (r2 != 0) goto L_0x0225
            r2 = r20
            goto L_0x01b9
        L_0x0225:
            r2.setType(r7)
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r8 = r0.mTimeCycleAttributesMap
            r8.put(r7, r2)
            r2 = r20
            goto L_0x01b9
        L_0x0230:
            r11 = r34
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r2 = r0.mKeyList
            if (r2 == 0) goto L_0x0253
            java.util.Iterator r2 = r2.iterator()
        L_0x023a:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x0253
            java.lang.Object r7 = r2.next()
            androidx.constraintlayout.motion.widget.Key r7 = (androidx.constraintlayout.motion.widget.Key) r7
            boolean r8 = r7 instanceof androidx.constraintlayout.motion.widget.KeyTimeCycle
            if (r8 == 0) goto L_0x0252
            r8 = r7
            androidx.constraintlayout.motion.widget.KeyTimeCycle r8 = (androidx.constraintlayout.motion.widget.KeyTimeCycle) r8
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r9 = r0.mTimeCycleAttributesMap
            r8.addTimeValues(r9)
        L_0x0252:
            goto L_0x023a
        L_0x0253:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r2 = r0.mTimeCycleAttributesMap
            java.util.Set r2 = r2.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x025d:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x0288
            java.lang.Object r7 = r2.next()
            java.lang.String r7 = (java.lang.String) r7
            r8 = 0
            boolean r9 = r6.containsKey(r7)
            if (r9 == 0) goto L_0x027a
            java.lang.Object r9 = r6.get(r7)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r8 = r9.intValue()
        L_0x027a:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r9 = r0.mTimeCycleAttributesMap
            java.lang.Object r9 = r9.get(r7)
            androidx.constraintlayout.motion.utils.ViewTimeCycle r9 = (androidx.constraintlayout.motion.utils.ViewTimeCycle) r9
            r9.setup(r8)
            goto L_0x025d
        L_0x0286:
            r11 = r34
        L_0x0288:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionPaths> r2 = r0.mMotionPaths
            int r2 = r2.size()
            r7 = 2
            int r2 = r2 + r7
            androidx.constraintlayout.motion.widget.MotionPaths[] r2 = new androidx.constraintlayout.motion.widget.MotionPaths[r2]
            r8 = 1
            androidx.constraintlayout.motion.widget.MotionPaths r9 = r0.mStartMotionPath
            r13 = 0
            r2[r13] = r9
            int r9 = r2.length
            r13 = 1
            int r9 = r9 - r13
            androidx.constraintlayout.motion.widget.MotionPaths r13 = r0.mEndMotionPath
            r2[r9] = r13
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionPaths> r9 = r0.mMotionPaths
            int r9 = r9.size()
            if (r9 <= 0) goto L_0x02af
            int r9 = r0.mCurveFitType
            r13 = -1
            if (r9 != r13) goto L_0x02af
            r9 = 0
            r0.mCurveFitType = r9
        L_0x02af:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionPaths> r9 = r0.mMotionPaths
            java.util.Iterator r9 = r9.iterator()
        L_0x02b5:
            boolean r13 = r9.hasNext()
            if (r13 == 0) goto L_0x02c7
            java.lang.Object r13 = r9.next()
            androidx.constraintlayout.motion.widget.MotionPaths r13 = (androidx.constraintlayout.motion.widget.MotionPaths) r13
            int r14 = r8 + 1
            r2[r8] = r13
            r8 = r14
            goto L_0x02b5
        L_0x02c7:
            r9 = 18
            java.util.HashSet r13 = new java.util.HashSet
            r13.<init>()
            androidx.constraintlayout.motion.widget.MotionPaths r14 = r0.mEndMotionPath
            java.util.LinkedHashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r14 = r14.attributes
            java.util.Set r14 = r14.keySet()
            java.util.Iterator r14 = r14.iterator()
        L_0x02da:
            boolean r15 = r14.hasNext()
            if (r15 == 0) goto L_0x030c
            java.lang.Object r15 = r14.next()
            java.lang.String r15 = (java.lang.String) r15
            androidx.constraintlayout.motion.widget.MotionPaths r7 = r0.mStartMotionPath
            java.util.LinkedHashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r7 = r7.attributes
            boolean r7 = r7.containsKey(r15)
            if (r7 == 0) goto L_0x030a
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.StringBuilder r7 = r7.append(r10)
            java.lang.StringBuilder r7 = r7.append(r15)
            java.lang.String r7 = r7.toString()
            boolean r7 = r4.contains(r7)
            if (r7 != 0) goto L_0x030a
            r13.add(r15)
        L_0x030a:
            r7 = 2
            goto L_0x02da
        L_0x030c:
            r7 = 0
            java.lang.String[] r10 = new java.lang.String[r7]
            java.lang.Object[] r7 = r13.toArray(r10)
            java.lang.String[] r7 = (java.lang.String[]) r7
            r0.mAttributeNames = r7
            int r7 = r7.length
            int[] r7 = new int[r7]
            r0.mAttributeInterpolatorCount = r7
            r7 = 0
        L_0x031d:
            java.lang.String[] r10 = r0.mAttributeNames
            int r14 = r10.length
            if (r7 >= r14) goto L_0x0363
            r10 = r10[r7]
            int[] r14 = r0.mAttributeInterpolatorCount
            r15 = 0
            r14[r7] = r15
            r14 = 0
        L_0x032a:
            int r15 = r2.length
            if (r14 >= r15) goto L_0x035c
            r15 = r2[r14]
            java.util.LinkedHashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r15 = r15.attributes
            boolean r15 = r15.containsKey(r10)
            if (r15 == 0) goto L_0x0355
            r15 = r2[r14]
            java.util.LinkedHashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r15 = r15.attributes
            java.lang.Object r15 = r15.get(r10)
            androidx.constraintlayout.widget.ConstraintAttribute r15 = (androidx.constraintlayout.widget.ConstraintAttribute) r15
            if (r15 == 0) goto L_0x0352
            r21 = r3
            int[] r3 = r0.mAttributeInterpolatorCount
            r22 = r3[r7]
            int r23 = r15.numberOfInterpolatedValues()
            int r22 = r22 + r23
            r3[r7] = r22
            goto L_0x035e
        L_0x0352:
            r21 = r3
            goto L_0x0357
        L_0x0355:
            r21 = r3
        L_0x0357:
            int r14 = r14 + 1
            r3 = r21
            goto L_0x032a
        L_0x035c:
            r21 = r3
        L_0x035e:
            int r7 = r7 + 1
            r3 = r21
            goto L_0x031d
        L_0x0363:
            r21 = r3
            r3 = 0
            r7 = r2[r3]
            int r3 = r7.mPathMotionArc
            int r7 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r3 == r7) goto L_0x0370
            r3 = 1
            goto L_0x0371
        L_0x0370:
            r3 = 0
        L_0x0371:
            java.lang.String[] r7 = r0.mAttributeNames
            int r7 = r7.length
            int r7 = r7 + r9
            boolean[] r7 = new boolean[r7]
            r10 = 1
        L_0x0378:
            int r14 = r2.length
            if (r10 >= r14) goto L_0x038d
            r14 = r2[r10]
            int r15 = r10 + -1
            r15 = r2[r15]
            r22 = r4
            java.lang.String[] r4 = r0.mAttributeNames
            r14.different(r15, r7, r4, r3)
            int r10 = r10 + 1
            r4 = r22
            goto L_0x0378
        L_0x038d:
            r22 = r4
            r4 = 0
            r8 = 1
        L_0x0391:
            int r10 = r7.length
            if (r8 >= r10) goto L_0x039d
            boolean r10 = r7[r8]
            if (r10 == 0) goto L_0x039a
            int r4 = r4 + 1
        L_0x039a:
            int r8 = r8 + 1
            goto L_0x0391
        L_0x039d:
            int[] r8 = new int[r4]
            r0.mInterpolateVariables = r8
            r8 = 2
            int r10 = java.lang.Math.max(r8, r4)
            double[] r8 = new double[r10]
            r0.mInterpolateData = r8
            double[] r8 = new double[r10]
            r0.mInterpolateVelocity = r8
            r4 = 0
            r8 = 1
        L_0x03b0:
            int r14 = r7.length
            if (r8 >= r14) goto L_0x03c1
            boolean r14 = r7[r8]
            if (r14 == 0) goto L_0x03be
            int[] r14 = r0.mInterpolateVariables
            int r15 = r4 + 1
            r14[r4] = r8
            r4 = r15
        L_0x03be:
            int r8 = r8 + 1
            goto L_0x03b0
        L_0x03c1:
            int r8 = r2.length
            int[] r14 = r0.mInterpolateVariables
            int r14 = r14.length
            r23 = r3
            r15 = 2
            int[] r3 = new int[r15]
            r15 = 1
            r3[r15] = r14
            r14 = 0
            r3[r14] = r8
            java.lang.Object r3 = java.lang.reflect.Array.newInstance(r1, r3)
            double[][] r3 = (double[][]) r3
            int r8 = r2.length
            double[] r8 = new double[r8]
            r14 = 0
        L_0x03da:
            int r15 = r2.length
            if (r14 >= r15) goto L_0x03fa
            r15 = r2[r14]
            r24 = r4
            r4 = r3[r14]
            r25 = r6
            int[] r6 = r0.mInterpolateVariables
            r15.fillStandard(r4, r6)
            r4 = r2[r14]
            float r4 = r4.time
            r15 = r7
            double r6 = (double) r4
            r8[r14] = r6
            int r14 = r14 + 1
            r7 = r15
            r4 = r24
            r6 = r25
            goto L_0x03da
        L_0x03fa:
            r24 = r4
            r25 = r6
            r15 = r7
            r4 = 0
        L_0x0400:
            int[] r6 = r0.mInterpolateVariables
            int r7 = r6.length
            if (r4 >= r7) goto L_0x045f
            r6 = r6[r4]
            java.lang.String[] r7 = androidx.constraintlayout.motion.widget.MotionPaths.names
            int r7 = r7.length
            if (r6 >= r7) goto L_0x0452
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String[] r14 = androidx.constraintlayout.motion.widget.MotionPaths.names
            r26 = r6
            int[] r6 = r0.mInterpolateVariables
            r6 = r6[r4]
            r6 = r14[r6]
            java.lang.StringBuilder r6 = r7.append(r6)
            java.lang.String r7 = " ["
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            r7 = 0
        L_0x042a:
            int r14 = r2.length
            if (r7 >= r14) goto L_0x044d
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.StringBuilder r14 = r14.append(r6)
            r27 = r3[r7]
            r28 = r9
            r29 = r10
            r9 = r27[r4]
            java.lang.StringBuilder r9 = r14.append(r9)
            java.lang.String r6 = r9.toString()
            int r7 = r7 + 1
            r9 = r28
            r10 = r29
            goto L_0x042a
        L_0x044d:
            r28 = r9
            r29 = r10
            goto L_0x0458
        L_0x0452:
            r26 = r6
            r28 = r9
            r29 = r10
        L_0x0458:
            int r4 = r4 + 1
            r9 = r28
            r10 = r29
            goto L_0x0400
        L_0x045f:
            r28 = r9
            r29 = r10
            java.lang.String[] r4 = r0.mAttributeNames
            int r4 = r4.length
            r6 = 1
            int r4 = r4 + r6
            androidx.constraintlayout.core.motion.utils.CurveFit[] r4 = new androidx.constraintlayout.core.motion.utils.CurveFit[r4]
            r0.mSpline = r4
            r4 = 0
        L_0x046d:
            java.lang.String[] r6 = r0.mAttributeNames
            int r7 = r6.length
            if (r4 >= r7) goto L_0x04e3
            r7 = 0
            r9 = 0
            r10 = 0
            r6 = r6[r4]
            r14 = 0
        L_0x0478:
            int r11 = r2.length
            if (r14 >= r11) goto L_0x04c3
            r11 = r2[r14]
            boolean r11 = r11.hasCustomData(r6)
            if (r11 == 0) goto L_0x04ba
            if (r9 != 0) goto L_0x04a6
            int r11 = r2.length
            double[] r10 = new double[r11]
            int r11 = r2.length
            r12 = r2[r14]
            int r12 = r12.getCustomDataCount(r6)
            r26 = r10
            r27 = r13
            r10 = 2
            int[] r13 = new int[r10]
            r10 = 1
            r13[r10] = r12
            r10 = 0
            r13[r10] = r11
            java.lang.Object r10 = java.lang.reflect.Array.newInstance(r1, r13)
            r9 = r10
            double[][] r9 = (double[][]) r9
            r10 = r26
            goto L_0x04a8
        L_0x04a6:
            r27 = r13
        L_0x04a8:
            r11 = r2[r14]
            float r11 = r11.time
            double r11 = (double) r11
            r10[r7] = r11
            r11 = r2[r14]
            r12 = r9[r7]
            r13 = 0
            r11.getCustomData(r6, r12, r13)
            int r7 = r7 + 1
            goto L_0x04bc
        L_0x04ba:
            r27 = r13
        L_0x04bc:
            int r14 = r14 + 1
            r11 = r34
            r13 = r27
            goto L_0x0478
        L_0x04c3:
            r27 = r13
            double[] r10 = java.util.Arrays.copyOf(r10, r7)
            java.lang.Object[] r11 = java.util.Arrays.copyOf(r9, r7)
            r9 = r11
            double[][] r9 = (double[][]) r9
            androidx.constraintlayout.core.motion.utils.CurveFit[] r11 = r0.mSpline
            int r12 = r4 + 1
            int r13 = r0.mCurveFitType
            androidx.constraintlayout.core.motion.utils.CurveFit r13 = androidx.constraintlayout.core.motion.utils.CurveFit.get(r13, r10, r9)
            r11[r12] = r13
            int r4 = r4 + 1
            r11 = r34
            r13 = r27
            goto L_0x046d
        L_0x04e3:
            r27 = r13
            androidx.constraintlayout.core.motion.utils.CurveFit[] r4 = r0.mSpline
            int r6 = r0.mCurveFitType
            androidx.constraintlayout.core.motion.utils.CurveFit r6 = androidx.constraintlayout.core.motion.utils.CurveFit.get(r6, r8, r3)
            r7 = 0
            r4[r7] = r6
            r4 = r2[r7]
            int r4 = r4.mPathMotionArc
            int r6 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r4 == r6) goto L_0x0539
            int r4 = r2.length
            int[] r6 = new int[r4]
            double[] r7 = new double[r4]
            r9 = 2
            int[] r10 = new int[r9]
            r11 = 1
            r10[r11] = r9
            r9 = 0
            r10[r9] = r4
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r10)
            double[][] r1 = (double[][]) r1
            r9 = 0
        L_0x050d:
            if (r9 >= r4) goto L_0x0533
            r10 = r2[r9]
            int r10 = r10.mPathMotionArc
            r6[r9] = r10
            r10 = r2[r9]
            float r10 = r10.time
            double r10 = (double) r10
            r7[r9] = r10
            r10 = r1[r9]
            r11 = r2[r9]
            float r11 = r11.f371x
            double r11 = (double) r11
            r13 = 0
            r10[r13] = r11
            r10 = r1[r9]
            r11 = r2[r9]
            float r11 = r11.f372y
            double r11 = (double) r11
            r14 = 1
            r10[r14] = r11
            int r9 = r9 + 1
            goto L_0x050d
        L_0x0533:
            androidx.constraintlayout.core.motion.utils.CurveFit r9 = androidx.constraintlayout.core.motion.utils.CurveFit.getArc(r6, r7, r1)
            r0.mArcSpline = r9
        L_0x0539:
            r1 = 2143289344(0x7fc00000, float:NaN)
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            r0.mCycleMap = r4
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r4 = r0.mKeyList
            if (r4 == 0) goto L_0x05af
            java.util.Iterator r4 = r5.iterator()
        L_0x054a:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x0576
            java.lang.Object r6 = r4.next()
            java.lang.String r6 = (java.lang.String) r6
            androidx.constraintlayout.motion.utils.ViewOscillator r7 = androidx.constraintlayout.motion.utils.ViewOscillator.makeSpline(r6)
            if (r7 != 0) goto L_0x055d
            goto L_0x054a
        L_0x055d:
            boolean r9 = r7.variesByPath()
            if (r9 == 0) goto L_0x056d
            boolean r9 = java.lang.Float.isNaN(r1)
            if (r9 == 0) goto L_0x056d
            float r1 = r30.getPreCycleDistance()
        L_0x056d:
            r7.setType(r6)
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewOscillator> r9 = r0.mCycleMap
            r9.put(r6, r7)
            goto L_0x054a
        L_0x0576:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r4 = r0.mKeyList
            java.util.Iterator r4 = r4.iterator()
        L_0x057c:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x0595
            java.lang.Object r6 = r4.next()
            androidx.constraintlayout.motion.widget.Key r6 = (androidx.constraintlayout.motion.widget.Key) r6
            boolean r7 = r6 instanceof androidx.constraintlayout.motion.widget.KeyCycle
            if (r7 == 0) goto L_0x0594
            r7 = r6
            androidx.constraintlayout.motion.widget.KeyCycle r7 = (androidx.constraintlayout.motion.widget.KeyCycle) r7
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewOscillator> r9 = r0.mCycleMap
            r7.addCycleValues(r9)
        L_0x0594:
            goto L_0x057c
        L_0x0595:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewOscillator> r4 = r0.mCycleMap
            java.util.Collection r4 = r4.values()
            java.util.Iterator r4 = r4.iterator()
        L_0x059f:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x05af
            java.lang.Object r6 = r4.next()
            androidx.constraintlayout.motion.utils.ViewOscillator r6 = (androidx.constraintlayout.motion.utils.ViewOscillator) r6
            r6.setup(r1)
            goto L_0x059f
        L_0x05af:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionController.setup(int, int, float, long):void");
    }

    public String toString() {
        return " start: x: " + this.mStartMotionPath.f371x + " y: " + this.mStartMotionPath.f372y + " end: x: " + this.mEndMotionPath.f371x + " y: " + this.mEndMotionPath.f372y;
    }

    private void readView(MotionPaths motionPaths) {
        motionPaths.setBounds((float) ((int) this.mView.getX()), (float) ((int) this.mView.getY()), (float) this.mView.getWidth(), (float) this.mView.getHeight());
    }

    public void setView(View view) {
        this.mView = view;
        this.mId = view.getId();
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp instanceof ConstraintLayout.LayoutParams) {
            this.mConstraintTag = ((ConstraintLayout.LayoutParams) lp).getConstraintTag();
        }
    }

    public View getView() {
        return this.mView;
    }

    /* access modifiers changed from: package-private */
    public void setStartCurrentState(View v) {
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        this.mStartMotionPath.setBounds(v.getX(), v.getY(), (float) v.getWidth(), (float) v.getHeight());
        this.mStartPoint.setState(v);
    }

    public void setStartState(ViewState rect, View v, int rotation, int preWidth, int preHeight) {
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        Rect r = new Rect();
        switch (rotation) {
            case 1:
                int cx = rect.left + rect.right;
                r.left = ((rect.top + rect.bottom) - rect.width()) / 2;
                r.top = preWidth - ((rect.height() + cx) / 2);
                r.right = r.left + rect.width();
                r.bottom = r.top + rect.height();
                break;
            case 2:
                int cx2 = rect.left + rect.right;
                r.left = preHeight - ((rect.width() + (rect.top + rect.bottom)) / 2);
                r.top = (cx2 - rect.height()) / 2;
                r.right = r.left + rect.width();
                r.bottom = r.top + rect.height();
                break;
        }
        this.mStartMotionPath.setBounds((float) r.left, (float) r.top, (float) r.width(), (float) r.height());
        this.mStartPoint.setState(r, v, rotation, rect.rotation);
    }

    /* access modifiers changed from: package-private */
    public void rotate(Rect rect, Rect out, int rotation, int preHeight, int preWidth) {
        switch (rotation) {
            case 1:
                int cx = rect.left + rect.right;
                out.left = ((rect.top + rect.bottom) - rect.width()) / 2;
                out.top = preWidth - ((rect.height() + cx) / 2);
                out.right = out.left + rect.width();
                out.bottom = out.top + rect.height();
                return;
            case 2:
                int cx2 = rect.left + rect.right;
                out.left = preHeight - ((rect.width() + (rect.top + rect.bottom)) / 2);
                out.top = (cx2 - rect.height()) / 2;
                out.right = out.left + rect.width();
                out.bottom = out.top + rect.height();
                return;
            case 3:
                int cx3 = rect.left + rect.right;
                int i = rect.top + rect.bottom;
                out.left = ((rect.height() / 2) + rect.top) - (cx3 / 2);
                out.top = preWidth - ((rect.height() + cx3) / 2);
                out.right = out.left + rect.width();
                out.bottom = out.top + rect.height();
                return;
            case 4:
                int cx4 = rect.left + rect.right;
                out.left = preHeight - ((rect.width() + (rect.bottom + rect.top)) / 2);
                out.top = (cx4 - rect.height()) / 2;
                out.right = out.left + rect.width();
                out.bottom = out.top + rect.height();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void setStartState(Rect cw, ConstraintSet constraintSet, int parentWidth, int parentHeight) {
        int rotate = constraintSet.mRotate;
        if (rotate != 0) {
            rotate(cw, this.mTempRect, rotate, parentWidth, parentHeight);
        }
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        readView(this.mStartMotionPath);
        this.mStartMotionPath.setBounds((float) cw.left, (float) cw.top, (float) cw.width(), (float) cw.height());
        ConstraintSet.Constraint constraint = constraintSet.getParameters(this.mId);
        this.mStartMotionPath.applyParameters(constraint);
        this.mMotionStagger = constraint.motion.mMotionStagger;
        this.mStartPoint.setState(cw, constraintSet, rotate, this.mId);
        this.mTransformPivotTarget = constraint.transform.transformPivotTarget;
        this.mQuantizeMotionSteps = constraint.motion.mQuantizeMotionSteps;
        this.mQuantizeMotionPhase = constraint.motion.mQuantizeMotionPhase;
        this.mQuantizeMotionInterpolator = getInterpolator(this.mView.getContext(), constraint.motion.mQuantizeInterpolatorType, constraint.motion.mQuantizeInterpolatorString, constraint.motion.mQuantizeInterpolatorID);
    }

    private static Interpolator getInterpolator(Context context, int type, String interpolatorString, int id) {
        switch (type) {
            case -2:
                return AnimationUtils.loadInterpolator(context, id);
            case -1:
                final Easing easing = Easing.getInterpolator(interpolatorString);
                return new Interpolator() {
                    public float getInterpolation(float v) {
                        return (float) Easing.this.get((double) v);
                    }
                };
            case 0:
                return new AccelerateDecelerateInterpolator();
            case 1:
                return new AccelerateInterpolator();
            case 2:
                return new DecelerateInterpolator();
            case 3:
                return null;
            case 4:
                return new BounceInterpolator();
            case 5:
                return new OvershootInterpolator();
            default:
                return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void setEndState(Rect cw, ConstraintSet constraintSet, int parentWidth, int parentHeight) {
        int rotate = constraintSet.mRotate;
        if (rotate != 0) {
            rotate(cw, this.mTempRect, rotate, parentWidth, parentHeight);
            cw = this.mTempRect;
        }
        this.mEndMotionPath.time = 1.0f;
        this.mEndMotionPath.position = 1.0f;
        readView(this.mEndMotionPath);
        this.mEndMotionPath.setBounds((float) cw.left, (float) cw.top, (float) cw.width(), (float) cw.height());
        this.mEndMotionPath.applyParameters(constraintSet.getParameters(this.mId));
        this.mEndPoint.setState(cw, constraintSet, rotate, this.mId);
    }

    /* access modifiers changed from: package-private */
    public void setBothStates(View v) {
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        this.mNoMovement = true;
        this.mStartMotionPath.setBounds(v.getX(), v.getY(), (float) v.getWidth(), (float) v.getHeight());
        this.mEndMotionPath.setBounds(v.getX(), v.getY(), (float) v.getWidth(), (float) v.getHeight());
        this.mStartPoint.setState(v);
        this.mEndPoint.setState(v);
    }

    private float getAdjustedPosition(float position, float[] velocity) {
        if (velocity != null) {
            velocity[0] = 1.0f;
        } else {
            float f = this.mStaggerScale;
            if (((double) f) != 1.0d) {
                float f2 = this.mStaggerOffset;
                if (position < f2) {
                    position = 0.0f;
                }
                if (position > f2 && ((double) position) < 1.0d) {
                    position = Math.min((position - f2) * f, 1.0f);
                }
            }
        }
        float adjusted = position;
        Easing easing = this.mStartMotionPath.mKeyFrameEasing;
        float start = 0.0f;
        float end = Float.NaN;
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            MotionPaths frame = it.next();
            if (frame.mKeyFrameEasing != null) {
                if (frame.time < position) {
                    easing = frame.mKeyFrameEasing;
                    start = frame.time;
                } else if (Float.isNaN(end)) {
                    end = frame.time;
                }
            }
        }
        if (easing != null) {
            if (Float.isNaN(end)) {
                end = 1.0f;
            }
            float offset = (position - start) / (end - start);
            adjusted = ((end - start) * ((float) easing.get((double) offset))) + start;
            if (velocity != null) {
                velocity[0] = (float) easing.getDiff((double) offset);
            }
        }
        return adjusted;
    }

    /* access modifiers changed from: package-private */
    public void endTrigger(boolean start) {
        if ("button".equals(Debug.getName(this.mView)) && this.mKeyTriggers != null) {
            int i = 0;
            while (true) {
                KeyTrigger[] keyTriggerArr = this.mKeyTriggers;
                if (i < keyTriggerArr.length) {
                    keyTriggerArr[i].conditionallyFire(start ? -100.0f : 100.0f, this.mView);
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean interpolate(View child, float global_position, long time, KeyCache keyCache) {
        float position;
        ViewTimeCycle.PathRotate timePathRotate;
        boolean timeAnimation;
        char c;
        float section;
        View view = child;
        float position2 = getAdjustedPosition(global_position, (float[]) null);
        if (this.mQuantizeMotionSteps != Key.UNSET) {
            float f = position2;
            float steps = 1.0f / ((float) this.mQuantizeMotionSteps);
            float jump = ((float) Math.floor((double) (position2 / steps))) * steps;
            float section2 = (position2 % steps) / steps;
            if (!Float.isNaN(this.mQuantizeMotionPhase)) {
                section2 = (this.mQuantizeMotionPhase + section2) % 1.0f;
            }
            Interpolator interpolator = this.mQuantizeMotionInterpolator;
            if (interpolator != null) {
                section = interpolator.getInterpolation(section2);
            } else {
                section = ((double) section2) > 0.5d ? 1.0f : 0.0f;
            }
            position = (section * steps) + jump;
        } else {
            position = position2;
        }
        HashMap<String, ViewSpline> hashMap = this.mAttributesMap;
        if (hashMap != null) {
            for (ViewSpline aSpline : hashMap.values()) {
                aSpline.setProperty(view, position);
            }
        }
        HashMap<String, ViewTimeCycle> hashMap2 = this.mTimeCycleAttributesMap;
        if (hashMap2 != null) {
            boolean timeAnimation2 = false;
            ViewTimeCycle.PathRotate timePathRotate2 = null;
            for (ViewTimeCycle aSpline2 : hashMap2.values()) {
                if (aSpline2 instanceof ViewTimeCycle.PathRotate) {
                    timePathRotate2 = aSpline2;
                } else {
                    timeAnimation2 |= aSpline2.setProperty(child, position, time, keyCache);
                }
            }
            timeAnimation = timeAnimation2;
            timePathRotate = timePathRotate2;
        } else {
            timeAnimation = false;
            timePathRotate = null;
        }
        CurveFit[] curveFitArr = this.mSpline;
        if (curveFitArr != null) {
            curveFitArr[0].getPos((double) position, this.mInterpolateData);
            this.mSpline[0].getSlope((double) position, this.mInterpolateVelocity);
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr = this.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos((double) position, dArr);
                    this.mArcSpline.getSlope((double) position, this.mInterpolateVelocity);
                }
            }
            if (!this.mNoMovement) {
                this.mStartMotionPath.setView(position, child, this.mInterpolateVariables, this.mInterpolateData, this.mInterpolateVelocity, (double[]) null, this.mForceMeasure);
                this.mForceMeasure = false;
            }
            if (this.mTransformPivotTarget != Key.UNSET) {
                if (this.mTransformPivotView == null) {
                    this.mTransformPivotView = ((View) child.getParent()).findViewById(this.mTransformPivotTarget);
                }
                View layout = this.mTransformPivotView;
                if (layout != null) {
                    float cy = ((float) (layout.getTop() + this.mTransformPivotView.getBottom())) / 2.0f;
                    float cx = ((float) (this.mTransformPivotView.getLeft() + this.mTransformPivotView.getRight())) / 2.0f;
                    if (child.getRight() - child.getLeft() > 0 && child.getBottom() - child.getTop() > 0) {
                        view.setPivotX(cx - ((float) child.getLeft()));
                        view.setPivotY(cy - ((float) child.getTop()));
                    }
                }
            }
            HashMap<String, ViewSpline> hashMap3 = this.mAttributesMap;
            if (hashMap3 != null) {
                for (SplineSet aSpline3 : hashMap3.values()) {
                    if (aSpline3 instanceof ViewSpline.PathRotate) {
                        double[] dArr2 = this.mInterpolateVelocity;
                        if (dArr2.length > 1) {
                            SplineSet splineSet = aSpline3;
                            ((ViewSpline.PathRotate) aSpline3).setPathRotate(child, position, dArr2[0], dArr2[1]);
                        }
                    }
                }
            }
            if (timePathRotate != null) {
                double[] dArr3 = this.mInterpolateVelocity;
                c = 1;
                timeAnimation |= timePathRotate.setPathRotate(child, keyCache, position, time, dArr3[0], dArr3[1]);
            } else {
                c = 1;
            }
            int i = 1;
            while (true) {
                CurveFit[] curveFitArr2 = this.mSpline;
                if (i >= curveFitArr2.length) {
                    break;
                }
                curveFitArr2[i].getPos((double) position, this.mValuesBuff);
                CustomSupport.setInterpolatedValue(this.mStartMotionPath.attributes.get(this.mAttributeNames[i - 1]), view, this.mValuesBuff);
                i++;
            }
            if (this.mStartPoint.mVisibilityMode == 0) {
                if (position <= 0.0f) {
                    view.setVisibility(this.mStartPoint.visibility);
                } else if (position >= 1.0f) {
                    view.setVisibility(this.mEndPoint.visibility);
                } else if (this.mEndPoint.visibility != this.mStartPoint.visibility) {
                    view.setVisibility(0);
                }
            }
            if (this.mKeyTriggers != null) {
                int i2 = 0;
                while (true) {
                    KeyTrigger[] keyTriggerArr = this.mKeyTriggers;
                    if (i2 >= keyTriggerArr.length) {
                        break;
                    }
                    keyTriggerArr[i2].conditionallyFire(position, view);
                    i2++;
                }
            }
        } else {
            c = 1;
            float float_l = this.mStartMotionPath.f371x + ((this.mEndMotionPath.f371x - this.mStartMotionPath.f371x) * position);
            float float_t = this.mStartMotionPath.f372y + ((this.mEndMotionPath.f372y - this.mStartMotionPath.f372y) * position);
            int l = (int) (float_l + 0.5f);
            int t = (int) (float_t + 0.5f);
            int r = (int) (float_l + 0.5f + this.mStartMotionPath.width + ((this.mEndMotionPath.width - this.mStartMotionPath.width) * position));
            int b = (int) (0.5f + float_t + this.mStartMotionPath.height + ((this.mEndMotionPath.height - this.mStartMotionPath.height) * position));
            int width = r - l;
            int height = b - t;
            if (this.mEndMotionPath.width == this.mStartMotionPath.width && this.mEndMotionPath.height == this.mStartMotionPath.height && !this.mForceMeasure) {
                float f2 = float_l;
            } else {
                view.measure(View.MeasureSpec.makeMeasureSpec(width, 1073741824), View.MeasureSpec.makeMeasureSpec(height, 1073741824));
                float f3 = float_l;
                this.mForceMeasure = false;
            }
            view.layout(l, t, r, b);
        }
        HashMap<String, ViewOscillator> hashMap4 = this.mCycleMap;
        if (hashMap4 != null) {
            for (ViewOscillator osc : hashMap4.values()) {
                if (osc instanceof ViewOscillator.PathRotateSet) {
                    double[] dArr4 = this.mInterpolateVelocity;
                    ((ViewOscillator.PathRotateSet) osc).setPathRotate(child, position, dArr4[0], dArr4[c]);
                } else {
                    osc.setProperty(view, position);
                }
            }
        }
        return timeAnimation;
    }

    /* access modifiers changed from: package-private */
    public void getDpDt(float position, float locationX, float locationY, float[] mAnchorDpDt) {
        double[] dArr;
        float f = position;
        float position2 = getAdjustedPosition(position, this.mVelocity);
        CurveFit[] curveFitArr = this.mSpline;
        if (curveFitArr != null) {
            curveFitArr[0].getSlope((double) position2, this.mInterpolateVelocity);
            this.mSpline[0].getPos((double) position2, this.mInterpolateData);
            float v = this.mVelocity[0];
            int i = 0;
            while (true) {
                dArr = this.mInterpolateVelocity;
                if (i >= dArr.length) {
                    break;
                }
                dArr[i] = dArr[i] * ((double) v);
                i++;
            }
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr2 = this.mInterpolateData;
                if (dArr2.length > 0) {
                    curveFit.getPos((double) position2, dArr2);
                    this.mArcSpline.getSlope((double) position2, this.mInterpolateVelocity);
                    this.mStartMotionPath.setDpDt(locationX, locationY, mAnchorDpDt, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
                    return;
                }
                return;
            }
            this.mStartMotionPath.setDpDt(locationX, locationY, mAnchorDpDt, this.mInterpolateVariables, dArr, this.mInterpolateData);
            return;
        }
        float dleft = this.mEndMotionPath.f371x - this.mStartMotionPath.f371x;
        float dTop = this.mEndMotionPath.f372y - this.mStartMotionPath.f372y;
        float dWidth = this.mEndMotionPath.width - this.mStartMotionPath.width;
        float dHeight = this.mEndMotionPath.height - this.mStartMotionPath.height;
        mAnchorDpDt[0] = ((1.0f - locationX) * dleft) + ((dleft + dWidth) * locationX);
        mAnchorDpDt[1] = ((1.0f - locationY) * dTop) + ((dTop + dHeight) * locationY);
    }

    /* access modifiers changed from: package-private */
    public void getPostLayoutDvDp(float position, int width, int height, float locationX, float locationY, float[] mAnchorDpDt) {
        VelocityMatrix vmat;
        float position2 = getAdjustedPosition(position, this.mVelocity);
        HashMap<String, ViewSpline> hashMap = this.mAttributesMap;
        ViewOscillator viewOscillator = null;
        SplineSet trans_x = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, ViewSpline> hashMap2 = this.mAttributesMap;
        SplineSet trans_y = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, ViewSpline> hashMap3 = this.mAttributesMap;
        SplineSet rotation = hashMap3 == null ? null : hashMap3.get(Key.ROTATION);
        HashMap<String, ViewSpline> hashMap4 = this.mAttributesMap;
        SplineSet scale_x = hashMap4 == null ? null : hashMap4.get("scaleX");
        HashMap<String, ViewSpline> hashMap5 = this.mAttributesMap;
        SplineSet scale_y = hashMap5 == null ? null : hashMap5.get("scaleY");
        HashMap<String, ViewOscillator> hashMap6 = this.mCycleMap;
        ViewOscillator osc_x = hashMap6 == null ? null : hashMap6.get("translationX");
        HashMap<String, ViewOscillator> hashMap7 = this.mCycleMap;
        ViewOscillator osc_y = hashMap7 == null ? null : hashMap7.get("translationY");
        HashMap<String, ViewOscillator> hashMap8 = this.mCycleMap;
        ViewOscillator osc_r = hashMap8 == null ? null : hashMap8.get(Key.ROTATION);
        HashMap<String, ViewOscillator> hashMap9 = this.mCycleMap;
        ViewOscillator osc_sx = hashMap9 == null ? null : hashMap9.get("scaleX");
        HashMap<String, ViewOscillator> hashMap10 = this.mCycleMap;
        if (hashMap10 != null) {
            viewOscillator = hashMap10.get("scaleY");
        }
        ViewOscillator osc_sy = viewOscillator;
        VelocityMatrix vmat2 = new VelocityMatrix();
        vmat2.clear();
        vmat2.setRotationVelocity(rotation, position2);
        vmat2.setTranslationVelocity(trans_x, trans_y, position2);
        vmat2.setScaleVelocity(scale_x, scale_y, position2);
        vmat2.setRotationVelocity((KeyCycleOscillator) osc_r, position2);
        vmat2.setTranslationVelocity((KeyCycleOscillator) osc_x, (KeyCycleOscillator) osc_y, position2);
        vmat2.setScaleVelocity((KeyCycleOscillator) osc_sx, (KeyCycleOscillator) osc_sy, position2);
        CurveFit curveFit = this.mArcSpline;
        if (curveFit != null) {
            double[] dArr = this.mInterpolateData;
            if (dArr.length > 0) {
                curveFit.getPos((double) position2, dArr);
                this.mArcSpline.getSlope((double) position2, this.mInterpolateVelocity);
                vmat = vmat2;
                ViewOscillator viewOscillator2 = osc_x;
                ViewOscillator osc_x2 = osc_r;
                ViewOscillator viewOscillator3 = osc_sx;
                ViewOscillator viewOscillator4 = osc_sy;
                this.mStartMotionPath.setDpDt(locationX, locationY, mAnchorDpDt, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
            } else {
                vmat = vmat2;
                ViewOscillator viewOscillator5 = osc_sx;
                ViewOscillator viewOscillator6 = osc_sy;
                ViewOscillator viewOscillator7 = osc_x;
                ViewOscillator osc_x3 = osc_r;
            }
            vmat.applyTransform(locationX, locationY, width, height, mAnchorDpDt);
            return;
        }
        VelocityMatrix vmat3 = vmat2;
        ViewOscillator osc_sx2 = osc_sx;
        ViewOscillator osc_sy2 = osc_sy;
        ViewOscillator osc_x4 = osc_x;
        ViewOscillator osc_x5 = osc_r;
        if (this.mSpline != null) {
            float position3 = getAdjustedPosition(position2, this.mVelocity);
            this.mSpline[0].getSlope((double) position3, this.mInterpolateVelocity);
            this.mSpline[0].getPos((double) position3, this.mInterpolateData);
            float v = this.mVelocity[0];
            int i = 0;
            while (true) {
                double[] dArr2 = this.mInterpolateVelocity;
                if (i < dArr2.length) {
                    dArr2[i] = dArr2[i] * ((double) v);
                    i++;
                } else {
                    float f = locationX;
                    float f2 = locationY;
                    float f3 = v;
                    this.mStartMotionPath.setDpDt(f, f2, mAnchorDpDt, this.mInterpolateVariables, dArr2, this.mInterpolateData);
                    vmat3.applyTransform(f, f2, width, height, mAnchorDpDt);
                    return;
                }
            }
        } else {
            float dleft = this.mEndMotionPath.f371x - this.mStartMotionPath.f371x;
            float dTop = this.mEndMotionPath.f372y - this.mStartMotionPath.f372y;
            mAnchorDpDt[0] = ((1.0f - locationX) * dleft) + ((dleft + (this.mEndMotionPath.width - this.mStartMotionPath.width)) * locationX);
            mAnchorDpDt[1] = ((1.0f - locationY) * dTop) + ((dTop + (this.mEndMotionPath.height - this.mStartMotionPath.height)) * locationY);
            vmat3.clear();
            VelocityMatrix vmat4 = vmat3;
            vmat4.setRotationVelocity(rotation, position2);
            vmat4.setTranslationVelocity(trans_x, trans_y, position2);
            vmat4.setScaleVelocity(scale_x, scale_y, position2);
            vmat4.setRotationVelocity((KeyCycleOscillator) osc_x5, position2);
            ViewOscillator osc_x6 = osc_x4;
            vmat4.setTranslationVelocity((KeyCycleOscillator) osc_x6, (KeyCycleOscillator) osc_y, position2);
            ViewOscillator osc_sy3 = osc_sy2;
            vmat4.setScaleVelocity((KeyCycleOscillator) osc_sx2, (KeyCycleOscillator) osc_sy3, position2);
            ViewOscillator viewOscillator8 = osc_sy3;
            ViewOscillator viewOscillator9 = osc_x6;
            VelocityMatrix velocityMatrix = vmat4;
            vmat4.applyTransform(locationX, locationY, width, height, mAnchorDpDt);
        }
    }

    public int getDrawPath() {
        int mode = this.mStartMotionPath.mDrawPath;
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            mode = Math.max(mode, it.next().mDrawPath);
        }
        return Math.max(mode, this.mEndMotionPath.mDrawPath);
    }

    public void setDrawPath(int debugMode) {
        this.mStartMotionPath.mDrawPath = debugMode;
    }

    /* access modifiers changed from: package-private */
    public String name() {
        return this.mView.getContext().getResources().getResourceEntryName(this.mView.getId());
    }

    /* access modifiers changed from: package-private */
    public void positionKeyframe(View view, KeyPositionBase key, float x, float y, String[] attribute, float[] value) {
        RectF start = new RectF();
        start.left = this.mStartMotionPath.f371x;
        start.top = this.mStartMotionPath.f372y;
        start.right = start.left + this.mStartMotionPath.width;
        start.bottom = start.top + this.mStartMotionPath.height;
        RectF end = new RectF();
        end.left = this.mEndMotionPath.f371x;
        end.top = this.mEndMotionPath.f372y;
        end.right = end.left + this.mEndMotionPath.width;
        end.bottom = end.top + this.mEndMotionPath.height;
        key.positionAttributes(view, start, end, x, y, attribute, value);
    }

    public int getKeyFramePositions(int[] type, float[] pos) {
        int i = 0;
        int count = 0;
        Iterator<Key> it = this.mKeyList.iterator();
        while (it.hasNext()) {
            Key key = it.next();
            int i2 = i + 1;
            type[i] = key.mFramePosition + (key.mType * 1000);
            float time = ((float) key.mFramePosition) / 100.0f;
            this.mSpline[0].getPos((double) time, this.mInterpolateData);
            this.mStartMotionPath.getCenter((double) time, this.mInterpolateVariables, this.mInterpolateData, pos, count);
            count += 2;
            i = i2;
        }
        return i;
    }

    public int getKeyFrameInfo(int type, int[] info) {
        int i = type;
        int count = 0;
        int cursor = 0;
        float[] pos = new float[2];
        Iterator<Key> it = this.mKeyList.iterator();
        while (it.hasNext()) {
            Key key = it.next();
            if (key.mType == i || i != -1) {
                int len = cursor;
                info[cursor] = 0;
                int cursor2 = cursor + 1;
                info[cursor2] = key.mType;
                int cursor3 = cursor2 + 1;
                info[cursor3] = key.mFramePosition;
                float time = ((float) key.mFramePosition) / 100.0f;
                this.mSpline[0].getPos((double) time, this.mInterpolateData);
                float f = time;
                this.mStartMotionPath.getCenter((double) time, this.mInterpolateVariables, this.mInterpolateData, pos, 0);
                int cursor4 = cursor3 + 1;
                info[cursor4] = Float.floatToIntBits(pos[0]);
                int cursor5 = cursor4 + 1;
                info[cursor5] = Float.floatToIntBits(pos[1]);
                if (key instanceof KeyPosition) {
                    KeyPosition kp = (KeyPosition) key;
                    int cursor6 = cursor5 + 1;
                    info[cursor6] = kp.mPositionType;
                    int cursor7 = cursor6 + 1;
                    info[cursor7] = Float.floatToIntBits(kp.mPercentX);
                    cursor5 = cursor7 + 1;
                    info[cursor5] = Float.floatToIntBits(kp.mPercentY);
                }
                cursor = cursor5 + 1;
                info[len] = cursor - len;
                count++;
            }
        }
        return count;
    }
}
