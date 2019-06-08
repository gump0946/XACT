package com.gw.xact.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class XactConfig {
	@Value("${test.msg}")
	public static String name;
}
