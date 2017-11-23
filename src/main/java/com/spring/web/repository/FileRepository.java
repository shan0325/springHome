package com.spring.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.web.domain.File;

@Repository("fileRepository")
public interface FileRepository extends JpaRepository<File, Long> {

}
