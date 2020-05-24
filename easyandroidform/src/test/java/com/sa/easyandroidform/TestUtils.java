package com.sa.easyandroidform;

import com.sa.easyandroidform.fields.BaseField;
import com.sa.easyandroidform.fields.BooleanField;
import com.sa.easyandroidform.fields.DoubleField;
import com.sa.easyandroidform.fields.FloatField;
import com.sa.easyandroidform.fields.IntegerField;
import com.sa.easyandroidform.fields.LongField;
import com.sa.easyandroidform.fields.MandatoryBooleanField;
import com.sa.easyandroidform.fields.MandatoryDoubleField;
import com.sa.easyandroidform.fields.MandatoryFloatField;
import com.sa.easyandroidform.fields.MandatoryIntegerField;
import com.sa.easyandroidform.fields.MandatoryLongField;
import com.sa.easyandroidform.fields.MandatoryStringField;
import com.sa.easyandroidform.fields.StringField;
import com.sa.easyandroidform.form.FormModel;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public final class TestUtils {
    private TestUtils(){}

    public static void fomModelSetValue(@NotNull FormModel<?> formModel) {
        for (BaseField<?> baseField : formModel.fields) {
            if(baseField instanceof FormModel){
                fomModelSetValue((FormModel<?>) baseField);
            }else {
                setRandom(baseField);
            }
        }
    }

    public static void setRandom(BaseField<?> field){
        if(field instanceof IntegerField) {
            ((IntegerField) field).setField(new Random().nextInt());
        }else if(field instanceof StringField) {
            ((StringField) field).setField(StringUtils.random(10));
        }else if(field instanceof FloatField) {
            ((FloatField) field).setField(new Random().nextFloat());
        }else if(field instanceof DoubleField) {
            ((DoubleField) field).setField(new Random().nextDouble());
        }else if(field instanceof BooleanField) {
            ((BooleanField) field).setField(new Random().nextBoolean());
        }else if(field instanceof LongField) {
            ((LongField) field).setField(new Random().nextLong());
        }else if(field instanceof MandatoryDoubleField) {
            ((MandatoryDoubleField) field).setField(new Random().nextDouble());
        }else if(field instanceof MandatoryBooleanField) {
            ((MandatoryBooleanField) field).setField(new Random().nextBoolean());
        }else if(field instanceof MandatoryStringField) {
            ((MandatoryStringField) field).setField(StringUtils.random(10));
        }else if(field instanceof MandatoryFloatField) {
            ((MandatoryFloatField) field).setField(new Random().nextFloat());
        }else if(field instanceof MandatoryIntegerField) {
            ((MandatoryIntegerField) field).setField(new Random().nextInt());
        }else if(field instanceof MandatoryLongField) {
            ((MandatoryLongField) field).setField(new Random().nextLong());
        }
    }
}
