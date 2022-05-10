package com.api.epharmacy.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.api.epharmacy.ui.model.request.OrderDetailRequestModel;
import com.api.epharmacy.ui.model.request.OrderItemStatusRequestModel;
import com.api.epharmacy.ui.model.request.SearchRequestModel;
import com.api.epharmacy.ui.model.request.UploadOrderDocumentRequestModel;
import com.api.epharmacy.ui.model.response.OrderDetailResponseModel;

public interface OrderService {

	OrderDetailResponseModel createOrder(OrderDetailRequestModel orderList);

	OrderDetailResponseModel getOrderByOrderId(long orderId);

	List<OrderDetailResponseModel> getOrders(SearchRequestModel searchDetail, int page, int limit);

	OrderItemStatusRequestModel changeOrderStatus(long orderId, OrderItemStatusRequestModel orderStatus);

	OrderItemStatusRequestModel sellOrders(OrderItemStatusRequestModel sellOrder);

	OrderItemStatusRequestModel changePaymentStatus(long orderId, OrderItemStatusRequestModel paymentStatus);

	List<OrderDetailResponseModel> searchOrders(SearchRequestModel searchkeyDetail, int page, int limit);

	List<OrderDetailResponseModel> getOrdersByPaymentStatus(String paymentStatus, int page, int limit);

	OrderDetailResponseModel getOrdersByOrderNumber(String orderNumber);

	List<OrderDetailResponseModel> getMyOrderHistory(String userId, int page, int limit);
	
	public String uploadOrderDocument(long orderId, UploadOrderDocumentRequestModel uploadedDocument,
	        HttpServletRequest request);
	
	public String getOrderDocument(String fileName);

	OrderItemStatusRequestModel verifyOrderPaymentStatus(long orderId);

	OrderItemStatusRequestModel rejectOrderPaymentStatus(long orderId);

}
