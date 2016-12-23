package com.test.owner;

import org.aeonbits.owner.ConfigFactory;

public class Test {

	public static void main(String[] args) {

//		ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
//	    System.out.println(cfg.hostname());
	    
//	    ServerConfig instance = ConfigCache.getOrCreate(ServerConfig.class);
//	    
//	    System.out.println(instance.hostname());
	    
		ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
		
		
		System.out.println(cfg.maxThreads());
//		cfg.setProperty("server.max.threads", "40");
		System.out.println(cfg.maxThreads());
		
		cfg.reload();
		
		cfg.removeProperty("server.max.threads");
	}

}
