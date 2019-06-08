package com.gw.xact.rp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TesActServiceImpl {

	public void activiti() {
		System.out.println("任务开始执行。。。。");
	}

	public List<String> users() {
		ArrayList<String> slist = new ArrayList<String>();
		slist.add("xiaoming");
		slist.add("xiaohong");
		return slist;
	}
}
