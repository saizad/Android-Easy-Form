package com.sa.easyandroidfrom.fields;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import io.reactivex.observers.TestObserver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FieldTest {

    @Spy
    final Field<Object> spyField = Mockito.spy(new Field<>("random"));

    @BeforeAll
    static void setUp() {
        MockitoAnnotations.initMocks(new FieldTest());
    }

    @Test
    public void field_value_not_changed__with_og_field() {
        final Field<Object> spyField = Mockito.spy(new Field<>("random", "any"));
        assertFalse(spyField.hasValueChanged());
    }

    @Test
    public void field_hasValueChanged() {
        spyField.setField(Mockito.anyInt());
        assertTrue(spyField.hasValueChanged());
    }

    @Test
    public void field_throw_validation() throws Exception {
        Mockito.doThrow(new Exception("Testing is file valid by throwing validate"))
                .when(spyField).validate();
        spyField.setField(Mockito.anyString());
        assertFalse(spyField.isFieldValid());
    }

    @Test
    public void field_is_valid() {
        spyField.setField(Mockito.anyString());
        assertTrue(spyField.isFieldValid());
    }

    @Test
    void observable_called() {
        spyField.observable()
                .test()
                .assertValueCount(1);
    }

    @Test
    void validate_called_once() throws Exception {
        spyField.setField(Mockito.anyString());
        Mockito.verify(spyField, Mockito.times(1)).validate();
    }

    @Test
    void observable_called_after_mandatory() {


        spyField.setField(true);

        final TestObserver<?> objectTestObserver = spyField.observable()
                .doOnNext(System.out::println)
                .doFinally(() -> System.out.println("Finally"))
                .doOnComplete(() -> System.out.println("On Completed"))
                .toList()
                .doOnError(System.out::println)
                .doOnSuccess(__ -> System.out.println(__.size()))
                .test()
                .assertNoValues();
        spyField.setField(true);

        objectTestObserver.dispose();
    }
}