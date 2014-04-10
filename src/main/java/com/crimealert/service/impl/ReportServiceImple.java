package com.crimealert.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.crimealert.model.GeoSummary;
import com.crimealert.model.Summary;
import com.crimealert.service.ReportService;

@Slf4j
@Service
public class ReportServiceImple implements ReportService {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Summary> getCountriesSummary(final Boolean forCrime) {
		
		String query = "select cr.country, count(*) from tbl_complaint cr group by cr.country";
		if(forCrime) {
			query = "select cr.country, count(*) from tbl_crime cr group by cr.country";
		}
		
		final Query q = em.createNativeQuery(query);
		List<Object> result = q.getResultList();
		log.debug("result {}", result.size());
		  return convertResult(result);
	}
	
	private List<Summary> convertResult(final List<Object> result) {
		final List<Summary> summary = new ArrayList<Summary>();
		for (int i = 0; i < result.size(); i++) {
			final Summary s = new Summary();
			
			Object[] r = (Object[]) result.get(i);
			s.setKey((String)r[0]);
			s.setCount((BigInteger)r[1]); 
			
			summary.add(s);
			
		}
		return summary;
	}

	@Override
	public List<Summary> getStatesSummaryByCountry(Boolean forCrime, String country) {
		
		String query = "select cr.administrative_area_level_1,count(*) from tbl_complaint cr where country = '"+country+"' group by cr.administrative_area_level_1";
		if(forCrime) {
			query = "select cr.administrative_area_level_1,count(*) from tbl_crime cr where country = '"+country+"' group by cr.administrative_area_level_1";
		}
		final Query q = em.createNativeQuery(query);
		List<Object> result = q.getResultList();
		log.debug("result {}", result.size());
		return convertResult(result);
	}

	@Override
	public List<GeoSummary> getLocationSummary(Boolean forCrime) {
		String query = "select count(*),round(latitude,2),round(longitude,2) from tbl_crime group by round(latitude,2),round(longitude,2)";
		if(forCrime) {
			query = "select count(*),round(latitude,2),round(longitude,2) from tbl_crime group by round(latitude,2),round(longitude,2)";
		}
		
		final Query q = em.createNativeQuery(query);
		List<Object> result = q.getResultList();
		log.debug("result {}", result.size());
		  return convertGeoResult(result);
	}

	private List<GeoSummary> convertGeoResult(final List<Object> result) {
		final List<GeoSummary> summary = new ArrayList<GeoSummary>();
		for (int i = 0; i < result.size(); i++) {
			final GeoSummary s = new GeoSummary();
			
			Object[] r = (Object[]) result.get(i);
			s.setMagnitute((BigInteger)r[0]);
			s.setLatitude((Double)r[1]);
			s.setLongitude((Double)r[2]);			
			summary.add(s);
			
		}
		return summary;
	}
	
}
