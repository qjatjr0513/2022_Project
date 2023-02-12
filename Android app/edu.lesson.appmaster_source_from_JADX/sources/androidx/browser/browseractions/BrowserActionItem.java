package androidx.browser.browseractions;

import android.app.PendingIntent;

public class BrowserActionItem {
    private final PendingIntent mAction;
    private final int mIconId;
    private final String mTitle;

    public BrowserActionItem(String title, PendingIntent action, int iconId) {
        this.mTitle = title;
        this.mAction = action;
        this.mIconId = iconId;
    }

    public BrowserActionItem(String title, PendingIntent action) {
        this(title, action, 0);
    }

    public int getIconId() {
        return this.mIconId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public PendingIntent getAction() {
        return this.mAction;
    }
}
