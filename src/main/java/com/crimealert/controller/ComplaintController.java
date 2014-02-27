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

import com.crimealert.model.Complaint;
import com.crimealert.model.User;
import com.crimealert.service.ComplaintService;

@Controller
@RequestMapping(value="/complaint")
@Slf4j
public class ComplaintController extends BaseController{
	
	@Autowired
	ComplaintService complaintService;
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public ModelAndView createComplaint(){
		log.debug("inside complaint create");
		ModelAndView mav=new ModelAndView("complaint/complaint-create");
		return setSelectedMenu(mav);
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView saveComplaint(@ModelAttribute Complaint complaint){
		log.debug("inside complaint save: {}", complaint.getComplaintId());
		
		complaint.setComplaintDate(new Date(new java.util.Date().getTime()));
		complaint.setMap("map");
		complaint.setUser(getLoggedInUser());
		
		complaintService.saveComplaint(complaint);
		return new ModelAndView(new RedirectView("list"));
	}
	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public ModelAndView editComplaint(@PathVariable int id){
		log.debug("inside complaint edit: {}", id);
		ModelAndView mav=new ModelAndView("complaint/complaint-create");
		mav.addObject("complaint", complaintService.getComplaintById(id));
		return setSelectedMenu(mav);
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public ModelAndView deleteComplaint(@PathVariable int id){
		log.debug("inside complaint delete: {}", id);
		
		final Complaint complaint = complaintService.getComplaintById(id);
		final User user = getLoggedInUser();
		if(complaint.getUser().getUserId().equals(user.getUserId())) {
			complaintService.deleteComplaint(complaint.getComplaintId());
		}
		return new ModelAndView(new RedirectView("/crime-alert/complaint/list"));
	}
	
	@RequestMapping(value= "/list", method=RequestMethod.GET )
	public ModelAndView listComplaints(){
		log.debug("inside complaint list");
		
		final List<Complaint> complaints = complaintService.getAllComplaints();
		ModelAndView mav = new ModelAndView("complaint/complaint-list");
		mav.addObject("complaints", complaints);

		return setSelectedMenu(mav);
	}
}

