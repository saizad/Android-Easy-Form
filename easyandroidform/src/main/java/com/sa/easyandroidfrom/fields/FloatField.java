package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class FloatField extends BaseField<Float> {

    public FloatField(@NonNull String fieldId) {
        super(fieldId);
    }

    public FloatField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public FloatField(@NonNull String fieldId, @Nullable Float ogField) {
        super(fieldId, ogField);
    }

    public FloatField(@NonNull String fieldId, @Nullable Float ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    protected boolean isFieldValueModified(@NonNull Float field, @NonNull Float ogField) {
        return field.compareTo(ogField) != 0;
    }

    @Override
    public void validate() throws Exception {

    }
}
