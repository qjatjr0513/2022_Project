package androidx.savedstate;

import android.os.Bundle;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.savedstate.Recreator;
import java.util.Iterator;
import java.util.Map;

public final class SavedStateRegistry {
    private static final String SAVED_COMPONENTS_KEY = "androidx.lifecycle.BundlableSavedStateRegistry.key";
    boolean mAllowingSavingState = true;
    private SafeIterableMap<String, SavedStateProvider> mComponents = new SafeIterableMap<>();
    private Recreator.SavedStateProvider mRecreatorProvider;
    private boolean mRestored;
    private Bundle mRestoredState;

    public interface AutoRecreated {
        void onRecreated(SavedStateRegistryOwner savedStateRegistryOwner);
    }

    public interface SavedStateProvider {
        Bundle saveState();
    }

    SavedStateRegistry() {
    }

    public Bundle consumeRestoredStateForKey(String key) {
        if (this.mRestored) {
            Bundle bundle = this.mRestoredState;
            if (bundle == null) {
                return null;
            }
            Bundle result = bundle.getBundle(key);
            this.mRestoredState.remove(key);
            if (this.mRestoredState.isEmpty()) {
                this.mRestoredState = null;
            }
            return result;
        }
        throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
    }

    public void registerSavedStateProvider(String key, SavedStateProvider provider) {
        if (this.mComponents.putIfAbsent(key, provider) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }

    public void unregisterSavedStateProvider(String key) {
        this.mComponents.remove(key);
    }

    public boolean isRestored() {
        return this.mRestored;
    }

    public void runOnNextRecreation(Class<? extends AutoRecreated> clazz) {
        if (this.mAllowingSavingState) {
            if (this.mRecreatorProvider == null) {
                this.mRecreatorProvider = new Recreator.SavedStateProvider(this);
            }
            try {
                clazz.getDeclaredConstructor(new Class[0]);
                this.mRecreatorProvider.add(clazz.getName());
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("Class" + clazz.getSimpleName() + " must have default constructor in order to be automatically recreated", e);
            }
        } else {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    /* access modifiers changed from: package-private */
    public void performRestore(Lifecycle lifecycle, Bundle savedState) {
        if (!this.mRestored) {
            if (savedState != null) {
                this.mRestoredState = savedState.getBundle(SAVED_COMPONENTS_KEY);
            }
            lifecycle.addObserver(new GenericLifecycleObserver() {
                public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
                    if (event == Lifecycle.Event.ON_START) {
                        SavedStateRegistry.this.mAllowingSavingState = true;
                    } else if (event == Lifecycle.Event.ON_STOP) {
                        SavedStateRegistry.this.mAllowingSavingState = false;
                    }
                }
            });
            this.mRestored = true;
            return;
        }
        throw new IllegalStateException("SavedStateRegistry was already restored.");
    }

    /* access modifiers changed from: package-private */
    public void performSave(Bundle outBundle) {
        Bundle components = new Bundle();
        Bundle bundle = this.mRestoredState;
        if (bundle != null) {
            components.putAll(bundle);
        }
        Iterator<Map.Entry<String, SavedStateProvider>> it = this.mComponents.iteratorWithAdditions();
        while (it.hasNext()) {
            Map.Entry<String, SavedStateProvider> entry1 = it.next();
            components.putBundle(entry1.getKey(), entry1.getValue().saveState());
        }
        outBundle.putBundle(SAVED_COMPONENTS_KEY, components);
    }
}
