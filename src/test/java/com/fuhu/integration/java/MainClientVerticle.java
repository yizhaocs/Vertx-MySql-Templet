package com.fuhu.integration.java;

import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.json.JsonObject;
import org.vertx.testtools.TestVerticle;

public class MainClientVerticle extends TestVerticle{
	/* Setup for Singleton Pattern */
	protected final SingletonOfClientConfigSetup mSingletonOfClientConfigSetup = SingletonOfClientConfigSetup.getInstance();
	/* For ClientConfig.json file */
	protected int CURL_HTTP_PORT;
	protected String CURL_HTTP_HOST;
	protected JsonObject dbConfig;
	@Override
	public void start() {
		initialize();
		dbConfig = mSingletonOfClientConfigSetup.getDBconfig();
		CURL_HTTP_HOST = mSingletonOfClientConfigSetup.getServerHost();
		CURL_HTTP_PORT = mSingletonOfClientConfigSetup.getServerPort();
		container.deployModule(System.getProperty("vertx.modulename"), new AsyncResultHandler<String>() {
			@Override
			public void handle(AsyncResult<String> asyncResult) {
				container.deployModule("io.vertx~mod-mysql-postgresql_2.10~0.3.1", dbConfig, new AsyncResultHandler<String>() {
					public void handle(AsyncResult<String> asyncResult) {
						System.out.println("Client MySQL/Postgres module deployment ID: " + asyncResult.result());
						System.out.println("Client MySQL/Postgres module deployment failed: " + asyncResult.failed());
						if (asyncResult.failed()) {
							System.out.println("MySQL/Postgres module deployment asyncResult.cause printStackTrace: ");
							asyncResult.cause().printStackTrace();
						} else {
							startTests();
						}
					}
				});

			}
		});
	}
}
