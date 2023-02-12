package com.google.firebase.firestore;

import com.google.firebase.firestore.util.Util;

public class GeoPoint implements Comparable<GeoPoint> {
    private final double latitude;
    private final double longitude;

    public GeoPoint(double latitude2, double longitude2) {
        if (Double.isNaN(latitude2) || latitude2 < -90.0d || latitude2 > 90.0d) {
            throw new IllegalArgumentException("Latitude must be in the range of [-90, 90]");
        } else if (Double.isNaN(longitude2) || longitude2 < -180.0d || longitude2 > 180.0d) {
            throw new IllegalArgumentException("Longitude must be in the range of [-180, 180]");
        } else {
            this.latitude = latitude2;
            this.longitude = longitude2;
        }
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public int compareTo(GeoPoint other) {
        int comparison = Util.compareDoubles(this.latitude, other.latitude);
        if (comparison == 0) {
            return Util.compareDoubles(this.longitude, other.longitude);
        }
        return comparison;
    }

    public String toString() {
        return "GeoPoint { latitude=" + this.latitude + ", longitude=" + this.longitude + " }";
    }

    public boolean equals(Object o) {
        if (!(o instanceof GeoPoint)) {
            return false;
        }
        GeoPoint geoPoint = (GeoPoint) o;
        if (this.latitude == geoPoint.latitude && this.longitude == geoPoint.longitude) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long temp = Double.doubleToLongBits(this.latitude);
        long temp2 = Double.doubleToLongBits(this.longitude);
        return (((int) ((temp >>> 32) ^ temp)) * 31) + ((int) ((temp2 >>> 32) ^ temp2));
    }
}
