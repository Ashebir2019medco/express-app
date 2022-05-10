package com.api.epharmacy.ui.model.request;

import java.util.List;

public class OrderItemsStatusRequestModel {

	private long orderItemId;

	List<OrderItemStatusRequestModel> statuses;

	public List<OrderItemStatusRequestModel> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<OrderItemStatusRequestModel> statuses) {
		this.statuses = statuses;
	}

	public long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}


}
