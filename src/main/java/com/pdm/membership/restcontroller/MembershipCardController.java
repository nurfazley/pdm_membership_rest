package com.pdm.membership.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdm.membership.model.MembershipCard;
import com.pdm.membership.service.MembershipCardService;

@RestController
public class MembershipCardController {
	
	@Autowired
	private MembershipCardService membershipCardService;
	
	
	@GetMapping("/membershipcards/card/{cardNumber}")
	public MembershipCard findMembershipCardByCardNumber(@PathVariable String cardNumber) {
		MembershipCard membershipCard = membershipCardService.findMembershipCardByCardNumber(cardNumber);
		printPrettyJson(membershipCard);
		
		return membershipCard;
	}
	
	@GetMapping("/membershipcards/member/{memberId}")
	public MembershipCard findMembershipCardByMemberId(@PathVariable String memberId) {
		MembershipCard membershipCard = membershipCardService.findMembershipCardByMemberId(memberId);
		printPrettyJson(membershipCard);
		
		return membershipCard;
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
