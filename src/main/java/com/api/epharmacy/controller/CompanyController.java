package com.api.epharmacy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.epharmacy.service.CompanyService;
import com.api.epharmacy.shared.dto.UserDto;
import com.api.epharmacy.ui.model.request.InventoryListViewSearchRequestModel;
import com.api.epharmacy.ui.model.request.SearchRequestModel;
import com.api.epharmacy.ui.model.request.UserDetailRequestModel;
import com.api.epharmacy.ui.model.response.CompanyResponse;
import com.api.epharmacy.ui.model.response.InventoryListViewResponse;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	CompanyService companyService;
	
	@GetMapping(path="/{companyId}")
	public CompanyResponse getCompanyDetail(@PathVariable long companyId) {
		CompanyResponse returnValue= new CompanyResponse();
		UserDto userDto = companyService.getCompanyById(companyId);
		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue;
	}
	
	@GetMapping
	public List<CompanyResponse> getCompanies(@RequestParam(value="page", defaultValue = "1") int page,
			@RequestParam(value="limit", defaultValue = "25") int limit){
		
		List<CompanyResponse> returnValue = new ArrayList<>();
		
		List<UserDto> companies= companyService.getCompanies(page,limit);
		
		for(UserDto userDto : companies) {
			CompanyResponse userModel = new CompanyResponse();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
		}
		
		return returnValue;
	}
	
	@PostMapping(path="/search")
	public List<CompanyResponse> searchCompanies(@RequestBody SearchRequestModel searchkey, @RequestParam(value="page", defaultValue = "1") int page,
								   @RequestParam(value="limit", defaultValue = "25") int limit){
		
		List<CompanyResponse> returnValue = new ArrayList<>();
		
		List<UserDto> companies= companyService.searchCompanies(searchkey,page,limit);
		
		for(UserDto userDto : companies) {
			CompanyResponse userModel = new CompanyResponse();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
		}
		
		return returnValue;
	}
	
	@PutMapping(path="/{id}")
	public CompanyResponse updateCompany(@PathVariable long id, @RequestBody UserDetailRequestModel companyDetails) {
		
		CompanyResponse returnValue= new CompanyResponse();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(companyDetails, userDto);
		
		UserDto createdUser = companyService.updateCompany(id, userDto);
		BeanUtils.copyProperties(createdUser, returnValue);
		
		return returnValue;
	}	
	
}
