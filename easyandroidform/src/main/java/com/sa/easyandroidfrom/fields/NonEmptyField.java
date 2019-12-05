package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.ObjectUtils;


public class NonEmptyField<F> extends Field<F> {

    public NonEmptyField(@NonNull String fieldId) {
        super(fieldId);
    }

    public NonEmptyField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public NonEmptyField(@NonNull String fieldId, @Nullable F ogField) {
        super(fieldId, ogField);
    }

    public NonEmptyField(@NonNull String fieldId, @Nullable F ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    public void validate() throws Exception {
        if (ObjectUtils.isNull(getField())) {
            throw new Exception("Field is mandatory");
        }
    }
}
