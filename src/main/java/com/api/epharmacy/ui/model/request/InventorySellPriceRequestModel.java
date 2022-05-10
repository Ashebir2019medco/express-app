package com.api.epharmacy.ui.model.request;

import java.time.Instant;

public class InventorySellPriceRequestModel {
	
	private long inventoryId;
	
	private double sellPrice;
	
	private double discountAmount;
	
	private Instant effectiveDateTime;
	
	private long nowEffectiveDateTimeDifference;
	
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
	
	public long getNowEffectiveDateTimeDifference() {
		return nowEffectiveDateTimeDifference;
	}
	
	public void setNowEffectiveDateTimeDifference(long nowEffectiveDateTimeDifference) {
		this.nowEffectiveDateTimeDifference = nowEffectiveDateTimeDifference;
	}
	
}
