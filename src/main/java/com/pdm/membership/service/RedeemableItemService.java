package com.pdm.membership.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdm.membership.dao.RedeemableItemDAO;
import com.pdm.membership.model.RedeemableItem;

@Service("redeemableItemService")
@Transactional
public class RedeemableItemService {
	
	@Autowired
	private RedeemableItemDAO redeemableItemDAO;
	
	
	public List<RedeemableItem> findAll() {
		return redeemableItemDAO.findAll();
	}
	
	public RedeemableItem findById(Long id) {
		return redeemableItemDAO.findById(id).orElse(new RedeemableItem());
	}
	
	public RedeemableItem findByItemCode(String itemCode) {
		return redeemableItemDAO.findByItemCode(itemCode);
	}
}
