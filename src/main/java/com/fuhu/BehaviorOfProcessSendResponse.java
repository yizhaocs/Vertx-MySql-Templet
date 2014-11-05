package com.fuhu;

import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public interface BehaviorOfProcessSendResponse {
	static SingletonOfConstantsS css = SingletonOfConstantsS.getInstance();
	static SingletonOfPrintingMethodsOfServer pmfs = SingletonOfPrintingMethodsOfServer.getInstance();
	SingletonOfEndResponse endResponse = SingletonOfEndResponse.getInstance();
	SingletonOfConstantsS cs = SingletonOfConstantsS.getInstance();
	void execute(StatesOfServer state,Message<JsonObject> databaseMessage, HttpServerRequest bridge_between_server_and_client, String currentTime);
}
