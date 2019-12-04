package com.sa.easyandroidfrom.fields;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.ObjectUtils;


public class NonNullBooleanField extends BooleanField {

    public NonNullBooleanField(@NonNull String fieldId, @Nullable Boolean ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    public NonNullBooleanField(@NonNull String fieldId) {
        super(fieldId);
    }

    public NonNullBooleanField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public NonNullBooleanField(@NonNull String fieldId, @Nullable Boolean ogField) {
        super(fieldId, ogField);
    }

    @Override
    @CallSuper
    public void setField(@Nullable Boolean value) {
        super.setField(ObjectUtils.coalesce(value, false));
    }
}
