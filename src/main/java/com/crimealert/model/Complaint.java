package com.crimealert.model;


import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	
	@Column(name="latitude", nullable=false)
	private String latitude;
	
	@Column(name="longitude", nullable=false)
	private String longitude;

	@Column(name="address", nullable=false)
	private String address;

	@Column(name="locality", nullable=false)
	private String locality;

	@Column(name="administrative_area_level_1", nullable=false)
	private String administrative_area_level_1;
	
	@Column(name="country", nullable=false)
	private String country;	

	@Column(name="map", nullable=false)
	private String map;
	
	@Column(name="opendata", nullable=true)
	private Boolean opendata;
	
	@Column(name="uploads", nullable=true)
	private String uploads;
	
	@Transient
	private String location;
	
	  @ManyToMany(cascade = {CascadeType.ALL})
	  @JoinTable(name = "complaint_to_comments", 
	  			 joinColumns = { @JoinColumn(name = "complaint_id", referencedColumnName = "complaint_id") }, 
	  			 inverseJoinColumns = { @JoinColumn(name = "comment_id", referencedColumnName = "comment_id") })
	  @Fetch(FetchMode.JOIN)
	  private List<Comment>  comments;	
}

