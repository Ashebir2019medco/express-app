package com.api.epharmacy.ui.model.request;

import java.time.Instant;

public class OrderPaymentRequestModel {
	
	private long orderId;
	
	private long orderPaymentVerificationId;
	
	private double paidAmount;
	
	private Instant paymentDateTime;
	
	public long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
	public long getOrderPaymentVerificationId() {
		return orderPaymentVerificationId;
	}
	
	public void setOrderPaymentVerificationId(long orderPaymentVerificationId) {
		this.orderPaymentVerificationId = orderPaymentVerificationId;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	
	public Instant getPaymentDateTime() {
		return paymentDateTime;
	}

	
	public void setPaymentDateTime(Instant paymentDateTime) {
		this.paymentDateTime = paymentDateTime;
	}
	

}
