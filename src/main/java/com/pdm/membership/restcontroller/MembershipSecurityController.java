package com.pdm.membership.restcontroller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdm.membership.model.Member;
import com.pdm.membership.service.MemberService;
import com.pdm.membership.service.MembershipSecurityService;


@RestController
public class MembershipSecurityController {

	@Autowired
	private MembershipSecurityService membershipSecurityService;
	
	@Autowired
	private MemberService memberService;
	
	
	@PostMapping("/security/authenticate")
	public Member authenticateMember(@RequestBody Map<String, String> body) {
		String userName = body.get("userName");
		String password = body.get("password");
		Member member = null;
		
		System.out.println("Username: " + userName + ", Password: " + password);
				
		if (userName != null && password != null) {
			member = membershipSecurityService.authenticateUser(userName, password);
			
			if (member != null && member.isPersisted()) {
				String fpnToken = body.get("fpnToken");
				
				member.setLastLoginDateTime(new Date());
				member.setFpnToken(fpnToken);
				member = memberService.update(member);
				
				printPrettyJson(member);
			}
		}
		
		return member;
	}
	
	@PostMapping("/security/logout")
	public Member logout(@RequestBody Map<String, String> body) {
		String memberId = body.get("memberId");
		Member member = memberService.findByMemberId(memberId);
		
		if (member != null && member.isPersisted()) {
			member.setLastLogoutDateTime(new Date());
			member = memberService.update(member);
		}
		
		return member;
	}
	
	private String printPrettyJson(Object obj) {
		String jsonStr = new String();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
			System.out.println(jsonStr);
		} 
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonStr;
	}
}
