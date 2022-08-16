package com.pdm.membership.dao;

import java.util.Calendar;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pdm.membership.model.Member;

public class MemberDAOImpl implements MemberDAOCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public Member findMemberByMemberId(String memberId) {
		TypedQuery<Member> query = entityManager.createQuery("FROM Member m WHERE m.memberId = :memberId", Member.class);
		query.setParameter("memberId", memberId);
		
		return Optional.of(query.getSingleResult()).orElse(new Member());
	}
	
	@Override
	public Member findMemberByUserName(String userName) {
		TypedQuery<Member> query = entityManager.createQuery("FROM Member m WHERE m.userName = :userName", Member.class);
		query.setParameter("userName", userName);
		
		return Optional.of(query.getSingleResult()).orElse(new Member());
	}
	
	@Override
	public Member findMemberByIc(String ic) {
		TypedQuery<Member> query = entityManager.createQuery("FROM Member m WHERE m.ic = :ic", Member.class);
		query.setParameter("ic", ic);
		
		return Optional.of(query.getSingleResult()).orElse(new Member());
	}
	
	@Override
	public Member findMemberByEmail(String email) {
		TypedQuery<Member> query = entityManager.createQuery("FROM Member m WHERE m.email = :email", Member.class);
		query.setParameter("email", email);
		
		return Optional.of(query.getSingleResult()).orElse(new Member());
	}
	
	@Override
	public Member findMemberByMobileNo(String mobileNo) {
		TypedQuery<Member> query = entityManager.createQuery("FROM Member m WHERE m.phoneNo = :phoneNo", Member.class);
		query.setParameter("phoneNo", mobileNo);
		
		return Optional.of(query.getSingleResult()).orElse(new Member());
	}
	
	@Override
	public String getLastMemberId() {
		String startOfMemberId = Integer.toString(Calendar.getInstance().get(Calendar.YEAR)) + "%";
		
		TypedQuery<Member> query = entityManager.createQuery("FROM Member m WHERE m.id = (SELECT MAX(m.id) FROM Member m WHERE m.memberId LIKE :startOfMemberId)", Member.class);
		query.setParameter("startOfMemberId", startOfMemberId);
		Member member = null;
		
		try {
			member = query.getSingleResult();
		}
		catch (Exception e) {
			member = null;
		}
		
		return member == null || member.getId() == null ? new String() : member.getMemberId();
	}
}
