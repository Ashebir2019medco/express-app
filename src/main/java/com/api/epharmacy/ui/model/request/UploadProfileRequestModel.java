package com.api.epharmacy.ui.model.request;

import org.springframework.web.multipart.MultipartFile;

public class UploadProfileRequestModel {
	
	private MultipartFile  profilePicture;
	private String userId;
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public MultipartFile getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(MultipartFile profilePicture) {
		this.profilePicture = profilePicture;
	}
		

}
