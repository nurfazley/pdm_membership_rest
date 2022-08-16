package com.pdm.membership.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pdm.membership.model.Email;
import com.pdm.membership.model.lookup.EmailStatus;


public class EmailDAOImpl implements EmailDAOCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public List<Email> findAllByStatus(EmailStatus emailStatus) {
		TypedQuery<Email> query = entityManager.createQuery("FROM com.pdm.membership.model.Email email WHERE email.status = :emailStatus", Email.class);
		query.setParameter("emailStatus", emailStatus);
		
		return query.getResultList();
	}
}
