package com.fuhu.integration.java;


public class CurlCommandsSetterOfPerPackageAndUser extends BehaviorOfCurlCommandsSetter {

	@Override
	public void execute(StatesOfClient state) {
		switch (state) {
		case STATE_PER_PACKAGE_AND_USER_INSERT_1:
		case STATE_PER_PACKAGE_AND_USER_INSERT_2:
		case STATE_PER_PACKAGE_AND_USER_UPDATE:
			currentRequest = ct.PUT_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE_AND_USER_UPSERT_AND_DELETE;
			currentDataSendToServer = ct.testBinaryData;
			break;
		case STATE_PER_PACKAGE_AND_USER_GET_2:
			currentRequest = ct.GET_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE_AND_USER_GET + "1111111111";
			currentDataSendToServer = null;
			break;
		case STATE_PER_PACKAGE_AND_USER_GET_1:
		case STATE_PER_PACKAGE_AND_USER_GET_3:
		case STATE_PER_PACKAGE_AND_USER_GET_4:
		case STATE_PER_PACKAGE_AND_USER_GET_5:
			currentRequest = ct.GET_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE_AND_USER_GET + "9999999999";
			currentDataSendToServer = null;
			break;
		case STATE_PER_PACKAGE_AND_USER_DELETE_1:
		case STATE_PER_PACKAGE_AND_USER_DELETE_2:
			currentRequest = ct.DELETE_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE_AND_USER_UPSERT_AND_DELETE;
			currentDataSendToServer = null;
			break;
		default:
			pmfc.printCurrentStateInfo();
		}
	}
}
