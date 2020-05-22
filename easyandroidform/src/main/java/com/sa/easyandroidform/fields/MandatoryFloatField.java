package com.sa.easyandroidform.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class MandatoryFloatField extends MandatoryField<Float> {


    public MandatoryFloatField(@NonNull String fieldId) {
        super(fieldId);
    }


    public MandatoryFloatField(@NonNull String fieldId, @Nullable Float ogField) {
        super(fieldId, ogField);
    }

    @Override
    protected boolean isFieldValueModified(@NonNull Float field, @NonNull Float ogField) {
        return field.compareTo(ogField) != 0;
    }

}
