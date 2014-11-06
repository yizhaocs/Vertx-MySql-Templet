package com.fuhu.integration.java;

import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.json.JsonObject;

public interface BehaviorOfAssertionChecker {
	public SingletonOfConstantsT ct = SingletonOfConstantsT.getInstance();

	public void execute(StatesOfClient state,JsonObject currentServerResponseInJsonFormat,Buffer currentServerResponseInBinaryStreamFormat, int statusCode);

}
