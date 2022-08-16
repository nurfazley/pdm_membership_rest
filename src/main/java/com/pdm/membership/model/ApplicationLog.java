package com.pdm.membership.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "ApplicationLog")
public class ApplicationLog implements Serializable {

	private static final long serialVersionUID = -474292198988197758L;

	@Id
	@SequenceGenerator(name = "appLogSequence", sequenceName = "app_log_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appLogSequence")
	private Long id;
	
	@Column(name = "applicationLogDateTime")
	private Date applicationLogDateTime;
	
	@Column(name = "applicationLogString")
	private String applicationLogString;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Member member;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getApplicationLogDateTime() {
		return applicationLogDateTime;
	}

	public void setApplicationLogDateTime(Date applicationLogDateTime) {
		this.applicationLogDateTime = applicationLogDateTime;
	}

	public String getApplicationLogString() {
		return applicationLogString;
	}

	public void setApplicationLogString(String applicationLogString) {
		this.applicationLogString = applicationLogString;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
