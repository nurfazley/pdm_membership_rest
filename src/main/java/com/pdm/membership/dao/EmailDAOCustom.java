package com.pdm.membership.dao;

import java.util.List;

import com.pdm.membership.model.Email;
import com.pdm.membership.model.lookup.EmailStatus;


public interface EmailDAOCustom {

	public List<Email> findAllByStatus(EmailStatus emailStatus);
}
