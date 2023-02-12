package com.google.android.gms.auth.api.phone;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.internal.p009authapiphone.zzn;

/* compiled from: com.google.android.gms:play-services-auth-api-phone@@17.4.0 */
public final class SmsCodeRetriever {
    public static final String EXTRA_SMS_CODE = "com.google.android.gms.auth.api.phone.EXTRA_SMS_CODE";
    public static final String EXTRA_STATUS = "com.google.android.gms.auth.api.phone.EXTRA_STATUS";
    public static final String SMS_CODE_RETRIEVED_ACTION = "com.google.android.gms.auth.api.phone.SMS_CODE_RETRIEVED";

    public static SmsCodeAutofillClient getAutofillClient(Context context) {
        return new zzn(context);
    }

    public static SmsCodeAutofillClient getAutofillClient(Activity activity) {
        return new zzn(activity);
    }

    private SmsCodeRetriever() {
    }
}
