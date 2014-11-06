package com.fuhu.integration.java;

public class CurlCommandsSetterOfStatusCheck extends BehaviorOfCurlCommandsSetter {

	@Override
	public void execute(StatesOfClient state) {
		switch (state) {
		case STATE_INSERT_APIKEY_MISSING_STATUS_CHECK:
		case STATE_INSERT_APIKEY_INVALID_STATUS_CHECK:
		case STATE_INSERT_ACCEPT_MISSING_STATUS_CHECK:
		case STATE_INSERT_ACCEPT_INVALID_STATUS_CHECK:
		case STATE_INSERT_CONTENTTYPE_MISSING_STATUS_CHECK:
		case STATE_INSERT_CONTENTTYPE_INVALID_STATUS_CHECK:
			currentRequest = ct.PUT_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE_UPSERT_AND_DELETE;
			currentDataSendToServer = ct.TEST_BINARYDATE_POST;
			break;
		case STATE_GET_APIKEY_MISSING_STATUS_CHECK:
		case STATE_GET_APIKEY_INVALID_STATUS_CHECK:
		case STATE_GET_ACCEPT_MISSING_STATUS_CHECK:
		case STATE_GET_ACCEPT_INVALID_STATUS_CHECK:
			currentRequest = ct.GET_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE_GET + "9999999999";
			currentDataSendToServer = null;
			break;
		case STATE_DELETE_APIKEY_MISSING_STATUS_CHECK:
		case STATE_DELETE_APIKEY_INVALID_STATUS_CHECK:
		case STATE_DELETE_ACCEPT_MISSING_STATUS_CHECK:
		case STATE_DELETE_ACCEPT_INVALID_STATUS_CHECK:
			currentRequest = ct.DELETE_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE_UPSERT_AND_DELETE;
			currentDataSendToServer = null;
			break;
		default:
			pmfc.printCurrentStateInfo();
		}
	}
}
