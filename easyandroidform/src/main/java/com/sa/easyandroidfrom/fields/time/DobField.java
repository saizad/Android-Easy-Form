package com.sa.easyandroidfrom.fields.time;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.joda.time.DateTime;

import io.reactivex.exceptions.CompositeException;

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
    public void validate() throws CompositeException {
        super.validate();
        final DateTime dateTime = dateTime();
        if(isMandatory() || dateTime != null) {
            final DateTime currentDateTime = new DateTime();
            if(dateTime == null){
                throw new CompositeException(new Exception(getFieldId() + " is mandatory"));
            }
            if (dateTime.plusYears(minAge).isAfter(currentDateTime)) {
                throw new CompositeException(new Exception("Minimum age required is " + minAge));
            }
        }
    }
}
