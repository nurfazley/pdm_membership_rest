package com.pdm.membership.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pdm.membership.model.Email;

@Repository("emailDAO")
public interface EmailDAO extends JpaRepository<Email, Long>, JpaSpecificationExecutor<Email>, EmailDAOCustom {}
