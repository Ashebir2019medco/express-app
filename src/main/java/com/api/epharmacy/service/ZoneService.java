package com.api.epharmacy.service;

import java.util.List;

import com.api.epharmacy.ui.model.request.ZoneRequestModel;
import com.api.epharmacy.ui.model.response.ZoneResponseModel;

public interface ZoneService {

	ZoneResponseModel saveZone(ZoneRequestModel zoneDetail);

	List<ZoneResponseModel> getAllZones(String searchKey, int page, int limit);

	ZoneResponseModel getZone(Integer zoneId);

	ZoneResponseModel updateZone(Integer zoneId, ZoneRequestModel zoneDetail);

	String deleteZone(Integer zoneId);

}
