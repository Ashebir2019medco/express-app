package com.api.epharmacy.scheduling.service;

import org.springframework.scheduling.annotation.Scheduled;

public interface ScheduledTasksExecutorService {

	  @Scheduled(cron = "${time-cron-for-order-pre-order-management}")
	  public void orderPreOrderManagementJob() throws InterruptedException;
}
