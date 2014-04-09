package com.crimealert.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.crimealert.model.Email;
import com.crimealert.model.User;
import com.crimealert.repository.UserRepository;
import com.crimealert.service.UserService;
import com.crimealert.util.MailgunEmail;

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
	
	@Value("${domainPath}")
	private String DOMAIN_PATH;

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
	public User saveUser(User u) {
		u.setDateJoined(utils.getCurrentTimeStamp());
		u.setEnabled(false);
		u.setVerifyToken(utils.generateToken());		
		u = userRepository.save(u);
		
		if (u != null) {
			// send verification email
			final Email email = new Email();
			email.setTo(u.getEmail());
			email.setContent("Thank u for signing up. Please click the below link to verify your email. <br /><br />"
					+ DOMAIN_PATH + "verify/" + u.getVerifyToken()
					+ "<br /><br />Thanks,<br />CencolShare Team");
			email.setSubject("Hey " + u.getFirstName() + ", Welcome to CrimeAlert!");
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
			return user;
		}
	}
}
