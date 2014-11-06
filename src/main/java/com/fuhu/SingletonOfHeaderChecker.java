package com.fuhu;

import java.util.Map;

import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public class SingletonOfHeaderChecker {
	/* Setup for Singleton pattern */
	private static SingletonOfHeaderChecker instance = null;

	private SingletonOfHeaderChecker() {

	}

	public static SingletonOfHeaderChecker getInstance() {
		if (instance == null) {
			instance = new SingletonOfHeaderChecker();
		}
		return instance;
	}

	SingletonOfEndResponse endResponse = SingletonOfEndResponse.getInstance();
	static SingletonOfConstantsS css = SingletonOfConstantsS.getInstance();
	SingletonOfPrintingMethodsOfServer pmfs = SingletonOfPrintingMethodsOfServer.getInstance();

	protected void getHeaders(StatesOfServer state, HttpServerRequest curlRequest, Map<String, Boolean> containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map, Map<String, String> valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map) {
		pmfs.printHeaders(state, curlRequest.headers().entries());
		for (Map.Entry<String, String> header : curlRequest.headers().entries()) {
			String key = header.getKey().toUpperCase();
			String value = header.getValue();
			if (key.equals(css.APIKEY_K.toUpperCase())) {
				containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map.put(css.CONTAINS_APIKEY, true);
				valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map.put(css.VALUE_OF_APIKEY, value);
			} else if (key.equals(css.CONTENT_TYPE_K.toUpperCase())) {
				containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map.put(css.CONTAINS_CONTENT_TYPE, true);
				valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map.put(css.VALUE_OF_CONTENT_TYPE, value);
			} else if (key.equals(css.ACCEPT_K.toUpperCase())) {
				containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map.put(css.CONTAINS_ACCEPT, true);
				valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map.put(css.VALUE_OF_ACCEPT, value);
			}
		}
	}

	/*
	 * @return: true if no error and vice versa
	 */
	protected boolean execute(StatesOfServer state, JsonObject response, HttpServerRequest curlRequest, Map<String, Boolean> containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map, Map<String, String> valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map) {
		getHeaders(state, curlRequest, containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map, valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map);
		switch (state) {
		case STATE_PER_PACKAGE_UPSERT:
		case STATE_PER_PACKAGE_AND_USER_UPSERT:
			return without_apiKey_contentType_accept_error(state, response, curlRequest, containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map, valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map);
		case STATE_PER_PACKAGE_GET:
		case STATE_PER_PACKAGE_AND_USER_GET:
		case STATE_PER_PACKAGE_DELETE:
		case STATE_PER_PACKAGE_AND_USER_DELETE:
			return without_apiKey_accept_error(state, response, curlRequest, containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map, valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map);
		default:
			return true;
		}
	}

	/*
	 * @return: true if no error and vice versa
	 */
	protected boolean without_apiKey_contentType_accept_error(StatesOfServer state, JsonObject response, HttpServerRequest curlRequest, Map<String, Boolean> containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map,
			Map<String, String> valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map) {
		return (APIKeyHeaderChecker(state, response, curlRequest, containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map, valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map)
				&& ContentTypeHeaderChecker(state, response, curlRequest, containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map, valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map) && acceptHeaderChecker(state, response, curlRequest,
					containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map, valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map));
	}

	/*
	 * @return: true if no error and vice versa
	 */
	protected boolean without_apiKey_accept_error(StatesOfServer state, JsonObject response, HttpServerRequest curlRequest, Map<String, Boolean> containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map,
			Map<String, String> valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map) {
		return (APIKeyHeaderChecker(state, response, curlRequest, containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map, valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map) && acceptHeaderChecker(state, response, curlRequest,
				containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map, valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map));
	}

	/*
	 * @return: true if no error and vice versa
	 */
	protected boolean APIKeyHeaderChecker(StatesOfServer state, JsonObject response, HttpServerRequest curlRequest, Map<String, Boolean> containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map, Map<String, String> valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map) {
		if (!containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map.get(css.CONTAINS_APIKEY)) {
			response.putString(css.STATUS, css.API_KEY_MISSING).putString(css.STATUS_DESCRIPTION, css.API_KEY_MISSING_DESC);
			endResponse.endResponseWithJson(state, response, curlRequest);
			return false;
		} else if (!css.APIKEY_V.equals(valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map.get(css.VALUE_OF_APIKEY))) {
			response.putString(css.STATUS, css.API_KEY_INVALID).putString(css.STATUS_DESCRIPTION, css.API_KEY_INVALID_DESC);
			endResponse.endResponseWithJson(state, response, curlRequest);
			return false;
		}
		return true;
	}

	/*
	 * @return: true if no error and vice versa
	 */
	protected boolean ContentTypeHeaderChecker(StatesOfServer state, JsonObject response, HttpServerRequest curlRequest, Map<String, Boolean> containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map,
			Map<String, String> valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map) {
		if (!containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map.get(css.CONTAINS_CONTENT_TYPE)) {
			response.putString(css.STATUS, css.MISSING_CONTENT_TYPE_HEADER).putString(css.STATUS_DESCRIPTION, css.MISSING_CONTENT_TYPE_HEADER_DESC);
			endResponse.endResponseWithJson(state, response, curlRequest);
			return false;
		} else {
			if (!valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map.get(css.VALUE_OF_CONTENT_TYPE).toUpperCase().equals(css.CONTENT_TYPE_BINARY_DATA_V.toUpperCase())) {
				response.putString(css.STATUS, css.INVALID_CONTENT_TYPE_HEADER).putString(css.STATUS_DESCRIPTION, css.INVALID_CONTENT_TYPE_HEADER_DESC);
				endResponse.endResponseWithJson(state, response, curlRequest);
				return false;
			}
		}
		return true;
	}

	/*
	 * @return: true if no error and vice versa
	 */
	protected boolean acceptHeaderChecker(StatesOfServer state, JsonObject response, HttpServerRequest curlRequest, Map<String, Boolean> containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map, Map<String, String> valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map) {
		if (!containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map.get(css.CONTAINS_ACCEPT)) {
			response.putString(css.STATUS, css.MISSING_ACCEPT_HEADER).putString(css.STATUS_DESCRIPTION, css.MISSING_ACCEPT_HEADER_DESC);
			endResponse.endResponseWithJson(state, response, curlRequest);
			return false;
		} else {
			if (state.equals(StatesOfServer.STATE_PER_PACKAGE_GET) || state.equals(StatesOfServer.STATE_PER_PACKAGE_AND_USER_GET)) {
				if (!valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map.get(css.VALUE_OF_ACCEPT).toUpperCase().equals(css.ACCEPT_V_BINARY_DATA_V.toUpperCase())) {
					response.putString(css.STATUS, css.INVALID_ACCEPT_HEADER).putString(css.STATUS_DESCRIPTION, css.INVALID_ACCEPT_HEADER_DESC);
					endResponse.endResponseWithJson(state, response, curlRequest);
				}
			} else {
				if (!valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map.get(css.VALUE_OF_ACCEPT).toUpperCase().equals(css.ACCEPT_V_JSON_V.toUpperCase())) {
					response.putString(css.STATUS, css.INVALID_ACCEPT_HEADER).putString(css.STATUS_DESCRIPTION, css.INVALID_ACCEPT_HEADER_DESC);
					endResponse.endResponseWithJson(state, response, curlRequest);
				}
			}
		}
		return true;
	}
}
