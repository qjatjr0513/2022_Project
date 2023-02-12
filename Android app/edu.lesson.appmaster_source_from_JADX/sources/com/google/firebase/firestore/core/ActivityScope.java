package com.google.firebase.firestore.core;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ActivityScope {
    private static final String FRAGMENT_TAG = "FirestoreOnStopObserverFragment";
    private static final String SUPPORT_FRAGMENT_TAG = "FirestoreOnStopObserverSupportFragment";

    private static class CallbackList {
        private final List<Runnable> callbacks;

        private CallbackList() {
            this.callbacks = new ArrayList();
        }

        /* access modifiers changed from: package-private */
        public void run() {
            for (Runnable callback : this.callbacks) {
                if (callback != null) {
                    callback.run();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void add(Runnable callback) {
            this.callbacks.add(callback);
        }
    }

    public static class StopListenerSupportFragment extends Fragment {
        CallbackList callbacks = new CallbackList();

        public void onStop() {
            CallbackList callbacksCopy;
            super.onStop();
            synchronized (this.callbacks) {
                callbacksCopy = this.callbacks;
                this.callbacks = new CallbackList();
            }
            callbacksCopy.run();
        }
    }

    public static class StopListenerFragment extends android.app.Fragment {
        CallbackList callbacks = new CallbackList();

        public void onStop() {
            CallbackList callbacksCopy;
            super.onStop();
            synchronized (this.callbacks) {
                callbacksCopy = this.callbacks;
                this.callbacks = new CallbackList();
            }
            callbacksCopy.run();
        }
    }

    private static <T> T castFragment(Class<T> fragmentClass, Object fragment, String tag) {
        if (fragment == null) {
            return null;
        }
        try {
            return fragmentClass.cast(fragment);
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag '" + tag + "' is a " + fragment.getClass().getName() + " but should be a " + fragmentClass.getName());
        }
    }

    private static void onActivityStopCallOnce(Activity activity, Runnable callback) {
        Assert.hardAssert(!(activity instanceof FragmentActivity), "onActivityStopCallOnce must be called with a *non*-FragmentActivity Activity.", new Object[0]);
        activity.runOnUiThread(new ActivityScope$$ExternalSyntheticLambda0(activity, callback));
    }

    static /* synthetic */ void lambda$onActivityStopCallOnce$0(Activity activity, Runnable callback) {
        StopListenerFragment fragment = (StopListenerFragment) castFragment(StopListenerFragment.class, activity.getFragmentManager().findFragmentByTag(FRAGMENT_TAG), FRAGMENT_TAG);
        if (fragment == null || fragment.isRemoving()) {
            fragment = new StopListenerFragment();
            activity.getFragmentManager().beginTransaction().add(fragment, FRAGMENT_TAG).commitAllowingStateLoss();
            activity.getFragmentManager().executePendingTransactions();
        }
        fragment.callbacks.add(callback);
    }

    private static void onFragmentActivityStopCallOnce(FragmentActivity activity, Runnable callback) {
        activity.runOnUiThread(new ActivityScope$$ExternalSyntheticLambda1(activity, callback));
    }

    static /* synthetic */ void lambda$onFragmentActivityStopCallOnce$1(FragmentActivity activity, Runnable callback) {
        StopListenerSupportFragment fragment = (StopListenerSupportFragment) castFragment(StopListenerSupportFragment.class, activity.getSupportFragmentManager().findFragmentByTag(SUPPORT_FRAGMENT_TAG), SUPPORT_FRAGMENT_TAG);
        if (fragment == null || fragment.isRemoving()) {
            fragment = new StopListenerSupportFragment();
            activity.getSupportFragmentManager().beginTransaction().add((Fragment) fragment, SUPPORT_FRAGMENT_TAG).commitAllowingStateLoss();
            activity.getSupportFragmentManager().executePendingTransactions();
        }
        fragment.callbacks.add(callback);
    }

    public static ListenerRegistration bind(Activity activity, ListenerRegistration registration) {
        if (activity != null) {
            if (activity instanceof FragmentActivity) {
                Objects.requireNonNull(registration);
                onFragmentActivityStopCallOnce((FragmentActivity) activity, new ActivityScope$$ExternalSyntheticLambda2(registration));
            } else {
                Objects.requireNonNull(registration);
                onActivityStopCallOnce(activity, new ActivityScope$$ExternalSyntheticLambda2(registration));
            }
        }
        return registration;
    }
}
