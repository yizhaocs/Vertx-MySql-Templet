package com.fuhu;

import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

public class ProcessSendResponseOfGet implements BehaviorOfProcessSendResponse {
	@Override
	public void execute(StatesOfServer state,  Message<JsonObject> databaseMessage, HttpServerRequest bridge_between_server_and_client) {
		JsonObject databaseMessageBody = null;

		if (databaseMessage != null) {
			databaseMessageBody = databaseMessage.body();
			pmfs.printDatabaseMessage(state, databaseMessageBody);
		}
		switch (state) {
		case STATE_PER_PACKAGE_GET:
		case STATE_PER_PACKAGE_AND_USER_GET:
			JsonArray databaseMessageResults = databaseMessageBody.getArray("results");
			JsonArray results = databaseMessageResults.get(0);
			JsonArray binaryDataArray = results.get(0);
			byte[] bytearr = new byte[binaryDataArray.size()];
			for (int i = 0; i < binaryDataArray.size(); i++) {
				bytearr[i] = binaryDataArray.get(i);
			}
			bridge_between_server_and_client.response().putHeader(cs.CONTENT_TYPE_K, cs.CONTENT_TYPE_BINARY_DATA_V);
			endResponse.endResponseWithBinaryData(state, new Buffer().appendBytes(bytearr), bridge_between_server_and_client);
			break;
		default:
			break;
		}
	}

}
