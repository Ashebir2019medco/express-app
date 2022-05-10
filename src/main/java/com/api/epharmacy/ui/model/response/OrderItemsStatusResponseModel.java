package com.api.epharmacy.ui.model.response;

import java.util.List;


public class OrderItemsStatusResponseModel {

	private long orderItemId;

	List<OrderItemStatusResponseModel> statuses;

	public long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public List<OrderItemStatusResponseModel> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<OrderItemStatusResponseModel> statuses) {
		this.statuses = statuses;
	}



}
