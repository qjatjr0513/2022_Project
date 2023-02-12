package androidx.lifecycle;

public interface DefaultLifecycleObserver extends FullLifecycleObserver {
    void onCreate(LifecycleOwner owner) {
    }

    void onStart(LifecycleOwner owner) {
    }

    void onResume(LifecycleOwner owner) {
    }

    void onPause(LifecycleOwner owner) {
    }

    void onStop(LifecycleOwner owner) {
    }

    void onDestroy(LifecycleOwner owner) {
    }
}
