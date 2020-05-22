package com.sa.easyandroidform.fields.time;

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
    protected DateTime resolveToDateTime(@NonNull String dateString) throws Exception {
        return super.resolveToDateTime(dateString).withTimeAtStartOfDay();
    }
}
