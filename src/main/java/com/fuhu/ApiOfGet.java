package com.fuhu;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

public class ApiOfGet extends SuperClassOfApis {
	private BehaviorOfQueryGenerator mBehaviorOfQueryGenerator = null;
	private BehaviorOfDatabaseResponseProcessor mBehaviorOfProcessSendResponse = null;
	private JsonObject response;

	public ApiOfGet() {
		response = new JsonObject();
		response.putString(cs.NABI_CLIENT_DATA_BACKUP_APIVersion_K, cs.NABI_CLIENT_DATA_BACKUP_APIVersion_V);
		mBehaviorOfQueryGenerator = new QueryGeneratorOfGet();
		mBehaviorOfProcessSendResponse = new ProcessDatabaseResponseOfGet();
	}

	public void execute(final StatesOfServer state, final Vertx vertx, final HttpServerRequest bridge_between_server_and_client) {
		try {
			String userKey = state.equals(StatesOfServer.STATE_PER_PACKAGE_AND_USER_GET) ? bridge_between_server_and_client.params().get("userKey") : null;
			String packageName = bridge_between_server_and_client.params().get("packageName");
			String streamKey = bridge_between_server_and_client.params().get("streamKey");
			String ts = bridge_between_server_and_client.params().get(cs.TS);

			String[] whereClauseCoulmns = { cs.perPackageAndUser_TableColumns[0], cs.perPackageAndUser_TableColumns[1], cs.perPackageAndUser_TableColumns[2], cs.perPackageAndUser_TableColumns[4] };
			String[] whereClauseOperator = { "=", "=", "=", "<" };
			String[] whereClauseValues = { state.equals(StatesOfServer.STATE_PER_PACKAGE_AND_USER_GET) ? "'" + userKey + "'" : "'\"" + "\"'", "'" + packageName + "'", "'" + streamKey + "'", ts };

			String queryResult = queryGenerator.select(cs.perPackageAndUser_TableColumns[3], cs.tableName, whereClauseCoulmns, whereClauseOperator, whereClauseValues);

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
						pmfs.printDatabaseMessage(state, databaseMessageBody);
						if (databaseMessageBody.getString(cs.DB_STATUS).equals(cs.DB_ERROR) == false) {
							mBehaviorOfProcessSendResponse.execute(state, response, databaseMessageBody, bridge_between_server_and_client, null);
						} else {
							endResponse.endResponseWithDatabaseError(state, response, bridge_between_server_and_client, databaseMessageBody);
						}
					}
				}
			});
		} catch (Exception e) {
			container.logger().error(e.getStackTrace());
			endResponse.endResponseWithUnknownError(state, response, bridge_between_server_and_client);
		} finally {

		}
	}

}
