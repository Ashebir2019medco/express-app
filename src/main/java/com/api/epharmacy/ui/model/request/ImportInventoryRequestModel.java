package com.api.epharmacy.ui.model.request;


import org.springframework.web.multipart.MultipartFile;

public class ImportInventoryRequestModel {
	
	private MultipartFile importExcel;

	public MultipartFile getImportExcel() {
		return importExcel;
	}

	public void setImportExcel(MultipartFile importExcel) {
		this.importExcel = importExcel;
	}

	

}
