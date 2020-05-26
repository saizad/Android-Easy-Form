package com.sa.easyandroidform.form;

import androidx.annotation.NonNull;

import com.sa.easyandroidform.fields.BaseField;

import java.util.ArrayList;
import java.util.Arrays;


public class FormModelHelper extends FormModel<Object> {

    public FormModelHelper(BaseField<?>... list) {
        super(new ArrayList<>(Arrays.asList(list)));
    }

    @NonNull
    @Override
    protected Object buildForm() {
        return new Object();
    }
}
