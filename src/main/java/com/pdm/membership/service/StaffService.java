package com.pdm.membership.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdm.membership.dao.StaffDAO;
import com.pdm.membership.model.Staff;

@Service("staffService")
@Transactional
public class StaffService {
	
	@Autowired
	private StaffDAO staffDAO;
	

	public Staff findStaffByIc(String ic) {
		return staffDAO.findStaffByIc(ic);
	}
	
	public Staff findStaffByPhoneNumber(String phoneNumber) {
		return staffDAO.findStaffByPhoneNumber(phoneNumber);
	}
	
	public Staff findStaffByEmail(String email) {
		return staffDAO.findStaffByEmail(email);
	}
}
