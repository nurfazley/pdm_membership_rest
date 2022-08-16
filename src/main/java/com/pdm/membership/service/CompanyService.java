package com.pdm.membership.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdm.membership.dao.CompanyDAO;
import com.pdm.membership.model.Company;

@Service("companyService")
@Transactional
public class CompanyService {
	
	@Autowired
	private CompanyDAO companyDAO;
	
	
	public Company findCompanyByCompanyCode(String companyCode) {
		return companyDAO.findCompanyByCompanyCode(companyCode);
	}
}
