package com.sa.easyandroidfrom.fields;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.ObjectUtils;


public class MandatoryIntegerField extends MandatoryField<Integer> {


    public MandatoryIntegerField(@NonNull String fieldId) {
        super(fieldId);
    }


    public MandatoryIntegerField(@NonNull String fieldId, @Nullable Integer ogField) {
        super(fieldId, ogField);
    }


    @Override
    protected boolean isFieldValueModified(@NonNull Integer field, @NonNull Integer ogField) {
        return field.compareTo(ogField) != 0;
    }
    @Override
    @CallSuper
    public void setField(@Nullable Integer value) {
        super.setField(ObjectUtils.coalesce(value, 0));
    }
}
