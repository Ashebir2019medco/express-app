package com.api.epharmacy.ui.model.request;

public class InventoryTransactionUpdateRequestModel {

//	private long inventoryTransactionDetailId;
	private double quantity;
	private double costOfInventory;
	private double sellPrice;
	private double discountAmount;
	
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getCostOfInventory() {
		return costOfInventory;
	}
	public void setCostOfInventory(double costOfInventory) {
		this.costOfInventory = costOfInventory;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	
	
	/*
	 * public long getInventoryTransactionDetailId() { return
	 * inventoryTransactionDetailId; } public void
	 * setInventoryTransactionDetailId(long inventoryTransactionDetailId) {
	 * this.inventoryTransactionDetailId = inventoryTransactionDetailId; }
	 */	
	
}
