package com.api.epharmacy.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.epharmacy.model.audit.Audit;

@Entity(name = "rejected_payment_reason")
public class RejectedPaymentReasonEntity extends Audit {
	
	private static final long serialVersionUID = 8780446767813680073L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rejectedPaymentReasonId;
	
	@Column(nullable = false)
	private long orderPaymentVerificationId;
	
	@Column
	private String rejectedReason;
	
	@Column
	private boolean isDeleted;
	
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
	
	public boolean isDeleted() {
		return isDeleted;
	}
	
	@Override
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
