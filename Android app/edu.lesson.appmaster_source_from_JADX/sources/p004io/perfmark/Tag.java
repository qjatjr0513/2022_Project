package p004io.perfmark;

import javax.annotation.Nullable;

/* renamed from: io.perfmark.Tag */
public final class Tag {
    final long tagId;
    @Nullable
    final String tagName;

    Tag(@Nullable String tagName2, long tagId2) {
        this.tagName = tagName2;
        this.tagId = tagId2;
    }
}
