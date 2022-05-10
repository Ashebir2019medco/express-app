package com.api.epharmacy.ui.model.request;

public class PaymentStatusVerificationRequestModel {
	
	private String status;
	
	private String rejectedReason;
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getRejectedReason() {
		return rejectedReason;
	}
	
	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}
	
}
