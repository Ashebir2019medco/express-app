package com.api.epharmacy.shared.dto;

public class InventoryItemsDto {
	private long inventoryId;
	private long inventoryPriceListId;
	private String inventoryGenericName;
	private String inventoryType;
	private String inventoryBrandName;
	private String measuringUnit;
	private String transactionType;
	private long inventoryTransactionDetailId;
	private double quantity;
	private double costOfInventory;
	private double sellPrice;
	private double discountAmount;
	private String dosageForm;
	private String strength;
	private String volume;
	private double soldPrice;
	private double totalPages;
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
	public String getInventoryGenericName() {
		return inventoryGenericName;
	}
	public void setInventoryGenericName(String inventoryGenericName) {
		this.inventoryGenericName = inventoryGenericName;
	}
	public String getInventoryType() {
		return inventoryType;
	}
	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}
	public String getInventoryBrandName() {
		return inventoryBrandName;
	}
	public void setInventoryBrandName(String inventoryBrandName) {
		this.inventoryBrandName = inventoryBrandName;
	}
	public String getMeasuringUnit() {
		return measuringUnit;
	}
	public void setMeasuringUnit(String measuringUnit) {
		this.measuringUnit = measuringUnit;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public long getInventoryTransactionDetailId() {
		return inventoryTransactionDetailId;
	}
	public void setInventoryTransactionDetailId(long inventoryTransactionDetailId) {
		this.inventoryTransactionDetailId = inventoryTransactionDetailId;
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
	public String getDosageForm() {
		return dosageForm;
	}
	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public double getSoldPrice() {
		return soldPrice;
	}
	public void setSoldPrice(double soldPrice) {
		this.soldPrice = soldPrice;
	}
	public double getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(double totalPages) {
		this.totalPages = totalPages;
	}
	

	
}
