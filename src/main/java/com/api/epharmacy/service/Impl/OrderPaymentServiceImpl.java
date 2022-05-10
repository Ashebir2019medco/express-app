package com.api.epharmacy.service.Impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.epharmacy.exception.AppException;
import com.api.epharmacy.io.entity.OrderPaymentDocumentsEntity;
import com.api.epharmacy.io.entity.OrderPaymentEntity;
import com.api.epharmacy.io.entity.OrderPaymentOrderPaymentVerificationEntity;
import com.api.epharmacy.io.entity.OrderPaymentTransactionEntity;
import com.api.epharmacy.io.entity.OrderPaymentVerificationEntity;
import com.api.epharmacy.io.entity.RejectedPaymentReasonEntity;
import com.api.epharmacy.io.entity.UserEntity;
import com.api.epharmacy.io.repositories.OrderDocumentsRepository;
import com.api.epharmacy.io.repositories.OrderPaymentOrderPaymentVerificationRepository;
import com.api.epharmacy.io.repositories.OrderPaymentRepository;
import com.api.epharmacy.io.repositories.OrderPaymentTransactionRepository;
import com.api.epharmacy.io.repositories.OrderPaymentVerificationRepository;
import com.api.epharmacy.io.repositories.RejectedPaymentRepository;
import com.api.epharmacy.security.JwtTokenProvider;
import com.api.epharmacy.service.OrderPaymentService;
import com.api.epharmacy.service.UserNotificationsService;
import com.api.epharmacy.ui.model.request.OrderPaymentRequestModel;
import com.api.epharmacy.ui.model.request.PaymentStatusVerificationRequestModel;
import com.api.epharmacy.ui.model.request.UploadOrderDocumentRequestModel;
import com.api.epharmacy.ui.model.request.UserNotificationsRequestModel;
import com.api.epharmacy.ui.model.response.OrderPaymentResponseModel;

@Service
public class OrderPaymentServiceImpl implements OrderPaymentService {
	
	@Autowired
	OrderPaymentRepository orderPaymentRepository;
	
	@Autowired
	OrderPaymentVerificationRepository orderPaymentVerificationRepository;
	
	@Autowired
	RejectedPaymentRepository rejectedPaymentRepository;
	
	@Autowired
	OrderDocumentsRepository orderDocumentsRepository;
	
	@Autowired
	OrderPaymentTransactionRepository orderPaymentTransactionRepository;
	
	@Autowired
	UserNotificationsService userNotificationsService;
	
	@Autowired
	OrderPaymentOrderPaymentVerificationRepository orderPaymentOrderPaymentVerificationRepository;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	private String rootDirectory = "src";
	
	private String uploadDir = rootDirectory + "/uploadedOrderDocuments/";
	
	Logger LOGGER = Logger.getLogger(OrderServiceImpl.class.getName());
	
	@Override
	public OrderPaymentResponseModel payOrder(OrderPaymentRequestModel requestDetail) {
		
		OrderPaymentResponseModel returnValue = new OrderPaymentResponseModel();
		OrderPaymentEntity orderPaymentEntity = new OrderPaymentEntity();
		BeanUtils.copyProperties(requestDetail, orderPaymentEntity);
		OrderPaymentEntity savedDetail = orderPaymentRepository.save(orderPaymentEntity);
		
		if (requestDetail.getOrderPaymentVerificationId() > 0) {
			OrderPaymentOrderPaymentVerificationEntity orderPaymentOrderPaymentVerificationEntity2 = new OrderPaymentOrderPaymentVerificationEntity();
			orderPaymentOrderPaymentVerificationEntity2.setOrderPaymentId(savedDetail.getOrderPaymentId());
			orderPaymentOrderPaymentVerificationEntity2
			        .setOrderPaymentVerificationId(requestDetail.getOrderPaymentVerificationId());
			List<OrderPaymentOrderPaymentVerificationEntity> orderPaymentOrderPaymentVerificationEntities = orderPaymentOrderPaymentVerificationRepository
			        .findByOrderPaymentVerificationIdAndIsDeleted(requestDetail.getOrderPaymentVerificationId(), false);
			
			if (orderPaymentOrderPaymentVerificationEntities.size() == 0)
				orderPaymentOrderPaymentVerificationRepository.save(orderPaymentOrderPaymentVerificationEntity2);
		}
		BeanUtils.copyProperties(savedDetail, returnValue);
		
		//		List<OrderPaymentResponseModel> returnValue = new ArrayList<>();
		//		
		//		for (OrderPaymentRequestModel orderPaymentRequestModel : requestDetail) {
		//			
		//			OrderPaymentEntity orderPaymentEntity = new OrderPaymentEntity();
		//			BeanUtils.copyProperties(orderPaymentRequestModel, orderPaymentEntity);
		//			OrderPaymentEntity savedDetail = orderPaymentRepository.save(orderPaymentEntity);
		//			
		//			if (orderPaymentRequestModel.getOrderPaymentVerificationId() > 0) {
		//				OrderPaymentOrderPaymentVerificationEntity orderPaymentOrderPaymentVerificationEntity2 = new OrderPaymentOrderPaymentVerificationEntity();
		//				orderPaymentOrderPaymentVerificationEntity2.setOrderPaymentId(savedDetail.getOrderPaymentId());
		//				orderPaymentOrderPaymentVerificationEntity2
		//				        .setOrderPaymentVerificationId(orderPaymentRequestModel.getOrderPaymentVerificationId());
		//				List<OrderPaymentOrderPaymentVerificationEntity> orderPaymentOrderPaymentVerificationEntities = orderPaymentOrderPaymentVerificationRepository
		//				        .findByOrderPaymentVerificationIdAndIsDeleted(
		//				            orderPaymentRequestModel.getOrderPaymentVerificationId(), false);
		//				
		//				if (orderPaymentOrderPaymentVerificationEntities.size() == 0)
		//					orderPaymentOrderPaymentVerificationRepository.save(orderPaymentOrderPaymentVerificationEntity2);
		//			}
		//			OrderPaymentResponseModel orderPaymentResponseModel = new OrderPaymentResponseModel();
		//			BeanUtils.copyProperties(savedDetail, orderPaymentResponseModel);
		//			returnValue.add(orderPaymentResponseModel);
		//		}
		
		return returnValue;
	}
	
	@Override
	public String verifyOrderPayment(long orderPaymentVerificationId, PaymentStatusVerificationRequestModel requestDetail) {
		String returnValue = "";
		OrderPaymentVerificationEntity orderPaymentVerificationEntity = orderPaymentVerificationRepository
		        .findByOrderPaymentVerificationIdAndIsDeleted(orderPaymentVerificationId, false);
		if (orderPaymentVerificationEntity == null)
			throw new AppException("Record doesn't exist!");
		
		orderPaymentVerificationEntity.setStatus(requestDetail.getStatus());
		OrderPaymentVerificationEntity savedDetail = orderPaymentVerificationRepository.save(orderPaymentVerificationEntity);
		if (savedDetail != null) {
			if (!requestDetail.getRejectedReason().equals("")) {
				List<RejectedPaymentReasonEntity> rejectedPaymentReasonEntity = rejectedPaymentRepository.findByOrderPaymentVerificationIdAndIsDeleted(orderPaymentVerificationEntity.getOrderPaymentVerificationId(), false);
				if (rejectedPaymentReasonEntity.size() > 0) {
					rejectedPaymentReasonEntity.get(0).setRejectedReason(requestDetail.getRejectedReason());
					rejectedPaymentRepository.save(rejectedPaymentReasonEntity.get(0));
				}
				
				else {
					RejectedPaymentReasonEntity rejectedPaymentReasonEntity2 = new RejectedPaymentReasonEntity();
					BeanUtils.copyProperties(requestDetail, rejectedPaymentReasonEntity2);
					rejectedPaymentRepository.save(rejectedPaymentReasonEntity2);
				}
     			
			}
			returnValue = "Saved successfully!";
			
		}
		else 
			returnValue = "Something goes wrong!";
			
			
		
		return returnValue;
	}
	
	@Override
	public String updateOrderPayment(long orderPaymentVerificationId, UploadOrderDocumentRequestModel uploadedDocument,
	        HttpServletRequest request) {
		String returnValue = "Document not uploaded, please upload a valid document";
		String notificationMessage = "Please verify payment receipt!";
		
		OrderPaymentVerificationEntity orderPaymentVerificationEntity = orderPaymentVerificationRepository
		        .findByOrderPaymentVerificationIdAndIsDeleted(orderPaymentVerificationId, false);
		
		if (orderPaymentVerificationEntity == null)
			throw new AppException("Record doesn't exist!");
		
		try {
			File directory = new File(uploadDir);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			for (MultipartFile file : uploadedDocument.getUploadedDocument()) {
				byte[] bytes = file.getBytes();
				
				String fileName = file.getOriginalFilename();
				(fileName.substring(fileName.lastIndexOf(".") + 1)).toLowerCase();
				String newFileName = LocalDate.now() + "_order_"
				        + orderPaymentVerificationEntity.getOrderPaymentVerificationId() + "_" + fileName;
				String fileFullPathAndName = this.uploadDir + newFileName;
				Path newPath = Paths.get(fileFullPathAndName);
				Files.write(newPath, bytes);
				
				OrderPaymentDocumentsEntity orderDocumentsEntity = new OrderPaymentDocumentsEntity();
				orderDocumentsEntity
				        .setOrderPaymentVerificationId(orderPaymentVerificationEntity.getOrderPaymentVerificationId());
				orderDocumentsEntity.setUploadedDocument(newFileName);
				OrderPaymentDocumentsEntity savedOrderDocumentsEntity = orderDocumentsRepository.save(orderDocumentsEntity);
				if (savedOrderDocumentsEntity.getUploadedDocument() != null) {
					returnValue = "Your payment is waiting for a verification and we'll notify you shortly!";
				}
			}
		}
		catch (Exception e) {
			LOGGER.error("Exception: Documnet failed to upload");
		}
		
		for (String transactionId : uploadedDocument.getTransactionID()) {
			OrderPaymentTransactionEntity orderPaymentTransactionEntity = new OrderPaymentTransactionEntity();
			orderPaymentTransactionEntity
			        .setOrderPaymentVerificationId(orderPaymentVerificationEntity.getOrderPaymentVerificationId());
			orderPaymentTransactionEntity.setTransactionNumber(transactionId);
			orderPaymentTransactionRepository.save(orderPaymentTransactionEntity);
		}
		
		UserEntity userEntity = tokenProvider.getUserByToken(request);
		if (userEntity != null) {
			UserNotificationsRequestModel userNotificationsRequestModel = new UserNotificationsRequestModel();
			userNotificationsRequestModel.setSenderId(userEntity.getUserId());
			userNotificationsRequestModel.setReceiverId("Finance");
			userNotificationsRequestModel.setMessage(notificationMessage);
			userNotificationsRequestModel.setDetailLink("/pages/order/" + orderPaymentVerificationEntity.getOrderId());
			userNotificationsRequestModel.setReceiverIsRole(true);
			userNotificationsService.saveUserNotification(userNotificationsRequestModel);
		}
		return returnValue;
	}
	
	@Override
	public OrderPaymentResponseModel updatePaymentOrder(long orderPaymnetId, OrderPaymentRequestModel requestDetail) {
		
		OrderPaymentResponseModel returnValue = new OrderPaymentResponseModel();
		OrderPaymentEntity orderPaymentEntity = orderPaymentRepository.findByOrderPaymentIdAndIsDeleted(orderPaymnetId,
		    false);
		if (orderPaymentEntity == null)
			throw new AppException("No record with this id!");
		
		orderPaymentEntity.setPaidAmount(requestDetail.getPaidAmount());
		OrderPaymentEntity savedDetail = orderPaymentRepository.save(orderPaymentEntity);
		BeanUtils.copyProperties(savedDetail, returnValue);
		
		return returnValue;
	}
	
	@Override
	public String deleteDocument(Long orderDocumentsId) throws IOException {
		
		String returnValue = "";
		
		OrderPaymentDocumentsEntity orderPaymentDocumentsEntity = orderDocumentsRepository
		        .findByOrderDocumentsIdAndIsDeleted(orderDocumentsId, false);
		if (orderPaymentDocumentsEntity == null)
			throw new AppException("No record with this id!");
		
		orderPaymentDocumentsEntity.setDeleted(true);
		orderDocumentsRepository.save(orderPaymentDocumentsEntity);
		
		String filePath = uploadDir + orderPaymentDocumentsEntity.getUploadedDocument();
		Path path = Paths.get(filePath);
		
		try {
			Files.delete(path);
			returnValue = "Deleted successfully!";
		}
		catch (NoSuchFileException ex) {
			System.out.printf("No such file or directory: %s\n", path);
			returnValue = "No such file or directory: " + path;
		}
		catch (DirectoryNotEmptyException ex) {
			System.out.printf("Directory %s is not empty\n", path);
			returnValue = "Directory" + path + "is not empty";
		}
		catch (IOException ex) {
			System.out.println(ex);
		}
		
		return returnValue;
	}
	
}
