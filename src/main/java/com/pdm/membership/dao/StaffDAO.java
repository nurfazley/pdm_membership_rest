package com.pdm.membership.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pdm.membership.model.Staff;


@Repository("staffDAO")
public interface StaffDAO extends JpaRepository<Staff, Long>, JpaSpecificationExecutor<Staff>, StaffDAOCustom {}
