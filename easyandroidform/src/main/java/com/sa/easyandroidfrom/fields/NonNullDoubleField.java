package com.sa.easyandroidfrom.fields;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.ObjectUtils;


public class NonNullDoubleField extends DoubleField {

    public NonNullDoubleField(@NonNull String fieldId) {
        super(fieldId);
    }

    public NonNullDoubleField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public NonNullDoubleField(@NonNull String fieldId, @Nullable Double ogField) {
        super(fieldId, ogField);
    }

    public NonNullDoubleField(@NonNull String fieldId, @Nullable Double ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    @CallSuper
    public void setField(@Nullable Double value) {
        super.setField(ObjectUtils.coalesce(value, 0D));
    }
}
