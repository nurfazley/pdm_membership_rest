package com.pdm.membership.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdm.membership.model.Member;
import com.pdm.membership.model.StaffPurchaseScheme;
import com.pdm.membership.service.MemberService;

@RestController
public class StaffPurchaseSchemeController {
	
	@Autowired
	private MemberService memberService;
	

	@GetMapping("/scheme/member/{memberId}")
	public StaffPurchaseScheme getSchemeByMemberId(@PathVariable String memberId) {
		Member member = memberService.findByMemberId(memberId);
		StaffPurchaseScheme scheme = member.getStaffPurchaseScheme();
		printPrettyJson(scheme);
		
		return scheme;
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
