package com.pdm.membership.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdm.membership.dao.GrossSystemMemberDAO;
import com.pdm.membership.model.GrossSystemMember;

@Service("grossSystemMemberService")
@Transactional
public class GrossSystemMemberService {

	@Autowired
	private GrossSystemMemberDAO grossSystemMemberDAO;
	
	
	public GrossSystemMember findByIc(String ic) {
		GrossSystemMember member = null;
		
		try {
			member = grossSystemMemberDAO.findByIc(ic);
		}
		catch (Exception e) {
			member = null;	
		}
		
		return member;
	}
	
	public boolean checkIsExistingMemberByIc(String ic) {
		GrossSystemMember member = null;
		boolean isExistingMember = false;
		
		try {
			member = grossSystemMemberDAO.findByIc(ic);
			isExistingMember = member == null ? false : true;
		}
		catch (Exception e) {
			member = null;
			isExistingMember = false;
		}
		
		return isExistingMember;
	}
	
	public List<GrossSystemMember> saveAll(List<GrossSystemMember> grossMemberList) {
		return grossSystemMemberDAO.saveAll(grossMemberList);
	}
	
	public void truncateGrossSystemMemberTable() {
		grossSystemMemberDAO.truncateGrossSystemMemberTable();
	}
}
