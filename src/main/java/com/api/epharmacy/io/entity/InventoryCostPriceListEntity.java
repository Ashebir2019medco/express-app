package com.api.epharmacy.io.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.epharmacy.model.audit.Audit;

@Entity(name="inventory_cost_price_list")
public class InventoryCostPriceListEntity extends Audit {

	private static final long serialVersionUID = 4431063383834203223L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long inventoryCostPriceListId;
	
	@Column(nullable = false)
	private long inventoryId;
	
	@Column(nullable = false)
	private double quantity;
	
	@Column(nullable = false)
	private double costOfInventory;

	@Column
	private Instant costDateTime;

	public long getInventoryCostPriceListId() {
		return inventoryCostPriceListId;
	}

	public void setInventoryCostPriceListId(long inventoryCostPriceListId) {
		this.inventoryCostPriceListId = inventoryCostPriceListId;
	}

	public long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(long inventoryId) {
		this.inventoryId = inventoryId;
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

	public Instant getCostDateTime() {
		return costDateTime;
	}

	public void setCostDateTime(Instant costDateTime) {
		this.costDateTime = costDateTime;
	}
	
	
	
}
