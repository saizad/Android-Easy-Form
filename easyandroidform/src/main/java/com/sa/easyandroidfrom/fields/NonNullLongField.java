package com.sa.easyandroidfrom.fields;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.ObjectUtils;


public class NonNullLongField extends LongField {

    public NonNullLongField(@NonNull String fieldId) {
        super(fieldId);
    }

    public NonNullLongField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public NonNullLongField(@NonNull String fieldId, @Nullable Long ogField) {
        super(fieldId, ogField);
    }

    public NonNullLongField(@NonNull String fieldId, @Nullable Long ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    @CallSuper
    public void setField(@Nullable Long value) {
        super.setField(ObjectUtils.coalesce(value, 0L));
    }
}
