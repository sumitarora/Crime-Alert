package com.crimealert.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.crimealert.enums.Role;
import com.crimealert.model.User;
import com.crimealert.service.UserService;

@Controller
@Slf4j
public class SecurityController {
	
	@Autowired(required=true)
	UserService userService;

	@Autowired 
	HttpServletRequest request;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login() {
		log.debug("inside login");
		return new ModelAndView("login");
	}

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView register() {
		log.debug("inside register");
		return new ModelAndView("register");
	}
	
	@RequestMapping(value="/registeruser", method=RequestMethod.POST)
	public ModelAndView registerUser() {
		final String fname = request.getParameter("first_name");
		final String lname = request.getParameter("last_name");
		final String email = request.getParameter("email");
		final String password = request.getParameter("password");
		
		final User u = new User();
		u.setFirstName(fname);
		u.setLastName(lname);
		u.setEmail(email);
		u.setPassword(password);
		u.setRole(Role.USER);
		u.setEnabled(true);
		userService.insertUser(u);
		log.debug("inside register user");
		return new ModelAndView("register");
	}	
	
	@RequestMapping(value="/accessdenied", method=RequestMethod.GET)
	public ModelAndView accessdenied() {
		log.debug("inside accessdenied");
		return new ModelAndView("common/accessdenied");
	}

   @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
	   SecurityContextHolder.clearContext();	   
	   return "redirect:/login";
    }
	   
	@RequestMapping(value = "/create", method = RequestMethod.GET)
    public String redirectRequestToRegistrationPage() {
		log.debug("creating user");
		//userService.insertUser("sumit", "123456");
		return "";
    }
	
}