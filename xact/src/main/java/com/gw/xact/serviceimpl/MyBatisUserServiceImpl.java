package com.gw.xact.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gw.xact.daomain.dao.UserDao;
import com.gw.xact.entity.XactUser;
import com.gw.xact.service.MyBatisUserService;
import com.gw.xact.service.UserService;

@Service
public class MyBatisUserServiceImpl implements MyBatisUserService {
	@Autowired
	private UserDao myBatisUserDao;

	@Autowired
	private UserService service;
	@Override
	public XactUser getUser(Long id) {
		System.out.println("------test------");
		service.printUser("ganwei");
		System.out.println("------test------");
		return myBatisUserDao.getUser(id);
	}
}
