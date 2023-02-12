package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.logging.Logging;
import java.util.concurrent.Executor;

class SafeLoggingExecutor implements Executor {
    private final Executor delegate;

    SafeLoggingExecutor(Executor delegate2) {
        this.delegate = delegate2;
    }

    public void execute(Runnable command) {
        this.delegate.execute(new SafeLoggingRunnable(command));
    }

    static class SafeLoggingRunnable implements Runnable {
        private final Runnable delegate;

        SafeLoggingRunnable(Runnable delegate2) {
            this.delegate = delegate2;
        }

        public void run() {
            try {
                this.delegate.run();
            } catch (Exception e) {
                Logging.m401e("Executor", "Background execution failure.", e);
            }
        }
    }
}
