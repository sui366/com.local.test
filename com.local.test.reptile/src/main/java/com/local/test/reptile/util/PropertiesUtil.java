package com.local.test.reptile.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("propertiesUtil")
public class PropertiesUtil {

	
	@Value("${local.url}")
	private String localUrl;
	
	
	@Value("${ssn.data.url}")
	private String ssnDataUrl;

	public String getLocalUrl() {
		return localUrl;
	}

	public String getSsnDataUrl() {
		return ssnDataUrl;
	}
	
	
}
