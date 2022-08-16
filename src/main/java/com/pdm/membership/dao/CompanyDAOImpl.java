package com.pdm.membership.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pdm.membership.model.Company;

public class CompanyDAOImpl implements CompanyDAOCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public Company findCompanyByCompanyCode(String companyCode) {
		TypedQuery<Company> query = entityManager.createQuery("FROM Company c WHERE c.companyCode = :companyCode", Company.class);
		query.setParameter("companyCode", companyCode);
		
		return Optional.of(query.getSingleResult()).orElse(new Company());
	}
}
