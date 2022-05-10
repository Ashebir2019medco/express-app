package com.api.epharmacy.ui.model.response;

public class TransactionHistoryResponse {
	
	private long inventoryTransactionDetailId;
	private long inventoryId;
	private String inventoryGenericName;
	private String inventoryType;
	private String inventoryBrandName;
	private String dosageForm;
	private String strength;
	private String volume;
	private String measuringUnit;
	private String transactionType;
	private double quantity;
	private double costOfInventory;
	private double soldPrice;
	private double discountAmount;
	private int totalPages;
	
	
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
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
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public long getInventoryTransactionDetailId() {
		return inventoryTransactionDetailId;
	}
	public void setInventoryTransactionDetailId(long inventoryTransactionDetailId) {
		this.inventoryTransactionDetailId = inventoryTransactionDetailId;
	}
	public long getInventoryId() {
		return inventoryId;
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
	public double getSoldPrice() {
		return soldPrice;
	}
	public void setSoldPrice(double soldPrice) {
		this.soldPrice = soldPrice;
	}
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	
}
