package com.sa.easyandroidform.fields;

import android.net.Uri;

import org.jetbrains.annotations.NotNull;


public class FileFieldTest extends AllValueValidFieldTest<Uri> {

    private static final String FIELD_NAME = "random";
    private static final Uri VALUE = Uri.parse("http://google.com");
    private static final Uri NEW_VALUE = Uri.parse("http://reddit.com");

    public FileFieldTest() {
        super(new FileField(FIELD_NAME), new FileField(FIELD_NAME, true), new FileField(FIELD_NAME, VALUE, true), new FileField(FIELD_NAME, VALUE));
    }

    @NotNull
    @Override
    protected String fieldName() {
        return FIELD_NAME;
    }

    @NotNull
    @Override
    protected Uri getNewValidFieldValue() {
        return NEW_VALUE;
    }
}