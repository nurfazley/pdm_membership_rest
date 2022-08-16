package com.pdm.membership.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pdm.membership.model.Voucher;

public class VoucherDAIOImpl implements VoucherDAOCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public List<Voucher> findVoucherByMemberId(Long id) {
		TypedQuery<Voucher> query = entityManager.createQuery("FROM Voucher v WHERE v.member.id = :id", Voucher.class);
		query.setParameter("id", id);
		
		return query.getResultList();
	}
}
