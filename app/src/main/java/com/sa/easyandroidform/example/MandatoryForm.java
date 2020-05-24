package com.sa.easyandroidform.example;

import androidx.annotation.NonNull;

import com.sa.easyandroidform.fields.EmailField;
import com.sa.easyandroidform.fields.MandatoryStringField;
import com.sa.easyandroidform.fields.PhoneNumberField;
import com.sa.easyandroidform.form.FormModel;

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

        public final MandatoryStringField firstNameField;
        public final MandatoryStringField lastNameField;
        public final EmailField emailField;
        public final PhoneNumberField mobileNumberField;

        public Form() {
            super(Arrays.asList(new MandatoryStringField("firstname"), new MandatoryStringField("lastname"), new EmailField("email", true), new PhoneNumberField("mobilenumber",true, 10)));

            firstNameField = (MandatoryStringField) findField("firstname");
            lastNameField = (MandatoryStringField) findField("lastname");
            emailField = (EmailField) findField("email");
            mobileNumberField = (PhoneNumberField) findField("mobilenumber");
        }

        @NonNull
        @Override
        protected MandatoryForm buildForm() {
            return new MandatoryForm(firstNameField.getField(), lastNameField.getField(), emailField.getField(), mobileNumberField.getField());
        }
    }
}
