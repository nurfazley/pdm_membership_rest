package com.pdm.membership.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pdm.membership.model.MemberTransaction;

@Repository("memberTransactionDAO")
public interface MemberTransactionDAO extends JpaRepository<MemberTransaction, Long>, JpaSpecificationExecutor<MemberTransaction>, MemberTransactionDAOCustom {}
