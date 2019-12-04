package com.sa.easyandroidfrom.fields.time;

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

    @Override
    public void validate() throws Exception {
        super.validate();
        final DateTime dateTime = dateTime();
        final DateTime currentDateTime = new DateTime();
        if(dateTime.plusYears(minAge).isAfter(currentDateTime)){
            throw new Exception("Minimum age required is " + minAge);
        }
    }
}
