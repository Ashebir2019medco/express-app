package com.api.epharmacy.io.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name="inventoryTransactionDetail")
public class InventoryTransactionDetailEntity implements Serializable {

	private static final long serialVersionUID = 4549899596811967141L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long inventoryTransactionDetailId;
	
	@Column(nullable = false)
	private long inventoryId;
	
	@Column(nullable = false, length=20)
	private String transactionType;
	
	@Column(nullable = false)
	private double quantity;
	
	@Column(nullable = false)
	private double costOfInventory;
	
	@Column(nullable = false)
	private double soldPrice;
	
	@Column(nullable = true)
	private double discountAmount;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date transactionTime;

	public long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public long getInventoryTransactionDetailId() {
		return inventoryTransactionDetailId;
	}

	public void setInventoryTransactionDetailId(long inventoryTransactionDetailId) {
		this.inventoryTransactionDetailId = inventoryTransactionDetailId;
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

	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
	
	
}
