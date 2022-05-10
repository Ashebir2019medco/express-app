package com.api.epharmacy.io.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.api.epharmacy.model.audit.Audit;

@Entity(name="user_notifications")
public class UserNotificationsEntity extends Audit {

	private static final long serialVersionUID = -1500349168344396612L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userNotificationsId;

	@Column(nullable = false)
	private String senderId;

	@Column(nullable = false)
	private String receiverId;
	
	@Column(nullable = false)
	private boolean receiverIsRole = false;
	
	@Column
	private String detailLink;

	@Column(nullable = false)
	private String message;

	//@CreatedDate
    private Instant sentDateTime;
	
	@Column(nullable = false)
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

	public long getUserNotificationsId() {
		return userNotificationsId;
	}

	public void setUserNotificationsId(long userNotificationsId) {
		this.userNotificationsId = userNotificationsId;
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
