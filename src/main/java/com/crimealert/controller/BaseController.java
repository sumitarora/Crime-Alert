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
		mav.addObject("crimeActive", "");
		mav.addObject("profileActive", "");
		
		if(request.getRequestURI().contains("complaint")) {
			mav.addObject("complaintActive", "active");
		} else if(request.getRequestURI().contains("profile")) {
			mav.addObject("profileActive", "active");
		} else if(request.getRequestURI().contains("crime")) {
			mav.addObject("crimeActive", "active");
		}
		return mav;
	}
	
	public User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
		final User users = userService.getUserByEmail(user.getUsername());
		return users;
	}

}
