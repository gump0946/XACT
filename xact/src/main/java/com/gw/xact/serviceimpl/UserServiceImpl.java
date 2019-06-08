package com.gw.xact.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gw.xact.service.TestService;
import com.gw.xact.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TestService service;

	@Override
	public int printUser(String book) {
		// TODO Auto-generated method stub
		System.out.println("in printUser book");
		service.printHello();
		return 0;
	}

	public TestService getService() {
		return service;
	}

	public void setService(TestService service) {
		this.service = service;
	}

}
