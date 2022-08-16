package com.pdm.membership.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pdm.membership.model.MembershipCard;

@Repository("membershipCardDAO")
public interface MembershipCardDAO extends JpaRepository<MembershipCard, Long>, JpaSpecificationExecutor<MembershipCard>, MembershipCardDAOCustom {}
