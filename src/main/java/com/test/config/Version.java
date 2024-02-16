package com.test.config;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Version {

  @PostConstruct
  public void javaVersion() {

    log.info("JAVA version used in this service is {}", System.getProperty("java.version"));
  }

}
