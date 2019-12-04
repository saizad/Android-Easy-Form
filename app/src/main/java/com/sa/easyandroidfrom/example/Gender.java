package com.sa.easyandroidfrom.example;


import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.List;

import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@StringDef({ Gender.MALE, Gender.FEMALE, Gender.OTHER })
public @interface Gender {

  String MALE = "Male";
  String FEMALE = "Female";
  String OTHER = "Other";

  class ToList {
    public static List<String> getList() {
      ArrayList<String> genders = new ArrayList<>();
      genders.add(Gender.MALE);
      genders.add(Gender.FEMALE);
      genders.add(Gender.OTHER);
      return genders;
    }
  }
}
