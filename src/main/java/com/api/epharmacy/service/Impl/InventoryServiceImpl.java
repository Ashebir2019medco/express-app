package com.api.epharmacy.service.Impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.epharmacy.exception.AppException;
import com.api.epharmacy.io.entity.InventoryEntity;
import com.api.epharmacy.io.repositories.InventoryRepository;
import com.api.epharmacy.service.InventoryService;
import com.api.epharmacy.shared.GenerateRandomString;
import com.api.epharmacy.ui.model.request.ImportInventoryRequestModel;
import com.api.epharmacy.ui.model.request.InventoryRequestModel;
import com.api.epharmacy.ui.model.request.SearchRequestModel;
import com.api.epharmacy.ui.model.response.InventoryCategoryResponse;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;
	
	@Value("${file.upload-dir}")
    private String uploadDirectory;
	
	@Autowired
	GenerateRandomString generateRandomString;
	
	@Override
	public InventoryRequestModel insertNewInventory(InventoryRequestModel inventoryDetails) throws IOException {
		
		if(inventoryRepository.findByInventoryGenericNameIgnoreCaseAndInventoryBrandNameIgnoreCaseAndInventoryTypeIgnoreCaseAndDosageFormIgnoreCaseAndStrengthIgnoreCase(inventoryDetails.getInventoryGenericName(), inventoryDetails.getInventoryBrandName(), inventoryDetails.getInventoryType(), inventoryDetails.getDosageForm(), inventoryDetails.getStrength()) != null)
			  throw new RuntimeException("Record already exists.");
		
		InventoryRequestModel returnvalue = new InventoryRequestModel();
		InventoryEntity inventoryEntity = new InventoryEntity();
		BeanUtils.copyProperties(inventoryDetails, inventoryEntity);
		
		if( inventoryDetails.getUploadImage() != null) {
//			String rootDirectory = new File("").getAbsolutePath();
			String rootDirectory = "/var/www/html/images";
			//			String rootDirectory = "C:/wamp64/www/images";
			
			String uploadDir = rootDirectory + "/inventory-item-images/";
			File directory = new File(uploadDir);
		    if (!directory.exists()){
		        directory.mkdirs();
		    }
					    
			byte[] bytes = inventoryDetails.getUploadImage().getBytes();
			Instant instant = Instant.now();
			long timeStampSeconds = instant.getEpochSecond();
			String randomText = generateRandomString.generateUserId(10) + "_" + timeStampSeconds;
			
			String fileName = inventoryDetails.getUploadImage().getOriginalFilename();
			String extention = fileName.substring(fileName.lastIndexOf(".") + 1);
			String newFileName = randomText + "." +  extention;
			
			Path path = Paths.get(uploadDir + newFileName);
		    Files.write(path, bytes);
		    
			inventoryEntity.setInventoryImage(newFileName);
		}
		
		InventoryEntity StoredInventoryDetail = inventoryRepository.save(inventoryEntity);
		
		BeanUtils.copyProperties(StoredInventoryDetail, returnvalue);
		
		return returnvalue;
	}

	@Override
	public InventoryRequestModel getInventoryByInventoryId(long inventoryId) {
		
		InventoryRequestModel returnvalue = new InventoryRequestModel();
		InventoryEntity inventoryEntity = inventoryRepository.findByInventoryId(inventoryId);
		
		if(inventoryEntity == null) throw new AppException("Inventory Item not found.");
		
		BeanUtils.copyProperties(inventoryEntity, returnvalue);
		
		return returnvalue;
	}

	@Override
	public List<InventoryRequestModel> getInventeryItems(int page, int limit) {
		
		List<InventoryRequestModel> returnValue = new ArrayList<>();
	    
	    if(page > 0) page = page - 1;
	   
	    Pageable pageableRequest = PageRequest.of(page, limit,Sort.by("InventoryId").descending());
	    
	    Page<InventoryEntity> inventoryPage = inventoryRepository.findAll(pageableRequest);
	    int totalPages = inventoryPage.getTotalPages();
	    List<InventoryEntity> inventoryItems = inventoryPage.getContent();
	    for(InventoryEntity inventoryEntity : inventoryItems) {
	    	//int size = returnValue.size();
	    	InventoryRequestModel inventoryRequestModel = new InventoryRequestModel(); 
	    	BeanUtils.copyProperties(inventoryEntity, inventoryRequestModel);
	    	if(returnValue.size() == 0) {
	    		inventoryRequestModel.setTotalPages(totalPages);
	    	}
	    	returnValue.add(inventoryRequestModel);
	    }
	    
		return returnValue;
		
	}

	@Override
	public InventoryRequestModel updateInventoryItem(long inventoryId, InventoryRequestModel inventoryItem) throws IOException {
		
		InventoryRequestModel returnValue = new InventoryRequestModel();
		InventoryEntity inventoryEntity = inventoryRepository.findByInventoryId(inventoryId);
		
		if(inventoryEntity == null) 
			throw new RuntimeException("Inventory Item not found.");
		
		inventoryEntity.setInventoryGenericName(inventoryItem.getInventoryGenericName());
		inventoryEntity.setInventoryBrandName(inventoryItem.getInventoryBrandName());
		inventoryEntity.setInventoryType(inventoryItem.getInventoryType());
		inventoryEntity.setMeasuringUnit(inventoryItem.getMeasuringUnit());
		inventoryEntity.setMinimumStockQuantity(inventoryItem.getMinimumStockQuantity());
		inventoryEntity.setSubCategory(inventoryItem.getSubCategory());
		inventoryEntity.setDosageForm(inventoryItem.getDosageForm());
		inventoryEntity.setStrength(inventoryItem.getStrength());
		inventoryEntity.setVolume(inventoryItem.getVolume());
		
		if(inventoryItem.getUploadImage() != null) {
			
			//			String rootDirectory = new File("").getAbsolutePath();
			String rootDirectory = "/var/www/html/images";
			//			String rootDirectory = "C:/wamp64/www/images";
			String uploadDir = rootDirectory + "/inventory-item-images/";
			File directory = new File(uploadDir);
		    if (!directory.exists()){
		        directory.mkdirs();
		    }
		    
		    // Add remove Previous file
			byte[] bytes = inventoryItem.getUploadImage().getBytes();
			Instant instant = Instant.now();
			long timeStampSeconds = instant.getEpochSecond();
			String randomText = generateRandomString.generateUserId(10) + "_" + timeStampSeconds;
			
			String fileName = inventoryItem.getUploadImage().getOriginalFilename();
			String extention = fileName.substring(fileName.lastIndexOf(".") + 1);
			String newFileName = randomText + "." +  extention;
			
			Path path = Paths.get(uploadDir + newFileName);
		    Files.write(path, bytes);
		    
			inventoryEntity.setInventoryImage(newFileName);
		}
		
		InventoryEntity updatedInventoryItem = inventoryRepository.save(inventoryEntity);
		
		BeanUtils.copyProperties(updatedInventoryItem, returnValue); 
		returnValue.setInventoryId(inventoryEntity.getInventoryId());
		return returnValue;
	}

	@Override
	public List<InventoryRequestModel> searchInventeryItems(SearchRequestModel searchkeyDetail, int page, int limit) {
		
		String searchKey = searchkeyDetail.getSearchKey();
		List<InventoryRequestModel> returnValue = new ArrayList<>();
	    
	    if(page > 0) page = page - 1;
	   
	    Pageable pageableRequest = PageRequest.of(page, limit);
	    
	    Page<InventoryEntity> inventoryPage = inventoryRepository.findByInventoryGenericNameContainingOrInventoryBrandNameContainingOrInventoryTypeContainingOrMeasuringUnitContaining(searchKey,searchKey,searchKey,searchKey,pageableRequest);
	    int totalPages = inventoryPage.getTotalPages();
	    List<InventoryEntity> inventoryItems = inventoryPage.getContent();
	    for(InventoryEntity inventoryEntity : inventoryItems) {
	    	//int size = returnValue.size();
	    	InventoryRequestModel inventoryRequestModel = new InventoryRequestModel(); 
	    	BeanUtils.copyProperties(inventoryEntity, inventoryRequestModel);
	    	if(returnValue.size() == 0) {
	    		inventoryRequestModel.setTotalPages(totalPages);
	    	}
	    	returnValue.add(inventoryRequestModel);
	    }
	    
		return returnValue;
	}

	@Override
	public List<InventoryCategoryResponse> getInventoryCategories() {
		
		List<InventoryCategoryResponse> returnValue = new ArrayList<>();
		List<String> categories = inventoryRepository.findDistinctInventoryType();
		
		for(String category : categories) {
			InventoryCategoryResponse categoryResponse = new InventoryCategoryResponse(); 
	    	categoryResponse.setInventoryType(category);
	    	returnValue.add(categoryResponse);
	    }
		
		return returnValue;
	}

	@Override
	public String importInventoryItems(ImportInventoryRequestModel importInventoryDetails) throws IOException {
		
		String returnValue = "";
		XSSFWorkbook workbook = new XSSFWorkbook(importInventoryDetails.getImportExcel().getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
	    int i;
	    for(i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {

	    	InventoryEntity inventoryEntity = new InventoryEntity();
	    	
	        XSSFRow row = worksheet.getRow(i);
	        
	        String genericName = row.getCell(0,Row.CREATE_NULL_AS_BLANK).getStringCellValue();
	        String brandName = row.getCell(6,Row.CREATE_NULL_AS_BLANK).getStringCellValue();
	        String inventoryType = row.getCell(4,Row.CREATE_NULL_AS_BLANK).getStringCellValue();
	        String dosageForm = row.getCell(1,Row.CREATE_NULL_AS_BLANK).getStringCellValue();
	        String strength = row.getCell(2,Row.CREATE_NULL_AS_BLANK).getStringCellValue();
	        
	      
	        Pageable pageableRequest = PageRequest.of(1, 1);
			Page<InventoryEntity> checkItem = inventoryRepository.findByInventoryGenericNameIgnoreCaseAndInventoryBrandNameIgnoreCaseAndInventoryTypeIgnoreCaseAndDosageFormIgnoreCaseAndStrengthIgnoreCase(genericName, brandName, inventoryType, dosageForm, strength,pageableRequest);
			long count = checkItem.getTotalElements();
	        if(count == 0) {
	        	if(row.getCell(0,Row.CREATE_NULL_AS_BLANK).getStringCellValue() != null) {
		        	inventoryEntity.setInventoryGenericName(row.getCell(0).getStringCellValue());
		        }
		        
		        if(row.getCell(1,Row.CREATE_NULL_AS_BLANK).getStringCellValue() != null) {
		        	inventoryEntity.setDosageForm(row.getCell(1).getStringCellValue());
		        }
		        
		        if(row.getCell(2,Row.CREATE_NULL_AS_BLANK).getStringCellValue() != null) {
		        	inventoryEntity.setStrength(row.getCell(2).getStringCellValue());
		        }
		        
		        if(row.getCell(3,Row.CREATE_NULL_AS_BLANK).getStringCellValue() != null) {
		        	inventoryEntity.setVolume(row.getCell(3).getStringCellValue());
		        }
		        
		        if(row.getCell(4,Row.CREATE_NULL_AS_BLANK).getStringCellValue() != null) {
		        	inventoryEntity.setInventoryType(row.getCell(4).getStringCellValue());
		        }
		        
		        if(row.getCell(5,Row.CREATE_NULL_AS_BLANK).getStringCellValue() != null) {
		        	inventoryEntity.setSubCategory(row.getCell(5).getStringCellValue());
		        }
		        
		        if(row.getCell(6,Row.CREATE_NULL_AS_BLANK).getStringCellValue() != null) {
		        	inventoryEntity.setInventoryBrandName(row.getCell(6).getStringCellValue());
		        }
		        
		        if(row.getCell(7,Row.CREATE_NULL_AS_BLANK).getStringCellValue() != null) {
		        	inventoryEntity.setMinimumStockQuantity((float) row.getCell(7).getNumericCellValue());
		        }
		        
		        if(row.getCell(8,Row.CREATE_NULL_AS_BLANK).getStringCellValue() != null) {
		        	inventoryEntity.setMeasuringUnit(row.getCell(8).getStringCellValue());
		        }
		        
		        inventoryRepository.save(inventoryEntity);
	        }
	        
	          
	    }
	    if(i > 1 && (worksheet.getPhysicalNumberOfRows()) > 1) {
	    	returnValue = "Inventory Items Imported Successfully";
	    }
		
		return returnValue;
	}

	@Override
	public List<InventoryRequestModel> getSelectedInventeryItems(Long[] inventoryIds) {
		List<InventoryRequestModel> returnValue = new ArrayList<>(); 

		List<InventoryEntity> inventoryItems = inventoryRepository.findByInventoryIdIn(inventoryIds);
	    for(InventoryEntity inventoryEntity : inventoryItems) {
	    	InventoryRequestModel inventoryRequestModel = new InventoryRequestModel(); 
	    	BeanUtils.copyProperties(inventoryEntity, inventoryRequestModel);
	    	returnValue.add(inventoryRequestModel);
	    }
	    
		return returnValue;
	}

}
