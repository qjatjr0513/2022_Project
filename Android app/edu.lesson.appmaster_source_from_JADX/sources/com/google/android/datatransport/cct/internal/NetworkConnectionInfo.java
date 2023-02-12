package com.google.android.datatransport.cct.internal;

import android.util.SparseArray;
import com.google.android.datatransport.cct.internal.AutoValue_NetworkConnectionInfo;

public abstract class NetworkConnectionInfo {

    public static abstract class Builder {
        public abstract NetworkConnectionInfo build();

        public abstract Builder setMobileSubtype(MobileSubtype mobileSubtype);

        public abstract Builder setNetworkType(NetworkType networkType);
    }

    public abstract MobileSubtype getMobileSubtype();

    public abstract NetworkType getNetworkType();

    public enum NetworkType {
        MOBILE(0),
        WIFI(1),
        MOBILE_MMS(2),
        MOBILE_SUPL(3),
        MOBILE_DUN(4),
        MOBILE_HIPRI(5),
        WIMAX(6),
        BLUETOOTH(7),
        DUMMY(8),
        ETHERNET(9),
        MOBILE_FOTA(10),
        MOBILE_IMS(11),
        MOBILE_CBS(12),
        WIFI_P2P(13),
        MOBILE_IA(14),
        MOBILE_EMERGENCY(15),
        PROXY(16),
        VPN(17),
        NONE(-1);
        
        private static final SparseArray<NetworkType> valueMap = null;
        private final int value;

        static {
            NetworkType networkType;
            NetworkType networkType2;
            NetworkType networkType3;
            NetworkType networkType4;
            NetworkType networkType5;
            NetworkType networkType6;
            NetworkType networkType7;
            NetworkType networkType8;
            NetworkType networkType9;
            NetworkType networkType10;
            NetworkType networkType11;
            NetworkType networkType12;
            NetworkType networkType13;
            NetworkType networkType14;
            NetworkType networkType15;
            NetworkType networkType16;
            NetworkType networkType17;
            NetworkType networkType18;
            NetworkType networkType19;
            SparseArray<NetworkType> sparseArray = new SparseArray<>();
            valueMap = sparseArray;
            sparseArray.put(0, networkType);
            sparseArray.put(1, networkType2);
            sparseArray.put(2, networkType3);
            sparseArray.put(3, networkType4);
            sparseArray.put(4, networkType5);
            sparseArray.put(5, networkType6);
            sparseArray.put(6, networkType7);
            sparseArray.put(7, networkType8);
            sparseArray.put(8, networkType9);
            sparseArray.put(9, networkType10);
            sparseArray.put(10, networkType11);
            sparseArray.put(11, networkType12);
            sparseArray.put(12, networkType19);
            sparseArray.put(13, networkType13);
            sparseArray.put(14, networkType14);
            sparseArray.put(15, networkType15);
            sparseArray.put(16, networkType16);
            sparseArray.put(17, networkType18);
            sparseArray.put(-1, networkType17);
        }

        private NetworkType(int value2) {
            this.value = value2;
        }

        public int getValue() {
            return this.value;
        }

        public static NetworkType forNumber(int value2) {
            return valueMap.get(value2);
        }
    }

    public enum MobileSubtype {
        UNKNOWN_MOBILE_SUBTYPE(0),
        GPRS(1),
        EDGE(2),
        UMTS(3),
        CDMA(4),
        EVDO_0(5),
        EVDO_A(6),
        RTT(7),
        HSDPA(8),
        HSUPA(9),
        HSPA(10),
        IDEN(11),
        EVDO_B(12),
        LTE(13),
        EHRPD(14),
        HSPAP(15),
        GSM(16),
        TD_SCDMA(17),
        IWLAN(18),
        LTE_CA(19),
        COMBINED(100);
        
        private static final SparseArray<MobileSubtype> valueMap = null;
        private final int value;

        static {
            MobileSubtype mobileSubtype;
            MobileSubtype mobileSubtype2;
            MobileSubtype mobileSubtype3;
            MobileSubtype mobileSubtype4;
            MobileSubtype mobileSubtype5;
            MobileSubtype mobileSubtype6;
            MobileSubtype mobileSubtype7;
            MobileSubtype mobileSubtype8;
            MobileSubtype mobileSubtype9;
            MobileSubtype mobileSubtype10;
            MobileSubtype mobileSubtype11;
            MobileSubtype mobileSubtype12;
            MobileSubtype mobileSubtype13;
            MobileSubtype mobileSubtype14;
            MobileSubtype mobileSubtype15;
            MobileSubtype mobileSubtype16;
            MobileSubtype mobileSubtype17;
            MobileSubtype mobileSubtype18;
            MobileSubtype mobileSubtype19;
            MobileSubtype mobileSubtype20;
            SparseArray<MobileSubtype> sparseArray = new SparseArray<>();
            valueMap = sparseArray;
            sparseArray.put(0, mobileSubtype);
            sparseArray.put(1, mobileSubtype2);
            sparseArray.put(2, mobileSubtype3);
            sparseArray.put(3, mobileSubtype4);
            sparseArray.put(4, mobileSubtype5);
            sparseArray.put(5, mobileSubtype6);
            sparseArray.put(6, mobileSubtype7);
            sparseArray.put(7, mobileSubtype8);
            sparseArray.put(8, mobileSubtype9);
            sparseArray.put(9, mobileSubtype10);
            sparseArray.put(10, mobileSubtype11);
            sparseArray.put(11, mobileSubtype12);
            sparseArray.put(12, mobileSubtype20);
            sparseArray.put(13, mobileSubtype13);
            sparseArray.put(14, mobileSubtype14);
            sparseArray.put(15, mobileSubtype15);
            sparseArray.put(16, mobileSubtype16);
            sparseArray.put(17, mobileSubtype17);
            sparseArray.put(18, mobileSubtype18);
            sparseArray.put(19, mobileSubtype19);
        }

        private MobileSubtype(int value2) {
            this.value = value2;
        }

        public int getValue() {
            return this.value;
        }

        public static MobileSubtype forNumber(int value2) {
            return valueMap.get(value2);
        }
    }

    public static Builder builder() {
        return new AutoValue_NetworkConnectionInfo.Builder();
    }
}
