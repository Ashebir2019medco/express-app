package com.api.epharmacy.service;

import java.util.List;

import com.api.epharmacy.ui.model.request.RegionRequestModel;
import com.api.epharmacy.ui.model.response.RegionResponseModel;

public interface RegionService {

	RegionResponseModel saveRegion(RegionRequestModel regionDetail);

	List<RegionResponseModel> getAllRegions(String searchKey, int page, int limit);

	RegionResponseModel getRegion(Integer regionId);

	RegionResponseModel updateRegion(Integer regionId, RegionRequestModel regionDetail);

	String deleteRegion(Integer regionId);


}
