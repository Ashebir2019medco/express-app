package com.api.epharmacy.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.epharmacy.model.audit.Audit;

@Entity(name = "woreda")
public class WoredaEntity extends Audit implements Serializable{

	private static final long serialVersionUID = -3231125076495504590L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer woredaId;

	@Column(nullable = false, length = 50)
	private Integer countryId;
	
	@Column(nullable = false, length = 50)
	private Integer regionId;
	
	@Column(nullable = false, length = 50)
	private Integer zoneId;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column
	private boolean isDeleted = false;

	public Integer getWoredaId() {
		return woredaId;
	}

	public void setWoredaId(Integer woredaId) {
		this.woredaId = woredaId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	@Override
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	
}
