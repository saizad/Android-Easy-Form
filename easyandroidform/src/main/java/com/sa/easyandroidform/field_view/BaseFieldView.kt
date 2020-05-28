package com.sa.easyandroidform.field_view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.sa.easyandroidform.Utils
import com.sa.easyandroidform.fields.BaseField
import io.reactivex.android.schedulers.AndroidSchedulers

abstract class BaseFieldView<F> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    lateinit var fieldItem: BaseField<F>

    fun setField(field: BaseField<F>) {

        this.fieldItem = field

        if (field.isMandatory) {
            fieldMandatory()
        }

        field.errorStateObservable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if(field.isValid) {
                    displayError(false, null)
                }else {
                    val second = it.second
                    if(second != null && field.isSet){
                        displayError(true, Utils.compositeExceptionMessage(second))
                    }
                }
            }

        field.observable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                showValue(field.field)
            }

        field.networkError()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { error -> displayError(true, error) }

    }

    fun updateValue(value: F?) {
        fieldItem.field = value
    }

    abstract fun fieldMandatory()
    abstract fun showValue(field: F?)
    abstract fun displayError(show: Boolean, error: String?)

}
