package com.sa.easyandroidfrom.fields;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class FileField extends BaseField<Uri> {

  public FileField(@NonNull String fieldId) {
    super(fieldId);
  }

  public FileField(@NonNull String fieldId, boolean isMandatory) {
    super(fieldId, isMandatory);
  }

  public FileField(@NonNull String fieldId, @Nullable Uri ogField) {
    super(fieldId, ogField);
  }

  public FileField(@NonNull String fieldId, @Nullable Uri ogField, boolean isMandatory) {
    super(fieldId, ogField, isMandatory);
  }

  @Override
  public void validate() throws Exception {

  }

  @Override
  protected boolean isFieldValueModified(@NonNull Uri field, @NonNull Uri ogField) {
    return field.toString().compareTo(ogField.toString()) != 0;
  }
}
