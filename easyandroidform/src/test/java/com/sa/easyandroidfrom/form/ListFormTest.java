package com.sa.easyandroidfrom.form;


import androidx.annotation.NonNull;

import com.sa.easyandroidfrom.form.test.BaseListFormTest;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListFormTest extends BaseListFormTest<ListFormTest.ListModel.Form> {

    @NotNull
    @Override
    public ListModel.Form initForm() {
        return new ListModel.Form();
    }

    public static class Form extends ListForm<FormModelTest.ModelClass> {

        public Form(String formName, boolean isMandatory, List<FormModelTest.ModelClass> bodies) {
            super(formName, isMandatory, bodies, integer ->
                    new FormModelTest.ModelClass.Form(formName + "" + 1, isMandatory));
        }
    }

    public static class ListModel extends FieldFormTest.FieldModelClass {
        public final List<FormModelTest.ModelClass> modelClassList;

        public ListModel(List<FormModelTest.ModelClass> modelClassList, FieldFormTest.FieldModelClass fieldModelClass) {
            super(fieldModelClass.modelClass, new FormModelTest.ModelClass(fieldModelClass));
            this.modelClassList = modelClassList;
        }

        public static class Form extends FieldFormTest.FieldModelClass.Form<ListModel> {
            protected final ListFormTest.Form modelList;

            public Form() {
                super(new ArrayList<>(
                        Collections.singletonList(
                                new ListFormTest.Form("list-models", true,
                                        Arrays.asList(
                                                new FormModelTest.ModelClass(1, "asdfasd"),
                                                new FormModelTest.ModelClass(2, "asdf")
                                        )
                                )
                        )));
                modelList = requiredField("list-models");
            }

            @NonNull
            @Override
            protected ListModel buildForm() {
                final FieldFormTest.FieldModelClass fieldModelClass = super.buildForm();
                return new ListModel(modelList.requiredBuild(), fieldModelClass);
            }
        }
    }
}
