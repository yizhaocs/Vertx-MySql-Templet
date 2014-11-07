package com.fuhu;

import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public interface BehaviorOfQueryGenerator {
	static SingletonOfConstantsS css = SingletonOfConstantsS.getInstance();
	static SingletonOfPrintingMethodsOfServer pmfs = SingletonOfPrintingMethodsOfServer.getInstance();
	SingletonOfConstantsS cs = SingletonOfConstantsS.getInstance();
	SingletonOfQueryBuilder queryGenerator = SingletonOfQueryBuilder.getInstance();
	SingletonOfUtility utility = SingletonOfUtility.getInstance();
	public String execute(StatesOfServer state, JsonObject response, HttpServerRequest bridge_between_server_and_client,Buffer curlBody, String currentTime);
}
