package com.api.epharmacy.io.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.epharmacy.model.audit.Audit;

@Entity(name="inventory_sell_price_list")
public class InventorySellPriceListEntity extends Audit {

	private static final long serialVersionUID = 4431063383834203223L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long inventorySellPriceListId;
	
	@Column(nullable = false)
	private long inventoryId;
	
	@Column(nullable = false)
	private double sellPrice;
																
	@Column(nullable = false)
	private double discountAmount;

	@Column
	private Instant effectiveDateTime;

	private long nowEffectiveDateTimeDifference;
	
	public long getNowEffectiveDateTimeDifference() {
		return nowEffectiveDateTimeDifference;
	}

	public void setNowEffectiveDateTimeDifference(long nowEffectiveDateTimeDifference) {
		this.nowEffectiveDateTimeDifference = nowEffectiveDateTimeDifference;
	}

	public long getInventorySellPriceListId() {
		return inventorySellPriceListId;
	}

	public void setInventorySellPriceListId(long inventorySellPriceListId) {
		this.inventorySellPriceListId = inventorySellPriceListId;
	}

	public long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(long inventoryId) {
		this.inventoryId = inventoryId;
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

	public Instant getEffectiveDateTime() {
		return effectiveDateTime;
	}

	public void setEffectiveDateTime(Instant effectiveDateTime) {
		this.effectiveDateTime = effectiveDateTime;
	}
	
}
