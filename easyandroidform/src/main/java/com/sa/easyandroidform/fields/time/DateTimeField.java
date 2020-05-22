package com.sa.easyandroidform.fields.time;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidform.ObjectUtils;
import com.sa.easyandroidform.fields.StringField;

import org.joda.time.DateTime;

import io.reactivex.exceptions.CompositeException;


public class DateTimeField extends StringField {

    private DateTime dateTime;

    public DateTimeField(@NonNull String fieldId) {
        super(fieldId);
    }

    public DateTimeField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public DateTimeField(@NonNull String fieldId, @Nullable String ogField) {
        super(fieldId, ogField);
    }

    public DateTimeField(@NonNull String fieldId, @Nullable String ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    public final void validate() throws CompositeException {
        super.validate();
        dateTime = null;
        try {
            if (isSet()) {
                dateTime = resolveToDateTime(requiredField());
            }
        } catch (Exception e) {
            throw new CompositeException(e);
        }
    }

    DateTime resolveToDateTime(@NonNull String dateString) throws Exception {
        return new DateTime(dateString);
    }

    @Nullable
    public final DateTime dateTime() {
        return dateTime;
    }

    @NonNull
    public final DateTime requiredDateTime() {
        return ObjectUtils.requireNonNull(dateTime);
    }
}
