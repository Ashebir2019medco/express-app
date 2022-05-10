package com.api.epharmacy.ui.model.request;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

public class UserNotificationsRequestModel {

	private String senderId;

	private String receiverId;

	private boolean receiverIsRole;
	
	private String detailLink;
	
	private String message;

	private Instant sentDateTime;
	
	private boolean seen;

	private Instant seenDateTime;

	public String getDetailLink() {
		return detailLink;
	}

	public void setDetailLink(String detailLink) {
		this.detailLink = detailLink;
	}

	public boolean isReceiverIsRole() {
		return receiverIsRole;
	}

	public void setReceiverIsRole(boolean receiverIsRole) {
		this.receiverIsRole = receiverIsRole;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public Instant getSentDateTime() {
		return sentDateTime;
	}

	public void setSentDateTime(Instant sentDateTime) {
		this.sentDateTime = sentDateTime;
	}

	public Instant getSeenDateTime() {
		return seenDateTime;
	}

	public void setSeenDateTime(Instant seenDateTime) {
		this.seenDateTime = seenDateTime;
	}


}
