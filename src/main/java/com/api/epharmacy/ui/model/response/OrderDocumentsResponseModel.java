package com.api.epharmacy.ui.model.response;

import java.util.Date;

public class OrderDocumentsResponseModel {

	private long orderDocumentsId;

	private long orderPaymentVerificationId;

	private String uploadedDocument;
	
	private Date uploadedDate;

	public long getOrderDocumentsId() {
		return orderDocumentsId;
	}

	public void setOrderDocumentsId(long orderDocumentsId) {
		this.orderDocumentsId = orderDocumentsId;
	}
	
	public long getOrderPaymentVerificationId() {
		return orderPaymentVerificationId;
	}

	public void setOrderPaymentVerificationId(long orderPaymentVerificationId) {
		this.orderPaymentVerificationId = orderPaymentVerificationId;
	}

	public String getUploadedDocument() {
		return uploadedDocument;
	}

	public void setUploadedDocument(String uploadedDocument) {
		this.uploadedDocument = uploadedDocument;
	}

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

}
