package com.test.utility;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;

import com.test.exception.config.ApiException;

public final class StringTemplate {

  private String url;

  private Map<String, String> params = new HashMap<>();

  public StringTemplate(String url) {
    this.url = url;
  }

  public static StringTemplate template(String urlTemplate) {
    if (urlTemplate == null) {
      throw new ApiException("NULL template");
    }
    return new StringTemplate(urlTemplate);
  }

  public StringTemplate addParameter(String pramName, Object parmValue) {
    if (parmValue == null) {
      throw new ApiException("NULL value parameter");
    }
    if (pramName == null) {
      throw new ApiException("NULL name parameter");
    }
    params.put(pramName, parmValue.toString());
    return this;

  }

  public String build() {
    return new StringSubstitutor(params).replace(url);

  }

}
