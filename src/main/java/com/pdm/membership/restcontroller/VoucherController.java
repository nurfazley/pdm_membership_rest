package com.pdm.membership.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdm.membership.model.Voucher;
import com.pdm.membership.service.VoucherService;

@RestController
public class VoucherController {

	@Autowired
	private VoucherService voucherService;
	
	
	@GetMapping("/vouchers/member/{memberId}")
	List<Voucher> findVoucherByMemberId(@PathVariable Long memberId) {
		List<Voucher> voucherList = voucherService.findVoucherByMemberId(memberId);
		voucherList.forEach(voucher -> printPrettyJson(voucher));
		
		return voucherList == null ? new ArrayList<>() : voucherList;
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
