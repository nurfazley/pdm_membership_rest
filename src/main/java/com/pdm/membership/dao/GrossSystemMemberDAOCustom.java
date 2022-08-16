package com.pdm.membership.dao;

import com.pdm.membership.model.GrossSystemMember;

public interface GrossSystemMemberDAOCustom {
	
	public GrossSystemMember findByIc(String ic);
	
	public void truncateGrossSystemMemberTable();
}
