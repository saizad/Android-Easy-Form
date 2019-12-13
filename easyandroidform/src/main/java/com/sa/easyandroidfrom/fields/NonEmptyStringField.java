package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.StringUtils;


public class NonEmptyStringField extends StringField {

    public NonEmptyStringField(@NonNull String fieldId) {
        super(fieldId);
    }

    public NonEmptyStringField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public NonEmptyStringField(@NonNull String fieldId, @Nullable String ogField) {
        super(fieldId, ogField);
    }

    public NonEmptyStringField(@NonNull String fieldId, @Nullable String ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    public void setField(@Nullable String value) {
        if (StringUtils.isNullOrEmpty(value)) {
            super.setField(null);
        } else {
            super.setField(value);
        }
    }
}
