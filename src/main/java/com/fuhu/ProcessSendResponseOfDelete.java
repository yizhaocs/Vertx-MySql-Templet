package com.fuhu;

import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public class ProcessSendResponseOfDelete implements BehaviorOfProcessSendResponse {
	@Override
	public void execute(StatesOfServer state, Message<JsonObject> databaseMessage, HttpServerRequest bridge_between_server_and_client, String currentTime) {
		JsonObject databaseMessageBody = null;
		/* upload profile photo and get profile photo apis does not need database */
		if (databaseMessage != null) {
			databaseMessageBody = databaseMessage.body();
			pmfs.printDatabaseMessage(state, databaseMessageBody);
		}
		JsonObject response = new JsonObject();
		switch (state) {
		case STATE_PER_PACKAGE_DELETE:
		case STATE_PER_PACKAGE_AND_USER_DELETE:
			response.putNumber("status", 0);
			bridge_between_server_and_client.response().putHeader(cs.CONTENT_TYPE_K, cs.CONTENT_TYPE_JSON_V);
			endResponse.endResponseWithJson(state, response, bridge_between_server_and_client);
			break;
		default:
			break;
		}
	}
}
