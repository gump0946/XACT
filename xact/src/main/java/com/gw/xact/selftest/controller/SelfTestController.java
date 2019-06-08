package com.gw.xact.selftest.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gw.xact.common.view.RequestWrapper;
import com.gw.xact.common.view.ResponseWrapper;
import com.gw.xact.selftest.view.TestVi;
import com.gw.xact.selftest.view.TestVo;

@RestController
@RequestMapping("/xact")
public class SelfTestController {

	@RequestMapping("/hello")
	public @ResponseBody ResponseWrapper<TestVo> index(@RequestBody RequestWrapper<TestVi> request) {
		System.out.println("in index");
		ResponseWrapper<TestVo> response = new ResponseWrapper<TestVo>();
		TestVo vo = new TestVo();
		System.out.println("Print request: ");
		System.out.println(request.getUniqueId());
		System.out.println(request.getData().getId());
		
		System.out.println("Set response: ");
		response.setData(vo);
		response.getData().setId("654321");
		response.getData().setName("gantest");

		return response;

	}

	@RequestMapping("/test2")
	public @ResponseBody ResponseWrapper<TestVo> test2(@RequestBody TestVi testvi) {
		System.out.println("in index");
		ResponseWrapper<TestVo> response = new ResponseWrapper<TestVo>();
		TestVo vo = new TestVo();
		System.out.println("Print request: ");
		System.out.println(testvi.getId());
		System.out.println(testvi.getName());

		System.out.println("Set response: ");
		response.setData(vo);
		response.getData().setId("654321");
		response.getData().setName("gantest");

		return response;

	}

}
