package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class MandatoryBooleanField extends MandatoryField<Boolean> {


    public MandatoryBooleanField(@NonNull String fieldId) {
        super(fieldId);
    }

    public MandatoryBooleanField(@NonNull String fieldId, @Nullable Boolean ogField) {
        super(fieldId, ogField);
    }

    @Override
    protected boolean isFieldValueModified(@NonNull Boolean field, @NonNull Boolean ogField) {
        return field.compareTo(ogField) != 0;
    }
}
