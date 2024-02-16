package com.test.validation.impl;

import java.util.Objects;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.test.dto.FileDTO;
import com.test.validation.FileDTOValidator;

public class FileDTOValidatorImpl implements ConstraintValidator<FileDTOValidator, FileDTO> {

	@Override
	public boolean isValid(FileDTO fileDTO, ConstraintValidatorContext context) {

		return Stream.of(checkNull(fileDTO), checkFile(fileDTO))
				.noneMatch(Boolean.TRUE::equals);

	}

	private boolean checkNull(FileDTO fileDTO) {
		return Objects.nonNull(fileDTO);
	}

	private boolean checkFile(FileDTO fileDTO) {
		return Objects.nonNull(fileDTO) && Objects.nonNull(fileDTO.getFile());
	}

}