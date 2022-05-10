package com.api.epharmacy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.api.epharmacy.service.CountryService;
import com.api.epharmacy.ui.model.request.CountryRequestModel;
import com.api.epharmacy.ui.model.response.CountryResponseModel;

@RestController
@RequestMapping("/country")
public class CountryController {

	@Autowired
	CountryService countryService;
	
	@PostMapping
	public CountryResponseModel saveCountry(@RequestBody CountryRequestModel countryDetail) {
		CountryResponseModel returnValue = countryService.saveCountry(countryDetail);
		return returnValue;

	}

	@GetMapping
	public List<CountryResponseModel> getAllCountries(
			@RequestParam(value = "searchKey", defaultValue = "") String searchKey,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "1000") int limit) {

		List<CountryResponseModel> returnValue = countryService.getAllCountries(searchKey, page, limit);

		return returnValue;

	}

	@GetMapping(path = "/{countryId}")
	public CountryResponseModel getCountry(@PathVariable Integer countryId, HttpServletRequest headerRequest) {

		CountryResponseModel returnValue = countryService.getCountry(countryId);
		return returnValue;
	}

	@PutMapping(path = "/{countryId}")
	public CountryResponseModel updateCountry(@PathVariable Integer countryId,
			@RequestBody CountryRequestModel countryDetail) {
		CountryResponseModel returnValue = countryService.updateCountry(countryId, countryDetail);
		return returnValue;
	}

	@DeleteMapping(path = "/{countryId}")
	public String deleteCountry(@PathVariable Integer countryId) {

		String returnValue = countryService.deleteCountry(countryId);
		return returnValue;
	}

}
