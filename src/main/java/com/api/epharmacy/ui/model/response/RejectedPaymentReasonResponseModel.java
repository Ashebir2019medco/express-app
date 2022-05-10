package com.api.epharmacy.ui.model.response;

public class RejectedPaymentReasonResponseModel {
	
	private long rejectedPaymentReasonId;
	
	private long orderPaymentVerificationId;
	
	private String rejectedReason;
	
	public long getRejectedPaymentReasonId() {
		return rejectedPaymentReasonId;
	}
	
	public void setRejectedPaymentReasonId(long rejectedPaymentReasonId) {
		this.rejectedPaymentReasonId = rejectedPaymentReasonId;
	}
	
	public long getOrderPaymentVerificationId() {
		return orderPaymentVerificationId;
	}
	
	public void setOrderPaymentVerificationId(long orderPaymentVerificationId) {
		this.orderPaymentVerificationId = orderPaymentVerificationId;
	}

	
	public String getRejectedReason() {
		return rejectedReason;
	}

	
	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}
	

}
