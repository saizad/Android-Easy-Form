package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.ObjectUtils;


public class MandatoryField<F> extends Field<F> {

    public MandatoryField(@NonNull String fieldId) {
        this(fieldId, null);
    }

    public MandatoryField(@NonNull String fieldId, @Nullable F ogField) {
        super(fieldId, ogField, true);
    }

    @Override
    public void validate() throws Exception {
        if (ObjectUtils.isNull(getField())) {
            throw new Exception("Field is mandatory");
        }
    }
}
