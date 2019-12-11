package com.sa.easyandroidfrom.fields;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class NonZeroIntFieldTest {

    private static final String FIELD_NAME = "random";

    @Spy
    final NonZeroIntField nonZeroIntField = Mockito.spy(new NonZeroIntField(FIELD_NAME));

    @BeforeAll
    static void setUp() {
        MockitoAnnotations.initMocks(new FieldTest());
    }

    @Test
    public void validObservable_0_false() {
        nonZeroIntField.setField(0);
        nonZeroIntField.validObservable()
                .test()
                .assertValue(false);
    }

    @Test
    public void validObservable_negative_value_false() {
        nonZeroIntField.setField(-1);
        nonZeroIntField.validObservable()
                .test()
                .assertValue(false);
    }

    @Test
    public void validObservable_true() {
        nonZeroIntField.setField(10);
        nonZeroIntField.validObservable()
                .test()
                .assertValue(true);
    }

    @Test
    public void setObservable_same_value() {
        final int value = 10;
        nonZeroIntField.setField(value);
        nonZeroIntField.setObservable()
                .test()
                .assertValue(value);
    }

    @Test
    public void validObservable_mandatory_true() {
        final NonZeroIntField nonZeroIntField = new NonZeroIntField("dasfa", 1,true);
        nonZeroIntField.validObservable()
                .test()
                .assertValue(true);
    }

    @Test
    public void validObservable_mandatory_false() {
        final NonZeroIntField nonZeroIntField = new NonZeroIntField("dasfa", true);
        nonZeroIntField.validObservable()
                .test()
                .assertValue(false);
    }
}