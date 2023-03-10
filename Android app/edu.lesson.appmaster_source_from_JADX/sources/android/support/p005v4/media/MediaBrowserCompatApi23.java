package android.support.p005v4.media;

import android.media.browse.MediaBrowser;
import android.os.Parcel;

/* renamed from: android.support.v4.media.MediaBrowserCompatApi23 */
class MediaBrowserCompatApi23 {

    /* renamed from: android.support.v4.media.MediaBrowserCompatApi23$ItemCallback */
    interface ItemCallback {
        void onError(String str);

        void onItemLoaded(Parcel parcel);
    }

    public static Object createItemCallback(ItemCallback callback) {
        return new ItemCallbackProxy(callback);
    }

    public static void getItem(Object browserObj, String mediaId, Object itemCallbackObj) {
        ((MediaBrowser) browserObj).getItem(mediaId, (MediaBrowser.ItemCallback) itemCallbackObj);
    }

    /* renamed from: android.support.v4.media.MediaBrowserCompatApi23$ItemCallbackProxy */
    static class ItemCallbackProxy<T extends ItemCallback> extends MediaBrowser.ItemCallback {
        protected final T mItemCallback;

        public ItemCallbackProxy(T callback) {
            this.mItemCallback = callback;
        }

        public void onItemLoaded(MediaBrowser.MediaItem item) {
            if (item == null) {
                this.mItemCallback.onItemLoaded((Parcel) null);
                return;
            }
            Parcel parcel = Parcel.obtain();
            item.writeToParcel(parcel, 0);
            this.mItemCallback.onItemLoaded(parcel);
        }

        public void onError(String itemId) {
            this.mItemCallback.onError(itemId);
        }
    }

    private MediaBrowserCompatApi23() {
    }
}
