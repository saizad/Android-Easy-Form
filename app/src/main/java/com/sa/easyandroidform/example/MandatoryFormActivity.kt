package com.sa.easyandroidform.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_mandatory_form.*

class MandatoryFormActivity : AppCompatActivity() {

    val form = MandatoryForm.Form()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mandatory_form)

        firstName.setField(form.firstNameField)
        lastName.setField(form.lastNameField)
        email.setField(form.emailField)
        mobileNumber.setField(form.mobileNumberField)

        form.isAllFieldValidObservable
            .subscribe {
                save.isEnabled = it
            }
    }
}
