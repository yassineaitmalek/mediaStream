package com.test.repositories.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractDSLRepository<T, U> extends JpaRepository<T, U>, JpaSpecificationExecutor<T> {

}