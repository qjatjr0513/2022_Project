package com.google.android.gms.internal.firebase_messaging;

import androidx.core.app.NotificationCompat;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.messaging.reporting.MessagingClientEvent;
import java.io.IOException;

/* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
final class zza implements ObjectEncoder<MessagingClientEvent> {
    static final zza zza = new zza();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;
    private static final FieldDescriptor zzh;
    private static final FieldDescriptor zzi;
    private static final FieldDescriptor zzj;
    private static final FieldDescriptor zzk;
    private static final FieldDescriptor zzl;
    private static final FieldDescriptor zzm;
    private static final FieldDescriptor zzn;
    private static final FieldDescriptor zzo;
    private static final FieldDescriptor zzp;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("projectNumber");
        zzo zzo2 = new zzo();
        zzo2.zza(1);
        zzb = builder.withProperty(zzo2.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("messageId");
        zzo zzo3 = new zzo();
        zzo3.zza(2);
        zzc = builder2.withProperty(zzo3.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("instanceId");
        zzo zzo4 = new zzo();
        zzo4.zza(3);
        zzd = builder3.withProperty(zzo4.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("messageType");
        zzo zzo5 = new zzo();
        zzo5.zza(4);
        zze = builder4.withProperty(zzo5.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("sdkPlatform");
        zzo zzo6 = new zzo();
        zzo6.zza(5);
        zzf = builder5.withProperty(zzo6.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("packageName");
        zzo zzo7 = new zzo();
        zzo7.zza(6);
        zzg = builder6.withProperty(zzo7.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("collapseKey");
        zzo zzo8 = new zzo();
        zzo8.zza(7);
        zzh = builder7.withProperty(zzo8.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("priority");
        zzo zzo9 = new zzo();
        zzo9.zza(8);
        zzi = builder8.withProperty(zzo9.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("ttl");
        zzo zzo10 = new zzo();
        zzo10.zza(9);
        zzj = builder9.withProperty(zzo10.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("topic");
        zzo zzo11 = new zzo();
        zzo11.zza(10);
        zzk = builder10.withProperty(zzo11.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("bulkId");
        zzo zzo12 = new zzo();
        zzo12.zza(11);
        zzl = builder11.withProperty(zzo12.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder(NotificationCompat.CATEGORY_EVENT);
        zzo zzo13 = new zzo();
        zzo13.zza(12);
        zzm = builder12.withProperty(zzo13.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("analyticsLabel");
        zzo zzo14 = new zzo();
        zzo14.zza(13);
        zzn = builder13.withProperty(zzo14.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("campaignId");
        zzo zzo15 = new zzo();
        zzo15.zza(14);
        zzo = builder14.withProperty(zzo15.zzb()).build();
        FieldDescriptor.Builder builder15 = FieldDescriptor.builder("composerLabel");
        zzo zzo16 = new zzo();
        zzo16.zza(15);
        zzp = builder15.withProperty(zzo16.zzb()).build();
    }

    private zza() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        MessagingClientEvent messagingClientEvent = (MessagingClientEvent) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, messagingClientEvent.getProjectNumber());
        objectEncoderContext.add(zzc, (Object) messagingClientEvent.getMessageId());
        objectEncoderContext.add(zzd, (Object) messagingClientEvent.getInstanceId());
        objectEncoderContext.add(zze, (Object) messagingClientEvent.getMessageType());
        objectEncoderContext.add(zzf, (Object) messagingClientEvent.getSdkPlatform());
        objectEncoderContext.add(zzg, (Object) messagingClientEvent.getPackageName());
        objectEncoderContext.add(zzh, (Object) messagingClientEvent.getCollapseKey());
        objectEncoderContext.add(zzi, messagingClientEvent.getPriority());
        objectEncoderContext.add(zzj, messagingClientEvent.getTtl());
        objectEncoderContext.add(zzk, (Object) messagingClientEvent.getTopic());
        objectEncoderContext.add(zzl, messagingClientEvent.getBulkId());
        objectEncoderContext.add(zzm, (Object) messagingClientEvent.getEvent());
        objectEncoderContext.add(zzn, (Object) messagingClientEvent.getAnalyticsLabel());
        objectEncoderContext.add(zzo, messagingClientEvent.getCampaignId());
        objectEncoderContext.add(zzp, (Object) messagingClientEvent.getComposerLabel());
    }
}
