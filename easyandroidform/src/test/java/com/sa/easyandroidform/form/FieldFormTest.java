package com.sa.easyandroidform.form;


import androidx.annotation.NonNull;

import com.sa.easyandroidform.fields.BaseField;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FieldFormTest extends BaseFieldFormTest<FieldFormTest.FieldModelClass.Form<FieldFormTest.FieldModelClass>> {

    @NotNull
    @Override
    public FieldModelClass.Form initForm() {
        return new FieldModelClass.Form();
    }


    public static class FieldModelClass extends FormModelTest.ModelClass {

        public final FormModelTest.ModelClass modelClass;

        public FieldModelClass(FormModelTest.ModelClass fieldModelClass, FormModelTest.ModelClass baseClass) {
            super(baseClass);
            this.modelClass = fieldModelClass;
        }

        public static class Form<M extends FieldModelClass> extends FormModelTest.ModelClass.Form<M> {

            public final FormModelTest.ModelClass.Form<FieldModelClass> modelClassField = requiredField("model-class");

            public Form(String formName, boolean isMandatory, ArrayList<BaseField> fields) {
                super(formName, isMandatory, new ArrayList<BaseField>() {{
                    addAll(fields);
                    addAll(fields());
                }});
            }

            private Form() {
                this("field-form", false);
            }

            public Form(ArrayList<BaseField> fields) {
                this("field-form", false, fields);
            }

            public Form(String formName, boolean isMandatory) {
                super(formName, isMandatory, new ArrayList<>(fields()));
            }

            private static List<BaseField<?>> fields() {
                return Collections.singletonList(
                        new FormModelTest.ModelClass.Form<>("model-class", true)
                );
            }

            @NonNull
            @Override
            protected M buildForm() {
                final FormModelTest.ModelClass buildForm = super.buildForm();
                return (M) new FieldModelClass(modelClassField.requiredBuild(), buildForm);
            }
        }
    }
}
