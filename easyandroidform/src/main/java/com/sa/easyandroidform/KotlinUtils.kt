package com.sa.easyandroidform

import com.sa.easyandroidform.fields.BaseField

fun checkForDuplicateFieldName(fields:List<BaseField<*>>): List<BaseField<*>> {
    return Utils.checkForDuplicateFieldName(fields)
}