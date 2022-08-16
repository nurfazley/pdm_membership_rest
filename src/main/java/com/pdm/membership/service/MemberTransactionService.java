package com.pdm.membership.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdm.membership.dao.MemberTransactionDAO;
import com.pdm.membership.model.MemberTransaction;

@Service("memberTransactionService")
@Transactional
public class MemberTransactionService {
	
	@Autowired
	private MemberTransactionDAO memberTransactionDAO;
	

	public List<MemberTransaction> findMemberTransactionByMemberNo(String memberNo) {
		return memberTransactionDAO.findMemberTransactionByMemberNo(memberNo);
	}
	
	public List<MemberTransaction> saveAll(List<MemberTransaction> memberTransactionList) {
		return memberTransactionDAO.saveAll(memberTransactionList);
	}
	
	public MemberTransaction save(MemberTransaction transaction) {
		return memberTransactionDAO.save(transaction);
	}
	
	public void truncateMemberTransactionAndGrossSystemMemberTable() {
		memberTransactionDAO.truncateMemberTransactionAndGrossSystemMemberTable();
	}
	
	public void truncateMemberTransactionTable() {
		memberTransactionDAO.truncateMemberTransactionTable();
	}
}
