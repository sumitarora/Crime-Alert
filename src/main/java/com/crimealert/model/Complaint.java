package com.crimealert.model;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_complaint")
public class Complaint {
        
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_complaint_id")
	@SequenceGenerator(name="seq_complaint_id", sequenceName="seq_complaint_id")	
	@Column(name="complaint_id")
    private Integer complaintId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name="title", nullable=false)
	private String title;

	@Column(name="cdate", nullable=false)
	private Date complaintDate;
	
	@Column(name="description", nullable=true)
	private String description;
	
	@Column(name="location", nullable=false)
	private String location;

	@Column(name="address", nullable=false)
	private String address;

	@Column(name="city", nullable=false)
	private String city;
	
	@Column(name="provience", nullable=false)
	private String provience;
	
	@Column(name="country", nullable=false)
	private String country;	

	@Column(name="map", nullable=false)
	private String map;
        
}