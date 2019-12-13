package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class LongField extends Field<Long> {

    public LongField(@NonNull String fieldId) {
        super(fieldId);
    }

    public LongField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public LongField(@NonNull String fieldId, @Nullable Long ogField) {
        super(fieldId, ogField);
    }

    public LongField(@NonNull String fieldId, @Nullable Long ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    protected boolean isFieldValueModified(@NonNull Long field, @NonNull Long ogField) {
        return field.compareTo(ogField) != 0;
    }

    @Override
    public void validate() throws Exception {

    }
}
