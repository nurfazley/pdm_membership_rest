package com.pdm.membership.restcontroller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdm.membership.model.Image;
import com.pdm.membership.model.RedeemableItem;
import com.pdm.membership.service.RedeemableItemService;

@RestController
public class RedeemableItemController {

	@Autowired
	private RedeemableItemService redeemableItemService;
	
	
	@PostMapping("/redeemableitems/redeem")
	public RedeemableItem redeemItem(@RequestBody Map<String, String> map) {
		String redeemableItemCode = map.get("redeemableItemCode");
		int quantity = map.get("quantity") == null ? 0 : Integer.parseInt(map.get("quantity"));
		
		RedeemableItem item = redeemableItemService.findByItemCode(redeemableItemCode);
		int reducePoint = quantity * item.getPoint();
		
		return item;
	}
	
	@GetMapping("/redeemableitems")
	public List<RedeemableItem> findAll() {
		List<RedeemableItem> redeemableItemList = redeemableItemService.findAll();
		Comparator<RedeemableItem> comparator = (RedeemableItem r1, RedeemableItem r2) -> Long.compare(r2.getId(), r1.getId());
		redeemableItemList.sort(comparator);
		redeemableItemList.forEach(item -> printPrettyJson(item));
		
		return redeemableItemList;
	}
	
	@GetMapping("/redeemableitems/{id}")
	public RedeemableItem getRedeemableItemById(@PathVariable Long id) {
		RedeemableItem redeemableItem = redeemableItemService.findById(id);
		printPrettyJson(redeemableItem);
		
		return redeemableItem;
	}
	
	@GetMapping("/redeemableitems/{id}/image")
	public void getNewsListPageImage(@PathVariable Long id, HttpServletResponse response) {
		RedeemableItem redeemableItem = redeemableItemService.findById(id);
		printPrettyJson(redeemableItem);
		
		if (redeemableItem != null && redeemableItem.getImage() != null) {
			Image image = redeemableItem.getImage();
			printPrettyJson(image);
			InputStream is = new ByteArrayInputStream(image.getContentByte());
			
			if (image.getContentType() == null) {
				response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			}
			else {
				response.setContentType(image.getContentType());
			}
			
			try {
				IOUtils.copy(is, response.getOutputStream());
				response.flushBuffer();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
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
