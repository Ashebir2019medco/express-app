package com.api.epharmacy.io.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.api.epharmacy.model.audit.Audit;

@Entity(name = "order_documents")
public class OrderPaymentDocumentsEntity extends Audit {
	
	private static final long serialVersionUID = 5037377443545177244L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderDocumentsId;
	
	@Column
	private long orderPaymentVerificationId;
	
	@Column
	private String uploadedDocument;
	
	@Column
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadedDate;
	
	public long getOrderDocumentsId() {
		return orderDocumentsId;
	}
	
	public void setOrderDocumentsId(long orderDocumentsId) {
		this.orderDocumentsId = orderDocumentsId;
	}
	
	public long getOrderPaymentVerificationId() {
		return orderPaymentVerificationId;
	}
	
	public void setOrderPaymentVerificationId(long orderPaymentVerificationId) {
		this.orderPaymentVerificationId = orderPaymentVerificationId;
	}
	
	public String getUploadedDocument() {
		return uploadedDocument;
	}
	
	public void setUploadedDocument(String uploadedDocument) {
		this.uploadedDocument = uploadedDocument;
	}
	
	public Date getUploadedDate() {
		return uploadedDate;
	}
	
	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}
	
}
