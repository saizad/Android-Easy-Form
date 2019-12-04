package com.sa.easyandroidfrom.fields;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.ObjectUtils;


public class NonNullFloatField extends FloatField {


    public NonNullFloatField(@NonNull String fieldId) {
        super(fieldId);
    }

    public NonNullFloatField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public NonNullFloatField(@NonNull String fieldId, @Nullable Float ogField) {
        super(fieldId, ogField);
    }

    public NonNullFloatField(@NonNull String fieldId, @Nullable Float ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    @CallSuper
    public void setField(@Nullable Float value) {
        super.setField(ObjectUtils.coalesce(value, 0F));
    }
}
