package androidx.browser.customtabs;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback;
import android.support.customtabs.ICustomTabsService;
import android.widget.RemoteViews;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.util.List;

public final class CustomTabsSession {
    private static final String TAG = "CustomTabsSession";
    private final ICustomTabsCallback mCallback;
    private final ComponentName mComponentName;
    private final Object mLock = new Object();
    private final ICustomTabsService mService;

    public static CustomTabsSession createMockSessionForTesting(ComponentName componentName) {
        return new CustomTabsSession((ICustomTabsService) null, new CustomTabsSessionToken.MockCallback(), componentName);
    }

    CustomTabsSession(ICustomTabsService service, ICustomTabsCallback callback, ComponentName componentName) {
        this.mService = service;
        this.mCallback = callback;
        this.mComponentName = componentName;
    }

    public boolean mayLaunchUrl(Uri url, Bundle extras, List<Bundle> otherLikelyBundles) {
        try {
            return this.mService.mayLaunchUrl(this.mCallback, url, extras, otherLikelyBundles);
        } catch (RemoteException e) {
            return false;
        }
    }

    public boolean setActionButton(Bitmap icon, String description) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(CustomTabsIntent.KEY_ICON, icon);
        bundle.putString(CustomTabsIntent.KEY_DESCRIPTION, description);
        Bundle metaBundle = new Bundle();
        metaBundle.putBundle(CustomTabsIntent.EXTRA_ACTION_BUTTON_BUNDLE, bundle);
        try {
            return this.mService.updateVisuals(this.mCallback, metaBundle);
        } catch (RemoteException e) {
            return false;
        }
    }

    public boolean setSecondaryToolbarViews(RemoteViews remoteViews, int[] clickableIDs, PendingIntent pendingIntent) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(CustomTabsIntent.EXTRA_REMOTEVIEWS, remoteViews);
        bundle.putIntArray(CustomTabsIntent.EXTRA_REMOTEVIEWS_VIEW_IDS, clickableIDs);
        bundle.putParcelable(CustomTabsIntent.EXTRA_REMOTEVIEWS_PENDINGINTENT, pendingIntent);
        try {
            return this.mService.updateVisuals(this.mCallback, bundle);
        } catch (RemoteException e) {
            return false;
        }
    }

    @Deprecated
    public boolean setToolbarItem(int id, Bitmap icon, String description) {
        Bundle bundle = new Bundle();
        bundle.putInt(CustomTabsIntent.KEY_ID, id);
        bundle.putParcelable(CustomTabsIntent.KEY_ICON, icon);
        bundle.putString(CustomTabsIntent.KEY_DESCRIPTION, description);
        Bundle metaBundle = new Bundle();
        metaBundle.putBundle(CustomTabsIntent.EXTRA_ACTION_BUTTON_BUNDLE, bundle);
        try {
            return this.mService.updateVisuals(this.mCallback, metaBundle);
        } catch (RemoteException e) {
            return false;
        }
    }

    public boolean requestPostMessageChannel(Uri postMessageOrigin) {
        try {
            return this.mService.requestPostMessageChannel(this.mCallback, postMessageOrigin);
        } catch (RemoteException e) {
            return false;
        }
    }

    public int postMessage(String message, Bundle extras) {
        int postMessage;
        synchronized (this.mLock) {
            try {
                postMessage = this.mService.postMessage(this.mCallback, message, extras);
            } catch (RemoteException e) {
                return -2;
            } catch (Throwable th) {
                throw th;
            }
        }
        return postMessage;
    }

    public boolean validateRelationship(int relation, Uri origin, Bundle extras) {
        if (relation < 1 || relation > 2) {
            return false;
        }
        try {
            return this.mService.validateRelationship(this.mCallback, relation, origin, extras);
        } catch (RemoteException e) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public IBinder getBinder() {
        return this.mCallback.asBinder();
    }

    /* access modifiers changed from: package-private */
    public ComponentName getComponentName() {
        return this.mComponentName;
    }
}
