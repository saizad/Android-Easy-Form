package com.sa.easyandroidfrom.fields.time;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.joda.time.DateTime;


public class FutureDateTimeField extends DateTimeField {


    public FutureDateTimeField(@NonNull String fieldId) {
        super(fieldId);
    }

    public FutureDateTimeField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public FutureDateTimeField(@NonNull String fieldId, @Nullable String ogField) {
        super(fieldId, ogField);
    }

    public FutureDateTimeField(@NonNull String fieldId, @Nullable String ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    public void validate() throws Exception {
        final DateTime dateTime = dateTime();
        if (dateTime != null && dateTime.isBeforeNow()) {
            throw new Exception("Date time can't be set before current date time");
        }
    }
}
