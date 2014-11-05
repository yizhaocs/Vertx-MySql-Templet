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
	protected final String[] perPackageAndUser_TableColumns = {"user_key", "package_name", "stream_key", "binary_data", "updated_at" };
	/* Constants of database */
	protected final String tableName = "databackup";
	/* Constants of Headers */
	protected final String ACCEPT_K = "Accept";
	protected final String ACCEPT_V = "binary/octet-stream";
	protected final String APIKEY_K = "APIKEY";
	protected final String APIKEY_V = "1234567890abcdef";
	protected final String CONTENT_TYPE_K = "Content-Type";
	protected final String CONTENT_TYPE_BINARY_DATA_V = "binary/octet-stream";
	protected final String CONTENT_TYPE_JSON_V = "application/json";
	/* Constants of SingletonOfPrintingMethodsOfServer */
	protected final String HEADER_BEGIN = "{-----------------Server State:";
	protected final String HEADER_END = "-------------------";
	protected final String END_SMALL = "----------------------------------------------------------------------------------}";
	
	

	/* Constrants for status codes */
	protected final static String OK = EnumOfAPIStatus.ok.getStatusCode();
	protected final static String UNKNOWN_ERROR = EnumOfAPIStatus.unknownError.getStatusCode();
	protected final static String API_KEY_MISSING = EnumOfAPIStatus.apiKeyMissing.getStatusCode();
	protected final static String API_KEY_INVALID = EnumOfAPIStatus.apiKeyInvalid.getStatusCode();
	protected final static String MISSING_ACCEPT_HEADER = EnumOfAPIStatus.missingAcceptHeader.getStatusCode();
	protected final static String INVALID_ACCEPT_HEADER = EnumOfAPIStatus.invalidAcceptHeader.getStatusCode();
	protected final static String MISSING_SESSION_KEY = EnumOfAPIStatus.missingSessionKey.getStatusCode();
	protected final static String INVALID_SESSION_KEY = EnumOfAPIStatus.invalidSessionKey.getStatusCode();
	protected final static String MISSING_CONTENT_TYPE_HEADER = EnumOfAPIStatus.missingContentTypeHeader.getStatusCode();
	protected final static String INVALID_CONTENT_TYPE_HEADER = EnumOfAPIStatus.invalidContentTypeHeader.getStatusCode();
	protected final static String MISSING_HTTP_REQUEST_BODY = EnumOfAPIStatus.missingHttpRequestBody.getStatusCode();
	protected final static String INVALID_OR_INCORRECT_HTTP_REQUEST_BODY = EnumOfAPIStatus.invalidOrIncorrectHttpRequestBody.getStatusCode();
	protected final static String MISSING_USER_UUID_STATUS_CODE = EnumOfAPIStatus.missingUserUuid_inside_http_headers.getStatusCode();
	protected final static String DUPLICATED_EMAIL_CODE = EnumOfAPIStatus.duplicateEmail.getStatusCode();
	/* Constrants for status codes */
	protected final static String OK_DESC = EnumOfAPIStatus.ok.getDesc();
	protected final static String UNKNOWN_ERROR_DESC = EnumOfAPIStatus.unknownError.getDesc();
	protected final static String API_KEY_MISSING_DESC = EnumOfAPIStatus.apiKeyMissing.getDesc();
	protected final static String API_KEY_INVALID_DESC = EnumOfAPIStatus.apiKeyInvalid.getDesc();
	protected final static String MISSING_ACCEPT_HEADER_DESC = EnumOfAPIStatus.missingAcceptHeader.getDesc();
	protected final static String INVALID_ACCEPT_HEADER_DESC = EnumOfAPIStatus.invalidAcceptHeader.getDesc();
	protected final static String MISSING_SESSION_KEY_DESC = EnumOfAPIStatus.missingSessionKey.getDesc();
	protected final static String INVALID_SESSION_KEY_DESC = EnumOfAPIStatus.invalidSessionKey.getDesc();
	protected final static String MISSING_CONTENT_TYPE_HEADER_DESC = EnumOfAPIStatus.missingContentTypeHeader.getDesc();
	protected final static String INVALID_CONTENT_TYPE_HEADER_DESC = EnumOfAPIStatus.invalidContentTypeHeader.getDesc();
	protected final static String MISSING_HTTP_REQUEST_BODY_DESC = EnumOfAPIStatus.missingHttpRequestBody.getDesc();
	protected final static String INVALID_OR_INCORRECT_HTTP_REQUEST_BODY_DESC = EnumOfAPIStatus.invalidOrIncorrectHttpRequestBody.getDesc();
	protected final static String MISSING_USER_UUID_STATUS_DESC = EnumOfAPIStatus.missingUserUuid_inside_http_headers.getDesc();
	protected final static String DUPLICATED_EMAIL_DESC = EnumOfAPIStatus.duplicateEmail.getDesc();

	/* Constants for reading database returned message from database to Server in Json format */
	protected final static String DB_STATUS = "status";
	protected final static String DB_ROWS = "rows";
	protected final static String DB_FIELD = "fields";
	protected final static String DB_RESULT = "results";
	protected final static String DB_ERROR = "error";
	protected final static String DB_MESSAGE = "message";
	
	/* Constants and variables for writing output data from Server to Client */
	protected final static String NABI_CLIENT_DATA_BACKUP_APIVersion_K = "NabiClientDataBackupAPIVersion";
	protected final static String NABI_CLIENT_DATA_BACKUP_APIVersion_V = "1.0";
	protected final static String STATUS = "status";
	protected final static String STATUS_DESCRIPTION = "statusDescription";
	protected final static String LAST_TIME_MODIFIED = "lastTimeModified";
}
