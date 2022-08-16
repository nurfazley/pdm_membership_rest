package com.pdm.membership.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdm.membership.model.Company;
import com.pdm.membership.model.Member;
import com.pdm.membership.service.CompanyService;
import com.pdm.membership.service.MemberService;


@RestController
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/companies/{companyCode}")
	public Company getCompanyByCompanyCode(@PathVariable String companyCode) {
		Company company = companyService.findCompanyByCompanyCode(companyCode);
		printPrettyJson(company);
		
		return company;
	}
	
	@GetMapping("/companies/member/{memberId}")
	public Company getCompanyByMemberId(@PathVariable String memberId) {
		Member member = memberService.findByMemberId(memberId);
		Company company = member.getCompany();
		printPrettyJson(company);
		
		return company;
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
