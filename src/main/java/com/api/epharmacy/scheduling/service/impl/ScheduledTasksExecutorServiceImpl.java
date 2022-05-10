package com.api.epharmacy.scheduling.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.api.epharmacy.scheduling.service.ScheduledTasksExecutorService;
import com.api.epharmacy.service.InventoryTransactionService;

@Service
@Transactional
public class ScheduledTasksExecutorServiceImpl implements ScheduledTasksExecutorService {
  static final Logger LOGGER = 
    Logger.getLogger(ScheduledTasksExecutorServiceImpl.class.getName());
  
  @Autowired
  InventoryTransactionService inventoryTransactionService;
  
  @Scheduled(cron = "${time-cron-for-order-pre-order-management}")
  public void orderPreOrderManagementJob() throws InterruptedException {
	LOGGER.info("\nTask for order-pre-order-management started at "+LocalDateTime.now()+"\n");
	inventoryTransactionService.orderPreOrderManagementJob();
	LOGGER.info("\nSUCCESS! Task for order-pre-order-management successfully completed at "+LocalDateTime.now()+"\n");
  }

}