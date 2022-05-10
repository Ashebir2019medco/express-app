package com.api.epharmacy.ui.model.request;

import org.springframework.web.multipart.MultipartFile;

public class InventoryDetailRequestModel {

	private long inventoryId;
	private String inventoryGenericName;
	private String inventoryType;
	private String subCategory;
	private String inventoryBrandName;
	private String dosageForm;
	private String strength;
	private String volume;
	private String measuringUnit;
	private double availableQuantity;
	private double minimumStockQuantity;
	private MultipartFile  uploadImage;
	private String inventoryImage;
	private int totalPages;
	
	
	public String getInventoryImage() {
		return inventoryImage;
	}
	public void setInventoryImage(String inventoryImage) {
		this.inventoryImage = inventoryImage;
	}
	public MultipartFile getUploadImage() {
		return uploadImage;
	}
	public void setUploadImage(MultipartFile uploadImage) {
		this.uploadImage = uploadImage;
	}
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
	public double getMinimumStockQuantity() {
		return minimumStockQuantity;
	}
	public void setMinimumStockQuantity(double minimumStockQuantity) {
		this.minimumStockQuantity = minimumStockQuantity;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	
}
