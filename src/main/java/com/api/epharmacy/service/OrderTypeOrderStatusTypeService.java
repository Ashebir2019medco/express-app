package com.api.epharmacy.service;

import java.util.List;

import com.api.epharmacy.ui.model.request.OrderTypeOrderStatusTypeRequestModel;
import com.api.epharmacy.ui.model.response.OrderTypeOrderStatusTypeResponseModel;

public interface OrderTypeOrderStatusTypeService {
	
	OrderTypeOrderStatusTypeResponseModel saveOrderTypeOrderStatusType(OrderTypeOrderStatusTypeRequestModel requestDetail);
	
	OrderTypeOrderStatusTypeResponseModel getOrderTypeOrderStatusType(Integer orderTypeOrderStatusTypeId);
	
	OrderTypeOrderStatusTypeResponseModel updateOrderTypeOrderStatusType(Integer orderTypeOrderStatusTypeId,
	        OrderTypeOrderStatusTypeRequestModel requestDetail);
	
	String deleteOrderTypeOrderStatusType(Integer orderTypeOrderStatusTypeId);
	
	OrderTypeOrderStatusTypeResponseModel filterOrderStatusType(Integer orderTypId);
	
	List<OrderTypeOrderStatusTypeResponseModel> getOrderTypeOrderStatusTypes(int page, int limit, int orderTypeId,
	        int orderStatusTypeId, String searchKey);
	
}
