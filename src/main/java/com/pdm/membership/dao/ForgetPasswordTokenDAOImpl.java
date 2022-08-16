package com.pdm.membership.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pdm.membership.model.ForgetPasswordToken;
import com.pdm.membership.model.lookup.ForgetPasswordTokenStatus;


public class ForgetPasswordTokenDAOImpl implements ForgetPasswordTokenDAOCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public ForgetPasswordToken getTokenFromTokenString(String tokenString) {
		TypedQuery<ForgetPasswordToken> query = entityManager.createQuery("FROM ForgetPasswordToken t WHERE t.tokenString = :tokenString", ForgetPasswordToken.class);
		query.setParameter("tokenString", tokenString);
		ForgetPasswordToken token = query.getSingleResult();
		
		return token;
	}
	
	public ForgetPasswordToken getTokenFromPrefixString(String prefix) {
		TypedQuery<ForgetPasswordToken> query = entityManager.createQuery("FROM ForgetPasswordToken t WHERE t.tokenString LIKE :prefix", ForgetPasswordToken.class);
		query.setParameter("prefix", prefix + "%");
		ForgetPasswordToken token = query.getSingleResult();
		
		return token;
	}

	@Override
	public ForgetPasswordToken getTokenFromTokenStringAndStatus(String tokenString, ForgetPasswordTokenStatus status) {
		TypedQuery<ForgetPasswordToken> query = entityManager.createQuery("FROM ForgetPasswordToken t WHERE t.tokenString = :tokenString AND forgetPasswordTokenStatus = :tokenStatus", ForgetPasswordToken.class);
		query.setParameter("tokenString", tokenString);
		query.setParameter("tokenStatus", status);
		ForgetPasswordToken token = query.getSingleResult();
		
		return token;
	}
}
