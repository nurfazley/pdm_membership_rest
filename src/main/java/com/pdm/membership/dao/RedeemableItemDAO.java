package com.pdm.membership.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pdm.membership.model.RedeemableItem;

@Repository("redeemableItemDAO")
public interface RedeemableItemDAO extends JpaRepository<RedeemableItem, Long>, JpaSpecificationExecutor<RedeemableItem>, RedeemableItemDAOCustom {}
