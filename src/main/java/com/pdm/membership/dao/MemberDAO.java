package com.pdm.membership.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pdm.membership.model.Member;

@Repository("memberDAO")
public interface MemberDAO extends JpaRepository<Member, Long>, CrudRepository<Member, Long>, JpaSpecificationExecutor<Member>, MemberDAOCustom {}
