package com.sa.easyandroidform.fields.time;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.joda.time.DateTime;


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
    DateTime resolveToDateTime(@NonNull String dateString) throws Exception {
        final DateTime dateTime = super.resolveToDateTime(dateString);
        if (dateTime.isAfterNow()) {
            throw new Exception("Date time can't be set to future or current date");
        }
        return dateTime;
    }
}
