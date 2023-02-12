package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
public class TaskUtil {
    public static void setResultOrApiException(Status status, TaskCompletionSource<Void> completionSource) {
        setResultOrApiException(status, (Object) null, completionSource);
    }

    @Deprecated
    public static Task<Void> toVoidTaskThatFailsOnFalse(Task<Boolean> task) {
        return task.continueWith(new zacx());
    }

    public static <ResultT> boolean trySetResultOrApiException(Status status, ResultT result, TaskCompletionSource<ResultT> completionSource) {
        if (status.isSuccess()) {
            return completionSource.trySetResult(result);
        }
        return completionSource.trySetException(new ApiException(status));
    }

    public static <TResult> void setResultOrApiException(Status status, TResult result, TaskCompletionSource<TResult> completionSource) {
        if (status.isSuccess()) {
            completionSource.setResult(result);
        } else {
            completionSource.setException(new ApiException(status));
        }
    }
}
