package com.pdm.membership.dao;

import com.pdm.membership.model.Company;

public interface CompanyDAOCustom {

	public Company findCompanyByCompanyCode(String companyCode);
}
