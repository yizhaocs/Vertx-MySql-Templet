package com.fuhu;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public class ApiOfUpsert extends SuperClassOfApis {
	private BehaviorOfDatabaseResponseProcessor mBehaviorOfProcessSendResponse = null;
	private JsonObject response;

	public ApiOfUpsert() {
		response = new JsonObject();
		response.putString(cs.NABI_CLIENT_DATA_BACKUP_APIVersion_K, cs.NABI_CLIENT_DATA_BACKUP_APIVersion_V);
		mBehaviorOfProcessSendResponse = new ProcessDatabaseResponseOfUpsert();
	}

	public void execute(final StatesOfServer state, final Vertx vertx, final HttpServerRequest bridge_between_server_and_client) {
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
					JsonObject rawCommandJson = new JsonObject();
					rawCommandJson.putString("action", "raw");
					rawCommandJson.putString("command", queryResult);
					System.out.println("rawCommandJson:" + rawCommandJson.encodePrettily());
					vertx.eventBus().send("backend", rawCommandJson, new Handler<Message<JsonObject>>() {
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
