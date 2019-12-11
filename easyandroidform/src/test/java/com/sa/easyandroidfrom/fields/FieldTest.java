package com.sa.easyandroidfrom.fields;

import com.sa.easyandroidfrom.ObservablesCall;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

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
    void validate_called_once() throws Exception {
        spyField.setField(Mockito.anyString());
        Mockito.verify(spyField, Mockito.times(1)).validate();
    }

    @Test
    void null_filed_with_mandatory_field() {
        spyField.setIsMandatory(true);
        ObservablesCall.isFieldValid(spyField);
    }

    @Test
    void observable_called() {
        ObservablesCall.observable(spyField, 1);
    }

    @Test
    void setObservable_called() {
        spyField.setField("Na");
        ObservablesCall.setObservable(spyField,1);
    }

    @Test
    void setObservable_not_called() {
        ObservablesCall.setObservable(spyField,0);
    }

    @Test
    void validObservable_true() {
        spyField.setField("Na");
        ObservablesCall.validObservable(spyField, true);
    }

    @Test
    void validObservable_false() {
        spyField.setIsMandatory(true);
        ObservablesCall.validObservable(spyField, false);
    }

    @Test
    void invalidObservable_called() {
        spyField.setIsMandatory(true);
        ObservablesCall.invalidObservable(spyField, 1);
    }

    @Test
    void invalidObservable_not_called() {
        ObservablesCall.invalidObservable(spyField, 0);
    }

    @Test
    void nonEmptyInvalidObservable_not_called() {
        spyField.setIsMandatory(true);
        ObservablesCall.nonEmptyInvalidObservable(spyField, 0);
    }

    @Test
    void notEmptyValidObservable_called() {
        spyField.setField("dsafd");
        ObservablesCall.notEmptyValidObservable(spyField, 1);
    }

    @Test
    void notEmptyValidObservable_not_called() {
        spyField.setIsMandatory(true);
        ObservablesCall.notEmptyValidObservable(spyField, 0);
    }

    @Test
    void fieldUnsetObservable_called(){
        ObservablesCall.fieldUnsetObservable(spyField, 1);
    }

    @Test
    void fieldUnsetObservable_not_called(){
        spyField.setField("adsff");
        ObservablesCall.fieldUnsetObservable(spyField, 0);
    }

    @Test
    void networkError_called(){
        spyField.networkErrorPublish("error");
        ObservablesCall.networkError(spyField, 1);
    }
    @Test
    void networkError_not_called(){
        spyField.networkErrorPublish("error");
        spyField.setField("dsadfadf");
        ObservablesCall.networkError(spyField, 0);
    }

    @Test
    void isModifiedObservable_true(){
        spyField.setField("asdfadf");
        ObservablesCall.isModifiedObservable(spyField, true);
    }

    @Test
    void isModifiedObservable_false(){
        ObservablesCall.isModifiedObservable(spyField, false);
    }

    @Test
    void errorState_true(){
        ObservablesCall.errorState(spyField,true);
    }

    @Test
    void errorState_false(){
        spyField.setIsMandatory(true);
        ObservablesCall.errorState(spyField,false);
    }
}