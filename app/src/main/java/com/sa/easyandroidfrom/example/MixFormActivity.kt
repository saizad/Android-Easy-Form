package com.sa.easyandroidfrom.example

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.sa.easyandroidfrom.ObjectUtils
import kotlinx.android.synthetic.main.activity_mix_form.*
import org.joda.time.DateTime

class MixFormActivity : AppCompatActivity() {

    val form = MixForm.Form()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mix_form)

        firstName.setField(form.firstNameField)
        lastName.setField(form.lastNameField)
        email.setField(form.emailField)
        mobileNumber.setField(form.mobileNumberField)
        password.setField(form.passwordField)
        confirmPassword.setField(form.confirmPasswordField)

        val toList = Gender.ToList.getList()
        Utils.initChipGroup(gender, form.genderField.field, toList) {
            val chip = it as Chip
            form.genderField.field = chip.text.toString()
        }

        form.dobField
            .invalidObservable()
            .subscribe {
                dobError.text = it.first
            }

        form.dobField.setObservable()
            .subscribe {
                dob.text = it
                Utils.switchVisibility(dobError, !form.dobField.isFieldValid)
            }

        dob.setOnClickListener {
            val dateTime = ObjectUtils.coalesce(form.dobField.dateTime(), DateTime())
            DatePickerDialog(this, { view, year1, month1, dayOfMonth ->
                val month = month1 + 1
                val str = "$year1-$month-$dayOfMonth"
                form.dobField.field = str
            }, dateTime.year, dateTime.monthOfYear - 1, dateTime.dayOfMonth).show()
        }

        form.isAllFieldValidObservable
            .subscribe {
                save.isEnabled = it
            }
    }
}
