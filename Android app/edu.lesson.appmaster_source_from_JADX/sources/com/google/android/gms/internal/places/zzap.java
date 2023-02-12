package com.google.android.gms.internal.places;

import com.google.android.gms.internal.places.zzbc;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzap {
    private static volatile boolean zzez = false;
    private static final Class<?> zzfa = zzan();
    private static volatile zzap zzfb;
    static final zzap zzfc = new zzap(true);
    private final Map<zzb, zzbc.zzf<?, ?>> zzfd;

    private static Class<?> zzan() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    static final class zzb {
        private final int number;
        private final Object object;

        zzb(Object obj, int i) {
            this.object = obj;
            this.number = i;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.object) * 65535) + this.number;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            if (this.object == zzb.object && this.number == zzb.number) {
                return true;
            }
            return false;
        }
    }

    public static zzap zzao() {
        zzap zzap = zzfb;
        if (zzap == null) {
            synchronized (zzap.class) {
                zzap = zzfb;
                if (zzap == null) {
                    zzap = zzaq.zzaq();
                    zzfb = zzap;
                }
            }
        }
        return zzap;
    }

    public final <ContainingType extends zzck> zzbc.zzf<ContainingType, ?> zzb(ContainingType containingtype, int i) {
        return this.zzfd.get(new zzb(containingtype, i));
    }

    zzap() {
        this.zzfd = new HashMap();
    }

    private zzap(boolean z) {
        this.zzfd = Collections.emptyMap();
    }
}
