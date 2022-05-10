package com.api.epharmacy.ui.model.response;

import java.util.Date;

public class CompanyResponse {
	
	private long companyId;
	private String accountId;
	private String companyName;
	private String licenceNumber;
	private String tinNumber;
	private String companyStatus;
	private Integer regionId;
	private String Region;
	private String WoredaCity;
	private String Street;
	private Date registrationDate;
	private int totalPages;
	
	public Integer getRegionId() {
		return regionId;
	}
	
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	public String getRegion() {
		return Region;
	}
	public void setRegion(String region) {
		Region = region;
	}
	public String getWoredaCity() {
		return WoredaCity;
	}
	public void setWoredaCity(String woredaCity) {
		WoredaCity = woredaCity;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLicenceNumber() {
		return licenceNumber;
	}
	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}
	public String getTinNumber() {
		return tinNumber;
	}
	public void setTinNumber(String tinNumber) {
		this.tinNumber = tinNumber;
	}
	public String getCompanyStatus() {
		return companyStatus;
	}
	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	
}
