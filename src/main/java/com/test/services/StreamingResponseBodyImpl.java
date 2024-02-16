package com.test.services;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StreamingResponseBodyImpl implements StreamingResponseBody {

  private final InputStream inputStream;

  @Override
  public void writeTo(OutputStream outputStream) throws IOException {

    byte[] buffer = new byte[1024];
    int bytesRead;
    while ((bytesRead = inputStream.read(buffer)) != -1) {
      outputStream.write(buffer, 0, bytesRead);
    }

  }

}
