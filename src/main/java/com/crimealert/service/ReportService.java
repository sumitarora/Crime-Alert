package com.crimealert.service;

import java.util.List;

import com.crimealert.model.GeoSummary;
import com.crimealert.model.Summary;

public interface ReportService {

	public List<Summary> getCountriesSummary(final Boolean forCrime);
	
	public List<Summary> getStatesSummaryByCountry(final Boolean forCrime, String country);
	
	public List<GeoSummary> getLocationSummary(final Boolean forCrime);
	
}
