package com.sa.easyandroidform.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class StringField extends BaseField<String> {

    public StringField(@NonNull String fieldId) {
        super(fieldId);
    }

    public StringField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public StringField(@NonNull String fieldId, @Nullable String ogField) {
        super(fieldId, ogField);
    }

    public StringField(@NonNull String fieldId, @Nullable String ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    protected boolean isFieldValueModified(@NonNull String field, @NonNull String ogField) {
        return field.compareTo(ogField) != 0;
    }
}