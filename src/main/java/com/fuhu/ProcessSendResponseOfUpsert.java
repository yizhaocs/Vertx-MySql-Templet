package com.fuhu;

import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public class ProcessSendResponseOfUpsert implements BehaviorOfProcessSendResponse {
	@Override
	public void execute(StatesOfServer state, Message<JsonObject> databaseMessage, HttpServerRequest bridge_between_server_and_client) {
		JsonObject databaseMessageBody = null;
		/* upload profile photo and get profile photo apis does not need database */
		if (databaseMessage != null) {
			databaseMessageBody = databaseMessage.body();
			pmfs.printDatabaseMessage(state, databaseMessageBody);
		}
		JsonObject response = new JsonObject();
		switch (state) {
		case STATE_PER_PACKAGE_UPSERT:

			break;
		case STATE_PER_PACKAGE_AND_USER_UPSERT:

			break;
		default:
			break;
		}
	}
}
