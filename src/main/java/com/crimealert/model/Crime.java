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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_crime")
public class Crime {
        
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_crime_id")
	@SequenceGenerator(name="seq_crime_id", sequenceName="seq_crime_id")	
	@Column(name="crime_id")
    private Integer crimeId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name="title", nullable=false)
	private String title;

	@Column(name="cdate", nullable=false)
	private Date crimeDate;
	
	@Column(name="description", nullable=true)
	private String description;
	
	@Column(name="location", nullable=false)
	private String location;

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
	
	@Column(name="uploads", nullable=true)
	private String uploads;
	
	  @ManyToMany(cascade = {CascadeType.ALL})
	  @JoinTable(name = "crime_to_comments", 
	  			 joinColumns = { @JoinColumn(name = "crime_id", referencedColumnName = "crime_id") }, 
	  			 inverseJoinColumns = { @JoinColumn(name = "comment_id", referencedColumnName = "comment_id") })
	  @Fetch(FetchMode.JOIN)
	  private List<Comment>  comments;

}

