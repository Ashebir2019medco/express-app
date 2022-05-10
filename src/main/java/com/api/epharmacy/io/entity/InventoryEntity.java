package com.api.epharmacy.io.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.epharmacy.model.audit.Audit;

@Entity(name="inventory")
public class InventoryEntity extends Audit {

	private static final long serialVersionUID = -1130814157378815168L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long inventoryId;
	
	@Column(nullable = false, length=250)
	private String inventoryGenericName;
	
	@Column(nullable = false,length=150)
	private String inventoryType;
	
	@Column(nullable = true,length=100)
	private String subCategory;
	
	@Column(length=250)
	private String inventoryBrandName;
	
	@Column(nullable = true,length=100)
	private String dosageForm;
	
	@Column(nullable = true,length=100)
	private String strength;
	
	@Column(nullable = true,length=100)
	private String volume;
	
	@Column(nullable = true,length=50)
	private String measuringUnit;
	
	@Column(nullable = false)
	private double availableQuantity=0;
	
	@Column(nullable = false)
	private double minimumStockQuantity;
	
	@Column(nullable = true,length=100)
	private String inventoryImage;
	
	public String getInventoryImage() {
		return inventoryImage;
	}

	public void setInventoryImage(String inventoryImage) {
		this.inventoryImage = inventoryImage;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
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
	
	
}
