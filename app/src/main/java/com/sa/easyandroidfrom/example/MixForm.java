package com.sa.easyandroidfrom.example;

import androidx.annotation.NonNull;

import com.sa.easyandroidfrom.fields.BasePasswordField;
import com.sa.easyandroidfrom.fields.ConfirmPasswordField;
import com.sa.easyandroidfrom.fields.EmailField;
import com.sa.easyandroidfrom.fields.GenderField;
import com.sa.easyandroidfrom.fields.NonEmptyMandatoryStringField;
import com.sa.easyandroidfrom.fields.PhoneNumberField;
import com.sa.easyandroidfrom.fields.time.DobField;
import com.sa.easyandroidfrom.form.FormModel;

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

        public final NonEmptyMandatoryStringField firstNameField;
        public final NonEmptyMandatoryStringField lastNameField;
        public final EmailField emailField;
        public final PhoneNumberField mobileNumberField;
        public final DobField dobField;
        public final GenderField genderField;
        public final BasePasswordField passwordField;
        public final ConfirmPasswordField confirmPasswordField;

        public Form() {
            super(Arrays.asList(
                    new NonEmptyMandatoryStringField("firstname"),
                    new NonEmptyMandatoryStringField("lastname"),
                    new EmailField("email", true),
                    new PhoneNumberField("MobileNumber", true, 10),
                    new DobField("dob", 18),
                    new GenderField("gender", Gender.ToList.getList()),
                    new BasePasswordField("password"),
                    new ConfirmPasswordField("Confirm Password")
            ));

            firstNameField = (NonEmptyMandatoryStringField) getField("firstname");
            lastNameField = (NonEmptyMandatoryStringField) getField("lastname");
            emailField = (EmailField) getField("email");
            mobileNumberField = (PhoneNumberField) getField("MobileNumber");
            dobField = (DobField) getField("dob");
            genderField = (GenderField) getField("gender");
            passwordField = (BasePasswordField) getField("password");
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
