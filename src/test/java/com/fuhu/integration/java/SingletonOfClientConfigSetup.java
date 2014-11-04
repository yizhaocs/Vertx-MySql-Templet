package com.fuhu.integration.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.vertx.java.core.json.JsonObject;

public class SingletonOfClientConfigSetup {
	/* Setup for Singleton pattern */
	private static SingletonOfClientConfigSetup instance = null;
	private JsonObject config;

	private SingletonOfClientConfigSetup() {
		String sConfig = null;
		try {
			sConfig = IOUtils.toString(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/ClientConfig.json"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		config = new JsonObject(sConfig);
	}

	public static SingletonOfClientConfigSetup getInstance() {
		if (instance == null) {
			instance = new SingletonOfClientConfigSetup();
		}
		return instance;
	}

	protected JsonObject getDBconfig() {
		return config.getObject("DbConnectionParam");
	}

	protected String getServerHost() {
		return config.getString("server_host");
	}

	protected int getServerPort() {
		return config.getInteger("server_port");
	}

}
