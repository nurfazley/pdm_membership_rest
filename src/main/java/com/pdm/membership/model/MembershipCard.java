package com.pdm.membership.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "MembershipCard")
public class MembershipCard implements Serializable {

	private static final long serialVersionUID = 5415684716919406531L;

	@Id
	@SequenceGenerator(name = "membershipCardSequence", sequenceName = "membership_card_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "membershipCardSequence")
	private Long id;
	
	@Column(name = "lastUpdateDateTime")
	private Date lastUpdateDateTime;
	
	@Column(name = "cardNumber", unique = true)
	private String cardNumber;
	
	@Column(name = "cardType")
	private String cardType;
	
	@Column(name = "issuedPointAmount")
	private Integer issuedPointAmount = 0;
	
	@Column(name = "activePointAmount")
	private Integer activePointAmount = 0;

	@Column(name = "expiredPointAmount")
	private Integer expiredPointAmount = 0;

	@Column(name = "redeemedPointAmount")
	private Integer redeemedPointAmount = 0;
	
	@OneToOne(mappedBy = "membershipCard")
	@JsonIgnore
	private Member member;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLastUpdateDateTime() {
		return lastUpdateDateTime;
	}

	public void setLastUpdateDateTime(Date lastUpdateDateTime) {
		this.lastUpdateDateTime = lastUpdateDateTime;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Integer getIssuedPointAmount() {
		return issuedPointAmount;
	}

	public void setIssuedPointAmount(Integer issuedPointAmount) {
		this.issuedPointAmount = issuedPointAmount;
	}

	public Integer getActivePointAmount() {
		return activePointAmount;
	}

	public void setActivePointAmount(Integer activePointAmount) {
		this.activePointAmount = activePointAmount;
	}

	public Integer getExpiredPointAmount() {
		return expiredPointAmount;
	}

	public void setExpiredPointAmount(Integer expiredPointAmount) {
		this.expiredPointAmount = expiredPointAmount;
	}

	public Integer getRedeemedPointAmount() {
		return redeemedPointAmount;
	}

	public void setRedeemedPointAmount(Integer redeemedPointAmount) {
		this.redeemedPointAmount = redeemedPointAmount;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	@Override
	public String toString() {
		return "MembershipCard[" + id 
								 + ", "
								 + lastUpdateDateTime
								 + ", "
								 + cardNumber
								 + ", "
								 + cardType
								 + ", "
								 + issuedPointAmount
								 + ", "
								 + activePointAmount
								 + ", "
								 + expiredPointAmount
								 + ", "
								 + redeemedPointAmount
								 + "]";
	}
}
