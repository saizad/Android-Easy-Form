package com.sa.easyandroidform.fields;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidform.StringUtils;

import io.reactivex.exceptions.CompositeException;


public class MandatoryStringField extends MandatoryField<String> {

    public MandatoryStringField(@NonNull String fieldId) {
        this(fieldId, null);
    }

    public MandatoryStringField(@NonNull String fieldId, @Nullable String ogField) {
        super(fieldId, ogField);
    }

    @Override
    protected boolean isFieldValueModified(@NonNull String field, @NonNull String ogField) {
        return field.compareTo(ogField) != 0;
    }

    @Override
    @CallSuper
    public void validate() throws CompositeException {
        super.validate();
        if (StringUtils.isNullOrEmpty(getField())) {
            throw new CompositeException(new Exception("Field can't be null or empty"));
        }
    }
}
