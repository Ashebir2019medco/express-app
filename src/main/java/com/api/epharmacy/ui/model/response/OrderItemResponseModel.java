package com.api.epharmacy.ui.model.response;

import java.util.List;

public class OrderItemResponseModel {
	
	private long orderItemId;
	private long inventoryId;
	private String inventoryGenericName;
	private double orderQuantity;
	private double preOrderQuantity;
	private double unitPrice;
	private List<OrderStatusTypeResponseModel> orderStatusTypeInfo;
	private List<OrderItemStatusResponseModel> orderItemStatusInfo;
	
	public String getInventoryGenericName() {
		return inventoryGenericName;
	}
	public void setInventoryGenericName(String inventoryGenericName) {
		this.inventoryGenericName = inventoryGenericName;
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
	public long getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}
	public List<OrderStatusTypeResponseModel> getOrderStatusTypeInfo() {
		return orderStatusTypeInfo;
	}
	public void setOrderStatusTypeInfo(List<OrderStatusTypeResponseModel> orderStatusTypeInfo) {
		this.orderStatusTypeInfo = orderStatusTypeInfo;
	}
	public List<OrderItemStatusResponseModel> getOrderItemStatusInfo() {
		return orderItemStatusInfo;
	}
	public void setOrderItemStatusInfo(List<OrderItemStatusResponseModel> orderItemStatusInfo) {
		this.orderItemStatusInfo = orderItemStatusInfo;
	}
	
	
}
