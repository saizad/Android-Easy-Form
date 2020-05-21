package com.sa.easyandroidfrom.form.test;

import com.sa.easyandroidfrom.fields.BaseField;
import com.sa.easyandroidfrom.form.FormModel;
import com.sa.easyandroidfrom.form.ListForm;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;

abstract public class BaseListFormTest<F extends FormModel<?>> extends BaseFieldFormTest<F> {


    @Nullable
    @Override
    public Object invalidValue(@NotNull BaseField<?> field) {
        setNull(field);
        return super.invalidValue(field);
    }

    private void setNull(@NotNull BaseField<?> field){
        if(field instanceof ListForm<?>){
            for (BaseField baseField : ((ListForm<?>) field).fields) {
                formModelSetNullValue(((FormModel) baseField));
            }
        }
    }

    @Test
    @Override
    public void isSet_False() {
        for (BaseField field : form.fields) {
            if(field instanceof ListForm<?>){
                setNull(field);
            }
        }
        super.isSet_False();
    }
}
