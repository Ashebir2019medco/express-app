package com.api.epharmacy.service;

import java.util.List;

import com.api.epharmacy.ui.model.request.CountryRequestModel;
import com.api.epharmacy.ui.model.response.CountryResponseModel;

public interface CountryService {

	CountryResponseModel saveCountry(CountryRequestModel countryDetail);

	List<CountryResponseModel> getAllCountries(String searchKey, int page, int limit);

	CountryResponseModel getCountry(Integer countryId);

	CountryResponseModel updateCountry(Integer countryId, CountryRequestModel countryDetail);

	String deleteCountry(Integer countryId);

}
