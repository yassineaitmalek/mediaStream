package com.test.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtility {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmssddMMyyyy");

  private static final ZoneId ZONE_AFRICA_CASABLANCA = ZoneId.of("Africa/Casablanca");

  public static SimpleDateFormat getSimpleDateFormat() {
    return new SimpleDateFormat("yyyy-MM-dd");
  }

  public static LocalDate nowDate() {

    return LocalDate.now(ZONE_AFRICA_CASABLANCA);

  }

  public static LocalTime nowTime() {

    return LocalTime.now(ZONE_AFRICA_CASABLANCA);

  }

  public static LocalDateTime nowDateTime() {

    return LocalDateTime.now(ZONE_AFRICA_CASABLANCA);

  }

  public static String nowDateTimeFormatted() {

    return nowDateTime().format(DATE_TIME_FORMATTER);

  }

  public static LocalDate convertDateToLocalDate(Date date) {

    return Optional.ofNullable(date)
        .map(e -> LocalDateTime.ofInstant(e.toInstant(),
            ZONE_AFRICA_CASABLANCA).toLocalDate())
        .orElse(null);

  }

  public static Date convertLocalDateToDate(LocalDate localDate) {

    return Optional.ofNullable(localDate)
        .map(e -> Date.from(e.atStartOfDay(
            ZONE_AFRICA_CASABLANCA).toInstant()))
        .orElse(null);

  }

  public static Long calculatesAbsDaysDifference(LocalDate date1, LocalDate date2) {
    return Math.abs(ChronoUnit.DAYS.between(date1, date2));
  }

  public static ZonedDateTime getZoneDateTime(LocalDateTime dateTime, ZoneId zoneId) {
    return ZonedDateTime.of(dateTime, zoneId);
  }

  public static ZonedDateTime getZoneDateTimeByLocalDateTime(LocalDateTime dateTime) {
    return ZonedDateTime.of(dateTime, ZONE_AFRICA_CASABLANCA);
  }

  public static ZonedDateTime getZoneDateTimeByLocalDate(LocalDate date) {
    return date.atStartOfDay(ZONE_AFRICA_CASABLANCA);
  }

  public static ZonedDateTime closeToMinutes(ZonedDateTime zonedDateTime, long minutes) {
    if (calculateMinutesDifference(zonedDateTime, nowZonedDateTime()) < minutes) {
      return zonedDateTime;
    }
    return zonedDateTimeMinusMinutes(zonedDateTime, minutes);
  }

  public static long calculateMinutesDifference(ZonedDateTime zonedDateTime1, ZonedDateTime zonedDateTime2) {
    return ChronoUnit.MINUTES.between(zonedDateTime1, zonedDateTime2);
  }

  public static ZonedDateTime nowZonedDateTime() {
    return ZonedDateTime.now(ZONE_AFRICA_CASABLANCA);
  }

  public static LocalDate getLastDayOfMonth(LocalDate date) {
    return date.withDayOfMonth(date.getMonth().length(date.isLeapYear()));
  }

  public static LocalDate getFirstDateOfYear(int year) {
    return LocalDate.of(year, 1, 1);
  }

  public static LocalDate getLastDateOfYear(int year) {
    return LocalDate.of(year, 12, 31);
  }

  public static LocalDate getFirstDayOfMonth(LocalDate date) {
    return date.withDayOfMonth(1);
  }

  public static LocalDate recentDate(LocalDate a, LocalDate b) {
    return (a.isBefore(b)) ? b : a;
  }

  public static LocalDate recentDate(List<LocalDate> dates, LocalDate defaultDate) {
    return dates.stream().filter(Objects::nonNull).reduce(DateUtility::recentDate).orElse(defaultDate);
  }

  public static LocalDate oldestDate(LocalDate a, LocalDate b) {
    return (a.isBefore(b)) ? a : b;
  }

  public static LocalDate oldestDate(List<LocalDate> dates, LocalDate defaultDate) {
    return dates.stream().filter(Objects::nonNull).reduce(DateUtility::oldestDate).orElse(defaultDate);
  }

  public static boolean isBeforeOrEqual(LocalDate a, LocalDate b) {
    return a.isBefore(b) || a.isEqual(b);
  }

  public static LocalDateTime dateTimeMinusMinutes(LocalDateTime dateTime, long minutes) {
    return dateTime.minus(minutes, ChronoUnit.MINUTES);
  }

  public static LocalDateTime dateTimeMinusSeconds(LocalDateTime dateTime, long seconds) {
    return dateTime.minus(seconds, ChronoUnit.SECONDS);
  }

  public static LocalDateTime dateTimeMinusDays(LocalDateTime dateTime, long days) {
    return dateTime.minus(days, ChronoUnit.DAYS);
  }

  public static LocalDateTime dateTimeMinusHours(LocalDateTime dateTime, long hours) {
    return dateTime.minus(hours, ChronoUnit.HOURS);
  }

  public static LocalDateTime dateTimeMinusWeeks(LocalDateTime dateTime, long weeks) {
    return dateTime.minus(weeks, ChronoUnit.WEEKS);
  }

  public static LocalDateTime dateTimeMinusMonths(LocalDateTime dateTime, long months) {
    return dateTime.minus(months, ChronoUnit.MONTHS);
  }

  public static LocalDateTime dateTimeMinusYears(LocalDateTime dateTime, long years) {
    return dateTime.minus(years, ChronoUnit.YEARS);
  }

  public static LocalDateTime dateTimePlusMinutes(LocalDateTime dateTime, long minutes) {
    return dateTime.plus(minutes, ChronoUnit.MINUTES);
  }

  public static LocalDateTime dateTimePlusSeconds(LocalDateTime dateTime, long seconds) {
    return dateTime.plus(seconds, ChronoUnit.SECONDS);
  }

  public static LocalDateTime dateTimePlusDays(LocalDateTime dateTime, long days) {
    return dateTime.plus(days, ChronoUnit.DAYS);
  }

  public static LocalDateTime dateTimePlusHours(LocalDateTime dateTime, long hours) {
    return dateTime.plus(hours, ChronoUnit.HOURS);
  }

  public static LocalDateTime dateTimePlusWeeks(LocalDateTime dateTime, long weeks) {
    return dateTime.plus(weeks, ChronoUnit.WEEKS);
  }

  public static LocalDateTime dateTimePlusMonths(LocalDateTime dateTime, long months) {
    return dateTime.plus(months, ChronoUnit.MONTHS);
  }

  public static LocalDateTime dateTimePlusYears(LocalDateTime dateTime, long years) {
    return dateTime.plus(years, ChronoUnit.YEARS);
  }

  public static ZonedDateTime zonedDateTimeMinusMinutes(ZonedDateTime zonedDateTime, long minutes) {
    return zonedDateTime.minus(minutes, ChronoUnit.MINUTES);
  }

  public static ZonedDateTime zonedDateTimeMinusSeconds(ZonedDateTime zonedDateTime, long seconds) {
    return zonedDateTime.minus(seconds, ChronoUnit.SECONDS);
  }

  public static ZonedDateTime zonedDateTimeMinusDays(ZonedDateTime zonedDateTime, long days) {
    return zonedDateTime.minus(days, ChronoUnit.DAYS);
  }

  public static ZonedDateTime zonedDateTimeMinusHours(ZonedDateTime zonedDateTime, long hours) {
    return zonedDateTime.minus(hours, ChronoUnit.HOURS);
  }

  public static ZonedDateTime zonedDateTimeMinusWeeks(ZonedDateTime zonedDateTime, long weeks) {
    return zonedDateTime.minus(weeks, ChronoUnit.WEEKS);
  }

  public static ZonedDateTime zonedDateTimeMinusMonths(ZonedDateTime zonedDateTime, long months) {
    return zonedDateTime.minus(months, ChronoUnit.MONTHS);
  }

  public static ZonedDateTime zonedDateTimeMinusYears(ZonedDateTime zonedDateTime, long years) {
    return zonedDateTime.minus(years, ChronoUnit.YEARS);
  }

  public static ZonedDateTime zonedDateTimePlusMinutes(ZonedDateTime zonedDateTime, long minutes) {
    return zonedDateTime.plus(minutes, ChronoUnit.MINUTES);
  }

  public static ZonedDateTime zonedDateTimePlusSeconds(ZonedDateTime zonedDateTime, long seconds) {
    return zonedDateTime.plus(seconds, ChronoUnit.SECONDS);
  }

  public static ZonedDateTime zonedDateTimePlusDays(ZonedDateTime zonedDateTime, long days) {
    return zonedDateTime.plus(days, ChronoUnit.DAYS);
  }

  public static ZonedDateTime zonedDateTimePlusHours(ZonedDateTime zonedDateTime, long hours) {
    return zonedDateTime.plus(hours, ChronoUnit.HOURS);
  }

  public static ZonedDateTime zonedDateTimePlusWeeks(ZonedDateTime zonedDateTime, long weeks) {
    return zonedDateTime.plus(weeks, ChronoUnit.WEEKS);
  }

  public static ZonedDateTime zonedDateTimePlusMonths(ZonedDateTime zonedDateTime, long months) {
    return zonedDateTime.plus(months, ChronoUnit.MONTHS);
  }

  public static ZonedDateTime zonedDateTimePlusYears(ZonedDateTime zonedDateTime, long years) {
    return zonedDateTime.plus(years, ChronoUnit.YEARS);
  }

  public static LocalDate dateMinusDays(LocalDate date, long days) {
    return date.minus(days, ChronoUnit.DAYS);
  }

  public static LocalDate dateMinusWeeks(LocalDate date, long weeks) {
    return date.minus(weeks, ChronoUnit.WEEKS);
  }

  public static LocalDate dateMinusMonths(LocalDate date, long months) {
    return date.minus(months, ChronoUnit.MONTHS);
  }

  public static LocalDate dateMinusYears(LocalDate date, long years) {
    return date.minus(years, ChronoUnit.YEARS);
  }

  public static LocalDate datePlusDays(LocalDate date, long days) {
    return date.plus(days, ChronoUnit.DAYS);
  }

  public static LocalDate datePlusWeeks(LocalDate date, long weeks) {
    return date.plus(weeks, ChronoUnit.WEEKS);
  }

  public static LocalDate datePlusMonths(LocalDate date, long months) {
    return date.plus(months, ChronoUnit.MONTHS);
  }

  public static LocalDate datePlusYears(LocalDate date, long years) {
    return date.plus(years, ChronoUnit.YEARS);
  }

  public static LocalTime timeMinusMinutes(LocalTime time, long minutes) {
    return time.minus(minutes, ChronoUnit.MINUTES);
  }

  public static LocalTime timeMinusSeconds(LocalTime time, long seconds) {
    return time.minus(seconds, ChronoUnit.SECONDS);
  }

  public static LocalTime timeMinusHours(LocalTime time, long hours) {
    return time.minus(hours, ChronoUnit.HOURS);
  }

  public static LocalTime timePlusMinutes(LocalTime time, long minutes) {
    return time.plus(minutes, ChronoUnit.MINUTES);
  }

  public static LocalTime timePlusSeconds(LocalTime time, long seconds) {
    return time.plus(seconds, ChronoUnit.SECONDS);
  }

  public static LocalTime timePlusHours(LocalTime time, long hours) {
    return time.plus(hours, ChronoUnit.HOURS);
  }

}
