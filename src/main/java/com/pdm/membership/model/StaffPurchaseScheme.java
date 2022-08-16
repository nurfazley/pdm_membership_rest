package com.pdm.membership.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "StaffPurchaseScheme")
public class StaffPurchaseScheme implements Serializable {

	private static final long serialVersionUID = -3428012218296775472L;

	@Id
	@SequenceGenerator(name = "membershipCardSequence", sequenceName = "membership_card_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "membershipCardSequence")
	private Long id;
	
	@Column(name = "lastUpdateDateTime")
	private Date lastUpdateDateTime;
	
	@Column(name = "allocationAmount")
	private BigDecimal allocationAmount = new BigDecimal(0);
	
	@Column(name = "usedAmount")
	private BigDecimal usedAmount = new BigDecimal(0);
	
	@Column(name = "balanceAmount")
	private BigDecimal balanceAmount = new BigDecimal(0);
	
	@OneToOne(mappedBy = "staffPurchaseScheme")
	@JsonIgnore
	private Member staff;
	

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

	public BigDecimal getAllocationAmount() {
		return allocationAmount;
	}

	public void setAllocationAmount(BigDecimal allocationAmount) {
		this.allocationAmount = allocationAmount;
	}

	public BigDecimal getUsedAmount() {
		return usedAmount;
	}

	public void setUsedAmount(BigDecimal usedAmount) {
		this.usedAmount = usedAmount;
	}

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Member getStaff() {
		return staff;
	}

	public void setStaff(Member staff) {
		this.staff = staff;
	}
}
