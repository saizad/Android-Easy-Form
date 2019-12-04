package com.sa.easyandroidfrom.fields.time;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.Utils;

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

    @Nullable
    public DateTime dateTime() {
        if(isSet()){
            return Utils.parseTime(getField());
        }
        return null;
    }
}
