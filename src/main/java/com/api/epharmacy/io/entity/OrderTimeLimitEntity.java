package com.api.epharmacy.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.epharmacy.model.audit.Audit;

@Entity(name = "order_time_limit")
public class OrderTimeLimitEntity extends Audit {
	
	private static final long serialVersionUID = 5529802082101741051L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderTimeLimitId;
	
	@Column(nullable = false)
	private String timeLimit;
	
	private boolean isDeleted;
	
	public Integer getOrderTimeLimitId() {
		return orderTimeLimitId;
	}
	
	public void setOrderTimeLimitId(Integer orderTimeLimitId) {
		this.orderTimeLimitId = orderTimeLimitId;
	}
	
	public String getTimeLimit() {
		return timeLimit;
	}
	
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}
	
	@Override
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
