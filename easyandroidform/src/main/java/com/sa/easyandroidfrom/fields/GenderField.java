package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.exceptions.CompositeException;

public class GenderField extends NonEmptyStringField {


    private final @NonNull List<String> genders;

    public GenderField(@NonNull String fieldId, @NonNull List<String> genders) {
        this(fieldId, false, genders);
    }

    public GenderField(@NonNull String fieldId, boolean isMandatory, @NonNull List<String> genders) {
        this(fieldId, null, isMandatory, genders);
    }

    public GenderField(@NonNull String fieldId, @Nullable String ogField, @NonNull List<String> genders) {
        this(fieldId, ogField, false, genders);
    }

    public GenderField(@NonNull String fieldId, @Nullable String ogField, boolean isMandatory, @NotNull List<String> genders) {
        super(fieldId, ogField, isMandatory);
        this.genders = genders;
    }

    @Override
    public void validate() throws CompositeException {
        super.validate();
        for (String gender : genders) {
            if (gender.equalsIgnoreCase(getField())) {
                return;
            }
        }
        throw new CompositeException(new Exception("Invalid gender selected"));
    }
}
