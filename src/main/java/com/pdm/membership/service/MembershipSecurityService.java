package com.pdm.membership.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdm.membership.dao.MemberDAO;
import com.pdm.membership.model.Member;
import com.pdm.membership.security.MhPasswordEncoder;

@Service("membershipSecurityService")
@Transactional
public class MembershipSecurityService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private MhPasswordEncoder mhPasswordEncoder;
	
	
	public Member authenticateUser(String userName, String password) {
		boolean authenticated = false;
		Member member = null;
		
		try {
			member = memberDAO.findMemberByUserName(userName);
			
			if (member != null && member.getId() != null) {
				authenticated = mhPasswordEncoder.isPasswordMatches(password, member.getPassword(), member.getSalt());
				
				// If password is matched, update system user profile
				if (authenticated) {
					member.setLastLoginDateTime(new Date());
					memberDAO.save(member);
				}
				// If password is not matched, set system user to null
				else {
					member = null;
				}
			}
		}
		catch (Exception e) {
			member = null;
		}
				
		return member;
	}
	
	public String encryptPassword(String password, String salt) {
		return MhPasswordEncoder.encrypt(password, salt);
	}
}
