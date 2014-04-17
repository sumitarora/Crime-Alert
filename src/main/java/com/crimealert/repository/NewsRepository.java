package com.crimealert.repository;

import org.springframework.data.repository.CrudRepository;

import com.crimealert.model.News;

public interface NewsRepository extends CrudRepository<News, Integer>{
	
	
}