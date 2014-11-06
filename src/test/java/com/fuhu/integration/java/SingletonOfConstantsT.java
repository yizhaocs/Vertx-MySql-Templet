package com.fuhu.integration.java;

import java.util.HashMap;
import java.util.Map;

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
	
	/* Constant for state-map */
	protected final static Map<StatesOfClient, Boolean> mapStates;
	static {
		mapStates = new HashMap<StatesOfClient, Boolean>();
		for (StatesOfClient s : StatesOfClient.values()) {
			mapStates.put(s, false);
		}
	}

	/* Constants for HTTP request methods */
	protected final String POST_REQUEST = "POST";
	protected final String GET_REQUEST = "GET";
	protected final String PUT_REQUEST = "PUT";
	protected final String DELETE_REQUEST = "DELETE";
	/* Constants of API path */
	protected final String PATH_OF_PER_PACKAGE_UPSERT_AND_DELETE = "/cloud/:packageName/stream/:streamKey";
	protected final String PATH_OF_PER_PACKAGE_GET = "/cloud/:packageName/stream/:streamKey/timestamp";
	protected final String PATH_OF_PER_PACKAGE_AND_USER_UPSERT_AND_DELETE = "/cloud/user/:userKey/:packageName/stream/:streamKey";
	protected final String PATH_OF_PER_PACKAGE_AND_USER_GET = "/cloud/user/:userKey/:packageName/stream/:streamKey/timestamp";
	/* Constants for print command */
	protected final String END_SMALL = "----------------------------------------------------------------------------------}";
	protected final String END_BIG = "EndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEnd";
}
