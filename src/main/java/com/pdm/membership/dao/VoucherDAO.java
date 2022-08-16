package com.pdm.membership.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pdm.membership.model.Voucher;

@Repository("voucherDAO")
public interface VoucherDAO extends JpaRepository<Voucher, Long>, JpaSpecificationExecutor<Voucher>, VoucherDAOCustom {}
