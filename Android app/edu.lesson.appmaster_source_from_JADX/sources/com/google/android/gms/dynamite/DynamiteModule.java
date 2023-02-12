package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public final class DynamiteModule {
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzi();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zzj();
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzk();
    public static final VersionPolicy PREFER_LOCAL = new zzg();
    public static final VersionPolicy PREFER_REMOTE = new zzf();
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new zzh();
    public static final VersionPolicy zza = new zzl();
    private static Boolean zzb;
    private static String zzc;
    private static boolean zzd;
    private static int zze = -1;
    private static final ThreadLocal<zzn> zzf = new ThreadLocal<>();
    private static final ThreadLocal<Long> zzg = new zzd();
    private static final VersionPolicy.IVersions zzh = new zze();
    private static zzq zzj;
    private static zzr zzk;
    private final Context zzi;

    /* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    /* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
    public static class LoadingException extends Exception {
        /* synthetic */ LoadingException(String str, zzp zzp) {
            super(str);
        }

        /* synthetic */ LoadingException(String str, Throwable th, zzp zzp) {
            super(str, th);
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
    public interface VersionPolicy {

        /* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
        public interface IVersions {
            int zza(Context context, String str);

            int zzb(Context context, String str, boolean z) throws LoadingException;
        }

        /* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
        public static class SelectionResult {
            public int localVersion = 0;
            public int remoteVersion = 0;
            public int selection = 0;
        }

        SelectionResult selectModule(Context context, String str, IVersions iVersions) throws LoadingException;
    }

    private DynamiteModule(Context context) {
        Preconditions.checkNotNull(context);
        this.zzi = context;
    }

    public static int getLocalVersion(Context context, String moduleId) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            StringBuilder sb = new StringBuilder(String.valueOf(moduleId).length() + 61);
            sb.append("com.google.android.gms.dynamite.descriptors.");
            sb.append(moduleId);
            sb.append(".");
            sb.append("ModuleDescriptor");
            Class<?> loadClass = classLoader.loadClass(sb.toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (Objects.equal(declaredField.get((Object) null), moduleId)) {
                return declaredField2.getInt((Object) null);
            }
            String valueOf = String.valueOf(declaredField.get((Object) null));
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 51 + String.valueOf(moduleId).length());
            sb2.append("Module descriptor id '");
            sb2.append(valueOf);
            sb2.append("' didn't match expected id '");
            sb2.append(moduleId);
            sb2.append("'");
            Log.e("DynamiteModule", sb2.toString());
            return 0;
        } catch (ClassNotFoundException e) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(moduleId).length() + 45);
            sb3.append("Local module descriptor class for ");
            sb3.append(moduleId);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e2) {
            String valueOf2 = String.valueOf(e2.getMessage());
            Log.e("DynamiteModule", valueOf2.length() != 0 ? "Failed to load module descriptor class: ".concat(valueOf2) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    public static int getRemoteVersion(Context context, String moduleId) {
        return zza(context, moduleId, false);
    }

    public static DynamiteModule load(Context context, VersionPolicy policy, String moduleId) throws LoadingException {
        int i;
        Boolean bool;
        DynamiteModule dynamiteModule;
        IObjectWrapper iObjectWrapper;
        zzr zzr;
        boolean z;
        Boolean valueOf;
        IObjectWrapper iObjectWrapper2;
        Context context2 = context;
        VersionPolicy versionPolicy = policy;
        String str = moduleId;
        Class<DynamiteModule> cls = DynamiteModule.class;
        ThreadLocal<zzn> threadLocal = zzf;
        zzn zzn = threadLocal.get();
        zzn zzn2 = new zzn((zzm) null);
        threadLocal.set(zzn2);
        ThreadLocal<Long> threadLocal2 = zzg;
        long longValue = threadLocal2.get().longValue();
        try {
            threadLocal2.set(Long.valueOf(SystemClock.elapsedRealtime()));
            VersionPolicy.SelectionResult selectModule = versionPolicy.selectModule(context2, str, zzh);
            int i2 = selectModule.localVersion;
            int i3 = selectModule.remoteVersion;
            StringBuilder sb = new StringBuilder(String.valueOf(moduleId).length() + 68 + String.valueOf(moduleId).length());
            sb.append("Considering local module ");
            sb.append(str);
            sb.append(":");
            sb.append(i2);
            sb.append(" and remote module ");
            sb.append(str);
            sb.append(":");
            sb.append(i3);
            Log.i("DynamiteModule", sb.toString());
            int i4 = selectModule.selection;
            if (i4 != 0) {
                if (i4 == -1) {
                    if (selectModule.localVersion != 0) {
                        i4 = -1;
                    }
                }
                if (!(i4 == 1 && selectModule.remoteVersion == 0)) {
                    if (i4 == -1) {
                        DynamiteModule zzc2 = zzc(context2, str);
                        if (longValue == 0) {
                            threadLocal2.remove();
                        } else {
                            threadLocal2.set(Long.valueOf(longValue));
                        }
                        Cursor cursor = zzn2.zza;
                        if (cursor != null) {
                            cursor.close();
                        }
                        threadLocal.set(zzn);
                        return zzc2;
                    } else if (i4 == 1) {
                        try {
                            int i5 = selectModule.remoteVersion;
                            try {
                                synchronized (cls) {
                                    bool = zzb;
                                }
                                if (bool != null) {
                                    if (bool.booleanValue()) {
                                        StringBuilder sb2 = new StringBuilder(String.valueOf(moduleId).length() + 51);
                                        sb2.append("Selected remote version of ");
                                        sb2.append(str);
                                        sb2.append(", version >= ");
                                        sb2.append(i5);
                                        Log.i("DynamiteModule", sb2.toString());
                                        synchronized (cls) {
                                            zzr = zzk;
                                        }
                                        if (zzr != null) {
                                            zzn zzn3 = threadLocal.get();
                                            if (zzn3 == null || zzn3.zza == null) {
                                                throw new LoadingException("No result cursor", (zzp) null);
                                            }
                                            Context applicationContext = context.getApplicationContext();
                                            Cursor cursor2 = zzn3.zza;
                                            ObjectWrapper.wrap(null);
                                            synchronized (cls) {
                                                if (zze >= 2) {
                                                    z = true;
                                                } else {
                                                    z = false;
                                                }
                                                valueOf = Boolean.valueOf(z);
                                            }
                                            if (valueOf.booleanValue()) {
                                                Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
                                                iObjectWrapper2 = zzr.zzf(ObjectWrapper.wrap(applicationContext), str, i5, ObjectWrapper.wrap(cursor2));
                                            } else {
                                                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                                                iObjectWrapper2 = zzr.zze(ObjectWrapper.wrap(applicationContext), str, i5, ObjectWrapper.wrap(cursor2));
                                            }
                                            Context context3 = (Context) ObjectWrapper.unwrap(iObjectWrapper2);
                                            if (context3 != null) {
                                                dynamiteModule = new DynamiteModule(context3);
                                            } else {
                                                throw new LoadingException("Failed to get module context", (zzp) null);
                                            }
                                        } else {
                                            throw new LoadingException("DynamiteLoaderV2 was not cached.", (zzp) null);
                                        }
                                    } else {
                                        StringBuilder sb3 = new StringBuilder(String.valueOf(moduleId).length() + 51);
                                        sb3.append("Selected remote version of ");
                                        sb3.append(str);
                                        sb3.append(", version >= ");
                                        sb3.append(i5);
                                        Log.i("DynamiteModule", sb3.toString());
                                        zzq zzf2 = zzf(context);
                                        if (zzf2 != null) {
                                            int zze2 = zzf2.zze();
                                            if (zze2 >= 3) {
                                                zzn zzn4 = threadLocal.get();
                                                if (zzn4 != null) {
                                                    iObjectWrapper = zzf2.zzi(ObjectWrapper.wrap(context), str, i5, ObjectWrapper.wrap(zzn4.zza));
                                                } else {
                                                    throw new LoadingException("No cached result cursor holder", (zzp) null);
                                                }
                                            } else if (zze2 == 2) {
                                                Log.w("DynamiteModule", "IDynamite loader version = 2");
                                                iObjectWrapper = zzf2.zzj(ObjectWrapper.wrap(context), str, i5);
                                            } else {
                                                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                                                iObjectWrapper = zzf2.zzh(ObjectWrapper.wrap(context), str, i5);
                                            }
                                            if (ObjectWrapper.unwrap(iObjectWrapper) != null) {
                                                dynamiteModule = new DynamiteModule((Context) ObjectWrapper.unwrap(iObjectWrapper));
                                            } else {
                                                throw new LoadingException("Failed to load remote module.", (zzp) null);
                                            }
                                        } else {
                                            throw new LoadingException("Failed to create IDynamiteLoader.", (zzp) null);
                                        }
                                    }
                                    if (longValue == 0) {
                                        threadLocal2.remove();
                                    } else {
                                        threadLocal2.set(Long.valueOf(longValue));
                                    }
                                    Cursor cursor3 = zzn2.zza;
                                    if (cursor3 != null) {
                                        cursor3.close();
                                    }
                                    threadLocal.set(zzn);
                                    return dynamiteModule;
                                }
                                throw new LoadingException("Failed to determine which loading route to use.", (zzp) null);
                            } catch (RemoteException e) {
                                throw new LoadingException("Failed to load remote module.", e, (zzp) null);
                            } catch (LoadingException e2) {
                                throw e2;
                            } catch (Throwable th) {
                                CrashUtils.addDynamiteErrorToDropBox(context2, th);
                                throw new LoadingException("Failed to load remote module.", th, (zzp) null);
                            }
                        } catch (LoadingException e3) {
                            String valueOf2 = String.valueOf(e3.getMessage());
                            Log.w("DynamiteModule", valueOf2.length() != 0 ? "Failed to load remote module: ".concat(valueOf2) : new String("Failed to load remote module: "));
                            int i6 = selectModule.localVersion;
                            if (i6 != 0) {
                                if (policy.selectModule(context2, str, new zzo(i6, 0)).selection == -1) {
                                    DynamiteModule zzc3 = zzc(context2, str);
                                    if (i != 0) {
                                        zzg.set(Long.valueOf(longValue));
                                    }
                                    return zzc3;
                                }
                            }
                            throw new LoadingException("Remote load failed. No local fallback found.", e3, (zzp) null);
                        }
                    } else {
                        StringBuilder sb4 = new StringBuilder(47);
                        sb4.append("VersionPolicy returned invalid code:");
                        sb4.append(i4);
                        throw new LoadingException(sb4.toString(), (zzp) null);
                    }
                }
            }
            int i7 = selectModule.localVersion;
            int i8 = selectModule.remoteVersion;
            StringBuilder sb5 = new StringBuilder(String.valueOf(moduleId).length() + 92);
            sb5.append("No acceptable module ");
            sb5.append(str);
            sb5.append(" found. Local version is ");
            sb5.append(i7);
            sb5.append(" and remote version is ");
            sb5.append(i8);
            sb5.append(".");
            throw new LoadingException(sb5.toString(), (zzp) null);
        } finally {
            if (longValue == 0) {
                zzg.remove();
            } else {
                zzg.set(Long.valueOf(longValue));
            }
            Cursor cursor4 = zzn2.zza;
            if (cursor4 != null) {
                cursor4.close();
            }
            zzf.set(zzn);
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:44:0x0092=Splitter:B:44:0x0092, B:19:0x003c=Splitter:B:19:0x003c} */
    public static int zza(android.content.Context r9, java.lang.String r10, boolean r11) {
        /*
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r0 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r0)     // Catch:{ all -> 0x01d1 }
            java.lang.Boolean r1 = zzb     // Catch:{ all -> 0x01ce }
            r2 = 0
            if (r1 != 0) goto L_0x00d8
            android.content.Context r1 = r9.getApplicationContext()     // Catch:{ ClassNotFoundException -> 0x00b3, IllegalAccessException -> 0x00b1, NoSuchFieldException -> 0x00af }
            java.lang.ClassLoader r1 = r1.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x00b3, IllegalAccessException -> 0x00b1, NoSuchFieldException -> 0x00af }
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule$DynamiteLoaderClassLoader> r3 = com.google.android.gms.dynamite.DynamiteModule.DynamiteLoaderClassLoader.class
            java.lang.String r3 = r3.getName()     // Catch:{ ClassNotFoundException -> 0x00b3, IllegalAccessException -> 0x00b1, NoSuchFieldException -> 0x00af }
            java.lang.Class r1 = r1.loadClass(r3)     // Catch:{ ClassNotFoundException -> 0x00b3, IllegalAccessException -> 0x00b1, NoSuchFieldException -> 0x00af }
            java.lang.String r3 = "sClassLoader"
            java.lang.reflect.Field r1 = r1.getDeclaredField(r3)     // Catch:{ ClassNotFoundException -> 0x00b3, IllegalAccessException -> 0x00b1, NoSuchFieldException -> 0x00af }
            java.lang.Class r3 = r1.getDeclaringClass()     // Catch:{ ClassNotFoundException -> 0x00b3, IllegalAccessException -> 0x00b1, NoSuchFieldException -> 0x00af }
            monitor-enter(r3)     // Catch:{ ClassNotFoundException -> 0x00b3, IllegalAccessException -> 0x00b1, NoSuchFieldException -> 0x00af }
            java.lang.Object r4 = r1.get(r2)     // Catch:{ all -> 0x00ac }
            java.lang.ClassLoader r4 = (java.lang.ClassLoader) r4     // Catch:{ all -> 0x00ac }
            if (r4 == 0) goto L_0x003f
            java.lang.ClassLoader r1 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x00ac }
            if (r4 != r1) goto L_0x0037
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00ac }
            goto L_0x00aa
        L_0x0037:
            zzd(r4)     // Catch:{ LoadingException -> 0x003b }
            goto L_0x003c
        L_0x003b:
            r1 = move-exception
        L_0x003c:
            java.lang.Boolean r1 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x00ac }
            goto L_0x00aa
        L_0x003f:
            boolean r4 = zzd     // Catch:{ all -> 0x00ac }
            if (r4 != 0) goto L_0x00a0
            java.lang.Boolean r4 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x00ac }
            boolean r4 = r4.equals(r2)     // Catch:{ all -> 0x00ac }
            if (r4 == 0) goto L_0x004c
            goto L_0x00a0
        L_0x004c:
            int r4 = zzb(r9, r10, r11)     // Catch:{ LoadingException -> 0x0095 }
            java.lang.String r5 = zzc     // Catch:{ LoadingException -> 0x0095 }
            if (r5 == 0) goto L_0x0092
            boolean r5 = r5.isEmpty()     // Catch:{ LoadingException -> 0x0095 }
            if (r5 == 0) goto L_0x005b
            goto L_0x0092
        L_0x005b:
            java.lang.ClassLoader r5 = com.google.android.gms.dynamite.zzb.zza()     // Catch:{ LoadingException -> 0x0095 }
            if (r5 == 0) goto L_0x0062
            goto L_0x0085
        L_0x0062:
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ LoadingException -> 0x0095 }
            r6 = 29
            if (r5 < r6) goto L_0x0077
            dalvik.system.DelegateLastClassLoader r5 = new dalvik.system.DelegateLastClassLoader     // Catch:{ LoadingException -> 0x0095 }
            java.lang.String r6 = zzc     // Catch:{ LoadingException -> 0x0095 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r6)     // Catch:{ LoadingException -> 0x0095 }
            java.lang.ClassLoader r7 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ LoadingException -> 0x0095 }
            r5.<init>(r6, r7)     // Catch:{ LoadingException -> 0x0095 }
            goto L_0x0085
        L_0x0077:
            com.google.android.gms.dynamite.zzc r5 = new com.google.android.gms.dynamite.zzc     // Catch:{ LoadingException -> 0x0095 }
            java.lang.String r6 = zzc     // Catch:{ LoadingException -> 0x0095 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r6)     // Catch:{ LoadingException -> 0x0095 }
            java.lang.ClassLoader r7 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ LoadingException -> 0x0095 }
            r5.<init>(r6, r7)     // Catch:{ LoadingException -> 0x0095 }
        L_0x0085:
            zzd(r5)     // Catch:{ LoadingException -> 0x0095 }
            r1.set(r2, r5)     // Catch:{ LoadingException -> 0x0095 }
            java.lang.Boolean r5 = java.lang.Boolean.TRUE     // Catch:{ LoadingException -> 0x0095 }
            zzb = r5     // Catch:{ LoadingException -> 0x0095 }
            monitor-exit(r3)     // Catch:{ all -> 0x00ac }
            monitor-exit(r0)     // Catch:{ all -> 0x01ce }
            return r4
        L_0x0092:
            monitor-exit(r3)     // Catch:{ all -> 0x00ac }
            monitor-exit(r0)     // Catch:{ all -> 0x01ce }
            return r4
        L_0x0095:
            r4 = move-exception
            java.lang.ClassLoader r4 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x00ac }
            r1.set(r2, r4)     // Catch:{ all -> 0x00ac }
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00ac }
            goto L_0x00aa
        L_0x00a0:
            java.lang.ClassLoader r4 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x00ac }
            r1.set(r2, r4)     // Catch:{ all -> 0x00ac }
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00ac }
        L_0x00aa:
            monitor-exit(r3)     // Catch:{ all -> 0x00ac }
            goto L_0x00d6
        L_0x00ac:
            r1 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x00ac }
            throw r1     // Catch:{ ClassNotFoundException -> 0x00b3, IllegalAccessException -> 0x00b1, NoSuchFieldException -> 0x00af }
        L_0x00af:
            r1 = move-exception
            goto L_0x00b4
        L_0x00b1:
            r1 = move-exception
            goto L_0x00b4
        L_0x00b3:
            r1 = move-exception
        L_0x00b4:
            java.lang.String r3 = "DynamiteModule"
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x01ce }
            int r4 = r1.length()     // Catch:{ all -> 0x01ce }
            int r4 = r4 + 30
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ce }
            r5.<init>(r4)     // Catch:{ all -> 0x01ce }
            java.lang.String r4 = "Failed to load module via V2: "
            r5.append(r4)     // Catch:{ all -> 0x01ce }
            r5.append(r1)     // Catch:{ all -> 0x01ce }
            java.lang.String r1 = r5.toString()     // Catch:{ all -> 0x01ce }
            android.util.Log.w(r3, r1)     // Catch:{ all -> 0x01ce }
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x01ce }
        L_0x00d6:
            zzb = r1     // Catch:{ all -> 0x01ce }
        L_0x00d8:
            monitor-exit(r0)     // Catch:{ all -> 0x01ce }
            boolean r0 = r1.booleanValue()     // Catch:{ all -> 0x01d1 }
            r1 = 0
            if (r0 == 0) goto L_0x0106
            int r9 = zzb(r9, r10, r11)     // Catch:{ LoadingException -> 0x00e5 }
            return r9
        L_0x00e5:
            r10 = move-exception
            java.lang.String r11 = "DynamiteModule"
            java.lang.String r0 = "Failed to retrieve remote module version: "
            java.lang.String r10 = r10.getMessage()     // Catch:{ all -> 0x01d1 }
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x01d1 }
            int r2 = r10.length()     // Catch:{ all -> 0x01d1 }
            if (r2 == 0) goto L_0x00fd
            java.lang.String r10 = r0.concat(r10)     // Catch:{ all -> 0x01d1 }
            goto L_0x0102
        L_0x00fd:
            java.lang.String r10 = new java.lang.String     // Catch:{ all -> 0x01d1 }
            r10.<init>(r0)     // Catch:{ all -> 0x01d1 }
        L_0x0102:
            android.util.Log.w(r11, r10)     // Catch:{ all -> 0x01d1 }
            return r1
        L_0x0106:
            com.google.android.gms.dynamite.zzq r3 = zzf(r9)     // Catch:{ all -> 0x01d1 }
            if (r3 != 0) goto L_0x010e
            goto L_0x01c5
        L_0x010e:
            int r0 = r3.zze()     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            r4 = 3
            if (r0 < r4) goto L_0x0179
            java.lang.ThreadLocal<com.google.android.gms.dynamite.zzn> r0 = zzf     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            java.lang.Object r0 = r0.get()     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            com.google.android.gms.dynamite.zzn r0 = (com.google.android.gms.dynamite.zzn) r0     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            if (r0 == 0) goto L_0x0129
            android.database.Cursor r0 = r0.zza     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            if (r0 == 0) goto L_0x0129
            int r1 = r0.getInt(r1)     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            goto L_0x01c5
        L_0x0129:
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r9)     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            java.lang.ThreadLocal<java.lang.Long> r0 = zzg     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            java.lang.Object r0 = r0.get()     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            long r7 = r0.longValue()     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            r5 = r10
            r6 = r11
            com.google.android.gms.dynamic.IObjectWrapper r10 = r3.zzk(r4, r5, r6, r7)     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            java.lang.Object r10 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r10)     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            android.database.Cursor r10 = (android.database.Cursor) r10     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            if (r10 == 0) goto L_0x0166
            boolean r11 = r10.moveToFirst()     // Catch:{ RemoteException -> 0x0176, all -> 0x0173 }
            if (r11 != 0) goto L_0x014e
            goto L_0x0166
        L_0x014e:
            int r11 = r10.getInt(r1)     // Catch:{ RemoteException -> 0x0176, all -> 0x0173 }
            if (r11 <= 0) goto L_0x015c
            boolean r0 = zze(r10)     // Catch:{ RemoteException -> 0x0176, all -> 0x0173 }
            if (r0 == 0) goto L_0x015c
            goto L_0x015e
        L_0x015c:
            r2 = r10
        L_0x015e:
            if (r2 == 0) goto L_0x0163
            r2.close()     // Catch:{ all -> 0x01d1 }
        L_0x0163:
            r1 = r11
            goto L_0x01c5
        L_0x0166:
            java.lang.String r11 = "DynamiteModule"
            java.lang.String r0 = "Failed to retrieve remote module version."
            android.util.Log.w(r11, r0)     // Catch:{ RemoteException -> 0x0176, all -> 0x0173 }
            if (r10 == 0) goto L_0x01c5
            r10.close()     // Catch:{ all -> 0x01d1 }
            goto L_0x01c5
        L_0x0173:
            r11 = move-exception
            r2 = r10
            goto L_0x01c8
        L_0x0176:
            r11 = move-exception
            r2 = r10
            goto L_0x01a1
        L_0x0179:
            r4 = 2
            if (r0 != r4) goto L_0x018c
            java.lang.String r0 = "DynamiteModule"
            java.lang.String r4 = "IDynamite loader version = 2, no high precision latency measurement."
            android.util.Log.w(r0, r4)     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r9)     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            int r1 = r3.zzg(r0, r10, r11)     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            goto L_0x01c5
        L_0x018c:
            java.lang.String r0 = "DynamiteModule"
            java.lang.String r4 = "IDynamite loader version < 2, falling back to getModuleVersion2"
            android.util.Log.w(r0, r4)     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r9)     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            int r1 = r3.zzf(r0, r10, r11)     // Catch:{ RemoteException -> 0x019f, all -> 0x019c }
            goto L_0x01c5
        L_0x019c:
            r10 = move-exception
            r11 = r10
            goto L_0x01c8
        L_0x019f:
            r10 = move-exception
            r11 = r10
        L_0x01a1:
            java.lang.String r10 = "DynamiteModule"
            java.lang.String r0 = "Failed to retrieve remote module version: "
            java.lang.String r11 = r11.getMessage()     // Catch:{ all -> 0x01c6 }
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x01c6 }
            int r3 = r11.length()     // Catch:{ all -> 0x01c6 }
            if (r3 == 0) goto L_0x01b8
            java.lang.String r11 = r0.concat(r11)     // Catch:{ all -> 0x01c6 }
            goto L_0x01bd
        L_0x01b8:
            java.lang.String r11 = new java.lang.String     // Catch:{ all -> 0x01c6 }
            r11.<init>(r0)     // Catch:{ all -> 0x01c6 }
        L_0x01bd:
            android.util.Log.w(r10, r11)     // Catch:{ all -> 0x01c6 }
            if (r2 == 0) goto L_0x01c5
            r2.close()     // Catch:{ all -> 0x01d1 }
        L_0x01c5:
            return r1
        L_0x01c6:
            r10 = move-exception
            r11 = r10
        L_0x01c8:
            if (r2 == 0) goto L_0x01cd
            r2.close()     // Catch:{ all -> 0x01d1 }
        L_0x01cd:
            throw r11     // Catch:{ all -> 0x01d1 }
        L_0x01ce:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x01ce }
            throw r10     // Catch:{ all -> 0x01d1 }
        L_0x01d1:
            r10 = move-exception
            com.google.android.gms.common.util.CrashUtils.addDynamiteErrorToDropBox(r9, r10)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zza(android.content.Context, java.lang.String, boolean):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x00c5 A[Catch:{ all -> 0x00ce }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00c6 A[Catch:{ all -> 0x00ce }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00d2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zzb(android.content.Context r10, java.lang.String r11, boolean r12) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {
        /*
            r0 = 0
            java.lang.ThreadLocal<java.lang.Long> r1 = zzg     // Catch:{ Exception -> 0x00be, all -> 0x00bb }
            java.lang.Object r1 = r1.get()     // Catch:{ Exception -> 0x00be, all -> 0x00bb }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ Exception -> 0x00be, all -> 0x00bb }
            long r1 = r1.longValue()     // Catch:{ Exception -> 0x00be, all -> 0x00bb }
            android.content.ContentResolver r3 = r10.getContentResolver()     // Catch:{ Exception -> 0x00be, all -> 0x00bb }
            java.lang.String r10 = "api_force_staging"
            java.lang.String r4 = "api"
            r9 = 1
            if (r9 == r12) goto L_0x0019
            r10 = r4
        L_0x0019:
            android.net.Uri$Builder r12 = new android.net.Uri$Builder     // Catch:{ Exception -> 0x00be, all -> 0x00bb }
            r12.<init>()     // Catch:{ Exception -> 0x00be, all -> 0x00bb }
            java.lang.String r4 = "content"
            android.net.Uri$Builder r12 = r12.scheme(r4)     // Catch:{ Exception -> 0x00be, all -> 0x00bb }
            java.lang.String r4 = "com.google.android.gms.chimera"
            android.net.Uri$Builder r12 = r12.authority(r4)     // Catch:{ Exception -> 0x00be, all -> 0x00bb }
            android.net.Uri$Builder r10 = r12.path(r10)     // Catch:{ Exception -> 0x00be, all -> 0x00bb }
            android.net.Uri$Builder r10 = r10.appendPath(r11)     // Catch:{ Exception -> 0x00be, all -> 0x00bb }
            java.lang.String r11 = "requestStartTime"
            java.lang.String r12 = java.lang.String.valueOf(r1)     // Catch:{ Exception -> 0x00be, all -> 0x00bb }
            android.net.Uri$Builder r10 = r10.appendQueryParameter(r11, r12)     // Catch:{ Exception -> 0x00be, all -> 0x00bb }
            android.net.Uri r4 = r10.build()     // Catch:{ Exception -> 0x00be, all -> 0x00bb }
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r10 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x00be, all -> 0x00bb }
            if (r10 == 0) goto L_0x00a7
            boolean r11 = r10.moveToFirst()     // Catch:{ Exception -> 0x00b9, all -> 0x00b6 }
            if (r11 == 0) goto L_0x00a7
            r11 = 0
            int r12 = r10.getInt(r11)     // Catch:{ Exception -> 0x00b9, all -> 0x00b6 }
            if (r12 <= 0) goto L_0x0091
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r1 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r1)     // Catch:{ Exception -> 0x00b9, all -> 0x00b6 }
            r2 = 2
            java.lang.String r2 = r10.getString(r2)     // Catch:{ all -> 0x008e }
            zzc = r2     // Catch:{ all -> 0x008e }
            java.lang.String r2 = "loaderVersion"
            int r2 = r10.getColumnIndex(r2)     // Catch:{ all -> 0x008e }
            if (r2 < 0) goto L_0x0070
            int r2 = r10.getInt(r2)     // Catch:{ all -> 0x008e }
            zze = r2     // Catch:{ all -> 0x008e }
        L_0x0070:
            java.lang.String r2 = "disableStandaloneDynamiteLoader"
            int r2 = r10.getColumnIndex(r2)     // Catch:{ all -> 0x008e }
            if (r2 < 0) goto L_0x0084
            int r2 = r10.getInt(r2)     // Catch:{ all -> 0x008e }
            if (r2 == 0) goto L_0x007f
            goto L_0x0080
        L_0x007f:
            r9 = r11
        L_0x0080:
            zzd = r9     // Catch:{ all -> 0x008e }
            r11 = r9
            goto L_0x0085
        L_0x0084:
        L_0x0085:
            monitor-exit(r1)     // Catch:{ all -> 0x008e }
            boolean r1 = zze(r10)     // Catch:{ Exception -> 0x00b9, all -> 0x00b6 }
            if (r1 == 0) goto L_0x0092
            r10 = r0
            goto L_0x0092
        L_0x008e:
            r11 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x008e }
            throw r11     // Catch:{ Exception -> 0x00b9, all -> 0x00b6 }
        L_0x0091:
        L_0x0092:
            if (r11 != 0) goto L_0x009a
            if (r10 == 0) goto L_0x0099
            r10.close()
        L_0x0099:
            return r12
        L_0x009a:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r11 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ Exception -> 0x00a5, all -> 0x00a2 }
            java.lang.String r12 = "forcing fallback to container DynamiteLoader impl"
            r11.<init>(r12, r0)     // Catch:{ Exception -> 0x00a5, all -> 0x00a2 }
            throw r11     // Catch:{ Exception -> 0x00a5, all -> 0x00a2 }
        L_0x00a2:
            r11 = move-exception
            r0 = r10
            goto L_0x00d0
        L_0x00a5:
            r11 = move-exception
            goto L_0x00c1
        L_0x00a7:
            java.lang.String r11 = "DynamiteModule"
            java.lang.String r12 = "Failed to retrieve remote module version."
            android.util.Log.w(r11, r12)     // Catch:{ Exception -> 0x00b9, all -> 0x00b6 }
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r11 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ Exception -> 0x00b9, all -> 0x00b6 }
            java.lang.String r12 = "Failed to connect to dynamite module ContentResolver."
            r11.<init>(r12, r0)     // Catch:{ Exception -> 0x00b9, all -> 0x00b6 }
            throw r11     // Catch:{ Exception -> 0x00b9, all -> 0x00b6 }
        L_0x00b6:
            r11 = move-exception
            r0 = r10
            goto L_0x00d0
        L_0x00b9:
            r11 = move-exception
            goto L_0x00c1
        L_0x00bb:
            r10 = move-exception
            r11 = r10
            goto L_0x00d0
        L_0x00be:
            r10 = move-exception
            r11 = r10
            r10 = r0
        L_0x00c1:
            boolean r12 = r11 instanceof com.google.android.gms.dynamite.DynamiteModule.LoadingException     // Catch:{ all -> 0x00ce }
            if (r12 == 0) goto L_0x00c6
            throw r11     // Catch:{ all -> 0x00ce }
        L_0x00c6:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r12 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ all -> 0x00ce }
            java.lang.String r1 = "V2 version check failed"
            r12.<init>(r1, r11, r0)     // Catch:{ all -> 0x00ce }
            throw r12     // Catch:{ all -> 0x00ce }
        L_0x00ce:
            r11 = move-exception
            r0 = r10
        L_0x00d0:
            if (r0 == 0) goto L_0x00d5
            r0.close()
        L_0x00d5:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzb(android.content.Context, java.lang.String, boolean):int");
    }

    private static DynamiteModule zzc(Context context, String str) {
        String valueOf = String.valueOf(str);
        Log.i("DynamiteModule", valueOf.length() != 0 ? "Selected local version of ".concat(valueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static void zzd(ClassLoader classLoader) throws LoadingException {
        zzr zzr;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zzr = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (queryLocalInterface instanceof zzr) {
                    zzr = (zzr) queryLocalInterface;
                } else {
                    zzr = new zzr(iBinder);
                }
            }
            zzk = zzr;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, (zzp) null);
        }
    }

    private static boolean zze(Cursor cursor) {
        zzn zzn = zzf.get();
        if (zzn == null || zzn.zza != null) {
            return false;
        }
        zzn.zza = cursor;
        return true;
    }

    private static zzq zzf(Context context) {
        zzq zzq;
        synchronized (DynamiteModule.class) {
            zzq zzq2 = zzj;
            if (zzq2 != null) {
                return zzq2;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzq = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    zzq = queryLocalInterface instanceof zzq ? (zzq) queryLocalInterface : new zzq(iBinder);
                }
                if (zzq != null) {
                    zzj = zzq;
                    return zzq;
                }
            } catch (Exception e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.e("DynamiteModule", valueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(valueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
            }
        }
        return null;
    }

    public Context getModuleContext() {
        return this.zzi;
    }

    public IBinder instantiate(String className) throws LoadingException {
        try {
            return (IBinder) this.zzi.getClassLoader().loadClass(className).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String valueOf = String.valueOf(className);
            throw new LoadingException(valueOf.length() != 0 ? "Failed to instantiate module class: ".concat(valueOf) : new String("Failed to instantiate module class: "), e, (zzp) null);
        }
    }
}
