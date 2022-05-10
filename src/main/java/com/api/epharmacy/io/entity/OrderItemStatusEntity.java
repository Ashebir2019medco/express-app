package com.api.epharmacy.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.epharmacy.model.audit.Audit;

@Entity(name="order_item_status")
public class OrderItemStatusEntity extends Audit {

	private static final long serialVersionUID = 3626520303178340761L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderItemStatusId;

	@Column
	private long orderItemId;
	
	@Column
	private double quantity;
	
	@Column
	private Integer orderStatusTypeId;

	public long getOrderItemStatusId() {
		return orderItemStatusId;
	}

	public void setOrderItemStatusId(long orderItemStatusId) {
		this.orderItemStatusId = orderItemStatusId;
	}

	public long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Integer getOrderStatusTypeId() {
		return orderStatusTypeId;
	}

	public void setOrderStatusTypeId(Integer orderStatusTypeId) {
		this.orderStatusTypeId = orderStatusTypeId;
	}

	
}
