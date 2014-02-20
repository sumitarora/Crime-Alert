package com.crimealert.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/crime")
public class CrimeController {
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView newCrimePage() 
	{
		ModelAndView mav = new ModelAndView("crime/crime-create");
		return mav;
		}
	}
