package com.fuhu;

public class SingletonOfConstantsS {
	/* Setup for Singleton pattern */
	private static SingletonOfConstantsS instance = null;

	private SingletonOfConstantsS() {

	}

	public static SingletonOfConstantsS getInstance() {
		if (instance == null) {
			instance = new SingletonOfConstantsS();
		}
		return instance;
	}

	/* Constants of API path */
	protected final String PATH_OF_PER_PACKAGE = "/cloud/:packageName/stream/:streamKey";
	protected final String PATH_OF_PER_PACKAGE_AND_USER = "/cloud/user/:userKey/:packageName/stream/:streamKey";
	/* Constants of table columns */
	//protected final String[] perPackage_TableColumns = {"package_name", "stream_key", "binary_data", "is_deleted", "created_at", "updated_at" };
	protected final String[] perPackageAndUser_TableColumns = {"user_key", "package_name", "stream_key", "binary_data",  "created_at", "updated_at" };
	/* Constants of database */
	protected final String tableName = "databackup";
	/* Constants of Headers */
	protected final String ACCEPT_K = "Accept";
	protected final String ACCEPT_V = "binary/octet-stream";
	protected final String APIKEY_K = "APIKEY";
	protected final String CONTENT_TYPE_K = "Content-Type";
	protected final String CONTENT_TYPE_V = "binary/octet-stream";
	/* Constants of SingletonOfPrintingMethodsOfServer */
	protected final String HEADER_BEGIN = "{-----------------Server State:";
	protected final String HEADER_END = "-------------------";
	protected final String END_SMALL = "----------------------------------------------------------------------------------}";
}
