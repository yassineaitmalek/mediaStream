package com.test.repositories.local;

import org.springframework.stereotype.Repository;

import com.test.models.local.AFile;
import com.test.repositories.config.AbstractRepository;

@Repository
public interface AFileRepository extends AbstractRepository<AFile, String> {

}
