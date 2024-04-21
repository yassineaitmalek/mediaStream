package com.test.controllers.config;

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
public class ApiPartialInput {

  private byte[] bytes;

  private long start;

  private long end;

  private long size;

  private String content;

  private String ext;

  private long lenght;
}
