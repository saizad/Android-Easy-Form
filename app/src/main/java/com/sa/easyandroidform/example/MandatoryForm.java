package com.sa.easyandroidform.example;

import androidx.annotation.NonNull;

import com.sa.easyandroidform.fields.MandatoryStringField;
import com.sa.easyandroidform.form.FormModel;

import java.util.ArrayList;
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


    public static class Form extends FormModel<MandatoryForm> {

                public final MandatoryStringField firstNameField;
        public final MandatoryStringField lastNameField;
        public final ExampleEmailField emailField;
        public final ExampleMobileNumberField mobileNumberField;

        public Form() {
            super(new ArrayList<>(Arrays.asList(
                    new MandatoryStringField("firstname", "asdffas"),
                    new MandatoryStringField("lastname", "asdfadf"),
                    new ExampleMobileNumberField("mobilenumber", "9844893089", true, 10),
                    new ExampleEmailField("email", "addassadfa@gmail.com", true)
            )));

            firstNameField = requiredFindField("firstname");
            lastNameField = requiredFindField("lastname");
            emailField = requiredFindField("email");
            mobileNumberField = requiredFindField("mobilenumber");
        }

        @NonNull
        @Override
        protected MandatoryForm buildForm() {
            return new MandatoryForm("", "", "", "");
//            return new MandatoryForm(firstNameField.getField(), lastNameField.getField(), emailField.getField(), mobileNumberField.getField());
        }
    }
}
