package com.pdm.membership.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.pdm.membership.model.MembershipCard;

public class MembershipCardDAOImpl implements MembershipCardDAOCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public MembershipCard findMembershipCardByCardNumber(String cardNumber) {
		TypedQuery<MembershipCard> query = entityManager.createQuery("FROM MembershipCard m WHERE m.cardNumber = :cardNumber", MembershipCard.class);
		query.setParameter("cardNumber", cardNumber);
		
		return Optional.of(query.getSingleResult()).orElse(new MembershipCard());
	}


	@Override
	public MembershipCard findMembershipCardByMemberId(String memberId) {
		TypedQuery<MembershipCard> query = entityManager.createQuery("FROM MembershipCard m WHERE m.member.memberId = :memberId", MembershipCard.class);
		query.setParameter("memberId", memberId);
		
		return Optional.of(query.getSingleResult()).orElse(new MembershipCard());
	}


	@Override
	public void truncateMembershipCardTable() {
		String sqlString = "TRUNCATE TABLE membership_card";
		Query query = entityManager.createNativeQuery(sqlString);
		query.executeUpdate();
	}
}
