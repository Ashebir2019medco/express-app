
package com.api.epharmacy.ui.model.request;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.api.epharmacy.ui.model.response.OrderDocumentsResponseModel;
import com.api.epharmacy.ui.model.response.OrderItemResponseModel;
import com.api.epharmacy.ui.model.response.PreOrderResponseModel;

public class OrderDetailRequestModel {

	private long companyId;
	private String companyName;
	private String fullName;
	private String userId;
	private String deliveryAddress;
	private double totalPrice;
	
	private Instant orderDateTime;
	private Instant deliveryDateTime;
	private int totalPages;
	private String paymentOption;

	private double amountTobePaid;

	private Integer siteId;

	private Integer orderTypeId;

	private String siteName;
	private String transactionID;
	List<OrderItemRequestModel> orderItems;
	List<OrderDocumentsResponseModel> orderDocuments;
	
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public String getPaymentOption() {
		return paymentOption;
	}
	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public double getAmountTobePaid() {
		return amountTobePaid;
	}
	public void setAmountTobePaid(double amountTobePaid) {
		this.amountTobePaid = amountTobePaid;
	}
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	
	public List<OrderItemRequestModel> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemRequestModel> orderItems) {
		this.orderItems = orderItems;
	}
	public List<OrderDocumentsResponseModel> getOrderDocuments() {
		return orderDocuments;
	}
	public void setOrderDocuments(List<OrderDocumentsResponseModel> orderDocuments) {
		this.orderDocuments = orderDocuments;
	}
	public Integer getOrderTypeId() {
		return orderTypeId;
	}
	public void setOrderTypeId(Integer orderTypeId) {
		this.orderTypeId = orderTypeId;
	}	
}