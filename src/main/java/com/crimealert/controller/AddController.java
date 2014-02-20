package com.crimealert.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/add")
@Slf4j
	public class AddController {
	@RequestMapping(value="/report", method=RequestMethod.GET)
	public ModelAndView crimeReport(){
		ModelAndView mav = new ModelAndView("report");
		return mav;
	}
}

