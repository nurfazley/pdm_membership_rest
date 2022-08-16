package com.pdm.membership.dao;

import com.pdm.membership.model.RedeemableItem;

public interface RedeemableItemDAOCustom {
	
	public RedeemableItem findByItemCode(String itemCode);

}
