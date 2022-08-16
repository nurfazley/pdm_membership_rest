package com.pdm.membership.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pdm.membership.model.RedeemableItem;

public class RedeemableItemDAOImpl implements RedeemableItemDAOCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public RedeemableItem findByItemCode(String itemCode) {
		TypedQuery<RedeemableItem> query = entityManager.createQuery("FROM RedeemableItem r WHERE r.code = :itemCode", RedeemableItem.class);
		query.setParameter("itemCode", itemCode);
		
		return query.getSingleResult();
	}
}
