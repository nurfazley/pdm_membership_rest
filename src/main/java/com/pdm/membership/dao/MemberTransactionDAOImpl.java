package com.pdm.membership.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.pdm.membership.model.MemberTransaction;

public class MemberTransactionDAOImpl implements MemberTransactionDAOCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<MemberTransaction> findMemberTransactionByMemberNo(String memberId) {
		TypedQuery<MemberTransaction> query = entityManager.createQuery("FROM MemberTransaction mt WHERE mt.grossSystemMember.memberId = :memberId", MemberTransaction.class);
		query.setParameter("memberId", memberId);
		
		return query.getResultList();
	}


	@Override
	public void truncateMemberTransactionTable() {
		String sqlString = "TRUNCATE TABLE member_transaction RESTART IDENTITY";
		Query query = entityManager.createNativeQuery(sqlString);
		query.executeUpdate();
	}


	@Override
	public void truncateMemberTransactionAndGrossSystemMemberTable() {
		String sqlString = "TRUNCATE TABLE member_transaction, gross_member RESTART IDENTITY";
		Query query = entityManager.createNativeQuery(sqlString);
		query.executeUpdate();
	}
}
