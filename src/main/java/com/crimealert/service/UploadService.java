package com.crimealert.service;

import com.crimealert.model.Upload;

public interface UploadService {

	Upload saveUpload(Upload upload);
	void deleteUpload(Long id);
	Upload getUploadById(Long id);

}

