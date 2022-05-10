package com.api.epharmacy.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.api.epharmacy.ui.model.request.OrderDetailRequestModel;
import com.api.epharmacy.ui.model.request.OrderItemStatusRequestModel;
import com.api.epharmacy.ui.model.request.OrderItemsStatusRequestModel;
import com.api.epharmacy.ui.model.request.OrderStatusInfoRequestModel;
import com.api.epharmacy.ui.model.request.SearchRequestModel;
import com.api.epharmacy.ui.model.request.UploadOrderDocumentRequestModel;
import com.api.epharmacy.ui.model.response.OrderItemStatusResponseModel;
import com.api.epharmacy.ui.model.response.OrderItemsStatusResponseModel;
import com.api.epharmacy.ui.model.response.OrderStatusInfoResponseModel;

public interface OrderItemStatusService {

	OrderStatusInfoResponseModel getOrderItemsStatusByOrderId(long orderId);

	String changeOrderItemsStatus(OrderStatusInfoRequestModel orderStatusInfoRequestModel);

}
