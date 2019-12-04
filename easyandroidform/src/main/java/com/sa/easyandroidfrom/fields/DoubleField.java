package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.ObjectUtils;


public class DoubleField extends BaseField<Double> {

    public DoubleField(@NonNull String fieldId) {
        super(fieldId);
    }

    public DoubleField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public DoubleField(@NonNull String fieldId, @Nullable Double ogField) {
        super(fieldId, ogField);
    }

    public DoubleField(@NonNull String fieldId, @Nullable Double ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Double) {
            return obj.equals(getField());
        } else {
            return ObjectUtils.isSameObject(obj, getField());
        }
    }

    @Override
    public void validate() throws Exception {

    }
}
