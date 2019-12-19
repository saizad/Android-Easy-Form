package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class MandatoryLongField extends MandatoryField<Long> {

    public MandatoryLongField(@NonNull String fieldId) {
        super(fieldId);
    }


    public MandatoryLongField(@NonNull String fieldId, @Nullable Long ogField) {
        super(fieldId, ogField);
    }

    @Override
    protected boolean isFieldValueModified(@NonNull Long field, @NonNull Long ogField) {
        return field.compareTo(ogField) != 0;
    }
}
