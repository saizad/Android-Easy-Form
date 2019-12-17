package com.sa.easyandroidfrom.fields;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.ObjectUtils;


public class MandatoryDoubleField extends MandatoryField<Double> {

    public MandatoryDoubleField(@NonNull String fieldId) {
        super(fieldId);
    }

    public MandatoryDoubleField(@NonNull String fieldId, @Nullable Double ogField) {
        super(fieldId, ogField);
    }

    @Override
    protected boolean isFieldValueModified(@NonNull Double field, @NonNull Double ogField) {
        return field.compareTo(ogField) != 0;
    }

    @Override
    @CallSuper
    public void setField(@Nullable Double value) {
        super.setField(ObjectUtils.coalesce(value, 0D));
    }
}
