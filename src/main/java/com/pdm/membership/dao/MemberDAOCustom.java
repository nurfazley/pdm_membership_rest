package com.pdm.membership.dao;

import com.pdm.membership.model.Member;

public interface MemberDAOCustom {
	
	public Member findMemberByUserName(String userName);
	
	public Member findMemberByMemberId(String memberId);
	
	public Member findMemberByIc(String ic);
	
	public Member findMemberByEmail(String email);
	
	public Member findMemberByMobileNo(String mobileNo);
	
	public String getLastMemberId();
}
