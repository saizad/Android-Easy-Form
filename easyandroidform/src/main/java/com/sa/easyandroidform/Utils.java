package com.sa.easyandroidform;

import androidx.annotation.NonNull;

import com.sa.easyandroidform.fields.BaseField;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.exceptions.CompositeException;

public final class Utils {

    private Utils() {
    }

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

    public static String compositeExceptionMessage(CompositeException e){
        final StringBuilder stringBuilder = new StringBuilder();
        for (Throwable exception : e.getExceptions()) {
            stringBuilder.append(exception.getMessage()).append("\n");
        }
        return StringUtils.stripTrailingLeadingNewLines(stringBuilder.toString());
    }

    public static List<BaseField<?>> checkForDuplicateFieldName(List<BaseField<?>> list) throws Exception {
        final List<BaseField<?>> duplicates = ListUtils.checkForDuplicate(list, (baseField1, baseField2) ->
                baseField1.getFieldId().equals(baseField2.getFieldId()));
        if(!duplicates.isEmpty()){
            throw new Exception("Duplicate fieldIds" + listToString(duplicates, BaseField::getFieldId));
        }
        return list;
    }

    public static <T> String listToString(List<T> list, Func1<T, String> func1){
        StringBuilder sb = new StringBuilder();
        for (String s : ListUtils.extractValue(list, func1)) {
            sb.append(", ").append(s);
        }
        return "[" + sb.toString().replaceFirst(", ", "") + "]";
    }

    public static  <T> T randomListItem(List<T> list){
        return list.get(new Random().nextInt(Math.max(0, list.size() - 1)));
    }

    public static <T> List<T> filter(List<T> target, IPredicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T element: target) {
            if (predicate.apply(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public interface IPredicate<T> { boolean apply(T type); }

}
