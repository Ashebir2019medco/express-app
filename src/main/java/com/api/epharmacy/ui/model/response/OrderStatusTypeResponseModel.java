package com.api.epharmacy.ui.model.response;

import java.time.Instant;

public class OrderStatusTypeResponseModel {
	
	private Integer orderStatusTypeId;
	
	private String orderStatusType;
	
	private Integer[] orderTypeIds;
	
	private int totalPage;
	
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
	
	public Integer[] getOrderTypeIds() {
		return orderTypeIds;
	}
	
	public void setOrderTypeIds(Integer[] orderTypeIds) {
		this.orderTypeIds = orderTypeIds;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
