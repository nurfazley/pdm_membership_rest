package com.pdm.membership.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pdm.membership.model.Company;


@Repository("companyDAO")
public interface CompanyDAO extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company>, CompanyDAOCustom {}
