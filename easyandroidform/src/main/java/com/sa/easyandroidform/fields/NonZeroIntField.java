package com.sa.easyandroidform.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.reactivex.exceptions.CompositeException;

public class NonZeroIntField extends IntegerField {


    public NonZeroIntField(@NonNull String fieldId) {
        this(fieldId, false);
    }

    public NonZeroIntField(@NonNull String fieldId, boolean isMandatory) {
        this(fieldId, null, isMandatory);
    }

    public NonZeroIntField(@NonNull String fieldId, @Nullable Integer ogField) {
        this(fieldId, ogField, false);
    }

    public NonZeroIntField(@NonNull String fieldId, @Nullable Integer ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    public void validate() throws CompositeException {
        super.validate();
        final Integer field = getField();
        if (field != null && field <= 0) {
            throw new CompositeException(new Exception("Number should be greater than 0"));
        }
    }

    @Override
    protected boolean isFieldValueModified(@NonNull Integer field, @NonNull Integer ogField) {
        return !field.equals(ogField);
    }
}
