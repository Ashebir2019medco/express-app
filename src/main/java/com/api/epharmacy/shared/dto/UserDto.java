package com.api.epharmacy.shared.dto;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable {
	
	private static final long serialVersionUID = 559579346246652215L;
	
	private long id;
	
	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	private String grandFatherName;
	
	private String email;
	
	private String password;
	
	private String mobilePhone;
	
	private String userType;
	
	private String userStatus;
	
	private String userRole;
	
	private String encryptedPassword;
	
	private String emailVerificationToken;
	
	private Boolean emailVerficationStatus = false;
	
	private String profilePicture;
	
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
	
	public String getUserRole() {
		return userRole;
	}
	
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public String getProfilePicture() {
		return profilePicture;
	}
	
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	public Date getRegistrationDate() {
		return registrationDate;
	}
	
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
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
	
	public String getGrandFatherName() {
		return grandFatherName;
	}
	
	public void setGrandFatherName(String grandFatherName) {
		this.grandFatherName = grandFatherName;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getUserStatus() {
		return userStatus;
	}
	
	public void setUserStatus(String status) {
		this.userStatus = status;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	
	public Boolean getEmailVerficationStatus() {
		return emailVerficationStatus;
	}
	
	public void setEmailVerficationStatus(Boolean emailVerficationStatus) {
		this.emailVerficationStatus = emailVerficationStatus;
	}
	
}
