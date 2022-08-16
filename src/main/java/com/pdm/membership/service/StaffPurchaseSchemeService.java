package com.pdm.membership.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdm.membership.dao.StaffPurchaseSchemeDAO;
import com.pdm.membership.model.StaffPurchaseScheme;

@Service("staffPurchaseSchemeService")
@Transactional
public class StaffPurchaseSchemeService {

	@Autowired
	private StaffPurchaseSchemeDAO staffPurchaseSchemeDAO;
	
	
	public StaffPurchaseScheme saveStaffPurchaseScheme(StaffPurchaseScheme scheme) {
		return staffPurchaseSchemeDAO.save(scheme);
	}
}
