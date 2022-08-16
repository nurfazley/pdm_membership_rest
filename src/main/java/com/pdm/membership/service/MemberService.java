package com.pdm.membership.service;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdm.membership.dao.MemberDAO;
import com.pdm.membership.dao.StaffDAO;
import com.pdm.membership.model.Member;
import com.pdm.membership.model.Staff;

@Service("memberService")
@Transactional
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private StaffDAO staffDAO;
	
	
	public Member save(Member member) {
		return memberDAO.save(member);
	}
	
	public Member update(Member member) {
		return memberDAO.save(member);
	}
	
	public boolean checkMemberIsStaffByIc(String ic) {
		Staff staff = staffDAO.findStaffByIc(ic);
		
		if (staff != null && staff.getIc() != null) {
			return true;
		}
		
		return false;
	}
	
	public Member findById(Long id) {
		return memberDAO.findById(id).orElseGet(() -> new Member());
	}
	
	public Member findByMemberId(String memberId) {
		Member member = null;
		
		try {
			member = memberDAO.findMemberByMemberId(memberId);
		}
		catch(Exception e) {
			member = null;
		}
		
		return member;
	}
	
	public Member findByIc(String ic) {
		Member member = null;
		
		try {
			member = memberDAO.findMemberByIc(ic);
		}
		catch(Exception e) {
			member = null;
		}
		
		return member;
	}
	
	public Member findByEmail(String email) {
		Member member = null;
		
		try {
			member = memberDAO.findMemberByEmail(email);
		}
		catch(Exception e) {
			member = null;
		}
		
		return member;
	}
	
	public Member findByMobileNo(String mobileNo) {
		Member member = null;
		
		try {
			member = memberDAO.findMemberByMobileNo(mobileNo);
		}
		catch(Exception e) {
			member = null;
		}
		
		return member;
	}
	
	public Member findByUserName(String userName) {
		Member member = null;
		
		try {
			member = memberDAO.findMemberByUserName(userName);
		}
		catch(Exception e) {
			member = null;
		}
		
		return member;
	}
	
	public List<Member> findAll() {
		return memberDAO.findAll();
	}
	
	public synchronized String generateMemberId() {
		String lastMemberId = memberDAO.getLastMemberId();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String runningNumberFormat = null;
		
		if (lastMemberId != null && !lastMemberId.equals("")) {
			int value = Integer.parseInt(lastMemberId.substring(4, 12)) + 1;
			runningNumberFormat = String.format("%08d", value);
		}
		else {
			runningNumberFormat = String.format("%08d", 1);
		}
		
		return year + runningNumberFormat;
	}
}
