package com.test.controllers.config;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiDataResponse<T> {

  private String status;

  private Integer httpStatus;

  @Builder.Default
  private ZonedDateTime timestamp = ZonedDateTime.now(ZoneId.of("Z"));

  private T data;

}
