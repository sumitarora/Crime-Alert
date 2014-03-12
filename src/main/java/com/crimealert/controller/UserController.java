package com.crimealert.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.crimealert.service.UserService;

@Controller
@RequestMapping(value="/user")
@Slf4j
public class UserController extends BaseController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView viewAllUsers(){
		log.debug("inside view all users");
		ModelAndView mav=new ModelAndView("user/user-list");
		mav.addObject("users", userService.getAllUsers());
		return setSelectedMenu(mav);
	}
	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public ModelAndView editUser(@PathVariable int id){
		log.debug("inside user edit: {}", id);
		ModelAndView mav=new ModelAndView("user/profile");
		mav.addObject("user", userService.getUserById(id));
		mav.addObject("fromAdmin", true);
		return setSelectedMenu(mav);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ModelAndView viewUserProfile(@PathVariable int id){
		log.debug("inside user edit: {}", id);
		ModelAndView mav=new ModelAndView("user/view-profile");
		mav.addObject("user", userService.getUserById(id));
		mav.addObject("fromAdmin", true);
		return setSelectedMenu(mav);
	}
	
}
