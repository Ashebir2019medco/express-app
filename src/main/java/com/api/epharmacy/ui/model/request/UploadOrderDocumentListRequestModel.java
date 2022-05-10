package com.api.epharmacy.ui.model.request;

import java.util.List;

public class UploadOrderDocumentListRequestModel {
	
	List<UploadOrderDocumentRequestModel> uploadOrderDocumentRequestModels;
	
	public List<UploadOrderDocumentRequestModel> getUploadOrderDocumentRequestModels() {
		return uploadOrderDocumentRequestModels;
	}
	
	public void setUploadOrderDocumentRequestModels(List<UploadOrderDocumentRequestModel> uploadOrderDocumentRequestModels) {
		this.uploadOrderDocumentRequestModels = uploadOrderDocumentRequestModels;
	}
	
}
