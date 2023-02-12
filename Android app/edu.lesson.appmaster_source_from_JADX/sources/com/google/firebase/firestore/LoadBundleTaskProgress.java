package com.google.firebase.firestore;

import com.google.firebase.firestore.bundle.BundleMetadata;

public final class LoadBundleTaskProgress {
    static final LoadBundleTaskProgress INITIAL = new LoadBundleTaskProgress(0, 0, 0, 0, (Exception) null, TaskState.SUCCESS);
    private final long bytesLoaded;
    private final int documentsLoaded;
    private final Exception exception;
    private final TaskState taskState;
    private final long totalBytes;
    private final int totalDocuments;

    public enum TaskState {
        ERROR,
        RUNNING,
        SUCCESS
    }

    public LoadBundleTaskProgress(int documentsLoaded2, int totalDocuments2, long bytesLoaded2, long totalBytes2, Exception exception2, TaskState taskState2) {
        this.documentsLoaded = documentsLoaded2;
        this.totalDocuments = totalDocuments2;
        this.bytesLoaded = bytesLoaded2;
        this.totalBytes = totalBytes2;
        this.taskState = taskState2;
        this.exception = exception2;
    }

    public static LoadBundleTaskProgress forInitial(BundleMetadata bundleMetadata) {
        return new LoadBundleTaskProgress(0, bundleMetadata.getTotalDocuments(), 0, bundleMetadata.getTotalBytes(), (Exception) null, TaskState.RUNNING);
    }

    public static LoadBundleTaskProgress forSuccess(BundleMetadata bundleMetadata) {
        return new LoadBundleTaskProgress(bundleMetadata.getTotalDocuments(), bundleMetadata.getTotalDocuments(), bundleMetadata.getTotalBytes(), bundleMetadata.getTotalBytes(), (Exception) null, TaskState.SUCCESS);
    }

    public int getDocumentsLoaded() {
        return this.documentsLoaded;
    }

    public int getTotalDocuments() {
        return this.totalDocuments;
    }

    public long getBytesLoaded() {
        return this.bytesLoaded;
    }

    public long getTotalBytes() {
        return this.totalBytes;
    }

    public TaskState getTaskState() {
        return this.taskState;
    }

    public Exception getException() {
        return this.exception;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoadBundleTaskProgress that = (LoadBundleTaskProgress) o;
        if (this.documentsLoaded != that.documentsLoaded || this.totalDocuments != that.totalDocuments || this.bytesLoaded != that.bytesLoaded || this.totalBytes != that.totalBytes || this.taskState != that.taskState) {
            return false;
        }
        Exception exc = this.exception;
        if (exc != null) {
            return exc.equals(that.exception);
        }
        if (that.exception == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.bytesLoaded;
        long j2 = this.totalBytes;
        int result = ((((((((this.documentsLoaded * 31) + this.totalDocuments) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.taskState.hashCode()) * 31;
        Exception exc = this.exception;
        return result + (exc != null ? exc.hashCode() : 0);
    }
}
