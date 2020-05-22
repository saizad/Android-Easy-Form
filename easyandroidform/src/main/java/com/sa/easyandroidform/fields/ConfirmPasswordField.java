package com.sa.easyandroidform.fields;


import androidx.annotation.NonNull;

import com.sa.easyandroidform.ObjectUtils;
import com.sa.easyandroidform.StringUtils;

import io.reactivex.Observable;
import io.reactivex.exceptions.CompositeException;

public class ConfirmPasswordField extends PasswordField {

    private PasswordField passwordField;

    public ConfirmPasswordField(@NonNull String fieldId) {
        super(fieldId);
    }

    public void setRelated(@NonNull PasswordField passwordField) {
        ObjectUtils.requireNonNull(passwordField,"PasswordField para should not be null");
        this.passwordField = passwordField;
        passwordField.observable().subscribe(__ -> publish());
    }

    public Observable<Boolean> nonEmptyPasswordMatch(){
        return setObservable()
                .filter(__ -> passwordField.isSet())
                .map(s -> s.equals(passwordField.getField()));
    }

    @Override
    public void validate() throws CompositeException {
        super.validate();
        if(passwordField == null){
            throw new CompositeException(new Exception("Confirm Password can't be validated without providing original password"));
        }

        if (!StringUtils.isNullOrEmpty(passwordField.getField())) {
            if (!passwordField.getField().equals(getField())) {
                throw new CompositeException(new Exception("Password doesn't match"));
            }
        }
    }
}
