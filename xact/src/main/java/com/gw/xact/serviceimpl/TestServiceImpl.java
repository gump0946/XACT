package com.gw.xact.serviceimpl;

import org.springframework.stereotype.Service;

import com.gw.xact.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Override
	public void printHello() {
		System.out.println("-------test--------");
		System.out.println("hello test");

	}

}
