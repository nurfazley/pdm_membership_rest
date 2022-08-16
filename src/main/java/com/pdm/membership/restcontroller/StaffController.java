package com.pdm.membership.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdm.membership.model.Staff;
import com.pdm.membership.service.StaffService;

@RestController
public class StaffController {

	@Autowired
	private StaffService staffService;
	
	
	@GetMapping("/staff/ic/{ic}")
	public Staff findStaffByIc(String ic) {
		Staff staff = staffService.findStaffByIc(ic);
		printPrettyJson(staff);
		
		return staff;
	}
	
	@GetMapping("/staff/phone/{phoneNumber}")
	public Staff findStaffByPhoneNumber(String phoneNumber) {
		Staff staff = staffService.findStaffByPhoneNumber(phoneNumber);
		printPrettyJson(staff);
		
		return staff;
	}
	
	@GetMapping("/staff/email/{email}")
	public Staff findStaffByEmail(String email) {
		Staff staff = staffService.findStaffByEmail(email);
		printPrettyJson(staff);
		
		return staff;
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
