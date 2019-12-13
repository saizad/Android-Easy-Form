package com.sa.easyandroidfrom.fields;


abstract public class BasePasswordFieldTest extends BaseNonEmptyMandatoryStringFieldTest {


    public BasePasswordFieldTest(PasswordField passwordField) {
        super(passwordField, passwordField);
    }

    @Override
    final void ogfield_same() {

    }
}