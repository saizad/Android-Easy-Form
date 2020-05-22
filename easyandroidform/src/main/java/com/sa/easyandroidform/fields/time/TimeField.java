package com.sa.easyandroidform.fields.time;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidform.Utils;

import org.joda.time.DateTime;

public class TimeField extends DateTimeField {


    public TimeField(@NonNull String fieldId) {
        super(fieldId);
    }

    public TimeField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public TimeField(@NonNull String fieldId, @Nullable String ogField) {
        super(fieldId, ogField);
    }

    public TimeField(@NonNull String fieldId, @Nullable String ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    protected DateTime resolveToDateTime(@NonNull String dateString) throws Exception {
        return Utils.parseTime(dateString);
    }
}
