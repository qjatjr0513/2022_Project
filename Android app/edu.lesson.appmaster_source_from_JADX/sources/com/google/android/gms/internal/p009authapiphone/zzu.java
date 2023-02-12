package com.google.android.gms.internal.p009authapiphone;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;

/* renamed from: com.google.android.gms.internal.auth-api-phone.zzu */
/* compiled from: com.google.android.gms:play-services-auth-api-phone@@17.4.0 */
public final class zzu extends SmsRetrieverClient {
    public zzu(Context context) {
        super(context);
    }

    public zzu(Activity activity) {
        super(activity);
    }

    public final Task<Void> startSmsRetriever() {
        return doWrite(TaskApiCall.builder().run(new zzx(this)).setFeatures(zzaa.zzb).build());
    }

    public final Task<Void> startSmsUserConsent(String str) {
        return doWrite(TaskApiCall.builder().run(new zzw(this, str)).setFeatures(zzaa.zzc).build());
    }
}
