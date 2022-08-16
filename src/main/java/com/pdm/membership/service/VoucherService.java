package com.pdm.membership.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdm.membership.dao.VoucherDAO;
import com.pdm.membership.model.Voucher;

@Service("voucherService")
@Transactional
public class VoucherService {

	@Autowired
	private VoucherDAO voucherDAO;
	
	
	public List<Voucher> findAll() {
		return voucherDAO.findAll();
	}
	
	public List<Voucher> findVoucherByMemberId(Long id) {
		return voucherDAO.findVoucherByMemberId(id);
	}
}
