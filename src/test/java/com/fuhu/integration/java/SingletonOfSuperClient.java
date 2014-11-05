package com.fuhu.integration.java;

import static org.vertx.testtools.VertxAssert.assertNotNull;
import static org.vertx.testtools.VertxAssert.assertTrue;
import static org.vertx.testtools.VertxAssert.fail;
import static org.vertx.testtools.VertxAssert.testComplete;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.http.HttpClientRequest;
import org.vertx.java.core.http.HttpClientResponse;
import org.vertx.java.core.json.JsonObject;
import org.vertx.testtools.TestVerticle;

public class SingletonOfSuperClient extends TestVerticle {
	/* Setup for Factory pattern */
	protected final FactoryOfAssertionChecker mAssertionCheckerFactory = new FactoryOfAssertionChecker();
	protected final FactoryOfCurlCommandsSetter mCurlCommandsSetterFactory = new FactoryOfCurlCommandsSetter();
	/* Setup for Strategy pattern */
	protected static BehaviorOfCurlCommandDataGenerater mCurlCommandDataGenerater;
	protected static BehaviorOfReponseDataGenerater mReponseDataGenerater;
	protected static BehaviorOfAssertionChecker mAssertionChecker;
	protected BehaviorOfCurlCommandsSetter mCurlCommandsSetter;
	/* For State Machine */
	protected static StatesOfClient curState = StatesOfClient.STATE_RESET;
	/* Setup for Singleton Pattern */
	protected final SingletonOfClientConfigSetup mSingletonOfClientConfigSetup = SingletonOfClientConfigSetup.getInstance();
	protected final SingletonOfConstantsT ct = SingletonOfConstantsT.getInstance();
	protected final static SingletonOfConstantsT cts = SingletonOfConstantsT.getInstance();
	protected final static SingletonOfPrintingMethodsOfClient pmfc = SingletonOfPrintingMethodsOfClient.getInstance();

	/* Variables for HTTP Client */
	protected HttpClient client = null;
	protected static HttpClientRequest requestSendFromClienttoServer = null;
	/* For ClientConfig.json file */
	protected int CURL_HTTP_PORT;
	protected String CURL_HTTP_HOST;
	/* Variables for current data */
	protected static JsonObject currentServerResponseInJsonFormat;
	/**/
	protected static int statusCode = 0;
	protected JsonObject dbConfig;

	protected void sendRequest(StatesOfClient state) {
		resetState();
		setState(state);
		pmfc.printWhichStateIsTesting();

		try {
			if (CURL_HTTP_PORT != 443) {
				client = vertx.createHttpClient().setPort(CURL_HTTP_PORT).setHost(CURL_HTTP_HOST);
			} else {
				client = vertx.createHttpClient().setSSL(true).setTrustAll(true).setPort(CURL_HTTP_PORT).setHost(CURL_HTTP_HOST);
			}

			mCurlCommandsSetter = mCurlCommandsSetterFactory.createSetter(getState());
			mCurlCommandsSetter.execute(state);

			pmfc.printCurrentRequestAndPathInCurlCommand();
			requestSendFromClienttoServer = client.request(BehaviorOfCurlCommandsSetter.currentRequest, BehaviorOfCurlCommandsSetter.currentPath, new Handler<HttpClientResponse>() {
				/*
				 * This handler recieves SerΩ©ver response
				 */
				@Override
				public void handle(HttpClientResponse responseRecievedAtClientFromServer) {
					statusCode = responseRecievedAtClientFromServer.statusCode();
					responseRecievedAtClientFromServer.bodyHandler(new Handler<Buffer>() {
						/*
						 * This handler recieves Server response
						 * body buffer from previous handler
						 */
						@Override
						public void handle(Buffer body) {
							try {
								try {
									pmfc.printMessageFromServer(body);
									currentServerResponseInJsonFormat = new JsonObject(body.toString());
									getDataFromDB();
								} catch (Exception e) {

								}
								mAssertionChecker = mAssertionCheckerFactory.createChecker(getState());
								mAssertionChecker.execute(getState());

							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								pmfc.printEnd();
								testComplete();
							}
						}
					});
				}
			}).exceptionHandler(new Handler<Throwable>() {
				@Override
				public void handle(Throwable t) {
					t.printStackTrace();
				}
			});

			try {
				headersSetUp();
				jsonBodySetUp();
			} catch (Exception e) {

			}
		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			if (client != null) {
				client.close();
			}
		}
	}

	private void getDataFromDB() {
		switch (getState()) {
		default:
			break;
		}
	}

	private void headersSetUp() {

	}

	private void jsonBodySetUp() {
		requestSendFromClienttoServer.end();
	}

	protected static void resetState() {
		curState = StatesOfClient.STATE_RESET;
	}

	protected static void setState(StatesOfClient state) {
		curState = state;
	}

	public static StatesOfClient getState() {
		return curState;
	}

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
