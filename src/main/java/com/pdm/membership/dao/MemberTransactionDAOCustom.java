package com.pdm.membership.dao;

import java.util.List;

import com.pdm.membership.model.MemberTransaction;

public interface MemberTransactionDAOCustom {
	
	public List<MemberTransaction> findMemberTransactionByMemberNo(String memberId);
	
	public void truncateMemberTransactionAndGrossSystemMemberTable();
	
	public void truncateMemberTransactionTable();
}
