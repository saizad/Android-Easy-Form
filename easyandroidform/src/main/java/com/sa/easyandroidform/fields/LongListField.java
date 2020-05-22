package com.sa.easyandroidform.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LongListField extends ListField<Long> {

    public LongListField(@NonNull String fieldId) {
        super(fieldId);
    }

    public LongListField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public LongListField(@NonNull String fieldId, @Nullable List<Long> ogField) {
        super(fieldId, ogField);
    }

    public LongListField(@NonNull String fieldId, @Nullable List<Long> ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    protected boolean compare(Long item1, Long item2) {
        return item1.compareTo(item2) == 0;
    }
}
