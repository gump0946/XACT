package com.gw.xact.daomain.dao;

import org.springframework.stereotype.Repository;

import com.gw.xact.entity.XactUser;

@Repository
public interface UserDao {
	public XactUser getUser(Long id);
}
