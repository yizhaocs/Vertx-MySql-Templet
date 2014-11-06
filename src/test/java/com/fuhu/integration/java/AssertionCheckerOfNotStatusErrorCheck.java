package com.fuhu.integration.java;

import static org.vertx.testtools.VertxAssert.assertEquals;
import static org.vertx.testtools.VertxAssert.assertNotNull;

import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.json.JsonObject;

public class AssertionCheckerOfNotStatusErrorCheck implements BehaviorOfAssertionChecker {

	@Override
	public void execute(StatesOfClient state, JsonObject currentServerResponseInJsonFormat, Buffer currentServerResponseInBinaryStreamFormat, int statusCode) {
		switch (state) {
		/* Check for insert is correct */
		case STATE_PER_PACKAGE_INSERT_1:
		case STATE_PER_PACKAGE_AND_USER_INSERT_1:
			ct.mapStates.put(state, true);
			assertEquals(200, statusCode);
			assetionsForGeneral(currentServerResponseInJsonFormat);
			assertNotNull(currentServerResponseInJsonFormat.getString(ct.LAST_TIME_MODIFIED));
			break;
		case STATE_PER_PACKAGE_GET_1:
		case STATE_PER_PACKAGE_AND_USER_GET_1:
			ct.mapStates.put(state, true);
			assertEquals(200, statusCode);
			assertEquals(ct.TEST_BINARYDATE_POST, currentServerResponseInBinaryStreamFormat);
			break;
		/* Check for get is correct */
		case STATE_PER_PACKAGE_GET_2:
		case STATE_PER_PACKAGE_AND_USER_GET_2:
			ct.mapStates.put(state, true);
			assertEquals(404, statusCode);
			break;
		/* Check for delete is correct */
		case STATE_PER_PACKAGE_DELETE_1:
		case STATE_PER_PACKAGE_AND_USER_DELETE_1:
			ct.mapStates.put(state, true);
			assertEquals(200, statusCode);
			assetionsForGeneral(currentServerResponseInJsonFormat);
			break;
		case STATE_PER_PACKAGE_GET_3:
		case STATE_PER_PACKAGE_AND_USER_GET_3:
			ct.mapStates.put(state, true);
			assertEquals(404, statusCode);
			break;
		/* Check for update is correct */
		case STATE_PER_PACKAGE_INSERT_2:
		case STATE_PER_PACKAGE_AND_USER_INSERT_2:
			ct.mapStates.put(state, true);
			assertEquals(200, statusCode);
			assetionsForGeneral(currentServerResponseInJsonFormat);
			assertNotNull(currentServerResponseInJsonFormat.getString(ct.LAST_TIME_MODIFIED));
			break;
		case STATE_PER_PACKAGE_UPDATE:
		case STATE_PER_PACKAGE_AND_USER_UPDATE:
			ct.mapStates.put(state, true);
			assertEquals(200, statusCode);
			assetionsForGeneral(currentServerResponseInJsonFormat);
			break;
		case STATE_PER_PACKAGE_GET_4:
		case STATE_PER_PACKAGE_AND_USER_GET_4:
			ct.mapStates.put(state, true);
			assertEquals(200, statusCode);
			assertEquals(ct.TEST_BINARYDATE_UPDATE, currentServerResponseInBinaryStreamFormat);
			break;
		/* Clean up everything then check for all data are cleaned */
		case STATE_PER_PACKAGE_DELETE_2:
		case STATE_PER_PACKAGE_AND_USER_DELETE_2:
			ct.mapStates.put(state, true);
			assertEquals(200, statusCode);
			assetionsForGeneral(currentServerResponseInJsonFormat);
			break;
		case STATE_PER_PACKAGE_GET_5:
		case STATE_PER_PACKAGE_AND_USER_GET_5:
			ct.mapStates.put(state, true);
			assertEquals(404, statusCode);
			break;
		default:
		}
	}

	/* Assertion methods for status error */
	private void assetionsForGeneral(JsonObject currentServerResponseInJsonFormat) {
		assertNotNull(currentServerResponseInJsonFormat.getString(ct.NABI_CLIENT_DATA_BACKUP_APIVersion_K));
		assertEquals(ct.NABI_CLIENT_DATA_BACKUP_APIVersion_V, currentServerResponseInJsonFormat.getString(ct.NABI_CLIENT_DATA_BACKUP_APIVersion_K));
		assertNotNull(currentServerResponseInJsonFormat.getString(ct.STATUS));
		assertEquals(ct.OK, currentServerResponseInJsonFormat.getString(ct.STATUS));
	}
}
