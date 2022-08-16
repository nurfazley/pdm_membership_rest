package com.pdm.membership.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pdm.membership.model.StaffPurchaseScheme;

@Repository("staffPurchaseSchemeDAO")
public interface StaffPurchaseSchemeDAO extends JpaRepository<StaffPurchaseScheme, Long>, JpaSpecificationExecutor<StaffPurchaseScheme> {}
