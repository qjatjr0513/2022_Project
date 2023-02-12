package p004io.perfmark;

import javax.annotation.Nullable;

/* renamed from: io.perfmark.Impl */
public class Impl {
    static final Link NO_LINK = new Link(Long.MIN_VALUE);
    private static final long NO_LINK_ID = Long.MIN_VALUE;
    static final Tag NO_TAG = new Tag("", Long.MIN_VALUE);
    static final long NO_TAG_ID = Long.MIN_VALUE;
    static final String NO_TAG_NAME = "";

    protected Impl(Tag key) {
        if (key != NO_TAG) {
            throw new AssertionError("nope");
        }
    }

    /* access modifiers changed from: protected */
    public void setEnabled(boolean value) {
    }

    /* access modifiers changed from: protected */
    public <T> void startTask(T t, StringFunction<? super T> stringFunction) {
    }

    /* access modifiers changed from: protected */
    public void startTask(String taskName, Tag tag) {
    }

    /* access modifiers changed from: protected */
    public void startTask(String taskName) {
    }

    /* access modifiers changed from: protected */
    public void startTask(String taskName, String subTaskName) {
    }

    /* access modifiers changed from: protected */
    public void event(String eventName, Tag tag) {
    }

    /* access modifiers changed from: protected */
    public void event(String eventName) {
    }

    /* access modifiers changed from: protected */
    public void event(String eventName, String subEventName) {
    }

    /* access modifiers changed from: protected */
    public void stopTask() {
    }

    /* access modifiers changed from: protected */
    public void stopTask(String taskName, Tag tag) {
    }

    /* access modifiers changed from: protected */
    public void stopTask(String taskName) {
    }

    /* access modifiers changed from: protected */
    public void stopTask(String taskName, String subTaskName) {
    }

    /* access modifiers changed from: protected */
    public Link linkOut() {
        return NO_LINK;
    }

    /* access modifiers changed from: protected */
    public void linkIn(Link link) {
    }

    /* access modifiers changed from: protected */
    public void attachTag(Tag tag) {
    }

    /* access modifiers changed from: protected */
    public void attachTag(String tagName, String tagValue) {
    }

    /* access modifiers changed from: protected */
    public void attachTag(String tagName, long tagValue) {
    }

    /* access modifiers changed from: protected */
    public void attachTag(String tagName, long tagValue0, long tagValue1) {
    }

    /* access modifiers changed from: protected */
    public <T> void attachTag(String tagName, T t, StringFunction<? super T> stringFunction) {
    }

    /* access modifiers changed from: protected */
    public Tag createTag(@Nullable String tagName, long tagId) {
        return NO_TAG;
    }

    @Nullable
    protected static String unpackTagName(Tag tag) {
        return tag.tagName;
    }

    protected static long unpackTagId(Tag tag) {
        return tag.tagId;
    }

    protected static long unpackLinkId(Link link) {
        return link.linkId;
    }

    protected static Tag packTag(@Nullable String tagName, long tagId) {
        return new Tag(tagName, tagId);
    }

    protected static Link packLink(long linkId) {
        return new Link(linkId);
    }
}
