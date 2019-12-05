package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class IntegerField extends Field<Integer> {

    public IntegerField(@NonNull String fieldId) {
        super(fieldId);
    }

    public IntegerField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public IntegerField(@NonNull String fieldId, @Nullable Integer ogField) {
        super(fieldId, ogField);
    }

    public IntegerField(@NonNull String fieldId, @Nullable Integer ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    protected boolean isFieldValueModified(@NonNull Integer field, @NonNull Integer ogField) {
        return !field.equals(ogField);
    }

    @Override
    public void validate() throws Exception {

    }

}
