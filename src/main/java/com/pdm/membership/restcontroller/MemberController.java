package com.pdm.membership.restcontroller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdm.membership.model.Company;
import com.pdm.membership.model.ForgetPasswordToken;
import com.pdm.membership.model.GrossSystemMember;
import com.pdm.membership.model.Member;
import com.pdm.membership.model.MembershipCard;
import com.pdm.membership.model.Staff;
import com.pdm.membership.model.StaffPurchaseScheme;
import com.pdm.membership.model.lookup.ForgetPasswordTokenStatus;
import com.pdm.membership.model.lookup.MembershipCardType;
import com.pdm.membership.service.CompanyService;
import com.pdm.membership.service.ForgetPasswordTokenService;
import com.pdm.membership.service.GrossSystemMemberService;
import com.pdm.membership.service.MemberService;
import com.pdm.membership.service.MembershipCardService;
import com.pdm.membership.service.MembershipSecurityService;
import com.pdm.membership.service.StaffPurchaseSchemeService;
import com.pdm.membership.service.StaffService;


@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MembershipCardService membershipCardService;
	
	@Autowired
	private MembershipSecurityService membershipSecurityService;
	
	@Autowired
	private StaffPurchaseSchemeService staffPurchaseSchemeService;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private GrossSystemMemberService grossSystemMemberService;
	
	@Autowired
	private ForgetPasswordTokenService tokenService;
	
	private static final BigDecimal ALLOCATION_AMOUNT = new BigDecimal(200);
	
	
	@GetMapping("/members/{id}")
	public Member one(@PathVariable Long id) {
		Member member = memberService.findById(id);
		printPrettyJson(member);
		
		return member == null ? null : member;
	}
	
	@GetMapping("/members/checkMemberIsStaffByIc/{ic}")
	public Map<String, Boolean> checkMemberIsStaffByIc(@PathVariable String ic) {
		boolean isStaff = false;
		isStaff = memberService.checkMemberIsStaffByIc(ic);
		
		return Collections.singletonMap("isStaff", isStaff);
	}
	
	@GetMapping("/members/checkIsExistingMemberByIc/{ic}")
	public Map<String, Boolean> checkIsExistingMemberByIc(@PathVariable String ic) {
		boolean isExistingMember = false;
		isExistingMember = grossSystemMemberService.checkIsExistingMemberByIc(ic);
		
		return Collections.singletonMap("isExistingMember", isExistingMember);
	}
	
	@GetMapping("/members/ic/{ic}")
	public Member getMemberByIc(@PathVariable String ic) {
		Member member = memberService.findByIc(ic);
		printPrettyJson(member);
		
		return member == null ? null : member;
	}
	
	@GetMapping("/members/email/{email}")
	public Member getMemberByEmail(@PathVariable String email) {
		Member member = memberService.findByEmail(email);
		printPrettyJson(member);
		
		return member == null ? null : member;
	}
	
	@GetMapping("/members/mobile/{mobileNo}")
	public Member getMemberByMobileNo(@PathVariable String mobileNo) {
		Member member = memberService.findByMobileNo(mobileNo);
		printPrettyJson(member);
		
		return member == null ? null : member;
	}
	
	@GetMapping("/members/userName/{userName}")
	public Member getMemberByUserName(@PathVariable String userName) {
		Member member = memberService.findByUserName(userName);
		printPrettyJson(member);
		
		return member == null ? null : member;
	}
	
	@PostMapping("/members/changeMemberPassword")
	public Member changeMemberPassword(@RequestBody Map<String, String> map) {
		String tokenString = map.get("tokenString");
		String password = map.get("password");
		
		ForgetPasswordToken token = tokenService.getTokenFromTokenStringAndStatus(tokenString, ForgetPasswordTokenStatus.active);
		Member member = token.getMember();
		
		if (member != null) {
			member.setPassword(membershipSecurityService.encryptPassword(password, member.getSalt()));
			member = memberService.update(member);
		}
		
		return member;
	}
	
	@PostMapping("/members")
	public Member createMember(@RequestBody Member member) {
		Member newMember = new Member();
		
		// 1) Save as a new member
		synchronized (this) {
			member.setMemberId(memberService.generateMemberId());
			Date now = new Date();
			member.setCreatedDateTime(now);
			member.setUpdatedDateTime(now);
			
			member.setSalt(UUID.randomUUID().toString().replace("-", ""));
			member.setPassword(membershipSecurityService.encryptPassword(member.getPassword(), member.getSalt()));
			
			// Save into mobile db section
			newMember = memberService.save(member);
			printPrettyJson(newMember);
			
			// Save into Gross db section
		}
		
		// 2) Check whether existing member using hard card
		GrossSystemMember grossMember = grossSystemMemberService.findByIc(newMember.getIc());
		MembershipCard membershipCard = new MembershipCard();
		
		if (grossMember != null) {
			newMember.setMemberId(grossMember.getMemberId());
			newMember = memberService.update(newMember);
			
			membershipCard.setCardNumber(newMember.getIc());
			membershipCard.setCardType(MembershipCardType.NORMAL);
			membershipCard.setIssuedPointAmount(grossMember.getIssuedPointAmount());
			membershipCard.setActivePointAmount(grossMember.getActivePointAmount());
			membershipCard.setExpiredPointAmount(grossMember.getExpiredPointAmount());
			membershipCard.setRedeemedPointAmount(grossMember.getRedeemedPointAmount());
			membershipCard.setLastUpdateDateTime(grossMember.getLastUpdateDateTime());
			
			membershipCard.setMember(newMember);
		}
		else {
			membershipCard.setCardNumber(newMember.getIc());
			membershipCard.setCardType(MembershipCardType.NORMAL);
			membershipCard.setLastUpdateDateTime(new Date());
			
			membershipCard.setMember(newMember);
		}
		
		membershipCardService.saveMembershipCard(membershipCard);
		newMember.setMembershipCard(membershipCard);
		newMember = memberService.update(newMember);
		
		// 3) Check whether a staff in MUIP group of companies
		if (newMember.isStaff()) {
			StaffPurchaseScheme scheme = new StaffPurchaseScheme();
			scheme.setAllocationAmount(ALLOCATION_AMOUNT);
			scheme.setLastUpdateDateTime(new Date());
			scheme.setStaff(newMember);
			staffPurchaseSchemeService.saveStaffPurchaseScheme(scheme);
			
			Staff staff = staffService.findStaffByIc(newMember.getIc());
			Company company = companyService.findCompanyByCompanyCode(staff.getCompanyCode());
			
			if (staff != null && company != null) {
				newMember.setStaffId(staff.getStaffNo());
				newMember.setCompany(company);
			}
			
			newMember.setStaffPurchaseScheme(scheme);
			memberService.update(newMember);
		}
		
		return newMember;
	}
	
	@PutMapping("/members/{id}")
	public Member update(@RequestBody Member member, @PathVariable Long id) {
		Member existingMember = memberService.findById(id);
		existingMember.setAddress(member.getAddress());
		existingMember.setPostCode(member.getPostCode());
		existingMember.setState(member.getState());
		existingMember.setCountry(member.getCountry());
		existingMember.setUpdatedDateTime(new Date());
		
		member = memberService.update(existingMember);
		printPrettyJson(existingMember);
		
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
