package com.crimealert.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crimealert.model.GeoSummary;
import com.crimealert.model.Group;
import com.crimealert.model.GroupValue;
import com.crimealert.model.Summary;
import com.crimealert.service.ReportService;

@Controller
@RequestMapping(value="/reports")
@Slf4j
public class ReportController extends BaseController {

	@Autowired
	ReportService reportService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView showReportsMenu(){
		log.debug("inside show reports menu");
		ModelAndView mav=new ModelAndView("reports/menu");
		List<Summary> summary = reportService.getCountriesSummary(true);
		mav.addObject("summary", summary);
		return setSelectedMenu(mav);
	}
	
	@RequestMapping(value="/summary",method=RequestMethod.POST)
	@ResponseBody
	public List<Summary> getSummary(){
		//String reportBy = request.getParameter("reportBy");
		String reportOf = request.getParameter("reportOf");
		String country = request.getParameter("country");
		
		Boolean forCrime = false;
		if(reportOf == null || reportOf.equals("cr")) {
			forCrime = true;
		}
		
		if(country != null && !country.equals("")) {
			List<Summary> summary = reportService.getStatesSummaryByCountry(forCrime, country);
			return summary;			
		}
		
		List<Summary> summary = reportService.getCountriesSummary(forCrime);		
		return summary;
	}

	@RequestMapping(value="/locationsummary",method=RequestMethod.GET)
	@ResponseBody
	public List<GeoSummary> getLocationSummary(){
		return reportService.getLocationSummary(true);
	}
	
	@RequestMapping(value="/scattter",method=RequestMethod.GET)
	@ResponseBody
	public List<Group> getScatterSummary(){
		List<Group> groups = new ArrayList<Group>();
		
		List<Summary> summaryCrime = reportService.getCountriesSummary(true);
		if(summaryCrime.size() > 0) {
			Group g = new Group();
			g.setKey("Crimes");
			
			List<GroupValue> groupValues = new ArrayList<GroupValue>();
			for (int i = 0; i < summaryCrime.size(); i++) {
				final GroupValue gv = new GroupValue();
				gv.setSize(summaryCrime.get(i).getCount());
				
				int x = (int)(Math.random() * 100);
				int y = (int)(Math.random() * 100);
				log.debug("x: {}, y: {}", x, y);
				gv.setX(x);
				gv.setY(y);
				
				gv.setSeries(0);
				gv.setShape("circle");
				groupValues.add(gv);
			}
			g.setValues(groupValues);
			groups.add(g);
		}
		
		List<Summary> summaryComplaint = reportService.getCountriesSummary(true);
		if(summaryComplaint.size() > 0) {
			Group g = new Group();
			g.setKey("Complaints");
			
			List<GroupValue> groupValues = new ArrayList<GroupValue>();
			for (int i = 0; i < summaryComplaint.size(); i++) {
				final GroupValue gv = new GroupValue();
				gv.setSize(summaryComplaint.get(i).getCount());

				int x = (int)(Math.random() * 100);
				int y = (int)(Math.random() * 100);
				log.debug("x: {}, y: {}", x, y);
				gv.setX(x);
				gv.setY(y);
				
				gv.setSeries(0);
				gv.setShape("circle");
				groupValues.add(gv);
			}
			g.setValues(groupValues);
			groups.add(g);
		}		
		return groups;
	}	
	
}
