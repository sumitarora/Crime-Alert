package com.crimealert.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.crimealert.model.Comment;
import com.crimealert.model.Crime;
import com.crimealert.model.User;
import com.crimealert.service.CrimeService;

@Controller
@RequestMapping(value="/crime")
@Slf4j
public class CrimeController extends BaseController{

	@Autowired
	CrimeService crimeService;
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public ModelAndView createCrime(){
		log.debug("inside Crime create");
		ModelAndView mav=new ModelAndView("crime/crime-create");
		return setSelectedMenu(mav);
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView saveCrime(@ModelAttribute Crime crime){
		log.debug("inside Crime save: {}", crime.getCrimeId());
		
		crime.setCrimeDate(new Date(new java.util.Date().getTime()));
		crime.setMap("map");		
		
		if(request.getParameter("userId") != null && !request.getParameter("userId").equals("")) {
			crime.setUser(userService.getUserById(Integer.parseInt(request.getParameter("userId"))));
		} else {
			crime.setUser(getLoggedInUser());
		}
		
		String[] location = crime.getLocation().split(",");
		crime.setLatitude(location[0]);
		crime.setLongitude(location[1]);
		
		crimeService.saveCrime(crime);
		return new ModelAndView(new RedirectView("list"));
	}
	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public ModelAndView editCrime(@PathVariable int id){
		log.debug("inside Crime edit: {}", id);
		ModelAndView mav=new ModelAndView("crime/crime-create");
		mav.addObject("crime", crimeService.findCrimeById(id));
		return setSelectedMenu(mav);
	}

	@RequestMapping(value="/view/{id}",method=RequestMethod.GET)
	public ModelAndView viewCrime(@PathVariable int id){
		log.debug("inside Crime view: {}", id);
		ModelAndView mav=new ModelAndView("crime/crime-view");
		mav.addObject("crime", crimeService.findCrimeById(id));
		return setSelectedMenu(mav);
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public ModelAndView deleteCrime(@PathVariable int id){
		log.debug("inside Crime delete: {}", id);
		final Crime crime = crimeService.findCrimeById(id);
		final User user = getLoggedInUser();
		if(crime.getUser().getUserId().equals(user.getUserId())) {
			crimeService.deleteCrime(crime.getCrimeId());
		}
		return new ModelAndView(new RedirectView("/crime-alert/crime/list"));
	}
	
	@RequestMapping(value= "/list", method=RequestMethod.GET )
	public ModelAndView listCrimes(){
		log.debug("inside Crime list");
		
		final List<Crime> crimes = crimeService.getAllCrimes(getLoggedInUser());
		ModelAndView mav = new ModelAndView("crime/crime-list");
		mav.addObject("crimes", crimes);

		return setSelectedMenu(mav);
	}

	@RequestMapping(value= "/comment", method=RequestMethod.POST )
	@ResponseBody
	public Map<String, String> saveComment(){
		
		final Map<String, String> result = new HashMap<String, String>();
		log.debug("inside save comment for crime");
		final User loggedinUser = getLoggedInUser();
		if(loggedinUser == null) {
			result.put("result", "fail");
			result.put("message", "You must be loggedin to make a comment.");
			return result;
		}
		
		final int crimeId = Integer.parseInt(request.getParameter("crimeId"));
		final Crime crime = crimeService.findCrimeById(crimeId);
		if(crime == null) {
			result.put("result", "fail");
			result.put("message", "Crime not found to comment.");
			return result;
		}
		
		List<Comment> comments = crime.getComments();
		
		if(comments == null) {
			comments = new ArrayList<Comment>();
		}
		
		final Comment comment = new Comment();
		comment.setUser(getLoggedInUser());
		comment.setCommentDate(new Date((new java.util.Date().getTime())));
		comment.setDescription(request.getParameter("description"));
		comments.add(comment);
		
		crime.setComments(comments);
		crimeService.saveCrime(crime);

		result.put("result", "success");
		return result;
	}

	@RequestMapping(value= "/list/all", method=RequestMethod.GET )
	public ModelAndView listAllCrimes(){
		log.debug("inside Crime list");
		
		final List<Crime> crimes = crimeService.getAllCrimes();
		ModelAndView mav = new ModelAndView("crime/crime-list");
		mav.addObject("crimes", crimes);

		return setSelectedMenu(mav);
	}
	
}
