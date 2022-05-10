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

import com.api.epharmacy.service.WoredaService;
import com.api.epharmacy.ui.model.request.WoredaRequestModel;
import com.api.epharmacy.ui.model.response.WoredaResponseModel;

@RestController
@RequestMapping("/woreda")
public class WoredaController {

	@Autowired
	WoredaService woredaService;
	
	@PostMapping
	public WoredaResponseModel saveWoreda(@RequestBody WoredaRequestModel woredaDetail) {
		WoredaResponseModel returnValue = woredaService.saveWoreda(woredaDetail);
		return returnValue;
	}
	
	@GetMapping
	public List<WoredaResponseModel> getAllWoredas(@RequestParam(value="searchKey", defaultValue = "") String searchKey, @RequestParam(value="page", defaultValue = "1") int page,
			   @RequestParam(value="limit", defaultValue = "1000") int limit){
		
		List<WoredaResponseModel> returnValue = woredaService.getAllWoreda(searchKey, page,limit);
		
		return returnValue;
		
	}
	@GetMapping(path = "/{woredaId}")
	public WoredaResponseModel getWoreda(@PathVariable Integer woredaId) {
		WoredaResponseModel returnValue = woredaService.getWoreda(woredaId); 
		return returnValue;
	}
	@PutMapping(path="/{woredaId}")
	public WoredaResponseModel updateWoreda(@PathVariable Integer woredaId,@RequestBody WoredaRequestModel woredaDetail) {
		WoredaResponseModel returnValue = woredaService.updateWoreda(woredaId, woredaDetail);
		return returnValue;
	}
	@DeleteMapping(path="/{woredaId}")
	public String deleteWoreda(@PathVariable Integer woredaId) {
		
		String returnValue = woredaService.deleteWoreda(woredaId);
		return returnValue;
	}
}
