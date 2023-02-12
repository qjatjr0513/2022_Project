package com.google.firebase;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.firestore.util.Preconditions;
import java.util.Date;

public final class Timestamp implements Comparable<Timestamp>, Parcelable {
    public static final Parcelable.Creator<Timestamp> CREATOR = new Parcelable.Creator<Timestamp>() {
        public Timestamp createFromParcel(Parcel source) {
            return new Timestamp(source);
        }

        public Timestamp[] newArray(int size) {
            return new Timestamp[size];
        }
    };
    private final int nanoseconds;
    private final long seconds;

    public Timestamp(long seconds2, int nanoseconds2) {
        validateRange(seconds2, nanoseconds2);
        this.seconds = seconds2;
        this.nanoseconds = nanoseconds2;
    }

    protected Timestamp(Parcel in) {
        this.seconds = in.readLong();
        this.nanoseconds = in.readInt();
    }

    public Timestamp(Date date) {
        long millis = date.getTime();
        long seconds2 = millis / 1000;
        int nanoseconds2 = ((int) (millis % 1000)) * 1000000;
        if (nanoseconds2 < 0) {
            seconds2--;
            nanoseconds2 += 1000000000;
        }
        validateRange(seconds2, nanoseconds2);
        this.seconds = seconds2;
        this.nanoseconds = nanoseconds2;
    }

    public static Timestamp now() {
        return new Timestamp(new Date());
    }

    public long getSeconds() {
        return this.seconds;
    }

    public int getNanoseconds() {
        return this.nanoseconds;
    }

    public Date toDate() {
        return new Date((this.seconds * 1000) + ((long) (this.nanoseconds / 1000000)));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.seconds);
        dest.writeInt(this.nanoseconds);
    }

    public int compareTo(Timestamp other) {
        long j = this.seconds;
        long j2 = other.seconds;
        if (j == j2) {
            return Integer.signum(this.nanoseconds - other.nanoseconds);
        }
        return Long.signum(j - j2);
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Timestamp)) {
            return false;
        }
        if (compareTo((Timestamp) other) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.seconds;
        return (37 * ((37 * ((int) j) * 37) + ((int) (j >> 32)))) + this.nanoseconds;
    }

    public String toString() {
        return "Timestamp(seconds=" + this.seconds + ", nanoseconds=" + this.nanoseconds + ")";
    }

    private static void validateRange(long seconds2, int nanoseconds2) {
        Preconditions.checkArgument(nanoseconds2 >= 0, "Timestamp nanoseconds out of range: %s", Integer.valueOf(nanoseconds2));
        Preconditions.checkArgument(((double) nanoseconds2) < 1.0E9d, "Timestamp nanoseconds out of range: %s", Integer.valueOf(nanoseconds2));
        Preconditions.checkArgument(seconds2 >= -62135596800L, "Timestamp seconds out of range: %s", Long.valueOf(seconds2));
        Preconditions.checkArgument(seconds2 < 253402300800L, "Timestamp seconds out of range: %s", Long.valueOf(seconds2));
    }
}
