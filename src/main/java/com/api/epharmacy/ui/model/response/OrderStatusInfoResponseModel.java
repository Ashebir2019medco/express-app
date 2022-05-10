package com.api.epharmacy.ui.model.response;

import java.util.List;

import com.api.epharmacy.ui.model.response.OrderItemsStatusResponseModel;

public class OrderStatusInfoResponseModel {

	List<OrderItemsStatusResponseModel> orderItemsStatusInfo;

	public List<OrderItemsStatusResponseModel> getOrderItemsStatusInfo() {
		return orderItemsStatusInfo;
	}

	public void setOrderItemsStatusInfo(List<OrderItemsStatusResponseModel> orderItemsStatusInfo) {
		this.orderItemsStatusInfo = orderItemsStatusInfo;
	}


}
