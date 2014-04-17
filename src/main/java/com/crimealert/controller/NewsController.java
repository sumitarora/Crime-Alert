package com.crimealert.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.crimealert.model.Comment;
import com.crimealert.model.Email;
import com.crimealert.model.Feedback;
import com.crimealert.model.News;
import com.crimealert.model.User;
import com.crimealert.service.NewsService;

@Controller
@RequestMapping(value="/news")
@Slf4j
public class NewsController extends BaseController {
	
	@Autowired
	NewsService newsService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView list(){
		log.debug("inside list news");
		List<News> news = newsService.getAllNews();
		ModelAndView mav=new ModelAndView("news/list-all");
		mav.addObject("news", news);
		return setSelectedMenu(mav);
	}
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public ModelAndView create(){
		log.debug("inside create news");
		ModelAndView mav=new ModelAndView("news/create");
		return setSelectedMenu(mav);
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView save(@ModelAttribute News news){
		log.debug("inside save news");
		news.setNewsDate(new java.sql.Date(new java.util.Date().getTime()));
		newsService.saveNews(news);
		return new ModelAndView(new RedirectView(""));
	}
	
	@RequestMapping(value="/view/{id}",method=RequestMethod.GET)
	public ModelAndView view(@PathVariable int id){
		log.debug("inside view news");
		News news = newsService.getNewsById(id);
		ModelAndView mav=new ModelAndView("news/view");
		mav.addObject("news", news);
		return setSelectedMenu(mav);		
	}
	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public ModelAndView editCrime(@PathVariable int id){
		log.debug("inside edit news");
		News news = newsService.getNewsById(id);
		ModelAndView mav=new ModelAndView("news/create");
		mav.addObject("news", news);
		return setSelectedMenu(mav);
	}	
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id){
		log.debug("inside news delete: {}", id);
		newsService.deleteNews(id);
		return new ModelAndView(new RedirectView(DOMAIN_PATH + "/news"));
	}	
	
}
