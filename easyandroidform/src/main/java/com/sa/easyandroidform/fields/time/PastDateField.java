package com.sa.easyandroidform.fields.time;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.joda.time.DateTime;

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
    protected DateTime resolveToDateTime(@NonNull String dateString) throws Exception {
        final DateTime dateTime = super.resolveToDateTime(dateString);
        final DateTime current = new DateTime().withTimeAtStartOfDay().plusDays(1).minusMillis(1);
        if (dateTime.isAfter(current) || dateTime.isEqual(current)) {
            throw new Exception("Date can't be set to future or current date");
        }
        return dateTime;
    }
}
