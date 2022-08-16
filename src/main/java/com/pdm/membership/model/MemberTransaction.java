package com.pdm.membership.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "MemberTransaction")
public class MemberTransaction implements Serializable {

	private static final long serialVersionUID = -5976757664133089682L;

	@Id
	@SequenceGenerator(name = "memberTransactionSequence", sequenceName = "member_transaction_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memberTransactionSequence")
	private Long id;
	
	private Date transactionDate;
	
	private String location;
	
	private String registerNo;
	
	private String receiptNo;
	
	private Double quantity = 0.0d;
	
	private Double pointEarnt = 0.0d;
	
	private Double pointRedeemed = 0.0d;
	
	private Double totalPointBalance = 0.0d;
	
	private Date recordProcessedDateTime;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Member member;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private GrossSystemMember grossSystemMember;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRegisterNo() {
		return registerNo;
	}

	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getPointEarnt() {
		return pointEarnt;
	}

	public void setPointEarnt(Double pointEarnt) {
		this.pointEarnt = pointEarnt;
	}

	public Double getPointRedeemed() {
		return pointRedeemed;
	}

	public void setPointRedeemed(Double pointRedeemed) {
		this.pointRedeemed = pointRedeemed;
	}

	public Double getTotalPointBalance() {
		return totalPointBalance;
	}

	public void setTotalPointBalance(Double totalPointBalance) {
		this.totalPointBalance = totalPointBalance;
	}

	public Date getRecordProcessedDateTime() {
		return recordProcessedDateTime;
	}

	public void setRecordProcessedDateTime(Date recordProcessedDateTime) {
		this.recordProcessedDateTime = recordProcessedDateTime;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public GrossSystemMember getGrossSystemMember() {
		return grossSystemMember;
	}

	public void setGrossSystemMember(GrossSystemMember grossSystemMember) {
		this.grossSystemMember = grossSystemMember;
	}
}
