package com.sa.easyandroidfrom.fields;

import androidx.core.util.Pair;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import io.reactivex.observers.TestObserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FieldTest {

    private static final String FIELD_NAME = "random";
    
    @Spy
    final Field<Object> spyField = Mockito.spy(new Field<>(FIELD_NAME));

    @BeforeAll
    static void setUp() {
        MockitoAnnotations.initMocks(new FieldTest());
    }

    @Test
    public void fieldid_same(){
        assertEquals(FIELD_NAME, spyField.getFieldId());
    }

    @Test
    public void isMandatory_false(){
        assertFalse(spyField.isMandatory());
    }

    @Test
    public void isMandatory_true(){
        final Field<Object> spyField = Mockito.spy(new Field<>(FIELD_NAME, true));
        assertTrue(spyField.isMandatory());
    }

    @Test
    public void ogfield_same(){
        final String any = "any";
        final Field<Object> spyField = Mockito.spy(new Field<>(FIELD_NAME, any, true));
        assertEquals(any, spyField.getOgField());
    }

    @Test
    public void isModified_false(){
        assertFalse(spyField.isModified());
    }

    @Test
    public void isModified_true(){
        spyField.setField("dsafasdf");
        assertTrue(spyField.isModified());
    }

    @Test
    public void field_value_not_changed__with_og_field() {
        final Field<Object> spyField = Mockito.spy(new Field<>(FIELD_NAME, "any"));
        assertTrue(spyField.hasValueChanged());
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
        final TestObserver<Object> objectTestObserver = spyField.observable()
                .test()
                .assertValueCount(1);
        objectTestObserver.dispose();
    }

    @Test
    void validate_called_once() throws Exception {
        spyField.setField(Mockito.anyString());
        Mockito.verify(spyField, Mockito.times(1)).validate();
    }

    @Test
    void null_filed_with_mandatory_field() {
        spyField.setIsMandatory(true);
        assertFalse(spyField.isFieldValid());
    }

    @Test
    void setObservable_called() {
        spyField.setField("Na");
        spyField.setObservable()
                .test()
                .assertValueCount(1);
    }

    @Test
    void setObservable_not_called() {
        final TestObserver<Object> objectTestObserver = spyField.setObservable()
                .test()
                .assertEmpty();
        objectTestObserver.dispose();
    }

    @Test
    void validObservable_true() {
        spyField.setField("Na");
        spyField.validObservable()
                .test()
                .assertValue(true);
    }

    @Test
    void validObservable_false() {
        spyField.setIsMandatory(true);
        spyField.validObservable()
                .test()
                .assertValue(false);
    }

    @Test
    void invalidObservable_called() {
        spyField.setIsMandatory(true);
        spyField.invalidObservable()
                .test()
                .assertValueCount(1);
    }

    @Test
    void invalidObservable_not_called() {
        final TestObserver<Pair<String, Object>> testObserver = spyField.invalidObservable()
                .test()
                .assertEmpty();
        testObserver.dispose();
    }

    @Test
    void nonEmptyInvalidObservable_not_called() {
        spyField.setIsMandatory(true);
        final TestObserver<Pair<String, Object>> testObserver =
                spyField.nonEmptyInvalidObservable()
                .test()
                .assertEmpty();
        testObserver.dispose();
    }

    @Test
    void notEmptyValidObservable_called() {
        spyField.setField("dsafd");
        final TestObserver<?> testObserver =
                spyField.notEmptyValidObservable()
                        .test()
                        .assertValueCount(1);
        testObserver.dispose();
    }

    @Test
    void notEmptyValidObservable_not_called() {
        spyField.setIsMandatory(true);
        final TestObserver<?> testObserver =
                spyField.notEmptyValidObservable()
                        .test()
                        .assertEmpty();
        testObserver.dispose();
    }

    @Test
    void fieldUnsetObservable_called(){
        final TestObserver<Object> testObserver = spyField.fieldUnsetObservable()
                .test()
                .assertValueCount(1);
        testObserver.dispose();
    }

    @Test
    void fieldUnsetObservable_not_called(){
        spyField.setField("adsff");
        final TestObserver<Object> testObserver = spyField.fieldUnsetObservable()
                .test()
                .assertEmpty();

        testObserver.dispose();
    }
    @Test
    void networkError_called(){
        spyField.networkErrorPublish("alsfdkjasdfl");
        final TestObserver<?> testObserver = spyField.networkError()
                .test()
                .assertValueCount(1);

        testObserver.dispose();
    }
    @Test
    void networkError_not_called(){
        spyField.networkErrorPublish("alsfdkjasdfl");
        spyField.setField("dsadfadf");
        final TestObserver<?> testObserver = spyField.networkError()
                .test()
                .assertEmpty();

        testObserver.dispose();
    }

    @Test
    void isModifiedObservable_true(){
        spyField.setField("asdfadf");
        final TestObserver<Boolean> testObserver = spyField.modified()
                .test()
                .assertValue(true);
        testObserver.dispose();
    }

    @Test
    void isModifiedObservable_false(){
        final TestObserver<Boolean> testObserver = spyField.modified()
                .test()
                .assertValue(false);
        testObserver.dispose();
    }

    @Test
    void errorState_true(){
        final TestObserver<?> testObserver = spyField.errorState()
                .map(__ -> __.first)
                .test()
                .assertValue(true);
        testObserver.dispose();
    }

    @Test
    void errorState_false(){
        spyField.setIsMandatory(true);
        final TestObserver<?> testObserver = spyField.errorState()
                .map(__ -> __.first)
                .test()
                .assertValue(false);
        testObserver.dispose();
    }
}