package com.api.epharmacy.ui.model.request;

public class OrderTypeOrderStatusTypeRequestModel {
	
	private Integer orderTypeId;
	
	private Integer orderStatusTypeId;
	
	private Integer weight;

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
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
	
}
