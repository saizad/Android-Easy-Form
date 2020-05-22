package com.sa.easyandroidform.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidform.ObjectUtils;

import io.reactivex.exceptions.CompositeException;


public class MandatoryField<F> extends Field<F> {

    public MandatoryField(@NonNull String fieldId) {
        this(fieldId, null);
    }

    public MandatoryField(@NonNull String fieldId, @Nullable F ogField) {
        super(fieldId, ogField, true);
    }

    @Override
    public void validate() throws CompositeException {
        super.validate();
        if (ObjectUtils.isNull(getField())) {
            throw new CompositeException(new Exception("Field is mandatory"));
        }
    }
}
