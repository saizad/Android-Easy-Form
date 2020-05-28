package com.sa.easyandroidform.form;


import androidx.annotation.NonNull;

import com.sa.easyandroidform.TestUtils;
import com.sa.easyandroidform.fields.BaseField;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sa.easyandroidform.TestUtils.fomModelSetValue;

public class FieldFormTest extends BaseFieldFormTest<FieldFormTest.FieldModelClass.Form<FieldFormTest.FieldModelClass>> {

    @NotNull
    @Override
    public FieldModelClass.Form<FieldFormTest.FieldModelClass> initForm() {
        return new FieldModelClass.Form<>();
    }

    @Override
    public void setValidValue(BaseField<?> field) {
        if(field instanceof FormModel){
            fomModelSetValue((FormModel<?>) field);
        }else {
            TestUtils.setRandom(field);
        }
    }

    @Override
    public BaseField<?> changeFormFieldToAnyValue() {
        TestUtils.setRandom(form.mandatoryModelClassField.floatField);
        return form.mandatoryModelClassField;
    }

    public static class FieldModelClass extends FormModelTest.ModelClass {

        public final FormModelTest.ModelClass mandatoryModelClass;
        public FormModelTest.ModelClass modelClass;

        public FieldModelClass(FormModelTest.ModelClass mandatoryModelClass, FormModelTest.ModelClass baseClass) {
            super(baseClass);
            this.mandatoryModelClass = mandatoryModelClass;
        }

        public static class Form<M extends FieldModelClass> extends FormModelTest.ModelClass.Form<M> {

            private static final String MODEL_CLASS_FIELD_NAME = "model-class";
            private static final String MANDATORY_MODEL_CLASS_FIELD_NAME = "mandatory-model-class";

            public final FormModelTest.ModelClass.Form<FieldModelClass> modelClassField = requiredFindField(MODEL_CLASS_FIELD_NAME);

            public final FormModelTest.ModelClass.Form<FieldModelClass> mandatoryModelClassField = requiredFindField(MANDATORY_MODEL_CLASS_FIELD_NAME);

            public Form(String formName, ArrayList<BaseField<?>> fields) {
                super(formName, new ArrayList<BaseField<?>>() {{
                    addAll(fields);
                    addAll(fields());
                }});
            }

            private Form() {
                this("field-form");
            }

            public Form(ArrayList<BaseField<?>> fields) {
                this("field-form", fields);
            }

            public Form(String formName) {
                super(formName, new ArrayList<>(fields()));
            }

            private static List<BaseField<?>> fields() {
                return Arrays.asList(
                        new FormModelTest.ModelClass.Form<>(MODEL_CLASS_FIELD_NAME),
                        new FormModelTest.ModelClass.Form<>(MANDATORY_MODEL_CLASS_FIELD_NAME)
                );
            }

            @NonNull
            @Override
            protected M buildForm() {
                final FormModelTest.ModelClass buildForm = super.buildForm();
                final M m = (M) new FieldModelClass(mandatoryModelClassField.requiredBuild(), buildForm);
                m.modelClass = modelClassField.requiredBuild();
                return m;
            }
        }
    }
}
