package com.api.epharmacy.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.epharmacy.service.OrderItemStatusService;
import com.api.epharmacy.service.OrderService;
import com.api.epharmacy.shared.dto.UserDto;
import com.api.epharmacy.ui.model.request.InventoryRequestModel;
import com.api.epharmacy.ui.model.request.OrderDetailRequestModel;
import com.api.epharmacy.ui.model.request.OrderItemsStatusRequestModel;
import com.api.epharmacy.ui.model.request.OrderStatusInfoRequestModel;
import com.api.epharmacy.ui.model.request.SearchRequestModel;
import com.api.epharmacy.ui.model.request.UploadOrderDocumentRequestModel;
import com.api.epharmacy.ui.model.request.UserDetailRequestModel;
import com.api.epharmacy.ui.model.response.OrderItemsStatusResponseModel;
import com.api.epharmacy.ui.model.response.OrderStatusInfoResponseModel;
import com.api.epharmacy.ui.model.response.UserRest;

@RestController
@RequestMapping("/order-item-status")
public class OrderItemStatusController {
	
	@Autowired
	OrderItemStatusService orderItemStatusService;

	@GetMapping(path="/{orderId}")
	public OrderStatusInfoResponseModel getOrderItemsStatusByOrderId(@PathVariable long orderId) {

		OrderStatusInfoResponseModel returnValue = orderItemStatusService.getOrderItemsStatusByOrderId(orderId);
		return returnValue;
	}
	
	@PostMapping
	public String changeOrderItemsStatus(@RequestBody OrderStatusInfoRequestModel orderStatusInfoRequestModel) {
		
		String returnValue = orderItemStatusService.changeOrderItemsStatus(orderStatusInfoRequestModel);
		
		return returnValue;
	}
}
