package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GenderField extends NonEmptyStringField {


    private final List<String> genders;

    public GenderField(@NonNull String fieldId, List<String> genders) {
        this(fieldId, false, genders);
    }

    public GenderField(@NonNull String fieldId, boolean isMandatory, List<String> genders) {
        this(fieldId, null, isMandatory, genders);
    }

    public GenderField(@NonNull String fieldId, @Nullable String ogField, List<String> genders) {
        this(fieldId, ogField, false, genders);
    }

    public GenderField(@NonNull String fieldId, @Nullable String ogField, boolean isMandatory, List<String> genders) {
        super(fieldId, ogField, isMandatory);
        this.genders = genders;
    }

    @Override
    public void validate() throws Exception {
        for (String gender : genders) {
            if(gender.equalsIgnoreCase(getField())){
                return;
            }
        }

        throw new Exception("Invalid gender selected");
    }
}
