package androidx.constraintlayout.motion.widget;

import android.view.View;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.Arrays;
import java.util.LinkedHashMap;

class MotionPaths implements Comparable<MotionPaths> {
    static final int CARTESIAN = 0;
    public static final boolean DEBUG = false;
    static final int OFF_HEIGHT = 4;
    static final int OFF_PATH_ROTATE = 5;
    static final int OFF_POSITION = 0;
    static final int OFF_WIDTH = 3;
    static final int OFF_X = 1;
    static final int OFF_Y = 2;
    public static final boolean OLD_WAY = false;
    static final int PERPENDICULAR = 1;
    static final int SCREEN = 2;
    public static final String TAG = "MotionPaths";
    static String[] names = {"position", "x", "y", "width", "height", "pathRotate"};
    LinkedHashMap<String, ConstraintAttribute> attributes = new LinkedHashMap<>();
    float height;
    int mAnimateCircleAngleTo;
    int mAnimateRelativeTo = Key.UNSET;
    int mDrawPath = 0;
    Easing mKeyFrameEasing;
    int mMode = 0;
    int mPathMotionArc = Key.UNSET;
    float mPathRotate = Float.NaN;
    float mProgress = Float.NaN;
    float mRelativeAngle = Float.NaN;
    MotionController mRelativeToController = null;
    double[] mTempDelta = new double[18];
    double[] mTempValue = new double[18];
    float position;
    float time;
    float width;

    /* renamed from: x */
    float f371x;

    /* renamed from: y */
    float f372y;

    public MotionPaths() {
    }

    /* access modifiers changed from: package-private */
    public void initCartesian(KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        KeyPosition keyPosition = c;
        MotionPaths motionPaths = startTimePoint;
        MotionPaths motionPaths2 = endTimePoint;
        float position2 = ((float) keyPosition.mFramePosition) / 100.0f;
        this.time = position2;
        this.mDrawPath = keyPosition.mDrawPath;
        float scaleWidth = Float.isNaN(keyPosition.mPercentWidth) ? position2 : keyPosition.mPercentWidth;
        float scaleHeight = Float.isNaN(keyPosition.mPercentHeight) ? position2 : keyPosition.mPercentHeight;
        float f = motionPaths2.width;
        float f2 = motionPaths.width;
        float scaleX = f - f2;
        float f3 = motionPaths2.height;
        float f4 = motionPaths.height;
        float scaleY = f3 - f4;
        this.position = this.time;
        float path = position2;
        float f5 = motionPaths.f371x;
        float position3 = position2;
        float position4 = motionPaths.f372y;
        float endCenterX = motionPaths2.f371x + (f / 2.0f);
        float endCenterY = motionPaths2.f372y + (f3 / 2.0f);
        float pathVectorX = endCenterX - (f5 + (f2 / 2.0f));
        float pathVectorY = endCenterY - (position4 + (f4 / 2.0f));
        this.f371x = (float) ((int) ((f5 + (pathVectorX * path)) - ((scaleX * scaleWidth) / 2.0f)));
        this.f372y = (float) ((int) ((position4 + (pathVectorY * path)) - ((scaleY * scaleHeight) / 2.0f)));
        this.width = (float) ((int) (f2 + (scaleX * scaleWidth)));
        this.height = (float) ((int) (f4 + (scaleY * scaleHeight)));
        float dxdx = Float.isNaN(keyPosition.mPercentX) ? position3 : keyPosition.mPercentX;
        float dydx = Float.isNaN(keyPosition.mAltPercentY) ? 0.0f : keyPosition.mAltPercentY;
        float dydy = Float.isNaN(keyPosition.mPercentY) ? position3 : keyPosition.mPercentY;
        float dxdy = Float.isNaN(keyPosition.mAltPercentX) ? 0.0f : keyPosition.mAltPercentX;
        this.mMode = 0;
        this.f371x = (float) ((int) (((motionPaths.f371x + (pathVectorX * dxdx)) + (pathVectorY * dxdy)) - ((scaleX * scaleWidth) / 2.0f)));
        this.f372y = (float) ((int) (((motionPaths.f372y + (pathVectorX * dydx)) + (pathVectorY * dydy)) - ((scaleY * scaleHeight) / 2.0f)));
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition.mTransitionEasing);
        this.mPathMotionArc = keyPosition.mPathMotionArc;
    }

    public MotionPaths(int parentWidth, int parentHeight, KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        if (startTimePoint.mAnimateRelativeTo != Key.UNSET) {
            initPolar(parentWidth, parentHeight, c, startTimePoint, endTimePoint);
            return;
        }
        switch (c.mPositionType) {
            case 1:
                initPath(c, startTimePoint, endTimePoint);
                return;
            case 2:
                initScreen(parentWidth, parentHeight, c, startTimePoint, endTimePoint);
                return;
            default:
                initCartesian(c, startTimePoint, endTimePoint);
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void initPolar(int parentWidth, int parentHeight, KeyPosition c, MotionPaths s, MotionPaths e) {
        float f;
        float f2;
        float f3;
        float position2 = ((float) c.mFramePosition) / 100.0f;
        this.time = position2;
        this.mDrawPath = c.mDrawPath;
        this.mMode = c.mPositionType;
        float scaleWidth = Float.isNaN(c.mPercentWidth) ? position2 : c.mPercentWidth;
        float scaleHeight = Float.isNaN(c.mPercentHeight) ? position2 : c.mPercentHeight;
        float f4 = e.width;
        float f5 = s.width;
        float f6 = e.height;
        float f7 = s.height;
        this.position = this.time;
        this.width = (float) ((int) (f5 + ((f4 - f5) * scaleWidth)));
        this.height = (float) ((int) (f7 + ((f6 - f7) * scaleHeight)));
        float f8 = 1.0f - position2;
        float f9 = position2;
        switch (c.mPositionType) {
            case 1:
                float f10 = Float.isNaN(c.mPercentX) ? position2 : c.mPercentX;
                float f11 = e.f371x;
                float f12 = s.f371x;
                this.f371x = (f10 * (f11 - f12)) + f12;
                float f13 = Float.isNaN(c.mPercentY) ? position2 : c.mPercentY;
                float f14 = e.f372y;
                float f15 = s.f372y;
                this.f372y = (f13 * (f14 - f15)) + f15;
                break;
            case 2:
                if (Float.isNaN(c.mPercentX)) {
                    float f16 = e.f371x;
                    float f17 = s.f371x;
                    f = ((f16 - f17) * position2) + f17;
                } else {
                    f = c.mPercentX * Math.min(scaleHeight, scaleWidth);
                }
                this.f371x = f;
                if (Float.isNaN(c.mPercentY)) {
                    float f18 = e.f372y;
                    float f19 = s.f372y;
                    f2 = ((f18 - f19) * position2) + f19;
                } else {
                    f2 = c.mPercentY;
                }
                this.f372y = f2;
                break;
            default:
                if (Float.isNaN(c.mPercentX)) {
                    f3 = position2;
                } else {
                    f3 = c.mPercentX;
                }
                float f20 = e.f371x;
                float f21 = s.f371x;
                this.f371x = (f3 * (f20 - f21)) + f21;
                float f22 = Float.isNaN(c.mPercentY) ? position2 : c.mPercentY;
                float f23 = e.f372y;
                float f24 = s.f372y;
                this.f372y = (f22 * (f23 - f24)) + f24;
                break;
        }
        this.mAnimateRelativeTo = s.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(c.mTransitionEasing);
        this.mPathMotionArc = c.mPathMotionArc;
    }

    public void setupRelative(MotionController mc, MotionPaths relative) {
        double dx = (double) (((this.f371x + (this.width / 2.0f)) - relative.f371x) - (relative.width / 2.0f));
        double dy = (double) (((this.f372y + (this.height / 2.0f)) - relative.f372y) - (relative.height / 2.0f));
        this.mRelativeToController = mc;
        this.f371x = (float) Math.hypot(dy, dx);
        if (Float.isNaN(this.mRelativeAngle)) {
            this.f372y = (float) (Math.atan2(dy, dx) + 1.5707963267948966d);
        } else {
            this.f372y = (float) Math.toRadians((double) this.mRelativeAngle);
        }
    }

    /* access modifiers changed from: package-private */
    public void initScreen(int parentWidth, int parentHeight, KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        KeyPosition keyPosition = c;
        MotionPaths motionPaths = startTimePoint;
        MotionPaths motionPaths2 = endTimePoint;
        float position2 = ((float) keyPosition.mFramePosition) / 100.0f;
        this.time = position2;
        this.mDrawPath = keyPosition.mDrawPath;
        float scaleWidth = Float.isNaN(keyPosition.mPercentWidth) ? position2 : keyPosition.mPercentWidth;
        float scaleHeight = Float.isNaN(keyPosition.mPercentHeight) ? position2 : keyPosition.mPercentHeight;
        float f = motionPaths2.width;
        float f2 = motionPaths.width;
        float scaleX = f - f2;
        float f3 = motionPaths2.height;
        float f4 = motionPaths.height;
        float scaleY = f3 - f4;
        this.position = this.time;
        float path = position2;
        float f5 = motionPaths.f371x;
        float f6 = position2;
        float position3 = motionPaths.f372y;
        float endCenterX = motionPaths2.f371x + (f / 2.0f);
        float endCenterY = motionPaths2.f372y + (f3 / 2.0f);
        this.f371x = (float) ((int) ((f5 + ((endCenterX - (f5 + (f2 / 2.0f))) * path)) - ((scaleX * scaleWidth) / 2.0f)));
        this.f372y = (float) ((int) ((position3 + ((endCenterY - (position3 + (f4 / 2.0f))) * path)) - ((scaleY * scaleHeight) / 2.0f)));
        this.width = (float) ((int) (f2 + (scaleX * scaleWidth)));
        this.height = (float) ((int) (f4 + (scaleY * scaleHeight)));
        this.mMode = 2;
        if (!Float.isNaN(keyPosition.mPercentX)) {
            this.f371x = (float) ((int) (keyPosition.mPercentX * ((float) ((int) (((float) parentWidth) - this.width)))));
        }
        if (!Float.isNaN(keyPosition.mPercentY)) {
            this.f372y = (float) ((int) (keyPosition.mPercentY * ((float) ((int) (((float) parentHeight) - this.height)))));
        }
        this.mAnimateRelativeTo = this.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition.mTransitionEasing);
        this.mPathMotionArc = keyPosition.mPathMotionArc;
    }

    /* access modifiers changed from: package-private */
    public void initPath(KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        KeyPosition keyPosition = c;
        MotionPaths motionPaths = startTimePoint;
        MotionPaths motionPaths2 = endTimePoint;
        float position2 = ((float) keyPosition.mFramePosition) / 100.0f;
        this.time = position2;
        this.mDrawPath = keyPosition.mDrawPath;
        float scaleWidth = Float.isNaN(keyPosition.mPercentWidth) ? position2 : keyPosition.mPercentWidth;
        float scaleHeight = Float.isNaN(keyPosition.mPercentHeight) ? position2 : keyPosition.mPercentHeight;
        float scaleX = motionPaths2.width - motionPaths.width;
        float scaleY = motionPaths2.height - motionPaths.height;
        this.position = this.time;
        float path = Float.isNaN(keyPosition.mPercentX) ? position2 : keyPosition.mPercentX;
        float f = motionPaths.f371x;
        float f2 = motionPaths.width;
        float f3 = motionPaths.f372y;
        float f4 = position2;
        float position3 = motionPaths.height;
        float endCenterX = motionPaths2.f371x + (motionPaths2.width / 2.0f);
        float endCenterY = motionPaths2.f372y + (motionPaths2.height / 2.0f);
        float pathVectorX = endCenterX - ((f2 / 2.0f) + f);
        float pathVectorY = endCenterY - (f3 + (position3 / 2.0f));
        this.f371x = (float) ((int) ((f + (pathVectorX * path)) - ((scaleX * scaleWidth) / 2.0f)));
        this.f372y = (float) ((int) ((f3 + (pathVectorY * path)) - ((scaleY * scaleHeight) / 2.0f)));
        this.width = (float) ((int) (f2 + (scaleX * scaleWidth)));
        this.height = (float) ((int) (position3 + (scaleY * scaleHeight)));
        KeyPosition keyPosition2 = c;
        float perpendicular = Float.isNaN(keyPosition2.mPercentY) ? 0.0f : keyPosition2.mPercentY;
        float f5 = endCenterX;
        this.mMode = 1;
        MotionPaths motionPaths3 = startTimePoint;
        float f6 = endCenterY;
        float f7 = (float) ((int) ((motionPaths3.f371x + (pathVectorX * path)) - ((scaleX * scaleWidth) / 2.0f)));
        this.f371x = f7;
        float f8 = pathVectorX;
        float f9 = (float) ((int) ((motionPaths3.f372y + (pathVectorY * path)) - ((scaleY * scaleHeight) / 2.0f)));
        this.f372y = f9;
        this.f371x = f7 + ((-pathVectorY) * perpendicular);
        this.f372y = f9 + (pathVectorX * perpendicular);
        this.mAnimateRelativeTo = this.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition2.mTransitionEasing);
        this.mPathMotionArc = keyPosition2.mPathMotionArc;
    }

    private static final float xRotate(float sin, float cos, float cx, float cy, float x, float y) {
        return (((x - cx) * cos) - ((y - cy) * sin)) + cx;
    }

    private static final float yRotate(float sin, float cos, float cx, float cy, float x, float y) {
        return ((x - cx) * sin) + ((y - cy) * cos) + cy;
    }

    private boolean diff(float a, float b) {
        if (Float.isNaN(a) || Float.isNaN(b)) {
            if (Float.isNaN(a) != Float.isNaN(b)) {
                return true;
            }
            return false;
        } else if (Math.abs(a - b) > 1.0E-6f) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void different(MotionPaths points, boolean[] mask, String[] custom, boolean arcMode) {
        boolean diffx = diff(this.f371x, points.f371x);
        boolean diffy = diff(this.f372y, points.f372y);
        int c = 0 + 1;
        mask[0] = mask[0] | diff(this.position, points.position);
        int c2 = c + 1;
        mask[c] = mask[c] | diffx | diffy | arcMode;
        int c3 = c2 + 1;
        mask[c2] = mask[c2] | diffx | diffy | arcMode;
        int c4 = c3 + 1;
        mask[c3] = mask[c3] | diff(this.width, points.width);
        int i = c4 + 1;
        mask[c4] = mask[c4] | diff(this.height, points.height);
    }

    /* access modifiers changed from: package-private */
    public void getCenter(double p, int[] toUse, double[] data, float[] point, int offset) {
        float f;
        int[] iArr = toUse;
        float v_x = this.f371x;
        float v_y = this.f372y;
        float v_width = this.width;
        float v_height = this.height;
        for (int i = 0; i < iArr.length; i++) {
            float value = (float) data[i];
            switch (iArr[i]) {
                case 1:
                    v_x = value;
                    break;
                case 2:
                    v_y = value;
                    break;
                case 3:
                    v_width = value;
                    break;
                case 4:
                    v_height = value;
                    break;
            }
        }
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] pos = new float[2];
            float[] vel = new float[2];
            motionController.getCenter(p, pos, vel);
            float rx = pos[0];
            float ry = pos[1];
            float radius = v_x;
            float[] fArr = vel;
            float f2 = v_x;
            float angle = v_y;
            float[] fArr2 = pos;
            f = 2.0f;
            v_y = (float) ((((double) ry) - (((double) radius) * Math.cos((double) angle))) - ((double) (v_height / 2.0f)));
            v_x = (float) ((((double) rx) + (((double) radius) * Math.sin((double) angle))) - ((double) (v_width / 2.0f)));
        } else {
            float f3 = v_x;
            f = 2.0f;
        }
        point[offset] = (v_width / f) + v_x + 0.0f;
        point[offset + 1] = (v_height / f) + v_y + 0.0f;
    }

    /* access modifiers changed from: package-private */
    public void getCenter(double p, int[] toUse, double[] data, float[] point, double[] vdata, float[] velocity) {
        int[] iArr = toUse;
        float v_x = this.f371x;
        float v_y = this.f372y;
        float v_width = this.width;
        float v_height = this.height;
        float dv_x = 0.0f;
        float dv_y = 0.0f;
        float dv_width = 0.0f;
        float dv_height = 0.0f;
        for (int i = 0; i < iArr.length; i++) {
            float value = (float) data[i];
            float dvalue = (float) vdata[i];
            switch (iArr[i]) {
                case 1:
                    v_x = value;
                    dv_x = dvalue;
                    break;
                case 2:
                    v_y = value;
                    dv_y = dvalue;
                    break;
                case 3:
                    v_width = value;
                    dv_width = dvalue;
                    break;
                case 4:
                    v_height = value;
                    dv_height = dvalue;
                    break;
            }
        }
        float dangle = (dv_width / 2.0f) + dv_x;
        float dpos_y = (dv_height / 2.0f) + dv_y;
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] pos = new float[2];
            float[] vel = new float[2];
            float f = dv_width;
            float f2 = dv_height;
            motionController.getCenter(p, pos, vel);
            float rx = pos[0];
            float ry = pos[1];
            float radius = v_x;
            float angle = v_y;
            float dradius = dv_x;
            float dangle2 = dv_y;
            float[] fArr = pos;
            float drx = vel[0];
            float f3 = v_x;
            float v_x2 = vel[1];
            float f4 = dv_x;
            float f5 = dv_y;
            float f6 = v_y;
            float v_y2 = radius;
            float[] fArr2 = vel;
            float f7 = dpos_y;
            float angle2 = angle;
            float angle3 = dangle;
            float v_x3 = (float) ((((double) rx) + (((double) v_y2) * Math.sin((double) angle2))) - ((double) (v_width / 2.0f)));
            float v_y3 = (float) ((((double) ry) - (((double) v_y2) * Math.cos((double) angle2))) - ((double) (v_height / 2.0f)));
            float dradius2 = dradius;
            float f8 = drx;
            float f9 = ry;
            float dangle3 = dangle2;
            float dangle4 = rx;
            dpos_y = (float) ((((double) v_x2) - (((double) dradius2) * Math.cos((double) angle2))) + (Math.sin((double) angle2) * ((double) dangle3)));
            v_y = v_y3;
            dangle = (float) (((double) drx) + (((double) dradius2) * Math.sin((double) angle2)) + (Math.cos((double) angle2) * ((double) dangle3)));
            v_x = v_x3;
        } else {
            float f10 = v_y;
            float f11 = dv_x;
            float f12 = dv_y;
            float f13 = dv_width;
            float f14 = dv_height;
            float f15 = dangle;
            float f16 = dpos_y;
        }
        point[0] = (v_width / 2.0f) + v_x + 0.0f;
        point[1] = (v_height / 2.0f) + v_y + 0.0f;
        velocity[0] = dangle;
        velocity[1] = dpos_y;
    }

    /* access modifiers changed from: package-private */
    public void getCenterVelocity(double p, int[] toUse, double[] data, float[] point, int offset) {
        float f;
        int[] iArr = toUse;
        float v_x = this.f371x;
        float v_y = this.f372y;
        float v_width = this.width;
        float v_height = this.height;
        for (int i = 0; i < iArr.length; i++) {
            float value = (float) data[i];
            switch (iArr[i]) {
                case 1:
                    v_x = value;
                    break;
                case 2:
                    v_y = value;
                    break;
                case 3:
                    v_width = value;
                    break;
                case 4:
                    v_height = value;
                    break;
            }
        }
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] pos = new float[2];
            float[] vel = new float[2];
            motionController.getCenter(p, pos, vel);
            float rx = pos[0];
            float ry = pos[1];
            float radius = v_x;
            float[] fArr = vel;
            float f2 = v_x;
            float angle = v_y;
            float[] fArr2 = pos;
            f = 2.0f;
            v_y = (float) ((((double) ry) - (((double) radius) * Math.cos((double) angle))) - ((double) (v_height / 2.0f)));
            v_x = (float) ((((double) rx) + (((double) radius) * Math.sin((double) angle))) - ((double) (v_width / 2.0f)));
        } else {
            float f3 = v_x;
            f = 2.0f;
        }
        point[offset] = (v_width / f) + v_x + 0.0f;
        point[offset + 1] = (v_height / f) + v_y + 0.0f;
    }

    /* access modifiers changed from: package-private */
    public void getBounds(int[] toUse, double[] data, float[] point, int offset) {
        float f = this.f371x;
        float f2 = this.f372y;
        float v_width = this.width;
        float v_height = this.height;
        for (int i = 0; i < toUse.length; i++) {
            float value = (float) data[i];
            switch (toUse[i]) {
                case 1:
                    float v_x = value;
                    break;
                case 2:
                    float v_y = value;
                    break;
                case 3:
                    v_width = value;
                    break;
                case 4:
                    v_height = value;
                    break;
            }
        }
        point[offset] = v_width;
        point[offset + 1] = v_height;
    }

    /* access modifiers changed from: package-private */
    public void setView(float position2, View view, int[] toUse, double[] data, double[] slope, double[] cycle, boolean mForceMeasure) {
        float v_x;
        float dangle;
        boolean z;
        boolean remeasure;
        float v_height;
        float v_y;
        float delta_path;
        float dv_height;
        View view2 = view;
        int[] iArr = toUse;
        double[] dArr = slope;
        float v_x2 = this.f371x;
        float v_y2 = this.f372y;
        float v_width = this.width;
        float v_height2 = this.height;
        float dv_x = 0.0f;
        float dv_y = 0.0f;
        float dv_width = 0.0f;
        float dvalue = 0.0f;
        float delta_path2 = 0.0f;
        float path_rotate = Float.NaN;
        if (iArr.length != 0) {
            v_x = v_x2;
            if (this.mTempValue.length <= iArr[iArr.length - 1]) {
                int scratch_data_length = iArr[iArr.length - 1] + 1;
                this.mTempValue = new double[scratch_data_length];
                this.mTempDelta = new double[scratch_data_length];
            }
        } else {
            v_x = v_x2;
        }
        float v_y3 = v_y2;
        float v_width2 = v_width;
        Arrays.fill(this.mTempValue, Double.NaN);
        for (int i = 0; i < iArr.length; i++) {
            this.mTempValue[iArr[i]] = data[i];
            this.mTempDelta[iArr[i]] = dArr[i];
        }
        int i2 = 0;
        float v_y4 = v_y3;
        float v_width3 = v_width2;
        while (true) {
            double[] dArr2 = this.mTempValue;
            if (i2 < dArr2.length) {
                double d = 0.0d;
                if (Double.isNaN(dArr2[i2])) {
                    if (cycle == null) {
                        dv_height = dvalue;
                        delta_path = delta_path2;
                    } else if (cycle[i2] == 0.0d) {
                        dv_height = dvalue;
                        delta_path = delta_path2;
                    }
                    dvalue = dv_height;
                    delta_path2 = delta_path;
                    i2++;
                    int[] iArr2 = toUse;
                }
                if (cycle != null) {
                    d = cycle[i2];
                }
                double deltaCycle = d;
                if (Double.isNaN(this.mTempValue[i2])) {
                    double d2 = deltaCycle;
                } else {
                    double d3 = deltaCycle;
                    deltaCycle = this.mTempValue[i2] + deltaCycle;
                }
                float value = (float) deltaCycle;
                dv_height = dvalue;
                delta_path = delta_path2;
                dvalue = (float) this.mTempDelta[i2];
                switch (i2) {
                    case 0:
                        delta_path2 = value;
                        dvalue = dv_height;
                        continue;
                    case 1:
                        dv_x = dvalue;
                        v_x = value;
                        dvalue = dv_height;
                        delta_path2 = delta_path;
                        continue;
                    case 2:
                        v_y4 = value;
                        dv_y = dvalue;
                        dvalue = dv_height;
                        delta_path2 = delta_path;
                        continue;
                    case 3:
                        v_width3 = value;
                        dv_width = dvalue;
                        dvalue = dv_height;
                        delta_path2 = delta_path;
                        continue;
                    case 4:
                        v_height2 = value;
                        float f = dvalue;
                        delta_path2 = delta_path;
                        continue;
                    case 5:
                        path_rotate = value;
                        dvalue = dv_height;
                        delta_path2 = delta_path;
                        continue;
                }
                dvalue = dv_height;
                delta_path2 = delta_path;
                i2++;
                int[] iArr22 = toUse;
            } else {
                float dv_height2 = dvalue;
                float f2 = delta_path2;
                MotionController motionController = this.mRelativeToController;
                if (motionController != null) {
                    float[] pos = new float[2];
                    float[] vel = new float[2];
                    motionController.getCenter((double) position2, pos, vel);
                    float rx = pos[0];
                    float ry = pos[1];
                    float radius = v_x;
                    float angle = v_y4;
                    float dradius = dv_x;
                    float dangle2 = dv_y;
                    float f3 = v_y4;
                    float v_y5 = vel[0];
                    float dry = vel[1];
                    float[] fArr = pos;
                    float f4 = dv_height2;
                    double d4 = (double) rx;
                    float f5 = dv_x;
                    float f6 = dv_y;
                    float f7 = rx;
                    float rx2 = angle;
                    float path_rotate2 = path_rotate;
                    float pos_x = (float) ((d4 + (((double) radius) * Math.sin((double) rx2))) - ((double) (v_width3 / 2.0f)));
                    float pos_y = (float) ((((double) ry) - (((double) radius) * Math.cos((double) rx2))) - ((double) (v_height2 / 2.0f)));
                    float dradius2 = dradius;
                    float[] fArr2 = vel;
                    float f8 = v_y5;
                    v_height = v_height2;
                    float dangle3 = dangle2;
                    dangle = v_width3;
                    float dpos_x = (float) (((double) v_y5) + (((double) dradius2) * Math.sin((double) rx2)) + (((double) radius) * Math.cos((double) rx2) * ((double) dangle3)));
                    float f9 = dradius2;
                    float dv_width2 = dv_width;
                    float dpos_y = (float) ((((double) dry) - (((double) dradius2) * Math.cos((double) rx2))) + (((double) radius) * Math.sin((double) rx2) * ((double) dangle3)));
                    float dv_x2 = dpos_x;
                    float dv_y2 = dpos_y;
                    v_x = pos_x;
                    float v_y6 = pos_y;
                    if (dArr.length >= 2) {
                        z = false;
                        dArr[0] = (double) dpos_x;
                        remeasure = true;
                        dArr[1] = (double) dpos_y;
                    } else {
                        remeasure = true;
                        z = false;
                    }
                    if (!Float.isNaN(path_rotate2)) {
                        float f10 = radius;
                        float path_rotate3 = path_rotate2;
                        float path_rotate4 = rx2;
                        float f11 = ry;
                        float f12 = dpos_x;
                        float f13 = dpos_y;
                        view2 = view;
                        view2.setRotation((float) (((double) path_rotate3) + Math.toDegrees(Math.atan2((double) dv_y2, (double) dv_x2))));
                    } else {
                        float f14 = ry;
                        float f15 = dpos_x;
                        float f16 = dpos_y;
                        float f17 = path_rotate2;
                        view2 = view;
                        float path_rotate5 = rx2;
                    }
                    float pos_y2 = dv_x2;
                    float f18 = dv_width2;
                    v_y = v_y6;
                } else {
                    float v_y7 = v_y4;
                    dangle = v_width3;
                    float dv_x3 = dv_x;
                    float dv_y3 = dv_y;
                    z = false;
                    float dv_height3 = dv_height2;
                    remeasure = true;
                    v_height = v_height2;
                    float v_height3 = dv_width;
                    if (!Float.isNaN(path_rotate)) {
                        float f19 = v_height3;
                        view2.setRotation((float) (((double) 0.0f) + ((double) path_rotate) + Math.toDegrees(Math.atan2((double) (dv_y3 + (dv_height3 / 2.0f)), (double) (dv_x3 + (v_height3 / 2.0f))))));
                    } else {
                        float dv_width3 = v_height3;
                    }
                    v_y = v_y7;
                    float f20 = dv_y3;
                    float f21 = dv_x3;
                }
                if (view2 instanceof FloatLayout) {
                    ((FloatLayout) view2).layout(v_x, v_y, v_x + dangle, v_y + v_height);
                    return;
                }
                int l = (int) (v_x + 0.5f);
                int t = (int) (v_y + 0.5f);
                int r = (int) (v_x + 0.5f + dangle);
                int b = (int) (0.5f + v_y + v_height);
                int i_width = r - l;
                int i_height = b - t;
                if (i_width == view.getMeasuredWidth() && i_height == view.getMeasuredHeight()) {
                    remeasure = z;
                }
                if (remeasure || mForceMeasure) {
                    view2.measure(View.MeasureSpec.makeMeasureSpec(i_width, 1073741824), View.MeasureSpec.makeMeasureSpec(i_height, 1073741824));
                }
                view2.layout(l, t, r, b);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void getRect(int[] toUse, double[] data, float[] path, int offset) {
        float angle;
        int[] iArr = toUse;
        float v_x = this.f371x;
        float v_y = this.f372y;
        float v_width = this.width;
        float v_height = this.height;
        float alpha = 0.0f;
        float rotationX = 0.0f;
        int i = 0;
        while (true) {
            float alpha2 = alpha;
            if (i < iArr.length) {
                float rotationX2 = rotationX;
                float value = (float) data[i];
                switch (iArr[i]) {
                    case 0:
                        float delta_path = value;
                        break;
                    case 1:
                        v_x = value;
                        break;
                    case 2:
                        v_y = value;
                        break;
                    case 3:
                        v_width = value;
                        break;
                    case 4:
                        v_height = value;
                        break;
                }
                i++;
                alpha = alpha2;
                rotationX = rotationX2;
            } else {
                MotionController motionController = this.mRelativeToController;
                if (motionController != null) {
                    float rx = motionController.getCenterX();
                    float f = v_y;
                    float radius = v_x;
                    float f2 = v_y;
                    float f3 = rx;
                    angle = 0.0f;
                    float v_x2 = (float) ((((double) rx) + (((double) radius) * Math.sin((double) v_y))) - ((double) (v_width / 2.0f)));
                    v_y = (float) ((((double) this.mRelativeToController.getCenterY()) - (((double) radius) * Math.cos((double) v_y))) - ((double) (v_height / 2.0f)));
                    v_x = v_x2;
                } else {
                    float f4 = v_y;
                    angle = 0.0f;
                }
                float x1 = v_x;
                float y1 = v_y;
                float x2 = v_x + v_width;
                float y2 = y1;
                float x3 = x2;
                float y3 = v_y + v_height;
                float x4 = x1;
                float y4 = y3;
                float cx = x1 + (v_width / 2.0f);
                float cy = y1 + (v_height / 2.0f);
                if (!Float.isNaN(Float.NaN)) {
                    cx = x1 + ((x2 - x1) * Float.NaN);
                }
                if (!Float.isNaN(Float.NaN)) {
                    cy = y1 + ((y3 - y1) * Float.NaN);
                }
                if (1.0f != 1.0f) {
                    float midx = (x1 + x2) / 2.0f;
                    x1 = ((x1 - midx) * 1.0f) + midx;
                    x2 = ((x2 - midx) * 1.0f) + midx;
                    x3 = ((x3 - midx) * 1.0f) + midx;
                    x4 = ((x4 - midx) * 1.0f) + midx;
                }
                if (1.0f != 1.0f) {
                    float midy = (y1 + y3) / 2.0f;
                    y1 = ((y1 - midy) * 1.0f) + midy;
                    y2 = ((y2 - midy) * 1.0f) + midy;
                    y3 = ((y3 - midy) * 1.0f) + midy;
                    y4 = ((y4 - midy) * 1.0f) + midy;
                }
                if (angle != 0.0f) {
                    float f5 = v_x;
                    float f6 = v_y;
                    float rotation = angle;
                    float rotation2 = v_width;
                    float f7 = v_height;
                    float sin = (float) Math.sin(Math.toRadians((double) rotation));
                    float cos = (float) Math.cos(Math.toRadians((double) rotation));
                    float f8 = cx;
                    float f9 = cy;
                    float f10 = x1;
                    float f11 = y1;
                    float tx1 = xRotate(sin, cos, f8, f9, f10, f11);
                    float ty1 = yRotate(sin, cos, f8, f9, f10, f11);
                    float f12 = x2;
                    float f13 = y2;
                    float tx2 = xRotate(sin, cos, f8, f9, f12, f13);
                    float ty2 = yRotate(sin, cos, f8, f9, f12, f13);
                    float f14 = x3;
                    float f15 = y3;
                    float tx3 = xRotate(sin, cos, f8, f9, f14, f15);
                    float ty3 = yRotate(sin, cos, f8, f9, f14, f15);
                    float f16 = x4;
                    float f17 = y4;
                    x1 = tx1;
                    y1 = ty1;
                    x2 = tx2;
                    y2 = ty2;
                    x3 = tx3;
                    y3 = ty3;
                    x4 = xRotate(sin, cos, f8, f9, f16, f17);
                    y4 = yRotate(sin, cos, f8, f9, f16, f17);
                } else {
                    float f18 = v_y;
                    float f19 = v_height;
                    float v_x3 = angle;
                    float rotation3 = v_width;
                }
                int offset2 = offset + 1;
                path[offset] = x1 + 0.0f;
                int offset3 = offset2 + 1;
                path[offset2] = y1 + 0.0f;
                int offset4 = offset3 + 1;
                path[offset3] = x2 + 0.0f;
                int offset5 = offset4 + 1;
                path[offset4] = y2 + 0.0f;
                int offset6 = offset5 + 1;
                path[offset5] = x3 + 0.0f;
                int offset7 = offset6 + 1;
                path[offset6] = y3 + 0.0f;
                int offset8 = offset7 + 1;
                path[offset7] = x4 + 0.0f;
                int i2 = offset8 + 1;
                path[offset8] = y4 + 0.0f;
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setDpDt(float locationX, float locationY, float[] mAnchorDpDt, int[] toUse, double[] deltaData, double[] data) {
        int[] iArr = toUse;
        float d_x = 0.0f;
        float d_y = 0.0f;
        float d_width = 0.0f;
        float d_height = 0.0f;
        for (int i = 0; i < iArr.length; i++) {
            float deltaV = (float) deltaData[i];
            float f = (float) data[i];
            switch (iArr[i]) {
                case 1:
                    d_x = deltaV;
                    break;
                case 2:
                    d_y = deltaV;
                    break;
                case 3:
                    d_width = deltaV;
                    break;
                case 4:
                    d_height = deltaV;
                    break;
            }
        }
        float deltaX = d_x - ((0.0f * d_width) / 2.0f);
        float deltaY = d_y - ((0.0f * d_height) / 2.0f);
        mAnchorDpDt[0] = ((1.0f - locationX) * deltaX) + ((deltaX + ((0.0f + 1.0f) * d_width)) * locationX) + 0.0f;
        mAnchorDpDt[1] = ((1.0f - locationY) * deltaY) + ((deltaY + ((0.0f + 1.0f) * d_height)) * locationY) + 0.0f;
    }

    /* access modifiers changed from: package-private */
    public void fillStandard(double[] data, int[] toUse) {
        float[] set = {this.position, this.f371x, this.f372y, this.width, this.height, this.mPathRotate};
        int c = 0;
        for (int i = 0; i < toUse.length; i++) {
            if (toUse[i] < set.length) {
                data[c] = (double) set[toUse[i]];
                c++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasCustomData(String name) {
        return this.attributes.containsKey(name);
    }

    /* access modifiers changed from: package-private */
    public int getCustomDataCount(String name) {
        ConstraintAttribute a = this.attributes.get(name);
        if (a == null) {
            return 0;
        }
        return a.numberOfInterpolatedValues();
    }

    /* access modifiers changed from: package-private */
    public int getCustomData(String name, double[] value, int offset) {
        ConstraintAttribute a = this.attributes.get(name);
        if (a == null) {
            return 0;
        }
        if (a.numberOfInterpolatedValues() == 1) {
            value[offset] = (double) a.getValueToInterpolate();
            return 1;
        }
        int N = a.numberOfInterpolatedValues();
        float[] f = new float[N];
        a.getValuesToInterpolate(f);
        int i = 0;
        while (i < N) {
            value[offset] = (double) f[i];
            i++;
            offset++;
        }
        return N;
    }

    /* access modifiers changed from: package-private */
    public void setBounds(float x, float y, float w, float h) {
        this.f371x = x;
        this.f372y = y;
        this.width = w;
        this.height = h;
    }

    public int compareTo(MotionPaths o) {
        return Float.compare(this.position, o.position);
    }

    public void applyParameters(ConstraintSet.Constraint c) {
        this.mKeyFrameEasing = Easing.getInterpolator(c.motion.mTransitionEasing);
        this.mPathMotionArc = c.motion.mPathMotionArc;
        this.mAnimateRelativeTo = c.motion.mAnimateRelativeTo;
        this.mPathRotate = c.motion.mPathRotate;
        this.mDrawPath = c.motion.mDrawPath;
        this.mAnimateCircleAngleTo = c.motion.mAnimateCircleAngleTo;
        this.mProgress = c.propertySet.mProgress;
        this.mRelativeAngle = c.layout.circleAngle;
        for (String s : c.mCustomConstraints.keySet()) {
            ConstraintAttribute attr = c.mCustomConstraints.get(s);
            if (attr != null && attr.isContinuous()) {
                this.attributes.put(s, attr);
            }
        }
    }

    public void configureRelativeTo(MotionController toOrbit) {
        double[] pos = toOrbit.getPos((double) this.mProgress);
    }
}
