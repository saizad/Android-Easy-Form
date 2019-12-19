package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class IntegerListField extends ListField<Integer> {

    public IntegerListField(@NonNull String fieldId) {
        super(fieldId);
    }

    public IntegerListField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public IntegerListField(@NonNull String fieldId, @Nullable List<Integer> ogField) {
        super(fieldId, ogField);
    }

    public IntegerListField(@NonNull String fieldId, @Nullable List<Integer> ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    protected boolean compare(Integer item1, Integer item2) {
        return item1.compareTo(item2) == 0;
    }
}
