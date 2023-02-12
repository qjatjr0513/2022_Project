package com.google.android.gms.maps.model;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public final class Dash extends PatternItem {
    public final float length;

    public Dash(float length2) {
        super(0, Float.valueOf(Math.max(length2, 0.0f)));
        this.length = Math.max(length2, 0.0f);
    }

    public String toString() {
        float f = this.length;
        StringBuilder sb = new StringBuilder(30);
        sb.append("[Dash: length=");
        sb.append(f);
        sb.append("]");
        return sb.toString();
    }
}
