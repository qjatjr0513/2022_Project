package androidx.core.location;

import android.location.LocationListener;
import android.os.Bundle;

public interface LocationListenerCompat extends LocationListener {
    void onStatusChanged(String provider, int status, Bundle extras) {
    }

    void onProviderEnabled(String provider) {
    }

    void onProviderDisabled(String provider) {
    }
}
