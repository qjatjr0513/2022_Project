package com.google.android.datatransport.runtime.retries;

public final class Retries {
    private Retries() {
    }

    public static <TInput, TResult, TException extends Throwable> TResult retry(int maxAttempts, TInput input, Function<TInput, TResult, TException> function, RetryStrategy<TInput, TResult> retryStrategy) throws Throwable {
        TResult result;
        if (maxAttempts < 1) {
            return function.apply(input);
        }
        do {
            result = function.apply(input);
            input = retryStrategy.shouldRetry(input, result);
            if (input == null || maxAttempts - 1 < 1) {
                return result;
            }
            result = function.apply(input);
            input = retryStrategy.shouldRetry(input, result);
            break;
        } while (maxAttempts - 1 < 1);
        return result;
    }
}
