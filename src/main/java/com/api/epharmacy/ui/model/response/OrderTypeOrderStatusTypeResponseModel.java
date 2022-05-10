package com.api.epharmacy.ui.model.response;

public class OrderTypeOrderStatusTypeResponseModel {
	
	private Integer orderTypeOrderStatusTypeId;
	
	private Integer orderTypeId;
	
	private String orderType;
	
	private Integer orderStatusTypeId;
	
	private String orderStatusType;
	
	private Integer weight;
	
	private int totalPage;
	
	public Integer getOrderTypeOrderStatusTypeId() {
		return orderTypeOrderStatusTypeId;
	}
	
	public void setOrderTypeOrderStatusTypeId(Integer orderTypeOrderStatusTypeId) {
		this.orderTypeOrderStatusTypeId = orderTypeOrderStatusTypeId;
	}
	
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
	
	public String getOrderType() {
		return orderType;
	}
	
	public void setOrderType(String orderType) {
		this.orderType = orderType;
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
