package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.ObjectUtils;


public class StringField extends BaseField<String> {

    public StringField(@NonNull String fieldId) {
        super(fieldId);
    }

    public StringField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public StringField(@NonNull String fieldId, @Nullable String ogField) {
        super(fieldId, ogField);
    }

    public StringField(@NonNull String fieldId, @Nullable String ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof String) {
            return obj.equals(getField());
        } else {
            return ObjectUtils.isSameObject(obj, getField());
        }
    }

    @Override
    public void validate() throws Exception {

    }
}
