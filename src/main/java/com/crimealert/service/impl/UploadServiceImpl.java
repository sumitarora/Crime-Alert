package com.crimealert.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimealert.model.Upload;
import com.crimealert.repository.UploadRepository;
import com.crimealert.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	UploadRepository uploadRepository;
	
	@Override
	public Upload saveUpload(Upload upload) {
		return uploadRepository.save(upload);
	}

	@Override
	public void deleteUpload(Long id) {
		uploadRepository.delete(id);
	}

	@Override
	public Upload getUploadById(Long id) {
		return uploadRepository.findOne(id);
	}

}
