package com.gw.xact.selftest.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.gw.xact.selftest.view.TestVi;

@Component
public class StringToTestVi implements Converter<String, TestVi> {

	@Override
	public TestVi convert(String viStr) {
		TestVi vi = new TestVi();
		String[] strArr = viStr.split("-");
		String id = strArr[0];
		String name = strArr[1];
		System.out.println("in convert test test");
		vi.setId(id);
		vi.setName(name);
		return vi;
	}
}
