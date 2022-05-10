package com.api.epharmacy.service;

import java.util.List;

import com.api.epharmacy.ui.model.request.WoredaRequestModel;
import com.api.epharmacy.ui.model.response.WoredaResponseModel;

public interface WoredaService {

	WoredaResponseModel saveWoreda(WoredaRequestModel woredaDetail);

	List<WoredaResponseModel> getAllWoreda(String searchKey, int page, int limit);


	String deleteWoreda(Integer woredaId);

	WoredaResponseModel updateWoreda(Integer woredaId, WoredaRequestModel woredaDetail);

	WoredaResponseModel getWoreda(Integer woredaId);

}
