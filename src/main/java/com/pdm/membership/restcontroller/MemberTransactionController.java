package com.pdm.membership.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pdm.membership.model.MemberTransaction;
import com.pdm.membership.service.MemberTransactionService;


@RestController
public class MemberTransactionController {

	@Autowired
	private MemberTransactionService memberTransactionService;
	
	
	@GetMapping("/transactions/member/{memberId}")
	public List<MemberTransaction> findMemberTransactionByMemberNo(@PathVariable String memberId) {
		return memberTransactionService.findMemberTransactionByMemberNo(memberId);
	}
}
