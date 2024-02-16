package com.test.config;

import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.test.utility.FileUtility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UploadFolder {

  @Value("${upload.folder.path:#{null}}")
  private String folderPath;

  public String getUploadFolderPath() {
    if (folderPath == null) {
      return Paths.get(System.getProperty("user.dir"), "upload").normalize().toAbsolutePath().toString();
    }
    return Paths.get(folderPath).toAbsolutePath().normalize().toString();
  }

  @PostConstruct
  public void createUploadFolder() throws Exception {
    log.info("upload folder used is {} ", getUploadFolderPath());
    FileUtility.createFolder(Paths.get(getUploadFolderPath()));

  }

}
