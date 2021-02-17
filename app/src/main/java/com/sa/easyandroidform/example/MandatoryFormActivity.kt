package com.sa.easyandroidform.example

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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

        form.emailField.validatingObservable().subscribe {
            emailValidating.isVisible = it
        }

        form.mobileNumberField.validatingObservable().subscribe {
            phoneValidating.isVisible = it
        }

        form.validObservable()
            .subscribe {
                save.isEnabled = it
            }
    }
}
