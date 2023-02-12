package com.google.android.gms.internal.places;

import com.google.android.gms.internal.places.zzbc;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

final class zzcl {
    static String zzb(zzck zzck, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append(str);
        zzb(zzck, sb, 0);
        return sb.toString();
    }

    private static void zzb(zzck zzck, StringBuilder sb, int i) {
        boolean z;
        zzck zzck2 = zzck;
        StringBuilder sb2 = sb;
        int i2 = i;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet<>();
        for (Method method : zzck.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String replaceFirst = str.replaceFirst("get", "");
            boolean z2 = true;
            if (replaceFirst.endsWith("List") && !replaceFirst.endsWith("OrBuilderList") && !replaceFirst.equals("List")) {
                String valueOf = String.valueOf(replaceFirst.substring(0, 1).toLowerCase());
                String valueOf2 = String.valueOf(replaceFirst.substring(1, replaceFirst.length() - 4));
                String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                Method method2 = (Method) hashMap.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zzb(sb2, i2, zzl(concat), zzbc.zzb(method2, (Object) zzck2, new Object[0]));
                }
            }
            if (replaceFirst.endsWith("Map") && !replaceFirst.equals("Map")) {
                String valueOf3 = String.valueOf(replaceFirst.substring(0, 1).toLowerCase());
                String valueOf4 = String.valueOf(replaceFirst.substring(1, replaceFirst.length() - 3));
                String concat2 = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
                Method method3 = (Method) hashMap.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zzb(sb2, i2, zzl(concat2), zzbc.zzb(method3, (Object) zzck2, new Object[0]));
                }
            }
            String valueOf5 = String.valueOf(replaceFirst);
            if (((Method) hashMap2.get(valueOf5.length() != 0 ? "set".concat(valueOf5) : new String("set"))) != null) {
                if (replaceFirst.endsWith("Bytes")) {
                    String valueOf6 = String.valueOf(replaceFirst.substring(0, replaceFirst.length() - 5));
                    if (hashMap.containsKey(valueOf6.length() != 0 ? "get".concat(valueOf6) : new String("get"))) {
                    }
                }
                String valueOf7 = String.valueOf(replaceFirst.substring(0, 1).toLowerCase());
                String valueOf8 = String.valueOf(replaceFirst.substring(1));
                String concat3 = valueOf8.length() != 0 ? valueOf7.concat(valueOf8) : new String(valueOf7);
                String valueOf9 = String.valueOf(replaceFirst);
                Method method4 = (Method) hashMap.get(valueOf9.length() != 0 ? "get".concat(valueOf9) : new String("get"));
                String valueOf10 = String.valueOf(replaceFirst);
                Method method5 = (Method) hashMap.get(valueOf10.length() != 0 ? "has".concat(valueOf10) : new String("has"));
                if (method4 != null) {
                    Object zzb = zzbc.zzb(method4, (Object) zzck2, new Object[0]);
                    if (method5 == null) {
                        if (zzb instanceof Boolean) {
                            z = !((Boolean) zzb).booleanValue();
                        } else if (zzb instanceof Integer) {
                            z = ((Integer) zzb).intValue() == 0;
                        } else if (zzb instanceof Float) {
                            z = ((Float) zzb).floatValue() == 0.0f;
                        } else if (zzb instanceof Double) {
                            z = ((Double) zzb).doubleValue() == 0.0d;
                        } else if (zzb instanceof String) {
                            z = zzb.equals("");
                        } else if (zzb instanceof zzw) {
                            z = zzb.equals(zzw.zzeg);
                        } else if (zzb instanceof zzck) {
                            z = zzb == ((zzck) zzb).zzbg();
                        } else if (zzb instanceof Enum) {
                            z = ((Enum) zzb).ordinal() == 0;
                        } else {
                            z = false;
                        }
                        if (z) {
                            z2 = false;
                        }
                    } else {
                        z2 = ((Boolean) zzbc.zzb(method5, (Object) zzck2, new Object[0])).booleanValue();
                    }
                    if (z2) {
                        zzb(sb2, i2, zzl(concat3), zzb);
                    }
                }
            }
        }
        if (zzck2 instanceof zzbc.zzc) {
            Iterator<Map.Entry<Object, Object>> it = ((zzbc.zzc) zzck2).zzik.iterator();
            if (it.hasNext()) {
                it.next().getKey();
                throw new NoSuchMethodError();
            }
        }
        zzbc zzbc = (zzbc) zzck2;
        if (zzbc.zzih != null) {
            zzbc.zzih.zzb(sb2, i2);
        }
    }

    static final void zzb(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object zzb : (List) obj) {
                zzb(sb, i, str, zzb);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry zzb2 : ((Map) obj).entrySet()) {
                zzb(sb, i, str, zzb2);
            }
        } else {
            sb.append(10);
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                sb.append(' ');
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"").append(zzdo.zzd(zzw.zzi((String) obj))).append('\"');
            } else if (obj instanceof zzw) {
                sb.append(": \"").append(zzdo.zzd((zzw) obj)).append('\"');
            } else if (obj instanceof zzbc) {
                sb.append(" {");
                zzb((zzbc) obj, sb, i + 2);
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else if (obj instanceof Map.Entry) {
                sb.append(" {");
                Map.Entry entry = (Map.Entry) obj;
                int i4 = i + 2;
                zzb(sb, i4, "key", entry.getKey());
                zzb(sb, i4, "value", entry.getValue());
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else {
                sb.append(": ").append(obj.toString());
            }
        }
    }

    private static final String zzl(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }
}
