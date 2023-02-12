package android.support.p005v4.media;

import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.support.p005v4.media.MediaBrowserCompatApi21;
import android.support.p005v4.media.session.MediaSessionCompat;
import java.util.List;

/* renamed from: android.support.v4.media.MediaBrowserCompatApi26 */
class MediaBrowserCompatApi26 {

    /* renamed from: android.support.v4.media.MediaBrowserCompatApi26$SubscriptionCallback */
    interface SubscriptionCallback extends MediaBrowserCompatApi21.SubscriptionCallback {
        void onChildrenLoaded(String str, List<?> list, Bundle bundle);

        void onError(String str, Bundle bundle);
    }

    static Object createSubscriptionCallback(SubscriptionCallback callback) {
        return new SubscriptionCallbackProxy(callback);
    }

    public static void subscribe(Object browserObj, String parentId, Bundle options, Object subscriptionCallbackObj) {
        ((MediaBrowser) browserObj).subscribe(parentId, options, (MediaBrowser.SubscriptionCallback) subscriptionCallbackObj);
    }

    public static void unsubscribe(Object browserObj, String parentId, Object subscriptionCallbackObj) {
        ((MediaBrowser) browserObj).unsubscribe(parentId, (MediaBrowser.SubscriptionCallback) subscriptionCallbackObj);
    }

    /* renamed from: android.support.v4.media.MediaBrowserCompatApi26$SubscriptionCallbackProxy */
    static class SubscriptionCallbackProxy<T extends SubscriptionCallback> extends MediaBrowserCompatApi21.SubscriptionCallbackProxy<T> {
        SubscriptionCallbackProxy(T callback) {
            super(callback);
        }

        public void onChildrenLoaded(String parentId, List<MediaBrowser.MediaItem> children, Bundle options) {
            MediaSessionCompat.ensureClassLoader(options);
            ((SubscriptionCallback) this.mSubscriptionCallback).onChildrenLoaded(parentId, children, options);
        }

        public void onError(String parentId, Bundle options) {
            MediaSessionCompat.ensureClassLoader(options);
            ((SubscriptionCallback) this.mSubscriptionCallback).onError(parentId, options);
        }
    }

    private MediaBrowserCompatApi26() {
    }
}
