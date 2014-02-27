package com.crimealert.repository;

import org.springframework.data.repository.CrudRepository;

import com.crimealert.model.Complaint;

public interface ComplaintRepository extends CrudRepository<Complaint, Integer> {

}

