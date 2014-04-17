package com.crimealert.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_news")
public class News {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_news_id")
	@SequenceGenerator(name="seq_news_id", sequenceName="seq_news_id")	
	@Column(name="news_id")
    private Integer newsId;

	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="description", nullable=false)
	private String description;

	@Column(name="ndate", nullable=false)
	private Date newsDate;
}

	
