package com.fuhu.integration.java;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitOfStatusCheck extends SuperClient {
	@BeforeClass
	public static void setUpClass() throws Exception {

	}

	/* UPSERT API statuc check */
	@Test
	public void _A1_STATE_INSERT_APIKEY_MISSING_STATUS_CHECK() {
		sendRequest(StatesOfClient.STATE_INSERT_APIKEY_MISSING_STATUS_CHECK);
	}

	@Test
	public void _A2_STATE_GET_APIKEY_MISSING_STATUS_CHECK() {
		sendRequest(StatesOfClient.STATE_GET_APIKEY_MISSING_STATUS_CHECK);
	}

	@Test
	public void _A3_STATE_DELETE_APIKEY_MISSING_STATUS_CHECK() {
		sendRequest(StatesOfClient.STATE_DELETE_APIKEY_MISSING_STATUS_CHECK);
	}

	@Test
	public void _B1_STATE_INSERT_APIKEY_INVALID_STATUS_CHECK() {
		sendRequest(StatesOfClient.STATE_INSERT_APIKEY_INVALID_STATUS_CHECK);
	}

	@Test
	public void _B2_STATE_GET_APIKEY_INVALID_STATUS_CHECK() {
		sendRequest(StatesOfClient.STATE_GET_APIKEY_INVALID_STATUS_CHECK);
	}

	@Test
	public void _B3_STATE_DELETE_APIKEY_INVALID_STATUS_CHECK() {
		sendRequest(StatesOfClient.STATE_DELETE_APIKEY_INVALID_STATUS_CHECK);
	}

	@Test
	public void _C1_STATE_INSERT_ACCEPT_MISSING_STATUS_CHECK() {
		sendRequest(StatesOfClient.STATE_INSERT_ACCEPT_MISSING_STATUS_CHECK);
	}

	@Test
	public void _C2_STATE_GET_ACCEPT_MISSING_STATUS_CHECK() {
		sendRequest(StatesOfClient.STATE_GET_ACCEPT_MISSING_STATUS_CHECK);
	}

	@Test
	public void _C3_STATE_DELETE_ACCEPT_MISSING_STATUS_CHECK() {
		sendRequest(StatesOfClient.STATE_DELETE_ACCEPT_MISSING_STATUS_CHECK);
	}

	@Test
	public void _D1_STATE_INSERT_ACCEPT_INVALID_STATUS_CHECK() {
		sendRequest(StatesOfClient.STATE_INSERT_ACCEPT_INVALID_STATUS_CHECK);
	}

	@Test
	public void _D2_STATE_GET_ACCEPT_INVALID_STATUS_CHECK() {
		sendRequest(StatesOfClient.STATE_GET_ACCEPT_INVALID_STATUS_CHECK);
	}

	@Test
	public void _D3_STATE_DELETE_ACCEPT_INVALID_STATUS_CHECK() {
		sendRequest(StatesOfClient.STATE_DELETE_ACCEPT_INVALID_STATUS_CHECK);
	}

	@Test
	public void _E1_STATE_INSERT_CONTENTTYPE_MISSING_STATUS_CHECK() {
		sendRequest(StatesOfClient.STATE_INSERT_CONTENTTYPE_MISSING_STATUS_CHECK);
	}

	@Test
	public void _F1_STATE_INSERT_CONTENTTYPE_INVALID_STATUS_CHECK() {
		sendRequest(StatesOfClient.STATE_INSERT_CONTENTTYPE_INVALID_STATUS_CHECK);
	}
}