package com.sa.easyandroidfrom.fields;


import org.junit.jupiter.api.Test;

public class NonZeroIntFieldTest {

    @Test
    public void test_validate_throws() {
        final NonZeroIntField nonZeroIntField = new NonZeroIntField("dasfa", 0);
        nonZeroIntField.validObservable()
                .test()
                .assertValue(false);
    }

    public void test_validate() {
        final NonZeroIntField nonZeroIntField = new NonZeroIntField("dasfa", 3);
    }
}