package com.pdm.membership.model;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Company")
public class Company implements Serializable {

	private static final long serialVersionUID = 5294306033222484058L;

	@Id
	@SequenceGenerator(name = "companySequence", sequenceName = "company_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companySequence")
	private Long id;
	
	@Column(name = "companyCode")
	private String companyCode;
	
	@Column(name = "companyName")
	private String companyName;
	
	@Column(name = "companyAddress")
	private String companyAddress;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	@JsonIgnore
	private List<Member> staffList;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public List<Member> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<Member> staffList) {
		this.staffList = staffList;
	}
}
