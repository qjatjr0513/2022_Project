package androidx.core.p008os;

import java.util.Locale;

/* renamed from: androidx.core.os.LocaleListInterface */
interface LocaleListInterface {
    Locale get(int i);

    Locale getFirstMatch(String[] strArr);

    Object getLocaleList();

    int indexOf(Locale locale);

    boolean isEmpty();

    int size();

    String toLanguageTags();
}
