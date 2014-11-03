package com.yizhao;

import java.util.Arrays;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerFileUpload;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

public class ApiOfGet extends SuperClassOfApis {

	public ApiOfGet() {

	}

	public void execute(StatesOfServer state, final Vertx vertx, final HttpServerRequest bridge_between_server_and_client) {
		String queryResult = qg.select("*", "backup", conditionBuilder(bridge_between_server_and_client));
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
				JsonArray databaseMessageResults = databaseMessageBody.getArray("results");
				JsonArray results = databaseMessageResults.get(0);
				JsonArray binaryData = results.get(3);
				JsonObject response = new JsonObject();
				response.putString("status", "okay");
				response.putArray("db", binaryData);
				// response.putString("binary data", currentTime);
				// response.putString("lastTimeModified", currentTime);
				// response.putString("timeCreated", currentTime);
				// response.putObject("result", databaseMessageBody);
				bridge_between_server_and_client.response().end(response.encodePrettily());
			}
		});
	}

	private String conditionBuilder(HttpServerRequest bridge_between_server_and_client) {
		StringBuilder conditions = new StringBuilder();
		conditions.append(cs.perPackageAndUser_TableColumns[1] + "=" + "\"" + bridge_between_server_and_client.params().get("packageName") + "\"");
		conditions.append(" AND ");
		conditions.append(cs.perPackageAndUser_TableColumns[2] + "=" + "\"" + bridge_between_server_and_client.params().get("streamKey") + "\"");
		conditions.append(";");
		return conditions.toString();
	}
}
