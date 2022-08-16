package com.pdm.membership.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pdm.membership.model.GrossSystemMember;


@Repository("grossSystemMemberDAO")
public interface GrossSystemMemberDAO extends JpaRepository<GrossSystemMember, String>, JpaSpecificationExecutor<GrossSystemMember>, GrossSystemMemberDAOCustom {}
