package com.google.android.gms.internal.places;

import java.util.Collections;
import java.util.List;
import java.util.Map;

final class zzde extends zzdb<FieldDescriptorType, Object> {
    zzde(int i) {
        super(i, (zzde) null);
    }

    public final void zzab() {
        if (!isImmutable()) {
            for (int i = 0; i < zzcu(); i++) {
                Map.Entry zzam = zzam(i);
                if (((zzax) zzam.getKey()).zzaz()) {
                    zzam.setValue(Collections.unmodifiableList((List) zzam.getValue()));
                }
            }
            for (Map.Entry entry : zzcv()) {
                if (((zzax) entry.getKey()).zzaz()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzab();
    }
}
