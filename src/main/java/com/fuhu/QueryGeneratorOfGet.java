package com.fuhu;

import java.util.Map;

import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

public class QueryGeneratorOfGet  implements BehaviorOfQueryGenerator{

	@Override
	public void execute(StatesOfServer state, Map<String, JsonObject> transactionJsonMap, Map<String, JsonArray> responseDataInJsonArray_Map, Map<String, String> device_uuid_Map, JsonObject responseDataInJsonObject, JsonObject response, Map<String, Object> curlData_field_value_map, Buffer curlBody,
			HttpServerRequest bridge_between_server_and_client) {
		switch (state) {
		case STATE_PER_PACKAGE_GET:
		case STATE_PER_PACKAGE_AND_USER_GET:
			
			break;
		default:
			break;
		}
	}

}