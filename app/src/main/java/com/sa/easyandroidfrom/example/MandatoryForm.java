package com.sa.easyandroidfrom.example;

import androidx.annotation.NonNull;

import com.sa.easyandroidfrom.fields.EmailField;
import com.sa.easyandroidfrom.fields.NonEmptyMandatoryStringField;
import com.sa.easyandroidfrom.fields.PhoneNumberField;
import com.sa.easyandroidfrom.form.FormModel;

import java.util.Arrays;

public class MandatoryForm {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String mobileNumber;

    public MandatoryForm(String firstName, String lastName, String email, String mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }


    public static class Form extends FormModel<MandatoryForm>{

        public final NonEmptyMandatoryStringField firstNameField;
        public final NonEmptyMandatoryStringField lastNameField;
        public final EmailField emailField;
        public final PhoneNumberField mobileNumberField;

        public Form() {
            super(Arrays.asList(new NonEmptyMandatoryStringField("firstname"), new NonEmptyMandatoryStringField("lastname"), new EmailField("email", true), new PhoneNumberField("mobilenumber",true, 10)));

            firstNameField = (NonEmptyMandatoryStringField) getField("firstname");
            lastNameField = (NonEmptyMandatoryStringField) getField("lastname");
            emailField = (EmailField) getField("email");
            mobileNumberField = (PhoneNumberField) getField("mobilenumber");
        }

        @NonNull
        @Override
        protected MandatoryForm buildForm() {
            return new MandatoryForm(firstNameField.getField(), lastNameField.getField(), emailField.getField(), mobileNumberField.getField());
        }
    }
}
