package com.google.android.gms.internal.p010firebaseauthapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.Status;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvl */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzvl extends BroadcastReceiver {
    final /* synthetic */ zzvn zza;
    private final String zzb;

    public zzvl(zzvn zzvn, String str) {
        this.zza = zzvn;
        this.zzb = str;
    }

    public final void onReceive(Context context, Intent intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            switch (((Status) extras.get("com.google.android.gms.auth.api.phone.EXTRA_STATUS")).getStatusCode()) {
                case 0:
                    String str = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                    zzvm zzvm = (zzvm) this.zza.zzd.get(this.zzb);
                    if (zzvm != null) {
                        zzvm.zze = zzvn.zzb(str);
                        if (zzvm.zze != null) {
                            if (!zzaf.zzd(zzvm.zzd)) {
                                zzvn.zze(this.zza, this.zzb);
                                break;
                            }
                        } else {
                            zzvn.zza.mo31546e("Unable to extract verification code.", new Object[0]);
                            break;
                        }
                    } else {
                        zzvn.zza.mo31546e("Verification code received with no active retrieval session.", new Object[0]);
                        break;
                    }
                    break;
            }
            context.getApplicationContext().unregisterReceiver(this);
        }
    }
}
