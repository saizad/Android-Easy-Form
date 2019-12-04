package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.ObjectUtils;


public class FloatField extends BaseField<Float> {

    public FloatField(@NonNull String fieldId) {
        super(fieldId);
    }

    public FloatField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public FloatField(@NonNull String fieldId, @Nullable Float ogField) {
        super(fieldId, ogField);
    }

    public FloatField(@NonNull String fieldId, @Nullable Float ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Float) {
            return obj.equals(getField());
        } else {
            return ObjectUtils.isSameObject(obj, getField());
        }
    }

    @Override
    public void validate() throws Exception {

    }
}
