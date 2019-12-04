package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.ObjectUtils;


public class LongField extends BaseField<Long> {

    public LongField(@NonNull String fieldId) {
        super(fieldId);
    }

    public LongField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public LongField(@NonNull String fieldId, @Nullable Long ogField) {
        super(fieldId, ogField);
    }

    public LongField(@NonNull String fieldId, @Nullable Long ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Long) {
            return obj.equals(getField());
        } else {
            return ObjectUtils.isSameObject(obj, getField());
        }
    }

    @Override
    public void validate() throws Exception {

    }
}
