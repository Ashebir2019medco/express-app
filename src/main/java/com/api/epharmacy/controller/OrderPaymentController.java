package com.api.epharmacy.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.epharmacy.service.OrderPaymentService;
import com.api.epharmacy.ui.model.request.OrderPaymentRequestModel;
import com.api.epharmacy.ui.model.request.PaymentStatusVerificationRequestModel;
import com.api.epharmacy.ui.model.request.UploadOrderDocumentRequestModel;
import com.api.epharmacy.ui.model.response.OrderPaymentResponseModel;

@RestController
@RequestMapping("/order-payment")
public class OrderPaymentController {
	
	@Autowired
	OrderPaymentService orderPaymentService;
	
	@PostMapping
	public OrderPaymentResponseModel payOrder(@RequestBody OrderPaymentRequestModel requestDetail) {
		
		OrderPaymentResponseModel retunValue = orderPaymentService.payOrder(requestDetail);
		return retunValue;
	}
	
	//	@PostMapping
	//	public List<OrderPaymentResponseModel> payOrder(@RequestBody List<OrderPaymentRequestModel> requestDetail) {
	//		
	//		List<OrderPaymentResponseModel> retunValue = orderPaymentService.payOrder(requestDetail);
	//		return retunValue;
	//	}
	
	@PutMapping("/{orderPaymnetId}")
	public OrderPaymentResponseModel updatePaymentOrder(@PathVariable long orderPaymnetId,
	        @RequestBody OrderPaymentRequestModel requestDetail) {
		
		OrderPaymentResponseModel retunValue = orderPaymentService.updatePaymentOrder(orderPaymnetId, requestDetail);
		return retunValue;
	}
	
	@PutMapping("/verify/{orderPaymentVerificationId}")
	public String verifyOrderPayment(@PathVariable long orderPaymentVerificationId,
	        @RequestBody PaymentStatusVerificationRequestModel requestDetail) {
		
		String retunValue = orderPaymentService
		        .verifyOrderPayment(orderPaymentVerificationId, requestDetail);
		
		return retunValue;
	}
	
	@PutMapping("/order-payment-verification/{orderPaymentVerificationId}")
	public String updateOrderPayment(@PathVariable long orderPaymentVerificationId,
	        @ModelAttribute UploadOrderDocumentRequestModel uploadOrderDocument, HttpServletRequest request) {
		
		return orderPaymentService.updateOrderPayment(orderPaymentVerificationId, uploadOrderDocument, request);
	}
	
	@DeleteMapping("/document/{orderDocumentsId}")
	public String deleteDocument(@PathVariable long orderDocumentsId) throws IOException {
		
		
		return orderPaymentService.deleteDocument(orderDocumentsId);
	}

}
