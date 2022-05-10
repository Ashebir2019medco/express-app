package com.api.epharmacy.ui.model.request;

public class OrderItemRequestModel {
	
	private long inventoryId;
	private double orderQuantity;
	private double preOrderQuantity;
	private double unitPrice;
	
    
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
	
	
}
