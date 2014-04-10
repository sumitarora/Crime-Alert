package com.crimealert.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.crimealert.model.User;
import com.crimealert.service.UserService;

@Slf4j
public abstract class BaseController {

	@Autowired 
	HttpServletRequest request;

	@Autowired
	UserService userService;

	public ModelAndView setSelectedMenu(final ModelAndView mav) {
		log.debug(request.getRequestURI());
		
		mav.addObject("complaintActive", "");
		mav.addObject("dashboardActive", "");
		mav.addObject("crimeActive", "");
		mav.addObject("profileActive", "");
		mav.addObject("homeActive", "");
		mav.addObject("reportsActive", "");
		mav.addObject("feedbackActive", "");
		
		if(request.getRequestURI().contains("complaint")) {
			mav.addObject("complaintActive", "active");
		} else if(request.getRequestURI().contains("dashboard")) {
			mav.addObject("dashboardActive", "active");
		} else if(request.getRequestURI().contains("profile")) {
			mav.addObject("profileActive", "active");
		} else if(request.getRequestURI().contains("home"))  {
			mav.addObject("homeActive", "active");
		} else if(request.getRequestURI().contains("user"))  {
			mav.addObject("userActive", "active");
		} else if(request.getRequestURI().contains("reports")) {
			mav.addObject("reportsActive", "active");
		} else if(request.getRequestURI().contains("feedback")) {
			mav.addObject("feedbackActive", "active");			
		} else if(request.getRequestURI().contains("crime")) {
			mav.addObject("crimeActive", "active");
		} 
		
		mav.addObject("loggedInUser", getLoggedInUser());
		return mav;
	}
	
	public User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.getPrincipal().equals("anonymousUser")) {
			org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			final User users = userService.getUserByEmail(user.getUsername());
			return users;			
		}
		return null;

	}

}
