package com.pdm.membership.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pdm.membership.model.ForgetPasswordToken;

@Repository("tokenDAO")
public interface ForgetPasswordTokenDAO extends JpaRepository<ForgetPasswordToken, Long>, JpaSpecificationExecutor<ForgetPasswordToken>, ForgetPasswordTokenDAOCustom {}
