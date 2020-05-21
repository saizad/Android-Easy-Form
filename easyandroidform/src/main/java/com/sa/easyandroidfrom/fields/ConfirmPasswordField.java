package com.sa.easyandroidfrom.fields;


import androidx.annotation.NonNull;

import com.sa.easyandroidfrom.StringUtils;

import io.reactivex.exceptions.CompositeException;

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
    public void validate() throws CompositeException {
        super.validate();
        if(passwordField == null){
            throw new CompositeException(new Exception("Confirm Password can't be validated without providing password"));
        }

        if (!StringUtils.isNullOrEmpty(passwordField.getField())) {
            if (!passwordField.getField().equals(getField())) {
                throw new CompositeException(new Exception("Password doesn't match"));
            }
        }
    }
}
