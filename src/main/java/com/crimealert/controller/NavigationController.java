package com.crimealert.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.crimealert.model.Complaint;
import com.crimealert.model.Crime;
import com.crimealert.service.ComplaintService;
import com.crimealert.service.CrimeService;

@Controller
@Slf4j

public class NavigationController extends BaseController {
	
	
	@Autowired
	CrimeService crimeService;
	
	@Autowired
	ComplaintService complaintService;
	
	@RequestMapping(value={"/", "index"}, method=RequestMethod.GET)
	public ModelAndView index() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		log.debug(auth.getPrincipal().toString());
		if(auth.getPrincipal().toString().equals("anonymousUser")) {
			return new ModelAndView("index");
		} else {
			return new ModelAndView(new RedirectView("/home", true));
			
		}	
		
	}
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ModelAndView search() {
		final String type = request.getParameter("type");
		final String criteria = request.getParameter("criteria");
		log.debug("searching for {} - {}", type, criteria);
		
		if(type.equals("cr")) {
			final ModelAndView mav = new ModelAndView("crime/crime-search");
			final List<Crime> crimes = crimeService.findByTitleOrDescription(criteria, criteria);
			mav.addObject("crimes", crimes);
			return setSelectedMenu(mav);			
		} else {
			final ModelAndView mav = new ModelAndView("complaint/complaint-search");
			final List<Complaint> complaints = complaintService.findByTitleOrDescription(criteria, criteria);
			mav.addObject("complaints", complaints);
			return setSelectedMenu(mav);
		}
	}


	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView home() {
		return new ModelAndView("home");
	}
}
