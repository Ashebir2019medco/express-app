package com.api.epharmacy.io.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.epharmacy.model.audit.Audit;

@Entity(name = "order_payment")
public class OrderPaymentEntity extends Audit {
	
	private static final long serialVersionUID = 8843186331560426860L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderPaymentId;
	
	@Column(nullable = false)
	private long orderId;
	
	@Column
	private double paidAmount;
	
	@Column
	private Instant paymentDateTime;
	
	@Column
	private boolean isDeleted;
	
	public long getOrderPaymentId() {
		return orderPaymentId;
	}
	
	public void setOrderPaymentId(long orderPaymentId) {
		this.orderPaymentId = orderPaymentId;
	}
	
	public long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(long orderId) {
		this.orderId = orderId;
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

	public boolean isDeleted() {
		return isDeleted;
	}
	
	@Override
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
