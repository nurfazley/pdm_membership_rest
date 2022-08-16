package com.pdm.membership.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pdm.membership.model.News;
import com.pdm.membership.model.lookup.NewsStatus;

public class NewsDAOImpl implements NewsDAOCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public List<News> findAllOrderByIdDesc() {
		TypedQuery<News> query = entityManager.createQuery("FROM News n ORDER BY n.id DESC", News.class);
		return query.getResultList();
	}


	@Override
	public List<News> findAllPublishedOrderByIdDesc() {
		TypedQuery<News> query = entityManager.createQuery("FROM News n WHERE n.newsStatus = :newsStatus ORDER BY n.id DESC", News.class);
		query.setParameter("newsStatus", NewsStatus.PUBLISHED);
		
		return query.getResultList();
	}
}
