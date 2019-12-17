package com.sa.easyandroidfrom.fields;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.ObjectUtils;


public class MandatoryBooleanField extends MandatoryField<Boolean> {


    public MandatoryBooleanField(@NonNull String fieldId) {
        super(fieldId);
    }

    public MandatoryBooleanField(@NonNull String fieldId, @Nullable Boolean ogField) {
        super(fieldId, ogField);
    }

    @Override
    protected boolean isFieldValueModified(@NonNull Boolean field, @NonNull Boolean ogField) {
        return field.compareTo(ogField) != 0;
    }

    @Override
    @CallSuper
    public void setField(@Nullable Boolean value) {
        super.setField(ObjectUtils.coalesce(value, false));
    }
}
