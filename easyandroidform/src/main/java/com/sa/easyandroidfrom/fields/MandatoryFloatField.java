package com.sa.easyandroidfrom.fields;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.ObjectUtils;


public class MandatoryFloatField extends MandatoryField<Float> {


    public MandatoryFloatField(@NonNull String fieldId) {
        super(fieldId);
    }


    public MandatoryFloatField(@NonNull String fieldId, @Nullable Float ogField) {
        super(fieldId, ogField);
    }

    @Override
    protected boolean isFieldValueModified(@NonNull Float field, @NonNull Float ogField) {
        return field.compareTo(ogField) != 0;
    }

    @Override
    @CallSuper
    public void setField(@Nullable Float value) {
        super.setField(ObjectUtils.coalesce(value, 0F));
    }
}
