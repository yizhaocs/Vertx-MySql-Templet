package com.yizhao;

import java.io.FileOutputStream;
import java.io.IOException;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

public class ApiOfGet extends SuperClassOfApis {
	public ApiOfGet() {

	}

	public void execute(StatesOfServer state, final Vertx vertx, final HttpServerRequest bridge_between_server_and_client) {
		String packageName = bridge_between_server_and_client.params().get("packageName");
		String streamKey = bridge_between_server_and_client.params().get("streamKey");
		String[] whereClauseCoulmns = { cs.perPackageAndUser_TableColumns[0], cs.perPackageAndUser_TableColumns[1], cs.perPackageAndUser_TableColumns[2] };
		String[] whereClauseValues = { "'\"" + "\"'", "'" + packageName + "'", "'" + streamKey + "'" };

		String queryResult = qg.select(cs.perPackageAndUser_TableColumns[3], cs.tableName, whereClauseCoulmns, whereClauseValues);
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
				JsonArray binaryDataArray = results.get(0);
				byte[] bytearr = new byte[binaryDataArray.size()];
				for (int i = 0; i < binaryDataArray.size(); i++) {
					bytearr[i] = binaryDataArray.get(i);
				}
				byteArrayToFile(bytearr);
				bridge_between_server_and_client.response().end(new Buffer().appendBytes(bytearr));
			}
		});
	}

	public void byteArrayToFile(byte[] byteArray) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream("/Users/yizhao/Desktop/abc.png");
			out.write(byteArray);
		} catch (IOException e) {
			System.out.println("Caught IOException: " + e.getMessage());
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println("Caught IOException: " + e.getMessage());
				}
			}
		}
	}
}
