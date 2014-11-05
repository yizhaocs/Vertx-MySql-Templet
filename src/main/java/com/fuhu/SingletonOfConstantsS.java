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
	protected final String APIKEY_V = "1234567890abcdef";
	protected final String CONTENT_TYPE_K = "Content-Type";
	protected final String CONTENT_TYPE_BINARY_DATA_V = "binary/octet-stream";
	protected final String CONTENT_TYPE_JSON_V = "application/json";
	/* Constants of SingletonOfPrintingMethodsOfServer */
	protected final String HEADER_BEGIN = "{-----------------Server State:";
	protected final String HEADER_END = "-------------------";
	protected final String END_SMALL = "----------------------------------------------------------------------------------}";
	
	

	/* Constrants for status codes */
	protected final static String OK = APIStatusEnum.ok.getStatusCode();
	protected final static String UNKNOWN_ERROR = APIStatusEnum.unknownError.getStatusCode();
	protected final static String API_KEY_MISSING = APIStatusEnum.apiKeyMissing.getStatusCode();
	protected final static String API_KEY_INVALID = APIStatusEnum.apiKeyInvalid.getStatusCode();
	protected final static String MISSING_ACCEPT_HEADER = APIStatusEnum.missingAcceptHeader.getStatusCode();
	protected final static String INVALID_ACCEPT_HEADER = APIStatusEnum.invalidAcceptHeader.getStatusCode();
	protected final static String MISSING_SESSION_KEY = APIStatusEnum.missingSessionKey.getStatusCode();
	protected final static String INVALID_SESSION_KEY = APIStatusEnum.invalidSessionKey.getStatusCode();
	protected final static String MISSING_CONTENT_TYPE_HEADER = APIStatusEnum.missingContentTypeHeader.getStatusCode();
	protected final static String INVALID_CONTENT_TYPE_HEADER = APIStatusEnum.invalidContentTypeHeader.getStatusCode();
	protected final static String MISSING_HTTP_REQUEST_BODY = APIStatusEnum.missingHttpRequestBody.getStatusCode();
	protected final static String INVALID_OR_INCORRECT_HTTP_REQUEST_BODY = APIStatusEnum.invalidOrIncorrectHttpRequestBody.getStatusCode();
	protected final static String MISSING_USER_UUID_STATUS_CODE = APIStatusEnum.missingUserUuid_inside_http_headers.getStatusCode();
	protected final static String DUPLICATED_EMAIL_CODE = APIStatusEnum.duplicateEmail.getStatusCode();
	/* Constrants for status codes */
	protected final static String OK_DESC = APIStatusEnum.ok.getDesc();
	protected final static String UNKNOWN_ERROR_DESC = APIStatusEnum.unknownError.getDesc();
	protected final static String API_KEY_MISSING_DESC = APIStatusEnum.apiKeyMissing.getDesc();
	protected final static String API_KEY_INVALID_DESC = APIStatusEnum.apiKeyInvalid.getDesc();
	protected final static String MISSING_ACCEPT_HEADER_DESC = APIStatusEnum.missingAcceptHeader.getDesc();
	protected final static String INVALID_ACCEPT_HEADER_DESC = APIStatusEnum.invalidAcceptHeader.getDesc();
	protected final static String MISSING_SESSION_KEY_DESC = APIStatusEnum.missingSessionKey.getDesc();
	protected final static String INVALID_SESSION_KEY_DESC = APIStatusEnum.invalidSessionKey.getDesc();
	protected final static String MISSING_CONTENT_TYPE_HEADER_DESC = APIStatusEnum.missingContentTypeHeader.getDesc();
	protected final static String INVALID_CONTENT_TYPE_HEADER_DESC = APIStatusEnum.invalidContentTypeHeader.getDesc();
	protected final static String MISSING_HTTP_REQUEST_BODY_DESC = APIStatusEnum.missingHttpRequestBody.getDesc();
	protected final static String INVALID_OR_INCORRECT_HTTP_REQUEST_BODY_DESC = APIStatusEnum.invalidOrIncorrectHttpRequestBody.getDesc();
	protected final static String MISSING_USER_UUID_STATUS_DESC = APIStatusEnum.missingUserUuid_inside_http_headers.getDesc();
	protected final static String DUPLICATED_EMAIL_DESC = APIStatusEnum.duplicateEmail.getDesc();

	/* Constants for reading database returned message from database to Server in Json format */
	protected final static String DB_STATUS = "status";
	protected final static String DB_ROWS = "rows";
	protected final static String DB_FIELD = "fields";
	protected final static String DB_RESULT = "results";
	protected final static String DB_ERROR = "error";
	protected final static String DB_MESSAGE = "message";
	
	/* Constants and variables for writing output data from Server to Client */
	protected final static String STATUS = "status";
	protected final static String STATUS_DESCRIPTION = "statusDescription";
	protected final static String LAST_TIME_MODIFIED = "lastTimeModified";
}
