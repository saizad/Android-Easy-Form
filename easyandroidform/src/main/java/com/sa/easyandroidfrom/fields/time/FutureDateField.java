package com.sa.easyandroidfrom.fields.time;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.joda.time.DateTime;


public class FutureDateField extends DateField {

    public FutureDateField(@NonNull String fieldId) {
        super(fieldId);
    }

    public FutureDateField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public FutureDateField(@NonNull String fieldId, @Nullable String ogField) {
        super(fieldId, ogField);
    }

    public FutureDateField(@NonNull String fieldId, @Nullable String ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    public void validate() throws Exception {
        final DateTime dateTime = dateTime();
        if (new DateTime().withTimeAtStartOfDay().plusDays(1).isBefore(dateTime)) {
            throw new Exception("Date can't be set to past or current date");
        }
    }
}
