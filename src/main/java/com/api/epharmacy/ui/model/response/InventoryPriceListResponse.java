package com.api.epharmacy.ui.model.response;

public class InventoryPriceListResponse {
	private long inventoryId;
	private long inventoryPriceListId;
	private double quantity;
	private double costOfInventory;
	private double sellPrice;
	private double discountAmount;
	
	
	public long getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(long inventoryId) {
		this.inventoryId = inventoryId;
	}
	public long getInventoryPriceListId() {
		return inventoryPriceListId;
	}
	public void setInventoryPriceListId(long inventoryPriceListId) {
		this.inventoryPriceListId = inventoryPriceListId;
	}
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
	
}
