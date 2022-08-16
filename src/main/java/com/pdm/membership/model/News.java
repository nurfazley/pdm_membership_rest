package com.pdm.membership.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pdm.membership.model.lookup.NewsStatus;
import com.pdm.membership.model.lookup.NewsType;

@Entity
@Table(name = "News")
public class News implements Serializable {

	private static final long serialVersionUID = -1016917963644003774L;

	@Id
	@SequenceGenerator(name = "newsSequence", sequenceName = "news_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "newsSequence")
	private Long id;
	
	@Column(name = "newsDateTime")
	private Date newsDateTime;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "bodyContent")
	private String bodyContent;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "newsType")
	private NewsType newsType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "newsStatus")
	private NewsStatus newsStatus = NewsStatus.DRAFT;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "news")
	@JsonIgnore
	private List<Image> imageList = new ArrayList<Image>();

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getNewsDateTime() {
		return newsDateTime;
	}

	public void setNewsDateTime(Date newsDateTime) {
		this.newsDateTime = newsDateTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBodyContent() {
		return bodyContent;
	}

	public void setBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
	}

	public NewsType getNewsType() {
		return newsType;
	}

	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}

	public NewsStatus getNewsStatus() {
		return newsStatus;
	}

	public void setNewsStatus(NewsStatus newsStatus) {
		this.newsStatus = newsStatus;
	}

	public List<Image> getImageList() {
		return imageList;
	}

	public void setImageList(List<Image> imageList) {
		this.imageList = imageList;
	}
}
