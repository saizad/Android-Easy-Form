package com.sa.easyandroidform.form;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidform.TestUtils;
import com.sa.easyandroidform.fields.BaseField;
import com.sa.easyandroidform.fields.BooleanField;
import com.sa.easyandroidform.fields.FloatField;
import com.sa.easyandroidform.fields.IntegerField;
import com.sa.easyandroidform.fields.StringField;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class NonMandatoryFormModelTest<M extends NonMandatoryFormModelTest.ModelClass> extends BaseModelFormTest<NonMandatoryFormModelTest.ModelClass.Form<M>> {

    @NotNull
    @Override
    public ModelClass.Form<M> initForm() {
        return new ModelClass.Form<>();
    }

    @Override
    void setValidValue(BaseField<?> field) {
        TestUtils.setRandom(field);
    }

    @Override
    BaseField<?> changeFormFieldToAnyValue() {
        TestUtils.setRandom(form.integerField);
        return form.integerField;
    }

    public static class ModelClass {

        public @Nullable
        Integer integer;
        public @Nullable
        String string;
        public @Nullable
        Boolean aBoolean;
        public @Nullable
        Float aFloat;

        public static class Form<M extends ModelClass> extends FormModel<M> {

            public final IntegerField integerField = requiredFindField("integer");
            public final StringField stringField = requiredFindField("string");
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
                        new IntegerField("integer"),
                        new StringField("string"),
                        new FloatField("float"),
                        new BooleanField("boolean")
                );
            }

            @NonNull
            @Override
            protected M buildForm() {
                final M m = (M) new ModelClass();
                m.aBoolean = booleanField.getField();
                m.aFloat = floatField.getField();
                m.integer = integerField.getField();
                m.string = stringField.getField();
                return m;
            }
        }
    }
}
