package android.support.p005v4.media.session;

import android.media.session.MediaSession;

/* renamed from: android.support.v4.media.session.MediaSessionCompatApi22 */
class MediaSessionCompatApi22 {
    public static void setRatingType(Object sessionObj, int type) {
        ((MediaSession) sessionObj).setRatingType(type);
    }

    private MediaSessionCompatApi22() {
    }
}
