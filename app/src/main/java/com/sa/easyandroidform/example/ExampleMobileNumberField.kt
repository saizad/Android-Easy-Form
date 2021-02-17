package com.sa.easyandroidform.example

import com.sa.easyandroidform.fields.PhoneNumberField
import io.reactivex.exceptions.CompositeException

class ExampleMobileNumberField(
    fieldId: String,
    ogField: String?,
    isMandatory: Boolean,
    length: Int
) : PhoneNumberField(fieldId, ogField, isMandatory, length) {

    @Throws(CompositeException::class)
    override fun validate() {
        super.validate()
        try {
            Thread.sleep(2000)
        }catch (e: Exception){

        }
    }
}