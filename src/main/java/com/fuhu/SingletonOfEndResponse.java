package com.fuhu;

import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public class SingletonOfEndResponse {
	/* Setup for Singleton pattern */
	private static SingletonOfEndResponse instance = null;

	private SingletonOfEndResponse() {

	}

	public static SingletonOfEndResponse getInstance() {
		if (instance == null) {
			instance = new SingletonOfEndResponse();
		}
		return instance;
	}

	SingletonOfSwitchesOfServer switchesOfServer = SingletonOfSwitchesOfServer.getInstance();
	SingletonOfConstantsS cs = SingletonOfConstantsS.getInstance();
	SingletonOfPrintingMethodsOfServer pmfs = SingletonOfPrintingMethodsOfServer.getInstance();

	/* Ending HTTP response for sendUnknownErrorResponse case */
	protected void endResponseWithUnknownError(StatesOfServer state, JsonObject response, HttpServerRequest bridge_between_server_and_client) {
		response.putString(cs.STATUS, cs.UNKNOWN_ERROR).putString(cs.STATUS_DESCRIPTION, cs.UNKNOWN_ERROR_DESC);
		endResponseWithJson(state, response, bridge_between_server_and_client);
	}

	/*
	 * Ending HTTP response for sendInvalidOrIncorrectHttpRequestBodyResponse
	 * case
	 */
	protected void sendInvalidOrIncorrectHttpRequestBodyResponse(StatesOfServer state, JsonObject response, HttpServerRequest bridge_between_server_and_client) {
		response.putString(cs.STATUS, cs.INVALID_OR_INCORRECT_HTTP_REQUEST_BODY);
		response.putString(cs.STATUS_DESCRIPTION, cs.INVALID_OR_INCORRECT_HTTP_REQUEST_BODY_DESC);
		endResponseWithJson(state, response, bridge_between_server_and_client);
	}

	/* Ending HTTP response for database error response case */
	protected void endResponseWithDatabaseError(StatesOfServer state, JsonObject response, HttpServerRequest bridge_between_server_and_client, JsonObject databaseMessageBody) {
		if (switchesOfServer.isStatusDescriptionSwitch()) {
			response.putString(cs.STATUS, EnumOfAPIStatus.databaseError.getStatusCode()).putString(cs.STATUS_DESCRIPTION, databaseMessageBody.getString(cs.DB_MESSAGE));
			endResponseWithJson(state, response, bridge_between_server_and_client);
		} else {
			response.putString(cs.STATUS, EnumOfAPIStatus.databaseError.getStatusCode());
			endResponseWithJson(state, response, bridge_between_server_and_client);
		}
	}

	protected void endResponseWithBinaryData(StatesOfServer state, Buffer response, HttpServerRequest bridge_between_server_and_client) {
		bridge_between_server_and_client.response().end(response);
	}

	/* Ending HTTP response with Json */
	protected void endResponseWithJson(StatesOfServer state, JsonObject response, HttpServerRequest bridge_between_server_and_client) {
		pmfs.printResponseToClient(state, response);
		if (switchesOfServer.isDubug()) {
			bridge_between_server_and_client.response().end(response.encodePrettily());
		} else {
			bridge_between_server_and_client.response().end(response.encode());
		}
	}
}
