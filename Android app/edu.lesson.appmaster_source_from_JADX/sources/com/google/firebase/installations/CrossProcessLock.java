package com.google.firebase.installations;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

class CrossProcessLock {
    private static final String TAG = "CrossProcessLock";
    private final FileChannel channel;
    private final FileLock lock;

    private CrossProcessLock(FileChannel channel2, FileLock lock2) {
        this.channel = channel2;
        this.lock = lock2;
    }

    static CrossProcessLock acquire(Context appContext, String lockName) {
        FileChannel channel2 = null;
        FileLock lock2 = null;
        try {
            channel2 = new RandomAccessFile(new File(appContext.getFilesDir(), lockName), "rw").getChannel();
            lock2 = channel2.lock();
            return new CrossProcessLock(channel2, lock2);
        } catch (IOException | Error | OverlappingFileLockException e) {
            Log.e(TAG, "encountered error while creating and acquiring the lock, ignoring", e);
            if (lock2 != null) {
                try {
                    lock2.release();
                } catch (IOException e2) {
                }
            }
            if (channel2 == null) {
                return null;
            }
            try {
                channel2.close();
                return null;
            } catch (IOException e3) {
                return null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void releaseAndClose() {
        try {
            this.lock.release();
            this.channel.close();
        } catch (IOException e) {
            Log.e(TAG, "encountered error while releasing, ignoring", e);
        }
    }
}
