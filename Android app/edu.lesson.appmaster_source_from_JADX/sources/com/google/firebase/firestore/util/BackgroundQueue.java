package com.google.firebase.firestore.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;

public class BackgroundQueue implements Executor {
    private Semaphore completedTasks = new Semaphore(0);
    private int pendingTaskCount = 0;

    public void execute(Runnable task) {
        this.pendingTaskCount++;
        Executors.BACKGROUND_EXECUTOR.execute(new BackgroundQueue$$ExternalSyntheticLambda0(this, task));
    }

    /* renamed from: lambda$execute$0$com-google-firebase-firestore-util-BackgroundQueue */
    public /* synthetic */ void mo9981x97f26df8(Runnable task) {
        task.run();
        this.completedTasks.release();
    }

    public void drain() {
        try {
            this.completedTasks.acquire(this.pendingTaskCount);
            this.pendingTaskCount = 0;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Assert.fail("Interrupted while waiting for background task", e);
        }
    }
}
