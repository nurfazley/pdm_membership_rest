package com.pdm.membership.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.pdm.membership.model.GrossSystemMember;

public class GrossSystemMemberDAOImpl implements GrossSystemMemberDAOCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public GrossSystemMember findByIc(String ic) {
		TypedQuery<GrossSystemMember> query = entityManager.createQuery("FROM GrossSystemMember g WHERE g.ic = :ic", GrossSystemMember.class);
		query.setParameter("ic", ic);
		
		return query.getSingleResult();
	}


	@Override
	public void truncateGrossSystemMemberTable() {
		String sqlString = "TRUNCATE TABLE gross_member RESTART IDENTITY";
		Query query = entityManager.createNativeQuery(sqlString);
		query.executeUpdate();
	}
}
