package com.sa.easyandroidfrom.fields.test;


import com.sa.easyandroidfrom.fields.PasswordField;

abstract public class BasePasswordFieldTest extends BaseMandatoryStringFieldTest {


    public BasePasswordFieldTest(PasswordField passwordField) {
        super(passwordField, passwordField);
    }

    @Override
    final void ogfield_same() {

    }
}