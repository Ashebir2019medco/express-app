package com.api.epharmacy.ui.model.response;

public class OrderStatusResponseModel {
	
	private long orderItemId;
	
	private Integer quantity;
	
	private Integer orderStatusTypeId;

	public long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getOrderStatusTypeId() {
		return orderStatusTypeId;
	}

	public void setOrderStatusTypeId(Integer orderStatusTypeId) {
		this.orderStatusTypeId = orderStatusTypeId;
	}

}
