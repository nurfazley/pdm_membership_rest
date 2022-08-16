package com.pdm.membership.dao;

import java.util.List;

import com.pdm.membership.model.Voucher;

public interface VoucherDAOCustom {
	
	public List<Voucher> findVoucherByMemberId(Long id);

}
