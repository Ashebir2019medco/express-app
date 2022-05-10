package com.api.epharmacy.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.epharmacy.model.audit.Audit;

@Entity(name = "order_type_order_status_type")
public class OrderTypeOrderStatusTypeEntity extends Audit implements Serializable {
	
	private static final long serialVersionUID = 2652597433718161762L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderTypeOrderStatusTypeId;
	
	@Column
	private Integer orderTypeId;

	@Column
	private Integer orderStatusTypeId;

	@Column
	private Integer weight;
	
	@Column
	private boolean isDeleted;
	
	public Integer getWeight() {
		return weight;
	}


	public void setWeight(Integer weight) {
		this.weight = weight;
	}


	public Integer getOrderTypeOrderStatusTypeId() {
		return orderTypeOrderStatusTypeId;
	}

	
	public void setOrderTypeOrderStatusTypeId(Integer orderTypeOrderStatusTypeId) {
		this.orderTypeOrderStatusTypeId = orderTypeOrderStatusTypeId;
	}
	
	public Integer getOrderTypeId() {
		return orderTypeId;
	}
	
	public void setOrderTypeId(Integer orderTypeId) {
		this.orderTypeId = orderTypeId;
	}
	
	public Integer getOrderStatusTypeId() {
		return orderStatusTypeId;
	}
	
	public void setOrderStatusTypeId(Integer orderStatusTypeId) {
		this.orderStatusTypeId = orderStatusTypeId;
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}
	
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
