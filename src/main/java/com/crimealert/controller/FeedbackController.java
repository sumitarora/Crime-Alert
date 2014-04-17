package com.crimealert.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.crimealert.model.Comment;
import com.crimealert.model.Email;
import com.crimealert.model.Feedback;
import com.crimealert.model.User;
import com.crimealert.service.FeedbackService;
import com.crimealert.util.MailgunEmail;
import com.crimealert.util.TemplateUtil;


@Controller
@RequestMapping(value="/feedback")
@Slf4j
public class FeedbackController extends BaseController {

	@Autowired
	FeedbackService feedbackService;

	@Autowired
	MailgunEmail mailgunEmail;
	
	@Autowired
	TemplateUtil templates;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView list(){
		log.debug("inside list feedback");
		List<Feedback> feedbacks = feedbackService.getAllByUser(getLoggedInUser());
		ModelAndView mav=new ModelAndView("feedback/list-all");
		mav.addObject("feedbacks", feedbacks);
		return setSelectedMenu(mav);
	}
	
	@RequestMapping(value="/list/all",method=RequestMethod.GET)
	public ModelAndView listAll(){
		log.debug("inside list feedback");
		List<Feedback> feedbacks = feedbackService.getAll();
		ModelAndView mav=new ModelAndView("feedback/list-all");
		mav.addObject("feedbacks", feedbacks);
		return setSelectedMenu(mav);
	}
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public ModelAndView create(){
		log.debug("inside create feedback");
		ModelAndView mav=new ModelAndView("feedback/create");
		return setSelectedMenu(mav);
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView save(@ModelAttribute Feedback feedback){
		log.debug("inside save feedback");
		feedback.setUser(getLoggedInUser());
		feedback.setFeedbackDate(new java.sql.Date(new java.util.Date().getTime()));
		feedbackService.saveFeedback(feedback);
		if(feedback != null) {
			final Email email = new Email();
			email.setTo("er.sumitarora@gmail.com");
			
		     final VelocityContext context = new VelocityContext();
		     context.put("sentTo", "er.sumitarora@gmail.com");
		     context.put("title", "New Feedback Added");
		     context.put("user", feedback.getUser().getFirstName() + " " + feedback.getUser().getLastName());
		     context.put("feedback", feedback.getType());

		    email.setContent(templates.getEmailTemplate("templates/feedback-admin.vm", context));
			email.setSubject("Crime Vigilant - New Feedback Added");
			mailgunEmail.sendEmail(email);
		}		
		return new ModelAndView(new RedirectView(""));
	}
	
	@RequestMapping(value="/view/{id}",method=RequestMethod.GET)
	public ModelAndView view(@PathVariable Long id){
		log.debug("inside view feedback");
		Feedback feedback = feedbackService.getFeedbackById(id);
		ModelAndView mav=new ModelAndView("feedback/view");
		mav.addObject("feedback", feedback);
		return setSelectedMenu(mav);		
	}
	
	@RequestMapping(value= "/comment", method=RequestMethod.POST )
	@ResponseBody
	public Map<String, String> saveComment(){
		
		final Map<String, String> result = new HashMap<String, String>();
		log.debug("inside save comment for feedback");
		final User loggedinUser = getLoggedInUser();
		if(loggedinUser == null) {
			result.put("result", "fail");
			result.put("message", "You must be loggedin to make a comment.");
			return result;
		}
		
		final Long feedbackId = Long.parseLong(request.getParameter("feedbackId"));
		final Feedback feedback = feedbackService.getFeedbackById(feedbackId);
		if(feedback == null) {
			result.put("result", "fail");
			result.put("message", "Feedback not found to comment.");
			return result;
		}
		
		List<Comment> comments = feedback.getComments();
		
		if(comments == null) {
			comments = new ArrayList<Comment>();
		}
		
		final Comment comment = new Comment();
		comment.setUser(getLoggedInUser());
		comment.setCommentDate(new Date((new java.util.Date().getTime())));
		comment.setDescription(request.getParameter("description"));
		comments.add(comment);
		
		feedback.setComments(comments);
		feedbackService.saveFeedback(feedback);

		if(feedback != null) {

			final Email email = new Email();
			email.setTo(feedback.getUser().getEmail());
			
		     final VelocityContext context = new VelocityContext();
		     context.put("sentTo", feedback.getUser().getEmail());
		     context.put("title", "New Comment Added");
		     context.put("user", feedback.getUser().getFirstName() + " " + feedback.getUser().getLastName());
		     context.put("feedback", feedback.getType());

		    email.setContent(templates.getEmailTemplate("templates/feedback-comment.vm", context));
			email.setSubject("Crime Vigilant - New Comment Added");
			mailgunEmail.sendEmail(email);
		}

		result.put("result", "success");
		return result;
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public ModelAndView delete(@PathVariable Long id){
		log.debug("inside Crime delete: {}", id);
		final Feedback feedback = feedbackService.getFeedbackById(id);
		final User user = getLoggedInUser();
		if(feedback.getUser().getUserId().equals(user.getUserId())) {
			feedbackService.deleteFeedback(id);
		}
		return new ModelAndView(new RedirectView(DOMAIN_PATH + "/feedback"));
	}	
	
}
