package com.pdm.membership.model.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@MappedSuperclass
public class AbstractMember implements Serializable {

	private static final long serialVersionUID = 6033839293805082222L;

	@Id
	@SequenceGenerator(name = "memberSequence", sequenceName = "member_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memberSequence")
	private Long id;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "password")
	//@JsonIgnore
	private String password;
	
	@Column(name = "salt")
	//@JsonIgnore
	private String salt;
	
	@Column(name = "fpnToken")
	private String fpnToken;
	
	@Column(name = "createdDateTime")
	private Date createdDateTime;
	
	@Column(name = "updatedDateTime")
	private Date updatedDateTime;
	
	@Column(name = "lastLoginDateTime")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
	private Date lastLoginDateTime;
	
	@Column(name = "lastLogoutDateTime")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
	private Date lastLogoutDateTime;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getFpnToken() {
		return fpnToken;
	}

	public void setFpnToken(String fpnToken) {
		this.fpnToken = fpnToken;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public Date getLastLoginDateTime() {
		return lastLoginDateTime;
	}

	public void setLastLoginDateTime(Date lastLoginDateTime) {
		this.lastLoginDateTime = lastLoginDateTime;
	}

	public Date getLastLogoutDateTime() {
		return lastLogoutDateTime;
	}

	public void setLastLogoutDateTime(Date lastLogoutDateTime) {
		this.lastLogoutDateTime = lastLogoutDateTime;
	}

	public boolean isPersisted() {
		return id != null;
	}
	
	@Override
	public int hashCode() {
		if (getId() != null) {
			return getId().hashCode();
		}
		
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
	
		if (obj == null) {
			return false;
		}
	
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		AbstractMember other = (AbstractMember) obj;
		
		if (getId() == null || other.getId() == null) {
			return false;
		}
		
		return getId().equals(other.getId());
	}
}
