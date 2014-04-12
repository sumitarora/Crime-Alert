package com.crimealert.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.crimealert.enums.Role;
import com.crimealert.model.Crime;
import com.crimealert.model.User;
import com.crimealert.service.UserService;

@Controller
@RequestMapping(value="/user")
@Slf4j
public class UserController extends BaseController {

	@Autowired
	UserService userService;

	@RequestMapping(value="/create",method=RequestMethod.GET)
	public ModelAndView createUser(){
		log.debug("inside view all users");
		ModelAndView mav=new ModelAndView("user/create");		
		return setSelectedMenu(mav);
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute User user){
		log.debug("inside view all users");
		if(request.getParameter("userenabled").equals("Y")) {
			user.setEnabled(true);
		} else {
			user.setEnabled(false);
		}
		
		if(request.getParameter("userrole").equals("0")) {
			user.setRole(Role.USER);
		} else if(request.getParameter("userrole").equals("2")) {
			user.setRole(Role.MANAGER);
		} else {
			user.setRole(Role.USER);
		}
		
		user.setPassword("123456");
		
		if(!(user.getPhoto() != null && !user.getPhoto().equals(""))) {
			user.setPhoto("https://s3.amazonaws.com/crime-alert/1394710047317.png");
		}		
		
		userService.saveUser(user, true);
		return new ModelAndView(new RedirectView(""));
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView viewAllUsers(){
		log.debug("inside view all users");
		ModelAndView mav=new ModelAndView("user/user-list");
		String criteria = request.getParameter("criteria");
		if(criteria != null && !criteria.equals("")) {
			mav.addObject("users", userService.searchUsers(criteria));
		} else {
			mav.addObject("users", userService.getAllUsers());
		}		
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


