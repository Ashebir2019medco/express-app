package com.api.epharmacy.io.entity;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.springframework.data.annotation.CreatedDate;

import com.api.epharmacy.model.audit.Audit;

@Entity(name="orders")
public class OrderEntity extends Audit {

	private static final long serialVersionUID = -5037377443545177244L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;
	
	@Column(nullable = false)
	private long companyId;

	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private Integer orderTypeId;
	
	@Column(nullable = false,unique = true)
	private String orderNumber;

	@Column(nullable = false)
	private double amountTobePaid;
	
	@Column(nullable = false)
	private double totalPrice;
	
	@Column
	private String deliveryAddress;

	@Column
	private Instant orderDateTime;

	@Column
	private Instant deliveryDateTime;

	@Column
	private String paymentOption;
	@Column
	private Integer siteId;

	@Column
	private String transactionID;
	
	@OneToMany(targetEntity = OrderItemEntity.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "orderId", referencedColumnName = "orderId", nullable = true)
	private List<OrderItemEntity> orderItems;
	
	public double getAmountTobePaid() {
		return amountTobePaid;
	}

	public void setAmountTobePaid(double amountTobePaid) {
		this.amountTobePaid = amountTobePaid;
	}

	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public List<OrderItemEntity> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemEntity> orderItems) {
		this.orderItems = orderItems;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Instant getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(Instant orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public Instant getDeliveryDateTime() {
		return deliveryDateTime;
	}

	public void setDeliveryDateTime(Instant deliveryDateTime) {
		this.deliveryDateTime = deliveryDateTime;
	}

	public Integer getOrderTypeId() {
		return orderTypeId;
	}

	public void setOrderTypeId(Integer orderTypeId) {
		this.orderTypeId = orderTypeId;
	}

	
}
