package com.fuhu.integration.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.vertx.java.core.buffer.Buffer;

public class CurlCommandsSetterOfPerPackage extends BehaviorOfCurlCommandsSetter {

	@Override
	public void execute(StatesOfClient state) {
		switch (state) {
		case STATE_PER_PACKAGE_INSERT_1:
		case STATE_PER_PACKAGE_INSERT_2:
			currentRequest = ct.PUT_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE_UPSERT_AND_DELETE;
			currentDataSendToServer = ct.TEST_BINARYDATE_POST;
			break;
		case STATE_PER_PACKAGE_UPDATE:
			currentRequest = ct.PUT_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE_UPSERT_AND_DELETE;
			currentDataSendToServer = ct.TEST_BINARYDATE_UPDATE;
			break;
		case STATE_PER_PACKAGE_GET_2:
			currentRequest = ct.GET_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE_GET + "1111111111";
			currentDataSendToServer = null;
			break;
		case STATE_PER_PACKAGE_GET_1:
		case STATE_PER_PACKAGE_GET_3:
		case STATE_PER_PACKAGE_GET_4:
		case STATE_PER_PACKAGE_GET_5:
			currentRequest = ct.GET_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE_GET + "9999999999";
			currentDataSendToServer = null;
			break;
		case STATE_PER_PACKAGE_DELETE_1:
		case STATE_PER_PACKAGE_DELETE_2:
			currentRequest = ct.DELETE_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE_UPSERT_AND_DELETE;
			currentDataSendToServer = null;
			break;
		default:
			pmfc.printCurrentStateInfo();
		}
	}
}
