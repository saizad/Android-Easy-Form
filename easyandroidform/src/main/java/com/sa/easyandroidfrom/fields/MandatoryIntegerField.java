package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class MandatoryIntegerField extends MandatoryField<Integer> {


    public MandatoryIntegerField(@NonNull String fieldId) {
        super(fieldId);
    }


    public MandatoryIntegerField(@NonNull String fieldId, @Nullable Integer ogField) {
        super(fieldId, ogField);
    }


    @Override
    protected boolean isFieldValueModified(@NonNull Integer field, @NonNull Integer ogField) {
        return field.compareTo(ogField) != 0;
    }

}
