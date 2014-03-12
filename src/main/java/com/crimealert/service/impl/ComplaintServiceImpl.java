package com.crimealert.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crimealert.model.Complaint;
import com.crimealert.model.User;
import com.crimealert.repository.ComplaintRepository;
import com.crimealert.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService {

	@Resource
	private ComplaintRepository complaintRepository;
	
	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public  Complaint saveComplaint(Complaint complaint) {
		Complaint createdComplaint = complaint;
		return complaintRepository.save(createdComplaint);
	}
	
	@Override
	public Complaint getComplaintById(int id) {
		return complaintRepository.findOne(id);
	}

	@Override
	@Transactional
	public void deleteComplaint(int id)  {
		complaintRepository.delete(id);
	}

	@Override
	public List<Complaint> getAllComplaints(User user) {
		return (List<Complaint>) complaintRepository.findComplaintsByUser(user);
	}
	
	@Override
	public List<Complaint> getAllComplaints() {
		return (List<Complaint>) complaintRepository.findAll();
	}
	
	@Override
	public List<Complaint> findByTitleOrDescription(String title, String description) {
		String query = "select * from tbl_complaint where title like '%"+title+"%' or description like '%"+description+"%'";
	    final Query q = em.createNativeQuery(query, Complaint.class);
	    return q.getResultList();		
	}	
	
}
