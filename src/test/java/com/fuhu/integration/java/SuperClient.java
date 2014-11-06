package com.fuhu.integration.java;

import static org.vertx.testtools.VertxAssert.fail;
import static org.vertx.testtools.VertxAssert.testComplete;

import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.http.HttpClientRequest;
import org.vertx.java.core.http.HttpClientResponse;
import org.vertx.java.core.json.JsonObject;

public class SuperClient extends MainClientVerticle {
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
	protected final SingletonOfConstantsT ct = SingletonOfConstantsT.getInstance();
	protected final static SingletonOfConstantsT cts = SingletonOfConstantsT.getInstance();
	protected final static SingletonOfPrintingMethodsOfClient pmfc = SingletonOfPrintingMethodsOfClient.getInstance();

	/* Variables for HTTP Client */
	protected HttpClient client = null;
	protected static HttpClientRequest requestSendFromClienttoServer = null;

	/* Variables for current data */
	protected JsonObject currentServerResponseInJsonFormat;
	protected Buffer currentServerResponseInBinaryStreamFormat;
	/**/
	protected int statusCode = 0;
	protected static String lastTimeModified;

	protected void sendRequest(StatesOfClient state) {
		resetState();
		setState(state);
		pmfc.printWhichStateIsTesting();
		addDelayBetweenInsertAndUpdate();
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
				 * This handler recieves Server response
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
									if (getState().equals(StatesOfClient.STATE_PER_PACKAGE_GET_1) || getState().equals(StatesOfClient.STATE_PER_PACKAGE_AND_USER_GET_1) || getState().equals(StatesOfClient.STATE_PER_PACKAGE_GET_2) || getState().equals(StatesOfClient.STATE_PER_PACKAGE_AND_USER_GET_2)
											|| getState().equals(StatesOfClient.STATE_PER_PACKAGE_GET_3) || getState().equals(StatesOfClient.STATE_PER_PACKAGE_AND_USER_GET_3) || getState().equals(StatesOfClient.STATE_PER_PACKAGE_GET_4)
											|| getState().equals(StatesOfClient.STATE_PER_PACKAGE_AND_USER_GET_4) || getState().equals(StatesOfClient.STATE_PER_PACKAGE_GET_5) || getState().equals(StatesOfClient.STATE_PER_PACKAGE_AND_USER_GET_5)) {
										currentServerResponseInBinaryStreamFormat = body;
									} else {
										pmfc.printMessageFromServer(body);
										currentServerResponseInJsonFormat = new JsonObject(body.toString());
									}
									getDataFromDB();
								} catch (Exception e) {

								}
								mAssertionChecker = mAssertionCheckerFactory.createChecker(getState());
								mAssertionChecker.execute(getState(), currentServerResponseInJsonFormat, currentServerResponseInBinaryStreamFormat, statusCode);

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
				sendRequest();
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
		case STATE_PER_PACKAGE_INSERT_1:
		case STATE_PER_PACKAGE_AND_USER_INSERT_1:
			lastTimeModified = currentServerResponseInJsonFormat.getString(lastTimeModified);
			break;
		default:
			break;
		}
	}

	private void headersSetUp() {
		if (getState().toString().indexOf("STATUS_CHECK") < 0) {
			requestSendFromClienttoServer.putHeader(cts.APIKEY_K, cts.APIKEY_V);
			if (getState().toString().indexOf("INSERT") >= 0 || getState().toString().indexOf("UPDATE") >= 0) {
				requestSendFromClienttoServer.putHeader(cts.CONTENT_TYPE_K, cts.CONTENT_TYPE_BINARY_DATA_V);
				requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_JSON_V);
			} else if (getState().toString().indexOf("GET") >= 0) {
				requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_BINARY_DATA_V);
			} else if (getState().toString().indexOf("DELETE") >= 0) {
				requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_JSON_V);
			}
		} else {
			if (getState().toString().indexOf("APIKEY_MISSING") >= 0) {
				// requestSendFromClienttoServer.putHeader(cts.APIKEY_K, cts.APIKEY_V);
				if (getState().toString().indexOf("INSERT") >= 0 || getState().toString().indexOf("UPDATE") >= 0) {
					requestSendFromClienttoServer.putHeader(cts.CONTENT_TYPE_K, cts.CONTENT_TYPE_BINARY_DATA_V);
					requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_JSON_V);
				} else if (getState().toString().indexOf("GET") >= 0) {
					requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_BINARY_DATA_V);
				} else if (getState().toString().indexOf("DELETE") >= 0) {
					requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_JSON_V);
				}
			} else if (getState().toString().indexOf("APIKEY_INVALID") >= 0) {
				requestSendFromClienttoServer.putHeader(cts.APIKEY_K, cts.APIKEY_INVALID_V);
				if (getState().toString().indexOf("INSERT") >= 0 || getState().toString().indexOf("UPDATE") >= 0) {
					requestSendFromClienttoServer.putHeader(cts.CONTENT_TYPE_K, cts.CONTENT_TYPE_BINARY_DATA_V);
					requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_JSON_V);
				} else if (getState().toString().indexOf("GET") >= 0) {
					requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_BINARY_DATA_V);
				} else if (getState().toString().indexOf("DELETE") >= 0) {
					requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_JSON_V);
				}
			} else if (getState().toString().indexOf("ACCEPT_MISSING") >= 0) {
				requestSendFromClienttoServer.putHeader(cts.APIKEY_K, cts.APIKEY_V);
				if (getState().toString().indexOf("INSERT") >= 0 || getState().toString().indexOf("UPDATE") >= 0) {
					requestSendFromClienttoServer.putHeader(cts.CONTENT_TYPE_K, cts.CONTENT_TYPE_BINARY_DATA_V);
					// requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_JSON_V);
				} else if (getState().toString().indexOf("GET") >= 0) {
					// requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_BINARY_DATA_V);
				} else if (getState().toString().indexOf("DELETE") >= 0) {
					// requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_JSON_V);
				}
			} else if (getState().toString().indexOf("ACCEPT_INVALID") >= 0) {
				requestSendFromClienttoServer.putHeader(cts.APIKEY_K, cts.APIKEY_V);
				if (getState().toString().indexOf("INSERT") >= 0 || getState().toString().indexOf("UPDATE") >= 0) {
					requestSendFromClienttoServer.putHeader(cts.CONTENT_TYPE_K, cts.CONTENT_TYPE_BINARY_DATA_V);
					requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_JSON_INVALID_V);
				} else if (getState().toString().indexOf("GET") >= 0) {
					requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_BINARY_DATA_INVALID_V);
				} else if (getState().toString().indexOf("DELETE") >= 0) {
					requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_JSON_INVALID_V);
				}
			} else if (getState().toString().indexOf("CONTENTTYPE_MISSING") >= 0) {
				requestSendFromClienttoServer.putHeader(cts.APIKEY_K, cts.APIKEY_V);
				if (getState().toString().indexOf("INSERT") >= 0 || getState().toString().indexOf("UPDATE") >= 0) {
					// requestSendFromClienttoServer.putHeader(cts.CONTENT_TYPE_K, cts.CONTENT_TYPE_BINARY_DATA_V);
					requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_JSON_V);
				} else if (getState().toString().indexOf("GET") >= 0) {
					requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_BINARY_DATA_V);
				} else if (getState().toString().indexOf("DELETE") >= 0) {
					requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_JSON_V);
				}
			} else if (getState().toString().indexOf("CONTENTTYPE_INVALID") >= 0) {
				requestSendFromClienttoServer.putHeader(cts.APIKEY_K, cts.APIKEY_V);
				if (getState().toString().indexOf("INSERT") >= 0 || getState().toString().indexOf("UPDATE") >= 0) {
					requestSendFromClienttoServer.putHeader(cts.CONTENT_TYPE_K, cts.CONTENT_TYPE_BINARY_DATA_INVALID_V);
					requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_JSON_V);
				} else if (getState().toString().indexOf("GET") >= 0) {
					requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_BINARY_DATA_V);
				} else if (getState().toString().indexOf("DELETE") >= 0) {
					requestSendFromClienttoServer.putHeader(cts.ACCEPT_K, cts.ACCEPT_JSON_V);
				}
			}
		}

	}

	private void sendRequest() {
		boolean a = getState().equals(StatesOfClient.STATE_PER_PACKAGE_INSERT_1);
		boolean b = getState().equals(StatesOfClient.STATE_PER_PACKAGE_INSERT_2);
		boolean c = getState().equals(StatesOfClient.STATE_PER_PACKAGE_UPDATE);
		boolean d = getState().equals(StatesOfClient.STATE_PER_PACKAGE_AND_USER_INSERT_1);
		boolean e = getState().equals(StatesOfClient.STATE_PER_PACKAGE_AND_USER_INSERT_2);
		boolean f = getState().equals(StatesOfClient.STATE_PER_PACKAGE_AND_USER_UPDATE);
		if (a || b || c || d || e || f) {
			requestSendFromClienttoServer.end(BehaviorOfCurlCommandsSetter.currentDataSendToServer);
		} else {
			requestSendFromClienttoServer.end();
		}
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

	private void addDelayBetweenInsertAndUpdate() {
		if (getState().equals(StatesOfClient.STATE_PER_PACKAGE_UPDATE) || getState().equals(StatesOfClient.STATE_PER_PACKAGE_AND_USER_UPDATE)) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
