package com.crimealert.service;

import java.util.List;

import com.crimealert.model.News;
public interface NewsService {
	
	News saveNews(News news);
	
	void deleteNews(int id);
	
	List<News> getAllNews();
	
	News getNewsById(int id);
	
	List<News> findTopNews();

}
