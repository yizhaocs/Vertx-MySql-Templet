package com.fuhu;

import java.util.HashMap;
import java.util.Map;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public class ApiOfGet extends StrategyPatternOfParentAPI {
	private JsonObject response;
	private Map<String, Boolean> containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map;
	private Map<String, String> valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map;

	public ApiOfGet() {
		response = new JsonObject();
		response.putString(cs.NABI_CLIENT_DATA_BACKUP_APIVersion_K, cs.NABI_CLIENT_DATA_BACKUP_APIVersion_V);
		mBehaviorOfQueryGenerator = new QueryGeneratorOfGet();
		mBehaviorOfProcessSendResponse = new DatabaseResponseProcessorOfGet();

		containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map = new HashMap<>();
		containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map.put(css.CONTAINS_APIKEY, false);
		containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map.put(css.CONTAINS_CONTENT_TYPE, false);
		containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map.put(css.CONTAINS_SESSION_KEY, false);
		containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map.put(css.CONTAINS_ACCEPT, false);

		valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map = new HashMap<>();
		valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map.put(css.VALUE_OF_APIKEY, null);
		valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map.put(css.VALUE_OF_CONTENT_TYPE, null);
		valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map.put(css.VALUE_OF_SESSION_KEY, null);
		valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map.put(css.VALUE_OF_ACCEPT, null);
	}

	public void execute(final StatesOfServer state, final Vertx vertx, final HttpServerRequest bridge_between_server_and_client) {
		try {
			if (mSingletonOfHeaderChecker.execute(state, response, bridge_between_server_and_client, containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map, valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map)) {
				String query = mBehaviorOfQueryGenerator.execute(state, response,bridge_between_server_and_client,null,null);
				JsonObject rawCommand = utility.rawCommandJsonGenerator(query);

				vertx.eventBus().send("backend", rawCommand, new Handler<Message<JsonObject>>() {
					/*
					 * This handler recieves response from MySql DBMS
					 */
					@Override
					public void handle(Message<JsonObject> databaseMessage) {
						JsonObject databaseMessageBody = null;
						if (databaseMessage == null) {
							endResponse.endResponseWithDatabaseError(state, response, bridge_between_server_and_client, databaseMessageBody);
						} else {
							databaseMessageBody = databaseMessage.body();
							pmfs.printDatabaseMessage(state, databaseMessageBody);
							if (databaseMessageBody.getString(cs.DB_STATUS).equals(cs.DB_ERROR) == false) {
								mBehaviorOfProcessSendResponse.execute(state, response, databaseMessageBody, bridge_between_server_and_client, null);
							} else {
								endResponse.endResponseWithDatabaseError(state, response, bridge_between_server_and_client, databaseMessageBody);
							}
						}
					}
				});
			}
		} catch (Exception e) {
			container.logger().error(e.getStackTrace());
			endResponse.endResponseWithUnknownError(state, response, bridge_between_server_and_client);
		} finally {

		}

	}

}
