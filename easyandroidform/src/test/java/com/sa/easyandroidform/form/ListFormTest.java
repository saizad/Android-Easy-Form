package com.sa.easyandroidform.form;


import androidx.annotation.NonNull;

import com.sa.easyandroidform.StringUtils;
import com.sa.easyandroidform.TestUtils;
import com.sa.easyandroidform.fields.BaseField;
import com.sa.easyandroidform.fields.StringField;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sa.easyandroidform.TestUtils.fomModelSetValue;

public class ListFormTest extends BaseListFormTest<ListFormTest.ListModel.Form> {

    @NotNull
    @Override
    public ListModel.Form initForm() {
        return new ListModel.Form();
    }

    @Override
    void setValidValue(BaseField<?> field) {
        if(field instanceof FormModel){
            fomModelSetValue((FormModel<?>) field);
        }else {
            TestUtils.setRandom(field);
        }
    }

    @Override
    BaseField<?> changeFormFieldToAnyValue() {
        final FormModelTest.ModelClass.Form listForm = (FormModelTest.ModelClass.Form) this.form.mandatoryModelList.fields.get(0);
        TestUtils.setRandom(listForm.mandatoryStringField);
        return this.form.mandatoryModelList;
    }


    public static class Form extends ListForm<FormModelTest.ModelClass> {

        public Form(String formName, List<FormModelTest.ModelClass> bodies) {
            super(formName, bodies, integer -> new FormModelTest.ModelClass.Form<>(formName + "" + integer));
        }
    }

    public static class ListModel extends FieldFormTest.FieldModelClass {
        public final List<FormModelTest.ModelClass> mandatoryModelClassList;
        public List<FormModelTest.ModelClass> modelClassList;

        public ListModel(List<FormModelTest.ModelClass> mandatoryModelClassList, FieldFormTest.FieldModelClass fieldModelClass) {
            super(fieldModelClass.modelClass, new FormModelTest.ModelClass(fieldModelClass));
            this.mandatoryModelClassList = mandatoryModelClassList;
        }

        private static List<FormModelTest.ModelClass> list(){
            return Arrays.asList(
                    new FormModelTest.ModelClass(1, StringUtils.random(10)),
                    new FormModelTest.ModelClass(2, StringUtils.random(10))
            );
        }

        public static class Form extends FieldFormTest.FieldModelClass.Form<ListModel> {
            protected final ListFormTest.Form mandatoryModelList;
            protected final ListFormTest.Form modelList;
            protected final StringField listFormString = requiredFindField("list-form-string");

            public Form() {
                super(new ArrayList<>(
                        Arrays.asList(
                                new StringField("list-form-string"),
                                new ListFormTest.Form("mandatory-list-models", list()),
                                new ListFormTest.Form("list-models", list())
                        )));
                mandatoryModelList = requiredFindField("mandatory-list-models");
                modelList = requiredFindField("list-models");
            }

            @NonNull
            @Override
            protected ListModel buildForm() {
                final FieldFormTest.FieldModelClass fieldModelClass = super.buildForm();
                final ListModel listModel = new ListModel(mandatoryModelList.requiredBuild(), fieldModelClass);
                listModel.modelClassList = modelList.requiredBuild();
                return listModel;
            }
        }
    }
}
