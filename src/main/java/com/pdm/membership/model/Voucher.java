package com.pdm.membership.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "Voucher")
public class Voucher implements Serializable {

	private static final long serialVersionUID = -4544215838340715645L;

	@Id
	@SequenceGenerator(name = "voucherSequence", sequenceName = "voucher_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voucherSequence")
	private Long id;
	
	@Column(name = "voucherDateTime")
	private Date voucherDateTime;
	
	@Column(name = "voucherId", unique = true)
	private String voucherId;
	
	@Column(name = "voucherName")
	private String voucherName;
	
	@Column(name = "voucherValue")
	private BigDecimal voucherValue;
	
	@Column(name = "validFrom")
	private Date validFrom;
	
	@Column(name = "validTo")
	private Date validTo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Member member;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getVoucherDateTime() {
		return voucherDateTime;
	}

	public void setVoucherDateTime(Date voucherDateTime) {
		this.voucherDateTime = voucherDateTime;
	}

	public String getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}

	public String getVoucherName() {
		return voucherName;
	}

	public void setVoucherName(String voucherName) {
		this.voucherName = voucherName;
	}

	public BigDecimal getVoucherValue() {
		return voucherValue;
	}

	public void setVoucherValue(BigDecimal voucherValue) {
		this.voucherValue = voucherValue;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
