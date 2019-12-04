package com.sa.easyandroidfrom.fields.time;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.joda.time.DateTime;

public class DateField extends DateTimeField {


    public DateField(@NonNull String fieldId) {
        super(fieldId);
    }

    public DateField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public DateField(@NonNull String fieldId, @Nullable String ogField) {
        super(fieldId, ogField);
    }

    public DateField(@NonNull String fieldId, @Nullable String ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    public void validate() throws Exception {
        new DateTime(super.getField());
    }

    @Nullable
    public DateTime dateTime() {
        final DateTime dateTime = super.dateTime();
        if (dateTime != null) {
            return dateTime.withTimeAtStartOfDay();
        }
        return null;
    }
}
