package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Field<F> extends BaseField<F> {

    public Field(@NonNull String fieldId) {
        super(fieldId);
    }

    public Field(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public Field(@NonNull String fieldId, @Nullable F ogField) {
        super(fieldId, ogField);
    }

    public Field(@NonNull String fieldId, @Nullable F ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    public void validate() throws Exception {

    }

    @Override
    protected boolean isFieldValueModified(@NonNull F field, @NonNull F ogField) {
        return isModified();
    }
}
