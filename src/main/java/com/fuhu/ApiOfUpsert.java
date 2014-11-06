package com.fuhu;

import java.util.HashMap;
import java.util.Map;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public class ApiOfUpsert extends SuperClassOfApis {
	private BehaviorOfQueryGenerator mBehaviorOfQueryGenerator = null;
	private BehaviorOfDatabaseResponseProcessor mBehaviorOfProcessSendResponse = null;
	private JsonObject response;
	private Map<String, Boolean> containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map;
	private Map<String, String> valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map;
	public ApiOfUpsert() {
		response = new JsonObject();
		response.putString(cs.NABI_CLIENT_DATA_BACKUP_APIVersion_K, cs.NABI_CLIENT_DATA_BACKUP_APIVersion_V);
		mBehaviorOfQueryGenerator = new QueryGeneratorOfUpsert();
		mBehaviorOfProcessSendResponse = new ProcessDatabaseResponseOfUpsert();
		
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
		getHeaders(state, bridge_between_server_and_client, containsAPIKEY_containsContentType_containsSessionKey_containsAccept_Map, valueOfAPIKEY_valueOfContentType_valueOfSessionKey_valueOfAccept_Map);
		try {
			bridge_between_server_and_client.bodyHandler(new Handler<Buffer>() {
				/*
				 * This handler recieves curl body buffer from Client
				 */
				@Override
				public void handle(Buffer curlBody) {
					String userKey = state.equals(StatesOfServer.STATE_PER_PACKAGE_AND_USER_UPSERT) ? bridge_between_server_and_client.params().get("userKey") : null;
					String packageName = bridge_between_server_and_client.params().get("packageName");
					String streamKey = bridge_between_server_and_client.params().get("streamKey");
					// StringBuilder binaryString = new StringBuilder(bytesToHex(curlBody.getBytes()));
					String hex = utility.byteArrayToHexString(curlBody.getBytes());
					final String currentTime = utility.getCurServerTime();
					String[] insertColumnsWithoutUserKey = { cs.perPackageAndUser_TableColumns[1], cs.perPackageAndUser_TableColumns[2], cs.perPackageAndUser_TableColumns[3], cs.perPackageAndUser_TableColumns[4] };
					String[] valuesWithoutUserKey = { "'" + packageName + "'", "'" + streamKey + "'", "X'" + hex + "'", currentTime };
					String[] insertColumnsWithUserKey = { cs.perPackageAndUser_TableColumns[0], cs.perPackageAndUser_TableColumns[1], cs.perPackageAndUser_TableColumns[2], cs.perPackageAndUser_TableColumns[3], cs.perPackageAndUser_TableColumns[4] };
					String[] valuesWithUserKey = { "'" + userKey + "'", "'" + packageName + "'", "'" + streamKey + "'", "X'" + hex + "'", currentTime };
					String[] updateColumns = { cs.perPackageAndUser_TableColumns[3], cs.perPackageAndUser_TableColumns[4] };
					String queryResult = state.equals(StatesOfServer.STATE_PER_PACKAGE_AND_USER_UPSERT) ? queryGenerator.upsert(cs.tableName, insertColumnsWithUserKey, valuesWithUserKey, updateColumns) : queryGenerator.upsert(cs.tableName, insertColumnsWithoutUserKey, valuesWithoutUserKey,
							updateColumns);
					System.out.println("query:" + queryResult);
					
					vertx.eventBus().send("backend", utility.rawCommandJsonGenerator(queryResult), new Handler<Message<JsonObject>>() {
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
								if (databaseMessageBody.getString(cs.DB_STATUS).equals(cs.DB_ERROR) == false) {
									mBehaviorOfProcessSendResponse.execute(state, response, databaseMessageBody, bridge_between_server_and_client, currentTime);
								} else {
									endResponse.endResponseWithDatabaseError(state, response, bridge_between_server_and_client, databaseMessageBody);
								}
							}
						}
					});
				}
			});
		} catch (Exception e) {
			container.logger().error(e.getStackTrace());
			endResponse.endResponseWithUnknownError(state, response, bridge_between_server_and_client);
		} finally {

		}
	}

}
