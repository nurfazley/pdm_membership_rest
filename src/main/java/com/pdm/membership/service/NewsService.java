package com.pdm.membership.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdm.membership.dao.NewsDAO;
import com.pdm.membership.model.News;


@Service("newsService")
@Transactional
public class NewsService {
	
	@Autowired
	private NewsDAO newsDAO;
	
	
	public News save(News news) {
		return newsDAO.save(news);
	}
	
	public News update(News news) {
		return newsDAO.save(news);
	}
	
	public List<News> findAll() {
		return newsDAO.findAll();
	}
	
	public List<News> findAllOrderByIdDesc() {
		return newsDAO.findAllOrderByIdDesc();
	}
	
	public News findById(Long id) {
		return newsDAO.findById(id).orElse(new News());
	}
	
	public List<News> findAllPublishedOrderByIdDesc() {
		return newsDAO.findAllPublishedOrderByIdDesc();
	}
}
