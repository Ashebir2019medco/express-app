package com.api.epharmacy.ui.model.response;

public class InventoryListViewResponse {

	private long inventoryId;
	private String inventoryGenericName;
	private String inventoryType;
	private String subCategory;
	private String inventoryBrandName;
	private String dosageForm;
	private String strength;
	private String volume;
	private String measuringUnit;
	
	private double availableQuantity = 0;
	private double sellPrice;
	private double discountAmount;
	private String inventoryImage;
	private int totalPages;
	
	
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
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
	public String getInventoryImage() {
		return inventoryImage;
	}
	public void setInventoryImage(String inventoryImage) {
		this.inventoryImage = inventoryImage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public long getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(long inventoryId) {
		this.inventoryId = inventoryId;
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
	
	public double getAvailableQuantity() {
		return availableQuantity;
	}
	
	public void setAvailableQuantity(double availableQuantity) {
		this.availableQuantity = availableQuantity;
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
