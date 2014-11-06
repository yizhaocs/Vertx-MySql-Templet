package com.fuhu.integration.java;

import static org.vertx.testtools.VertxAssert.assertEquals;
import static org.vertx.testtools.VertxAssert.assertNotNull;

import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.json.JsonObject;

public class AssertionCheckerOfStatusErrorCheck implements BehaviorOfAssertionChecker {

	@Override
	public void execute(StatesOfClient state, JsonObject currentServerResponseInJsonFormat, Buffer currentServerResponseInBinaryStreamFormat, int statusCode) {
		switch (state) {
		/* Check for APIKEY MISSING STATUS */
		case STATE_INSERT_APIKEY_MISSING_STATUS_CHECK:
		case STATE_GET_APIKEY_MISSING_STATUS_CHECK:
		case STATE_DELETE_APIKEY_MISSING_STATUS_CHECK:
			ct.mapStates.put(state, true);
			assertionsForStatusError(ct.MISSING_API_KEY, state, currentServerResponseInJsonFormat, currentServerResponseInBinaryStreamFormat, statusCode);
			break;
		/* Check for APIKEY INVALID STATUS */
		case STATE_INSERT_APIKEY_INVALID_STATUS_CHECK:
		case STATE_GET_APIKEY_INVALID_STATUS_CHECK:
		case STATE_DELETE_APIKEY_INVALID_STATUS_CHECK:
			ct.mapStates.put(state, true);
			assertionsForStatusError(ct.INVALID_API_KEY, state, currentServerResponseInJsonFormat, currentServerResponseInBinaryStreamFormat, statusCode);
			break;
		/* Check for ACCEPT MISSING STATUS */
		case STATE_INSERT_ACCEPT_MISSING_STATUS_CHECK:
		case STATE_GET_ACCEPT_MISSING_STATUS_CHECK:
		case STATE_DELETE_ACCEPT_MISSING_STATUS_CHECK:
			ct.mapStates.put(state, true);
			assertionsForStatusError(ct.MISSING_ACCEPT_HEADER, state, currentServerResponseInJsonFormat, currentServerResponseInBinaryStreamFormat, statusCode);
			break;
		/* Check for ACCEPT INVALID STATUS */
		case STATE_INSERT_ACCEPT_INVALID_STATUS_CHECK:
		case STATE_GET_ACCEPT_INVALID_STATUS_CHECK:
		case STATE_DELETE_ACCEPT_INVALID_STATUS_CHECK:
			ct.mapStates.put(state, true);
			assertionsForStatusError(ct.INVALID_ACCEPT_HEADER, state, currentServerResponseInJsonFormat, currentServerResponseInBinaryStreamFormat, statusCode);
			break;
		/* Check for CONTENT TYPE MISSING STATUS */
		case STATE_INSERT_CONTENTTYPE_MISSING_STATUS_CHECK:
			ct.mapStates.put(state, true);
			assertionsForStatusError(ct.MISSING_CONTENT_TYPE_HEADER, state, currentServerResponseInJsonFormat, currentServerResponseInBinaryStreamFormat, statusCode);
			break;
		/* Check for CONTENT TYPE INVALID STATUS */
		case STATE_INSERT_CONTENTTYPE_INVALID_STATUS_CHECK:
			ct.mapStates.put(state, true);
			assertEquals(200, statusCode);
			assertionsForStatusError(ct.INVALID_CONTENT_TYPE_HEADER, state, currentServerResponseInJsonFormat, currentServerResponseInBinaryStreamFormat, statusCode);
			break;
		default:
		}
	}

	private void assertionsForStatusError(String target, StatesOfClient state, JsonObject currentServerResponseInJsonFormat, Buffer currentServerResponseInBinaryStreamFormat, int statusCode) {
		assertEquals(200, statusCode);
		assertNotNull(currentServerResponseInJsonFormat.getString(ct.NABI_CLIENT_DATA_BACKUP_APIVersion_K));
		assertEquals(ct.NABI_CLIENT_DATA_BACKUP_APIVersion_V, currentServerResponseInJsonFormat.getString(ct.NABI_CLIENT_DATA_BACKUP_APIVersion_K));
		assertNotNull(currentServerResponseInJsonFormat.getString(ct.STATUS));
		assertEquals(target, currentServerResponseInJsonFormat.getString(ct.STATUS));
	}
}
