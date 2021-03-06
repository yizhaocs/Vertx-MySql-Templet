package com.fuhu.integration.java;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitOfPerPackage extends SuperClient {
	@BeforeClass
	public static void setUpClass() throws Exception {

	}

	/* Check for insert is correct */
	@Test
	public void _A1_STATE_PER_PACKAGE_INSERT_1() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_INSERT_1);
	}

	@Test
	public void _A2_STATE_PER_PACKAGE_GET_1() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_GET_1);
	}
	
	/* Check for get is correct */
	@Test
	public void _B1_STATE_PER_PACKAGE_GET_2() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_GET_2);
	}

	/* Check for delete is correct */
	@Test
	public void _C1_STATE_PER_PACKAGE_DELETE_1() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_DELETE_1);
	}

	@Test
	public void _C2_STATE_PER_PACKAGE_GET_3() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_GET_3);
	}

	/* Check for update is correct */
	@Test
	public void _D1_STATE_PER_PACKAGE_INSERT_2() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_INSERT_2);
	}

	@Test
	public void _D2_STATE_PER_PACKAGE_UPDATE() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_UPDATE);
	}

	@Test
	public void _D3_STATE_PER_PACKAGE_GET_4() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_GET_4);
	}

	/* Clean up everything then check for all data are cleaned */
	@Test
	public void _E1_STATE_PER_PACKAGE_DELETE_2() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_DELETE_2);
	}

	@Test
	public void _E2_STATE_PER_PACKAGE_GET_5() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_GET_5);
	}
}
