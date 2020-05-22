package com.sa.easyandroidform.example;

import androidx.annotation.NonNull;

import com.sa.easyandroidform.fields.PasswordField;
import com.sa.easyandroidform.fields.ConfirmPasswordField;
import com.sa.easyandroidform.fields.EmailField;
import com.sa.easyandroidform.fields.GenderField;
import com.sa.easyandroidform.fields.MandatoryStringField;
import com.sa.easyandroidform.fields.PhoneNumberField;
import com.sa.easyandroidform.fields.time.DobField;
import com.sa.easyandroidform.form.FormModel;

import java.util.ArrayList;
import java.util.Arrays;

public class MixForm extends MandatoryForm {

    private String gender;
    private String dob;
    private final String password;
    private final String confirmPassword;

    public MixForm(String firstName, String lastName, String email, String mobileNumber, String password, String confirmPassword) {
        super(firstName, lastName, email, mobileNumber);
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public static class Form extends FormModel<MixForm> {

        public final MandatoryStringField firstNameField;
        public final MandatoryStringField lastNameField;
        public final EmailField emailField;
        public final PhoneNumberField mobileNumberField;
        public final DobField dobField;
        public final GenderField genderField;
        public final PasswordField passwordField;
        public final ConfirmPasswordField confirmPasswordField;

        public Form() {
            super(new ArrayList<>(Arrays.asList(
                    new MandatoryStringField("firstname"),
                    new MandatoryStringField("lastname"),
                    new EmailField("email", true),
                    new PhoneNumberField("MobileNumber", true, 10),
                    new DobField("dob", 18),
                    new GenderField("gender", Gender.ToList.getList()),
                    new PasswordField("password"),
                    new ConfirmPasswordField("Confirm Password")
            )));

            firstNameField = (MandatoryStringField) getField("firstname");
            lastNameField = (MandatoryStringField) getField("lastname");
            emailField = (EmailField) getField("email");
            mobileNumberField = (PhoneNumberField) getField("MobileNumber");
            dobField = (DobField) getField("dob");
            genderField = (GenderField) getField("gender");
            passwordField = (PasswordField) getField("password");
            confirmPasswordField = (ConfirmPasswordField) getField("Confirm Password");
            confirmPasswordField.setRelated(passwordField);
        }

        @NonNull
        @Override
        protected MixForm buildForm() {
            final MixForm mixForm = new MixForm(firstNameField.getField(), lastNameField.getField(), emailField.getField(), mobileNumberField.getField(), passwordField.getField(), confirmPasswordField.getField());
            mixForm.gender = genderField.getField();
            mixForm.dob = dobField.getField();
            return mixForm;
        }
    }
}
