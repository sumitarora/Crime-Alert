package com.crimealert.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.crimealert.model.Email;
import com.crimealert.model.User;
import com.crimealert.repository.UserRepository;
import com.crimealert.service.UserService;
import com.crimealert.util.GeneralUtils;
import com.crimealert.util.MailgunEmail;
import com.crimealert.util.TemplateUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@PersistenceContext
	EntityManager em;

	@Autowired
	GeneralUtils utils;

	@Autowired
	MailgunEmail mailgunEmail;
	
	@Autowired
	TemplateUtil templates;
	
	@Value("${baseUrl}")
    private String BASE_URL;

	@Override
	public User getUserByEmail(String email) {
		List<User> users = userRepository.findByEmail(email);
		if(users.size() != 0) {
			return users.get(0);
		}
		return null;
	}
	
	@Override
	public User updateUser(final User u) {
		return userRepository.save(u);
	}
	
	@Override
	public User saveUser(User u, final Boolean create) {
		u.setDateJoined(utils.getCurrentTimeStamp());
		if(create) {
			u.setEnabled(false);
			u.setVerifyToken(utils.generateToken());
		}
		u = userRepository.save(u);
		
		if (u != null && create.equals(Boolean.TRUE)) {
			// send verification email
			final Email email = new Email();
			email.setTo(u.getEmail());
			
		     final VelocityContext context = new VelocityContext();
		     context.put("sentTo", u.getEmail());
		     context.put("title", "Welcome to CrimeAlert!");
		     context.put("name", u.getFirstName() + " " + u.getLastName());
		     context.put("verifyUrl", BASE_URL + "verify/" + u.getVerifyToken());

		    email.setContent(templates.getEmailTemplate("templates/email-confirmation.vm", context));
			email.setSubject("CrimeAlert - Account Created");
			mailgunEmail.sendEmail(email);
		}
		
		return u;
	}
	
	@Override
	public User getUserById(int id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public List<User> searchUsers(String criteria) {
		String query = "select * from tbl_user u where u.first_name like '%"+criteria+"%' or u.last_name like '%"+criteria+"%' or u.email like '%"+criteria+"%'";
	    final Query q = em.createNativeQuery(query, User.class);
	    return q.getResultList();		
	}
	
	
	@Override
	public User verifyEmail(String token) {
		final User user = userRepository.findByVerifyToken(token);
		if(user == null) {
			return null;
		} else {
			user.setEnabled(true);
			user.setVerifyToken("token processed");
			updateUser(user);
			
			if (user != null) {
				// send verification email
				final Email email = new Email();
				email.setTo(user.getEmail());
				
			     final VelocityContext context = new VelocityContext();
			     context.put("sentTo", user.getEmail());
			     context.put("title", "Thanks for Confirmation");
			     context.put("name", user.getFirstName() + " " + user.getLastName());
			     context.put("loginUrl", BASE_URL + "login");

			    email.setContent(templates.getEmailTemplate("templates/email-verified.vm", context));
				email.setSubject("CrimeAlert - Email Verified");
				mailgunEmail.sendEmail(email);
			}			
			return user;
		}
	}
}
