package com.test.repositories.local;

import org.springframework.stereotype.Repository;

import com.test.models.local.Attachement;
import com.test.repositories.config.AbstractRepository;

@Repository
public interface AttachementRepository extends AbstractRepository<Attachement, String> {

}
