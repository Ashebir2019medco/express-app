package com.api.epharmacy.ui.model.request;

import java.util.List;

public class OrderStatusInfoRequestModel {

	List<OrderItemsStatusRequestModel> orderItemsStatusInfo;

	public List<OrderItemsStatusRequestModel> getOrderItemsStatusInfo() {
		return orderItemsStatusInfo;
	}

	public void setOrderItemsStatusInfo(List<OrderItemsStatusRequestModel> orderItemsStatusInfo) {
		this.orderItemsStatusInfo = orderItemsStatusInfo;
	}

}
