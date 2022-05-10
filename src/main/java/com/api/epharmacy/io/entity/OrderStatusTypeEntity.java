package com.api.epharmacy.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.epharmacy.model.audit.Audit;

@Entity(name = "order_status_type")
public class OrderStatusTypeEntity extends Audit implements Serializable {
	
	private static final long serialVersionUID = 5333275916209882612L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderStatusTypeId;
	
	@Column(nullable = false)
	private String orderStatusType;
	
	@Column
	private boolean isDeleted;

	
	public Integer getOrderStatusTypeId() {
		return orderStatusTypeId;
	}

	
	public void setOrderStatusTypeId(Integer orderStatusTypeId) {
		this.orderStatusTypeId = orderStatusTypeId;
	}

	
	public String getOrderStatusType() {
		return orderStatusType;
	}

	
	public void setOrderStatusType(String orderStatusType) {
		this.orderStatusType = orderStatusType;
	}

	
	public boolean isDeleted() {
		return isDeleted;
	}

	
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	

}
