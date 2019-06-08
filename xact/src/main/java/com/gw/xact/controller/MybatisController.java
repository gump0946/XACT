package com.gw.xact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gw.xact.entity.XactUser;
import com.gw.xact.service.MyBatisUserService;

@RestController
@RequestMapping("/xact")
public class MybatisController {
	@Autowired
	private MyBatisUserService myBatisUserService;

	@RequestMapping("/getUser")
	public XactUser getUser() {
		System.out.println("id: ");
		return myBatisUserService.getUser(1L);
	}
}
