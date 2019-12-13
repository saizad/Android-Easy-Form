package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class DoubleField extends Field<Double> {

    public DoubleField(@NonNull String fieldId) {
        super(fieldId);
    }

    public DoubleField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public DoubleField(@NonNull String fieldId, @Nullable Double ogField) {
        super(fieldId, ogField);
    }

    public DoubleField(@NonNull String fieldId, @Nullable Double ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    protected boolean isFieldValueModified(@NonNull Double field, @NonNull Double ogField) {
        return field.compareTo(ogField) != 0;
    }

    @Override
    public void validate() throws Exception {

    }
}
