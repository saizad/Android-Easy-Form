package com.sa.easyandroidfrom.field_view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.sa.easyandroidfrom.ObjectUtils
import com.sa.easyandroidfrom.fields.BaseField
import io.reactivex.Observable
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

        field.observable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (ObjectUtils.isNotNull(field.field) && !field.isValidCache) {
                    error()
                } else if (field.isModified) {
                    edited()
                } else {
                    neutral()
                }
            }

        field.nonEmptyInvalidObservable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { pair ->
                showValue(field.field)
                displayError(!field.isValidCache, pair.first)
            }

        Observable.merge<Any>(
            field.notEmptyValidObservable(),
            field.fieldUnsetObservable()
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { o ->
                showValue(field.field)
                displayError(false, "")
            }

        field.networkError()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { error -> displayError(true, error) }

    }

    fun updateValue(value: F?) {
        fieldItem.field = value
    }

    abstract fun fieldMandatory()
    abstract fun error()
    abstract fun edited()
    abstract fun neutral()
    abstract fun showValue(field: F?)
    abstract fun displayError(show: Boolean, error: String?)

}
