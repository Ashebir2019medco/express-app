package com.api.epharmacy.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.api.epharmacy.ui.model.request.OrderPaymentRequestModel;
import com.api.epharmacy.ui.model.request.PaymentStatusVerificationRequestModel;
import com.api.epharmacy.ui.model.request.UploadOrderDocumentRequestModel;
import com.api.epharmacy.ui.model.response.OrderPaymentResponseModel;

public interface OrderPaymentService {
	
	OrderPaymentResponseModel payOrder(OrderPaymentRequestModel requestDetail);
	
	//	List<OrderPaymentResponseModel> payOrder(List<OrderPaymentRequestModel> requestDetail);
	
	String verifyOrderPayment(long orderPaymentVerificationId, PaymentStatusVerificationRequestModel requestDetail);
	
	String updateOrderPayment(long orderPaymentVerificationId, UploadOrderDocumentRequestModel uploadOrderDocument,
	        HttpServletRequest request);
	
	OrderPaymentResponseModel updatePaymentOrder(long orderPaymnetId, OrderPaymentRequestModel requestDetail);
	
	String deleteDocument(Long orderDocumentsId) throws IOException;
	
}
