package com.yizhao.integration.java;

import java.io.File;

import org.vertx.java.core.buffer.Buffer;



public abstract class BehaviorOfCurlCommandsSetter {
	protected SingletonOfConstantsT ct = SingletonOfConstantsT.getInstance();
	protected SingletonOfPrintingMethodsOfClient pmfc = SingletonOfPrintingMethodsOfClient.getInstance();
	protected static String currentRequest = null;
	protected static String currentPath = null;
	protected static Buffer currentDataSendToServer = null;

	public abstract void execute(StatesOfClient state);
}