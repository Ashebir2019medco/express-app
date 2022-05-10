package com.api.epharmacy.service;

import java.util.List;

import com.api.epharmacy.ui.model.request.OrderStatusTypeRequestModel;
import com.api.epharmacy.ui.model.response.OrderStatusTypeResponseModel;

public interface OrderStatusTypeService {
	
	OrderStatusTypeResponseModel saveOrderStatusType(OrderStatusTypeRequestModel requestDetail);
	
	OrderStatusTypeResponseModel getOrderStatusType(Integer orderStatusTypeId);
	
	List<OrderStatusTypeResponseModel> getOrderStatusTypes(int page, int limit, String searchKey);
	
	OrderStatusTypeResponseModel updateOrderStatusType(Integer orderStatusTypeId, OrderStatusTypeRequestModel requestDetail);
	
	String deleteOrderStatusType(Integer orderTypeId);
	
}
