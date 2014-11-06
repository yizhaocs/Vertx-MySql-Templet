package com.fuhu;

import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

public class ProcessDatabaseResponseOfGet implements BehaviorOfDatabaseResponseProcessor {
	@Override
	public void execute(StatesOfServer state, JsonObject response, JsonObject databaseMessageBody, HttpServerRequest bridge_between_server_and_client, String currentTime) {
		switch (state) {
		case STATE_PER_PACKAGE_GET:
		case STATE_PER_PACKAGE_AND_USER_GET:
			try {
				JsonArray databaseMessageResults = databaseMessageBody.getArray(cs.DB_RESULT);
				if (databaseMessageResults.size() > 0) {
					JsonArray results = databaseMessageResults.get(0);
					if (results.size() > 0) {
						JsonArray binaryDataArray = results.get(0);
						if (binaryDataArray.size() > 0) {
							byte[] bytearr = new byte[binaryDataArray.size()];
							for (int i = 0; i < binaryDataArray.size(); i++) {
								bytearr[i] = binaryDataArray.get(i);
							}
							bridge_between_server_and_client.response().putHeader(cs.CONTENT_TYPE_K, cs.CONTENT_TYPE_BINARY_DATA_V);
							endResponse.endResponseWithBinaryData(state, new Buffer().appendBytes(bytearr), bridge_between_server_and_client);
						} else {
							endWithEmpty(state, bridge_between_server_and_client);
						}
					} else {
						endWithEmpty(state, bridge_between_server_and_client);
					}
				} else {
					endWithEmpty(state, bridge_between_server_and_client);
				}
			} catch (Exception e) {
				bridge_between_server_and_client.response().setStatusCode(404);
				utility.logger.error(e.getStackTrace());
				endResponse.endResponseWithUnknownError(state, response, bridge_between_server_and_client);
			}
			break;
		default:
			break;
		}
	}

	private void endWithEmpty(StatesOfServer state, HttpServerRequest bridge_between_server_and_client) {
		bridge_between_server_and_client.response().setStatusCode(404);
		endResponse.endResponseWithBinaryData(state, new Buffer(), bridge_between_server_and_client);
	}

}
