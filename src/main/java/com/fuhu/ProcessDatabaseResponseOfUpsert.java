package com.fuhu;

import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public class ProcessDatabaseResponseOfUpsert implements BehaviorOfDatabaseResponseProcessor {
	@Override
	public void execute(StatesOfServer state, JsonObject response,JsonObject databaseMessageBody, HttpServerRequest bridge_between_server_and_client, String currentTime) {
		switch (state) {
		case STATE_PER_PACKAGE_UPSERT:
		case STATE_PER_PACKAGE_AND_USER_UPSERT:
			bridge_between_server_and_client.response().putHeader(cs.CONTENT_TYPE_K, cs.CONTENT_TYPE_JSON_V);
			response.putString(cs.STATUS, cs.OK);
			response.putString(cs.STATUS_DESCRIPTION, cs.OK_DESC);
			response.putString(cs.LAST_TIME_MODIFIED, currentTime);
			endResponse.endResponseWithJson(state, response, bridge_between_server_and_client);
			break;
		default:
			break;
		}
	}
}
