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
			bridge_between_server_and_client.expectMultiPart(true);
			bridge_between_server_and_client.uploadHandler(new Handler<HttpServerFileUpload>() {
				public void handle(final HttpServerFileUpload upload) {
					final Buffer mainBuffer = new Buffer();
					upload.dataHandler(new Handler<Buffer>() {
						@Override
						public void handle(Buffer buffer) {
							mainBuffer.appendBuffer(buffer);
						}
					}).endHandler(new Handler<Void>() {
						@Override
						public void handle(Void arg0) {
							String currentTime = getCurServerTime();
							StringBuilder queryBuilder = new StringBuilder();
							queryBuilder.append(" INSERT INTO ");
							queryBuilder.append(" per_package(");
							queryBuilder.append(cs.perPackage_TableColumns[1] + ",");
							queryBuilder.append(cs.perPackage_TableColumns[2] + ",");
							queryBuilder.append(cs.perPackage_TableColumns[3] + ",");
							queryBuilder.append(cs.perPackage_TableColumns[4] + ",");
							queryBuilder.append(cs.perPackage_TableColumns[5] + ",");
							queryBuilder.append(cs.perPackage_TableColumns[6]);
							queryBuilder.append(")");
						//	queryBuilder.append("test" + "(" + "a, b" + ")");
							queryBuilder.append(" VALUES(");
							queryBuilder.append("\"");
							queryBuilder.append(bridge_between_server_and_client.params().get("packageName"));
							queryBuilder.append("\"" + ",");
							queryBuilder.append("\"");
							queryBuilder.append(bridge_between_server_and_client.params().get("streamKey"));
							queryBuilder.append("\"" + ",");
							queryBuilder.append("\"");
							queryBuilder.append(Arrays.toString(mainBuffer.getBytes()));
							queryBuilder.append("\"" + ",");
							queryBuilder.append(0 + ",");
							queryBuilder.append(currentTime + ",");
							queryBuilder.append(currentTime);
							queryBuilder.append(")");
							queryBuilder.append(" ON DUPLICATE KEY UPDATE ");
							queryBuilder.append(cs.perPackage_TableColumns[0] + "=VALUES(" + cs.perPackage_TableColumns[0] + "),");
							queryBuilder.append(cs.perPackage_TableColumns[1] + "=VALUES(" + cs.perPackage_TableColumns[1] + "),");
							queryBuilder.append(cs.perPackage_TableColumns[2] + "=VALUES(" + cs.perPackage_TableColumns[2] + "),");
							queryBuilder.append(cs.perPackage_TableColumns[3] + "=VALUES(" + cs.perPackage_TableColumns[3] + "),");
							queryBuilder.append(cs.perPackage_TableColumns[4] + "=VALUES(" + cs.perPackage_TableColumns[4] + "),");
							queryBuilder.append(cs.perPackage_TableColumns[5] + "=VALUES(" + cs.perPackage_TableColumns[5] + "),");
							queryBuilder.append(cs.perPackage_TableColumns[6] + "=VALUES(" + cs.perPackage_TableColumns[6] + ")");
							queryBuilder.append(" ; ");
							String queryResult = queryBuilder.toString();
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
									response.putString("status", "0");
									response.putObject("result", databaseMessageBody);
									bridge_between_server_and_client.response().end(response.encodePrettily());
								}
							});
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
