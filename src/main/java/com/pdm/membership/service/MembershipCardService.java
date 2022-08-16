package com.pdm.membership.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdm.membership.dao.MembershipCardDAO;
import com.pdm.membership.model.MembershipCard;

@Service("membershipCardService")
@Transactional
public class MembershipCardService {

	@Autowired
	private MembershipCardDAO membershipCardDAO;
	
	
	public MembershipCard saveMembershipCard(MembershipCard membershipCard) {
		return membershipCardDAO.save(membershipCard);
	}
	
	public MembershipCard findMembershipCardByCardNumber(String cardNumber) {
		return membershipCardDAO.findMembershipCardByCardNumber(cardNumber);
	}
	
	public MembershipCard findMembershipCardByMemberId(String memberId) {
		return membershipCardDAO.findMembershipCardByMemberId(memberId);
	}
}
