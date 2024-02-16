package com.test.utility;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import io.vavr.control.Try;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CheckUtility {

  public static final DecimalFormat df = getDecimalFormat();

  public static final DecimalFormat df_xls = getDecimalFormatXLS();

  public static DecimalFormat getDecimalFormat() {
    DecimalFormat df = new DecimalFormat("0.00");
    df.setRoundingMode(RoundingMode.HALF_UP);
    return df;

  }

  public static DecimalFormat getDecimalFormatXLS() {
    DecimalFormat df = new DecimalFormat("#.##");
    df.setRoundingMode(RoundingMode.HALF_UP);
    return df;

  }

  public String checkString(String s) {
    return Optional.ofNullable(s)
        .map(String::trim)
        .filter(e -> !e.equals(""))
        .orElse(null);

  }

  public String formatDouble(Double d) {

    return Optional.ofNullable(d)
        .map(df::format)
        .orElse(null);

  }

  public String formatDoubleXls(Double d) {

    return Optional.ofNullable(d)
        .map(df_xls::format)
        .orElse(null);

  }

  public Long checkLong(String s) {
    return Optional.ofNullable(checkDouble(s))
        .map(Double::longValue)
        .orElse(null);

  }

  public Integer checkInteger(String s) {

    return Optional.ofNullable(checkDouble(s))
        .map(Double::intValue)
        .orElse(null);

  }

  public Double checkDouble(String s) {

    return Try.of(() -> Optional.ofNullable(checkString(s)))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(Double::parseDouble)
        .getOrNull();
  }

  public LocalDate checkDate(String s) {

    return Try.of(() -> checkString(s))
        .filter(Objects::nonNull)
        .map(LocalDate::parse)
        .getOrNull();

  }

  public String objectToString(Object obj) {
    return Optional.ofNullable(obj).map(Object::toString).orElse("");
  }

  public <T> String parseDefaultValue(T obj, T defaultValue) {
    if (obj == null) {
      return defaultValue.toString();
    }

    return obj.toString();
  }

}
