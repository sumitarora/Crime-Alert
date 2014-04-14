package com.crimealert.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.crimealert.enums.Role;
import com.crimealert.model.User;
import com.crimealert.service.UserService;

@Controller
@Slf4j
public class SecurityController extends BaseController {
	
	@Autowired(required=true)
	UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login() {
		if(getLoggedInUser() != null) {
			return new ModelAndView(new RedirectView("home"));
		}
		log.debug("inside login");
		return new ModelAndView("login");
	}

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView register() {
		if(getLoggedInUser() != null) {
			return new ModelAndView(new RedirectView("home"));
		}		
		log.debug("inside register");
		return new ModelAndView("register");
	}
	
	@RequestMapping(value="/forgotpassword", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView forgotPassword() {
		log.debug("inside forgot password");
		if(request.getMethod().equals(RequestMethod.GET.toString())) {
			return new ModelAndView("forgot-password");
		} else {
			final User user = userService.getUserByEmail(request.getParameter("email"));
			if(user == null) {
				return new ModelAndView("forgot-password");
			}
			userService.generateForgotPasswordToken(user);
			return new ModelAndView("forgot-password-success");
		}
		
	}	
	
	@RequestMapping(value="/resetforgotpassword/{token}", method=RequestMethod.GET)
	public ModelAndView resetPassword(@PathVariable String token) {
		log.debug("inside reset forgot password: {}", token);
		ModelAndView mav = new ModelAndView("reset-password");
		mav.addObject("token", token);
		return mav;
	}
	
	@RequestMapping(value="/changeforgotpassword", method=RequestMethod.POST)
	public ModelAndView changeForgotPassword() {
		
		String token = request.getParameter("token");
		String newPassword = request.getParameter("newPassword");
		
		User user = userService.findByForgotPasswordToken(token);
		user.setPassword(newPassword);
		user.setForgotPasswordToken(null);
		user = userService.updateUser(user);

		ModelAndView mav = new ModelAndView("reset-password");
		if(user != null) {
			mav.addObject("changed", true);
		}				
		return mav;
	}
	
	@RequestMapping(value="/registeruser", method=RequestMethod.POST)
	public ModelAndView registerUser() {
		final String fname = request.getParameter("first_name");
		final String lname = request.getParameter("last_name");
		final String email = request.getParameter("email");
		final String password = request.getParameter("password");
		
		User existing = userService.getUserByEmail(email);
		if(existing != null) {
			return new ModelAndView(new RedirectView("register?emailexists=true&email="+email));
		}
		final User u = new User();
		u.setFirstName(fname);
		u.setLastName(lname);
		u.setEmail(email);
		u.setPassword(password);
		u.setRole(Role.USER);
		u.setPhoto("");
		userService.saveUser(u, true);
		log.debug("inside register user");
		//return new ModelAndView(new RedirectView("login"));
		return new ModelAndView("register_success");
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
    
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView viewProfile() {
		log.debug("view user profile");
		ModelAndView mav = new ModelAndView("user/profile");
		mav.addObject("user", getLoggedInUser());
		mav.addObject("profileUpdate", true);
		return setSelectedMenu(mav);
    }

	@RequestMapping(value = "/profile/save", method = RequestMethod.POST)
    public ModelAndView saveProfile(final @ModelAttribute User user) {
		log.debug("updating user: {}", user.getUserId());
		log.debug("from admin: {}", user.getFromAdmin());

		if(!(user.getPhoto() != null && !user.getPhoto().equals(""))) {
			user.setPhoto("https://s3.amazonaws.com/crime-alert/1394710047317.png");
			log.debug("setting default pic");
		}
		
		if(user.getFromAdmin() != null && user.getFromAdmin()) {
			log.debug("updating user from admin");
			User userToUpdate = userService.getUserById(user.getUserId());
			user.setPassword(userToUpdate.getPassword());
			user.setUserId(userToUpdate.getUserId());

			if(request.getParameter("userenabled") != null && request.getParameter("userenabled").equals("Y")) {
				user.setEnabled(true);
			} else {
				user.setEnabled(false);
			}
			
			if(!userToUpdate.getRole().equals(Role.ADMIN)) {
				if(request.getParameter("userrole") != null && request.getParameter("userrole").equals("0")) {
					user.setRole(Role.USER);
				} else if(request.getParameter("userrole") != null && request.getParameter("userrole").equals("2")) {
					user.setRole(Role.MANAGER);
				} else {
					user.setRole(Role.USER);
				}				
			}
			userService.saveUser(user, false);			
			return new ModelAndView(new RedirectView(DOMAIN_PATH + "/user"));
		}
		
		user.setEnabled(getLoggedInUser().getEnabled());
		user.setPassword(getLoggedInUser().getPassword());
		user.setUserId(getLoggedInUser().getUserId());
		user.setRole(getLoggedInUser().getRole());
		userService.saveUser(user, false);
		
		return new ModelAndView(new RedirectView(""));
    }
	
	
	@RequestMapping(value = "/verify/{token}", method = RequestMethod.GET)
	public ModelAndView verifyEmail(ModelMap model, @PathVariable String token) {

		ModelAndView mav = new ModelAndView("verify_email");
		final User verifiedUser = userService.verifyEmail(token);

		if (verifiedUser == null) {
			mav.addObject("message", "Oops! Invalid token. Failed to verify");
		} else {
			mav.addObject("message",
					"Successfully verified your email address. Please procced to login");
		}

		return mav;
	}
	
	@RequestMapping(value="/changepassword", method=RequestMethod.GET)
	public ModelAndView changePassword(){
		ModelAndView mav = new ModelAndView("user/change-password");
		if(request.getParameter("c") != null) {
			mav.addObject("changed", true);
		}
		return setSelectedMenu(mav);
	}

	@RequestMapping(value="/resetpassword", method=RequestMethod.POST)
	public ModelAndView updatePassword(){
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");

		if(!getLoggedInUser().getPassword().equals(oldPassword)){
			request.setAttribute("error", "Old Password did not match");
			return new ModelAndView(new RedirectView("changepassword"));
		}
		User u = getLoggedInUser();
		u.setPassword(newPassword);
		userService.updateUser(u);
		log.debug(oldPassword + newPassword);
		return new ModelAndView(new RedirectView("changepassword?c=true"));
	}
	
}


