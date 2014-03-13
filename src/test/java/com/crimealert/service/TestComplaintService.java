package com.crimealert.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.crimealert.init.BaseTestCase;
import com.crimealert.model.Complaint;
import com.crimealert.model.User;

public class TestComplaintService extends BaseTestCase  {

	@Autowired
	UserService userService;

	@Autowired
	ComplaintService complaintService;
	
	@Test
	public void testSample() {
		int a = 10;
		int b = 10;
		assertEquals(a+b, 20);
	}
	
	@Test
	public void testSaveComplaint() {
		final User user = userService.saveUser(ddg.createUser());
		assertTrue(user.getUserId() > 0);
		
		Complaint complaint = ddg.createComplaint();
		complaint.setUser(user);
		complaint = complaintService.saveComplaint(complaint);		
		assertTrue(complaint.getComplaintId() > 0);		
	}
	
	@Test
	public void testDeleteComplaint() {
		final User user = userService.saveUser(ddg.createUser());
		assertTrue(user.getUserId() > 0);
		
		Complaint complaint = ddg.createComplaint();
		complaint.setUser(user);
		complaint = complaintService.saveComplaint(complaint);
		assertTrue(complaint.getComplaintId() > 0);
		
		complaintService.deleteComplaint(complaint.getComplaintId());
		complaint = complaintService.getComplaintById(complaint.getComplaintId());
		assertNull(complaint);				
	}
	
	@Test
	public void testFindComplaintById() {
		final User user = userService.saveUser(ddg.createUser());
		assertTrue(user.getUserId() > 0);
		
		Complaint complaint = ddg.createComplaint();
		complaint.setUser(user);
		complaint = complaintService.saveComplaint(complaint);
		assertTrue(complaint.getComplaintId() > 0);
		
		final Complaint fetchedComplaint = complaintService.getComplaintById(complaint.getComplaintId());
		
		assertEquals(complaint.getComplaintId(), fetchedComplaint.getComplaintId());
		assertEquals(complaint.getAddress(), fetchedComplaint.getAddress());
		//assertEquals(complaint.getCity(), fetchedComplaint.getCity());
		assertEquals(complaint.getCountry(), fetchedComplaint.getCountry());
		assertEquals(complaint.getDescription(), fetchedComplaint.getDescription());
		assertEquals(complaint.getLocation(), fetchedComplaint.getLocation());
		assertEquals(complaint.getMap(), fetchedComplaint.getMap());
		//assertEquals(complaint.getProvience(), fetchedComplaint.getProvience());
		assertEquals(complaint.getTitle(), fetchedComplaint.getTitle());
	}
	
	@Test
	public void testGetAllComplaints() {
		final User user = userService.saveUser(ddg.createUser());
		assertTrue(user.getUserId() > 0);
		
		Complaint complaint = ddg.createComplaint();
		complaint.setUser(user);
		complaint = complaintService.saveComplaint(complaint);
		assertTrue(complaint.getComplaintId() > 0);
		
		final List<Complaint> complaints = null;//complaintService.getAllComplaints();
		assertTrue(complaints.size() > 0);		
	}
}
