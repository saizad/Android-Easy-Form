package com.sa.easyandroidform.form;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidform.fields.BaseField;
import com.sa.easyandroidform.fields.BooleanField;
import com.sa.easyandroidform.fields.FloatField;
import com.sa.easyandroidform.fields.MandatoryIntegerField;
import com.sa.easyandroidform.fields.MandatoryStringField;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class FormModelTest<M extends FormModelTest.ModelClass> extends BaseFormTestJava<FormModelTest.ModelClass.Form<M>> {

    @NotNull
    @Override
    public ModelClass.Form<M> initForm() {
        return new ModelClass.Form<>();
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

            public final MandatoryIntegerField mandatoryIntegerField = requiredField("integer");
            public final MandatoryStringField mandatoryStringField = requiredField("string");
            public final FloatField floatField = requiredField("float");
            public final BooleanField booleanField = requiredField("boolean");

            public Form(String formName, boolean isMandatory, ArrayList<BaseField> fields) {
                super(formName, isMandatory, new ArrayList<BaseField>() {
                    {
                        addAll(fields);
                        addAll(fields());
                    }
                });
            }

            private Form() {
                this("form", false);
            }

            public Form(String formName, boolean isMandatory) {
                super(formName, isMandatory, new ArrayList<>(fields()));
            }

            private static List<BaseField<?>> fields() {
                return asList(
                        new MandatoryIntegerField("integer", 1),
                        new MandatoryStringField("string", "adsfasdf"),
                        new FloatField("float", 1f),
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
