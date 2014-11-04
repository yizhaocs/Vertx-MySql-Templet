package com.fuhu;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public class ApiOfDelete extends SuperClassOfApis {

	public ApiOfDelete() {

	}

	public void execute(StatesOfServer state, final Vertx vertx, final HttpServerRequest bridge_between_server_and_client) {
		String userKey = state.equals(StatesOfServer.STATE_PER_PACKAGE_AND_USER_DELETE) ? bridge_between_server_and_client.params().get("userKey") : null;
		String packageName = bridge_between_server_and_client.params().get("packageName");
		String streamKey = bridge_between_server_and_client.params().get("streamKey");
		
		String[] whereClauseCoulmns = { cs.perPackageAndUser_TableColumns[0], cs.perPackageAndUser_TableColumns[1], cs.perPackageAndUser_TableColumns[2] };
		String[] whereClauseValues = {state.equals(StatesOfServer.STATE_PER_PACKAGE_AND_USER_DELETE)? "'" + userKey + "'" :"'\"" + "\"'", "'" + packageName + "'", "'" + streamKey + "'" };
		String queryResult = queryGenerator.delete(cs.tableName, whereClauseCoulmns, whereClauseValues);

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
				JsonObject databaseMessageBody = databaseMessage.body();
				JsonObject response = new JsonObject();
				response.putString("status", "okay");
				response.putObject("result", databaseMessageBody);
				bridge_between_server_and_client.response().end(response.encodePrettily());
			}
		});
	}
	


}
