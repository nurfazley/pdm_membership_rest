package com.pdm.membership.dao;

import com.pdm.membership.model.Staff;

public interface StaffDAOCustom {

	public Staff findStaffByIc(String ic);
	
	public Staff findStaffByPhoneNumber(String phoneNumber);
	
	public Staff findStaffByEmail(String email);
}
