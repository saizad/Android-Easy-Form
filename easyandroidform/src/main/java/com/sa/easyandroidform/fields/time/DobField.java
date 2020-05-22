package com.sa.easyandroidform.fields.time;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.joda.time.DateTime;

public class DobField extends PastDateField {

    private final int minAge;

    public DobField(@NonNull String fieldId, int minAge) {
        this(fieldId, null, minAge);
    }

    public DobField(@NonNull String fieldId, @Nullable String ogField, int minAge) {
        this(fieldId, ogField, false, minAge);
    }

    public DobField(@NonNull String fieldId, @Nullable String ogField, boolean isMandatory, int minAge) {
        super(fieldId, ogField, isMandatory);
        this.minAge = minAge;
    }

    public DobField(@NonNull String fieldId, boolean isMandatory, int minAge) {
        this(fieldId, null, isMandatory, minAge);
    }

    @Override
    protected DateTime resolveToDateTime(@NonNull String dateString) throws Exception {
        final DateTime dateTime = super.resolveToDateTime(dateString);
        final DateTime currentDateTime = new DateTime();
        if (dateTime.plusYears(minAge).isAfter(currentDateTime)) {
            throw new ValidateDateException("Minimum age required is " + minAge);
        }
        return dateTime;
    }
}
