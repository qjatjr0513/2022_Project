package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
public abstract class zzcb extends zzbn implements zzcc {
    public zzcb() {
        super("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    public static zzcc asInterface(IBinder obj) {
        if (obj == null) {
            return null;
        }
        IInterface queryLocalInterface = obj.queryLocalInterface("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
        return queryLocalInterface instanceof zzcc ? (zzcc) queryLocalInterface : new zzca(obj);
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v9, types: [com.google.android.gms.internal.measurement.zzcf] */
    /* JADX WARNING: type inference failed for: r3v14, types: [com.google.android.gms.internal.measurement.zzcf] */
    /* JADX WARNING: type inference failed for: r3v20, types: [com.google.android.gms.internal.measurement.zzcf] */
    /* JADX WARNING: type inference failed for: r3v26, types: [com.google.android.gms.internal.measurement.zzcf] */
    /* JADX WARNING: type inference failed for: r3v30, types: [com.google.android.gms.internal.measurement.zzcf] */
    /* JADX WARNING: type inference failed for: r3v34, types: [com.google.android.gms.internal.measurement.zzck] */
    /* JADX WARNING: type inference failed for: r3v38, types: [com.google.android.gms.internal.measurement.zzcf] */
    /* JADX WARNING: type inference failed for: r3v42, types: [com.google.android.gms.internal.measurement.zzcf] */
    /* JADX WARNING: type inference failed for: r3v46, types: [com.google.android.gms.internal.measurement.zzcf] */
    /* JADX WARNING: type inference failed for: r3v50, types: [com.google.android.gms.internal.measurement.zzcf] */
    /* JADX WARNING: type inference failed for: r3v55, types: [com.google.android.gms.internal.measurement.zzcf] */
    /* JADX WARNING: type inference failed for: r3v60, types: [com.google.android.gms.internal.measurement.zzcf] */
    /* JADX WARNING: type inference failed for: r3v67, types: [com.google.android.gms.internal.measurement.zzci] */
    /* JADX WARNING: type inference failed for: r3v71, types: [com.google.android.gms.internal.measurement.zzci] */
    /* JADX WARNING: type inference failed for: r3v75, types: [com.google.android.gms.internal.measurement.zzci] */
    /* JADX WARNING: type inference failed for: r3v79, types: [com.google.android.gms.internal.measurement.zzcf] */
    /* JADX WARNING: type inference failed for: r3v84, types: [com.google.android.gms.internal.measurement.zzcf] */
    /* JADX WARNING: type inference failed for: r3v88 */
    /* JADX WARNING: type inference failed for: r3v89 */
    /* JADX WARNING: type inference failed for: r3v90 */
    /* JADX WARNING: type inference failed for: r3v91 */
    /* JADX WARNING: type inference failed for: r3v92 */
    /* JADX WARNING: type inference failed for: r3v93 */
    /* JADX WARNING: type inference failed for: r3v94 */
    /* JADX WARNING: type inference failed for: r3v95 */
    /* JADX WARNING: type inference failed for: r3v96 */
    /* JADX WARNING: type inference failed for: r3v97 */
    /* JADX WARNING: type inference failed for: r3v98 */
    /* JADX WARNING: type inference failed for: r3v99 */
    /* JADX WARNING: type inference failed for: r3v100 */
    /* JADX WARNING: type inference failed for: r3v101 */
    /* JADX WARNING: type inference failed for: r3v102 */
    /* JADX WARNING: type inference failed for: r3v103 */
    /* JADX WARNING: type inference failed for: r3v104 */
    /* JADX WARNING: type inference failed for: r3v105 */
    /* JADX WARNING: type inference failed for: r3v106 */
    /* JADX WARNING: type inference failed for: r3v107 */
    /* JADX WARNING: type inference failed for: r3v108 */
    /* JADX WARNING: type inference failed for: r3v109 */
    /* JADX WARNING: type inference failed for: r3v110 */
    /* JADX WARNING: type inference failed for: r3v111 */
    /* JADX WARNING: type inference failed for: r3v112 */
    /* JADX WARNING: type inference failed for: r3v113 */
    /* JADX WARNING: type inference failed for: r3v114 */
    /* JADX WARNING: type inference failed for: r3v115 */
    /* JADX WARNING: type inference failed for: r3v116 */
    /* JADX WARNING: type inference failed for: r3v117 */
    /* JADX WARNING: type inference failed for: r3v118 */
    /* JADX WARNING: type inference failed for: r3v119 */
    /* JADX WARNING: type inference failed for: r3v120 */
    /* JADX WARNING: type inference failed for: r3v121 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(int r11, android.os.Parcel r12, android.os.Parcel r13, int r14) throws android.os.RemoteException {
        /*
            r10 = this;
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IEventHandlerProxy"
            java.lang.String r2 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            r3 = 0
            switch(r11) {
                case 1: goto L_0x0421;
                case 2: goto L_0x0400;
                case 3: goto L_0x03c9;
                case 4: goto L_0x03ab;
                case 5: goto L_0x0381;
                case 6: goto L_0x035f;
                case 7: goto L_0x0352;
                case 8: goto L_0x0341;
                case 9: goto L_0x032c;
                case 10: goto L_0x0306;
                case 11: goto L_0x02f9;
                case 12: goto L_0x02f0;
                case 13: goto L_0x02e7;
                case 14: goto L_0x02de;
                case 15: goto L_0x02c4;
                case 16: goto L_0x02a6;
                case 17: goto L_0x0288;
                case 18: goto L_0x0268;
                case 19: goto L_0x024a;
                case 20: goto L_0x022c;
                case 21: goto L_0x020e;
                case 22: goto L_0x01f0;
                case 23: goto L_0x01e3;
                case 24: goto L_0x01d6;
                case 25: goto L_0x01c5;
                case 26: goto L_0x01b4;
                case 27: goto L_0x019b;
                case 28: goto L_0x018a;
                case 29: goto L_0x0179;
                case 30: goto L_0x0168;
                case 31: goto L_0x013e;
                case 32: goto L_0x0114;
                case 33: goto L_0x00ee;
                case 34: goto L_0x00d0;
                case 35: goto L_0x00b2;
                case 36: goto L_0x0094;
                case 37: goto L_0x008b;
                case 38: goto L_0x0069;
                case 39: goto L_0x0060;
                case 40: goto L_0x0042;
                case 41: goto L_0x0008;
                case 42: goto L_0x0035;
                case 43: goto L_0x002c;
                case 44: goto L_0x001b;
                case 45: goto L_0x000a;
                default: goto L_0x0008;
            }
        L_0x0008:
            r0 = 0
            return r0
        L_0x000a:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            long r2 = r12.readLong()
            r10.setConsentThirdParty(r1, r2)
            goto L_0x0438
        L_0x001b:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            long r2 = r12.readLong()
            r10.setConsent(r1, r2)
            goto L_0x0438
        L_0x002c:
            long r0 = r12.readLong()
            r10.clearMeasurementEnabled(r0)
            goto L_0x0438
        L_0x0035:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            android.os.Bundle r0 = (android.os.Bundle) r0
            r10.setDefaultEventParameters(r0)
            goto L_0x0438
        L_0x0042:
            android.os.IBinder r0 = r12.readStrongBinder()
            if (r0 != 0) goto L_0x0049
            goto L_0x005b
        L_0x0049:
            android.os.IInterface r1 = r0.queryLocalInterface(r2)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzcf
            if (r2 == 0) goto L_0x0056
            r3 = r1
            com.google.android.gms.internal.measurement.zzcf r3 = (com.google.android.gms.internal.measurement.zzcf) r3
            goto L_0x005b
        L_0x0056:
            com.google.android.gms.internal.measurement.zzcd r3 = new com.google.android.gms.internal.measurement.zzcd
            r3.<init>(r0)
        L_0x005b:
            r10.isDataCollectionEnabled(r3)
            goto L_0x0438
        L_0x0060:
            boolean r0 = com.google.android.gms.internal.measurement.zzbo.zzf(r12)
            r10.setDataCollectionEnabled(r0)
            goto L_0x0438
        L_0x0069:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x0070
            goto L_0x0082
        L_0x0070:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcf
            if (r3 == 0) goto L_0x007d
            r3 = r2
            com.google.android.gms.internal.measurement.zzcf r3 = (com.google.android.gms.internal.measurement.zzcf) r3
            goto L_0x0082
        L_0x007d:
            com.google.android.gms.internal.measurement.zzcd r3 = new com.google.android.gms.internal.measurement.zzcd
            r3.<init>(r1)
        L_0x0082:
            int r0 = r12.readInt()
            r10.getTestFlag(r3, r0)
            goto L_0x0438
        L_0x008b:
            java.util.HashMap r0 = com.google.android.gms.internal.measurement.zzbo.zzb(r12)
            r10.initForTests(r0)
            goto L_0x0438
        L_0x0094:
            android.os.IBinder r0 = r12.readStrongBinder()
            if (r0 != 0) goto L_0x009b
            goto L_0x00ad
        L_0x009b:
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzci
            if (r2 == 0) goto L_0x00a8
            r3 = r1
            com.google.android.gms.internal.measurement.zzci r3 = (com.google.android.gms.internal.measurement.zzci) r3
            goto L_0x00ad
        L_0x00a8:
            com.google.android.gms.internal.measurement.zzcg r3 = new com.google.android.gms.internal.measurement.zzcg
            r3.<init>(r0)
        L_0x00ad:
            r10.unregisterOnMeasurementEventListener(r3)
            goto L_0x0438
        L_0x00b2:
            android.os.IBinder r0 = r12.readStrongBinder()
            if (r0 != 0) goto L_0x00b9
            goto L_0x00cb
        L_0x00b9:
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzci
            if (r2 == 0) goto L_0x00c6
            r3 = r1
            com.google.android.gms.internal.measurement.zzci r3 = (com.google.android.gms.internal.measurement.zzci) r3
            goto L_0x00cb
        L_0x00c6:
            com.google.android.gms.internal.measurement.zzcg r3 = new com.google.android.gms.internal.measurement.zzcg
            r3.<init>(r0)
        L_0x00cb:
            r10.registerOnMeasurementEventListener(r3)
            goto L_0x0438
        L_0x00d0:
            android.os.IBinder r0 = r12.readStrongBinder()
            if (r0 != 0) goto L_0x00d7
            goto L_0x00e9
        L_0x00d7:
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzci
            if (r2 == 0) goto L_0x00e4
            r3 = r1
            com.google.android.gms.internal.measurement.zzci r3 = (com.google.android.gms.internal.measurement.zzci) r3
            goto L_0x00e9
        L_0x00e4:
            com.google.android.gms.internal.measurement.zzcg r3 = new com.google.android.gms.internal.measurement.zzcg
            r3.<init>(r0)
        L_0x00e9:
            r10.setEventInterceptor(r3)
            goto L_0x0438
        L_0x00ee:
            int r1 = r12.readInt()
            java.lang.String r2 = r12.readString()
            android.os.IBinder r3 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            android.os.IBinder r4 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r4)
            android.os.IBinder r0 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            r0 = r10
            r0.logHealthData(r1, r2, r3, r4, r5)
            goto L_0x0438
        L_0x0114:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            android.os.IBinder r4 = r12.readStrongBinder()
            if (r4 != 0) goto L_0x0123
            goto L_0x0135
        L_0x0123:
            android.os.IInterface r2 = r4.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcf
            if (r3 == 0) goto L_0x0130
            r3 = r2
            com.google.android.gms.internal.measurement.zzcf r3 = (com.google.android.gms.internal.measurement.zzcf) r3
            goto L_0x0135
        L_0x0130:
            com.google.android.gms.internal.measurement.zzcd r3 = new com.google.android.gms.internal.measurement.zzcd
            r3.<init>(r4)
        L_0x0135:
            long r4 = r12.readLong()
            r10.performAction(r1, r3, r4)
            goto L_0x0438
        L_0x013e:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            android.os.IBinder r4 = r12.readStrongBinder()
            if (r4 != 0) goto L_0x014d
            goto L_0x015f
        L_0x014d:
            android.os.IInterface r2 = r4.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcf
            if (r3 == 0) goto L_0x015a
            r3 = r2
            com.google.android.gms.internal.measurement.zzcf r3 = (com.google.android.gms.internal.measurement.zzcf) r3
            goto L_0x015f
        L_0x015a:
            com.google.android.gms.internal.measurement.zzcd r3 = new com.google.android.gms.internal.measurement.zzcd
            r3.<init>(r4)
        L_0x015f:
            long r4 = r12.readLong()
            r10.onActivitySaveInstanceState(r1, r3, r4)
            goto L_0x0438
        L_0x0168:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r12.readLong()
            r10.onActivityResumed(r1, r2)
            goto L_0x0438
        L_0x0179:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r12.readLong()
            r10.onActivityPaused(r1, r2)
            goto L_0x0438
        L_0x018a:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r12.readLong()
            r10.onActivityDestroyed(r1, r2)
            goto L_0x0438
        L_0x019b:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            long r3 = r12.readLong()
            r10.onActivityCreated(r1, r2, r3)
            goto L_0x0438
        L_0x01b4:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r12.readLong()
            r10.onActivityStopped(r1, r2)
            goto L_0x0438
        L_0x01c5:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r12.readLong()
            r10.onActivityStarted(r1, r2)
            goto L_0x0438
        L_0x01d6:
            java.lang.String r1 = r12.readString()
            long r2 = r12.readLong()
            r10.endAdUnitExposure(r1, r2)
            goto L_0x0438
        L_0x01e3:
            java.lang.String r1 = r12.readString()
            long r2 = r12.readLong()
            r10.beginAdUnitExposure(r1, r2)
            goto L_0x0438
        L_0x01f0:
            android.os.IBinder r0 = r12.readStrongBinder()
            if (r0 != 0) goto L_0x01f7
            goto L_0x0209
        L_0x01f7:
            android.os.IInterface r1 = r0.queryLocalInterface(r2)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzcf
            if (r2 == 0) goto L_0x0204
            r3 = r1
            com.google.android.gms.internal.measurement.zzcf r3 = (com.google.android.gms.internal.measurement.zzcf) r3
            goto L_0x0209
        L_0x0204:
            com.google.android.gms.internal.measurement.zzcd r3 = new com.google.android.gms.internal.measurement.zzcd
            r3.<init>(r0)
        L_0x0209:
            r10.generateEventId(r3)
            goto L_0x0438
        L_0x020e:
            android.os.IBinder r0 = r12.readStrongBinder()
            if (r0 != 0) goto L_0x0215
            goto L_0x0227
        L_0x0215:
            android.os.IInterface r1 = r0.queryLocalInterface(r2)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzcf
            if (r2 == 0) goto L_0x0222
            r3 = r1
            com.google.android.gms.internal.measurement.zzcf r3 = (com.google.android.gms.internal.measurement.zzcf) r3
            goto L_0x0227
        L_0x0222:
            com.google.android.gms.internal.measurement.zzcd r3 = new com.google.android.gms.internal.measurement.zzcd
            r3.<init>(r0)
        L_0x0227:
            r10.getGmpAppId(r3)
            goto L_0x0438
        L_0x022c:
            android.os.IBinder r0 = r12.readStrongBinder()
            if (r0 != 0) goto L_0x0233
            goto L_0x0245
        L_0x0233:
            android.os.IInterface r1 = r0.queryLocalInterface(r2)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzcf
            if (r2 == 0) goto L_0x0240
            r3 = r1
            com.google.android.gms.internal.measurement.zzcf r3 = (com.google.android.gms.internal.measurement.zzcf) r3
            goto L_0x0245
        L_0x0240:
            com.google.android.gms.internal.measurement.zzcd r3 = new com.google.android.gms.internal.measurement.zzcd
            r3.<init>(r0)
        L_0x0245:
            r10.getAppInstanceId(r3)
            goto L_0x0438
        L_0x024a:
            android.os.IBinder r0 = r12.readStrongBinder()
            if (r0 != 0) goto L_0x0251
            goto L_0x0263
        L_0x0251:
            android.os.IInterface r1 = r0.queryLocalInterface(r2)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzcf
            if (r2 == 0) goto L_0x025e
            r3 = r1
            com.google.android.gms.internal.measurement.zzcf r3 = (com.google.android.gms.internal.measurement.zzcf) r3
            goto L_0x0263
        L_0x025e:
            com.google.android.gms.internal.measurement.zzcd r3 = new com.google.android.gms.internal.measurement.zzcd
            r3.<init>(r0)
        L_0x0263:
            r10.getCachedAppInstanceId(r3)
            goto L_0x0438
        L_0x0268:
            android.os.IBinder r0 = r12.readStrongBinder()
            if (r0 != 0) goto L_0x026f
            goto L_0x0283
        L_0x026f:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IStringProvider"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzck
            if (r2 == 0) goto L_0x027e
            r3 = r1
            com.google.android.gms.internal.measurement.zzck r3 = (com.google.android.gms.internal.measurement.zzck) r3
            goto L_0x0283
        L_0x027e:
            com.google.android.gms.internal.measurement.zzcj r3 = new com.google.android.gms.internal.measurement.zzcj
            r3.<init>(r0)
        L_0x0283:
            r10.setInstanceIdProvider(r3)
            goto L_0x0438
        L_0x0288:
            android.os.IBinder r0 = r12.readStrongBinder()
            if (r0 != 0) goto L_0x028f
            goto L_0x02a1
        L_0x028f:
            android.os.IInterface r1 = r0.queryLocalInterface(r2)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzcf
            if (r2 == 0) goto L_0x029c
            r3 = r1
            com.google.android.gms.internal.measurement.zzcf r3 = (com.google.android.gms.internal.measurement.zzcf) r3
            goto L_0x02a1
        L_0x029c:
            com.google.android.gms.internal.measurement.zzcd r3 = new com.google.android.gms.internal.measurement.zzcd
            r3.<init>(r0)
        L_0x02a1:
            r10.getCurrentScreenClass(r3)
            goto L_0x0438
        L_0x02a6:
            android.os.IBinder r0 = r12.readStrongBinder()
            if (r0 != 0) goto L_0x02ad
            goto L_0x02bf
        L_0x02ad:
            android.os.IInterface r1 = r0.queryLocalInterface(r2)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzcf
            if (r2 == 0) goto L_0x02ba
            r3 = r1
            com.google.android.gms.internal.measurement.zzcf r3 = (com.google.android.gms.internal.measurement.zzcf) r3
            goto L_0x02bf
        L_0x02ba:
            com.google.android.gms.internal.measurement.zzcd r3 = new com.google.android.gms.internal.measurement.zzcd
            r3.<init>(r0)
        L_0x02bf:
            r10.getCurrentScreenName(r3)
            goto L_0x0438
        L_0x02c4:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            java.lang.String r2 = r12.readString()
            java.lang.String r3 = r12.readString()
            long r4 = r12.readLong()
            r0 = r10
            r0.setCurrentScreen(r1, r2, r3, r4)
            goto L_0x0438
        L_0x02de:
            long r0 = r12.readLong()
            r10.setSessionTimeoutDuration(r0)
            goto L_0x0438
        L_0x02e7:
            long r0 = r12.readLong()
            r10.setMinimumSessionDuration(r0)
            goto L_0x0438
        L_0x02f0:
            long r0 = r12.readLong()
            r10.resetAnalyticsData(r0)
            goto L_0x0438
        L_0x02f9:
            boolean r1 = com.google.android.gms.internal.measurement.zzbo.zzf(r12)
            long r2 = r12.readLong()
            r10.setMeasurementEnabled(r1, r2)
            goto L_0x0438
        L_0x0306:
            java.lang.String r1 = r12.readString()
            java.lang.String r4 = r12.readString()
            android.os.IBinder r0 = r12.readStrongBinder()
            if (r0 != 0) goto L_0x0315
            goto L_0x0327
        L_0x0315:
            android.os.IInterface r2 = r0.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcf
            if (r3 == 0) goto L_0x0322
            r3 = r2
            com.google.android.gms.internal.measurement.zzcf r3 = (com.google.android.gms.internal.measurement.zzcf) r3
            goto L_0x0327
        L_0x0322:
            com.google.android.gms.internal.measurement.zzcd r3 = new com.google.android.gms.internal.measurement.zzcd
            r3.<init>(r0)
        L_0x0327:
            r10.getConditionalUserProperties(r1, r4, r3)
            goto L_0x0438
        L_0x032c:
            java.lang.String r1 = r12.readString()
            java.lang.String r2 = r12.readString()
            android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r3)
            android.os.Bundle r0 = (android.os.Bundle) r0
            r10.clearConditionalUserProperty(r1, r2, r0)
            goto L_0x0438
        L_0x0341:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            long r2 = r12.readLong()
            r10.setConditionalUserProperty(r1, r2)
            goto L_0x0438
        L_0x0352:
            java.lang.String r1 = r12.readString()
            long r2 = r12.readLong()
            r10.setUserId(r1, r2)
            goto L_0x0438
        L_0x035f:
            java.lang.String r1 = r12.readString()
            android.os.IBinder r0 = r12.readStrongBinder()
            if (r0 != 0) goto L_0x036a
            goto L_0x037c
        L_0x036a:
            android.os.IInterface r2 = r0.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcf
            if (r3 == 0) goto L_0x0377
            r3 = r2
            com.google.android.gms.internal.measurement.zzcf r3 = (com.google.android.gms.internal.measurement.zzcf) r3
            goto L_0x037c
        L_0x0377:
            com.google.android.gms.internal.measurement.zzcd r3 = new com.google.android.gms.internal.measurement.zzcd
            r3.<init>(r0)
        L_0x037c:
            r10.getMaxUserProperties(r1, r3)
            goto L_0x0438
        L_0x0381:
            java.lang.String r1 = r12.readString()
            java.lang.String r4 = r12.readString()
            boolean r5 = com.google.android.gms.internal.measurement.zzbo.zzf(r12)
            android.os.IBinder r0 = r12.readStrongBinder()
            if (r0 != 0) goto L_0x0394
            goto L_0x03a6
        L_0x0394:
            android.os.IInterface r2 = r0.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcf
            if (r3 == 0) goto L_0x03a1
            r3 = r2
            com.google.android.gms.internal.measurement.zzcf r3 = (com.google.android.gms.internal.measurement.zzcf) r3
            goto L_0x03a6
        L_0x03a1:
            com.google.android.gms.internal.measurement.zzcd r3 = new com.google.android.gms.internal.measurement.zzcd
            r3.<init>(r0)
        L_0x03a6:
            r10.getUserProperties(r1, r4, r5, r3)
            goto L_0x0438
        L_0x03ab:
            java.lang.String r1 = r12.readString()
            java.lang.String r2 = r12.readString()
            android.os.IBinder r3 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            boolean r4 = com.google.android.gms.internal.measurement.zzbo.zzf(r12)
            long r5 = r12.readLong()
            r0 = r10
            r0.setUserProperty(r1, r2, r3, r4, r5)
            goto L_0x0438
        L_0x03c9:
            java.lang.String r1 = r12.readString()
            java.lang.String r4 = r12.readString()
            android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
            android.os.Parcelable r5 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r5)
            android.os.Bundle r5 = (android.os.Bundle) r5
            android.os.IBinder r6 = r12.readStrongBinder()
            if (r6 != 0) goto L_0x03e1
            r6 = r3
            goto L_0x03f3
        L_0x03e1:
            android.os.IInterface r2 = r6.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcf
            if (r3 == 0) goto L_0x03ed
            com.google.android.gms.internal.measurement.zzcf r2 = (com.google.android.gms.internal.measurement.zzcf) r2
            goto L_0x03f2
        L_0x03ed:
            com.google.android.gms.internal.measurement.zzcd r2 = new com.google.android.gms.internal.measurement.zzcd
            r2.<init>(r6)
        L_0x03f2:
            r6 = r2
        L_0x03f3:
            long r8 = r12.readLong()
            r0 = r10
            r2 = r4
            r3 = r5
            r4 = r6
            r5 = r8
            r0.logEventAndBundle(r1, r2, r3, r4, r5)
            goto L_0x0438
        L_0x0400:
            java.lang.String r1 = r12.readString()
            java.lang.String r2 = r12.readString()
            android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r3)
            android.os.Bundle r3 = (android.os.Bundle) r3
            boolean r4 = com.google.android.gms.internal.measurement.zzbo.zzf(r12)
            boolean r5 = com.google.android.gms.internal.measurement.zzbo.zzf(r12)
            long r6 = r12.readLong()
            r0 = r10
            r0.logEvent(r1, r2, r3, r4, r5, r6)
            goto L_0x0438
        L_0x0421:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzcl> r2 = com.google.android.gms.internal.measurement.zzcl.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r2)
            com.google.android.gms.internal.measurement.zzcl r2 = (com.google.android.gms.internal.measurement.zzcl) r2
            long r3 = r12.readLong()
            r10.initialize(r1, r2, r3)
        L_0x0438:
            r13.writeNoException()
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzcb.zza(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
