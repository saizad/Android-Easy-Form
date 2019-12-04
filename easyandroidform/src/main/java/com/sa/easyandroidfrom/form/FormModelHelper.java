package com.sa.easyandroidfrom.form;

import androidx.annotation.NonNull;

import com.sa.easyandroidfrom.fields.BaseField;

import java.util.Arrays;


public class FormModelHelper extends FormModel<Object> {

  public FormModelHelper(BaseField... list) {
    super(Arrays.asList(list));
  }

  @NonNull
  @Override
  protected Object buildForm() {
    return null;
  }
}
