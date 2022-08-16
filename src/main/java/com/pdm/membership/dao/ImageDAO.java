package com.pdm.membership.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pdm.membership.model.Image;

@Repository("imageDAO")
public interface ImageDAO extends JpaRepository<Image, Long>, JpaSpecificationExecutor<Image> {}
