package com.fuhu.integration.java;

import org.vertx.java.core.json.JsonObject;

public interface BehaviorOfAssertionChecker {
	public SingletonOfConstantsT ct = SingletonOfConstantsT.getInstance();

	public void execute(StatesOfClient state,JsonObject currentServerResponseInJsonFormat, int statusCode);

}
