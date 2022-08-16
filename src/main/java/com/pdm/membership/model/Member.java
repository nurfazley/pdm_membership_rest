package com.pdm.membership.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pdm.membership.model.base.AbstractMember;


@Entity
@Table(name = "Member")
public class Member extends AbstractMember {

	private static final long serialVersionUID = 2655520081680865867L;

	@Column(name = "memberId", unique = true)
	private String memberId;
	
	@Column(name = "fullName")
	private String fullName;

	@Column(name = "address")
	private String address;
	
	@Column(name = "postCode")
	private int postCode;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "ic")
	private String ic;
	
	@Column(name = "phoneNo")
	private String phoneNo;
	
	@Email
	@Column(name = "email")
	private String email;
	
	@Column(name = "staffId")
	private String staffId;

	@Column(name = "isMember")
	private boolean isMember = true;
	
	@Column(name = "isStaff")
	private boolean isStaff = false;
	
	@Column(name = "isMobileAppActivated")
	private boolean isMobileAppActivated = true;
	
	@Column(name = "isActive")
	private boolean isActive = false;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "membershipCard_id", referencedColumnName = "id")
	private MembershipCard membershipCard;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Company company;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staffPurchaseScheme_id", referencedColumnName = "id")
	private StaffPurchaseScheme staffPurchaseScheme;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
	@JsonIgnore
	private List<Voucher> voucherList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
	@JsonIgnore
	private List<ForgetPasswordToken> forgetPasswordTokenList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
	@JsonIgnore
	private List<ApplicationLog> applicationLogList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
	@JsonIgnore
	private List<MemberTransaction> memberTransactionList;
	
	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
	
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@JsonProperty(value = "isMember")
	public boolean isMember() {
		return isMember;
	}

	public void setMember(boolean isMember) {
		this.isMember = isMember;
	}

	@JsonProperty(value = "isStaff")
	public boolean isStaff() {
		return isStaff;
	}

	public void setStaff(boolean isStaff) {
		this.isStaff = isStaff;
	}

	@JsonProperty(value = "isMobileAppActivated")
	public boolean isMobileAppActivated() {
		return isMobileAppActivated;
	}

	public void setMobileAppActivated(boolean isMobileAppActivated) {
		this.isMobileAppActivated = isMobileAppActivated;
	}

	@JsonProperty(value = "isActive")
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public MembershipCard getMembershipCard() {
		return membershipCard;
	}

	public void setMembershipCard(MembershipCard membershipCard) {
		this.membershipCard = membershipCard;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public StaffPurchaseScheme getStaffPurchaseScheme() {
		return staffPurchaseScheme;
	}

	public void setStaffPurchaseScheme(StaffPurchaseScheme staffPurchaseScheme) {
		this.staffPurchaseScheme = staffPurchaseScheme;
	}

	public List<Voucher> getVoucherList() {
		return voucherList;
	}

	public void setVoucherList(List<Voucher> voucherList) {
		this.voucherList = voucherList;
	}

	public List<ForgetPasswordToken> getForgetPasswordTokenList() {
		return forgetPasswordTokenList;
	}

	public void setForgetPasswordTokenList(List<ForgetPasswordToken> forgetPasswordTokenList) {
		this.forgetPasswordTokenList = forgetPasswordTokenList;
	}

	public List<ApplicationLog> getApplicationLogList() {
		return applicationLogList;
	}

	public void setApplicationLogList(List<ApplicationLog> applicationLogList) {
		this.applicationLogList = applicationLogList;
	}

	public List<MemberTransaction> getMemberTransactionList() {
		return memberTransactionList;
	}

	public void setMemberTransactionList(List<MemberTransaction> memberTransactionList) {
		this.memberTransactionList = memberTransactionList;
	}
}
