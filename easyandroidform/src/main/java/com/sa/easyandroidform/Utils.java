package com.sa.easyandroidform;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import io.reactivex.exceptions.CompositeException;

public final class Utils {

    private Utils() {
    }

    public static final String APP_DATE_FORMATTER = "yyyy-MM-dd";
    public static final String APP_TIME_FORMATTER = "h:mm:ss";
    public static final String APP_TIME_FORMATTER_24_HOURS = "H:mm:ss";

    @NonNull
    public static DateTime parseTime(@NonNull String time) throws Exception {
        final String[] split = time.split(":");
        DateTime dateTime = new DateTime().withTimeAtStartOfDay();
        dateTime = dateTime
                .plusHours(Integer.parseInt(split[0]))
                .plusMinutes(Integer.parseInt(split[1]))
                .plusSeconds(Integer.parseInt(split[2]));

        final String s = dateTime.toString();
        return dateTime;
    }

    @Nullable
    public static String parseDateLong(@Nullable Long date) {
        return parseDateLong(date, APP_DATE_FORMATTER);
    }

    @Nullable
    public static String parseDateLong(@Nullable Long date, String formatter) {
        if (date != null) {
            DateTime dateTime = new DateTime(date);
            return dateTime.toString(formatter);
        }
        return null;
    }

    @NonNull
    public static DateTime parseDateToDateTime(@NonNull String date) {
        return DateTime.parse(date);
    }

    @NonNull
    public static DateTime parseDateTime(@NonNull String date) {
        return parseDateToDateTime(date);
    }

    @NonNull
    public static Long parseDate(@NonNull String date) {
        return parseDateToDateTime(date).getMillis();
    }

    public static boolean isSameDay(@NonNull String date, @NonNull String date1) {
        return isSameDay(parseDate(date), parseDate(date1));
    }

    public static boolean isSameDay(@Nullable Long date, @Nullable Long date1) {
        if (ObjectUtils.isNull(date) || ObjectUtils.isNull(date1)) {
            return false;
        }
        DateTime dateTime = new DateTime(date);
        DateTime dateTime1 = new DateTime(date1);

        return isSameDay(dateTime, dateTime1);
    }

    public static boolean isDateInBetween(@Nullable String startDate, @Nullable String endDate, @NonNull Long date) {
        if (ObjectUtils.isNull(startDate) && ObjectUtils.isNull(endDate)) {
            return false;
        }

        if (ObjectUtils.isNull(startDate)) {
            return isSameDay(parseDate(endDate), date);
        }

        if (ObjectUtils.isNull(endDate)) {
            return isSameDay(parseDate(startDate), date);
        }

        final Long startTime = parseDate(startDate);
        final Long endTime = parseDate(endDate);

        return startTime <= date && endTime >= date;
    }

    public static boolean isSameDay(@NonNull DateTime date, @NonNull DateTime date1) {
        return DateTime.parse(date.toString(APP_DATE_FORMATTER)).getMillis() == DateTime.parse(date1.toString(APP_DATE_FORMATTER)).getMillis();
    }

    public static boolean isToday(DateTime time) {
        return LocalDate.now().compareTo(new LocalDate(time)) == 0;
    }

    public static boolean isTomorrow(DateTime time) {
        return LocalDate.now().plusDays(1).compareTo(new LocalDate(time)) == 0;
    }

    public static boolean isYesterday(DateTime time) {
        return LocalDate.now().minusDays(1).compareTo(new LocalDate(time)) == 0;
    }

    public static String ordinal(int i) {
        String[] suffixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + suffixes[i % 10];

        }
    }

    public static String compositeExceptionMessage(CompositeException e){
        final StringBuilder stringBuilder = new StringBuilder();
        for (Throwable exception : e.getExceptions()) {
            stringBuilder.append(exception.getMessage()).append("\n");
        }
        return StringUtils.stripTrailingLeadingNewLines(stringBuilder.toString());
    }

}
