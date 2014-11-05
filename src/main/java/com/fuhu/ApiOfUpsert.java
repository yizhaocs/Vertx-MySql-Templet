package com.fuhu;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public class ApiOfUpsert extends SuperClassOfApis {
	private BehaviorOfProcessSendResponse mBehaviorOfProcessSendResponse = null;

	public ApiOfUpsert() {
		mBehaviorOfProcessSendResponse = new ProcessSendResponseOfUpsert();
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
					String[] insertColumnsWithoutUserKey = { cs.perPackageAndUser_TableColumns[1], cs.perPackageAndUser_TableColumns[2], cs.perPackageAndUser_TableColumns[3], cs.perPackageAndUser_TableColumns[4], cs.perPackageAndUser_TableColumns[5] };
					String[] valuesWithoutUserKey = { "'" + packageName + "'", "'" + streamKey + "'", "X'" + hex + "'", currentTime, currentTime };
					String[] insertColumnsWithUserKey = { cs.perPackageAndUser_TableColumns[0], cs.perPackageAndUser_TableColumns[1], cs.perPackageAndUser_TableColumns[2], cs.perPackageAndUser_TableColumns[3], cs.perPackageAndUser_TableColumns[4], cs.perPackageAndUser_TableColumns[5] };
					String[] valuesWithUserKey = { "'" + userKey + "'", "'" + packageName + "'", "'" + streamKey + "'", "X'" + hex + "'", currentTime, currentTime };
					String[] updateColumns = { cs.perPackageAndUser_TableColumns[3], cs.perPackageAndUser_TableColumns[5] };
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
							mBehaviorOfProcessSendResponse.execute(state, databaseMessage, bridge_between_server_and_client);
						}
					});
				}
			});
		} catch (Exception e) {
			container.logger().error(e.getStackTrace());
			JsonObject response = new JsonObject();
			response.putString("status", "1");
			response.putString("statusDescription", "Unknown Error");
			bridge_between_server_and_client.response().end(response.encodePrettily());
		} finally {

		}
	}

}
