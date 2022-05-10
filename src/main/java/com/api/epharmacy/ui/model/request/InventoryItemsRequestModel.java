package com.api.epharmacy.ui.model.request;

public class InventoryItemsRequestModel {
		private long inventoryId;
		private String transactionType;
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
		public String getTransactionType() {
			return transactionType;
		}
		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
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
