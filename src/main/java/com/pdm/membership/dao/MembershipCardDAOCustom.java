package com.pdm.membership.dao;

import com.pdm.membership.model.MembershipCard;

public interface MembershipCardDAOCustom {
	
	public MembershipCard findMembershipCardByCardNumber(String cardNumber);
	
	public MembershipCard findMembershipCardByMemberId(String memberId);
	
	public void truncateMembershipCardTable();
}
