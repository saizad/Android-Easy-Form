package com.sa.easyandroidform.form;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidform.TestUtils;
import com.sa.easyandroidform.fields.BaseField;
import com.sa.easyandroidform.fields.BooleanField;
import com.sa.easyandroidform.fields.FloatField;
import com.sa.easyandroidform.fields.MandatoryIntegerField;
import com.sa.easyandroidform.fields.MandatoryStringField;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

public class FormModelTest<M extends FormModelTest.ModelClass> extends BaseModelFormTest<FormModelTest.ModelClass.Form<M>> {

    @NotNull
    @Override
    public ModelClass.Form<M> initForm() {
        return new ModelClass.Form<>();
    }

    @Override
    public void setValidValue(BaseField<?> field) {
        TestUtils.setRandom(field);
    }

    @Override
    public BaseField<?> changeFormFieldToAnyValue() {
        TestUtils.setRandom(form.floatField);
        return form.floatField;
    }

    public static class ModelClass {

        public final Integer integer;
        public final String string;
        public @Nullable
        Boolean aBoolean;
        public @Nullable
        Float aFloat;

        public ModelClass(ModelClass modelClass) {
            this(modelClass.integer, modelClass.string);
            aFloat = modelClass.aFloat;
            aBoolean = modelClass.aBoolean;
        }

        public ModelClass(Integer integer, String string) {
            this.integer = integer;
            this.string = string;
        }

        public static class Form<M extends ModelClass> extends FormModel<M> {

            public final MandatoryIntegerField mandatoryIntegerField = requiredFindField("integer");
            public final MandatoryStringField mandatoryStringField = requiredFindField("string");
            public final FloatField floatField = requiredFindField("float");
            public final BooleanField booleanField = requiredFindField("boolean");

            public Form(String formName, ArrayList<BaseField<?>> fields) {
                super(formName, new ArrayList<BaseField<?>>() {
                    {
                        addAll(fields);
                        addAll(fields());
                    }
                });
            }

            private Form() {
                this("form");
            }

            public Form(String formName) {
                super(formName, new ArrayList<>(fields()));
            }

            private static List<BaseField<?>> fields() {
                return asList(
                        new MandatoryIntegerField("integer", new Random().nextInt(100)),
                        new MandatoryStringField("string"),
                        new FloatField("float", new Random().nextFloat()),
                        new BooleanField("boolean")
                );
            }

            @NonNull
            @Override
            protected M buildForm() {
                final M m = (M) new ModelClass(mandatoryIntegerField.requiredField(), mandatoryStringField.requiredField());
                m.aBoolean = booleanField.getField();
                m.aFloat = floatField.getField();
                return m;
            }
        }
    }
}
