package com.fuhu;

/*
 * Copyright 2013 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 */

import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.platform.Verticle;

/*
 This is a simple Java verticle which receives `ping` messages on the event bus and sends back `pong` replies
 */
public class MainServerVerticle extends Verticle {
	SingletonOfQueryBuilder queryGenerator = SingletonOfQueryBuilder.getInstance();
	SingletonOfUtility utility = SingletonOfUtility.getInstance();
	SingletonOfSwitchesOfServer switchesOfServer = SingletonOfSwitchesOfServer.getInstance();
	SingletonOfConstantsS cs = SingletonOfConstantsS.getInstance();
	static SingletonOfConstantsS css = SingletonOfConstantsS.getInstance();
	static SingletonOfPrintingMethodsOfServer pmfs = SingletonOfPrintingMethodsOfServer.getInstance();
	SingletonOfEndResponse endResponse = SingletonOfEndResponse.getInstance();
	SingletonOfServerConfigSetup mSingletonOfServerConfigSetup = SingletonOfServerConfigSetup.getInstance();
	SingletonOfHeaderChecker mSingletonOfHeaderChecker = SingletonOfHeaderChecker.getInstance();
	ApiOfUpsert mApiOfPost;
	ApiOfGet mApiOfGet;
	ApiOfDelete mApiOfDelete;

	private void deployMySqlModule() {
		container.deployModule("io.vertx~mod-mysql-postgresql_2.10~0.3.1", mSingletonOfServerConfigSetup.getDBconfig(), new AsyncResultHandler<String>() {
			public void handle(AsyncResult<String> asyncResult) {
				System.out.println("Server MySQL/Postgres module deployment ID: " + asyncResult.result());
				System.out.println("Server MySQL/Postgres module deployment failed: " + asyncResult.failed());
				if (asyncResult.failed()) {
					System.out.println("MySQL/Postgres module deployment asyncResult.cause printStackTrace: ");
					asyncResult.cause().printStackTrace();
				}
			}
		});
	}

	@Override
	public void start() {
		deployMySqlModule();
		utility.logger = container.logger();
		RouteMatcher httpRouteMatcher = new RouteMatcher();
		HttpServer httpServer = vertx.createHttpServer();
		httpServer.requestHandler(httpRouteMatcher);
		httpServer.listen(8080, "0.0.0.0");

		// curl -v --request PUT --data-binary "@3.png" http://localhost:8080/cloud/comfuhunabiradio/stream/stations --trace-ascii /dev/stdout
		httpRouteMatcher.put(cs.PATH_OF_PER_PACKAGE_UPSERT_AND_DELETE, new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest bridge_between_server_and_client) {
				container.logger().info("Invoked at upsert API");
				mApiOfPost = new ApiOfUpsert();
				mApiOfPost.execute(StatesOfServer.STATE_PER_PACKAGE_UPSERT, vertx, bridge_between_server_and_client);
			}
		});

		// curl -v -X GET http://localhost:8080/cloud/comfuhunabiradio/stream/stations/timestamp?ts=9999999999
		httpRouteMatcher.get(cs.PATH_OF_PER_PACKAGE_GET, new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest bridge_between_server_and_client) {
				container.logger().info("Invoked at get API");
				mApiOfGet = new ApiOfGet();
				mApiOfGet.execute(StatesOfServer.STATE_PER_PACKAGE_GET, vertx, bridge_between_server_and_client);
			}
		});

		// curl -v -X DELETE http://localhost:8080/cloud/comfuhunabiradio/stream/stations
		httpRouteMatcher.delete(cs.PATH_OF_PER_PACKAGE_UPSERT_AND_DELETE, new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest bridge_between_server_and_client) {
				container.logger().info("Invoked at delete API");
				mApiOfDelete = new ApiOfDelete();
				mApiOfDelete.execute(StatesOfServer.STATE_PER_PACKAGE_DELETE, vertx, bridge_between_server_and_client);
			}
		});

		// curl -v --request PUT --data-binary "@3.png" http://localhost:8080/cloud/user/yizhao/comfuhunabiradio/stream/stations --trace-ascii /dev/stdout
		httpRouteMatcher.put(cs.PATH_OF_PER_PACKAGE_AND_USER_UPSERT_AND_DELETE, new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest bridge_between_server_and_client) {
				container.logger().info("Invoked at upsert API");
				mApiOfPost = new ApiOfUpsert();
				mApiOfPost.execute(StatesOfServer.STATE_PER_PACKAGE_AND_USER_UPSERT, vertx, bridge_between_server_and_client);
			}
		});

		// curl -v -X GET http://localhost:8080/cloud/user/yizhao/comfuhunabiradio/stream/stations/timestamp?ts=1234567890
		httpRouteMatcher.get(cs.PATH_OF_PER_PACKAGE_AND_USER_GET, new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest bridge_between_server_and_client) {
				container.logger().info("Invoked at get API");
				mApiOfGet = new ApiOfGet();
				mApiOfGet.execute(StatesOfServer.STATE_PER_PACKAGE_AND_USER_GET, vertx, bridge_between_server_and_client);
			}
		});

		// curl -v -X DELETE http://localhost:8080/cloud/user/yizhao/comfuhunabiradio/stream/stations
		httpRouteMatcher.delete(cs.PATH_OF_PER_PACKAGE_AND_USER_UPSERT_AND_DELETE, new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest bridge_between_server_and_client) {
				container.logger().info("Invoked at delete API");
				mApiOfDelete = new ApiOfDelete();
				mApiOfDelete.execute(StatesOfServer.STATE_PER_PACKAGE_AND_USER_DELETE, vertx, bridge_between_server_and_client);
			}
		});

		httpRouteMatcher.noMatch(new Handler<HttpServerRequest>() {
			@Override
			public void handle(HttpServerRequest req) {
				req.response().end("{ \"status\": \"1\", \"api\": \"Nabi-Client-Data-Backup API no match\" }");
			}
		});
	}
}
