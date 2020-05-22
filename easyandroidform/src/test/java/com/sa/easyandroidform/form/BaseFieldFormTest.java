package com.sa.easyandroidform.form;

import com.sa.easyandroidform.fields.BaseField;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;

abstract public class BaseFieldFormTest<F extends FormModel<?>> extends BaseFormTest<F> {


    @Nullable
    @Override
    public Object invalidValue(@NotNull BaseField<?> field) {
        if(field instanceof FormModel){
            formModelSetNullValue(((FormModel<?>) field));
        }
        return super.invalidValue(field);
    }

    @Test
    @Override
    public void isSet_False() {
        for (BaseField<?> field : form.fields) {
            if(field instanceof FormModel){
                formModelSetNullValue(((FormModel) field));
            }else {
                field.setField(null);
            }
        }
    }

    protected void formModelSetNullValue(@NotNull FormModel<?> field) {
        for (BaseField baseField : field.fields) {
            baseField.setField(null);
        }
    }
}
