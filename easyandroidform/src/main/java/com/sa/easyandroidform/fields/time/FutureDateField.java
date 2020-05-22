package com.sa.easyandroidform.fields.time;

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
    protected DateTime resolveToDateTime(@NonNull String dateString) throws Exception {
        final DateTime dateTime = super.resolveToDateTime(dateString);
        final DateTime current = new DateTime().plusDays(1).withTimeAtStartOfDay().minusMillis(1);
        final String s = dateTime.toString();
        final String s1 = current.toString();
        if (dateTime.isBefore(current) || dateTime.equals(current)) {
            throw new Exception("Date can't be set to past or current date");
        }
        return dateTime;
    }
}
