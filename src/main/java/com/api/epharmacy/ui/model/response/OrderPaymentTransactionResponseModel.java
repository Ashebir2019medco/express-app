package com.api.epharmacy.ui.model.response;

public class OrderPaymentTransactionResponseModel {
	
	private long orderPaymentTransactionId;
	
	private long orderPaymentVerificationId;
	
	private String transactionNumber;
	
	public long getOrderPaymentTransactionId() {
		return orderPaymentTransactionId;
	}
	
	public void setOrderPaymentTransactionId(long orderPaymentTransactionId) {
		this.orderPaymentTransactionId = orderPaymentTransactionId;
	}
	
	public long getOrderPaymentVerificationId() {
		return orderPaymentVerificationId;
	}
	
	public void setOrderPaymentVerificationId(long orderPaymentVerificationId) {
		this.orderPaymentVerificationId = orderPaymentVerificationId;
	}
	
	public String getTransactionNumber() {
		return transactionNumber;
	}
	
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	
}
