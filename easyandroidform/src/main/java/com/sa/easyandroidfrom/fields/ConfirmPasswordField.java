package com.sa.easyandroidfrom.fields;


import androidx.annotation.NonNull;

import com.sa.easyandroidfrom.StringUtils;

public class ConfirmPasswordField extends BasePasswordField {

    private BasePasswordField basePasswordField;

    public ConfirmPasswordField(@NonNull String fieldId) {
        super(fieldId);
    }

    public void setRelated(BasePasswordField basePasswordField) {
        this.basePasswordField = basePasswordField;
        basePasswordField.observable().subscribe(__ -> publish());
    }

    @Override
    public void validate() throws Exception {
        super.validate();
        if (!StringUtils.isNullOrEmpty(basePasswordField.getField())) {
            if (!basePasswordField.getField().equals(getField())) {
                throw new Exception("Password doesn't match");
            }
        }
    }
}
