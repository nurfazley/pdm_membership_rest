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

import com.pdm.membership.model.lookup.ForgetPasswordTokenStatus;


@Entity
@Table(name = "ForgetPasswordToken")
public class ForgetPasswordToken implements Serializable {

	private static final long serialVersionUID = 9169364322143621621L;

	@Id
	@SequenceGenerator(name = "tokenSequence", sequenceName = "token_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tokenSequence")
	private Long id;
	
	@Column(name = "createdDateTime")
	private Date createdDateTime;
	
	@Column(name = "tokenString")
	private String tokenString;
	
	@Column(name = "tokenStatus")
	private ForgetPasswordTokenStatus forgetPasswordTokenStatus = ForgetPasswordTokenStatus.active;

	@ManyToOne(fetch = FetchType.EAGER)
	private Member member;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getTokenString() {
		return tokenString;
	}

	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}

	public ForgetPasswordTokenStatus getForgetPasswordTokenStatus() {
		return forgetPasswordTokenStatus;
	}

	public void setForgetPasswordTokenStatus(ForgetPasswordTokenStatus forgetPasswordTokenStatus) {
		this.forgetPasswordTokenStatus = forgetPasswordTokenStatus;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	public String getTokenPrefix() {
		return tokenString.substring(0, 4);
	}
	
	public String getTokenSuffix() {
		return tokenString.substring(4, 8);
	}
}
