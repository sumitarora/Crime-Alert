package com.crimealert.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimealert.model.Crime;
import com.crimealert.model.News;
import com.crimealert.repository.NewsRepository;
import com.crimealert.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	NewsRepository newsRepository;
	
	@PersistenceContext
	EntityManager em;	
	
	@Override
	public News saveNews(News news) {
		return newsRepository.save(news);
	}

	@Override
	public void deleteNews(int id) {
		newsRepository.delete(id);
	}

	@Override
	public List<News> getAllNews() {
		return (List<News>) newsRepository.findAll();
	}

	@Override
	public News getNewsById(int id) {
		return newsRepository.findOne(id);
	}

	@Override
  	public List<News> findTopNews() {
		String query = "select * from tbl_news order by news_id desc limit 4";
		final Query q = em.createNativeQuery(query, News.class);
		return q.getResultList();		
	}
}
