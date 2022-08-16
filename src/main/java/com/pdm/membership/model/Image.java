package com.pdm.membership.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Image")
public class Image implements Serializable {

	private static final long serialVersionUID = -1418728592962408981L;

	@Id
	@SequenceGenerator(name = "imageSequence", sequenceName = "image_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imageSequence")
	private Long id;
	
	@Column(name = "imageName")
	private String imageName;
	
	@Column(name = "contentType")
	private String contentType;
	
	@Column(name = "contentByte")
	private byte[] contentByte;

	@ManyToOne(fetch = FetchType.EAGER)
	private News news;
	
	@OneToOne(mappedBy = "image", fetch = FetchType.LAZY)
	@JsonIgnore
	private RedeemableItem redeemableItem;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getContentByte() {
		return contentByte;
	}

	public void setContentByte(byte[] contentByte) {
		this.contentByte = contentByte;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public RedeemableItem getRedeemableItem() {
		return redeemableItem;
	}

	public void setRedeemableItem(RedeemableItem redeemableItem) {
		this.redeemableItem = redeemableItem;
	}
}
