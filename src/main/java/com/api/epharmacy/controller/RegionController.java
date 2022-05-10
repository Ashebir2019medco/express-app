package com.api.epharmacy.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.epharmacy.service.RegionService;
import com.api.epharmacy.ui.model.request.RegionRequestModel;
import com.api.epharmacy.ui.model.response.RegionResponseModel;

@RestController
@RequestMapping("/region")
public class RegionController {
	
	@Autowired
	RegionService regionService;
	
	@PostMapping
	public RegionResponseModel saveRegion(@RequestBody RegionRequestModel regionDetail) {
		RegionResponseModel returnValue = regionService.saveRegion(regionDetail);
		return returnValue;
	}
	
	@GetMapping
	public List<RegionResponseModel> getAllRegions(@RequestParam(value="searchKey", defaultValue = "") String searchKey, @RequestParam(value="page", defaultValue = "1") int page,
			   @RequestParam(value="limit", defaultValue = "1000") int limit){
		
		List<RegionResponseModel> returnValue = regionService.getAllRegions(searchKey, page,limit);
		
		return returnValue;
		
	}
	@GetMapping(path = "/{regionId}")
	public RegionResponseModel getRegion(@PathVariable Integer regionId) {
		RegionResponseModel returnValue = regionService.getRegion(regionId); 
		return returnValue; 
	}
	@PutMapping(path="/{regionId}")
	public RegionResponseModel updateRegion(@PathVariable Integer regionId,@RequestBody RegionRequestModel regionDetail) {
		RegionResponseModel returnValue = regionService.updateRegion(regionId,regionDetail);
		return returnValue;
	}
	@DeleteMapping(path="/{regionId}")
	public String deleteRegion(@PathVariable Integer regionId) {
		
		String returnValue=regionService.deleteRegion(regionId);
		return returnValue;
	}


}