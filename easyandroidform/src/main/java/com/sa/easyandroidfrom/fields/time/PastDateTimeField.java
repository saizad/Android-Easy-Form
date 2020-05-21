package com.sa.easyandroidfrom.fields.time;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.joda.time.DateTime;

import io.reactivex.exceptions.CompositeException;


public class PastDateTimeField extends DateTimeField {


    public PastDateTimeField(@NonNull String fieldId) {
        super(fieldId);
    }

    public PastDateTimeField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public PastDateTimeField(@NonNull String fieldId, @Nullable String ogField) {
        super(fieldId, ogField);
    }

    public PastDateTimeField(@NonNull String fieldId, @Nullable String ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    public void validate() throws CompositeException {
        super.validate();
        final DateTime dateTime = dateTime();
        if (dateTime != null && dateTime.isAfterNow())
            throw new CompositeException(new Exception("Date time can't be set to future or current date"));
    }
}
