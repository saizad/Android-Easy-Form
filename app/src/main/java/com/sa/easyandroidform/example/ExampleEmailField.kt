package com.sa.easyandroidform.example

import com.sa.easyandroidform.fields.EmailField
import io.reactivex.exceptions.CompositeException

class ExampleEmailField :EmailField {
    constructor(fieldId: String) : super(fieldId) {}
    constructor(fieldId: String, isMandatory: Boolean) : super(
        fieldId,
        isMandatory
    ) {
    }

    constructor(fieldId: String, ogField: String?) : super(fieldId, ogField) {}
    constructor(
        fieldId: String,
        ogField: String?,
        isMandatory: Boolean
    ) : super(fieldId, ogField, isMandatory) {
    }

    @Throws(CompositeException::class)
    override fun validate() {
        super.validate()
        try {
            Thread.sleep(2000)
        }catch (e: Exception){
//            throw CompositeException(Exception("Thread Interrupted"))
        }
    }
}