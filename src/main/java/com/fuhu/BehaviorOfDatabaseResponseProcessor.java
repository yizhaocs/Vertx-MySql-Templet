package com.fuhu;

import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public interface BehaviorOfDatabaseResponseProcessor {
	static SingletonOfConstantsS css = SingletonOfConstantsS.getInstance();
	static SingletonOfPrintingMethodsOfServer pmfs = SingletonOfPrintingMethodsOfServer.getInstance();
	SingletonOfEndResponse endResponse = SingletonOfEndResponse.getInstance();
	SingletonOfConstantsS cs = SingletonOfConstantsS.getInstance();
	SingletonOfUtility utility = SingletonOfUtility.getInstance();
	void execute(StatesOfServer state,JsonObject response, JsonObject databaseMessage, HttpServerRequest bridge_between_server_and_client, String currentTime);
}
