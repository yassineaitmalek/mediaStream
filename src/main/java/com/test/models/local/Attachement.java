package com.test.models.local;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.test.constants.FileExtension;
import com.test.constants.FileType;
import com.test.models.config.BaseEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Attachement extends BaseEntity {

  @Schema(description = "Extention of the file")
  private String ext;

  @Schema(description = "Extention type of the file")
  private FileExtension extension;

  @Schema(description = "Original name of the file")
  private String originalFileName;

  @Schema(description = "Path where the file is stored")
  private String path;

  @Schema(description = "Size of the file")
  private Long fileSize;

  @Schema(description = "Type of the file")
  private FileType fileType;

}
