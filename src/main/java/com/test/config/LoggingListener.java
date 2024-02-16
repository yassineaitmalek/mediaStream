package com.test.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;

public class LoggingListener implements ApplicationListener<ApplicationEvent>, Ordered {

  @Override
  public int getOrder() {
    return LoggingApplicationListener.DEFAULT_ORDER - 1;
  }

  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    if (event instanceof ApplicationEnvironmentPreparedEvent) {
      ConfigurableEnvironment environment = ((ApplicationEnvironmentPreparedEvent) event).getEnvironment();

      StreamSupport.stream(environment.getPropertySources().spliterator(), false)
          .filter(EnumerablePropertySource.class::isInstance)
          .map(ps -> ((EnumerablePropertySource<?>) ps))
          .map(ps -> Arrays.asList(ps.getPropertyNames()))
          .flatMap(List::stream)
          .forEach(propName -> System.setProperty(propName, environment.getProperty(propName)));
    }
  }

}
