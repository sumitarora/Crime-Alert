package com.crimealert.repository;

import org.springframework.data.repository.CrudRepository;

import com.crimealert.model.Upload;

public interface UploadRepository extends CrudRepository<Upload, Long> {

}

