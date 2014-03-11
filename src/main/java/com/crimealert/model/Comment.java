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
@Table(name="tbl_comment")
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_comment_id")
	@SequenceGenerator(name="seq_comment_id", sequenceName="seq_comment_id")	
	@Column(name="comment_id")
    private Integer commentId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name="description", nullable=false)
	private String description;

	@Column(name="cdate", nullable=false)
	private Date commentDate;
}

	
