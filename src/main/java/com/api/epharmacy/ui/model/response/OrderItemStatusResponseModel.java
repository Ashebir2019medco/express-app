package com.api.epharmacy.ui.model.response;

import java.time.Instant;

public class OrderItemStatusResponseModel {

	private Integer orderStatusTypeId;
	private double quantity;
	private String orderStatusType;
	private Instant createdAt;
	
	private int totalPage;
	
	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

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
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
