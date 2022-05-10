package com.api.epharmacy.ui.model.request;

import org.springframework.web.multipart.MultipartFile;

public class UploadOrderDocumentRequestModel {
	
	private String[] transactionID;
	
	private MultipartFile[] uploadedDocument;
	
	public String[] getTransactionID() {
		return transactionID;
	}
	
	public void setTransactionID(String[] transactionID) {
		this.transactionID = transactionID;
	}
	
	public MultipartFile[] getUploadedDocument() {
		return uploadedDocument;
	}
	
	public void setUploadedDocument(MultipartFile[] uploadedDocument) {
		this.uploadedDocument = uploadedDocument;
	}
	

	
}
