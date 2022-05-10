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

import com.api.epharmacy.service.ZoneService;
import com.api.epharmacy.ui.model.request.ZoneRequestModel;
import com.api.epharmacy.ui.model.response.ZoneResponseModel;

@RestController
@RequestMapping("/zone")
public class ZoneController {
	
	@Autowired
	ZoneService zoneService;
	
	@PostMapping
	public ZoneResponseModel saveZone(@RequestBody ZoneRequestModel zoneDetail) {
		ZoneResponseModel returnValue = zoneService.saveZone(zoneDetail);
		return returnValue;
	}
	
	@GetMapping
	public List<ZoneResponseModel> getAllZones(@RequestParam(value="searchKey", defaultValue = "") String searchKey, @RequestParam(value="page", defaultValue = "1") int page,
			   @RequestParam(value="limit", defaultValue = "1000") int limit){
		
		List<ZoneResponseModel> returnValue = zoneService.getAllZones(searchKey, page,limit);
		
		return returnValue;
		
	}
	@GetMapping(path = "/{zoneId}")
	public ZoneResponseModel getZone(@PathVariable Integer zoneId) {
		ZoneResponseModel returnValue = zoneService.getZone(zoneId); 
		return returnValue;
	}
	@PutMapping(path="/{zoneId}")
	public ZoneResponseModel updateZone(@PathVariable Integer zoneId,@RequestBody ZoneRequestModel zoneDetail) {
		ZoneResponseModel returnValue = zoneService.updateZone(zoneId,zoneDetail);
		return returnValue;
	}
	@DeleteMapping(path="/{zoneId}")
	public String deleteZone(@PathVariable Integer zoneId) {
		
		String returnValue=zoneService.deleteZone(zoneId);
		return returnValue;
	}


}
