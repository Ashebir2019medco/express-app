package com.api.epharmacy.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.epharmacy.model.audit.Audit;

@Entity(name="order_item")
public class OrderItemEntity extends Audit {

	private static final long serialVersionUID = 3626520303178340761L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderItemId;
	
	@Column
	private long inventoryId;

	@Column
	private double orderQuantity;
	
	@Column
	private double preOrderQuantity;
	
	@Column
	private double unitPrice;
	
	@Column(insertable=false, nullable=true)
	private long orderId;

	private Long orderPaymentDifferenceInTime;
	
	public Long getOrderPaymentDifferenceInTime() {
		return orderPaymentDifferenceInTime;
	}

	public void setOrderPaymentDifferenceInTime(Long orderPaymentDifferenceInTime) {
		this.orderPaymentDifferenceInTime = orderPaymentDifferenceInTime;
	}

	public long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public double getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(double orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public double getPreOrderQuantity() {
		return preOrderQuantity;
	}

	public void setPreOrderQuantity(double preOrderQuantity) {
		this.preOrderQuantity = preOrderQuantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
}
