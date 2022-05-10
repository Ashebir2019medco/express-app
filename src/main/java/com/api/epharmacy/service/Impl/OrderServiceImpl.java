package com.api.epharmacy.service.Impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.epharmacy.exception.AppException;
import com.api.epharmacy.io.entity.CompanyEntity;
import com.api.epharmacy.io.entity.InventoryCostPriceListEntity;
import com.api.epharmacy.io.entity.InventoryEntity;
import com.api.epharmacy.io.entity.InventorySellPriceListEntity;
import com.api.epharmacy.io.entity.InventoryTransactionDetailEntity;
import com.api.epharmacy.io.entity.OrderEntity;
import com.api.epharmacy.io.entity.OrderItemEntity;
import com.api.epharmacy.io.entity.OrderItemStatusEntity;
import com.api.epharmacy.io.entity.OrderPaymentDocumentsEntity;
import com.api.epharmacy.io.entity.OrderPaymentEntity;
import com.api.epharmacy.io.entity.OrderPaymentOrderPaymentVerificationEntity;
import com.api.epharmacy.io.entity.OrderPaymentTransactionEntity;
import com.api.epharmacy.io.entity.OrderPaymentVerificationEntity;
import com.api.epharmacy.io.entity.OrderStatusTypeEntity;
import com.api.epharmacy.io.entity.OrderTypeEntity;
import com.api.epharmacy.io.entity.OrderTypeOrderStatusTypeEntity;
import com.api.epharmacy.io.entity.RejectedPaymentReasonEntity;
import com.api.epharmacy.io.entity.StockSiteEntity;
import com.api.epharmacy.io.entity.UserEntity;
import com.api.epharmacy.io.repositories.CompanyRepository;
import com.api.epharmacy.io.repositories.InventoryCostPriceListRepository;
import com.api.epharmacy.io.repositories.InventoryRepository;
import com.api.epharmacy.io.repositories.InventorySellPriceListRepository;
import com.api.epharmacy.io.repositories.InventoryTransactionDetailRepository;
import com.api.epharmacy.io.repositories.OrderDocumentsRepository;
import com.api.epharmacy.io.repositories.OrderItemRepository;
import com.api.epharmacy.io.repositories.OrderItemStatusRepository;
import com.api.epharmacy.io.repositories.OrderPaymentOrderPaymentVerificationRepository;
import com.api.epharmacy.io.repositories.OrderPaymentRepository;
import com.api.epharmacy.io.repositories.OrderPaymentTransactionRepository;
import com.api.epharmacy.io.repositories.OrderPaymentVerificationRepository;
import com.api.epharmacy.io.repositories.OrderRepository;
import com.api.epharmacy.io.repositories.OrderStatusTypeRepository;
import com.api.epharmacy.io.repositories.OrderTypeOrderStatusTypeRepository;
import com.api.epharmacy.io.repositories.OrderTypeRepository;
import com.api.epharmacy.io.repositories.RejectedPaymentRepository;
import com.api.epharmacy.io.repositories.StockSiteRepository;
import com.api.epharmacy.io.repositories.UserRepository;
import com.api.epharmacy.security.JwtTokenProvider;
import com.api.epharmacy.service.OrderService;
import com.api.epharmacy.service.UserNotificationsService;
import com.api.epharmacy.shared.GenerateRandomString;
import com.api.epharmacy.ui.model.request.OrderDetailRequestModel;
import com.api.epharmacy.ui.model.request.OrderItemRequestModel;
import com.api.epharmacy.ui.model.request.OrderItemStatusRequestModel;
import com.api.epharmacy.ui.model.request.SearchRequestModel;
import com.api.epharmacy.ui.model.request.UploadOrderDocumentRequestModel;
import com.api.epharmacy.ui.model.request.UserNotificationsRequestModel;
import com.api.epharmacy.ui.model.response.OrderDetailResponseModel;
import com.api.epharmacy.ui.model.response.OrderDocumentsResponseModel;
import com.api.epharmacy.ui.model.response.OrderItemResponseModel;
import com.api.epharmacy.ui.model.response.OrderItemStatusResponseModel;
import com.api.epharmacy.ui.model.response.OrderPaymentResponseModel;
import com.api.epharmacy.ui.model.response.OrderPaymentTransactionResponseModel;
import com.api.epharmacy.ui.model.response.OrderPaymentVerificationResponseModel;
import com.api.epharmacy.ui.model.response.RejectedPaymentReasonResponseModel;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	InventoryRepository inventoryRepository;
	
	@Autowired
	InventoryCostPriceListRepository inventoryCostPriceListRepository;
	
	@Autowired
	InventoryTransactionDetailRepository inventoryTransactionDetailRepository;
	
	@Autowired
	GenerateRandomString generateRandomString;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	StockSiteRepository stockSiteRepository;

	@Autowired
	OrderDocumentsRepository orderDocumentsRepository;
	
	@Autowired
	UserNotificationsService userNotificationsService;

    @Autowired
    private JwtTokenProvider tokenProvider;

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	OrderStatusTypeRepository orderStatusTypeRepository;
	
	@Autowired
	OrderTypeRepository orderTypeRepository;
	
	@Autowired
	OrderItemStatusRepository orderItemStatusRepository;

	@Autowired
	OrderTypeOrderStatusTypeRepository orderTypeOrderStatusTypeRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	OrderPaymentVerificationRepository orderPaymentVerificationRepository;
	
	@Autowired
	OrderPaymentTransactionRepository orderPaymentTransactionRepository;
	
	@Autowired
	RejectedPaymentRepository rejectedPaymentRepository;
	
	@Autowired
	OrderPaymentRepository orderPaymentRepository;
	
	@Autowired
	OrderPaymentOrderPaymentVerificationRepository orderPaymentOrderPaymentVerificationRepository;

	@Autowired
	InventorySellPriceListRepository inventorySellPriceListRepository;
	
	private String rootDirectory = "src";

	private String uploadDir = rootDirectory + "/uploadedOrderDocuments/";

	Logger LOGGER = Logger.getLogger(OrderServiceImpl.class.getName());

	@Override
	public OrderDetailResponseModel createOrder(OrderDetailRequestModel orderDetail) {
		
		OrderDetailResponseModel returnValue = new OrderDetailResponseModel();
		OrderEntity newOrder = new OrderEntity();
		BeanUtils.copyProperties(orderDetail, newOrder);

		List<OrderItemEntity> orderItems = new ArrayList<>();		
	    for(OrderItemRequestModel item : orderDetail.getOrderItems()) {
	    	OrderItemEntity newOrderItemEntity = new OrderItemEntity(); 
	    	BeanUtils.copyProperties(item, newOrderItemEntity); 
			InventoryEntity inventoryEntity=inventoryRepository.findByInventoryId(item.getInventoryId());
	    	if(inventoryEntity.getAvailableQuantity() < item.getOrderQuantity()) {
	    		double unavailableQuantity = item.getOrderQuantity() - inventoryEntity.getAvailableQuantity();
	    		newOrderItemEntity.setOrderQuantity(item.getOrderQuantity() - unavailableQuantity);
	    		newOrderItemEntity.setPreOrderQuantity(item.getPreOrderQuantity() + unavailableQuantity);
	    	}
	    	orderItems.add(newOrderItemEntity);
	    }
		newOrder.setOrderItems(orderItems);
		
		String orderNumber = "";
		boolean orderIdUnique = false;
		while(!orderIdUnique) {
			orderNumber = generateRandomString.generateOrderNumber(7);
			if (orderRepository.findByOrderNumber(orderNumber) == null) {
				orderIdUnique = true;
			}
			
		}
		newOrder.setOrderNumber(orderNumber);
		//		newOrder.setPaymentStatus("Unpaid");
		//		newOrder.setPreOrderpaymentStatus("Unpaid");
		OrderEntity savedOrderEntity = orderRepository.save(newOrder);
		
		OrderTypeOrderStatusTypeEntity leastWeightOrderTypeOrderStatusTypeEntity=orderTypeOrderStatusTypeRepository.findTopByOrderTypeIdAndIsDeletedOrderByWeight(savedOrderEntity.getOrderTypeId(), false);
		int orderStatusTypeId=leastWeightOrderTypeOrderStatusTypeEntity!=null? leastWeightOrderTypeOrderStatusTypeEntity.getOrderStatusTypeId(): null;

		for(OrderItemEntity orderItemEntity: savedOrderEntity.getOrderItems()) {
			OrderItemStatusEntity orderItemStatusEntity=new OrderItemStatusEntity();
			orderItemStatusEntity.setOrderItemId(orderItemEntity.getOrderItemId());
			orderItemStatusEntity.setQuantity(orderItemEntity.getOrderQuantity() + orderItemEntity.getPreOrderQuantity());
			orderItemStatusEntity.setOrderStatusTypeId(orderStatusTypeId);
			orderItemStatusRepository.save(orderItemStatusEntity);
		}
		
		List<OrderItemEntity> orderItemEntities = orderItemRepository.findByOrderId(savedOrderEntity.getOrderId());
		for (OrderItemEntity orderItemEntity : orderItemEntities) {
			InventoryEntity inventoryEntity=inventoryRepository.findByInventoryId(orderItemEntity.getInventoryId());
			inventoryEntity.setAvailableQuantity(inventoryEntity.getAvailableQuantity() - orderItemEntity.getOrderQuantity());
			inventoryRepository.save(inventoryEntity);
			

			InventoryCostPriceListEntity inventoryCostPriceListEntity = inventoryCostPriceListRepository.findTopByInventoryIdAndIsDeletedOrderByInventoryCostPriceListIdDesc(inventoryEntity.getInventoryId(), false);
			InventorySellPriceListEntity inventorySellPriceListEntity = inventorySellPriceListRepository.findTopByInventoryIdAndIsDeletedOrderByInventorySellPriceListIdDesc(orderItemEntity.getInventoryId(), false);
			InventoryTransactionDetailEntity inventoryTransactionDetailEntity=new InventoryTransactionDetailEntity();
			if(inventoryCostPriceListEntity!=null) {
				inventoryTransactionDetailEntity.setCostOfInventory(inventoryCostPriceListEntity.getCostOfInventory());
	    	}
	    	if(inventorySellPriceListEntity!=null) {
	    		inventoryTransactionDetailEntity.setSoldPrice(inventorySellPriceListEntity.getSellPrice());
				inventoryTransactionDetailEntity.setDiscountAmount(inventorySellPriceListEntity.getDiscountAmount());
	    	}

			inventoryTransactionDetailEntity.setTransactionType("Out");
	    	inventoryTransactionDetailRepository.save(inventoryTransactionDetailEntity);
		}
		
		BeanUtils.copyProperties(savedOrderEntity, returnValue);
		
		//		InventoryCostPriceListEntity inventoryCostPriceListEntity = inventoryCostPriceListRepository
		//		        .findTopByInventoryIdOrderByInventoryCostPriceListIdDesc(nventoryEntity.get);
		//		InventorySellPriceListEntity inventorySellPriceListEntity = inventorySellPriceListRepository
		//		        .findTopByInventoryIdOrderByInventorySellPriceListIdDesc();
		//		InventoryTransactionDetailEntity inventoryTransactionDetailEntity = new InventoryTransactionDetailEntity();
		//		if (inventoryCostPriceListEntity != null) {
		//			inventoryTransactionDetailEntity.setCostOfInventory(inventoryCostPriceListEntity.getCostOfInventory());
		//		}
		//		if (inventorySellPriceListEntity != null) {
		//			inventoryTransactionDetailEntity.setSoldPrice(inventorySellPriceListEntity.getSellPrice());
		//			inventoryTransactionDetailEntity.setDiscountAmount(inventorySellPriceListEntity.getDiscountAmount());
		//		}
		//		inventoryTransactionDetailRepository.save(inventoryTransactionDetailEntity);
		return returnValue;
	}

	@Override
	public OrderDetailResponseModel getOrderByOrderId(long orderId) {
		
		OrderDetailResponseModel returnValue = new OrderDetailResponseModel();
		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		if (orderEntity == null)
			throw new AppException("Order not found.");
		
		BeanUtils.copyProperties(orderEntity, returnValue);
		UserEntity userEntity = userRepository.findByUserId(orderEntity.getUserId());
		if(userEntity != null) {
	    	String fullName = userEntity.getFirstName() + " " + userEntity.getLastName();
	    	returnValue.setFullName(fullName);
    	}

    	CompanyEntity companyEntity = companyRepository.findByCompanyId(orderEntity.getCompanyId());
    	if(companyEntity != null) {
    		returnValue.setCompanyName(companyEntity.getCompanyName());
    	}
		
    	StockSiteEntity siteEntity = stockSiteRepository.findBySiteIdAndIsDeleted(orderEntity.getSiteId(), false);
		if (siteEntity != null)
			returnValue.setSiteName(siteEntity.getSiteName());
		
		OrderTypeEntity orderTypeEntity = orderTypeRepository.findByOrderTypeIdAndIsDeleted(orderEntity.getOrderTypeId(), false);
		if (orderTypeEntity != null)
			returnValue.setOrderType(orderTypeEntity.getOrderType());
		List<OrderItemEntity> orderItemEntities = orderItemRepository.findByOrderIdAndIsDeleted(orderId, false);
		List<OrderItemResponseModel> orderItemsResponseModel = new ArrayList<OrderItemResponseModel>();
		for (OrderItemEntity orderItemEntity : orderItemEntities) {
			OrderItemResponseModel orderItemResponseModel = new OrderItemResponseModel();
			BeanUtils.copyProperties(orderItemEntity, orderItemResponseModel);
			InventoryEntity inventoryEntity=inventoryRepository.findByInventoryId(orderItemEntity.getInventoryId());
			if(inventoryEntity!=null)
				orderItemResponseModel.setInventoryGenericName(inventoryEntity.getInventoryGenericName());
			orderItemsResponseModel.add(orderItemResponseModel);
		}
		returnValue.setOrderItems(orderItemsResponseModel);
		
		List<OrderPaymentVerificationResponseModel> orderPaymentVerificationResponseModels = new ArrayList<>();
		List<OrderPaymentVerificationEntity> orderPaymentVerificationEntities = orderPaymentVerificationRepository
		        .findByOrderIdAndIsDeleted(orderId, false);
		
		for (OrderPaymentVerificationEntity orderPaymentVerificationEntity : orderPaymentVerificationEntities) {
			OrderPaymentVerificationResponseModel orderPaymentVerificationResponseModel = new OrderPaymentVerificationResponseModel();
			BeanUtils.copyProperties(orderPaymentVerificationEntity, orderPaymentVerificationResponseModel);
			
			
			List<OrderPaymentOrderPaymentVerificationEntity> orderPaymentOrderPaymentVerificationEntities = orderPaymentOrderPaymentVerificationRepository.
					findByOrderPaymentVerificationIdAndIsDeleted(orderPaymentVerificationEntity.getOrderPaymentVerificationId(), false);
			if (orderPaymentOrderPaymentVerificationEntities.size() > 0)
				orderPaymentVerificationResponseModel.setPaid(true);
			else
				orderPaymentVerificationResponseModel.setPaid(false);
			List<OrderPaymentTransactionResponseModel> orderPaymentTransactionResponseModels = new ArrayList<>();
			List<OrderPaymentTransactionEntity> orderPaymentTransactionEntities = orderPaymentTransactionRepository
			        .findByOrderPaymentVerificationIdAndIsDeleted(
			            orderPaymentVerificationEntity.getOrderPaymentVerificationId(), false);
			for (OrderPaymentTransactionEntity orderPaymentTransactionEntity : orderPaymentTransactionEntities) {
				
				OrderPaymentTransactionResponseModel orderPaymentTransactionResponseModel = new OrderPaymentTransactionResponseModel();
				BeanUtils.copyProperties(orderPaymentTransactionEntity, orderPaymentTransactionResponseModel);
				orderPaymentTransactionResponseModels.add(orderPaymentTransactionResponseModel);
			}
			
			List<RejectedPaymentReasonResponseModel> rejectedPaymentReasonResponseModels = new ArrayList<>();
			List<RejectedPaymentReasonEntity> rejectedPaymentReasonEntities = rejectedPaymentRepository.findByOrderPaymentVerificationIdAndIsDeleted(
	            orderPaymentVerificationEntity.getOrderPaymentVerificationId(), false);
			for (RejectedPaymentReasonEntity rejectedPaymentReasonEntity : rejectedPaymentReasonEntities) {
				
				RejectedPaymentReasonResponseModel rejectedPaymentReasonResponseModel = new RejectedPaymentReasonResponseModel();
				BeanUtils.copyProperties(rejectedPaymentReasonEntity, rejectedPaymentReasonResponseModel);
				rejectedPaymentReasonResponseModels.add(rejectedPaymentReasonResponseModel);
			}
			
			List<OrderDocumentsResponseModel> orderDocumentsResponseModels = new ArrayList<>();
			List<OrderPaymentDocumentsEntity> orderDocumentsEntities = orderDocumentsRepository
			        .findByOrderPaymentVerificationIdAndIsDeleted(
			            orderPaymentVerificationEntity.getOrderPaymentVerificationId(), false);
			for (OrderPaymentDocumentsEntity orderDocumentsEntity : orderDocumentsEntities) {
				OrderDocumentsResponseModel orderDocumentsResponseModel = new OrderDocumentsResponseModel();
				BeanUtils.copyProperties(orderDocumentsEntity, orderDocumentsResponseModel);
				orderDocumentsResponseModels.add(orderDocumentsResponseModel);
			}
			
			orderPaymentVerificationResponseModel
			        .setOrderPaymentTransactionResponseModels(orderPaymentTransactionResponseModels);
			orderPaymentVerificationResponseModel
			        .setRejectedPaymentReasonResponseModels(rejectedPaymentReasonResponseModels);
			
			orderPaymentVerificationResponseModels.add(orderPaymentVerificationResponseModel);
			orderPaymentVerificationResponseModel.setOrderDocumentsResponseModels(orderDocumentsResponseModels);
			
			List<OrderPaymentOrderPaymentVerificationEntity> orderPaymentOrderPaymentVerificationEntities1 = orderPaymentOrderPaymentVerificationRepository
			        .findByOrderPaymentVerificationIdAndIsDeleted(
			            orderPaymentVerificationEntity.getOrderPaymentVerificationId(), false);
			
			long[] orderPaymetIds = new long[orderPaymentOrderPaymentVerificationEntities1.size()];
			int index = 0;
			for (OrderPaymentOrderPaymentVerificationEntity orderPaymentOrderPaymentVerificationEntity : orderPaymentOrderPaymentVerificationEntities1) {
				orderPaymetIds[index++] = orderPaymentOrderPaymentVerificationEntity.getOrderPaymentId();
			}
			orderPaymentVerificationResponseModel.setOrdaerPaymentIds(orderPaymetIds);
			

		}
		
		List<OrderPaymentResponseModel> orderPaymentResponseModels = new ArrayList<>();
		List<OrderPaymentEntity> orderPaymentEntities = orderPaymentRepository.findByOrderIdAndIsDeleted(orderId, false);
		
		for (OrderPaymentEntity orderPaymentEntity : orderPaymentEntities) {
			OrderPaymentResponseModel orderPaymentResponseModel = new OrderPaymentResponseModel();
			BeanUtils.copyProperties(orderPaymentEntity, orderPaymentResponseModel);
			orderPaymentResponseModels.add(orderPaymentResponseModel);
		}
		
		returnValue.setOrderPaymentVerificationResponseModel(orderPaymentVerificationResponseModels);
		returnValue.setOrderPaymentResponseModels(orderPaymentResponseModels);
		
		returnValue.setCreatedDateTime(Instant.now());
		return returnValue;
	}

	@Override
	public List<OrderDetailResponseModel> getOrders(SearchRequestModel searchDetail, int page, int limit) {
		List<OrderDetailResponseModel> returnValue = new ArrayList<>();
	    
	    if(page > 0) page = page - 1;

		String fromDate = searchDetail.getFromDate().trim().toLowerCase();
		String toDate = searchDetail.getToDate().trim().toLowerCase();
		Integer orderStatusTypeId = searchDetail.getOrderStatusTypeId();
	    Instant toDateInstant = null;
		Instant fromDateInstant = null;
		System.out.println("T0-orderStatusTypeId: "+orderStatusTypeId);

		if (!fromDate.equals("")) {
			LocalDate startDateInLocal = LocalDate.parse(fromDate.split("\\s+")[0]);
			fromDateInstant = startDateInLocal.atStartOfDay(ZoneId.systemDefault()).toInstant();
		}else {
			LocalDate startDateInLocal = LocalDate.now().minusYears(1000);
			fromDateInstant = startDateInLocal.atStartOfDay(ZoneId.systemDefault()).toInstant();
		}

		if (!toDate.equals("")) {
			LocalDate endDateInLocal = LocalDate.parse(toDate.split("\\s+")[0]);
			toDateInstant = endDateInLocal.atStartOfDay(ZoneId.systemDefault()).toInstant();
		}else {
			LocalDate endDateInLocal = LocalDate.now().plusYears(1000);
			toDateInstant = endDateInLocal.atStartOfDay(ZoneId.systemDefault()).toInstant();			
		}

		long totalRows = 0;
	    PageRequest.of(page, limit,Sort.by("orderId").descending());
	    
	    Integer[] orderTypeIds;
	    if(searchDetail.getOrderTypeId()!=null && searchDetail.getOrderTypeId()!=0)
	    	orderTypeIds=new Integer[]{searchDetail.getOrderTypeId()};
	    else {
	    	List <OrderTypeEntity> orderTypeEntities;
		    if(searchDetail.getOrderStatusTypeId()!=null && searchDetail.getOrderStatusTypeId()!=0) {
		    	List<OrderTypeOrderStatusTypeEntity> orderTypeOrderStatusTypeEntities=orderTypeOrderStatusTypeRepository.findByOrderStatusTypeIdAndIsDeleted(orderStatusTypeId, false);
		    	List<Integer> orderTypeList= orderTypeOrderStatusTypeEntities.stream().map(ot->ot.getOrderTypeId()).collect(Collectors.toList());
		    	Integer[] orderTypeListIds = orderTypeList.toArray(new Integer[orderTypeList.size()]);
		    	orderTypeEntities=orderTypeRepository.findByOrderTypeIdInAndIsDeleted(orderTypeListIds, false);
		    }
		    else
		    	orderTypeEntities=orderTypeRepository.findByIsDeleted(false);
		    
	    	List<Integer> orderTypeIdsList= orderTypeEntities.stream().map(ot->ot.getOrderTypeId()).collect(Collectors.toList());
	    	orderTypeIds = orderTypeIdsList.toArray(new Integer[orderTypeIdsList.size()]);
	    }
	       
	   System.out.println("1-orderTypeIds: "+orderTypeIds.length+" "+orderTypeIds);
/*
	    List<Object> results = entityManager.createQuery(
				"SELECT DISTINCT o.orderId FROM orders o, order_item oi, order_item_status ois WHERE o.orderId=oi.orderId AND oi.orderItemId=ois.orderItemId AND ois.orderStatusTypeId in (:orderStatusTypeIds) AND o.isDeleted=false AND o.createdAt>=:fromDateInstant AND o.createdAt<:toDateInstant ORDER BY o.orderId DESC",
				Object.class).setParameter("fromDateInstant", fromDateInstant).setParameter("toDateInstant", toDateInstant).setParameter("orderStatusTypeIds", Arrays.asList(orderStatusTypeIds))
			.setFirstResult((page) * limit).setMaxResults(limit).getResultList();

		totalRows = (long) entityManager.createQuery(
				"SELECT count(*) FROM orders o, order_item oi, order_item_status ois WHERE o.orderId=oi.orderId AND oi.orderItemId=ois.orderItemId AND ois.orderStatusTypeId in (:orderStatusTypeIds) AND o.isDeleted=false AND o.createdAt>=:fromDateInstant AND o.createdAt<:toDateInstant").setParameter("fromDateInstant", fromDateInstant).setParameter("toDateInstant", toDateInstant).setParameter("orderStatusTypeIds", Arrays.asList(orderStatusTypeIds)).getSingleResult();
*/		
	    List<Object> results = entityManager.createQuery(
				"SELECT DISTINCT o.orderId FROM orders o WHERE o.orderTypeId in (:orderTypeIds) AND o.isDeleted=false AND o.createdAt>=:fromDateInstant AND o.createdAt<:toDateInstant ORDER BY o.orderId DESC",
				Object.class).setParameter("fromDateInstant", fromDateInstant).setParameter("toDateInstant", toDateInstant).setParameter("orderTypeIds", Arrays.asList(orderTypeIds))
			.setFirstResult((page) * limit).setMaxResults(limit).getResultList();

		totalRows = (long) entityManager.createQuery(
				"SELECT count(*) FROM orders o WHERE o.orderTypeId in (:orderTypeIds) AND o.isDeleted=false AND o.createdAt>=:fromDateInstant AND o.createdAt<:toDateInstant").setParameter("fromDateInstant", fromDateInstant).setParameter("toDateInstant", toDateInstant).setParameter("orderTypeIds", Arrays.asList(orderTypeIds)).getSingleResult();
		  
		System.out.println("2-results: "+results.size());

		List<Long> orderIds = new ArrayList<>();		
		for(Object result: results) {
			orderIds.add((Long)result);
		}
	    List<OrderEntity> orderEntities = orderRepository.findByOrderIdInAndIsDeletedOrderByOrderIdDesc(orderIds, false);
		System.out.println("3-orderEntities: "+orderEntities.size());
	    List<OrderEntity> filteredOrderEntities;
	    if(searchDetail.getOrderStatusTypeId() == null || searchDetail.getOrderStatusTypeId() == 0)
	    	filteredOrderEntities= orderEntities;
	    else {
		    filteredOrderEntities = orderEntities.stream().filter(o->{
		    	List<OrderTypeOrderStatusTypeEntity> orderTypeOrderStatusTypeEntities;
			    if(searchDetail.getOrderStatusTypeId()!=null && searchDetail.getOrderStatusTypeId()!=0) {
			    	OrderTypeOrderStatusTypeEntity orderTypeOrderStatusTypeEntity=orderTypeOrderStatusTypeRepository.findTopByOrderTypeIdAndOrderStatusTypeIdAndIsDeleted(o.getOrderTypeId(), searchDetail.getOrderStatusTypeId(), false);
		    		orderTypeOrderStatusTypeEntities=orderTypeOrderStatusTypeRepository.findByOrderTypeIdAndWeightLessThanEqualAndIsDeletedOrderByWeight(o.getOrderTypeId(), orderTypeOrderStatusTypeEntity.getWeight(), false);
		    		System.out.println("T1-orderTypeOrderStatusTypeEntities: "+orderTypeOrderStatusTypeEntities.size());
			    }
	    		else
	    			orderTypeOrderStatusTypeEntities=orderTypeOrderStatusTypeRepository.findByOrderTypeIdAndIsDeletedOrderByWeight(o.getOrderTypeId(), false);

		    	List<Integer> orderStatusTypeList= orderTypeOrderStatusTypeEntities.stream().map(ot->ot.getOrderStatusTypeId()).collect(Collectors.toList());
		    	Integer[] orderStatusTypeIds = orderStatusTypeList.toArray(new Integer[orderStatusTypeList.size()]);
	    		System.out.println("T2-orderStatusTypeIds: "+orderStatusTypeIds.length);

	    		
		    	List<OrderItemEntity> validOrderItemEntities=orderItemRepository.findByOrderId(o.getOrderId()).stream().filter(oi->{
		    		List<OrderItemStatusEntity> orderItemStatusEntities=orderItemStatusRepository.findByOrderItemIdAndOrderStatusTypeIdInAndQuantityGreaterThanAndIsDeleted(oi.getOrderItemId(), orderStatusTypeIds, 0.0, false);
		    		OrderItemStatusEntity orderItemStatusEntity=null;
		    		int minWeight=Integer.MAX_VALUE;
		    		for(OrderItemStatusEntity orderItemStatus: orderItemStatusEntities) {
		    			OrderTypeOrderStatusTypeEntity orderTypeOrderStatusTypeEntity=orderTypeOrderStatusTypeRepository.findByOrderTypeIdAndOrderStatusTypeIdAndIsDeleted(o.getOrderTypeId(), orderItemStatus.getOrderStatusTypeId(), false);
		    			if(minWeight > orderTypeOrderStatusTypeEntity.getWeight()) {
		    				minWeight=orderTypeOrderStatusTypeEntity.getWeight();
		    				orderItemStatusEntity=orderItemStatus;
		    			}
	
		    		}
		    		if(orderItemStatusEntity!=null) {
				    	if(searchDetail.getOrderStatusTypeId()!=null && searchDetail.getOrderStatusTypeId()!=0) {
			    			if(orderItemStatusEntity.getOrderStatusTypeId()==searchDetail.getOrderStatusTypeId()) {
					    		System.out.println("latest2 orderItemStatusEntity: "+orderItemStatusEntity.getOrderItemStatusId());
			    				return true;
			    			}
			    			else
			    				return false;
			    		}else {
			    			return true;
			    		}
				    	/*OrderStatusTypeEntity orderStatusTypeEntity=orderStatusTypeRepository.findByOrderStatusTypeIdAndIsDeleted(orderStatusTypeId, false);
				    	o.setOrderStatus(orderStatusTypeEntity.getOrderStatusType());
		    			*/
		    		}
		    		else
		    			return false;
		    	}).collect(Collectors.toList());
	    		System.out.println("T4-validOrderItemEntities: "+validOrderItemEntities.size());
		    	//List<OrderItemStatusEntity> orderDetailStatusEntities=orderItemStatusRepository.findByOrderItemIdInAndOrderStatusTypeId(orderItemIds, orderStatusTypeId);
		    	return o.getOrderItems().size() == validOrderItemEntities.size();
		    }).collect(Collectors.toList());
	    }
	    //filteredOrderEntities=filteredOrderEntities.stream().sorted((o1, o2) -> o1.getOrderId() > o2.getOrderId()? 1: (o1.getOrderId() == o2.getOrderId()? 0: -1)).collect(Collectors.toList());
		System.out.println("filteredOrderEntities::::::: "+filteredOrderEntities.get(0).getOrderNumber());
		System.out.println("4-filteredOrderEntities: "+filteredOrderEntities.size());

	    for(OrderEntity orderEntity : filteredOrderEntities) {
	    	OrderDetailResponseModel orderDetailResponseModel = new OrderDetailResponseModel();
	    	BeanUtils.copyProperties(orderEntity, orderDetailResponseModel);
	    	
	    	UserEntity userEntity = userRepository.findByUserId(orderEntity.getUserId());
	    	if(userEntity != null) {
		    	String fullName = userEntity.getFirstName() + " " + userEntity.getLastName();
		    	orderDetailResponseModel.setFullName(fullName);
	    	}
	    	CompanyEntity companyEntity = companyRepository.findByCompanyId(orderEntity.getCompanyId());
	    	if(companyEntity != null) {
	    		orderDetailResponseModel.setCompanyName(companyEntity.getCompanyName());
	    	}
	    	if(returnValue.size() == 0) {
	    		orderDetailResponseModel.setTotalPages((int) Math.floor(totalRows / limit) + 1);
	    	}

			StockSiteEntity siteEntity = stockSiteRepository.findBySiteIdAndIsDeleted(orderEntity.getSiteId(), false);
			if (siteEntity != null)
				orderDetailResponseModel.setSiteName(siteEntity.getSiteName());
			
			new ArrayList<>();
			for(OrderItemEntity orderItemEnity: orderEntity.getOrderItems()) {
				OrderItemResponseModel orderItemResponseModel=new OrderItemResponseModel();
				BeanUtils.copyProperties(orderItemEnity, orderItemResponseModel);
				
				List<OrderItemStatusEntity> orderItemStatusEntities=orderItemStatusRepository.findByOrderItemIdAndIsDeleted(orderItemEnity.getOrderItemId(), false);
				List<OrderItemStatusResponseModel> orderItemStatusResponseModels=new ArrayList<>();
				for(OrderItemStatusEntity orderItemStatusEntity: orderItemStatusEntities) {
					OrderItemStatusResponseModel orderItemStatusResponseModel=new OrderItemStatusResponseModel();
					BeanUtils.copyProperties(orderItemStatusEntity, orderItemStatusResponseModel);
					
					OrderStatusTypeEntity orderStatusTypeEntity=orderStatusTypeRepository.findByOrderStatusTypeIdAndIsDeleted(orderItemStatusEntity.getOrderStatusTypeId(), false);
					if(orderStatusTypeEntity!=null)
						orderItemStatusResponseModel.setOrderStatusType(orderStatusTypeEntity.getOrderStatusType());
					orderItemStatusResponseModels.add(orderItemStatusResponseModel);
				}
			}
			
			OrderTypeEntity orderTypeEntity = orderTypeRepository.findByOrderTypeIdAndIsDeleted(orderEntity.getOrderTypeId(), false);
			if (orderTypeEntity != null)
				orderDetailResponseModel.setOrderType(orderTypeEntity.getOrderType());

	    	returnValue.add(orderDetailResponseModel);
	    }
	    
		return returnValue;
	}


	@Override
	public OrderItemStatusRequestModel changeOrderStatus(long orderId, OrderItemStatusRequestModel orderStatus) {
		
		OrderItemStatusRequestModel returnValue = new OrderItemStatusRequestModel();
		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		
		if(orderEntity == null) 
			throw new AppException("Order not found.");
		//		if(!"".equals(orderStatus.getOrderStatus()) && orderStatus.getOrderStatus()!=null) 
		//		     orderEntity.setOrderStatus(orderStatus.getOrderStatus());	
		//		else if(!"".equals(orderStatus.getPreOrderStatus()) && orderStatus.getPreOrderStatus()!=null)
		//			orderEntity.setPreOrderStatus(orderStatus.getPreOrderStatus());
		OrderEntity updatesOrderEntity = orderRepository.save(orderEntity);
		
		BeanUtils.copyProperties(updatesOrderEntity, returnValue); 
		return returnValue;
	}

	@Override
	public OrderItemStatusRequestModel sellOrders(OrderItemStatusRequestModel sellOrder) {
		OrderItemStatusRequestModel returnValue = new OrderItemStatusRequestModel();
		//		OrderEntity orderEntity = (OrderEntity) orderRepository.findByOrderNumber(sellOrder.getOrderNumber());
		//		if(orderEntity == null) throw new RuntimeException("Order not found.");
		//		
		//		long orderId = orderEntity.getOrderId();
		//		List<OrderDetailEntity> orderDetails = orderDetailRepository.findByOrderId(orderId);
		//		int count = 0;
		//		String transactionType = "Out";
		//		for(OrderDetailEntity orderDetail : orderDetails) {
		//			
		//			//			float soldQuantity = orderDetail.getOrderedQuantity();
		//			InventoryEntity inventoryEntity = inventoryRepository.findByInventoryId(orderDetail.getInventoryId());
		//			
		//			float availableQty = inventoryEntity.getAvailableQuantity();
		//			float updatedQty = availableQty - soldQuantity;
		//			//edited
		//			if (updatedQty > 0)
		//				inventoryEntity.setAvailableQuantity(updatedQty);
		//			else {
		//				inventoryEntity.setAvailableQuantity(0);
		//			}
		//			inventoryRepository.save(inventoryEntity);
		//			
		//						List<InventoryPriceListEntity> priceLists = inventoryPriceListRepository.findByInventoryId(inventoryEntity.getInventoryId(),Sort.by("inventoryPriceListId").ascending());
		//						outerLoop:
		//						for(InventoryPriceListEntity priceList : priceLists) {
		//							count ++;
		//							if(soldQuantity < priceList.getQuantity()) {
		//								float updatedSoldQuantity = priceList.getQuantity() - soldQuantity;
		//								priceList.setQuantity(updatedSoldQuantity);
		//								inventoryPriceListRepository.save(priceList);
		//								break outerLoop;
		//							}else if(soldQuantity > priceList.getQuantity()){
		//								soldQuantity = 0;
		//								
		//								inventoryPriceListRepository.delete(priceList);
		//							}else{
		//								inventoryPriceListRepository.delete(priceList);
		//								break outerLoop;
		//							}
		//							
		//					    }
		//			
		//			InventoryTransactionDetailEntity transactionDetail = new InventoryTransactionDetailEntity();
		//			transactionDetail.setTransactionType(transactionType);
		//			transactionDetail.setSoldPrice(orderDetail.getUnitPrice());
		//			transactionDetail.setQuantity(soldQuantity);
		//			BeanUtils.copyProperties(orderDetail, transactionDetail);
		//			
		//			
		//			inventoryTransactionDetailRepository.save(transactionDetail);
		//		}
		
		//		if(count > 0) {
		//			if(!"".equals(sellOrder.getOrderStatus())&&sellOrder.getOrderStatus()!=null) {
		//				orderEntity.setOrderStatus(sellOrder.getOrderStatus());
		//				orderEntity.setPaymentStatus(sellOrder.getPaymentStatus());
		//			}
		//		   else if(!"".equals(sellOrder.getPreOrderStatus())&&sellOrder.getPreOrderStatus()!=null) {
		//				orderEntity.setPreOrderpaymentStatus(sellOrder.getPreOrderPaymentStatus());
		//				orderEntity.setPreOrderStatus(sellOrder.getPreOrderStatus());
		//		   }
		//			OrderEntity updatedStatus = orderRepository.save(orderEntity);
		//			BeanUtils.copyProperties(updatedStatus, returnValue);
		//		}
		
		return returnValue;
	}

	@Override
	public OrderItemStatusRequestModel changePaymentStatus(long orderId, OrderItemStatusRequestModel paymentStatus) {
		
		OrderItemStatusRequestModel returnValue = new OrderItemStatusRequestModel();
		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		
		if(orderEntity == null) 
			throw new AppException("Order not found.");
		
		//		orderEntity.setPaymentStatus(paymentStatus.getPaymentStatus());	
		OrderEntity updatesOrderEntity = orderRepository.save(orderEntity);
		
		BeanUtils.copyProperties(updatesOrderEntity, returnValue); 
		return returnValue;
	}

	@Override
	public List<OrderDetailResponseModel> searchOrders(SearchRequestModel searchkeyDetail, int page, int limit) {
		
		searchkeyDetail.getSearchKey();
		List<OrderDetailResponseModel> returnValue = new ArrayList<>();
		
//		String queryText = "select address, photo from Address address, Photo photo "
//				+ " where address.personID=photo.personId";
//				List<Object[]> rows = session.createQuery(queryText).list();
//	    
	    if(page > 0) page = page - 1;

		//	    Pageable pageableRequest = PageRequest.of(page, limit,Sort.by("orderId").descending());
		//	    Page<OrderEntity> orderPage = orderRepository.findByOrderStatus(searchKey, pageableRequest);
		//	    int totalPages = orderPage.getTotalPages();
		//	    List<OrderEntity> orderItems = orderPage.getContent();
		//	    for(OrderEntity orderItem : orderItems) {
		//	    	OrderDetailRequestModel orderItemsListRequestModel = new OrderDetailRequestModel();
		//	    	BeanUtils.copyProperties(orderItem, orderItemsListRequestModel);
		//
		//	    	List<PreOrderEntity> preOrderEntity = preOrderRepository.findByOrderId(orderItem.getOrderId());
		//	    	List<PreOrderResponseModel> preOrderResponseModels = new ArrayList<>();
		//	    	for(PreOrderEntity preOrderEntity2:preOrderEntity) {
		//	    		PreOrderResponseModel preOrderResponseModel = new PreOrderResponseModel();
		//	    		BeanUtils.copyProperties(preOrderEntity2, preOrderResponseModel);
		//	    		preOrderResponseModels.add(preOrderResponseModel);
		//	    	}
		//			//	    	orderItemsListRequestModel.setPreOrderInfo(preOrderResponseModels);
		//	    	if(returnValue.size() == 0) {
		//	    		orderItemsListRequestModel.setTotalPages(totalPages);
		//	    	}
		//			
		//			StockSiteEntity siteEntity = stockSiteRepository.findBySiteIdAndIsDeleted(orderItem.getSiteId(), false);
		//			if (siteEntity != null)
		//				orderItemsListRequestModel.setSiteName(siteEntity.getSiteName());
		//			
		//	    	returnValue.add(orderItemsListRequestModel);
		//	    }
	    
		return returnValue;
	}

	@Override
	public List<OrderDetailResponseModel> getOrdersByPaymentStatus(String paymentStatus, int page, int limit) {
		
		List<OrderDetailResponseModel> returnValue = new ArrayList<>();
	    
	    if(page > 0) page = page - 1;

	    PageRequest.of(page, limit,Sort.by("orderId").descending());
	    
		List<Object[]> results;
		if (paymentStatus.equals("PostPaid")) {
			results = entityManager.createQuery(
			    "select o.orderId, o.companyId, o.userId,o.orderNumber, o.deliveryAddress, o.orderDateTime, o.deliveryDateTime,"
			            + " o.totalPrice, o.paymentOption, o.amountTobePaid, o.siteId, o.orderTypeId,"
			            + " c.companyName, u.firstName, u.lastName, ss.siteName, ot.orderType from orders o"
			            + " left join company c on c.companyId=o.companyId left join users u on u.userId=o.userId left join stock_site"
			            + " ss on ss.siteId=o.siteId left join order_type ot on ot.orderTypeId=o.orderTypeId"
			            + " WHERE (SELECT count(*)"
			            + " from order_payment WHERE orderId=o.orderId)=0 AND o.paymentOption='Post Paid'",
			    Object[].class).setFirstResult((page) * limit).setMaxResults(limit).getResultList();
			entityManager.createQuery(
			    "SELECT count(*) from orders o WHERE o.totalPrice<(SELECT SUM(paidAmount) from order_payment WHERE orderId=o.orderId)",
			    Object[].class).getSingleResult();
	    }
	    
		else if (paymentStatus.equals("Paid")) {
			results = entityManager.createQuery(
			    "select o.orderId, o.companyId, o.userId,o.orderNumber, o.deliveryAddress, o.orderDateTime, o.deliveryDateTime,"
			            + " o.totalPrice, o.paymentOption, o.amountTobePaid, o.siteId, o.orderTypeId,"
			            + " c.companyName, u.firstName, u.lastName, ss.siteName, ot.orderType from orders o"
			            + " left join company c on c.companyId=o.companyId left join users u on u.userId=o.userId left join stock_site"
			            + " ss on ss.siteId=o.siteId left join order_type ot on ot.orderTypeId=o.orderTypeId"
			            + " WHERE o.totalPrice=(SELECT SUM(paidAmount) from order_payment WHERE orderId=o.orderId)",
			    Object[].class).setFirstResult((page) * limit).setMaxResults(limit).getResultList();
			entityManager.createQuery(
			    "SELECT count(*) from orders o WHERE o.totalPrice<(SELECT SUM(paidAmount) from order_payment WHERE orderId=o.orderId)",
			    Object[].class).getSingleResult();
		}
		
		else if (paymentStatus.equals("PartialPaid")) {
			results = entityManager.createQuery(
			    "select o.orderId, o.companyId, o.userId,o.orderNumber, o.deliveryAddress, o.orderDateTime, o.deliveryDateTime,"
			            + " o.totalPrice, o.paymentOption, o.amountTobePaid, o.siteId, o.orderTypeId,"
			            + " c.companyName, u.firstName, u.lastName, ss.siteName, ot.orderType from orders o"
			            + " left join company c on c.companyId=o.companyId left join users u on u.userId=o.userId left join stock_site"
			            + " ss on ss.siteId=o.siteId left join order_type ot on ot.orderTypeId=o.orderTypeId"
			            + " WHERE (SELECT count(*) from order_payment WHERE orderId=o.orderId)>0 AND (o.paymentOption='Post Paid' OR "
			            + "o.paymentOption='Partial Paid')",
			    Object[].class).setFirstResult((page) * limit).setMaxResults(limit).getResultList();
			entityManager.createQuery(
			    "SELECT count(*) from orders o WHERE o.totalPrice<(SELECT SUM(paidAmount) from order_payment WHERE orderId=o.orderId)",
			    Object[].class).getSingleResult();
		}
		
		else if (paymentStatus.equals("UnPaid")) {
			results = entityManager.createQuery(
			    "select o.orderId, o.companyId, o.userId,o.orderNumber, o.deliveryAddress, o.orderDateTime, o.deliveryDateTime,"
			            + " o.totalPrice, o.paymentOption, o.amountTobePaid, o.siteId, o.orderTypeId,"
			            + " c.companyName, u.firstName, u.lastName, ss.siteName, ot.orderType from orders o"
			            + " left join company c on c.companyId=o.companyId left join users u on u.userId=o.userId left join stock_site"
			            + " ss on ss.siteId=o.siteId left join order_type ot on ot.orderTypeId=o.orderTypeId"
			            + " WHERE (SELECT count(*) from order_payment WHERE orderId=o.orderId)=0 AND (o.paymentOption='In Cash' OR "
			            + "o.paymentOption='Partial Paid')",
			    Object[].class).setFirstResult((page) * limit).setMaxResults(limit).getResultList();
			entityManager.createQuery(
			    "SELECT count(*) from orders o WHERE (SELECT count(*) from order_payment"
			            + " WHERE orderId=o.orderId)=0 AND (o.paymentOption='Paid' OR " + "o.paymentOption='Partial Paid')",
			    Object[].class).getSingleResult();
		}
		
		else {
			results = entityManager.createQuery(
			    "select o.orderId, o.companyId, o.userId,o.orderNumber, o.deliveryAddress, o.orderDateTime, o.deliveryDateTime,"
			            + " o.totalPrice, o.paymentOption, o.amountTobePaid, o.siteId, o.orderTypeId,"
			            + " c.companyName, u.firstName, u.lastName, ss.siteName, ot.orderType from orders o"
			            + " left join company c on c.companyId=o.companyId left join users u on u.userId=o.userId left join stock_site"
			            + " ss on ss.siteId=o.siteId left join order_type ot on ot.orderTypeId=o.orderTypeId"
			            + " WHERE (SELECT count(*)"
			            + " from order_payment WHERE orderId=o.orderId)=0 AND o.paymentOption='Post Paid'",
			    Object[].class).setFirstResult((page) * limit).setMaxResults(limit).getResultList();
			entityManager.createQuery(
			    "SELECT count(*) from orders o WHERE o.totalPrice<(SELECT SUM(paidAmount) from order_payment WHERE orderId=o.orderId)",
			    Object[].class).getSingleResult();
		}

	    for(Object[] result: results) {
	    	OrderDetailResponseModel orderDetailResponseModel = new OrderDetailResponseModel();
	    	orderDetailResponseModel.setOrderId((long)result[0]);
			orderDetailResponseModel.setCompanyId((long) result[1]);
			orderDetailResponseModel.setUserId((String) result[2]);
			orderDetailResponseModel.setOrderNumber((String) result[3]);
			orderDetailResponseModel.setDeliveryAddress((String) result[4]);
			orderDetailResponseModel.setOrderDateTime((Instant) result[5]);
			orderDetailResponseModel.setDeliveryDateTime((Instant) result[6]);
			orderDetailResponseModel.setTotalPrice((double) result[7]);
			orderDetailResponseModel.setPaymentOption((String) result[8]);
			orderDetailResponseModel.setAmountTobePaid((double) result[9]);
			orderDetailResponseModel.setSiteId((Integer) result[10]);
			orderDetailResponseModel.setOrderTypeId((Integer) result[11]);
			orderDetailResponseModel.setCompanyName((String) result[12]);
			orderDetailResponseModel.setFullName((String) result[13] + " " + (String) result[14]);
			orderDetailResponseModel.setSiteName((String) result[15]);
			orderDetailResponseModel.setOrderType((String) result[16]);
			returnValue.add(orderDetailResponseModel);
	    	
	    }
	
	return returnValue;
	}

	@Override
	public OrderDetailResponseModel getOrdersByOrderNumber(String orderNumber) {
		
		OrderDetailResponseModel returnValue = new OrderDetailResponseModel();
		
		OrderEntity orderEntity = (OrderEntity) orderRepository.findByOrderNumber(orderNumber);
		
		if(orderEntity == null) throw new AppException("Order not found.");
		
		BeanUtils.copyProperties(orderEntity, returnValue);
		
		UserEntity userEntity = userRepository.findByUserId(orderEntity.getUserId());
		if(userEntity != null) {
	    	String fullName = userEntity.getFirstName() + " " + userEntity.getLastName();
	    	returnValue.setFullName(fullName);
    	}
    	CompanyEntity companyEntity = companyRepository.findByCompanyId(orderEntity.getCompanyId());
    	if(companyEntity != null) {
    		returnValue.setCompanyName(companyEntity.getCompanyName());
    	}
		StockSiteEntity siteEntity = stockSiteRepository.findBySiteIdAndIsDeleted(orderEntity.getSiteId(), false);
		if (siteEntity != null)
			returnValue.setSiteName(siteEntity.getSiteName());

		OrderTypeEntity orderTypeEntity = orderTypeRepository.findByOrderTypeIdAndIsDeleted(orderEntity.getOrderTypeId(), false);
		if (orderTypeEntity != null)
			returnValue.setOrderType(orderTypeEntity.getOrderType());
		
    	return returnValue;
	}

	@Override
	public List<OrderDetailResponseModel> getMyOrderHistory(String userId, int page, int limit) {
		
		List<OrderDetailResponseModel> returnValue = new ArrayList<>();
	    
	    if(page > 0) page = page - 1;

	    Pageable pageableRequest = PageRequest.of(page, limit,Sort.by("orderId").descending());
	    Page<OrderEntity> orderPage = orderRepository.findByUserId(userId, pageableRequest);
	    int totalPages = orderPage.getTotalPages();
	    List<OrderEntity> orderEntities = orderPage.getContent();
	    for(OrderEntity orderEntity : orderEntities) {
	    	OrderDetailResponseModel orderDetailResponseModel = new OrderDetailResponseModel();
	    	BeanUtils.copyProperties(orderEntity, orderDetailResponseModel);
	    	if(returnValue.size() == 0) {
	    		orderDetailResponseModel.setTotalPages(totalPages);
	    	}
			StockSiteEntity siteEntity = stockSiteRepository.findBySiteIdAndIsDeleted(orderEntity.getSiteId(), false);
			if (siteEntity != null)
				orderDetailResponseModel.setSiteName(siteEntity.getSiteName());

	    	List<OrderItemResponseModel> orderItemResponseModels = new ArrayList<>();
	    	for(OrderItemEntity orderItemEntity: orderEntity.getOrderItems()) {
	    		OrderItemResponseModel orderItemResponseModel = new OrderItemResponseModel();
	    		BeanUtils.copyProperties(orderItemEntity, orderItemResponseModel);
				InventoryEntity inventoryEntity = inventoryRepository.findByInventoryIdAndIsDeleted(orderItemEntity.getInventoryId(), false);
	    		if(inventoryEntity!=null)
	    			orderItemResponseModel.setInventoryGenericName(inventoryEntity.getInventoryGenericName());
	    		List<OrderItemStatusEntity> orderItemStatusEntities=orderItemStatusRepository.findByOrderItemIdAndIsDeleted(orderItemEntity.getOrderItemId(), false);
	    		List<OrderItemStatusResponseModel> orderItemStatusResponseModels=new ArrayList<>();
	    		for(OrderItemStatusEntity orderItemStatusEntity: orderItemStatusEntities) {
	    			OrderItemStatusResponseModel orderItemStatusResponseModel=new OrderItemStatusResponseModel();
	    			BeanUtils.copyProperties(orderItemStatusEntity, orderItemStatusResponseModel);
	    			OrderStatusTypeEntity orderStatusTypeEntity=orderStatusTypeRepository.findByOrderStatusTypeIdAndIsDeleted(orderItemStatusEntity.getOrderStatusTypeId(),  false);
	    			if(orderStatusTypeEntity!=null) {
	    				orderItemStatusResponseModel.setOrderStatusType(orderStatusTypeEntity.getOrderStatusType());
	    			}
	    			orderItemStatusResponseModels.add(orderItemStatusResponseModel);
	    		}
	    		orderItemResponseModel.setOrderItemStatusInfo(orderItemStatusResponseModels);
	    		orderItemResponseModels.add(orderItemResponseModel);
	    	}

			OrderTypeEntity orderTypeEntity = orderTypeRepository.findByOrderTypeIdAndIsDeleted(orderEntity.getOrderTypeId(), false);
			if (orderTypeEntity != null)
				orderDetailResponseModel.setOrderType(orderTypeEntity.getOrderType());
			
	    	orderDetailResponseModel.setOrderItems(orderItemResponseModels);
			returnValue.add(orderDetailResponseModel);
		}
		return returnValue;
	}
	
	// Kidane
	@Override
	public String getOrderDocument(String filename) {
		try {
			String documentPath = uploadDir+""+filename;
			if(documentPath==null)
				return "";
			File file = new File(documentPath);/// path : is external directory file and local directory file in server, or
											/// path get of database.

			byte[] fileContent = Files.readAllBytes(file.toPath());
			return Base64.getEncoder().encodeToString(fileContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Document not found");
		}
	}

	public ResponseEntity<byte[]>  downloadPDF(String filename) {
		byte[] contents;
	    try {
	      contents = Files.readAllBytes(Paths.get(uploadDir+""+filename));
	    
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF);
	        // Here you have to set the actual filename of your pdf
	        headers.setContentDispositionFormData(filename, filename);
	        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
	        return response;
	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      throw new AppException("Document not found");
	    }

	  }

	@Override
	public String uploadOrderDocument(long orderId, UploadOrderDocumentRequestModel uploadedDocument, HttpServletRequest request) {
		
		//		2022-04-16_order_2_HINDYA AKMAL AWOL.pdf
		File fileToDelete = new File("uploadDir/2022-04-16_order_2_HINDYA AKMAL AWOL.pdf");
		fileToDelete.delete();
		String returnValue = "Document not uploaded, please upload a valid document";
		String notificationMessage = "Please verify payment receipt!";
		
		OrderPaymentVerificationEntity orderPaymentVerificationEntity = new OrderPaymentVerificationEntity();
		orderPaymentVerificationEntity.setOrderId(orderId);
		orderPaymentVerificationEntity.setStatus("Unverified");
		OrderPaymentVerificationEntity savedDetail = orderPaymentVerificationRepository.save(orderPaymentVerificationEntity);
		
		if (savedDetail == null)
			throw new AppException("Some thing goes wrong!");
		
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
				
				OrderPaymentDocumentsEntity orderDocumentsEntity = new OrderPaymentDocumentsEntity();
				orderDocumentsEntity.setOrderPaymentVerificationId(savedDetail.getOrderPaymentVerificationId());
				orderDocumentsEntity.setUploadedDocument(newFileName);
				OrderPaymentDocumentsEntity savedOrderDocumentsEntity = orderDocumentsRepository.save(orderDocumentsEntity);
				if (savedOrderDocumentsEntity.getUploadedDocument() != null) {
					returnValue = "Your payment is waiting for a verification and we'll notify you shortly!";
				}
				
				Files.write(newPath, bytes);
		}
			
		}catch(Exception e){
			LOGGER.error("Exception: Documnet failed to upload");
		}
		
		for (String transactionId : uploadedDocument.getTransactionID()) {
			OrderPaymentTransactionEntity orderPaymentTransactionEntity = new OrderPaymentTransactionEntity();
			orderPaymentTransactionEntity.setOrderPaymentVerificationId(savedDetail.getOrderPaymentVerificationId());
			orderPaymentTransactionEntity.setTransactionNumber(transactionId);
			orderPaymentTransactionRepository.save(orderPaymentTransactionEntity);
		}
		
		UserEntity userEntity = tokenProvider.getUserByToken(request);
		if(userEntity!=null) {
			UserNotificationsRequestModel userNotificationsRequestModel = new UserNotificationsRequestModel();
			userNotificationsRequestModel.setSenderId(userEntity.getUserId());
			userNotificationsRequestModel.setReceiverId("Finance");
			userNotificationsRequestModel.setMessage(notificationMessage);
			userNotificationsRequestModel.setDetailLink("/pages/order/"+orderId);
			userNotificationsRequestModel.setReceiverIsRole(true);
			userNotificationsService.saveUserNotification(userNotificationsRequestModel);
		}
		return returnValue;
	}

	@Override
	public OrderItemStatusRequestModel verifyOrderPaymentStatus(long orderId) {
		
		OrderItemStatusRequestModel returnValue = new OrderItemStatusRequestModel();
		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		
		if(orderEntity == null) 
			throw new AppException("Order not found.");
		"InCash".equalsIgnoreCase(orderEntity.getPaymentOption());
		orderEntity.getPaymentOption();
		orderEntity.getSiteId();
		//		orderEntity.setPaymentStatus(paymentOption);	
		//		orderEntity.setOrderStatus(orderStatus);	
		OrderEntity updatesOrderEntity = orderRepository.save(orderEntity);
		
		BeanUtils.copyProperties(updatesOrderEntity, returnValue); 
		return returnValue;
	}

	@Override
	public OrderItemStatusRequestModel rejectOrderPaymentStatus(long orderId) {
		
		OrderItemStatusRequestModel returnValue = new OrderItemStatusRequestModel();
		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		
		if(orderEntity == null) 
			throw new AppException("Order not found.");
		//		orderEntity.setPaymentStatus("Rejected");	
		OrderEntity updatesOrderEntity = orderRepository.save(orderEntity);
		
		BeanUtils.copyProperties(updatesOrderEntity, returnValue); 
		return returnValue;
	}

}
