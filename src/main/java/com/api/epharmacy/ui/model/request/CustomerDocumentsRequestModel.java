package com.api.epharmacy.ui.model.request;

import org.springframework.web.multipart.MultipartFile;

public class CustomerDocumentsRequestModel {

	private String description;
	private MultipartFile  filename;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MultipartFile getFilename() {
		return filename;
	}
	public void setFilename(MultipartFile filename) {
		this.filename = filename;
	}
	
	
}
