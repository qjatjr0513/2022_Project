package com.google.firebase.heartbeatinfo;

public interface HeartBeatInfo {
    HeartBeat getHeartBeatCode(String str);

    public enum HeartBeat {
        NONE(0),
        SDK(1),
        GLOBAL(2),
        COMBINED(3);
        
        private final int code;

        private HeartBeat(int code2) {
            this.code = code2;
        }

        public int getCode() {
            return this.code;
        }
    }
}
