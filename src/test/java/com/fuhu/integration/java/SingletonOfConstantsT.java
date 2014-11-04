package com.fuhu.integration.java;

public class SingletonOfConstantsT {
	/* Setup for Singleton pattern */
	private static SingletonOfConstantsT instance = null;

	private SingletonOfConstantsT() {

	}

	public static SingletonOfConstantsT getInstance() {
		if (instance == null) {
			instance = new SingletonOfConstantsT();
		}
		return instance;
	}

	/* Constants for HTTP request methods */
	protected final String POST_REQUEST = "POST";
	protected final String GET_REQUEST = "GET";
	protected final String PUT_REQUEST = "PUT";
	protected final String DELETE_REQUEST = "DELETE";
	/* Constants of API path */
	protected final String PATH_OF_PER_PACKAGE = "/cloud/comfuhunabiradio/stream/stations";
	protected final String PATH_OF_PER_PACKAGE_AND_USER = "/cloud/user/yizhao/comfuhunabiradio/stream/stations";
	/* Constants for print command */
	protected final String END_SMALL = "----------------------------------------------------------------------------------}";
	protected final String END_BIG = "EndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEnd";
}
