
package com.crimealert.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/view")
@Slf4j
public class ViewController {
	
	@RequestMapping(value= "/hello", method=RequestMethod.GET )
	public ModelAndView sayHello(){
		ModelAndView mav = new ModelAndView("hello");
		return mav;
	}
	

}
