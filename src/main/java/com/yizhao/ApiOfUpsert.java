package com.yizhao;

import java.util.Arrays;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerFileUpload;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public class ApiOfUpsert extends SuperClassOfApis {
	public ApiOfUpsert() {

	}

	public void execute(StatesOfServer state, final Vertx vertx, final HttpServerRequest bridge_between_server_and_client) {
		try {
			bridge_between_server_and_client.bodyHandler(new Handler<Buffer>() {
				/*
				 * This handler recieves curl body buffer from Client
				 */
				@Override
				public void handle(Buffer curlBody) {
					String packageName = bridge_between_server_and_client.params().get("packageName");
					String streamKey = bridge_between_server_and_client.params().get("streamKey");
					StringBuilder binaryString = new StringBuilder(Arrays.toString(curlBody.getBytes()));
					final String currentTime = getCurServerTime();
					String[] insertColumns = { cs.perPackageAndUser_TableColumns[1], cs.perPackageAndUser_TableColumns[2], cs.perPackageAndUser_TableColumns[3], cs.perPackageAndUser_TableColumns[4], cs.perPackageAndUser_TableColumns[5] };
					String[] values = { "\"" + packageName + "\"", "\"" + streamKey + "\"", "\"" + binaryString + "\"", currentTime, currentTime };
					String[] updateColumns = { cs.perPackageAndUser_TableColumns[3], cs.perPackageAndUser_TableColumns[5] };

					String queryResult = qg.upsert(cs.tableName, insertColumns, values, updateColumns);
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
							response.putObject("status", databaseMessageBody);
							bridge_between_server_and_client.response().end(response.encodePrettily());
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
