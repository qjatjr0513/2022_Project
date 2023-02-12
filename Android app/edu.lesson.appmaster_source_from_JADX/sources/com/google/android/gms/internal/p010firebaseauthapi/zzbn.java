package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbn */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzbn {
    private static final Logger zza = Logger.getLogger(zzbn.class.getName());
    private static final ConcurrentMap<String, zzbm> zzb = new ConcurrentHashMap();
    private static final ConcurrentMap<String, zzbl> zzc = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Boolean> zzd = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Object> zze = new ConcurrentHashMap();
    private static final ConcurrentMap<Class<?>, zzbg<?, ?>> zzf = new ConcurrentHashMap();
    private static final ConcurrentMap<String, zzaq> zzg = new ConcurrentHashMap();

    private zzbn() {
    }

    public static zzan<?> zza(String str) throws GeneralSecurityException {
        return zzp(str).zzb();
    }

    public static zzie zzb(String str, zzyu zzyu) throws GeneralSecurityException {
        zzan zzo = zzo(str, (Class) null);
        if (zzo instanceof zzbh) {
            return ((zzbh) zzo).zze(zzyu);
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 48);
        sb.append("manager for key type ");
        sb.append(str);
        sb.append(" is not a PrivateKeyManager");
        throw new GeneralSecurityException(sb.toString());
    }

    public static synchronized zzie zzc(zzij zzij) throws GeneralSecurityException {
        zzie zza2;
        synchronized (zzbn.class) {
            zzan<?> zza3 = zza(zzij.zzf());
            if (((Boolean) zzd.get(zzij.zzf())).booleanValue()) {
                zza2 = zza3.zza(zzij.zze());
            } else {
                String valueOf = String.valueOf(zzij.zzf());
                throw new GeneralSecurityException(valueOf.length() != 0 ? "newKey-operation not permitted for key type ".concat(valueOf) : new String("newKey-operation not permitted for key type "));
            }
        }
        return zza2;
    }

    public static synchronized zzaaz zzd(zzij zzij) throws GeneralSecurityException {
        zzaaz zzb2;
        synchronized (zzbn.class) {
            zzan<?> zza2 = zza(zzij.zzf());
            if (((Boolean) zzd.get(zzij.zzf())).booleanValue()) {
                zzb2 = zza2.zzb(zzij.zze());
            } else {
                String valueOf = String.valueOf(zzij.zzf());
                throw new GeneralSecurityException(valueOf.length() != 0 ? "newKey-operation not permitted for key type ".concat(valueOf) : new String("newKey-operation not permitted for key type "));
            }
        }
        return zzb2;
    }

    public static Class<?> zze(Class<?> cls) {
        zzbg zzbg = (zzbg) zzf.get(cls);
        if (zzbg == null) {
            return null;
        }
        return zzbg.zza();
    }

    @Deprecated
    public static <P> P zzf(zzie zzie) throws GeneralSecurityException {
        return zzq(zzie.zzf(), zzie.zze(), (Class) null);
    }

    public static <P> P zzg(zzie zzie, Class<P> cls) throws GeneralSecurityException {
        return zzq(zzie.zzf(), zzie.zze(), cls);
    }

    public static <P> P zzh(String str, zzaaz zzaaz, Class<P> cls) throws GeneralSecurityException {
        return zzo(str, cls).zzd(zzaaz);
    }

    public static <P> P zzi(String str, byte[] bArr, Class<P> cls) throws GeneralSecurityException {
        return zzq(str, zzyu.zzn(bArr), cls);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.google.android.gms.internal.firebase-auth-api.zzbf, com.google.android.gms.internal.firebase-auth-api.zzbf<B>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <B, P> P zzj(com.google.android.gms.internal.p010firebaseauthapi.zzbf<B> r4, java.lang.Class<P> r5) throws java.security.GeneralSecurityException {
        /*
            java.util.concurrent.ConcurrentMap<java.lang.Class<?>, com.google.android.gms.internal.firebase-auth-api.zzbg<?, ?>> r0 = zzf
            java.lang.Object r5 = r0.get(r5)
            com.google.android.gms.internal.firebase-auth-api.zzbg r5 = (com.google.android.gms.internal.p010firebaseauthapi.zzbg) r5
            if (r5 != 0) goto L_0x002e
            java.security.GeneralSecurityException r5 = new java.security.GeneralSecurityException
            java.lang.Class r4 = r4.zzc()
            java.lang.String r4 = r4.getName()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.String r0 = "No wrapper found for "
            int r1 = r4.length()
            if (r1 == 0) goto L_0x0025
            java.lang.String r4 = r0.concat(r4)
            goto L_0x002a
        L_0x0025:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r0)
        L_0x002a:
            r5.<init>(r4)
            throw r5
        L_0x002e:
            java.lang.Class r0 = r5.zza()
            java.lang.Class r1 = r4.zzc()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0041
            java.lang.Object r4 = r5.zzc(r4)
            return r4
        L_0x0041:
            java.security.GeneralSecurityException r0 = new java.security.GeneralSecurityException
            java.lang.Class r5 = r5.zza()
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.Class r4 = r4.zzc()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.String r1 = java.lang.String.valueOf(r5)
            int r1 = r1.length()
            java.lang.String r2 = java.lang.String.valueOf(r4)
            int r2 = r2.length()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            int r1 = r1 + 44
            int r1 = r1 + r2
            r3.<init>(r1)
            java.lang.String r1 = "Wrong input primitive class, expected "
            r3.append(r1)
            r3.append(r5)
            java.lang.String r5 = ", got "
            r3.append(r5)
            r3.append(r4)
            java.lang.String r4 = r3.toString()
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p010firebaseauthapi.zzbn.zzj(com.google.android.gms.internal.firebase-auth-api.zzbf, java.lang.Class):java.lang.Object");
    }

    static synchronized Map<String, zzaq> zzk() {
        Map<String, zzaq> unmodifiableMap;
        synchronized (zzbn.class) {
            unmodifiableMap = Collections.unmodifiableMap(zzg);
        }
        return unmodifiableMap;
    }

    public static synchronized <KeyProtoT extends zzaaz, PublicKeyProtoT extends zzaaz> void zzl(zzbi<KeyProtoT, PublicKeyProtoT> zzbi, zzav<PublicKeyProtoT> zzav, boolean z) throws GeneralSecurityException {
        Class<?> zzd2;
        synchronized (zzbn.class) {
            zzr("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey", zzbi.getClass(), zzbi.zza().zzd(), true);
            zzr("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey", zzav.getClass(), Collections.emptyMap(), false);
            ConcurrentMap<String, zzbm> concurrentMap = zzb;
            if (concurrentMap.containsKey("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey") && (zzd2 = ((zzbm) concurrentMap.get("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey")).zzd()) != null) {
                if (!zzd2.getName().equals(zzav.getClass().getName())) {
                    zza.logp(Level.WARNING, "com.google.crypto.tink.Registry", "registerAsymmetricKeyManagers", "Attempted overwrite of a registered key manager for key type type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey with inconsistent public key type type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey");
                    throw new GeneralSecurityException(String.format("public key manager corresponding to %s is already registered with %s, cannot be re-registered with %s", new Object[]{zzbi.getClass().getName(), zzd2.getName(), zzav.getClass().getName()}));
                }
            }
            if (!concurrentMap.containsKey("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey") || ((zzbm) concurrentMap.get("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey")).zzd() == null) {
                concurrentMap.put("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey", new zzbk(zzbi, zzav));
                zzc.put("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey", new zzbl(zzbi));
                zzs("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey", zzbi.zza().zzd());
            }
            ConcurrentMap<String, Boolean> concurrentMap2 = zzd;
            concurrentMap2.put("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey", true);
            if (!concurrentMap.containsKey("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey")) {
                concurrentMap.put("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey", new zzbj(zzav));
            }
            concurrentMap2.put("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey", false);
        }
    }

    public static synchronized <KeyProtoT extends zzaaz> void zzm(zzav<KeyProtoT> zzav, boolean z) throws GeneralSecurityException {
        synchronized (zzbn.class) {
            String zzg2 = zzav.zzg();
            zzr(zzg2, zzav.getClass(), zzav.zza().zzd(), true);
            ConcurrentMap<String, zzbm> concurrentMap = zzb;
            if (!concurrentMap.containsKey(zzg2)) {
                concurrentMap.put(zzg2, new zzbj(zzav));
                zzc.put(zzg2, new zzbl(zzav));
                zzs(zzg2, zzav.zza().zzd());
            }
            zzd.put(zzg2, true);
        }
    }

    public static synchronized <B, P> void zzn(zzbg<B, P> zzbg) throws GeneralSecurityException {
        synchronized (zzbn.class) {
            if (zzbg != null) {
                Class<P> zzb2 = zzbg.zzb();
                ConcurrentMap<Class<?>, zzbg<?, ?>> concurrentMap = zzf;
                if (concurrentMap.containsKey(zzb2)) {
                    zzbg zzbg2 = (zzbg) concurrentMap.get(zzb2);
                    if (!zzbg.getClass().getName().equals(zzbg2.getClass().getName())) {
                        Logger logger = zza;
                        Level level = Level.WARNING;
                        String valueOf = String.valueOf(zzb2);
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 62);
                        sb.append("Attempted overwrite of a registered PrimitiveWrapper for type ");
                        sb.append(valueOf);
                        logger.logp(level, "com.google.crypto.tink.Registry", "registerPrimitiveWrapper", sb.toString());
                        throw new GeneralSecurityException(String.format("PrimitiveWrapper for primitive (%s) is already registered to be %s, cannot be re-registered with %s", new Object[]{zzb2.getName(), zzbg2.getClass().getName(), zzbg.getClass().getName()}));
                    }
                }
                concurrentMap.put(zzb2, zzbg);
            } else {
                throw new IllegalArgumentException("wrapper must be non-null");
            }
        }
    }

    private static <P> zzan<P> zzo(String str, Class<P> cls) throws GeneralSecurityException {
        zzbm zzp = zzp(str);
        if (cls == null) {
            return zzp.zzb();
        }
        if (zzp.zze().contains(cls)) {
            return zzp.zza(cls);
        }
        String name = cls.getName();
        String valueOf = String.valueOf(zzp.zzc());
        Set<Class<?>> zze2 = zzp.zze();
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Class next : zze2) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(next.getCanonicalName());
            z = false;
        }
        String sb2 = sb.toString();
        int length = String.valueOf(name).length();
        StringBuilder sb3 = new StringBuilder(length + 77 + String.valueOf(valueOf).length() + String.valueOf(sb2).length());
        sb3.append("Primitive type ");
        sb3.append(name);
        sb3.append(" not supported by key manager of type ");
        sb3.append(valueOf);
        sb3.append(", supported primitives: ");
        sb3.append(sb2);
        throw new GeneralSecurityException(sb3.toString());
    }

    private static synchronized zzbm zzp(String str) throws GeneralSecurityException {
        zzbm zzbm;
        synchronized (zzbn.class) {
            ConcurrentMap<String, zzbm> concurrentMap = zzb;
            if (!concurrentMap.containsKey(str)) {
                String valueOf = String.valueOf(str);
                throw new GeneralSecurityException(valueOf.length() != 0 ? "No key manager found for key type ".concat(valueOf) : new String("No key manager found for key type "));
            }
            zzbm = (zzbm) concurrentMap.get(str);
        }
        return zzbm;
    }

    private static <P> P zzq(String str, zzyu zzyu, Class<P> cls) throws GeneralSecurityException {
        return zzo(str, cls).zzc(zzyu);
    }

    private static synchronized <KeyProtoT extends zzaaz, KeyFormatProtoT extends zzaaz> void zzr(String str, Class cls, Map<String, zzas<KeyFormatProtoT>> map, boolean z) throws GeneralSecurityException {
        synchronized (zzbn.class) {
            ConcurrentMap<String, zzbm> concurrentMap = zzb;
            zzbm zzbm = (zzbm) concurrentMap.get(str);
            if (zzbm != null && !zzbm.zzc().equals(cls)) {
                zza.logp(Level.WARNING, "com.google.crypto.tink.Registry", "ensureKeyManagerInsertable", str.length() != 0 ? "Attempted overwrite of a registered key manager for key type ".concat(str) : new String("Attempted overwrite of a registered key manager for key type "));
                throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", new Object[]{str, zzbm.zzc().getName(), cls.getName()}));
            } else if (z) {
                ConcurrentMap<String, Boolean> concurrentMap2 = zzd;
                if (concurrentMap2.containsKey(str) && !((Boolean) concurrentMap2.get(str)).booleanValue()) {
                    throw new GeneralSecurityException(str.length() != 0 ? "New keys are already disallowed for key type ".concat(str) : new String("New keys are already disallowed for key type "));
                } else if (concurrentMap.containsKey(str)) {
                    for (Map.Entry next : map.entrySet()) {
                        if (!zzg.containsKey(next.getKey())) {
                            String str2 = (String) next.getKey();
                            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 79 + str.length());
                            sb.append("Attempted to register a new key template ");
                            sb.append(str2);
                            sb.append(" from an existing key manager of type ");
                            sb.append(str);
                            throw new GeneralSecurityException(sb.toString());
                        }
                    }
                } else {
                    for (Map.Entry next2 : map.entrySet()) {
                        if (zzg.containsKey(next2.getKey())) {
                            String valueOf = String.valueOf((String) next2.getKey());
                            throw new GeneralSecurityException(valueOf.length() != 0 ? "Attempted overwrite of a registered key template ".concat(valueOf) : new String("Attempted overwrite of a registered key template "));
                        }
                    }
                }
            }
        }
    }

    private static <KeyFormatProtoT extends zzaaz> void zzs(String str, Map<String, zzas<KeyFormatProtoT>> map) {
        for (Map.Entry next : map.entrySet()) {
            zzg.put((String) next.getKey(), zzaq.zze(str, ((zzas) next.getValue()).zza.zzr(), ((zzas) next.getValue()).zzb));
        }
    }
}
