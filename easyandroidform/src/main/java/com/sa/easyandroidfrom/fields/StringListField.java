package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class StringListField extends ListField<String> {

    public StringListField(@NonNull String fieldId) {
        super(fieldId);
    }

    public StringListField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public StringListField(@NonNull String fieldId, @Nullable List<String> ogField) {
        super(fieldId, ogField);
    }

    public StringListField(@NonNull String fieldId, @Nullable List<String> ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    protected boolean compare(String item1, String item2) {
        return item1.compareTo(item2) == 0;
    }
}
