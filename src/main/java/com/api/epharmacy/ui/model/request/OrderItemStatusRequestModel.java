package com.api.epharmacy.ui.model.request;

public class OrderItemStatusRequestModel {
		
	private double quantity;
	
	private Integer orderStatusTypeId;

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
