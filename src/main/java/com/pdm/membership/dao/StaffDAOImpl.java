package com.pdm.membership.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pdm.membership.model.Staff;

public class StaffDAOImpl implements StaffDAOCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public Staff findStaffByIc(String ic) {
		TypedQuery<Staff> query = entityManager.createQuery("FROM Staff s WHERE s.ic = :ic", Staff.class);
		query.setParameter("ic", ic);
		Staff staff = null;
		
		try {
			staff = query.getSingleResult();
		}
		catch (NoResultException nre) {
			staff = null;
		}
		
		return staff;
	}

	@Override
	public Staff findStaffByPhoneNumber(String phoneNumber) {
		TypedQuery<Staff> query = entityManager.createQuery("FROM Staff s WHERE s.phoneNumber = :phoneNumber", Staff.class);
		query.setParameter("phoneNumber", phoneNumber);
		Staff staff = null;
		
		try {
			staff = query.getSingleResult();
		}
		catch (NoResultException nre) {
			staff = null;
		}
		
		return staff;
	}

	@Override
	public Staff findStaffByEmail(String email) {
		TypedQuery<Staff> query = entityManager.createQuery("FROM Staff s WHERE s.email = :email", Staff.class);
		query.setParameter("email", email);
		Staff staff = null;
		
		try {
			staff = query.getSingleResult();
		}
		catch (NoResultException nre) {
			staff = null;
		}
		
		return staff;
	}
}
