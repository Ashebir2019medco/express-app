package com.api.epharmacy.ui.model.request;

public class OrderStatusTypeRequestModel {
	
	private String orderStatusType;
	
	private Integer[] orderTypeIds;
	
	public String getOrderStatusType() {
		return orderStatusType;
	}
	
	public void setOrderStatusType(String orderStatusType) {
		this.orderStatusType = orderStatusType;
	}
	
	public Integer[] getOrderTypeIds() {
		return orderTypeIds;
	}
	
	public void setOrderTypeIds(Integer[] orderTypeIds) {
		this.orderTypeIds = orderTypeIds;
	}
	
}
