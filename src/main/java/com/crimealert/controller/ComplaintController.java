package com.crimealert.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/complaint")
@Slf4j
public class ComplaintController {

	@RequestMapping(value="/create",method=RequestMethod.GET)
	public ModelAndView sayHello(){
		ModelAndView mv=new ModelAndView("complaint/complaint-create");
		return mv;
		
	}

	@RequestMapping(value= "/list", method=RequestMethod.GET )
	public ModelAndView listComplaints(){
		ModelAndView mav = new ModelAndView("hello");
		return mav;
	}
}

