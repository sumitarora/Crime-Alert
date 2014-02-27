package com.crimealert.controller;

import java.sql.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
		crime.setUser(getLoggedInUser());
		
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
		
		final List<Crime> crimes = crimeService.getAllCrimes();
		ModelAndView mav = new ModelAndView("crime/crime-list");
		mav.addObject("crimes", crimes);

		return setSelectedMenu(mav);
	}
	
}
