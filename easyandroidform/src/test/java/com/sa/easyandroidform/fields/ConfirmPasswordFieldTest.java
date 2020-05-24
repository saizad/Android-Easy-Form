package com.sa.easyandroidform.fields;


import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.reactivex.observers.TestObserver;

public class ConfirmPasswordFieldTest extends BasePasswordFieldTest {

    private static final String FIELD_NAME = "random";
    private static final String NEW_VALUE = "new_value";
    private static final String RANDOM_VALUE = "random_value";
    private final PasswordField passwordField = new PasswordField(FIELD_NAME);

    public ConfirmPasswordFieldTest() {
        super(new ConfirmPasswordField(FIELD_NAME));
        ((ConfirmPasswordField) field).setRelated(passwordField);
    }

    @Test
    public void password_matches(){
        passwordField.setField(NEW_VALUE);
        field.setField(NEW_VALUE);
        Assertions.assertTrue(field.isValid());
    }

    @Test
    public void password_matches_fail(){
        passwordField.setField(NEW_VALUE);
        field.setField(RANDOM_VALUE);
        Assertions.assertFalse(field.isValid());
    }

    @Test
    public void confirm_password_called(){
        final TestObserver<Object> test = field.observable().test();
        passwordField.setField(NEW_VALUE);
        test.assertValueCount(2);
    }

    @Test
    public void nonEmptyPasswordMatch_false(){
        passwordField.setField(NEW_VALUE);
        field.setField(RANDOM_VALUE);
        ((ConfirmPasswordField) field).nonEmptyPasswordMatch()
                .test()
                .assertValue(false);
    }

    @Test
    public void nonEmptyPasswordMatch_true(){
        passwordField.setField(NEW_VALUE);
        field.setField(NEW_VALUE);
        ((ConfirmPasswordField) field).nonEmptyPasswordMatch()
                .test()
                .assertValue(true);
    }

    @NotNull
    @Override
    protected String fieldName() {
        return FIELD_NAME;
    }

    @NotNull
    @Override
    protected String getNewValidFieldValue() {
        return NEW_VALUE;
    }

}