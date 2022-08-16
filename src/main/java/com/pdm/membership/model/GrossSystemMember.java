package com.pdm.membership.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "GrossMember")
public class GrossSystemMember implements Serializable {

	private static final long serialVersionUID = -3423459599702057758L;

	@Id
	@SequenceGenerator(name = "grossMemberSequence", sequenceName = "gross_member_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grossMemberSequence")
	private Long id;
	
	@Column(name = "memberId")
	private String memberId;
	
	@Column(name = "fullName")
	private String fullName;
	
	@Column(name = "ic")
	private String ic;
	
	@Column(name = "phoneNo")
	private String phoneNo;
	
	@Email
	@Column(name = "email")
	private String email;
	
	@Column(name = "totalSpent")
	private BigDecimal totalSpent = new BigDecimal(0);
	
	@Column(name = "issuedPointAmount")
	private int issuedPointAmount = 0;
	
	@Column(name = "activePointAmount")
	private int activePointAmount = 0;
	
	@Column(name = "expiredPointAmount")
	private int expiredPointAmount = 0;
	
	@Column(name = "redeemedPointAmount")
	private int redeemedPointAmount = 0;
	
	@Column(name = "lastUpdateDateTime")
	private Date lastUpdateDateTime;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grossSystemMember")
	@JsonIgnore
	private List<MemberTransaction> memberTransactionList;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIc() {
		return ic;
	}

	public void setIc(String ic) {
		this.ic = ic;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getTotalSpent() {
		return totalSpent;
	}

	public void setTotalSpent(BigDecimal totalSpent) {
		this.totalSpent = totalSpent;
	}

	public int getIssuedPointAmount() {
		return issuedPointAmount;
	}

	public void setIssuedPointAmount(int issuedPointAmount) {
		this.issuedPointAmount = issuedPointAmount;
	}

	public int getActivePointAmount() {
		return activePointAmount;
	}

	public void setActivePointAmount(int activePointAmount) {
		this.activePointAmount = activePointAmount;
	}

	public int getExpiredPointAmount() {
		return expiredPointAmount;
	}

	public void setExpiredPointAmount(int expiredPointAmount) {
		this.expiredPointAmount = expiredPointAmount;
	}

	public int getRedeemedPointAmount() {
		return redeemedPointAmount;
	}

	public void setRedeemedPointAmount(int redeemedPointAmount) {
		this.redeemedPointAmount = redeemedPointAmount;
	}

	public Date getLastUpdateDateTime() {
		return lastUpdateDateTime;
	}

	public void setLastUpdateDateTime(Date lastUpdateDateTime) {
		this.lastUpdateDateTime = lastUpdateDateTime;
	}

	public List<MemberTransaction> getMemberTransactionList() {
		return memberTransactionList;
	}

	public void setMemberTransactionList(List<MemberTransaction> memberTransactionList) {
		this.memberTransactionList = memberTransactionList;
	}
}
