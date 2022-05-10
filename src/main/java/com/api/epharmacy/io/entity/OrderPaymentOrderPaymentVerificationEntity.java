package com.api.epharmacy.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.epharmacy.model.audit.Audit;

@Entity(name = "order_payment_order_payment_verification")
public class OrderPaymentOrderPaymentVerificationEntity extends Audit {
	
	private static final long serialVersionUID = 250535891785333248L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderPaymentOrderPaymentVerificationId;
	
	@Column(nullable = false)
	private long orderPaymentId;
	
	@Column(nullable = false)
	private long orderPaymentVerificationId;
	
	@Column
	private boolean isDeleted;
	
	public long getOrderPaymentOrderPaymentVerificationId() {
		return orderPaymentOrderPaymentVerificationId;
	}
	
	public void setOrderPaymentOrderPaymentVerificationId(long orderPaymentOrderPaymentVerificationId) {
		this.orderPaymentOrderPaymentVerificationId = orderPaymentOrderPaymentVerificationId;
	}
	
	public long getOrderPaymentId() {
		return orderPaymentId;
	}
	
	public void setOrderPaymentId(long orderPaymentId) {
		this.orderPaymentId = orderPaymentId;
	}
	
	public long getOrderPaymentVerificationId() {
		return orderPaymentVerificationId;
	}
	
	public void setOrderPaymentVerificationId(long orderPaymentVerificationId) {
		this.orderPaymentVerificationId = orderPaymentVerificationId;
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}
	
	@Override
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
