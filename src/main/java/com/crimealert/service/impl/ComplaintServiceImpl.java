package com.crimealert.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crimealert.model.Complaint;
import com.crimealert.repository.ComplaintRepository;
import com.crimealert.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService {

	@Resource
	private ComplaintRepository complaintRepository;

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
	
}
