package com.api.epharmacy.ui.model.response;

import java.util.List;

public class OrderPaymentVerificationResponseModel {
	
	private long orderPaymentVerificationId;
	
	private long orderId;
	
	private String status;
	
	private boolean paid;
	
	long[] ordaerPaymentIds;
	
	List<OrderDocumentsResponseModel> orderDocumentsResponseModels;
	
	List<OrderPaymentTransactionResponseModel> orderPaymentTransactionResponseModels;
	
	List<RejectedPaymentReasonResponseModel> rejectedPaymentReasonResponseModels;
	
	public long getOrderPaymentVerificationId() {
		return orderPaymentVerificationId;
	}
	
	public void setOrderPaymentVerificationId(long orderPaymentVerificationId) {
		this.orderPaymentVerificationId = orderPaymentVerificationId;
	}
	
	public long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean isPaid() {
		return paid;
	}
	
	public long[] getOrdaerPaymentIds() {
		return ordaerPaymentIds;
	}

	
	public void setOrdaerPaymentIds(long[] ordaerPaymentIds) {
		this.ordaerPaymentIds = ordaerPaymentIds;
	}
	
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
	public List<OrderDocumentsResponseModel> getOrderDocumentsResponseModels() {
		return orderDocumentsResponseModels;
	}
	
	public void setOrderDocumentsResponseModels(List<OrderDocumentsResponseModel> orderDocumentsResponseModels) {
		this.orderDocumentsResponseModels = orderDocumentsResponseModels;
	}
	
	public List<OrderPaymentTransactionResponseModel> getOrderPaymentTransactionResponseModels() {
		return orderPaymentTransactionResponseModels;
	}
	
	public void setOrderPaymentTransactionResponseModels(
	        List<OrderPaymentTransactionResponseModel> orderPaymentTransactionResponseModels) {
		this.orderPaymentTransactionResponseModels = orderPaymentTransactionResponseModels;
	}
	
	public List<RejectedPaymentReasonResponseModel> getRejectedPaymentReasonResponseModels() {
		return rejectedPaymentReasonResponseModels;
	}
	
	public void setRejectedPaymentReasonResponseModels(
	        List<RejectedPaymentReasonResponseModel> rejectedPaymentReasonResponseModels) {
		this.rejectedPaymentReasonResponseModels = rejectedPaymentReasonResponseModels;
	}
	
}
