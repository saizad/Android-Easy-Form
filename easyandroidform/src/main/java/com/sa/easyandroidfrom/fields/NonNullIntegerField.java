package com.sa.easyandroidfrom.fields;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.ObjectUtils;


public class NonNullIntegerField extends IntegerField {


    public NonNullIntegerField(@NonNull String fieldId) {
        super(fieldId);
    }

    public NonNullIntegerField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public NonNullIntegerField(@NonNull String fieldId, @Nullable Integer ogField) {
        super(fieldId, ogField);
    }

    public NonNullIntegerField(@NonNull String fieldId, @Nullable Integer ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    @CallSuper
    public void setField(@Nullable Integer value) {
        super.setField(ObjectUtils.coalesce(value, 0));
    }
}
