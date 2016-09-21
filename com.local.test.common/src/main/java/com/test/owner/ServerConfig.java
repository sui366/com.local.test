package com.test.owner;

import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Reloadable;

//@HotReload(value=10, unit = TimeUnit.SECONDS)
@Sources({ "classpath:ServerConfig.properties" })
public interface ServerConfig extends Reloadable  {
	@Key("server.http.port")
	int port();

	@Key("server.host.name")
	String hostname();

	@Key("server.max.threads")
	@DefaultValue("42")
	int maxThreads();
}