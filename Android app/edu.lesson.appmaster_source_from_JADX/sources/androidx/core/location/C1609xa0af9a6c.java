package androidx.core.location;

import androidx.core.location.LocationManagerCompat;
import java.lang.ref.WeakReference;
import java.util.function.Predicate;

/* renamed from: androidx.core.location.LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda7 */
public final /* synthetic */ class C1609xa0af9a6c implements Predicate {
    public static final /* synthetic */ C1609xa0af9a6c INSTANCE = new C1609xa0af9a6c();

    private /* synthetic */ C1609xa0af9a6c() {
    }

    public final boolean test(Object obj) {
        return LocationManagerCompat.LocationListenerTransport.lambda$unregister$1((WeakReference) obj);
    }
}
