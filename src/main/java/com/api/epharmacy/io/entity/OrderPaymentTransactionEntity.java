package com.api.epharmacy.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.epharmacy.model.audit.Audit;

@Entity(name = "order_payment_transaction")
public class OrderPaymentTransactionEntity extends Audit {
	
	private static final long serialVersionUID = 7416807470807234902L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderPaymentTransactionId;
	
	@Column(nullable = false)
	private long orderPaymentVerificationId;
	
	@Column
	private String transactionNumber;
	
	@Column
	private boolean isDeleted;
	
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
	
	public boolean isDeleted() {
		return isDeleted;
	}
	
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
