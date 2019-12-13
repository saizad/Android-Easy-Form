package com.sa.easyandroidfrom.fields;


import androidx.annotation.NonNull;

import com.sa.easyandroidfrom.StringUtils;

public class ConfirmPasswordField extends PasswordField {

    private PasswordField passwordField;

    public ConfirmPasswordField(@NonNull String fieldId) {
        super(fieldId);
    }

    public void setRelated(PasswordField passwordField) {
        this.passwordField = passwordField;
        passwordField.observable().subscribe(__ -> publish());
    }

    @Override
    public void validate() throws Exception {
        super.validate();
        if (!StringUtils.isNullOrEmpty(passwordField.getField())) {
            if (!passwordField.getField().equals(getField())) {
                throw new Exception("Password doesn't match");
            }
        }
    }
}
