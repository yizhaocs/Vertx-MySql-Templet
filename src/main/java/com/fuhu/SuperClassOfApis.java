package com.fuhu;

import java.util.Map;

import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public class SuperClassOfApis extends MainServerVerticle {
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

}
