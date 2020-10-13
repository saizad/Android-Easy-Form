package com.sa.easyandroidform

import com.sa.easyandroidform.fields.BaseField
import io.reactivex.exceptions.CompositeException

fun checkForDuplicateFieldName(fields:List<BaseField<*>>): List<BaseField<*>> {
    return Utils.checkForDuplicateFieldName(fields)
}

fun CompositeException.combine(): String? {
    val stringBuilder = StringBuilder()
    exceptions.forEach {
        stringBuilder.append(it.message).append("\n")
    }
    return StringUtils.stripTrailingLeadingNewLines(stringBuilder.toString())
}