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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_feedback")
public class Feedback {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_feedback_id")
	@SequenceGenerator(name="seq_feedback_id", sequenceName="seq_feedback_id")	
	@Column(name="feedback_id")
    private Long feedbackId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name="type", nullable=false)
	private String type;
	
	@Column(name="description", nullable=false)
	private String description;

	@Column(name="fdate", nullable=false)
	private Date feedbackDate;
	
	  @ManyToMany(cascade = {CascadeType.ALL})
	  @JoinTable(name = "feedback_to_comments", 
	  			 joinColumns = { @JoinColumn(name = "feedback_id", referencedColumnName = "feedback_id") }, 
	  			 inverseJoinColumns = { @JoinColumn(name = "comment_id", referencedColumnName = "comment_id") })
	  @Fetch(FetchMode.JOIN)
	  private List<Comment>  comments;	
	
}

	
