package com.crimealert.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.crimealert.model.Complaint;
import com.crimealert.model.Crime;
import com.crimealert.model.News;
import com.crimealert.model.User;
import com.crimealert.service.CommentService;
import com.crimealert.service.ComplaintService;
import com.crimealert.service.CrimeService;
import com.crimealert.service.NewsService;
import com.crimealert.service.ReportService;
import com.crimealert.util.UploadDataUtils;

@Controller
@Slf4j
public class NavigationController extends BaseController {

	@Autowired
	CrimeService crimeService;
	
	@Autowired
	ReportService reportService;
	
	@Autowired
	ComplaintService complaintService;
	
	@Autowired
	CommentService commentService;

	@Autowired
	NewsService newsService;
	
	@Autowired
	UploadDataUtils uploadData;
	
	@RequestMapping(value={"/", "index"}, method=RequestMethod.GET)
	public ModelAndView index() {
		final ModelAndView mav = new ModelAndView("index");
		mav.addObject("hideMenu", true);
		return setSelectedMenu(mav);

	}
	
	@RequestMapping(value={"/dashboard"}, method=RequestMethod.GET)
	public ModelAndView dashboard() {
		final List<Crime> crimes = crimeService.findTopCrimes();
		final List<Complaint> complaints = complaintService.findTopComplaints();
		final List<News> news = newsService.findTopNews();
		
		final ModelAndView mav = new ModelAndView("dashboard");
		mav.addObject("topCrimes", crimes);
		log.debug("total crimes: {}", crimes.size());
		
		mav.addObject("topComplaints", complaints);
		log.debug("total complaints: {}", complaints.size());
		
		mav.addObject("topNews", news);
		log.debug("total news: {}", news.size());
		
		return setSelectedMenu(mav);

	}	

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView home() {
		final User user = getLoggedInUser();
		final List<Crime> crimes = crimeService.getAllCrimes(user);
		final List<Complaint> complaints = complaintService.getAllComplaints(user);
		
		final ModelAndView mav = new ModelAndView("home");
		mav.addObject("crimesCount", crimes.size());
		log.debug("total crimes: {}", crimes.size());
		
		mav.addObject("complaintsCount", complaints.size());
		log.debug("total complaints: {}", complaints.size());
		
		mav.addObject("commentsCount", commentService.getAllUserComments(getLoggedInUser()).size());
		mav.addObject("miscCount", 0);
		
		return setSelectedMenu(mav);
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ModelAndView search() {
		final String type = request.getParameter("type");
		final String criteria = request.getParameter("criteria");
		final String by = request.getParameter("by");
		final String useopendata = request.getParameter("useopendata");
		Boolean opendata = true;
		if(useopendata != null && useopendata.equals("y")) {
			opendata = false;
		}
		
		log.debug("searching for {} - {} - {}", type, criteria, opendata);
		
		if(type.equals("cr")) {
			final ModelAndView mav = new ModelAndView("crime/crime-search");
			if(by.equals("t")) {
				final List<Crime> crimes = crimeService.findByTitleOrDescription(criteria, criteria, opendata);
				mav.addObject("crimes", crimes);
			} else {
				final List<Crime> crimes = crimeService.findByAddress(criteria, opendata);
				mav.addObject("crimes", crimes);				
			}
			mav.addObject("summary", reportService.getLocationSummary(true));
			return setSelectedMenu(mav);			
		} else {
			final ModelAndView mav = new ModelAndView("complaint/complaint-search");
			if(by.equals("t")) {
				final List<Complaint> complaints = complaintService.findByTitleOrDescription(criteria, criteria, opendata);
				mav.addObject("complaints", complaints);
			} else {
				final List<Complaint> complaints = complaintService.findByAddress(criteria, opendata);
				mav.addObject("complaints", complaints);
			}						
			return setSelectedMenu(mav);
		}
	}

	@RequestMapping(value="/generate", method=RequestMethod.GET)
	public String generateData() {
		try {
			uploadData.uploadData();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//generateFakeData.generateData();
		return null;
	}
}


