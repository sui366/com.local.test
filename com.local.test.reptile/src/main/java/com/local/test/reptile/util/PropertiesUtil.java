package com.local.test.reptile.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("propertiesUtil")
public class PropertiesUtil {

	
	@Value("${local.url}")
	private String localUrl;
	
	

	public String getLocalUrl() {
		return localUrl;
	}

	
}
