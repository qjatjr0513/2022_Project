package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Build;
import android.text.format.DateUtils;
import androidx.core.util.Pair;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

class DateStrings {
    private DateStrings() {
    }

    static String getYearMonth(Context context, long timeInMillis) {
        return DateUtils.formatDateTime(context, timeInMillis - ((long) TimeZone.getDefault().getOffset(timeInMillis)), 36);
    }

    static String getYearMonthDay(long timeInMillis) {
        return getYearMonthDay(timeInMillis, Locale.getDefault());
    }

    static String getYearMonthDay(long timeInMillis, Locale locale) {
        if (Build.VERSION.SDK_INT >= 24) {
            return UtcDates.getYearAbbrMonthDayFormat(locale).format(new Date(timeInMillis));
        }
        return UtcDates.getMediumFormat(locale).format(new Date(timeInMillis));
    }

    static String getMonthDay(long timeInMillis) {
        return getMonthDay(timeInMillis, Locale.getDefault());
    }

    static String getMonthDay(long timeInMillis, Locale locale) {
        if (Build.VERSION.SDK_INT >= 24) {
            return UtcDates.getAbbrMonthDayFormat(locale).format(new Date(timeInMillis));
        }
        return UtcDates.getMediumNoYear(locale).format(new Date(timeInMillis));
    }

    static String getMonthDayOfWeekDay(long timeInMillis) {
        return getMonthDayOfWeekDay(timeInMillis, Locale.getDefault());
    }

    static String getMonthDayOfWeekDay(long timeInMillis, Locale locale) {
        if (Build.VERSION.SDK_INT >= 24) {
            return UtcDates.getAbbrMonthWeekdayDayFormat(locale).format(new Date(timeInMillis));
        }
        return UtcDates.getFullFormat(locale).format(new Date(timeInMillis));
    }

    static String getYearMonthDayOfWeekDay(long timeInMillis) {
        return getYearMonthDayOfWeekDay(timeInMillis, Locale.getDefault());
    }

    static String getYearMonthDayOfWeekDay(long timeInMillis, Locale locale) {
        if (Build.VERSION.SDK_INT >= 24) {
            return UtcDates.getYearAbbrMonthWeekdayDayFormat(locale).format(new Date(timeInMillis));
        }
        return UtcDates.getFullFormat(locale).format(new Date(timeInMillis));
    }

    static String getDateString(long timeInMillis) {
        return getDateString(timeInMillis, (SimpleDateFormat) null);
    }

    static String getDateString(long timeInMillis, SimpleDateFormat userDefinedDateFormat) {
        Calendar currentCalendar = UtcDates.getTodayCalendar();
        Calendar calendarDate = UtcDates.getUtcCalendar();
        calendarDate.setTimeInMillis(timeInMillis);
        if (userDefinedDateFormat != null) {
            return userDefinedDateFormat.format(new Date(timeInMillis));
        }
        if (currentCalendar.get(1) == calendarDate.get(1)) {
            return getMonthDay(timeInMillis);
        }
        return getYearMonthDay(timeInMillis);
    }

    static Pair<String, String> getDateRangeString(Long start, Long end) {
        return getDateRangeString(start, end, (SimpleDateFormat) null);
    }

    static Pair<String, String> getDateRangeString(Long start, Long end, SimpleDateFormat userDefinedDateFormat) {
        if (start == null && end == null) {
            return Pair.create(null, null);
        }
        if (start == null) {
            return Pair.create(null, getDateString(end.longValue(), userDefinedDateFormat));
        }
        if (end == null) {
            return Pair.create(getDateString(start.longValue(), userDefinedDateFormat), null);
        }
        Calendar currentCalendar = UtcDates.getTodayCalendar();
        Calendar startCalendar = UtcDates.getUtcCalendar();
        startCalendar.setTimeInMillis(start.longValue());
        Calendar endCalendar = UtcDates.getUtcCalendar();
        endCalendar.setTimeInMillis(end.longValue());
        if (userDefinedDateFormat != null) {
            return Pair.create(userDefinedDateFormat.format(new Date(start.longValue())), userDefinedDateFormat.format(new Date(end.longValue())));
        } else if (startCalendar.get(1) != endCalendar.get(1)) {
            return Pair.create(getYearMonthDay(start.longValue(), Locale.getDefault()), getYearMonthDay(end.longValue(), Locale.getDefault()));
        } else {
            if (startCalendar.get(1) == currentCalendar.get(1)) {
                return Pair.create(getMonthDay(start.longValue(), Locale.getDefault()), getMonthDay(end.longValue(), Locale.getDefault()));
            }
            return Pair.create(getMonthDay(start.longValue(), Locale.getDefault()), getYearMonthDay(end.longValue(), Locale.getDefault()));
        }
    }
}
