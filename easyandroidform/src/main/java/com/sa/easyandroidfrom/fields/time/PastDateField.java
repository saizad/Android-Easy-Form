package com.sa.easyandroidfrom.fields.time;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.joda.time.DateTime;

import io.reactivex.exceptions.CompositeException;

public class PastDateField extends DateField {

    public PastDateField(@NonNull String fieldId) {
        super(fieldId);
    }

    public PastDateField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public PastDateField(@NonNull String fieldId, @Nullable String ogField) {
        super(fieldId, ogField);
    }

    public PastDateField(@NonNull String fieldId, @Nullable String ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    public void validate() throws CompositeException {
        super.validate();
        final DateTime dateTime = dateTime();
        if (dateTime != null && dateTime.isAfter(new DateTime().withTimeAtStartOfDay().plusDays(1))) {
            throw new CompositeException(new Exception("Date can't be set to future or current date"));
        }
    }
}
