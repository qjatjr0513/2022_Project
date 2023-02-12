package androidx.lifecycle;

import android.content.Context;
import androidx.startup.Initializer;
import java.util.Collections;
import java.util.List;

public final class ProcessLifecycleInitializer implements Initializer<LifecycleOwner> {
    public LifecycleOwner create(Context context) {
        LifecycleDispatcher.init(context);
        ProcessLifecycleOwner.init(context);
        return ProcessLifecycleOwner.get();
    }

    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.emptyList();
    }
}
