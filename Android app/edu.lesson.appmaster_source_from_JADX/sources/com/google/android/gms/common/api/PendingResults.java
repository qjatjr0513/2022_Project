package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
public final class PendingResults {
    private PendingResults() {
    }

    public static PendingResult<Status> canceledPendingResult() {
        StatusPendingResult statusPendingResult = new StatusPendingResult(Looper.getMainLooper());
        statusPendingResult.cancel();
        return statusPendingResult;
    }

    public static <R extends Result> PendingResult<R> immediateFailedResult(R result, GoogleApiClient apiClient) {
        Preconditions.checkNotNull(result, "Result must not be null");
        Preconditions.checkArgument(!result.getStatus().isSuccess(), "Status code must not be SUCCESS");
        zag zag = new zag(apiClient, result);
        zag.setResult(result);
        return zag;
    }

    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R result) {
        Preconditions.checkNotNull(result, "Result must not be null");
        zah zah = new zah((GoogleApiClient) null);
        zah.setResult(result);
        return new OptionalPendingResultImpl(zah);
    }

    public static <R extends Result> PendingResult<R> canceledPendingResult(R result) {
        boolean z;
        Preconditions.checkNotNull(result, "Result must not be null");
        if (result.getStatus().getStatusCode() == 16) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "Status code must be CommonStatusCodes.CANCELED");
        zaf zaf = new zaf(result);
        zaf.cancel();
        return zaf;
    }

    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R result, GoogleApiClient apiClient) {
        Preconditions.checkNotNull(result, "Result must not be null");
        zah zah = new zah(apiClient);
        zah.setResult(result);
        return new OptionalPendingResultImpl(zah);
    }

    public static PendingResult<Status> immediatePendingResult(Status result) {
        Preconditions.checkNotNull(result, "Result must not be null");
        StatusPendingResult statusPendingResult = new StatusPendingResult(Looper.getMainLooper());
        statusPendingResult.setResult(result);
        return statusPendingResult;
    }

    public static PendingResult<Status> immediatePendingResult(Status result, GoogleApiClient apiClient) {
        Preconditions.checkNotNull(result, "Result must not be null");
        StatusPendingResult statusPendingResult = new StatusPendingResult(apiClient);
        statusPendingResult.setResult(result);
        return statusPendingResult;
    }
}
