package com.api.epharmacy.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.epharmacy.model.audit.Audit;

@Entity(name = "stock_site")
public class StockSiteEntity extends Audit {
	
	private static final long serialVersionUID = 1322917321593640171L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer siteId;
	
	@Column(nullable = false)
	private String siteName;
	
	private boolean isDeleted;
	
	public Integer getSiteId() {
		return siteId;
	}
	
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	
	public String getSiteName() {
		return siteName;
	}
	
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}
	
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
