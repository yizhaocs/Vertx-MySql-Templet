package com.yizhao;

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

import java.io.IOException;

import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Verticle;

/*
 This is a simple Java verticle which receives `ping` messages on the event bus and sends back `pong` replies
 */
public class MainVerticle extends Verticle {
	SingletonOfConfig mSingletonOfConfig = SingletonOfConfig.getInstance();
	ApiOfUpsert mApiOfPost;
	ApiOfGet mApiOfGet;
	ApiOfDelete mApiOfDelete;

	SingletonOfConstantsS cs = SingletonOfConstantsS.getInstance();

	private void init() {
		JsonObject dbConfig = null;
		try {
			dbConfig = mSingletonOfConfig.getDbConnectionParam();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		container.deployModule("io.vertx~mod-mysql-postgresql_2.10~0.3.1", dbConfig, new AsyncResultHandler<String>() {
			public void handle(AsyncResult<String> asyncResult) {
				System.out.println("MySQL/Postgres module deployment ID: " + asyncResult.result());
				System.out.println("MySQL/Postgres module deployment failed: " + asyncResult.failed());
				if (asyncResult.failed()) {
					System.out.println("MySQL/Postgres module deployment asyncResult.cause printStackTrace: ");
					asyncResult.cause().printStackTrace();
				}
			}
		});
	}

	public void start() {
		init();
		RouteMatcher httpRouteMatcher = new RouteMatcher();
		HttpServer httpServer = vertx.createHttpServer();
		httpServer.requestHandler(httpRouteMatcher);
		httpServer.listen(8080, "0.0.0.0");

		// 
		// curl -v -X PUT http://localhost:8080/cloud/comfuhunabiradio/stream/stations -F "file=@3.png" --trace-ascii /dev/stdout
		httpRouteMatcher.put(cs.PATH_OF_PER_PACKAGE, new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest bridge_between_server_and_client) {
				container.logger().info("Invoked at upsert API");
				mApiOfPost = new ApiOfUpsert();
				mApiOfPost.execute(StatesOfServer.STATE_PER_PACKAGE_UPSERT, vertx, bridge_between_server_and_client);
			}
		});

		// curl -v -X GET http://localhost:8080/cloud/comfuhunabiradio/stream/stations 
		httpRouteMatcher.get(cs.PATH_OF_PER_PACKAGE, new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest bridge_between_server_and_client) {
				container.logger().info("Invoked at get API");
				mApiOfGet = new ApiOfGet();
				mApiOfGet.execute(StatesOfServer.STATE_PER_PACKAGE_GET, vertx, bridge_between_server_and_client);
			}
		});

		// curl -v -X DELETE http://localhost:8080/cloud/comfuhunabiradio/stream/stations 
		httpRouteMatcher.delete(cs.PATH_OF_PER_PACKAGE, new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest bridge_between_server_and_client) {
				container.logger().info("Invoked at delete API");
				mApiOfDelete = new ApiOfDelete();
				mApiOfDelete.execute(StatesOfServer.STATE_PER_PACKAGE_DELETE, vertx, bridge_between_server_and_client);
			}
		});
		
		
		httpRouteMatcher.noMatch(new Handler<HttpServerRequest>() {
			@Override
			public void handle(HttpServerRequest req) {
				req.response().end("{ \"status\": \"1\", \"api\": \"nabicloud no match\" }");
			}
		});
	}
}
