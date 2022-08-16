package com.pdm.membership.dao;

import java.util.List;

import com.pdm.membership.model.News;

public interface NewsDAOCustom {
	
	public List<News> findAllOrderByIdDesc();
	
	public List<News> findAllPublishedOrderByIdDesc();
}
